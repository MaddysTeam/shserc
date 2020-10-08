import axios from '../static/axios';

export function dicList(){
    return axios.post('api/dictionary/search')
}