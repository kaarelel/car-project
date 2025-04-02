<template>
  <div class="container mt-4">
    <h2 class="mb-4">Kliendi sisestused</h2>
    <table v-if="submissions.length" class="table table-striped">
      <thead>
      <tr>
        <th>Nimi</th>
        <th>Telefon</th>
        <th>Mark</th>
        <th>Mudel</th>
        <th>Juhiluba</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="entry in submissions" :key="entry.id">
        <td>{{ entry.fullName }}</td>
        <td>{{ entry.phoneNumber }}</td>
        <td>{{ entry.selectedBrand }}</td>
        <td>{{ entry.selectedModel }}</td>
        <td>{{ entry.hasDriverLicense ? 'Jah' : 'Ei' }}</td>
      </tr>
      </tbody>
    </table>
    <div v-else>
      <p>Andmeid pole veel sisestatud.</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const submissions = ref([])

onMounted(async () => {
  try {
    const res = await axios.get('/api/submissions')
    submissions.value = res.data
  } catch (err) {
    console.error('Viga andmete laadimisel:', err)
  }
})
</script>

<style scoped>
.container {
  max-width: 900px;
}
</style>
