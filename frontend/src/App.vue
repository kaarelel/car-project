<template>
  <div class="container mt-4">
    <div class="d-flex justify-content-end mb-4">
      <div class="language-switcher">
        <label for="lang-select" class="me-2">{{ $t('language') }}:</label>
        <select id="lang-select" v-model="locale" class="form-select form-select-sm w-auto">
          <option value="et">Eesti</option>
          <option value="en">English</option>
        </select>
      </div>
    </div>

    <router-view />
  </div>
</template>

<script setup>
import { useI18n } from 'vue-i18n'

const { locale } = useI18n()
</script>

<style scoped>
.language-switcher {
  display: flex;
  align-items: center;
  gap: 8px;
}

#lang-select {
  min-width: 120px;
  padding: 4px 8px;
  border-radius: 6px;
  border: 1px solid #ccc;
}
</style>
