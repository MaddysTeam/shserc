import axios from '@/app_shsedu/static/axios'

export function list(selectParam) {
    return axios.post('api/shseduNews/list', selectParam);
}

export function info(id) {
    return axios.post('api/shseduNews/' + id);
}

export function edit(params) {
    return axios.post('api/shseduNews/edit', params);
}

export function audit(auditParams) {
    return axios.post('api/shseduNews/audit', auditParams);
}