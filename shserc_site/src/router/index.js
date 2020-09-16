import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/layouts/index'
import Login from '@/views/login'
import UserList from '@/views/user/list'
import CompanyList from '@/views/company/list'
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
      },
      {
        path: '/company',
        name: 'CompanyList',
        component: CompanyList
      }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },

    {
      path: "*",
      redirect: "/"
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