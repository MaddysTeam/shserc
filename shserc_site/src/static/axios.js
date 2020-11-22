

import axios from 'axios'


//axios.defaults.baseURL = "api"; //process.env.NODE_ENV == 'development' ? '//localhost:28019' : 'localhost:28019'
axios.defaults.withCredentials = true
axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
axios.defaults.headers.post['Content-Type'] = 'application/json'

export default axios;
