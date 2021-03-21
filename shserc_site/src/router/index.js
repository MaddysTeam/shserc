import Vue from 'vue'
import Router from 'vue-router'
import routes from '@/app/routers/routes'
import protector from '@/app/routers/protector'

Vue.use(Router)

// const router = new Router({ routes });



const createRouter = () => new Router({
    mode: 'history',
    routes: routes
})

const router = createRouter();
router.beforeEach(protector);

export function reset() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // the relevant part
}



export default router;