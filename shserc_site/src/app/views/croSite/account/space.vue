<template>
  <div>
    <el-row>
      <!-- left menu start -->
      <el-col :span="5">
        <el-menu
          router
          default-active="defaultActiveIndex"
          class="el-menu-vertical-demo height600"
          @select="handleSelect"
          active-text-color="green"
        >
          <el-menu-item index="/croSite/space/bulletin">
            <i class="el-icon-menu"></i>
            <span slot="title">通知公告</span>
          </el-menu-item>
          <el-menu-item index="/croSite/space/info">
            <i class="el-icon-menu"></i>
            <span slot="title">个人信息</span>
          </el-menu-item>
          <el-menu-item index="/croSite/space/upload">
            <i class="el-icon-menu"></i>
            <span slot="title">资源上传</span>
          </el-menu-item>
          <el-submenu index="4">
            <template slot="title">  <i class="el-icon-menu"></i>我的资源</template>
            <el-menu-item index="/croSite/space/resource">我创建的资源</el-menu-item>
            <!-- <el-menu-item index="42">我点赞的资源</el-menu-item> -->
          </el-submenu>
          <el-menu-item index="/croSite/space/favorite">
            <i class="el-icon-document"></i>
            <span slot="title">我的收藏</span>
          </el-menu-item>
          <el-menu-item index="/croSite/space/download">
            <i class="el-icon-setting"></i>
            <span slot="title">我的下载</span>
          </el-menu-item>
          <el-menu-item index="7">
            <i class="el-icon-setting"></i>
            <span slot="title">我的评论</span>
          </el-menu-item>
          <el-menu-item index="/croSite/home/index">
            <i class="el-icon-setting"></i>
            <span slot="title">返回首页</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <!-- left menu end -->

      <el-col :span="19" class="p_30">

      <transition name="fade" mode="out-in" appear>
        <router-view class=""></router-view>
      </transition>
        <!-- <UploadResource v-if="defaultActiveIndex == 3 || isUpload"></UploadResource>
        <BulletinList v-else-if="defaultActiveIndex == 1"></BulletinList>
        <AccountInfo v-else-if="defaultActiveIndex== 2"></AccountInfo>
        <MyResourceList v-else-if="defaultActiveIndex==41"></MyResourceList>
        <MyFavoriteList v-else-if="defaultActiveIndex==5" :activeIndexChange="handleSelect(3)"></MyFavoriteList>
        <MyDownloadList v-else-if="defaultActiveIndex==6"></MyDownloadList> -->
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { routeNames } from '@/app/routers/routeNames'

export default {

  methods:{
    handleSelect(key, keyPath){
    }
  }

};
</script>
