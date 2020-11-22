import * as types from '@/app/static/type';
export default {
    namespaced: true,
    state: {
        token: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
        isAuth: localStorage.getItem('Authorization') ? true:false,
        
        //following for dictionary
        dict:[],
        deformity:[],
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