<template>
  <div class="form-wrapper">
    <h2 class="mb-4 text-center">{{ $t('title') }}</h2>
    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <label class="form-label">{{ $t('name') }}</label>
        <input v-model="form.fullName" class="form-control" type="text" :placeholder="$t('name')" required />
      </div>

      <div class="mb-3">
        <label class="form-label">{{ $t('phone') }}</label>
        <input v-model="form.phoneNumber" class="form-control" type="tel" :placeholder="$t('phone')" required />
      </div>

      <div class="mb-3">
        <label class="form-label">{{ $t('brand') }}</label>
        <select v-model="form.selectedBrand" class="form-select" required>
          <option disabled value="">{{ $t('brand') }}</option>
          <option v-for="brand in brands" :key="brand.code" :value="brand.code">{{ brand.name }}</option>
          <option value="MUU">Muu</option>
        </select>
      </div>

      <div v-if="form.selectedBrand === 'MUU'" class="mb-3">
        <label class="form-label">Sisesta automark: </label>
        <input v-model="form.manualBrand" class="form-control" type="text" />
      </div>

      <div class="mb-3">
        <label class="form-label">{{ $t('model') }}</label>
        <select v-model="form.selectedModel" class="form-select" :disabled="form.selectedBrand === ''">
          <option disabled value="">{{ $t('model') }}</option>
          <option v-for="model in availableModels" :key="model" :value="model">{{ model }}</option>
          <option value="MUU">Muu</option>
        </select>
      </div>

      <div v-if="form.selectedModel === 'MUU'" class="mb-3">
        <label class="form-label">Sisesta mudel: </label>
        <input v-model="form.manualModel" class="form-control" type="text" />
      </div>

      <div class="form-check mb-3">
        <input v-model="form.hasDriverLicense" class="form-check-input" type="checkbox" id="licenseCheck" />
        <label class="form-check-label" for="licenseCheck">{{ $t('license') }}</label>
      </div>

      <div class="d-flex gap-2">
        <button type="submit" class="btn btn-primary w-100">{{ $t('submit') }}</button>
        <button type="button" @click="resetForm" class="btn btn-outline-secondary w-100">{{ $t('reset') }}</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, watch } from 'vue'
import { useI18n } from 'vue-i18n'
import axios from 'axios'

const { locale } = useI18n()

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
let lastRequestedBrand = ''

const changeLocale = () => {
  localStorage.setItem('lang', locale.value)
}

onMounted(async () => {
  const savedLang = localStorage.getItem('lang')
  if (savedLang) locale.value = savedLang

  try {
    const brandRes = await axios.get('/api/brands')
    if (Array.isArray(brandRes.data)) {
      brands.value = brandRes.data.sort((a, b) => a.name.localeCompare(b.name))
    }

    const res = await axios.get('/api/my-submission')
    const data = res.data
    if (data) {
      form.fullName = data.fullName
      form.phoneNumber = data.phoneNumber
      form.selectedBrand = data.selectedBrand
      form.selectedModel = data.selectedModel
      form.hasDriverLicense = data.hasDriverLicense
      if (form.selectedBrand && form.selectedBrand !== 'MUU') {
        await loadModels(form.selectedBrand)
      }
    }
  } catch (err) {
    console.warn('Andmete laadimisel tekkis probleem:', err)
  }
})

watch(() => form.selectedBrand, async (brandCode) => {
  if (!brandCode || brandCode === 'MUU' || brandCode === lastRequestedBrand) return
  await loadModels(brandCode)
})

async function loadModels(brandCode) {
  lastRequestedBrand = brandCode
  try {
    const res = await axios.get(`/api/models?brandCode=${brandCode}`)
    availableModels.value = res.data
  } catch (err) {
    console.error("Mudelite laadimine ebaõnnestus:", err)
  }
}

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
    alert('Andmed salvestatud edukalt!')
  } catch (err) {
    alert('Salvestamine ebaõnnestus')
    console.error(err)
  }
}

const resetForm = async () => {
  try {
    await axios.post('/api/reset')
    Object.assign(form, {
      fullName: '',
      phoneNumber: '',
      selectedBrand: '',
      selectedModel: '',
      manualBrand: '',
      manualModel: '',
      hasDriverLicense: false
    })
    availableModels.value = []
    sessionStorage.removeItem('submissionId')
  } catch (err) {
    console.error('Sessiooni tühjendamine ebaõnnestus:', err)
  }
}
</script>

<style scoped>
.form-wrapper {
  max-width: 600px;
  margin: auto;
  padding: 1.5rem;
}

form > .mb-3 {
  margin-bottom: 1.25rem;
}

label.form-label {
  margin-bottom: 0.4rem;
  font-weight: 500;
}

.form-check {
  margin-top: 1rem;
  margin-bottom: 1.5rem;
}

@media (max-width: 576px) {
  .form-wrapper {
    padding: 1rem 0.5rem;
  }

  .form-label {
    font-size: 0.95rem;
  }

  .form-control,
  .form-select,
  .form-check-label,
  button {
    font-size: 0.95rem;
  }

  .btn {
    padding: 0.5rem 0.8rem;
  }

  form > .mb-3 {
    margin-bottom: 1rem;
  }
}
</style>


