<template>
  <div>
    <el-form
      ref="changePasswordForm"
      label-width="80px"
      :model="changePasswordModel"
      :rules="rules"
    >
      <el-form-item label="原密码" prop="oldPassword">
        <el-input
          type="password"
          v-model="changePasswordModel.oldPassword"
        ></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input
          type="password"
          v-model="changePasswordModel.newPassword"
        ></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="password">
        <el-input
          type="password"
          v-model="changePasswordModel.confirmPassword"
        ></el-input>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('changePasswordForm')"
          >修改</el-button
        >
        <el-button type="info" @click="handleBack()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { changePasswordModel } from "@/app/models/account";
import { changePassword } from "@/app/api/account";
import { messages } from "@/app/static/message";
import {
  validatePassword,
} from "@/static/validator";

export default {
  data() {
    return {
      changePasswordModel: changePasswordModel,
    
      rules: {
        oldPassword: [
          {
            validator: (rule, value, callback) => {
              validatePassword(
                rule,
                value,
                callback,
                messages.ACCOUNT_PASSWORD_INVALID
              );
            },
            trigger: "blur",
          },
        ],
      },
    };
  },

  methods: {
    
    // submit change password form
    submitForm(formName) {
      let _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          changePassword(this.changePasswordModel).then((res) => {
            if (res) {
              this.$notification.success({ message: messages.SUCCESS });
              // redirect to admin index page
              _this.$router.push("/admin/dashboard/summary");
            }
          });
        } else {
          console.log("");
          return false;
        }
      });
    },

    handleBack() {
      this.$router.back(-1);
    },
  },
};
</script>

<style>
.el-form-item__label {
  font-size: 12px;
}
</style>