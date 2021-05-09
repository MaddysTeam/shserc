import axios from '@/app/static/axios';

export function operationCount(userId) {
    return axios.post('api/croMy/operation', { id: userId })
}

export function myFavoriteList() {
    return axios.post('api/croMy/favorite')
}

export function downloadList() {
    return axios.post('api/croMy/download', )
}