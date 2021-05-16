import instance_ from '@/static/axios.js'
import { initDic } from '@/app/utils/dictHelper'
import { initRoles } from '@/app/utils/roleHelper'
import VideoPlayer from 'vue-video-player'
import TEditor from '@/components/Editor/index'

export const Plugins = {

    init: function(Vue, options) {

        Vue.directive('focus', {
            inserted: function(el) {
                el.focus();
                el.querySelector('input').focus();
            }
        });

        Vue.directive('dot', {
            bind: function(el, binding, vnode, oldVnode) {
                el.innerText = binding.value.substring(0, 15) + '....';
            },
        });


        /** regist editor  component */
        Vue.component('TinymceEditor', TEditor)

        /** initial global dictionary and roles */
        initDic(options.store);
        initRoles(options.store);

        /** initial axios */
        Vue.prototype.instance = instance_;
        Vue.config.productionTip = false


        /** initial video player */
        Vue.use(VideoPlayer)
        require('video.js/dist/video-js.css')
        require('vue-video-player/src/custom-theme.css')

    }

}