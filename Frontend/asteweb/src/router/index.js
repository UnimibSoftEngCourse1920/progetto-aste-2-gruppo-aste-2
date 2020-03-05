import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import LoginPage from '../views/LoginPage.vue'
import AdminPage from '../views/AdminPage.vue'
import TransactionsPage from '../views/TransactionsPage.vue'

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
    path: '/admin:id',
    name: 'admin',
    component: AdminPage
  },
  {
    path: '/transactions',
    name: '/transactions',
    component: TransactionsPage
  }


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
