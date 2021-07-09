import axios from '@/static/axios'
import { Notification } from 'element-ui';
import router from '@/router'
import store from '@/store/store.js'

axios.interceptors.request.use(
    config => {
        var app = store.state.app;
        if (app.isAuth) {
            config.headers['ShseduAuthorization'] = app.token;
            if (localStorage.getItem('ShseduAuthorization') != app.token) {
                // window.location.reload();
            }
        }
        return config;
    },
    error => {
        return Promise.reject(error)
    }
);

axios.interceptors.response.use(res => {
    if (typeof res !== 'object') {
        Notification.error('server error！');
        return Promise.reject(res);
    }
    if (res.resultCode != 200) {
        if (res.message) Notification.error({
            dangerouslyUseHTMLString: true,
            message: res.message.replace(/\r\n/g, "<br>")
        })
        if (res.resultCode == 416 || res.resultCode == 401 || res.resultCode == 400) {
            router.push({ path: '/login' })
        }
        return Promise.reject(res)
    }

    return res
}, error => {
    console.log(error);
    //   store.commit('app/logout');
    if (error.message) {
        Notification.error(error.message);
    } else if (error.response) {
        Notification.error(error.response);
    } else if (error.request) {
        Notification.error(error.request);

    } else {

    }
});

export default axios;