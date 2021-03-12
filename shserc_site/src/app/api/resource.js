import { Form } from 'element-ui';
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
