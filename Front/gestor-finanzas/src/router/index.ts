import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue'
import ClientePage from '../views/ClientePage.vue'
import LoginPage from '../views/LoginPage.vue'
import DasboardPage from '../views/DasboardPage.vue'
import PersonaPage from '../views/PersonaPage.vue'
import InicioPage from '@/views/InicioPage.vue';
import ProductoPage from '@/views/GastoPage.vue';
import RegistroPage from '@/views/RegistroPage.vue';
import GastoPage from '@/views/GastoPage.vue';
import IngresoPage from '@/views/IngresoPage.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/inicio'
  },
  {
    path: '/cliente',
    name: 'Cliente',
    component: ClientePage
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginPage
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: DasboardPage
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
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router