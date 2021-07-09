import axios from '@/app_shsedu/static/axios'

export function list(params) {  
    return axios.post('api/shseduUser/list', params)
}

export function edit(params) {
    return axios.post('api/shseduUser/edit', params)
}

export function info(id) {
    return axios.post('api/shseduUser/' + id);
}