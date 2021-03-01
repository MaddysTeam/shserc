import axios from '@/app/static/axios'

export function list(pageIndex, pageSize) {
    return axios.post('api/real/list', { 'current': pageIndex, 'size': pageSize })
}

export function edit(params) {
    return axios.post('api/real/edit', params)
}

export function info(id) {
    return axios.post('api/real/' + id);
}

export function register(){
    return axios.post('api/real/register')
}