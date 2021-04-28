<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="1">个人信息</el-menu-item>
      <el-menu-item index="2">我的下载</el-menu-item>
      <el-menu-item index="3"> 我的收藏</el-menu-item>
      <el-menu-item index="4"> 我的评论</el-menu-item>
    </el-menu>

    <div class="block_panel blue_edge" v-if="activeIndex == 1">
      <p class="panel_title">
        <span><i class="el-icon-user-solid"></i> 个人信息</span>
      </p>
      <div class="flex_space_between">
        <span>上次登录时间：<el-tag>2021/4/20</el-tag> </span>
        <div>
        <!-- <span class="m_30_right">登录次数：<el-tag type="danger">3650</el-tag></span>
        <span class="m_30_right">收藏资源：<el-tag type="danger">821</el-tag></span>
        <span class="m_30_right">下载次数：<el-tag type="danger">23</el-tag></span>
        <span class="m_30_right">评论次数：<el-tag type="danger">7</el-tag></span> -->
        <el-progress class="m_30_right"
                  type="circle"
                  :percentage="100"
                  :width="100"
                ></el-progress>
                <el-progress class="m_30_right"
                  type="circle"
                  :percentage="100"
                  :width="100"
                ></el-progress>
                <el-progress class="m_30_right"
                  type="circle"
                  :percentage="100"
                  :width="100"
                ></el-progress>
                <el-progress class="m_30_right"
                  type="circle"
                  :percentage="100"
                  :width="100"
                ></el-progress>
        </div>
      </div>
      <p class="m_30_top"></p>
      <Info></Info>
    </div>

    <div class="block_panel blue_edge" v-if="activeIndex == 2">
      <p class="panel_title">
        <span><i class="el-icon-user-solid"></i> 我的下载</span>
      </p>
      <DownloadList></DownloadList>
    </div>

    <div class="block_panel blue_edge" v-if="activeIndex == 3">
      <p class="panel_title">
        <span><i class="el-icon-user-solid"></i> 我的收藏</span>
      </p>
      <FavoriteList></FavoriteList>
    </div>
    
  </div>
</template>
<script>
import Info from "@/app/views/site/account/components/Space/info";
import DownloadList from   "@/app/views/site/account/components/Space/downloadList";
import FavoriteList  from   "@/app/views/site/account/components/Space/favoriteList";
import { mapState } from "vuex";

export default {
  components: { Info,DownloadList ,FavoriteList},

  data() {
    return {
      activeIndex: "1",
    };
  },

  mounted(){
     if(!this.isLogin){
        this.$router.push("/account/login");
     }
  },

   computed:{
    ...mapState({
        isLogin:(state)=>state.app.isAuth,
        account: (state)=>state.app.account
    })
  },

  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.activeIndex = key;
    },
  },
};
</script>

<style>
.site .el-menu {
  background: transparent;
}
.site .el-menu-item.is-active {
  color: #409eff;
}
</style>