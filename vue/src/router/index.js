import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue'
import AdminView from "@/views/AdminView.vue";
import Layout from "@/views/Layout.vue";

Vue.use(VueRouter)

const routes = [
    //登录路由
  {
    path: '/',
    name: 'Login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView
  },
  {
    path: '/index',
    name: 'Layout',
    component: Layout,
    children: [ // 子路由
       {
         path: '',
         name: 'home',
         component: HomeView
       },
      {
        path: 'admin',
        name: 'admin',
        component: AdminView
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
