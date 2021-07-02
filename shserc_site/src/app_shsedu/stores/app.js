import { setCookie, getCookie, delCookie } from "@/static/cookie"
export default {
    namespaced: true,
    state: {
        token: getCookie("ShseduAuthorization"),
        isAuth: getCookie("ShseduAuthorization") ? true : false,
        account: JSON.parse(localStorage.getItem("ShseudAccount")),

        // following for all roles and menus
        roles: [],
        menus: JSON.parse(localStorage.getItem("menu")),

        // TODO：dynamic routes
        dynamicRoutes: [],
    },
    mutations: {

    },
    actions: {

    },
    getters: {

    }
}