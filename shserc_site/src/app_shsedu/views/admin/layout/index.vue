<template>
  <el-container>
    <el-header>
      <div><span></span></div>
    </el-header>

    <el-container>
      <!-- left aside start-->
      <el-aside style="width: 230px">
        <DynamicMenu :dataSource="menus"></DynamicMenu>
      </el-aside>
      <!-- left aside end-->

      <!--right side start-->
      <el-main>
        <router-view></router-view>
      </el-main>
      <!--right side end-->
    </el-container>
  </el-container>
</template>

<script>
import DynamicMenu from "@/components/DynamicMenus/index";
import { CDN } from "@/static/CDN";
import { logout } from "@/app_shsedu/api/account";

export default {
  components: {
    DynamicMenu,
  },


  methods: {
    handleLogout() {},

    handleShowAccountInfo() {
      this.$router.push({ path: "/admin/account/info", replace: true });
    },

    handleChangePassword() {
      this.$router.push({ path: "/admin/account/password", replace: true });
    },

    handleImageError() {
      let img = event.srcElement;
      img.src = CDN.DEFAULT_HEADER_COVER;
      img.onerror = null; //to fix bugs for image auto flash
    },
  },
};
</script>

<style>
@import "../../../assets/css/admin.css";
</style>