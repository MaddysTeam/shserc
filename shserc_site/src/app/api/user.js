import axios from '@/app/static/axios'

export function list(params) {
    console.log(params)
    return axios.post('api/user/list', params)
}

export function edit(params) {
    return axios.post('api/user/edit', params)
}

export function info(id) {
    return axios.post('api/user/' + id);
}

export function bindUserRole(params){
    return axios.post('api/user/role/edit',params)
}
