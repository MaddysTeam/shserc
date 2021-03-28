import axios from '@/app/static/axios' 

export function list(selectParam){
    return axios.post('api/comment/list',selectParam);
}   
