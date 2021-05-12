import store from "@/store/store"
import router from "@/router"
import getBackednRoutesTemp from "./routerPermission"


const protector = (to, from, next) => {
    const module = store.state.app;
    if (to.meta.title) {
        document.title = to.meta.title;
    }

    if (to.path.indexOf("/404") >= 0 || to.path.indexOf("/login") >= 0) {
        next();
    } else if (to.path.indexOf("/croSite/space") >= 0) {
        if (!module.isAuth) {
            next({
                path: "/croSite/home/index"
            });
        } else {
            next();
        }
    } else if (to.path.indexOf("/account/space") >= 0) {
        if (!module.isAuth) {
            next({
                path: "/home/index"
            });
        } else {
            next();
        }
    } else if (to.path.indexOf("/logout") >= 0) {
        window.location.reload();
    } else if (to.path.indexOf("/admin") >= 0) {
        if (!module.isAuth) {
            next({
                path: "/admin/login"
            });
        } else {
            let dynamicRoutes = module.dynamicRoutes;
            let hasDynamicRoutes = dynamicRoutes && dynamicRoutes.length > 0;
            if (hasDynamicRoutes) {
                next();
            } else {
                next();
                // let tempRoute =getBackednRoutesTemp();
                // dynamicRoutes.push(tempRoute);
                // router.addRoutes(
                //     tempRoute.concat({ path: "*", redirect: "/404" })
                // );

                // next({ ...to, replace: true })
            }
        }
    } else {
        next();
    }
}


export default protector;