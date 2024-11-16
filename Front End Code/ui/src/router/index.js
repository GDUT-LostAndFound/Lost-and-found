import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/RegisterView.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/HomeView.vue')
  },
  {
    path: '/personalhome',
    name: 'personalhome',
    component: () => import('../views/PersonalHomeView.vue')
  },
  {
    path: '/post',
    name: 'post',
    component: () => import('../views/PostView.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
