import axios from '@/app/static/axios'


export function upload(options) {
    alert('get data from service');
    // var formData = new FormData();
    // formData.set('file', options.file);
    // formData.append('fileMultipartFile', options.file)
    // return axios.post('api/file/upload', formData, {
    //     headers: { 'Content-Type': 'multipart/form-data' },
    //     onUploadProgress: progress => {
    //         options.onProgress({ percent: (progress.loaded / progress.total * 100 | 0) });
    //     }
    // }).then(res => {
    //     if (res && res.data) {
    //         options.onSuccess(res);
    //     }
    // }).catch(error => {
    //     options.onError(error);
    // });
}


export function uploadFile(options) {
    var formData = new FormData();
    formData.set('file', options.file);
    formData.append('fileMultipartFile', options.file)
    return axios.post('api/file/upload', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
        onUploadProgress: progress => {
            options.onProgress({ percent: (progress.loaded / progress.total * 100 | 0) });
        }
    }).then(res => {
        if (res && res.data) {
            options.onSuccess(res);
        }
    }).catch(error => {
        options.onError(error);
    });
}