export function operationCount(userId) {
    return axios.post('api/my/resource/operation', { id: userId })
}