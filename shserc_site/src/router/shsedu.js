import Vue from 'vue'
import Router from 'vue-router'
import routes from '@/app_shsedu/routers/routes'
import protector from '@/app_shsedu/routers/protector'


const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.use(Router)

// const router = new Router({ routes });
console.log(routes);

const router = new Router({ routes });
console.log(router);


export function addDynamicRoutes() {
    router.addRoutes([{
        path: "/admin/user/list",
        name: "用户管理",
        component: () =>
            import ("@/app_shsedu/views/admin/user/list")
    }])
}


//addDynamicRoutes();

//router.beforeEach(protector);





export default router;