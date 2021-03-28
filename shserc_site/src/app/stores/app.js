import * as types from "@/app/static/type";
import { setCookie, getCookie,delCookie } from "@/static/cookie"
export default {
  namespaced: true,
  state: {
    token:  getCookie("Authorization"), 
    isAuth: getCookie("Authorization") ? true : false,
    account: JSON.parse(localStorage.getItem("account")),

    // following for all roles and menus
    roles: [],
    menus: JSON.parse(localStorage.getItem("menu")),

    // TODO：dynamic routes
    dynamicRoutes: [],

    // following for dictionary
    dict: [],
    deformity: [],
    resourceStatus: [],
    resourceDomains: [],
    resourceTypes: [],
    stages: [],
    grades: [],
    subjects: [],
    schoolTypes: [],
    learnFrom: [],
    source: [],
    states: []
  },
  mutations: {
    [types.LOGIN]: (state, data) => {
      if (data) {
        let token = data.token;
        // attention !  must set current state value and next step we can use localstorage, otherwise state value will be null when rendering page!
        state.isAuth = true;
        state.token = token;
        state.account= data;

        setCookie('Authorization', token, 1);
        localStorage.setItem("account", JSON.stringify(data));
      }
    },

    [types.LOGOUT]: (state) => {
      localStorage.removeItem("account");
      localStorage.removeItem("menu");
      delCookie("Authorization")
      state.account = {}
    },

    // roles
    [types.ROLES]: (state, data) => {
      state.roles = data;
    },

    // menus
    [types.MENUS]: (state, data) => {
        // attention !  must set current state value  and next step we can use localstorage, otherwise state value will be null!
      state.menus=data;
      localStorage.setItem("menu", JSON.stringify(data));
    },

    // routes
    [types.ROUTES]: (state, data) => {
      state.dynamicRoutes = data;
    },

    // following for dictionary
    [types.DICTIONARY]: (state, data) => {
      state.dict = data;
    },


    [types.DEFORMITY]: (state, data) => {
      state.deformity = data;
    },

    [types.RESOURCE_STATUS]: (state, data) => {
      state.resourceStatus = data;
    },

    [types.RESOURCE_DOMAINS]: (state, data) => {
      state.resourceDomains = data;
    },

    [types.RESOURCE_TYPES]: (state, data) => {
      state.resourceTypes = data;
    },

    [types.STAGES]: (state, data) => {
      state.stages = data;
    },

    [types.GRADES]: (state, data) => {
      state.grades = data;
    },

    [types.SUBJECTS]: (state, data) => {
      state.subjects = data;
    },

    [types.SCHOOLTYPES]: (state, data) => {
      state.schoolTypes = data;
    },

    [types.LEARNFROM]: (state, data) => {
      state.learnFrom = data;
    },

    [types.SOURCE]: (state, data) => {
      state.source = data;
    },

    [types.STATES]: (state, data) => {
      state.states = data;
    }

  },
  actions: {
    changeResourceTypes({ commit, state }, data) {
      commit(types.RESOURCE_TYPES, data)
    },
    changeGrades({ commit, state }, data) {
      commit(types.GRADES, data)
    }

  },
  getters: {
    currentMenus: state => state.menus,
    currentRoutes: state => state.routes,
    currentStates: state => state.states
  }
}


