import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import LoginPage from '../views/LoginPage.vue'
import DashboardPage from '../views/DashboardPage.vue';
import PersonaPage from '../views/PersonaPage.vue'
import InicioPage from '@/views/InicioPage.vue';
import RegistroPage from '@/views/RegistroPage.vue';
import GastoPage from '@/views/GastoPage.vue';
import IngresoPage from '@/views/IngresoPage.vue';
import DashboardAdminPage from '@/views/DashboardAdminPage.vue';
import CategoriaPage from '@/views/CategoriaPage.vue';
import FinanzaPage from '@/views/FinanzaPage.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/inicio'
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: DashboardPage
  },
  {
    path: '/gasto',
    name: 'Gasto',
    component: GastoPage
  },
  {
    path: '/persona',
    name: 'Persona',
    component: PersonaPage
  },
  {
    path: '/inicio',
    name: 'Inicio',
    component: InicioPage
  },
  {
    path: '/registro',
    name: 'Registro',
    component: RegistroPage
  },
  {
    path:'/ingreso',
    name: 'Ingreso',
    component: IngresoPage
  },
  {
    path:'/dashboardAdmin',
    name: 'DashboardAdmin',
    component: DashboardAdminPage
  },
  {
    path: '/categoria',
    name: 'Categoria',
    component: CategoriaPage
  },
  {
    path: '/finanza',
    name: 'Finanza',
    component: FinanzaPage
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
