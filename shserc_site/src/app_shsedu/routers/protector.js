import store from "@/store/shsedu"

const protector = (to, from, next) => {
    const module = store.state.app;
    if (to.path.indexOf("/404") >= 0 || to.path.indexOf("/login") >= 0) {
        next();
    } else if (to.path.indexOf("/logout") >= 0) {
        window.location.reload();
    } else if (to.path.indexOf("/admin") >= 0) {
        if (!module.isAuth) {
            next({
                path: "/admin/login"
            });
        } else {
            next();
        }
    }
}

export default protector;