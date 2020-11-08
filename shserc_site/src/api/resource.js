import { Form } from 'element-ui';
import axios from '../static/axios'

export function resourceList(pageIndex,pageSize,deformityId){
    return axios.post('api/resource/list',{'current':pageIndex,'size':pageSize,'deformityId':deformityId});
}

export function resource(id){
    return axios.post('api/resource/'+id);
}

export function edit(params){
    return axios.post('api/resource/edit',params);
}


export function uploadFile(options){
    var formData=new FormData();
    formData.set('file',options.file);
    formData.append('fileMultipartFile',options.file)
    return axios.post('api/file/upload',formData,{
        headers:{'Content-Type':'multipart/form-data'},
        onUploadProgress: progress=>{
            options.onProgress({ percent: (progress.loaded / progress.total * 100 | 0) });
        }
    }).then(res=>{
        if (res && res.data) {
            options.onSuccess(res);
          }
    }).catch(error=>{
        options.onError(error);
    });
}


export function uploadCover(parmas){

}