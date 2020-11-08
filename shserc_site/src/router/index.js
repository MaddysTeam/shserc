import Vue from 'vue'
import Router from 'vue-router'
import Admin from '@/layouts/admin/index'
import Login from '@/views/admin/login'
import UserList from '@/views/admin/user/list'
import CompanyList from '@/views/admin/company/list'
import ResourceList from '@/views/admin/resource/list'
import ResourceEdit from '@/views/admin/resource/edit'
import store from '../store/store'

Vue.use(Router)

const router = new Router({
  routes: [
    // frontend
    {
      path: '/',
      name: 'index',
      component: Admin, //TODO:这里改成前台
    },
    {
      path: '/home',
      name: 'home',
      component: Admin, //TODO:这里改成前台
    },
    // backend
    {
      path: '/admin',
      name: 'Index',
      component: Admin,
      children: [{
        path: '/admin/user/list',
        name: 'UserList',
        component: UserList
      },
      {
        path: '/admin/company/list',
        name: 'CompanyList',
        component: CompanyList
      },{
        path:'/admin/resource/list',
        name: 'ResourceList',
        component: ResourceList
      },{
        path:'/admin/resource/add/',
        name:'ResourceAdd',
        component:ResourceEdit
      },{
        path:'/admin/resource/edit/:id',
        name:'ResourceEdit',
        component:ResourceEdit
      }
      ]
    },
    {
      path: '/admin/login',
      name: 'Login',
      component: Login
    },

    // {
    //   path: "*",
    //   redirect: "/home"
    // }
  ]
});

/* route protector */
router.beforeEach((to, from, next) => {
  // ...
  if (to.path.indexOf('/admin') < 0) {
    next()
  }
  else {
    if (to.path.indexOf('admin') >= 0) {
      if (store.state.isAuth) {
        next()
      }
      else {
        next({
          path: '/login'
        });
      }
    }
  }

});

export default router;