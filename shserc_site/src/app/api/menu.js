import axios from '@/app/static/axios';

export function list(selectParam) {
    return axios.post('api/menu/list', 'post', selectParam);
}

export function edit(editParam){
    return axios.post('api/menu/edit','post',editParam);
}
