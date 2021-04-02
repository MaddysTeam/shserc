<template>
  <div>
    <div class="block_panel blue_edge">
      <!-- carousel start -->
      <el-carousel indicator-position="outside">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3>{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <!-- carousel end -->

    <!-- advance search box start -->
    <div class="block_panel">
      <p class="panel_title">
        <span><img src="/assets/img/ico_gift.png" />资源检索</span>
      </p>
      <div class="search">
        <div class="search bar1">
          <form>
            <input type="text" placeholder="请输入您要搜索的内容..." />
            <button type="submit">
              <i
                class="el-icon-search"
                style="font-size: 26px; color: #eee"
              ></i>
            </button>
          </form>
        </div>
      </div>

      <div class="text-center more" style="display: none">
        <a href="javascript:void(0)">
          <strong><i class="fa fa-arrow-down"></i> 更多条件</strong></a
        >
      </div>
      <div class="text-center less" style="display: none">
        <a href="javascript:void(0)">
          <strong><i class="fa fa-arrow-up"></i> 收起</strong></a
        >
      </div>
    </div>
    <!-- advance search box end -->

    <el-row :gutter="10">
      <el-col :xs="25" :sm="25" :md="16" :lg="16" :xl="16">
        <div class="block_panel">
          <p class="panel_title">
            <span><img src="/assets/img/ico_gift.png" />资源推荐</span>
          </p>
        </div>
      </el-col>
     <el-col :xs="25" :sm="25" :md="8" :lg="8" :xl="7">
             <div class="block_panel">
          <p class="panel_title">
            <span><img src="/assets/img/ico_gift.png" />用户登录</span>
          </p>
        </div>
     </el-col>
    </el-row>

    <div class="block_panel">
      <p class="panel_title">
        <span><img src="/assets/img/ico_gift.png" />资源排行</span>
      </p>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
</style>