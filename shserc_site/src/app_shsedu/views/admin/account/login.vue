<template>
  <div class="login_area">
    <div style="height: 20%"></div>
    <div class="login_box">
      <div class="avatar_box">
        <h3>特教之窗后台管理</h3>
      </div>

      <div>
        <!--login form-->
        <el-form
          :model="form"
          :rules="rules"
          ref="form"
          label-width="0px"
          class="login-form"
        >
          <!-- user name -->
          <el-form-item prop="name">
            <el-input
              prefix-icon="el-icon-search"
              v-model="form.name"
              placeholder="请输入用户名、邮箱或者手机号"
            ></el-input>
          </el-form-item>

          <!-- password -->
          <el-form-item prop="password">
            <el-input
              prefix-icon="el-icon-search"
              type="password"
              v-model="form.password"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>

          <!-- verify -->
          <el-form-item>
            <Verify
              :imgUrl="appEnum.ValidateImageUrls"
              :imgName="appEnum.validateImageNames"
              :type="4"
              style="width: 100%"
              @success="success"
              :showButton="false"
              :barSize="{ width: '100%', height: '40px' }"
            ></Verify>
          </el-form-item>

          <!-- buttons -->
          <el-form-item class="btns">
            <el-button
              :disabled="form.isAble"
              type="primary"
              @click="submitForm('form')"
              >登录</el-button
            >
            <el-button :disabled="form.isAble" type="info">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <!--login form-->
  </div>
</template>
<script>
import Verify from "vue2-verify";
import { loginModel } from "@/app_shsedu/models/account.js";
import {messages} from "@/app_shsedu/models/message.js"

export default {
  components: { Verify },
  methods:{
        
  }
};
</script>