import Admin from '@/app/views/admin/layout/index'
import Login from '@/app/views/admin/account/login'
import Empty from '@/app/views/admin/layout/empty'
import AccountInfo from '@/app/views/admin/account/info'
import Password from '@/app/views/admin/account/password'
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
import MenuList from '@/app/views/admin/menu/list'
// following for site
import Site from '@/app/views/site/layout/index'
import HomeIndex from '@/app/views/site/home/index'
import ResourceSearch from '@/app/views/site/resource/search'
import ResourceDetails from '@/app/views/site/resource/details'
import AccountLogin from '@/app/views/site/account/login'
import AccountSpace from '@/app/views/site/account/space'
import AccountRegister from '@/app/views/site/account/register'

export const menuRoutes = [{
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
            component: () =>
                import ('@/app/views/admin/user/list.vue'),
            children: []
        }]
    }, {
        id: 5,
        level: 1,
        name: '角色管理',
        path: '/admin/role/list',
        icon: 'el-icon-s-custom',
        component: RoleList,
        children: [],
    },
    {
        id: 6,
        level: 1,
        name: '单位管理',
        path: '/admin/company/list',
        icon: 'el-icon-school',
        component: CompanyList,
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
        component: Empty,
        children: [{
            id: 8,
            level: 2,
            name: '入库资源',
            path: "/admin/resource/index",
            icon: 'el-icon-files',
            component: ResourceIndex,
            children: []
        }]
    },
    {
        id: 9,
        level: 1,
        name: '资源包',
        path: '#',
        icon: 'el-icon-folder-opened',
        children: []
    },
    {
        id: 10,
        level: 1,
        name: '公告管理',
        icon: 'el-icon-edit-outline',
        path: '/admin/bulletin',
        component: Empty,
        children: [{
            id: 11,
            level: 2,
            name: '资源库公告',
            path: '/admin/bulletin/list',
            icon: 'el-icon-edit-outline',
            component: BulletinList,
            children: []
        }]
    },
    {
        id: 12,
        level: 1,
        name: '评论管理',
        icon: 'el-icon-chat-dot-square',
        path: '/admin/comment/list',
        component: Empty,
        children: [{
            id: 4,
            level: 2,
            name: '入库资源评论管理',
            path: "/admin/comment/list",
            icon: 'el-icon-chat-dot-square',
            component: CommentList,
            children: []
        }]
    }, {
        id: 13,
        level: 1,
        name: '菜单管理',
        icon: 'el-icon-folder',
        path: '/admin/menu/list',
        component: MenuList,
        children: []
    }
];

export const constRoutes = [{
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
        component: () =>
            import ('@/app/views/404.vue')
    }
];

const frontendRoutes = [
    // frontend
    {
        path: '/',
        name: 'Site',
        component: Site,
        children: [{
                path: '/home/index',
                name: 'HomeIndex',
                component: HomeIndex
            },
            {
                path: '/resource/search',
                name: 'ResourceSearch',
                component: ResourceSearch
            },
            {
                path: '/resource/details',
                name: 'ResourceDetails',
                component: ResourceDetails
            },
            {
                path: '/account/login',
                name: 'AccountLogin',
                component: AccountLogin
            },
            {
                path: '/account/space',
                name: 'AccountSpace',
                component: AccountSpace
            },
            {
                path: '/account/register',
                name: 'AccountRegister',
                component: AccountRegister
            }
        ],
        redirect: { name: 'HomeIndex' } // redirect to home index
    }
]

const backendRoutes = [
    // backend
    {
        path: '/admin',
        name: 'AdminIndex',
        component: Admin,
        children: [

            //TODO:  really think about 
            ...menuRoutes,
            {
                path: '/admin/account/password',
                name: 'ChangePassword',
                component: Password
            },
            {
                path: '/admin/account/info',
                name: 'AccountInfo',
                component: AccountInfo
            },

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
            }, {
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
]


const routes = [
    ...frontendRoutes,
    ...backendRoutes,
    ...constRoutes,
    {
        path: "*",
        redirect: "/404"
    }
];

export default routes;