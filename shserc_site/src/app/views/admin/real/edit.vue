<template>
  <el-dialog
    title="新建实名卡"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <!-- <el-form>
      <el-form-item></el-form-item>
    </el-form> -->
    <!-- <span>{{ value.realName }}</span> -->
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
      <!-- <el-form-item prop="companyId">
        <selectTree
          :data="source"
          placeholder="所属单位"
          :nodeKey="model.companyId"
          @input="handleSelectCompany"
        >
        </selectTree>
      </el-form-item> -->
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
import selectTree from "@/components/TreeSelector/index";
import { Notification } from "element-ui";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
} from "@/static/validator";

export default {
  components: { selectTree },
  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
  },
  data() {
    return {
      source: [],
      rules: {},
      model:{}
    };
  },
  mounted() {
    let _this = this;
    this.model=_this._props.model;
    console.log(this.model);
  },
  methods: {
    handleClose() {
      this.$emit("close");
    },

    handleSelectCompany(id) {
      //this.user.companyId = id + "";
    },
  },
};
</script>

<style scoped>
</style>