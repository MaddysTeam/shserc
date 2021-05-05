import axios from '@/app/static/axios'

export function list(selectParam) {
    return axios.post('api/croResource/list', selectParam);
}

export function info(id) {
    return axios.post('api/croResource/' + id);
}

export function edit(params) {
    return axios.post('api/croResource/edit', params);
}

export function audit(auditParams) {
    return axios.post('api/croResource/audit', auditParams);
}

export function favorite(id) {
    return axios.post('api/croResource/favorite/' + id);
}

export function star(id, score) {
    return axios.post('api/croResource/star/' + id + '/' + score);
}

export function download(id) {
    return axios.post('api/croResource/download/' + id);
}

export function listStarScores(id) {
    return axios.post('api/croResource/stars/' + id);
}