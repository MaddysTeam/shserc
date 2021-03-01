import axios from '@/app/static/axios'

export function list(pageIndex, pageSize) {
    return axios.post('api/user/list', { 'current': pageIndex, 'size': pageSize })
}

export function edit(params) {
    return axios.post('api/user/edit', params)
}

export function info(id) {
    return axios.post('api/user/' + id);
}
