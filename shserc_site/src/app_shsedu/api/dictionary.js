import axios from '@/app_shsedu/static/axios';

export function dicList(){
    return axios.post('api/dictionary/search')
}