import axios from '@/app/static/axios'

export function list(selectParams) {
    return axios.post('api/role/list', selectParams)
}

export function edit(params) {
    return axios.post('api/role/edit', params)
}

export function info(id) {
    return axios.post('api/role/' + id);
}