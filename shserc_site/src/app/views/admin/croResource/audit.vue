<template>
  <el-dialog :visible.sync="visible" :before-close="handleClose" title="资源审核" width="30%">
    <el-form ref="resourceAuditForm" v-model="model" label-width="80px">
      <el-form-item label="资源标题" > 
        <el-input v-model="model.resourceTitle" :disabled="true" />
      </el-form-item>
      <el-form-item label="审核资源" class="text_align_left">
        <el-switch 
          v-model="model.auditResult"
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
import {audit} from "@/app/api/croResource"
import { clean } from "@/app/utils/objectHelper";
import { Notification } from "element-ui";
import { mapState } from 'vuex';

export default {
  name: "audit",
  props: {
    visible: { type: Boolean, required: true },
    model: { type: Object },
  },

  data() {
      return{
       
      }
  },

  computed:{
     ...mapState({
        resourceStatus:(state)=> state.app.resourceStatus
     })
  },

  methods: {
    handleSubmit(formName) {
       audit(this.model).then((res) => {
            if (res) {
              Notification.success(res.message);
              this.handleClose(formName);
            }
          });
    },

     handleClose(formName) {
      this.$emit("close");
      clean(this.model);
    },
  }
};
</script>

<style scoped>

</style>
