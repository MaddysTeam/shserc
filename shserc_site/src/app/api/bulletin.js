import axios from '@/app/static/axios' 

export function list(selectParams){
    return axios.post('api/bulletin/search',selectParams);
}   

export function edit(params){
    return axios.post('api/bulletin/edit',params);
}

export function info(id){

}