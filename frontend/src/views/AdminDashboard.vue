<template>
  <div class="dashboard-wrapper">
    <h2 class="mb-4 text-center">{{ $t('adminTitle') }}</h2>

    <div v-if="submissions.length === 0" class="alert alert-info">
      {{ $t('noSubmissions') }}
    </div>

    <div class="table-responsive">
      <table class="table table-bordered table-striped align-middle">
        <thead class="table-light">
        <tr>
          <th>{{ $t('fullName') }}</th>
          <th>{{ $t('phoneNumber') }}</th>
          <th>{{ $t('selectedBrand') }}</th>
          <th>{{ $t('selectedModel') }}</th>
          <th>{{ $t('hasDriverLicense') }}</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="submission in sortedSubmissions" :key="submission.id">
          <td>{{ submission.fullName }}</td>
          <td>{{ submission.phoneNumber }}</td>
          <td>{{ submission.selectedBrand }}</td>
          <td>{{ submission.selectedModel }}</td>
          <td>{{ submission.hasDriverLicense ? $t('yes') : $t('no') }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'

const submissions = ref([])

onMounted(async () => {
  try {
    const res = await axios.get('/api/submissions')
    submissions.value = res.data
  } catch (err) {
    console.error('Andmete laadimine ebaõnnestus:', err)
  }
})

const sortedSubmissions = computed(() => {
  return [...submissions.value].reverse()
})
</script>

<style scoped>
.dashboard-wrapper {
  max-width: 1000px;
  margin: auto;
  padding: 1.5rem;
}

.table th {
  font-weight: 600;
  background-color: #f8f9fa;
}

.table td,
.table th {
  padding: 0.75rem;
  vertical-align: middle;
}

@media (max-width: 768px) {
  .table {
    font-size: 0.875rem;
  }

  .dashboard-wrapper {
    padding: 1rem 0.5rem;
  }

  .table th,
  .table td {
    padding: 0.5rem;
  }
}
</style>


