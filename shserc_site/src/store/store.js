import Vuex from 'vuex'
import Vue from 'vue'
import * as types from './type'

Vue.use(Vuex);

/* set global store */
export default new Vuex.Store({
  state: {
    token: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    isAuth: false
  },
  mutations: {
    [types.LOGIN]:(state,data)=>{
      if(data){
        localStorage.setItem("Authorization",data);
        state.isAuth=true;
      }
    }
    // setAuth(state, user) {
    //   state.token = user.token;
    //   if (user.token) {
    //     state.isAuth = true;
    //   }
    // },
    // clearAuth(state){
    //   localStorage.removeItem('Authorization');
    //   state.isAuth=false;
    // }
  }
});