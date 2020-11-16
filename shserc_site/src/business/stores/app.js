import * as types from '@/business/static/type';
export  default {
    namespaced: true,
    state: {
        token: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
        isAuth: true, //localStorage.getItem('Authorization') ? true:false
        
        //following for dictionary
        dict:[],
        deformity:[],
      },
      mutations: {
        [types.LOGIN]: (state, data) => {
          if (data) {
            localStorage.setItem('Authorization', data);
            state.isAuth = true;
          }
        },
        [types.LOGOUT]: (state) => {
          localStorage.removeItem('Authorization');
        },
    
         //following for dictionary
        [types.DICTIONARY]: (state, data) => {
          state.dict = data ;
        },
    
        [types.DEFORMITY]: (state, data) => {
          state.deformity = data ;
        }
    
      },
    actions:{},
    getters:{}
}