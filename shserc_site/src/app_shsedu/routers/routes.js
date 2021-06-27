 import Login from '@/app_shsedu/views/admin/account/login'

 const backendRoutes = [{
     path: '/login',
     name: 'Login',
     component: Login,
 }];

 const routes = [
     ...backendRoutes,
     //  {
     //      path: "*",
     //      redirect: "/404"
     //  }
 ]

 export default routes;