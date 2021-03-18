<template>
  <el-dialog
    title="编辑角色"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <!-- role  form start -->
    <el-form :model="model" ref="roleForm" :rules="rules">
      <el-form-item prop="roleName">
        <el-input
          v-model="model.roleName"
          prefix-icon="el-icon-user"
          placeholder="输入角色名称"
        ></el-input>
      </el-form-item>
      <el-form-item prop="">
        <el-input type="textarea"
          v-model="model.description"
          prefix-icon="el-icon-user"
          placeholder="输入角色描述"
        ></el-input>
      </el-form-item>
    
      <el-form-item class="btns">
        <el-button type="primary" @click="handleSubmit()"
          >确定</el-button
        >
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
    <!-- role  form end -->
  </el-dialog>
</template>

<script>
import { edit } from "@/app/api/role";
import { messages } from "@/app/static/message";
import { Notification } from "element-ui";
import {
  validateRequired,
  validateLessThan50
} from "@/static/validator";

export default {

  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
  },

  data() {
    var nameValidator = (rule, value, callback) => {
      validateRequired(rule, value, callback, messages.ROLE_NAME_REQUIRED);
      validateLessThan50(
        rule,
        value,
        callback,
        messages.ROLE_NAME_LENGHT_NOT_ALLOWED_MORE_THAN_50
      );
    };

    return {
      rules: {
        roleName: { validator: nameValidator, trigger: "blur" }
      },
    };
  },

  methods: {
    loadCompanyList() {
      companyList().then((res) => {
        if (res && res.data) {
          this.companySource = [JSON.parse(res.data)];
        }
      });
    },

    handleSubmit() {
      let _this = this;
      _this.$refs["roleForm"].validate((isValid) => {
        if (isValid) {
          edit(this.model).then((res) => {
            if (res) {
              Notification.success(res.message);
              this.handleClose();
            }
          });
        }
      });
    },

    handleClose() {
      this.$emit("close");
      setTimeout(() => {
        this.$refs["roleForm"].clearValidate();
      }, 500);
    },

    handleSelectCompany(id) {
      this.model.companyId = id + "";
    },
  },
};
</script>

<style scoped>
</style>