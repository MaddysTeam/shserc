import axios from '@/static/axios'
import { Notification } from 'element-ui';
import router from '@/router'
import store from '@/store/store.js'

axios.interceptors.request.use(
    config => {
        var app = store.state.app;
        if (app.isAuth) {
            config.headers['Authorization'] = app.token;
            if (localStorage.getItem('Authorization') != app.token) {
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
    if (typeof res.data !== 'object') {
        Notification.error('server error！');
        return Promise.reject(res);
    }

    if (res.data.resultCode != 200) {
        if (res.data.message) Notification.error({
            dangerouslyUseHTMLString: true,
            message: res.data.message.replace(/\r\n/g, "<br>")
        })
        if (res.data.resultCode == 416 || res.data.resultCode == 401 || res.data.resultCode == 400) {
            router.push({ path: '/login' })
        }
        return Promise.reject(res.data)
    }

    return res.data
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