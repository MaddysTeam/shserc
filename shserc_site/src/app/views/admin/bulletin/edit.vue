<template>
  <el-form label-width="80px">
    <el-form-item label="公告标题" prop="title">
      <el-input v-model="model.title" placeholder="公告标题"  ></el-input>
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
      
        <el-button type="primary" >点击上传附件</el-button>
        <div slot="tip" class="el-upload__tip"></div>
      </el-upload>
    </el-form-item>
    <el-form-item label="内容编辑">
      <div>
        <!-- <Editor v-model="content" @contentData="loadContent($event)"></Editor> -->
      </div>
    </el-form-item>
  </el-form>
</template>

<script>
import { bulletinModel } from "@/app/models/bulletin";
import { uploadFile } from "@/app/api/bulletin";
// import Editor from "@/components/Editor/index";

export default {
  components: {
   // Editor,
  },
  data() {
    return {
      fileList: [],
      model: bulletinModel,
      content: { html: '<strong style="color:red">hey!</strong>' },
    };
  },
  mounted() {},

  methods: {
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
      console.log(data);
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

    loadContent() {
      this.content.value = "<div>hello</div>";
    },
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