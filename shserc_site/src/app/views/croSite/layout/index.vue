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
              <li v-if="!isLogin">
                <router-link
                  to="/croHome/index"
                  replace
                  type="primary"
                  aria-label="欢迎访问特教众筹资源平台"
                  :underline="false"
                  >欢迎访问特教众筹资源平台</router-link
                >
              </li>
              <li v-if="!isLogin">|</li>
              <li v-if="!isLogin">
                <router-link
                  to="/home/index"
                  replace
                  type="primary"
                  aria-label="上海特殊教育资源库"
                  :underline="false"
                  >进入上海市特殊教育资源库</router-link
                >
              </li>
              <!-- <li role="tab">
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
              </li>-->
              <li role="tab">
                <router-link
                  v-if="isLogin"
                  to="/croMy/space"
                  class="font12"
                  :underline="false"
                  ><el-tag type="success"
                    >欢迎您！{{ account.userName }}, 进入我的空间</el-tag
                  ></router-link
                >
              </li>
            </ul>
            <div class="flex_space_between m_5_top m_30_right">
              <el-form class="m_10_top m_30_right search">
                <el-form-item class="searchBox">
                  <el-input
                    v-model="searchPhrase"
                    prefix-icon="el-icon-search"
                    placeholder="搜索众筹资源"
                  ></el-input>
                  <el-button
                    size="small"
                    type="success"
                    class="btn_login el-button el-button--primary searchButton"
                    @click="handleSearchCroResource()"
                    ><i class="el-icon-search"></i
                  ></el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <div></div>
        </header>
      </div>
    </div>

    <!-- site nav end -->
    <div class="site_container m_30_bottom">
      <div class="site_top" style="height: 190px; position: relative">
        <img
          src="../../../assets/images/i_banner.jpg"
          style="position: absolute; left: 0px"
        />
        <img src="../../../assets/images/logo.png" style="position: absolute; left: 0px; width:200px; left:30px; top:30px;"/>
        <div style="height:120px;"></div>
        <el-menu
          default-active="0"
          class="el-menu-demo m_30_left"
          mode="horizontal"
          style="position:none; background:transparent"
          text-color="#578d38"
          active-text-color ="#578d38"
        >
          <el-menu-item index="1"  style="; font-size:16px">首页</el-menu-item>
          <el-menu-item index="2" style=" font-size:16px">课程与教学</el-menu-item>
          <el-menu-item index="3" style="font-size:16px">康复与干预</el-menu-item>
          <el-menu-item index="4" style="font-size:16px">支持与服务</el-menu-item>
           <el-menu-item index="5" style="font-size:16px">分类搜索</el-menu-item>
        </el-menu>
      </div>

      <transition name="fade" mode="out-in" appear>
        <router-view class=""></router-view>
      </transition>

      
    </div>

    <div class="site_cro_footer"></div>

  </div>

  <!-- site page end -->
</template>

<script>
import { mapState } from "vuex";
import * as types from "@/app/static/type";
import { logout } from "@/app/api/account";

export default {
  components: {},

  computed: {
    ...mapState({
      isLogin: (state) => state.app.isAuth,
      account: (state) => state.app.account,
    }),
  },

  data() {
    return {
      searchPhrase: "",
    };
  },

  methods: {
    // handleShowAccountInfo(){
    //   this.$router.push({path:"/account/space"});
    // },

    //  handleLogout() {
    //   logout().then((res) => {
    //     this.$store.commit(types.APP + "/" + types.LOGOUT);
    //     this.$router.push({ path: "/admin/logout", replace: true });
    //   });
    // },

    handleSearchCroResource() {
      //this.$router.push("/resource/search?key="+this.searchPhrase);
      // this.searchPhrase="";
    },
  },
};
</script>

<style>
@import "../../../assets/css/site.css";

.el-menu-item:hover {
   background-color:#e1f3d8 !important; 
}
.el-menu-item.is-active {
background-color: transparent !important;
}
</style>

