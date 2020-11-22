import axios from '@/app/static/axios';

export function dicList(){
    return axios.post('api/dictionary/search')
}