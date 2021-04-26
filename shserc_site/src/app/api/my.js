import axios from '@/app/static/axios';

export function operationCount(userId) {
    return axios.post('api/my/operation', { id: userId })
}

export function myFavoriteList() {
    return axios.post('api/my/favorite')
}


export function downloadList() {
    return axios.post('api/my/download', )
}