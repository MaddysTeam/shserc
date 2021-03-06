// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'babel-polyfill'
import Vue from 'vue'
import '@/static/elementUI.js'
import '@/static/validator.js'
import '@/assets/css/global.css'
import App from './App'

//import store from '@/store/store'
//import router from '@/router'
//import { Plugins } from '@/plugins/shserc.js'

import store from '@/store/shsedu.js'
import router from '@/router/shsedu.js'
import { Plugins } from '@/plugins/shsedu.js'

/** initial mock data */
const mock = true;
if (mock) {
    require('./app_shsedu/mock/mock');
}

/** initial plugin */
Plugins.init(Vue, { store: store })


/* eslint-disable no-new */
new Vue({
    el: '#app',
    store,
    router,
    components: { App },
    template: '<App/>'
})