import Vuex from 'vuex'
import Vue from 'vue'
import app from '@/app_shsedu/stores/app'

Vue.use(Vuex);

/* set global store */
export default new Vuex.Store({
    modules: {
        app
    }
});