<template>
  <div>
    <!-- steps start -->
    <el-steps :active="active" finish-status="success">
      <el-step title="第一步：信息验证"></el-step>
      <el-step title="第一步：更新密码"></el-step>
    </el-steps>
    <!-- steps end -->

    <!-- step 1 page start -->
    <div class="step1" v-show="active == 0">
      <Step1 @nextClick="next()"></Step1>
    </div>
    <!-- step 1 page end -->

    <!-- step 2 page start -->
    <div class="step2" v-show="active == 1">
       <Step2 @nextClick="next()" @previousClick="previous()"></Step2> 
    </div>
    <!-- step 2 page end -->

  </div>
</template>

<script>
import Step1 from "@/app/views/site/account/components/Steps/forgetPassword/step1.vue";
import Step2 from "@/app/views/site/account/components/Steps/forgetPassword/step2.vue";

export default {
 components: { Step1,Step2 },

  data() {
    return {
      active: 0,
    };
  },

  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },

    previous() {
      this.active--;
    },
  },
};
</script>

<style scoped>
</style>