import { setCookie, getCookie, delCookie } from "@/static/cookie"
import * as types from "@/app_shsedu/static/type"
export default {
    namespaced: true,
    state: {
        token: getCookie("ShseduAuthorization"),
        isAuth: getCookie("ShseduAuthorization") ? true : false,
        account: JSON.parse(localStorage.getItem("ShseudAccount")),

        // following for all roles and menus
        roles: [],
        menus: JSON.parse(localStorage.getItem("ShseduMenu")),
        dict: JSON.parse(localStorage.getItem("dict")),
        districts: [],

        // TODO：dynamic routes
        dynamicRoutes: [],
    },
    mutations: {
        [types.LOGIN]: (state, data) => {
            if (data) {
                let token = data.token;
                // attention !  must set current state value and next step we can use localstorage, otherwise state value will be null when rendering page!
                state.isAuth = true;
                state.token = token;
                state.account = data;

                setCookie('ShseduAuthorization', token, 1);
                localStorage.setItem("ShseudAccount", JSON.stringify(data));
            }
        },

        [types.LOGOUT]: (state) => {
            localStorage.removeItem("ShseudAccount");
            localStorage.removeItem("ShseduMenu");
            delCookie("ShseduAuthorization")
            state.account = {}
        },

        [types.MENUS]: (state, data) => {
            // attention !  must set current state value  and next step we can use localstorage, otherwise state value will be null!
            state.menus = data;
            localStorage.setItem("ShseduMenu", JSON.stringify(data));
        },

         // following for dictionary
         [types.DICTIONARY]: (state, data) => {
            localStorage.setItem("dict", JSON.stringify(data));
        },


        [types.DISTRICTS]: (state, data) => {
            state.districts = data;
        }
    },
    actions: {

    },
    getters: {

    }
}