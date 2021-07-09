<template>
  <el-dialog
    title="编辑栏目"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <el-form :model="model" ref="columnForm" :rules="rules">
      <el-form-item prop="title">
        <el-input
          prefix-icon="el-icon-search"
          v-model="model.title"
          placeholder="栏目名称"
        ></el-input>
      </el-form-item>

      <el-form-item prop="parentId">
        <el-select-tree
          :data="columns"
          :clearable="true"
          :default-expand-all="true"
          :check-strictly="true"
          v-model="model.parentId"
          placeholder="选择上级栏目"
          size="medium"
          style="width: 100%"
          @change="selectChanged"
        >
        </el-select-tree>
      </el-form-item>

      <el-form-item prop="isMenu">
        <el-switch
          v-model="model.auditResult"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item prop="orderNo">
        <el-input-number
          v-model="model.orderNo"
          :min="1"
          :max="100"
          label="同级栏目顺序号"
        ></el-input-number>
      </el-form-item>

      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('columnForm')"
          >确定</el-button
        >
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { Notification } from "element-ui";
import { list, edit } from "@/app_shsedu/api/column.js";
import { messages } from "@/app_shsedu/static/message";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
} from "@/static/validator";
import { deepCopy, buildHierarchy } from "@/app/utils/objectHelper";
import { columnModel } from "@/app/models/column";
import ElSelectTree from "el-select-tree";

export default {
  components: {
    ElSelectTree,
  },

  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
    columns: { type: Array, required: true },
  },

  data() {
    return {
      // menu: deepCopy(menuModel),
      // model: {},
      options: [],
      rules: {
        title: [
          {
            validator: (rule, value, callback) => {
              validateRequired(rule, value, callback, messages.COLUMN_NOT_NULL);
            },
            trigger: "blur",
          },
          {
            validator: (rule, value, callback) => {
              validateLessThan50(
                rule,
                value,
                callback,
                messages.COLUMN_LENGHT_NOT_ALLOWED_MORE_THAN_50
              );
            },
            trigger: "blur",
          },
        ],
      },

      treeData: [],
    };
  },

//   mounted() {
//     this.handleLoadColumns();
//   },
  methods: {
    handleClose() {
      this.$emit("close");
      setTimeout(() => {
        this.$refs["columnForm"].clearValidate();
      }, 500);
    },

    handleLoadColumns() {
      //this.treeData = []; // this.$store.state.app.menus;
    },

    selectChanged(parentId) {
      this.model.parentId = parentId;
    },

    submitForm(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          edit(this.model).then((res) => {
            Notification.success({ message: messages.SUCCESS });
            _this.handleClose();
            //TOOD:调用父页面方法 刷新列表等操作
          });
        }
      });
    },
  },
};
</script>

<style>
.el-select-tree__item {
  font-size: 12px;
}
</style>