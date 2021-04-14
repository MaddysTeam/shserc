<template>
      <div class="page-login text-center">
    <div class="login-box center-block loginArea">

      <!-- login form start -->
      <el-form ref="loginForm"  :model="real"
      >
       <p class="title"><i class="el-icon-info"></i>  注册 - 第一步- 实名验证</p>
        <el-form-item label="卡号"> 
            <el-input v-model="real.card" suffix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
         <el-form-item label="密码"> 
            <el-input v-model="real.password" type="password" suffix-icon="el-icon-lock"></el-input>
        </el-form-item>
         <el-form-item label="确认密码"> 
            <el-input v-model="real.password" type="password" suffix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
           <el-button type="primary" style="width:100%"  @click="handleNextStep()">下一步</el-button>
        </el-form-item>
      </el-form>
      <!-- login form end -->
      
    </div>
  </div>
</template>

<script>
export default {
    props:{
       nextClick:{type: Function}
    },
    data(){
        return {
            real:{}
        }
    },
    
    methods:{
        handleNextStep(){
            this.$emit("nextClick");
        }
    }
}
</script>