<template>
  <el-dialog title="绑定角色" :visible.sync="visible" :before-close="handleClose">
    <el-form :model="userRole" ref="userRoleForm" :rules="rules">
      <el-form-item prop="role">
        <el-select @change="selectChanged" v-model="userRole.roleId" placeholder="选择角色" style="width: 100%">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm('userRoleForm')">确定</el-button>
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import { Notification } from "element-ui";
import { bindUserRole } from "@/app/api/user.js";
import { messages } from "@/app/static/message";
import {validateSelectValue} from "@/static/validator";
import {deepCopy} from "@/app/utils/objectHelper" 
import {userRoleModel} from "@/app/models/user"

export default {
  data() {
    return {
      userRole: deepCopy(userRoleModel),
      options: [],
      rules: {
        role:{validator:(rule,value,callback) => {validateSelectValue(rule,value,callback,messages.ROLE_SELECT_NOT_NULL);},trigger:'change'}
      },
    };
  },

  props: {
    visible: { type: Boolean, required: true },
  },
  mounted() {
    this.handleLoadRoles();
  },
  methods: {
      
    handleClose() {
      this.$emit("close");
    },

    handleLoadRoles() {
        this.options=this.$store.state.app.roles;  //get roles from store
    },

    selectChanged(roleId){
      this.userRole.roleId=roleId;
    },

    submitForm(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          bindUserRole(userRoleModel).then((res) => {
            Notification.success({ message: "编辑成功" });
            _this.handleClose();
          });
        }
      });
    }

  }
};
</script>

<style scoped>
</style>