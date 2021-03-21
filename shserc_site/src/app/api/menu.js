import axios from '@/app/static/axios';

export function list(selectParam) {
    return axios.post('api/menu/list', selectParam);
}

export function edit(editParam){
    return axios.post('api/menu/edit',editParam);
}
