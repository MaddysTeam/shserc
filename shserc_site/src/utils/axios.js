

import axios from 'axios'
import { Notification } from 'element-ui';
import router from '../router'
import store from '../store/store.js'

//axios.defaults.baseURL = "api"; //process.env.NODE_ENV == 'development' ? '//localhost:28019' : 'localhost:28019'
axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers['token'] = localStorage.getItem('token') || ''
axios.defaults.headers.post['Content-Type'] = 'application/json'

axios.interceptors.request.use(req => {
  config => {
    if (store.state.isAuth) {
      config.headers['token'] = store.state.token;
    }
    return config;
  },
  error=>{
    return Promise.reject()
  }
});
console.log(store)
axios.interceptors.response.use(res => {
  if (typeof res.data !== 'object') {
    Toast.fail('服务端异常！')
    return Promise.reject(res);
  }
  if (res.data.resultCode != 200) {
    if (res.data.message) Notification.error({ message: res.data.message })
    if (res.data.resultCode == 416) {
      router.push({ path: '/login' })
    }
    if(res.data.resultCode==401){
      
    }
    return Promise.reject(res.data)
  }

  return res.data
});

export default axios;
