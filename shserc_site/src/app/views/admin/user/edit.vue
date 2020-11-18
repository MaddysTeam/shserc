<template>
  <el-dialog
    title="编辑用户"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <el-form :model="user" ref="userForm" :rules="rules">
      <el-form-item prop="username">
        <el-input
          v-model="user.username"
          prefix-icon="el-icon-user"
          placeholder="输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="idCard">
        <el-input
          v-model="user.idCard"
          prefix-icon="el-icon-user"
          placeholder="输入身份证件号"
        ></el-input>
      </el-form-item>
      <el-form-item prop="companyId">
        <selectTree
          :data="source"
          placeholder="所属单位"
          :nodeKey="user.companyId"
          @input="handleSelectCompany"
        >
        </selectTree>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('userForm')"
          >确定</el-button
        >
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { Notification } from "element-ui";
import { regexs } from "@/static/regex.js";
import { messages } from "@/app/static/message.js";
import selectTree from "@/components/TreeSelector/index";
import { companyList } from "@/app/api/company";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
  validateCardNo,
} from "@/static/validator";

export default {
  name: "edit",
  components: { selectTree },
  props: {
    visible: { type: Boolean, required: true },
  },
  data() {
    var nameValidator = (rule, value, callback) => {
      validateRequired(rule, value, callback, messages.USER_NOT_NULL);
      validateLessThan50(
        rule,
        value,
        callback,
        messages.USER_LENGHT_NOT_ALLOWED_MORE_THAN_50
      );
    };

    var cardIdValidator = (rule, value, callback) => {
      validateCardNo(rule, value, callback, messages.USER_CARD_ID_NOT_VAILD);
    };

    var companyValidator = (rule, value, callback) => {
      validateSelectValue(
        rule,
        value,
        callback,
        messages.COMPANY_SELECT_NOT_NULL
      );
    };

    return {
      user: {
        id: 0,
        username: "",
        idCard: "",
        companyId: "",
      },
      rules: {
        username: { validator: nameValidator, trigger: "blur" },
        idCard: { validator: cardIdValidator, trigger: "blur" },
        companyId:{validator:companyValidator,trigger:'change'}
      },

      source: [],
    };
  },
  mounted() {
    this.handleBindCompanyList();
  },
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

    handleSelectCompany(id) {
      this.user.companyId = id + "";
    },

    handleBindCompanyList() {
      companyList().then((res) => {
        if (res && res.data) {
          this.source = [JSON.parse(res.data)];
        }
      });
    },
  },
};
</script>

<style scoped>
</style>