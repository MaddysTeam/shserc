
import Admin from '@/app/views/admin/layout/index'
import Login from '@/app/views/admin/account/login'
import UserList from '@/app/views/admin/user/list'
import CompanyList from '@/app/views/admin/company/list'
import ResourceList from '@/app/views/admin/resource/list'
import ResourceEdit from '@/app/views/admin/resource/edit'
import ResourceDetail from '@/app/views/admin/resource/detail'
import Summary from '@/app/views/admin/dashboard/summary'
import RoleList from '@/app/views/admin/role/list'
import RealList from '@/app/views/admin/real/list'

const routes = [
  // frontend
  {
    path: '/',
    name: 'index',
    redirect: { name: 'AdminIndex' } //TODO:这里改成前台
  },

  // backend
  {
    path: '/admin',
    name: 'AdminIndex',
    component: Admin,
    children: [{
      path: '/admin/dashboard/summary',
      name: 'Summary',
      component: Summary
    },
    {
      path: '/admin/user/list',
      name: 'UserList',
      component: UserList
    },
    {
      path: '/admin/real/list',
      name: 'RealList',
      component: RealList
    },
    {
      path: '/admin/role/list',
      name: 'RoleList',
      component: RoleList
    },
    {
      path: '/admin/company/list',
      name: 'CompanyList',
      component: CompanyList,
      meta: {
        keepAlive: false
      }
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
    name: 'AdminLogin',
    component: Login
  },
  {
    path: '/admin/logout',
    name: 'AdminLogout',
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/app/views/404.vue')
  },
  {
    path: "*",
    redirect: "/404"
  }
];

export default routes;