<template>
  <div class="page-login text-center">
    <div class="login-box center-block loginArea">
      <!-- login form start -->
      <el-form ref="form" :model="form" :rules="rules">
        <p class="title"><i class="el-icon-info"></i> 用户登录</p>

        <!-- user name -->
        <el-form-item label="用户名或手机号" prop="name">
          <el-input
            v-model="form.name"
            suffix-icon="el-icon-user-solid"
          ></el-input>
        </el-form-item>

        <!-- password -->
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="form.password"
            type="password"
            suffix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>

        <!-- verify -->
        <el-form-item>
          <Verify
            :imgUrl="appEnum.ValidateImageUrls"
            :imgName="appEnum.validateImageNames"
            :type="3"
            @success="success"
            :showButton="false"
            :barSize="{ width: '100%', height: '40px' }"
          ></Verify>
        </el-form-item>

        <el-form-item>
          <el-button  :disabled="form.isAble" type="primary" style="width: 100%" @click="submitForm('form')">提交</el-button>
        </el-form-item>
      </el-form>
      <!-- login form end -->

      <div>
        <p>
          <router-link to="/Account/ForgetPassword"
            >忘记密码 <i class="el-icon-question"></i
          ></router-link>
          |
          <router-link to="/Account/Register">注册</router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Verify from "vue2-verify";
import { encrypt } from "@/util/crypto";
import { appEnum } from "@/app/static/enum";
import { messages } from "@/app/static/message";
import {
  validateRequired,
  validateLessThan50,
  validatePassword,
} from "@/static/validator";
import * as types from "@/app/static/type";
import { login } from "@/app/api/account";
import { loginModel } from "@/app/models/account.js";
import { initMenus } from "@/app/utils/menuHelper";

export default {
  components:{Verify},

    computed: {
    ...mapState({
      isLogin: (state) => state.app.isAuth,
      account: (state) => state.app.account,
    }),
  },

  created(){
    if(this.isLogin){
       this.$router.push({
                  path: "/home/index",
                  replace:  true,
                  query: { _time: new Date().getTime() / 1000 },
                });
    }
  },

  data() {
    return {
      form: loginModel,
      appEnum: appEnum,
      rules: {
        name: [
          {
            validator: (rule, value, callback) => {
              validateRequired(
                rule,
                value,
                callback,
                messages.ACCOUNT_NAME_REQUIRED
              );
            },
          },
          {
            validator: (rule, value, callback) => {
              validateLessThan50(
                rule,
                value,
                callback,
                messages.ACCOUNT_LENGHT_NOT_ALLOWED_MORE_THAN_50
              );
            },
            trigger: "blur",
          },
        ],
      },
    };
  },

  methods: {
    success() {
      console.log("success!");
      this.form.isAble = false;
    },

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
              this.$notification.success({ message: "登录成功" });
              // save login info to store，save dynamic route info to store
              _this.$store.commit(
                types.APP + "/" + types.LOGIN,
                JSON.parse(res.data)
              );

              // to fix menu list is not show in admin site 
               initMenus(_this.$store, res.data.roleId, () => {
                 _this.$router.push({
                  path: "/home/index",
                  replace:  true,
                  query: { _time: new Date().getTime() / 1000 },
                });
              });

            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
  },
};
</script>

<style scoped>
</style>