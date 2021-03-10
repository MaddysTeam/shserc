// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import router from '@/router'
import instance_ from '@/static/axios.js'
import store from '@/store/store'
import '@/static/elementUI.js'
import '@/static/validator.js'
import '@/assets/css/global.css'
import { initDic } from '@/app/utils/dictHelper'
import VideoPlayer  from 'vue-video-player'
import TEditor from '@/components/Editor/index'
Vue.component('TinymceEditor',TEditor)

/** initial global dictionary */
initDic(store);

Vue.prototype.instance = instance_;
Vue.config.productionTip = false

/** initial video player */
Vue.use(VideoPlayer)
require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
