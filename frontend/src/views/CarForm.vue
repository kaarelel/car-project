<template>
  <div>
    <h2>Vali automarke ja mudeleid</h2>

    <label>Automark:</label>
    <select v-model="selectedBrand" @change="fetchModels">
      <option disabled value="">-- Vali mark --</option>
      <option v-for="brand in brands" :key="brand">{{ brand }}</option>
    </select>

    <label>Mudelid:</label>
    <select multiple v-model="selectedModels">
      <option v-for="model in models" :key="model">{{ model }}</option>
    </select>

    <button @click="save">Salvesta</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const brands = ref([])
const models = ref([])
const selectedBrand = ref('')
const selectedModels = ref([])

const fetchBrands = async () => {
  const res = await axios.get('http://localhost:8080/api/brands', { withCredentials: true })
  brands.value = res.data
}

const fetchModels = async () => {
  const res = await axios.get(`http://localhost:8080/api/models?brand=${selectedBrand.value}`, {
    withCredentials: true
  })
  models.value = res.data
}

const save = async () => {
  // Tulevikus: POST kasutaja valikud
  alert(`Salvestatakse: ${selectedBrand.value} / ${selectedModels.value.join(', ')}`)
}

onMounted(() => {
  fetchBrands()
})
</script>
