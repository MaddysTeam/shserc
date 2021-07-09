import Admin from "@/app_shsedu/views/admin/layout/index"
import Login from "@/app_shsedu/views/admin/account/login"
import UserList from "@/app_shsedu/views/admin/user/list"
import ColumnList from "@/app_shsedu/views/admin/columns/list"


export const constRoutes = [{
        path: "/admin/login",
        name: "AdminLogin",
        component: Login
    },
    {
        path: "/admin/logout",
        name: "AdminLogout",
    },
    {
        path: "/404",
        name: "404",
        component: () =>
            import ("@/app_shsedu/views/404.vue")
    }
];

export const menuRoutes = [
    { path: "/admin/user/list", name: "用户管理", component: UserList },
    { path: "/admin/column/list", name: "栏目管理", component: ColumnList }
];

export const backendRoutes = [{
    path: "/admin",
    name: "AdminIndex",
    component: Admin,
    children: [
        ...menuRoutes
    ]
}];


const routes = [
    ...constRoutes,
    ...backendRoutes,
     {
         path: "*",
         redirect: "/404"
     }
]

export default routes;