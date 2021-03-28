<template>
  <div>
    <el-form ref="accountForm" label-width="80px" v-model="account">
      <el-form-item>
        <!-- avatar start -->
        <el-avatar :size="200">
          <img :src="account.photoPath"  @error="handleImageError()"/>
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
          {{ account.userName }}
        </div>
      </el-form-item>
      <el-form-item label="所属单位">
         <selectTree
          :data="companySource"
          placeholder="所属单位"
          :nodeKey="account.companyId.toString()"
          :value="account.companyId.toString()"
          @input="handleSelectCompany"
        >
        </selectTree>
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="account.email"></el-input>
      </el-form-item>
      <el-form-item label="手机">
        <el-input v-model="account.mobile"></el-input>
      </el-form-item>
      <!-- others -->

      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('accountForm')"
          >修改</el-button
        >
        <el-button type="info" @click="handleBack()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { edit } from "@/app/api/user";
import { companyList } from "@/app/api/company";
import { accountModel } from "@/app/models/account";
import { uploadFile } from "@/app/api/file";
import { messages } from "@/app/static/message";
import selectTree from "@/components/TreeSelector/index";
import {CDN} from "@/static/CDN"

export default {
  components:{selectTree},
  data() {
    return {
      account: accountModel,
      fileList: [],
      companySource:[]
    };
  },

  mounted(){
    this.handLoadCompanyList();
  },

  methods: {

    handleLoadAccount(){
        this.account=this.$store.state.app.account;
        console.log(this.account);
        if( this.account.photoPath==null || !this.account.photoPath || this.account.photoPath=="" ){
          this.account.photoPath=CDN.DEFAULT_HEADER_COVER
        } 
        
        //account.companyId=account.companyId.toString();
        
    },

     handleImageError(){
      let img=event.srcElement;
      img.src = CDN.DEFAULT_HEADER_COVER;
	    img.onerror = null; //防止闪图
    },


    handLoadCompanyList() {
      companyList().then((res) => {
        if (res && res.data) {
          this.companySource = [JSON.parse(res.data)];

          this.handleLoadAccount();
        }
      });
    },

    handleSelectCompany(id) {
      this.account.companyId = id + "";
    },

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
      console.log(this.account);
       edit(this.account).then((res) => {
            if (res) {
              this.$notification.success(messages.SUCCESS);
            }
          });
      // this.$refs[formName].validate((vaild) => {
      //   if (vaild) {
         
      //   }
      // });
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