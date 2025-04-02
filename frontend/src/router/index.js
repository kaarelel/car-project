import { createRouter, createWebHistory } from 'vue-router'
import ClientForm from "@/views/ClientForm.vue";
import AdminDashboard from "@/views/AdminDashboard.vue";

const routes = [
    {
        path: '/',
        name: 'ClientForm',
        component: ClientForm
    },
    {
        path: '/admin',
        name: 'AdminDashboard',
        component: AdminDashboard
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
