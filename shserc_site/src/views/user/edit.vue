<template>
  <el-dialog title="编辑用户" :visible.sync="visible" :before-close="handleClose">
    <el-form :model="user" ref="userForm" :rules="rules">
      <el-form-item prop="name">
        <el-input :mode="user.username" prefix-icon="el-icon-user" placeholder="输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="idCard">
        <el-input :mode="user.idCard" prefix-icon="el-icon-user" placeholder="输入身份证件号"></el-input>
      </el-form-item>
      <el-form-item prop="company"></el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('userForm')">确定</el-button>
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
import { Notification } from "element-ui";
import { getChildren, edit } from "../../api/company.js";
import { regexs } from "../../constans/regex.js";
import { messages } from "../../constans/message.js";

export default {
  name: "edit",
  data() {
    return {
      user: {
        id: 0,
        username: "",
        idCard: "",
        companyId: 0,
      },
    };
  },
  mounted() {},
  methods: {
    handleClose() {
      this.$emit("close");
    },

    submitForm(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          _this.handleClose();
        }
      });
    },
  },
};
</script>
<style scoped>
</style>