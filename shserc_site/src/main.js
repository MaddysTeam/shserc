// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css' 
import './assets/css/global.css'
import { Button } from 'element-ui'
import { Form,FormItem} from 'element-ui'
import { Input } from 'element-ui'
import instance_ from './utils/axios.js';

Vue.prototype.instance = instance_;
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
