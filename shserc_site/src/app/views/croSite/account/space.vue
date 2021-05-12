<template>
  <div>
    <el-row>
      <!-- left menu start -->
      <el-col :span="5">
        <el-menu
          default-active="defaultActiveIndex"
          class="el-menu-vertical-demo height600"
          @select="handleSelect"
          active-text-color="green"
        >
          <el-menu-item index="1">
            <i class="el-icon-menu"></i>
            <span slot="title">通知公告</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title">资源上传</span>
          </el-menu-item>
          <el-submenu index="4">
            <template slot="title">  <i class="el-icon-menu"></i>我的资源</template>
            <el-menu-item index="41">我创建的资源</el-menu-item>
            <el-menu-item index="42">我点赞的资源</el-menu-item>
          </el-submenu>
          <el-menu-item index="5">
            <i class="el-icon-document"></i>
            <span slot="title">我的收藏</span>
          </el-menu-item>
          <el-menu-item index="6">
            <i class="el-icon-setting"></i>
            <span slot="title">我的下载</span>
          </el-menu-item>
          <el-menu-item index="7">
            <i class="el-icon-setting"></i>
            <span slot="title">我的评论</span>
          </el-menu-item>
          <el-menu-item index="8">
            <i class="el-icon-setting"></i>
            <span slot="title">返回首页</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <!-- left menu end -->

      <el-col :span="19" class="p_30_left_right">
        <UploadResource v-if="defaultActiveIndex == 3 || isUpload"></UploadResource>
        <BulletinList v-else-if="defaultActiveIndex == 1"></BulletinList>
        <AccountInfo v-else-if="defaultActiveIndex== 2"></AccountInfo>
        <MyResourceList v-else-if="defaultActiveIndex==41"></MyResourceList>
        <MyFavoriteList v-else-if="defaultActiveIndex==5"></MyFavoriteList>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { routeNames } from '@/app/routers/routeNames'
import UploadResource from "@/app/views/croSite/account/components/Space/upload";
import BulletinList from "@/app/views/croSite/account/components/Space/bulletin";
import AccountInfo from "@/app/views/croSite/account/components/Space/info";
import MyResourceList from "@/app/views/croSite/account/components/Space/myResource";
import MyFavoriteList from "@/app/views/croSite/account/components/Space/favoriteList";

export default {
  components: { UploadResource, BulletinList,AccountInfo,MyResourceList,MyFavoriteList },

  data() {
    return {
      defaultActiveIndex: 1,
      isUpload:false
    };
  },

  mounted() {
    console.log(this.$route);
    this.isUpload=this.$route.name==routeNames.CroMyUpload;
  },

  methods:{
    handleSelect(key, keyPath){
      this.isUpload=false;
       this.defaultActiveIndex=key;
    }
  }

};
</script>
