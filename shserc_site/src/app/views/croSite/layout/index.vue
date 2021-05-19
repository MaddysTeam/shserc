<template>
  <!-- site page start -->
  <div class="site">
    <!-- site nav start -->
    <div class="site_nav">
      <div class="div_ml_mr95">

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
                  to="/croSite/home/index"
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
              <li role="tab">
                <router-link
                  v-if="isLogin"
                  to="/croSite/space"
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
        <img
          src="../../../assets/images/logo.png"
          style="
            position: absolute;
            left: 0px;
            width: 200px;
            left: 30px;
            top: 30px;
          "
        />
        <div style="height: 120px"></div>
        <el-menu
          default-active="8"
          class="el-menu-demo m_30_left"
          mode="horizontal"
          style="position: none; background: transparent"
          text-color="#578d38"
          active-text-color="#578d38"
        >
          <el-menu-item
            index="8"
            style="font-size: 16px"
            @click="handelNavToHome"
            >首页</el-menu-item
          >
          <el-menu-item
            :index="index.toString()"
            v-for="(item, index) in domains"
            :key="item.id"
            style="font-size: 16px"
            @click="handelNavToSearch(item.id)"
            >{{ item.name }}</el-menu-item
          >
          <el-menu-item
            index="-1"
            style="font-size: 16px"
            @click="handelNavToSearch()"
            >分类搜索</el-menu-item
          >
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
import {routeNames} from "@/app/routers/routeNames";

export default {
  components: {},

  computed: {
    ...mapState({
      isLogin: (state) => state.app.isAuth,
      account: (state) => state.app.account,
      domains: (state) => state.app.resourceDomains,
    }),
  },

  data() {
    return {
      searchPhrase: "",
      routeNames:routeNames
    };
  },

  methods: {
    handleSearchCroResource() {
      //this.$router.push("/resource/search?key="+this.searchPhrase);
      // this.searchPhrase="";
    },

    handelNavToHome() {
      this.$router.push({ name: routeNames.CroSiteHomeIndex });
    },

    handelNavToSearch(domainId) {
      if (!domainId) domainId = 0;
      
      this.$router.push({
        path: "/croSite/resource/search?domainId=" + domainId,
      });
    },
  },
};
</script>

<style>
@import "../../../assets/css/site.css";

.el-menu-item:hover {
  background-color: #e1f3d8 !important;
}
.el-menu-item.is-active {
  background-color: transparent !important;
}
</style>

