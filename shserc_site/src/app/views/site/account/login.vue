<template>
  <div class="page-login text-center">
    <div class="login-box center-block loginArea">

      <!-- login form start -->
      <el-form ref="loginForm"  :model="account"
      >
       <p class="title"><i class="el-icon-info"></i>   用户登录</p>
        <el-form-item label="用户名或手机号"> 
            <el-input v-model="account.Name" suffix-icon="el-icon-user-solid"></el-input>
        </el-form-item>
         <el-form-item label="密码"> 
            <el-input v-model="account.Password" type="password" suffix-icon="el-icon-lock"></el-input>
        </el-form-item>
        <el-form-item>
           <el-button type="primary" style="width:100%">提交</el-button>
        </el-form-item>
      </el-form>
      <!-- login form end -->

      <div>
        <p>
          <router-link  to="/Account/ForgetPassword">忘记密码 <i class="el-icon-question"></i></router-link> |
          <router-link to="/Account/Register">注册</router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {

    data(){
        return {
            account: {}
        }
    }
}
</script>

<style scoped>

</style>