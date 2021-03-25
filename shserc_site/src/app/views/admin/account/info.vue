<template>
  <div>
    <el-form label-width="80px" v-model="account">
      <el-form-item>
        <el-avatar :size="200">
          <img
            :src="account.photoPath"
          />
        </el-avatar>
        <el-upload
          class="upload-file"
          action="https://jsonplaceholder.typicode.com/posts/"
        ></el-upload>
        <el-button type="primary"
          ><i class="el-icon-upload2"></i> 上传头像</el-button
        >
        <div slot="tip" class="el-upload__tip"></div>
      </el-form-item>
      <el-form-item label="账号名称">
          <div  style="text-align:left; border:1px solid #C0C4CC;padding-left:15px; font-size:inherit;border-radius:4px">3444444444</div>
        <!-- <el-input v-model="account.name"></el-input> -->
      </el-form-item>
      <el-form-item label="证件号" >
        <el-input v-model="account.idCard"></el-input>
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
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('companyForm')">修改</el-button>
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { accountModel } from "@/app/models/account";

export default {
  data() {
    return {
      account: accountModel,
    };
  },
};
</script>

<style scoped>

</style>