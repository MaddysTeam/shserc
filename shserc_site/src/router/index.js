import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/layouts/index'
import Login from '@/views/login'
import UserList from '@/Views/user/list'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component:Index
    },
    {
      path:'/login',
      name:'Login',
      component: Login
    },
    {
      path:'/users',
      name:'UserList',
      component:UserList
    }
  ]
})
