import axios from '../utils/axios'

export function login(params){
    console.log('login action')
    return axios.post('api/account/login',params)
}

export function userList(pageIndex,pageSize){
    console.log(pageSize);
    return axios.post('api/user/list',{'current':pageIndex,'size':pageSize}) //[{id:1,name:"jimmypoor"},{id:2,name:"jimmypoor"}]
}