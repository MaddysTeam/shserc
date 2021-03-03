import axios from '@/app/static/axios' 

export function list(){
    return axios.post('api/company/search');
}   

export function companyList(){
    return list();
}

export function getChildren(parentId){
    return axios.post('api/company/children?parentId='+parentId)
}

export function edit(params){
    return axios.post('api/company/edit',params);
}

export function info(id){

}
