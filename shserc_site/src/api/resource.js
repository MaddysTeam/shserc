import axios from '../static/axios'

export function resourceList(pageIndex,pageSize){
    console.log(pageSize);
   // return axios.post('api/resource/list',{'current':pageIndex,'size':pageSize});
   return [{id:1,title:"resource 1"},{id:2,name:"resource 2"}]
}

export function edit(params){

}