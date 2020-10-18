import axios from '../static/axios'

export function resourceList(pageIndex,pageSize){
    console.log(pageSize);
    return axios.post('api/resource/list',{'current':pageIndex,'size':pageSize}) //[{id:1,name:"jimmypoor"},{id:2,name:"jimmypoor"}]
}

export function edit(params){

}