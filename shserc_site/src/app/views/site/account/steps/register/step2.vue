<template>
  <div class="page-login text-center">
    <div class="login-box center-block loginArea">
      <!-- login form start -->
      <el-form ref="loginForm" :model="user">
        <p class="title"><i class="el-icon-info"></i> 注册 - 第二步-用户注册</p>
        <el-form-item prop="userName">
          <el-input
            v-focus
            v-model="user.userName"
            prefix-icon="el-icon-user"
            placeholder="输入用户名"
          ></el-input>
        </el-form-item>
          <el-form-item prop="password">
          <el-input
            type="password"
            v-model="user.password"
            prefix-icon="el-icon-postcard"
            placeholder="输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item prop="idCard">
          <el-input
            v-model="user.idCard"
            prefix-icon="el-icon-postcard"
            placeholder="输入身份证件号"
          ></el-input>
        </el-form-item>
        <el-form-item prop="realName">
          <el-input
            v-model="user.realName"
            prefix-icon="el-icon-postcard"
            placeholder="输入真实姓名"
          ></el-input>
        </el-form-item>
        <el-form-item prop="mobile">
          <el-input
            v-model="user.mobile"
            prefix-icon="el-icon-message"
            placeholder="输入手机号"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input
            v-model="user.email"
            prefix-icon="el-icon-message"
            placeholder="输入邮箱"
          ></el-input>
        </el-form-item>
        <el-form-item prop="companyId">
          <selectTree
            :data="companySource"
            placeholder="所属单位"
            :nodeKey="user.companyId"
            :value="user.companyId"
            @input="handleSelectCompany"
          >
          </selectTree>
        </el-form-item>
        <el-form-item>
          <el-button
            type="info"
            style="width: 30%"
            @click="handlePreviousStep()"
            >上一步</el-button
          >
          <el-button type="primary" style="width: 30%" @click="handleNextStep()"
            >提交</el-button
          >
        </el-form-item>
      </el-form>
      <!-- login form end -->
    </div>
  </div>
</template>

<script>
import selectTree from "@/components/TreeSelector/index";

export default {
  components: { selectTree },

  props: {
    nextClick: { type: Function },
    previousClick: { type: Function },
  },
  data() {
    return {
      user: {},
      companySource:[]
    };
  },

  methods: {
    handleNextStep() {
      this.$emit("nextClick");
      this.$router.push({
        path: "/account/login",
        replace: true,
      });
    },

    handlePreviousStep() {
      this.$emit("previousClick");
    },

    handleSelectCompany(id) {
      this.model.companyId = id + "";
    },

    loadCompanyList() {
      companyList().then((res) => {
        if (res && res.data) {
          this.companySource = [JSON.parse(res.data)];
        }
      });
    },
  },
};
</script>