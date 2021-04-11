<template>
<div>
  <el-steps :active="active" finish-status="success">
    <el-step title="第一步：实名验证"></el-step>
    <el-step title="第二步：用户注册"></el-step>
    <el-step title="完成"></el-step>
  </el-steps>
  <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
</div>
</template>

<script>
  export default {
    data() {
      return {
        active: 0
      };
    },

    methods: {
      next() {
        if (this.active++ > 2) this.active = 0;
      }
    }
  }
</script>

<style>
.site .el-step__title{
    font-size:12px;
}
</style>