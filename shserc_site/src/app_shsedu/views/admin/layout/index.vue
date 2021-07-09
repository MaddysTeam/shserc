<template>
  <el-container>
    <el-header style="height:70px">
      <div>
        <span>特教之窗后台管理</span>
      </div>
      <el-dropdown :hide-timeout="300">
        <!-- <el-button type="info" circle><el-image class="img_header" :src="account.photoPath"></el-image>
        </el-button> -->
        <el-link class="img-header " type="none">JimmyPoor</el-link>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="handleShowAccountInfo"
            ><i class="el-icon-user-solid"></i>用户信息</el-dropdown-item
          >
          <el-dropdown-item @click.native="handleChangePassword"
            ><i class="el-icon-lock"></i>修改密码</el-dropdown-item
          >
          <el-dropdown-item @click.native="handleLogout"
            ><i class="el-icon-info"></i>注销
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>

    <el-container>
      <!-- left aside start-->
      <el-aside style="width: 230px;height:700px;background:#333744">
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

  data() {
    return {
      account: this.$store.state.app.account,
      menus: this.$store.state.app.menus,
    };
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