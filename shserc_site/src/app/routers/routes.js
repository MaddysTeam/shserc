import Admin from '@/app/views/admin/layout/index'
import Login from '@/app/views/admin/account/login'
import Empty from '@/app/views/admin/layout/empty'
import UserList from '@/app/views/admin/user/list'
import CompanyList from '@/app/views/admin/company/list'
import ResourceList from '@/app/views/admin/resource/list'
import ResourceEdit from '@/app/views/admin/resource/edit'
import ResourceDetail from '@/app/views/admin/resource/detail'
import ResourceIndex from '@/app/views/admin/resource/index'
import Summary from '@/app/views/admin/dashboard/summary'
import RoleList from '@/app/views/admin/role/list'
import RealList from '@/app/views/admin/real/list'
import CommentList from '@/app/views/admin/comment/list'
import BulletinList from '@/app/views/admin/bulletin/list'
import BulletinEdit from '@/app/views/admin/bulletin/edit'


export const menuRoutes = [
  {
    path: '/admin/dashboard/summary',
    name: '仪表板',
    component: Summary,
    level: 1,
    id: 1,
    icon: 'el-icon-pie-chart',
    children: []
  },
  {
    id: 2,
    level: 1,
    name: '用户管理',
    icon: 'el-icon-user',
    path: '/admin/user',
    component: Empty,
    children: [{
      id: 3,
      level: 2,
      path: '/admin/real/list',
      name: '实名管理',
      component: RealList,
      children: []
    }, {
      id: 4,
      level: 1,
      path: '/admin/user/list',
      name: '用户查询',
      component: () => import('@/app/views/admin/user/list.vue'),
      children: []
    }]
  } ,{
    id: 5,
    level: 1,
    name: '角色管理',
    path: '/admin/role/list',
    icon: 'el-icon-s-custom',
    component:RoleList,
    children: [],
  },
  {
    id: 6,
    level: 1,
    name: '单位管理',
    path: '/admin/company/list',
    icon: 'el-icon-school',
    component:CompanyList,
    meta: {
      keepAlive: false
    },
    children: []
  }, {
    id: 7,
    level: 1,
    name: '资源管理',
    icon: 'el-icon-tickets',
    path: '/admin/resource',
    component:Empty,
    children: [
      {
        id: 8,
        level: 2,
        name: '入库资源',
        path: "/admin/resource/index",
        icon: 'el-icon-files',
        component:ResourceIndex,
        children: []
      }
    ]
  },
  {
    id: 9,
    level: 1,
    name: '资源包',
    path: '#',
    icon: 'el-icon-folder',
    children: []
  },
  {
    id: 10,
    level: 1,
    name: '公告管理',
    icon: 'el-icon-folder',
    path: '/admin/bulletin',
    component:Empty,
    children: [
      {
        id: 11,
        level: 2,
        name: '资源库公告',
        path: '/admin/bulletin/list',
        icon: 'el-icon-files',
        component:BulletinList,
        children: []
      }
    ]
  },
  {
    id: 12,
    level: 1,
    name: '评论管理',
    icon: 'el-icon-folder',
    path: '/admin/comment/list',
    component:Empty,
    children: [{
      id: 4,
      level: 2,
      name: '入库资源评论管理',
      path: "/admin/comment/list",
      icon: 'el-icon-files',
      component:CommentList,
      children: []
    }]
  }
];



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
    children: [
      ...menuRoutes
      // menuRoutes[0],
      // menuRoutes[1],
      // menuRoutes[2],
      // menuRoutes[3],
      // menuRoutes[4],
      // menuRoutes[5],
      // menuRoutes[6],
      // menuRoutes[7],

    {
      path: '/admin/resource/list',
      name: 'ResourceList',
      component: ResourceList
    }, 
    {
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
    },{
      path: '/admin/bulletin/add',
      name: 'ResourceAdd',
      component: BulletinEdit
    },
    {
      path: '/admin/bulletin/edit/:id',
      name: 'bulletinEdit',
      component: BulletinEdit
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