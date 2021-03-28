import axios from '@/app/static/axios'


export function uploadFileInEditor(editorBlob, onSuccess, onerror) {
    var formData = new FormData();
    //formData.set('file', editorBlob.blob(), editorBlob.filename());
    formData.append('file', editorBlob.blob(), editorBlob.filename())
    return axios.post('api/file/upload', formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
        // onUploadProgress: progress => {
        //     options.onProgress({ percent: (progress.loaded / progress.total * 100 | 0) });
        // }
    }).then(res => {
        if (res && res.data && onSuccess) {
            onSuccess(res.data )
        }
        else if (onerror) {
            onerror(error);
        }
    }).catch(error => {
        if (onerror) {
            onerror(error);
        }
    });
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