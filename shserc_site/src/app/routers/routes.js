
import Admin from '@/app/views/admin/layout/index'
import Login from '@/app/views/admin/account/login'
import UserList from '@/app/views/admin/user/list'
import CompanyList from '@/app/views/admin/company/list'
import ResourceList from '@/app/views/admin/resource/list'
import ResourceEdit from '@/app/views/admin/resource/edit'
import ResourceDetail from '@/app/views/admin/resource/detail'

const routes = [
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
    }, {
      path: '/admin/resource/list',
      name: 'ResourceList',
      component: ResourceList
    }, {
      path: '/admin/resource/add/',
      name: 'ResourceAdd',
      component: ResourceEdit
    }, {
      path: '/admin/resource/edit/:id',
      name: 'ResourceEdit',
      component: ResourceEdit
    }, {
      path: '/admin/resource/detail/:id',
      name: 'ResourceDetail',
      component: ResourceDetail
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
];

export default routes;