import Vuex from 'vuex'
import Vue from 'vue'
import * as types from './type'

Vue.use(Vuex);

/* set global store */
export default new Vuex.Store({
  state: {
    token: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    isAuth: localStorage.getItem('Authorization') ? true:false
  },
  mutations: {
    [types.LOGIN]:(state,data)=>{
      if(data){
        localStorage.setItem('Authorization',data);
        state.isAuth=true;
      }
    },
    [types.LOGOUT]:(state)=>{
      localStorage.removeItem('Authorization');
    }
  }
});