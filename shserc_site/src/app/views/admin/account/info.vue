<template>
  <div>
    <el-form ref="accountForm" label-width="80px" v-model="account">
      <el-form-item>
        <!-- avatar start -->
        <el-avatar :size="200">
          <img :src="account.photoPath" />
        </el-avatar>
        <!-- avatar end -->

        <!-- upload start -->
        <el-upload
          class="upload-file"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-change="uploadResourceHandleChange"
          :file-list="fileList"
          :on-exceed="uploadResourceHandleExceed"
          :on-error="uploadResourceError"
          :on-success="uploadResourceSuccess"
          :on-remove="uploadResourceOnRemoveTxt"
          :before-upload="uploadResourceOnBeforeUpload"
          :http-request="uploadResource"
        >
          <el-button type="primary">
            <i class="el-icon-upload font20"></i>上传头像
          </el-button>
        </el-upload>
        <div slot="tip" class="el-upload__tip"></div>
      </el-form-item>
      <!-- upload end -->

      <!-- others -->
      <el-form-item label="账号名称">
        <div class="el-label">
          {{ account.name }}
        </div>
      </el-form-item>
      <el-form-item label="所属单位">
        <el-input v-model="account.company"></el-input>
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="account.email"></el-input>
      </el-form-item>
      <el-form-item label="手机">
        <el-input v-model="account.mobile"></el-input>
      </el-form-item>
      <!-- others -->

      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('companyForm')"
          >修改</el-button
        >
        <el-button type="info" @click="handleBack()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { info } from "@/app/api/user";
import { accountModel } from "@/app/models/account";
import { uploadFile } from "@/app/api/file";
import { messages } from "@/app/static/message";

export default {
  data() {
    return {
      account: accountModel,
      fileList: [],
    };
  },

  methods: {
    uploadResource(options) {
      uploadFile(options);
    },

    uploadResourceHandleChange(file, fileList) {
      if (fileList.length > 0) {
        //TODO: will force upload  one file which will replace previous one
      }
    },

    uploadResourceHandleExceed() {
      this.$notification.error({ message: messages.FILE_UPLOAD_COUNT_ALLOWED });
    },

    uploadResourceError(error) {
      console.log(error);
    },

    uploadResourceSuccess(response, file, fileList) {
      var data = JSON.parse(response.data);
      this.account.photoPath = data.filePath;
    },
    uploadResourceOnRemoveTxt() {},

    uploadResourceOnBeforeUpload() {},

    handleBack() {
      this.$router.back(-1);
    },

    submitForm(formName) {
      this.$ref[formName].validate((vaild) => {
        if (vaild) {
          info(this.account).then((res) => {
            if (res) {
              this.$notification.success(messages.SUCCESS);
            }
          });
        }
      });
    },
  },
};
</script>

<style>
.el-form-item__label {
  font-size: 12px;
}

.el-label {
  text-align: left;
  border: 1px solid #c0c4cc;
  padding-left: 15px;
  font-size: inherit;
  border-radius: 4px;
}
</style>