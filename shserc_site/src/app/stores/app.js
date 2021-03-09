import * as types from '@/app/static/type';
export default {
    namespaced: true,
    state: {
        token: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
        isAuth: true, //localStorage.getItem('Authorization') ? true:false,
        
        //following for dictionary
        dict:[],
        deformity:[],
        resourceStatus:[],
        resourceDomains:[],
        resourceTypes:[],
        stages:[],
        grades:[],
        subjects:[],
        schoolTypes:[],
        learnFrom:[]
      },
      mutations: {
        [types.LOGIN]: (state, data) => {
          if (data) {
            localStorage.setItem('Authorization', data);
           state.isAuth = true;
           state.token = data;
          }
        },
        [types.LOGOUT]: (state) => {
          localStorage.removeItem('Authorization');
        },
    
         //dictionary
        [types.DICTIONARY]: (state, data) => {
          state.dict = data ;
        },
        
        // dict for resource
        [types.DEFORMITY]: (state,data) => {
          state.deformity = data ;
        },

        [types.RESOURCE_STATUS]: (state,data) => {
          state.resourceStatus = data ;
        },

        [types.RESOURCE_DOMAINS]: (state,data) => {
          state.resourceDomains = data ;
        },

        [types.RESOURCE_TYPES]: (state,data) => {
          state.resourceTypes = data ;
        },

        [types.STAGES]: (state,data) => {
          state.stages = data ;
        },

        [types.GRADES]: (state,data) => {
          state.grades = data ;
        },

        [types.SUBJECTS]: (state,data) => {
          state.subjects = data ;
        },

        [types.SCHOOLTYPES]: (state,data) => {
          state.schoolTypes = data ;
        },

        [types.LEARNFROM]: (state,data) => {
          state.learnFrom = data ;
        }
      },
    actions:{},
    getters:{}
}


