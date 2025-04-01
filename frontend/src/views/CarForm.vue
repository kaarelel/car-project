<template>
  <div class="container mt-5">
    <h2 class="mb-4">Palun sisestage enda kontaktandmed ning automargid, millest olete huvitatud</h2>

    <form @submit.prevent="handleSubmit">
      <div class="mb-3">
        <label for="name" class="form-label">Ees- ja perekonnanimi</label>
        <input
            id="name"
            v-model="form.name"
            type="text"
            class="form-control"
            required
        />
      </div>

      <div class="mb-3">
        <label for="phone" class="form-label">Kontakttelefon</label>
        <input
            id="phone"
            v-model="form.phone"
            type="tel"
            class="form-control"
            required
        />
      </div>

      <div class="mb-3">
        <label for="brands" class="form-label">Automargid</label>
        <select
            id="brands"
            v-model="form.brands"
            class="form-select"
            multiple
            required
        >
          <optgroup label="Mercedes-Benz">
            <option value="C 160">C 160</option>
            <option value="C 180">C 180</option>
            <option value="C 200">C 200</option>
            <option value="C 220">C 220</option>
          </optgroup>
          <optgroup label="BMW">
            <option value="315">315</option>
            <option value="316">316</option>
            <option value="317">317</option>
            <option value="318">318</option>
            <option value="319">319</option>
          </optgroup>
          <optgroup label="Audi">
            <option value="Q2">Q2</option>
            <option value="Q3">Q3</option>
            <option value="Q4">Q4</option>
            <option value="Q5">Q5</option>
            <option value="RS4">RS4</option>
          </optgroup>
          <option value="Citroën">Citroën</option>
          <option value="Muu">Muu</option>
        </select>
      </div>

      <div class="form-check mb-3">
        <input
            id="license"
            v-model="form.hasLicense"
            type="checkbox"
            class="form-check-input"
        />
        <label for="license" class="form-check-label">Kas Teil on kehtiv juhiluba?</label>
      </div>

      <button type="submit" class="btn btn-primary">Salvesta</button>
    </form>

    <div class="mt-4" v-if="submitted">
      <h5>Andmed salvestatud:</h5>
      <pre>{{ form }}</pre>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'

const form = reactive({
  name: '',
  phone: '',
  brands: [],
  hasLicense: false,
})

const submitted = ref(false)

const handleSubmit = () => {
  submitted.value = true
  console.log('Salvestatud andmed:', form)

  // TODO: saata andmed backendi
}
</script>

<style scoped>
select[multiple] {
  height: 200px;
}
</style>
