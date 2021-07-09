import axios from '@/app_shsedu/static/axios'

export function menuList(selectParam) {
    return axios.post('api/menu/list', selectParam);
}