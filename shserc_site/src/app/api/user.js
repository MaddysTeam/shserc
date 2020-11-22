import axios from '@/app/static/axios'

export function userList(pageIndex,pageSize){
    console.log(pageSize);
    return axios.post('api/user/list',{'current':pageIndex,'size':pageSize}) //[{id:1,name:"jimmypoor"},{id:2,name:"jimmypoor"}]
}

export function edit(params){

}
