import Vue from 'vue'
import Router from 'vue-router'
import routes from '@/app/routers/routes'
import protector from '@/app/routers/protector'

Vue.use(Router)

// const router = new Router({ routes });



const router = new Router({ routes });

router.beforeEach(protector);





export default router;