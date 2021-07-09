import axios from '@/app_shsedu/static/axios'

export function list(selectParam) {
    return axios.post('api/ShseduColumn/list', selectParam);
}

export function info(id) {
    return axios.post('api/ShseduColumn/' + id);
}

export function edit(params) {
    return axios.post('api/ShseduColumn/edit', params);
}

// export function audit(auditParams) {
//     return axios.post('api/shseduNews/audit', auditParams);
// }