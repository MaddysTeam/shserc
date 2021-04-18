import axios from '@/app/static/axios'

export function list(selectParam) {
    return axios.post('api/comment/list', selectParam);
}

export function edit(editParam) {
    return axios.post('api/comment/edit', editParam);
}

export function commentList(selectParam) {
    return list(selectParam);
}

export function commentEdit(editParam) {
    return edit(editParam);
}