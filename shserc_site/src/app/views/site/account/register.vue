<template>
<div>
  <el-steps :active="active" finish-status="success">
    <el-step title="第一步：实名验证"></el-step>
    <el-step title="第二步：用户注册"></el-step>
  </el-steps>

      <!-- step 1 page start -->
    <div class="step1" v-show="active == 0">
      <step1 @nextClick="next()"></step1>
    </div>
    <!-- step 1 page end -->

    <!-- step 2 page start -->
    <div class="step2" v-show="active == 1">
      <step2 @nextClick="next()" @previousClick="previous()"></step2>
    </div>
    <!-- step 2 page end -->

</div>
</template>

<script>
import step1 from "@/app/views/site/account/components/Steps/register/step1.vue";
import step2 from "@/app/views/site/account/components/Steps/register/step2.vue";

  export default {
    components:{step1,step2},

    data() { 
      return {
        active: 0
      };
    },

    methods: {
      next() {
        if (this.active++ > 2) this.active = 0;
      },
      
      previous(){
        this.active--;
      }
    }
  }
</script>

<style>
.site .el-step__title{
    font-size:12px;
}
</style>
