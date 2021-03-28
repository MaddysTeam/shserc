<template>
  <el-container class="container">
    <!-- top -->
    <el-header>
      <div>
        <span>特教资源库后台管理</span>
      </div>
      <el-dropdown :hide-timeout="300">
        <el-button type="info" circle
          ><el-image class="img_header" :src="account.photoPath"></el-image>
        </el-button>
        <!-- <el-link class="img-header">JimmyPoor</el-link> -->
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
      <!-- left aside -->
      <el-aside style="width: 230px">
        <DynamicMenu :dataSource="menus"></DynamicMenu>
      </el-aside>
      <!--right side-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import DynamicMenu from "@/components/DynamicMenus/index";
import * as types from "@/app/static/type";
import { logout } from "@/app/api/account";
import { CDN } from "@/static/CDN";

export default {
  name: "layout",

  components: {
    DynamicMenu,
  },

  data() {
    return {
      account:  this.$store.state.app.account,
      menus: this.$store.state.app.menus, 
    };
  },

  methods: {
    handleLogout() {
      logout().then((res) => {
        this.$store.commit(types.APP + "/" + types.LOGOUT);
        this.$router.push({ path: "/admin/logout", replace: true });
      });
    },

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

<style scoped>
.container {
  height: 100%;
  font-family: Microsoft YaHei;
}

.el-header {
  background: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 20px;
  color: #fff;
}
.el-header > div {
  display: flex;
  align-items: center;
}
.img-header {
  color: #fff;
  font-size: 12px;
}

.el-button.is-circle {
  padding: 1px;
}
.img_header {
  width: 38px;
  height: 38px;
  border-radius: 50%;
}
.el-aside {
  background: #333744;
}
.el-main {
  background: #eaedf1;
  width: 60%;
}
</style>