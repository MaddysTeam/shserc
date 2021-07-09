<template>
  <div class="login_area">
    <div style="height: 20%"></div>
    <div class="login_box">
      <div class="avatar_box">
        <h3>特教之窗后台管理</h3>
      </div>

      <div>
        <!--login form-->
        <el-form
          :model="form"
          :rules="rules"
          ref="form"
          label-width="0px"
          class="login-form"
        >
          <!-- user name -->
          <el-form-item prop="name">
            <el-input
              prefix-icon="el-icon-search"
              v-model="form.name"
              placeholder="请输入用户名、邮箱或者手机号"
            ></el-input>
          </el-form-item>

          <!-- password -->
          <el-form-item prop="password">
            <el-input
              prefix-icon="el-icon-search"
              type="password"
              v-model="form.password"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>

          <!-- verify -->
          <el-form-item>
            <Verify
              :type="3"
              style="width: 100%"
              @success="success"
              :showButton="false"
              :barSize="{ width: '100%', height: '40px' }"
            ></Verify>
          </el-form-item>

          <!-- buttons -->
          <el-form-item class="btns">
            <el-button
              :disabled="!form.isAble"
              type="primary"
              @click="submitForm('form')"
              >登录</el-button
            >
            <el-button :disabled="!form.isAble" type="info">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <!--login form-->
  </div>
</template>
<script>
import Verify from "vue2-verify"
import { loginModel } from "@/app_shsedu/models/account.js"
import { messages } from "@/app_shsedu/static/message.js"
import {login} from "@/app_shsedu/api/account"
import { appEnum } from "@/app_shsedu/static/enum"
import {
  validateRequired,
  validateLessThan50,
  validatePassword,
} from "@/static/validator";
import {initMenus} from "@/app_shsedu/utils/menuHelper"
import {encrypt} from "@/util/crypto"
import  * as types from  "@/app_shsedu/static/type"

export default {
  components: { Verify },
  data() {
    return {
      form: loginModel,
      rules: {
        name: [
          {
            validator: (rule, value, callback) => {validateRequired(rule,value,callback,messages.ACCOUNT_NAME_REQUIRED)},
            validator: (rule, value, callback) => {validateLessThan50(rule,value,callback,messages.ACCOUNT_LENGHT_NOT_ALLOWED_MORE_THAN_50)}
          },
        ],
      },
    };
  },

  methods: { 
   /**
     *  submit login from
     */
    submitForm(formName) {
      let _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          login({
            userName: _this.form.name,
            password: encrypt(_this.form.password), // encrypt password and  de-encrypt in server side
          }).then((res) => {
            if (res) {
              //Notification.success({ message: "登录成功" });
              // save login info to store，save dynamic route info to store
              _this.$store.commit(types.APP + "/" + types.LOGIN, JSON.parse(res.data));

              initMenus(_this.$store, res.data.roleId, () => {
                _this.$router.push({
                  path: "/admin/user/list",
                  replace:  true,
                  query: { _time: new Date().getTime() / 1000 },
                });
              });

              // redirect to admin index page
            }
          });
        } else {
          console.log("error submit!");
          return false;
        }
      });
    },

     success() {
      console.log("success!");
      this.form.isAble = true;
    },

   //TODO
    initMenus(){
        var mockMenus
    }

  },
};
</script>

<style scoped>
.login_area {
  background-color:rgb(220, 226, 241);
  height: 100%;
}

.login_box {
  background-color: #fff;
  padding: 5px;
  margin: 0 auto;
  width: 445px;
}

.login-form {
  bottom: 0;
  width: 90%;
  margin: 0 auto;
}

.btns {
  display: flex;
  justify-content: flex-end;
}

</style>