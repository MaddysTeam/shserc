import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/layouts/index'
import Login from '@/views/login'
import UserList from '@/views/user/list'
import store from '../store/store'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [{
        path: '/users',
        name: 'UserList',
        component: UserList
      }]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
});

/* route protector */
router.beforeEach((to, from, next) => {
  // ...
  if (to.path == '/login') {
    next()
  }
  else {
    console.log("isAuth:"+store.state.isAuth)
    console.log("next:"+to)
    if (store.state.isAuth) {
      next()
    }
    else {
      next({
        path: '/login'
      });
    }
  }

});

export default router;