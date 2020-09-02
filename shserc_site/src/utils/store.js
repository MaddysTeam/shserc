import Vuex from 'vuex'
import Vue from 'vue'

Vue.use(Vuex);

/* set global store */
export default new Vuex.Store({
  state:{
     isAuth:localStorage.getItem('Authorization')? localStorage.getItem('Authorization') : ''
  },
  mutations:{
     
  }
});