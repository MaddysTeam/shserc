import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/layouts/index'
import Login from '@/views/login'
import UserList from '@/Views/user/list'

Vue.use(Router)

const router= new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component:Index,
      children:[{
        path:'/users',
        name:'UserList',
        component:UserList
      }]
    },
    {
      path:'/login',
      name:'Login',
      component: Login
    }
  ]
});

/* route protector */
router.beforeEach((to, from, next) => {
  // ...
  if(to.path=='/login'){
   next()
}
else{
  next({
    path: '/login'
  });
}

});

export default router;