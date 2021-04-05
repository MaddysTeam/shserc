<template>
  <div   style="padding-left:30px; padding-right:30px">
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
      <!-- recommand  resource area start -->
      <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14" >
        <div class="block_panel" > 
          <p class="panel_title">
            <span><img src="/assets/img/ico_gift.png" />资源推荐</span>
          </p>

          <div class="body">
            <div class="res_square" v-for="i in [1, 2, 3, 4, 5, 6]" :key="i">
              <a href="/Resource/View/1158" title="让我们成为芭蕾演员吧"
                ><img class="cover" src="http://tjcdn.shec.edu.cn/demo/15.jpg"
              /></a>
              <div class="hot"></div>
              <div>
                <a href="/Resource/View/1158" title="让我们成为芭蕾演员吧"
                  ><p>让我们成为芭蕾演员吧</p></a
                >
                <span>作者：上海高教电子音像出版社 </span>
              </div>
            </div>
          </div>
        </div>
            <div class="block_panel">
          <p class="panel_title">
            <span><img src="/assets/img/ico_gift.png" />资源排行</span>
          </p>

          <div class="body">
            <div class="res_square" v-for="i in [1, 2, 3, 4, 5, 6]" :key="i">
              <a href="/Resource/View/1158" title="让我们成为芭蕾演员吧"
                ><img class="cover" src="http://tjcdn.shec.edu.cn/demo/15.jpg"
              /></a>
              <div class="hot"></div>
              <div>
                <a href="/Resource/View/1158" title="让我们成为芭蕾演员吧"
                  ><p>让我们成为芭蕾演员吧</p></a
                >
                <span>作者：上海高教电子音像出版社 </span>
              </div>
            </div>
          </div>
        </div>
      </el-col>
      <!-- recommand  resource area end -->

      <el-col :xs="25" :sm="25" :md="9" :lg="9" :xl="9" style="margin-left:4%">
        <div class="block_panel">
          <p class="panel_title">
            <span><img src="/assets/img/ico_gift.png" />用户登录</span>
          </p>
        </div>
      </el-col> 

        <el-col :xs="25" :sm="25" :md="9" :lg="9" :xl="9" style="margin-left:4%">
        <div class="block_panel">
          <p class="panel_title">
            <span><img src="/assets/img/ico_gift.png" />活跃用户</span>
          </p>
        </div>
      </el-col> 

    </el-row>

    <el-row class="p_30"> 
      <el-select style="width:60%"></el-select>
    </el-row>
    
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
</style>