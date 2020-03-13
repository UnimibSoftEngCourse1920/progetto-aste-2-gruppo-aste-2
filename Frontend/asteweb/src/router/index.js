import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import LoginPage from '../views/LoginPage.vue'
import AdminPage from '../views/AdminPage.vue'
import TransactionsPage from '../views/TransactionsPage.vue'
import UltimaConfig from '../views/UltimaConfig.vue'
import CreaConfig from '../views/CreaConfig.vue'
import UtenteCredito from '../views/UtenteCredito.vue'
import UserPage from '../views/UserPage.vue'
import UtenteGestione from '../views/UtenteGestionePage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: LoginPage
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminPage
  },
  {
    path: '/transactions',
    name: 'transactions',
    component: TransactionsPage
  },
  {
    path: '/ultimaconfig',
    name: 'ultimaconfig',
    component: UltimaConfig
  },
  {
    path: '/creaconfig',
    name: 'creaconfig',
    component: CreaConfig
  },
  {
    path: '/user',
    name: 'user',
    component: UserPage
  },
  {
    path: '/utentecredito',
    name: 'utentecredito',
    component: UtenteCredito
  },
  {
    path: '/utentegestione',
    name: 'utentegestione',
    component: UtenteGestione
  }


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
