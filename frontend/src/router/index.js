import { createRouter, createWebHistory } from 'vue-router'
import CarForm from '@/views/CarForm.vue'
import LoginView from '@/views/LoginView.vue'
import AdminPanel from '@/views/AdminPanel.vue'

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'LoginView',
        component: LoginView
    },
    {
        path: '/form',
        name: 'CarForm',
        component: CarForm
    },
    {
        path: '/admin',
        name: 'AdminPanel',
        component: AdminPanel
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
