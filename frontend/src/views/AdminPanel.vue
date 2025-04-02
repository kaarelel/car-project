<template>
  <div>
    <h2>Lisa uusi automudeleid</h2>

    <label>Automark:</label>
    <select v-model="selectedBrand">
      <option disabled value="">-- Vali mark --</option>
      <option v-for="brand in brands" :key="brand">{{ brand }}</option>
    </select>

    <label>Uus mudeli nimi:</label>
    <input v-model="newModel" placeholder="Näiteks: A4" />

    <button @click="addModel">Lisa mudel</button>
    <p v-if="success">{{ success }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const brands = ref([])
const selectedBrand = ref('')
const newModel = ref('')
const success = ref('')

const fetchBrands = async () => {
  const res = await axios.get('http://localhost:8080/api/brands', { withCredentials: true })
  brands.value = res.data
}

const addModel = async () => {
  await axios.post('http://localhost:8080/api/models', {
    brand: selectedBrand.value,
    model: newModel.value
  }, { withCredentials: true })

  success.value = 'Mudel lisatud!'
  newModel.value = ''
}

onMounted(() => {
  fetchBrands()
})
</script>
