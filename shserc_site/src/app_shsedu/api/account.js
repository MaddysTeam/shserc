import axios from '@/app_shsedu/static/axios'

export function login(params) {
    return axios.post('api/shseduAccount/login', params);
}

export function logout() {
    return axios.post('api/shseduAccount/logout')
}

export function changePassword(changePassword) {
    return axios.post('api/shseduAccount/password/change', changePassword)
}