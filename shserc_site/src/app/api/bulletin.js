import axios from '@/app/static/axios'

export function list(selectParams) {
    return axios.post('api/bulletin/list', selectParams);
}

export function edit(params) {
    return axios.post('api/bulletin/edit', params);
}

export function info(id) {
    return axios.post('api/bulletin/' + id);
}

export function del(deleteParams){
    return axios.post('api/bulletin/delete',deleteParams);
}
