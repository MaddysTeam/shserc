<template>
  <!-- site page start -->
  <div class="site">
    <!-- site nav start -->
    <div class="site_nav">
      <div class="div_ml_mr95">
        <!-- <a
          class="top_s_text p_l0"
          href="/Account/Login?returnUrl=%2FResource%2FAdvanceSearch%3FKeywords%3D%26SearchType%3D1%26PackageType%3D%26Domain%3D%26Deformity%3D%26ResourceType%3D%26Subject%3D%26SchoolType%3D%26LearnFrom%3D%26Grade%3D%26Sort%3D%26ShowMoreOptions%3D"
          >登录</a
        >
        <span class="top_s_bo"></span>
        <a
          class="top_s_text"
          href="/Account/Register?returnUrl=%2FResource%2FAdvanceSearch%3FKeywords%3D%26SearchType%3D1%26PackageType%3D%26Domain%3D%26Deformity%3D%26ResourceType%3D%26Subject%3D%26SchoolType%3D%26LearnFrom%3D%26Grade%3D%26Sort%3D%26ShowMoreOptions%3D"
          >注册</a
        >

        <span class="fr">欢迎访问上海市特殊教育资源库</span> -->

        <header
          role="banner"
          class="Sticky appHeader"
          data-za-module="TopNavBar"
          style="position: relative"
        >
          <div class="appHeader-inner">
            <ul role="navigation">
              <li>
                <router-link
                  to="/home/index"
                  replace
                  type="primary"
                  aria-label="上海特殊教育资源库"
                  :underline="false"
                  >欢迎访问上海市特殊教育资源库</router-link
                >
              </li>
              <li role="tab">
                <router-link
                  v-if="!isLogin"
                  to="/account/register"
                  type="danger"
                  class="font12"
                  :underline="false"
                  ><el-tag>注册</el-tag></router-link
                >
              </li>
              <li role="tab">
                <router-link
                  v-if="!isLogin"
                  to="/account/login"
                  class="font12"
                  :underline="false"
                  ><el-tag type="danger">登录</el-tag></router-link
                >
              </li>
              <li role="tab">
                <router-link
                  v-if="isLogin"
                  to="/account/space"
                  class="font12"
                  :underline="false"
                  ><el-tag type="success">欢迎您！{{account.userName}}, 进入我的空间</el-tag></router-link
                >
              </li>
            </ul>
            <div style="display:flex" class="m_5_top m_30_right">
              <el-form class="m_10_top m_30_right search" >
                <el-form-item class="searchBox">
                  <el-input
                    prefix-icon="el-icon-search"
                    placeholder="资源搜索"
                  ></el-input>
                  <el-button size="small" class="btn_login el-button el-button--primary searchButton"><i class="el-icon-search"></i></el-button>
                </el-form-item>
              </el-form>

              <el-dropdown :hide-timeout="300" v-if="isLogin">
                 <span>{{ account.userName}}</span>
                <el-image
                    class="img_header"
                    :src="account.photoPath"
                  ></el-image>
               
                <!-- <el-link class="img-header">JimmyPoor</el-link> -->
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="handleShowAccountInfo"
                    ><i class="el-icon-user-solid"></i
                    >个人空间</el-dropdown-item
                  >
                  <el-dropdown-item @click.native="handleLogout"
                    ><i class="el-icon-info"></i>注销
                  </el-dropdown-item>
                </el-dropdown-menu>
              
              </el-dropdown>
            </div>
          </div>
          <div></div>
        </header>
      </div>
    </div>

    <!-- site nav end -->
    <div class="site_container bk_gray">
      <div class="site_top">
        <img src="../../../assets/images/t_banner.jpg" />
      </div>

      <transition name="fade" mode="out-in" appear>
        <router-view class="p_30_left_right"></router-view>
      </transition>

      <div class="site_footer"></div>
    </div>
  </div>

  <!-- site page end -->
</template>

<script>
import { mapState } from 'vuex';

export default {
  components: {},

  computed:{
    ...mapState({
        isLogin:(state)=>state.app.isAuth,
        account: (state)=>state.app.account
    })
  },

  data(){
    return{

    }
  },
  
  methods:{
    handleShowAccountInfo(){
      this.$router.push({path:"/account/space"});
    }
  }
};
</script>

<style>
@import "../../../assets/css/site.css";
</style>

