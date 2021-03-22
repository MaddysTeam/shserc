import * as types from '@/app/static/type';
export default {
  namespaced: true,
  state: {
    token: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    isAuth: localStorage.getItem('Authorization') ? true : false,
    loginUserInfo: {},

    // following for all roles and menus
    roles: [],
    menus: [],

    // dynamic routes
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
    domains: [],
    source: []
  },
  mutations: {
    [types.LOGIN]: (state, data) => {
      if (data) {
        let token = data.token;
        localStorage.setItem('Authorization', token); // store token to localstorage
        state.isAuth = true;
        state.token = token;
        state.loginUserInfo = data;
        // state.menus = state.menus.filter(m => m.roles.some(id => id === data.roleId)); // store user menus by role id
        // console.log(state.loginUserInfo)
      }
    },

    [types.LOGOUT]: (state) => {
      localStorage.removeItem('Authorization');
      state.loginUserInfo = {}
    },

    // roles
    [types.ROLES]: (state, data) => {
      state.roles = data;
    },

    // menus
    [types.MENUS]: (state, data) => {
      localStorage.setItem('menu', JSON.stringify(data));
      state.menus = data;
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
    currentRoutes: state => state.routes
  }
}


