import axios from '@/static/axios' 

export function companyList(){
    return axios.post('api/company/search');
}   

export function getChildren(parentId){
    return axios.post('api/company/children?parentId='+parentId)
}

export function edit(params){
    return axios.post('api/company/edit',params);
}
