// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import instance_ from './static/axios.js'
import store from './store/store'
import './static/elementUI.js'
import './static/validator.js'
import './assets/css/global.css'
import { initDic } from './utils/dictHelper'

initDic(store);

Vue.prototype.instance = instance_;
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
