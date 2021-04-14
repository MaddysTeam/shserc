<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="1">个人信息</el-menu-item>
      <el-menu-item index="2" >我的下载</el-menu-item>
      <el-menu-item index="3"> 我的收藏</el-menu-item>
      <el-menu-item index="4"> 我的评论</el-menu-item>
    </el-menu>
    <div class="block_panel blue_edge" v-if="activeIndex==1">
        <p class="panel_title">
            <span><i class="el-icon-user-solid"></i> 个人信息</span>
          </p>
    </div>
      <div class="block_panel blue_edge" v-if="activeIndex==2">
        <p class="panel_title">
            <span><i class="el-icon-user-solid"></i> 我的下载</span>
          </p>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeIndex: "1",
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.activeIndex=key;
    },
  },
};
</script>

<style>
.site .el-menu{
  background: transparent;
}
.site .el-menu-item.is-active{
  color:#409EFF;
}
</style>