import axios from '@/app/static/axios'

export function list(selectParam) {
    return axios.post('api/comment/list', selectParam);
}

export function edit(editParam) {
    return axios.post('api/comment/edit', editParam);
}

export function audit(auditParam) {
    return axios.post('api/comment/audit', auditParam);
}