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
      <searchArea @handleSearch="handleSearchResource()"></searchArea>
    </div>
    <!-- advance search box end -->

    <el-row :gutter="10">
      <!-- recommand  resource area start -->
      <el-col :xs="14" :sm="14" :md="14" :lg="14" :xl="14">
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-star-on font20"></i> 资源推荐</span>
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
            <span><i class="el-icon-s-order"></i> 资源排行</span>
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

      <el-col :xs="25" :sm="25" :md="9" :lg="9" :xl="9" style="margin-left: 4%">
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-user-solid"></i> 用户登录</span>
          </p>
          <div>
            <el-form ref="form" :model="loginModel">
              <el-form-item prop="userName">
                <el-input
                  prefix-icon="el-icon-search"
                  v-model="loginModel.name"
                  placeholder="请输入用户名、邮箱或者手机号"
                ></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input
                  prefix-icon="el-icon-search"
                  v-model="loginModel.password"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <div class="flex_space_between">
                  <el-button
                    size="medium"
                    class="btn_login el-button el-button--primary"
                    >登录</el-button
                  >
                  <div>
                    <el-link type="info" :underline="false">注册/</el-link>
                    <el-link type="info" :underline="false">忘记密码</el-link>
                  </div>
                </div>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>

      <el-col :xs="25" :sm="25" :md="9" :lg="9" :xl="9" style="margin-left: 4%">
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-headset"></i> 活跃用户</span>
          </p>
        </div>
      </el-col>
    </el-row>

    <el-row class="p_30">
      <el-select style="width: 60%" >
        <el-option  v-for="i in [1,2,3,4,5]" value-key="i" :key="i"></el-option>
      </el-select>
    </el-row>
  </div>
</template>

<script>
import searchArea from "@/app/views/site/components/SearchArea";
import { loginModel } from "@/app/models/account";
import Index from "../../admin/layout/index.vue";
export default {
  components: { searchArea },
  data() {
    return {
      loginModel: loginModel,
    };
  },
  methods: {
    handleSearchResource() {
      this.$router.push("/resource/search")
    },
  },
};
</script>

<style >
.btn_login {
  /* color: #fff;
    background-color: #f39800; */
  text-align: center;
  border: none;
  height: 40px;
}
</style>