<template>
  <div style="height:630px;">
    <el-row>

      <!-- left menu start -->
      <el-col :span="5">
        <el-menu 
          default-active="1"
          class="el-menu-vertical-demo height600"
          @open="handleOpen"
          @close="handleClose"
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
          <el-menu-item index="4">
            <i class="el-icon-menu"></i>
            <span slot="title">我的资源</span>
          </el-menu-item>
          <el-menu-item index="5" >
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

      <el-col :span="19">
          <UploadResource></UploadResource>
      </el-col>

    </el-row>
  </div>
</template>

<script>
import UploadResource from "@/app/views/croSite/account/components/Space/upload"

export default {
    components:{UploadResource},

    mounted(){
        console.log(this.$route.path);
    }
};
</script>
