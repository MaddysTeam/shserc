import axios from '@/app/static/axios'

export function list(selectParam) {
    return axios.post('api/real/list', selectParam)
}

export function edit(editParam) {
    return axios.post('api/real/edit', editParam)
}

export function info(id) {
    return axios.post('api/real/' + id);
}

export function register(){
    return axios.post('api/real/register')
}