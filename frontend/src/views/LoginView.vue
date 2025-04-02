<template>
  <div class="login">
    <h2>Palun logi sisse</h2>
    <form @submit.prevent="handleLogin">
      <input v-model="username" placeholder="Kasutajanimi" />
      <input v-model="password" placeholder="Parool" type="password" />
      <button type="submit">Logi sisse</button>
    </form>
    <p v-if="error" style="color: red">{{ error }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const username = ref('')
const password = ref('')
const error = ref('')
const router = useRouter()

const handleLogin = async () => {
  try {
    await axios.post('http://localhost:8080/login', new URLSearchParams({
      username: username.value,
      password: password.value
    }), {
      withCredentials: true
    })

    const res = await axios.get('http://localhost:8080/api/me', {
      withCredentials: true
    })

    const role = res.data.role
    if (role === 'ADMIN') {
      router.push('/admin')
    } else {
      router.push('/form')
    }
  } catch (err) {
    error.value = 'Bad credentials'
  }
}
</script>
