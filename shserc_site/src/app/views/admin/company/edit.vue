<template>
  <el-dialog title="新增单位" :visible.sync="visible" :before-close="handleClose">
    <el-form :model="company" ref="companyForm" :rules="rules">
      <el-form-item prop="name">
        <el-input prefix-icon="el-icon-search" v-model="company.name" placeholder="单位名称"></el-input>
      </el-form-item>
      <el-form-item prop="parentId">
        <el-select @change="selectChanged" v-model="company.parentId" placeholder="请选择" style="width: 100%">
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
import { getChildren, edit } from "@/app/api/company.js";
import { messages } from "@/app/static/message";
import {validateRequired,validateLessThan50,validateSelectValue} from "@/static/validator";
import {deepCopy} from "@/app/utils/objectHelper" 
import {companyModel} from "@/app/models/company"

export default {
  name: "edit",
  
  data() {
    return {
      company: deepCopy(companyModel),
      model: {},
      options: [],
      rules: {
        name: [
             {validator:(rule,value,callback)=>{ validateRequired(rule,value,callback,messages.COMPANY_NOT_NULL);},trigger:'blur'},
             {validator:(rule,value,callback)=>{ validateLessThan50(rule,value,callback,messages.COMPANY_LENGHT_NOT_ALLOWED_MORE_THAN_50);},trigger:'blur'},
             ],
        parentId:{validator:(rule,value,callback) => {validateSelectValue(rule,value,callback,messages.COMPANY_SELECT_NOT_NULL);},trigger:'change'}
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

    selectChanged(parentId){
      this.company.parentId=parentId;
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
    }
  }
};
</script>

<style>
.el-tree-node__label, .el-select-dropdown__item{
  font-size:12px;
}
</style>