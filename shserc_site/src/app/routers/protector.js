import store from '@/store/store'

const protector = (to, from, next) => {
    const module = store.state.app;
    // ...
    if (to.path.indexOf('/admin') < 0) {
        next()
    }
    else {
        if (to.path.indexOf('admin') >= 0) {

            if (module.isAuth) {
                next()
            }
            else {
                next({
                    path: '/login'
                });
            }
        }
    }

}

export default protector;