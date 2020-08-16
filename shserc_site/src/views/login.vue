<template>
    <div class="login_area">
        <div class="login_box">
            <div class="avatar_box">
                <h3>特教资源库后台管理</h3>
            </div>

            <div>
                <el-form label-width="0px" class="login-form">
                    <!-- 用户名 -->
                    <el-form-item>
                        <el-input prefix-icon="el-icon-search" v-model="userName" placeholder="用户名"></el-input>
                    </el-form-item>
                    <!-- 密码 -->
                    <el-form-item>
                        <el-input prefix-icon="el-icon-search" type="password" v-model="password"  placeholder="密码""></el-input>
                    </el-form-item>
                    <!-- 按钮区域 -->
                    <el-form-item class="btns">
                        <el-button type="primary" @click="login">登录</el-button>
                        <el-button type="info">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <!--表单登录取域-->
    </div>
</template>

<script>
    import {login} from '../api/user';

    export default {
        data() {
            return {
               userName:'',
               password:''
            }
        },
        methods:{
            login(){
                login();
            }
        }

    }
</script>

<style scoped>
    .login_area{
        background-color: #2b4b6b;
        height: 100%;
    }
    .login_container {
        background-color: #2b4b6b;
        height: 100%;
    }

    .login_box {
        background-color: #fff;
        width: 30%;
        width: 500;
        padding:5px;
        position: absolute;
        top:30%;
        left:34.5%;
    }

    /* .avatar_box {
        height: 130px;
        width: 130px;
        border: 1px solid #eee;
        border-radius: 50%;
        padding: 10px;
        box-shadow: 0 0 10px #ddd;
        background-color: #fff;
        margin:0 auto;
    }

    .avatar_box img {
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: #eee;
    } */

    .login-form {
        bottom:0;
        width: 90%;
        margin:0 auto;
    }

    .btns {
      display: flex;
      justify-content:flex-end;
    }
</style>