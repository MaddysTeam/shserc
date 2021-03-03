<template>
  <el-dialog
    title="编辑实名卡"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <!-- real edit form start -->
    <el-form :model="model" ref="realForm" :rules="rules">
      <el-form-item prop="realName">
        <el-input
          v-model="model.realName"
          prefix-icon="el-icon-user"
          placeholder="输入真实姓名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="cardNo">
        <el-input
          v-model="model.cardNo"
          prefix-icon="el-icon-user"
          placeholder="输入实名卡号"
        ></el-input>
      </el-form-item>
      <el-form-item prop="cardPassword">
        <el-input
          v-model="model.cardPassword"
          prefix-icon="el-icon-user"
          placeholder="输入实名卡密码"
          type="password"
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
        <el-button type="primary" @click="handleSubmit('realForm')"
          >确定</el-button
        >
        <el-button type="info" @click="handleClose('realForm')">取消</el-button>
      </el-form-item>
    </el-form>
    <!-- real edit form end -->
  </el-dialog>
</template>

<script>
import { edit } from "@/app/api/real";
import { companyList } from "@/app/api/company";
import { messages } from "@/app/static/message";
import { clean } from "@/app/utils/objectHelper";
import selectTree from "@/components/TreeSelector/index";
import { Notification } from "element-ui";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
  validateIdCard,
} from "@/static/validator";

export default {
  components: { selectTree },
  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
  },
  data() {
    var nameValidator = (rule, value, callback) => {
      validateRequired(rule, value, callback, messages.REAL_NAME_IS_REQUIRED);
      validateLessThan50(
        rule,
        value,
        callback,
        messages.REAL_NAME_LENGHT_NOT_ALLOWED_MORE_THAN_50
      );
    };

    var cardIdValidator = (rule, value, callback) => {
      validateIdCard(rule, value, callback, messages.USER_CARD_ID_NOT_VAILD);
    };

    var cardNoValidator = (rule, value, callback) => {
      validateRequired(rule, value, callback, messages.REAL_CARDNO_IS_REQUIRED);
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
      companySource: [],
      rules: {
        realName: { validator: nameValidator, trigger: "blur" },
        idCard: { validator: cardIdValidator, trigger: "blur" },
        cardNo: { validator: cardNoValidator, trigger: "blur" },
        companyId: { validator: companyValidator, trigger: "change" },
      },
    };
  },
  mounted() {
    this.loadCompanyList();
  },
  methods: {
    loadCompanyList() {
      companyList().then((res) => {
        if (res && res.data) {
          this.companySource = [JSON.parse(res.data)];
        }
      });
    },

    handleSubmit(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          edit(this.model).then((res) => {
            if (res) {
              Notification.success(res.message);
              this.handleClose(formName);
            }
          });
        }
      });
    },

    handleClose(formName) {
      this.$emit("close");
      clean(this.model);
      this.$refs[formName].clearValidate();
    },

    handleSelectCompany(id) {
      this.model.companyId = id + "";
    },
  },
};
</script>

<style scoped>
</style>