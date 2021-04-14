<template>
      <div class="page-login text-center">
    <div class="login-box center-block loginArea">

      <!-- login form start -->
      <el-form ref="loginForm"  :model="account"
      >
       <p class="title"><i class="el-icon-info"></i>  找回密码 - 第一步</p>
        <el-form-item label="用户名或手机号"> 
            <el-input v-model="account.Name" suffix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
         <el-form-item label="身份证件号"> 
            <el-input v-model="account.idCard" type="password" suffix-icon="el-icon-lock"></el-input>
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
            account:{}
        }
    },
    
    methods:{
        handleNextStep(){
            this.$emit("nextClick");
        }
    }
}
</script>