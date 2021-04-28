import axios from '@/app/static/axios'

export function list(selectParam) {
    return axios.post('api/resource/list', selectParam);
}

export function info(id) {
    return axios.post('api/resource/' + id);
}

export function edit(params) {
    return axios.post('api/resource/edit', params);
}

export function audit(auditParams) {
    return axios.post('api/resource/audit', auditParams);
}

export function favorite(id) {
    return axios.post('api/resource/favorite/' + id);
}

export function star(id, score) {
    return axios.post('api/resource/star/' + id + '/' + score);
}

export function download(id) {
    return axios.post('api/resource/download/' + id);
}

export function listStarScores(id) {
    return axios.post('api/resource/stars/' + id);
}