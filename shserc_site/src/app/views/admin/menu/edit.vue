<template>
  <el-dialog
    title="编辑菜单"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <el-form :model="model" ref="menuForm" :rules="rules">
      <el-form-item prop="title">
        <el-input
          prefix-icon="el-icon-search"
          v-model="model.title"
          placeholder="菜单名称"
        ></el-input>
      </el-form-item>
      <el-form-item prop="path">
        <el-input
          prefix-icon="el-icon-search"
          v-model="model.path"
          placeholder="菜单路径"
        ></el-input>
      </el-form-item>
      <el-form-item prop="parentId">
        <el-select-tree
          :data="treeData"
          :clearable="true"
          :default-expand-all="true"
          :check-strictly="true"
          v-model="model.parentId"
          placeholder="选择菜单"
          size="medium"
          style="width: 100%"
          @change="selectChanged"
        >
        </el-select-tree>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('menuForm')"
          >确定</el-button
        >
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { Notification } from "element-ui";
import { list, edit } from "@/app/api/menu.js";
import { messages } from "@/app/static/message";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
} from "@/static/validator";
import { deepCopy, buildHierarchy } from "@/app/utils/objectHelper";
import { menuModel } from "@/app/models/menu";
import ElSelectTree from "el-select-tree";

export default {
  components: {
    ElSelectTree,
  },

  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
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
              validateRequired(rule, value, callback, messages.MENU_NOT_NULL);
            },
            trigger: "blur",
          },
          {
            validator: (rule, value, callback) => {
              validateLessThan50(
                rule,
                value,
                callback,
                messages.MENU_LENGHT_NOT_ALLOWED_MORE_THAN_50
              );
            },
            trigger: "blur",
          },
        ]
      },

      treeData: []
    };
  },

  mounted() {
    this.handleLoadMenus();
  },
  methods: {
    handleClose() {
      this.$emit("close");
      setTimeout(() => {
        this.$refs["menuForm"].clearValidate();
      }, 500);
    },

    handleLoadMenus() {
      this.treeData = this.$store.state.app.menus;
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
.el-select-tree__item{
  font-size:12px;
}
</style>