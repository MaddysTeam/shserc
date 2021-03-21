//TODO:
function hasPermission(roles, route) {
    if (route.meta && route.meta.roles) {
        return roles.some(role => route.meta.roles.includes(role))
    } else {
        return true
    }
}

//TODO:
export function bindRoutes(routes, roles) {
    const res = [];
    routes.forEach(route => {
        const tmp = { ...route }
        if (hasPermission(roles, tmp)) {
            if (tmp.children) {
                tmp.children = bindRoutes(tmp.children, roles)
            }
            res.push(tmp)
        }
    })

    return res
}

//TODO:
// add backend dynamic route
export function addBackendRoutes(route, routes) {
    route.addRoutes({});
}

//TODO:
// add frontend dnamic route
export function addFrontendRoutes(route, routes) {
    route.addRoutes({});
}

//TODO: will delete
export function  getBackednRoutesTemp() {
    let tempRoutes = [];

    return tempRoutes;
}