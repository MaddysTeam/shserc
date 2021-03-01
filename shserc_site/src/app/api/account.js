import axios from '@/app/static/axios'

export function login(params) {
    return axios.post('api/account/login', params)
}

export function logout() {
    return axios.post('api/account/logout')
}

export function changePassword(changePassword){
    return axios.post('api/account/changePassword',changePassword)
}

export function forgetPassword(){

}