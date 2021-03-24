import axios from '@/app/static/axios';

export function list(selectParam) {
    return axios.post('api/menu/list', selectParam);
}

export function edit(editParam){
    return axios.post('api/menu/edit',editParam);
}

export function editMenuRole(editParam){
    let jsonStr=JSON.stringify({menuRoles:editParam});
    return axios.post('api/menu/role/edit/',editParam);
}

export function changeState(changeStateParam){
    return axios.post('api/menu/state/edit',changeStateParam);
}
