<template>
  <el-dialog title="新增单位" :visible.sync="visible" :before-close="handleClose">
    <el-form :model="company" ref="companyForm" :rules="rules">
      <el-form-item prop="name">
        <el-input prefix-icon="el-icon-search" v-model="company.name" placeholder="单位名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="model" placeholder="请选择" style="width: 100%">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('companyForm')">确定</el-button>
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
    var checkName = (rule, value, callback) => {
      if (!value) {
        Notification.error({ message: messages()["COMPANY_NOT_NULL"] });
      } else if (!regexs()["length50"].test(value)) {
        Notification.error({
          message: messages()["COMPANY_LENGHT_NOT_ALLOWED_MORE_THAN_50"],
        });
      } else {
        callback();
      }
    };

    return {
      company: {
        name: "",
        parentId: "",
        id: "",
      },
      model: {},
      options: [],
      rules: {
        name: { validator: checkName, trigger: "blur" },
      },
    };
  },
  props: {
    visible: { type: Boolean, required: true },
  },
  mounted() {
    this.bindCompany(1);
  },
  methods: {
    handleClose() {
      this.$emit("close");
    },
    bindCompany(root) {
      getChildren(root).then((res) => {
        if (res && res.data) {
          var array = JSON.parse(res.data).children;
          var resultArray = [];
          for (let index = 0; index < array.length; index++) {
            resultArray.push({
              value: array[index].id,
              label: array[index].label,
            });
          }
          this.options = resultArray;
        }
      });
    },
    submitForm(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          edit(this.company).then((res) => {
            Notification.success({ message: "编辑成功" });
            _this.handleClose();
            //TOOD:调用父页面方法 刷新列表等操作
          });
        }
        
      });
    },
  },
};
</script>

<style scoped>
</style>