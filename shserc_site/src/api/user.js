import axios from '../utils/axios'

export function login(params){
    console.log('login action')
    return axios.post('api/account/login',params)
}