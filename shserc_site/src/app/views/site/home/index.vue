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
      <el-col :span="15">
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-star-on font20"></i> 资源推荐</span>
          </p>

          <div class="body">

            <ResourceBlockList :coverWidth="184" :coverHeight="120"></ResourceBlockList> 

          </div>
        </div>
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-s-order"></i> 资源排行</span>
          </p>

          <div class="body">

             <ResourceList></ResourceList>

          </div>
        </div>
      </el-col>
      <!-- recommand  resource area end -->

      <!-- login area start -->
      <el-col :span="8" style="margin-left: 4%">
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
                    <router-link
                      to="/account/register"
                      type="danger"
                      class="font12"
                      :underline="false"
                      ><el-tag>注册</el-tag></router-link
                    >
                    <router-link
                      to="/account/forgetPassword"
                      class="font12"
                      :underline="false"
                      ><el-tag type="danger">忘记密码</el-tag></router-link
                    >
                  </div>
                </div>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </el-col>
      <!-- login area end -->

      <!-- activity user list start -->
      <el-col :span="8" style="margin-left: 4%">
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-headset"></i> 活跃用户</span>
          </p>
        </div>
      </el-col>
    </el-row>
    <!-- activity user list end -->

    <el-row class="p_30">
      <el-select style="width: 60%">
        <el-option
          v-for="i in [1, 2, 3, 4, 5]"
          value-key="i"
          :key="i"
        ></el-option>
      </el-select>
    </el-row>
  </div>
</template>

<script>
import { selectParam, orderPhrasesModel } from "@/app/models/resource";
import { list } from "@/app/api/resource";
import searchArea from "@/app/views/site/resource/components/SearchArea";
import { loginModel } from "@/app/models/account";
import { deepCopy } from "@/app/utils/objectHelper";
import { DESC } from "@/app/static/type";
import ResourceList from "@/app/views/site/resource/components/List";
import ResourceBlockList from "@/app/views/site/resource/components/BlockList";

export default {
  components: { searchArea ,ResourceList,ResourceBlockList},
  data() {
    return {
      loginModel: loginModel,
      selectParam: deepCopy(selectParam),
      topFavoriteResources: [],
      topVisitResources: [],
      topCommentResources: [],
      topDownloadResources: [],
      value2: 5,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
    };
  },

  mounted() {
    this.loadTopFavoriteResources();
  },

  methods: {
    loadTopFavoriteResources() {
      selectParam.size = 6; // get top 12 favorite resources
      selectParam.orderPhrases[orderPhrasesModel.favorite] = DESC;
      list(selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topFavoriteResources = data.listData ? data.listData : [];
        }
      });
    },

    loadTopCommentResource() {},

    loadTopVisitResource() {},

    loadTopDownloadResource() {},

    loadActivityUsers() {},

    handleSearchResource() {
      this.$router.push("/resource/search");
    },

    handleLogin() {},
  },
};
</script>

<style >
.btn_login {
  text-align: center;
  border: none;
  height: 40px;
}
</style>