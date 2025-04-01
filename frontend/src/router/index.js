import { createRouter, createWebHistory } from 'vue-router'
import CarForm from '@/views/CarForm.vue'

const routes = [
    {
        path: '/',
        name: 'CarForm',
        component: CarForm
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
