<template>
  <div class="container mt-4">
    <h2 class="mb-4">
      Palun sisestage enda kontaktandmed ning automargid, millest olete huvitatud
    </h2>

    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <label class="form-label">Ees- ja perekonnanimi:</label>
        <input v-model="form.fullName" required class="form-control" type="text" />
      </div>

      <div class="mb-3">
        <label class="form-label">Kontakttelefon:</label>
        <input v-model="form.phoneNumber" required class="form-control" type="text" />
      </div>

      <div class="mb-3">
        <label class="form-label">Automark:</label>
        <select v-model="form.selectedBrand" required class="form-select">
          <option disabled value="">Vali automark</option>
          <option v-for="brand in brands" :key="brand.code" :value="brand.code">
            {{ brand.name }}
          </option>
          <option value="MUU">Muu</option>
        </select>
      </div>

      <div v-if="form.selectedBrand === 'MUU'" class="mb-3">
        <label class="form-label">Sisesta automark:</label>
        <input v-model="form.manualBrand" class="form-control" type="text" required />
      </div>

      <div class="mb-3">
        <label class="form-label">Automudel:</label>
        <select v-model="form.selectedModel"
                required
                class="form-select"
                :disabled="form.selectedBrand && form.selectedBrand !== 'MUU' && availableModels.length === 0">
          <option disabled value="">Vali mudel</option>
          <option v-for="model in availableModels" :key="model" :value="model">
            {{ model }}
          </option>
          <option value="MUU">Muu</option>
        </select>
      </div>

      <div v-if="form.selectedModel === 'MUU'" class="mb-3">
        <label class="form-label">Sisesta mudel:</label>
        <input v-model="form.manualModel" class="form-control" type="text" required />
      </div>

      <div class="form-check mb-3">
        <input v-model="form.hasDriverLicense" class="form-check-input" type="checkbox" id="driverLicenseCheck" required />
        <label class="form-check-label" for="driverLicenseCheck">
          Kas Teil on kehtiv juhiluba?
        </label>
      </div>

      <button type="submit" class="btn btn-primary">Salvesta</button>
    </form>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
import axios from 'axios'

const form = reactive({
  fullName: '',
  phoneNumber: '',
  selectedBrand: '',
  selectedModel: '',
  manualBrand: '',
  manualModel: '',
  hasDriverLicense: false
})

const brands = ref([])
const availableModels = ref([])

const loadBrands = async () => {
  try {
    const brandRes = await axios.get('/api/brands')
    const data = brandRes.data

    if (Array.isArray(data)) {
      brands.value = data.sort((a, b) => a.name.localeCompare(b.name))
    } else {
      console.warn('Tagastatud automargid ei ole massiiv:', data)
    }
  } catch (err) {
    console.error('Automarkide laadimine ebaõnnestus:', err)
  }
}

const loadModels = async (brandCode) => {
  try {
    const res = await axios.get(`/api/models?brandCode=${brandCode}`)
    availableModels.value = Array.isArray(res.data) ? [...new Set(res.data)] : []
    console.log("✅ Laetud mudelid:", availableModels.value)
  } catch (err) {
    console.error("Mudelite laadimine ebaõnnestus:", err)
  }
}


onMounted(async () => {
  await loadBrands()

  const submissionId = sessionStorage.getItem('submissionId')
  if (submissionId) {
    try {
      const res = await axios.get(`/api/submission/${submissionId}`)
      const data = res.data

      form.fullName = data.fullName
      form.phoneNumber = data.phoneNumber
      form.selectedBrand = data.selectedBrand
      form.selectedModel = data.selectedModel
      form.hasDriverLicense = data.hasDriverLicense

      if (form.selectedBrand && form.selectedBrand !== 'MUU') {
        await loadModels(form.selectedBrand)
      }
    } catch (err) {
      console.warn("Eelmise sisestuse laadimine ebaõnnestus:", err)
    }
  }
})

watch(() => form.selectedBrand, async (newBrand) => {
  console.log("🔁 Brand changed to:", newBrand)
  if (!newBrand || newBrand === 'MUU') {
    availableModels.value = []
    return
  }

  await loadModels(newBrand)
  console.log("✅ Available models after load:", availableModels.value)
})

const handleSubmit = async () => {
  const selectedBrand = form.selectedBrand === 'MUU' ? form.manualBrand : form.selectedBrand
  const selectedModel = form.selectedModel === 'MUU' ? form.manualModel : form.selectedModel

  const payload = {
    fullName: form.fullName,
    phoneNumber: form.phoneNumber,
    selectedBrand,
    selectedModel,
    hasDriverLicense: form.hasDriverLicense
  }

  try {
    const res = await axios.post('/api/submit', payload)
    sessionStorage.setItem('submissionId', res.data)
    alert('✅ Andmed salvestatud edukalt!')
  } catch (err) {
    alert('Salvestamine ebaõnnestus')
    console.error(err)
  }
}

</script>


<style scoped>
.container {
  max-width: 600px;
}
</style>
