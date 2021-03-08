<template>
  <el-dialog
    title="编辑用户"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <el-form :model="model" ref="userForm" :rules="rules">
      <el-form-item prop="username">
        <el-input
          v-model="model.userName"
          prefix-icon="el-icon-user"
          placeholder="输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="idCard">
        <el-input
          v-model="model.idCard"
          prefix-icon="el-icon-user"
          placeholder="输入身份证件号"
        ></el-input>
      </el-form-item>
      <el-form-item prop="companyId">
        <selectTree
          :data="companySource"
          placeholder="所属单位"
          :nodeKey="model.companyId"
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
import { clean } from "@/app/utils/objectHelper";
import { messages } from "@/app/static/message.js";
import selectTree from "@/components/TreeSelector/index";
import { list } from "@/app/api/company";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
  validateIdCard,
} from "@/static/validator";
import { edit } from "@/app/api/user";

export default {
  name: "edit",
  components: { selectTree },
  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object }
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
      validateIdCard(rule, value, callback, messages.USER_CARD_ID_NOT_VAILD);
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
      rules: {
        username: { validator: nameValidator, trigger: "blur" },
        idCard: { validator: cardIdValidator, trigger: "blur" },
        companyId: { validator: companyValidator, trigger: "change" },
      },

      companySource: [],
    };
  },
  mounted() {
    this.loadCompanyList();
  },
  methods: {
    handleClose() {
      this.$emit("close");
    },

    submitForm(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          edit(this.user).then((res) => {
            Notification.success("");
          });
          _this.handleClose();
        }
      });
    },

    handleSelectCompany(id) {
      this.user.companyId = id + "";
    },

    handleClose(formName) {
      this.$emit("close");
      clean(this.model);
      this.$refs[formName].clearValidate();
    },

    loadCompanyList() {
      list().then((res) => {
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