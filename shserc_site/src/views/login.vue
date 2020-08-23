<template>
    <div class="login_area">
        <div style="height: 30%;"></div>
        <div class="login_box">
            <div class="avatar_box">
                <h3>特教资源库后台管理</h3>
            </div>

            <div>
             <!--login form-->
                <el-form :model="form" :rules="rules" ref="form" label-width="0px" class="login-form">
                    <!-- user name -->
                    <el-form-item prop="name">
                        <el-input prefix-icon="el-icon-search" v-model="form.name" placeholder="用户名">
                        </el-input>
                    </el-form-item>
                    
                    <!-- password -->
                    <el-form-item prop="password">
                        <el-input prefix-icon="el-icon-search" type="password" v-model="form.password"
                            placeholder="密码"></el-input>
                    </el-form-item>

                    <!-- verify -->
                    <el-form-item>
                        <Verify :type="3" style="width:100%" @success="success" :showButton=false
                            :barSize="{width:'100%',height:'40px'}"></Verify>
                    </el-form-item>

                    <!-- buttons -->
                    <el-form-item class="btns">
                        <el-button :disabled="form.isAble"  type="primary" @click="submitForm('form')">登录</el-button>
                        <el-button :disabled="form.isAble"  type="info">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
        <!--login form-->
    </div>
</template>

<script>
    import Verify from 'vue2-verify'
    import { login } from '../api/user';
    import { Notification } from 'element-ui';

    export default {
        data() {
            return {
                form: {
                    name: '',
                    password: '',
                    isAble:true
                },
                rules: {
                    name: [
                        { required: true, message: '请输入用户名或邮箱', trigger: 'blur' }
                    ]
                }
            }
        },
        components: { Verify },
        methods: {
            success() {
                console.log('success!');
                this.form.isAble=false;
            },

            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        login({"userName":this.form.name,"password":this.form.password}).then(res=>{
                            Notification.success({message:"登录成功"});
                            sessionStorage.setItem("token",res.data.token)
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        },

    }
</script>

<style scoped>
    .login_area {
        background-color: #2b4b6b;
        height: 100%;
    }

    .login_box {
        background-color: #fff;
        padding: 5px;
        margin: 0 auto;
        width: 400px;
        /* position: absolute;
        top:30%;*/

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
        bottom: 0;
        width: 90%;
        margin: 0 auto;
    }

    .btns {
        display: flex;
        justify-content: flex-end;
    }
</style>