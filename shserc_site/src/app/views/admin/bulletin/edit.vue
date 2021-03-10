<template>
  <el-form label-width="80px">
    <el-form-item label="公告标题" prop="title">
      <el-input v-model="model.title" placeholder="公告标题"></el-input>
    </el-form-item>
    <el-form-item prop="title" align="left" label="附件上传">
      <el-upload
        class="upload-file"
        action="https://jsonplaceholder.typicode.com/posts/"
        :limit="1"
        :on-change="uploadResourceHandleChange"
        :file-list="fileList"
        :on-exceed="uploadResourceHandleExceed"
        :on-error="uploadResourceError"
        :on-success="uploadResourceSuccess"
        :on-remove="uploadResourceOnRemoveTxt"
        :before-upload="uploadResourceOnBeforeUpload"
        :http-request="uploadBulletinAttachment"
      >
        <el-button type="primary">点击上传附件</el-button>
        <div slot="tip" class="el-upload__tip"></div>
      </el-upload>
    </el-form-item>
    <el-form-item label="内容编辑">
      <div>
        <TinymceEditor v-model="content" @uploadImage="uploadImage"></TinymceEditor>
      </div>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="handleSubmit()">提交</el-button>
      <el-button type="info" @click="handleSubmit()">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { bulletinModel } from "@/app/models/bulletin";
import { uploadFile } from "@/app/api/bulletin";

export default {
  data() {
    return {
      fileList: [],
      model: bulletinModel,
      content:  ""
    };
  },
  mounted() {},

  methods: {
    // attachment methods   

    uploadResourceHandleChange(file, fileList) {
      if (fileList.length > 0) {
        //TODO: will force upload  one file which will replace previous one
      }
    },

    uploadResourceHandleExceed() {
      Notification.error({ message: messages.FILE_UPLOAD_COUNT_ALLOWED });
    },

    uploadResourceError(error) {
      console.log(error);
    },

    uploadResourceSuccess(response, file, fileList) {
      var data = JSON.parse(response.data);
      this.resource.resourcePath = data.filePath;
      this.resource.fileName = data.fileName;
      this.resource.fileExtName = data.fileExtName;
      this.resource.fileSize = data.fileSize;
    },

    uploadResourceOnRemoveTxt() {},

    uploadResourceOnBeforeUpload() {},

    uploadBulletinAttachment(options) {
      uploadFile(options);
    },

    // tinymce

    loadContent() {},

    uploadImage(blobInfo, success, failure) {
      const img = "http://tjcdn.shec.edu.cn/404.jpg";
      success(img);

        // function (blobInfo, success, failure) {
        //   this.temp();
        //   //upload();
        //   //文件上传的formData传递，忘记为什么要用这个了
        //   // const isAccord = blobInfo.blob().type === 'image/jpeg' || blobInfo.blob().type === 'image/png' || blobInfo.blob().type === 'image/GIF' || blobInfo.blob().type === 'image/jpg' || blobInfo.blob().type === 'image/BMP';
        //   // if (blobInfo.blob().size/1024/1024>2) {
        //   //   failure("上传失败，图片大小请控制在 2M 以内")
        //   // } else if (blobInfo.blob().type == isAccord) {
        //   //   failure('图片格式错误')
        //   // } else {
        //   //   console.log(blobInfo.blob());
        //   //   let formData = new FormData()
        //   //   // 服务端接收文件的参数名，文件数据，文件名
        //   //   formData.append('file', blobInfo.blob(), blobInfo.filename())
        //   //   axios({
        //   //     method: 'POST',
        //   //     headers: {
        //   //       Authorization: 'bearer ' + Cookies.get('access_token')
        //   //     },
        //   //     // 这里是你的上传地址
        //   //     url:  window.SITE_CONFIG['apiURL'] + '/oss/file/upload',
        //   //     data: formData,
        //   //   }).then((res) => {
        //   //     console.log(res)
        //   //     // 这里返回的是你图片的地址
        //   //     success(res.data.data.url)
        //   //   }).catch(() => {
        //   //     failure('上传失败')
        //   //   })
        //   // }
        // },
    },

    // form submit

    handleSubmit() {
     //alert(this.content);
    },

    handCancel() {},
  },
};
</script>

<style >
.edit_container {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.ql-editor {
  height: 400px;
  background: white;
}
</style>