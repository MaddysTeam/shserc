import axios from '../static/axios'

export function resourceList(pageIndex,pageSize,deformityId){
    console.log(pageSize);
    return axios.post('api/resource/list',{'current':pageIndex,'size':pageSize,'deformityId':deformityId});
}

export function edit(params){

}