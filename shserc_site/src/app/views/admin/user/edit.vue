<template>
  <el-dialog
    title="编辑用户"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <el-form :model="model" ref="userForm" :rules="rules">
      <el-form-item prop="userName">
        <el-input
          v-focus
          v-model="model.userName"
          prefix-icon="el-icon-user"
          placeholder="输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="idCard">
        <el-input
          v-model="model.idCard"
          prefix-icon="el-icon-postcard"
          placeholder="输入身份证件号"
        ></el-input>
      </el-form-item>
      <el-form-item prop="realName">
        <el-input
          v-model="model.realName"
          prefix-icon="el-icon-postcard"
          placeholder="输入真实姓名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="mobile">
        <el-input
          v-model="model.mobile"
          prefix-icon="el-icon-message"
          placeholder="输入手机号"
        ></el-input>
      </el-form-item>
      <el-form-item prop="email">
        <el-input
          v-model="model.email"
          prefix-icon="el-icon-message"
          placeholder="输入邮箱"
        ></el-input>
      </el-form-item>
      <el-form-item prop="companyId">
        <selectTree
          :data="companySource"
          placeholder="所属单位"
          :nodeKey="model.companyId"
          :value="model.companyId"
          @input="handleSelectCompany"
        >
        </selectTree>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('userForm')"
          >确定</el-button
        >
        <el-button type="info" @click="handleClose('userForm')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { Notification } from "element-ui";
import { messages } from "@/app/static/message.js";
import selectTree from "@/components/TreeSelector/index";
import { companyList } from "@/app/api/company";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
  validateIdCard,
  validateMobile,
  validateEmail,
} from "@/static/validator";
import { edit } from "@/app/api/user";

export default {
  name: "edit",
  components: { selectTree },
  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
  },
  data() {
    let nameValidator = (rule, value, callback) => {
      validateRequired(rule, value, callback, messages.USER_NOT_NULL, () =>
        validateLessThan50(
          rule,
          value,
          callback,
          messages.USER_LENGHT_NOT_ALLOWED_MORE_THAN_50
        )
      );
    };

    let cardIdValidator = (rule, value, callback) => {
      validateIdCard(rule, value, callback, messages.USER_CARD_ID_NOT_VAILD);
    };

    let mobileValidator = (rule, value, callback) => {
      validateMobile(rule, value, callback, messages.MOBILE_NOT_VAILD);
    };

    let emailValidator = (rule, value, callback) => {
      validateEmail(rule, value, callback, messages.EMAIL_NOT_VAILD);
    };

    let companyValidator = (rule, value, callback) => {
      validateSelectValue(
        rule,
        value,
        callback,
        messages.COMPANY_SELECT_NOT_NULL
      );
    };

    return {
      companySource: [],
      rules: {
        userName: { validator: nameValidator, trigger: "blur" },
        idCard: { validator: cardIdValidator, trigger: "blur" },
        companyId: { validator: companyValidator, trigger: "change" },
        mobile: { validator: mobileValidator, trigger: "change" },
        email: { validator: emailValidator, trigger: "change" },
      },
    };
  },
  mounted() {
    this.loadCompanyList();
  },
  methods: {
    handleClose(formName) {
      this.$emit("close");
      setTimeout(() => {
        this.$refs["userForm"].clearValidate();
      }, 500);
    },

    submitForm(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          console.log(this.model);
          edit(this.model).then((res) => {
            if (res) {
              Notification.success(res.message);
              _this.handleClose(formName);
            }
          });
        }
      });
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

<style scoped>
</style>