<template>
  <el-dialog>
    <el-form ref="resourceAuditForm" v-model="model">
      <el-form-item label="审核资源">
        <el-switch
          v-model="model.stateId"
          active-color="#13ce66"
          inactive-color="#ff4949"
        >
        </el-switch>
      </el-form-item>
      <el-form-item label="审核意见" prop="auditOpinion">
        <el-input
          v-model="model.auditOpinion"
          placeholder="输入审核意见"
        ></el-input>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="handleSubmit('resourceAuditForm')"
          >确定</el-button
        >
        <el-button type="info" @click="handleClose('resourceAuditForm')">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
import {audit} from "@/app/api/resource"
import { clean } from "@/app/utils/objectHelper";
import { Notification } from "element-ui";
export default {
  name: "audit",
  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
  },

  data() {
      return{}
  },

  methods: {

    handleSubmit(formName) {
      let _this = this;
      _this.$refs[formName].validate((isValid) => {
        if (isValid) {
          audit(this.model).then((res) => {
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


  }
};
</script>

<style scoped>
</style>
