<template>
  <div class="page-login text-center">
    <div class="login-box center-block loginArea">

      <!-- login form start -->
      <el-form ref="loginForm" :model="account">
        <p class="title"><i class="el-icon-info"></i> 找回密码 - 第二步</p>
        <el-form-item label="新密码">
          <el-input
            v-model="account.newPassword"
            suffix-icon="el-icon-user-solid"
          ></el-input>
        </el-form-item>
        <el-form-item label="确认新密码">
          <el-input
            v-model="account.confirmPassword"
            type="password"
            suffix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" style="width: 30%" @click="handlePreviousStep()"
            >上一步</el-button
          >
          <el-button
            type="primary"
            style="width:30%"
            @click="handleNextStep()"
            >提交</el-button
          >
        </el-form-item>
      </el-form>
      <!-- login form end -->

    </div>
  </div>
</template>

<script>
export default {
  props: {
    nextClick: { type: Function },
    previousClick: { type: Function },
  },
  data() {
    return {
      account: {},
    };
  },

  methods: {
    handleNextStep() {
      this.$emit("nextClick");
        this.$router.push({
            path:"/account/login",
            replace:true
        })
    },

    handlePreviousStep() {
        this.$emit("previousClick");
    },
  },
};
</script>