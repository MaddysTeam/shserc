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

            <ResourceBlockList :coverWidth="184" :coverHeight="120" :source="topFavoriteResources"></ResourceBlockList> 

          </div>
        </div>
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-s-order"></i> 资源排行</span>
          </p>

          <div class="body">

             <ResourceList :source="topVisitResources"></ResourceList>

          </div>
        </div>
      </el-col>
      <!-- recommand  resource area end -->

     
      <el-col style="margin-left: 20px;width:411px">

         <!-- login area start -->
        <div class="block_panel" >
          <p class="panel_title flex_space_between">
            <span v-if="isLogin"><i class="el-icon-user-solid"></i> 用户登录</span>
            <span v-if="!isLogin"><i class="el-icon-user-solid"></i> 欢迎您，{{ account.userName}}</span>
             <el-avatar :size="50" :src="account.photoPath"></el-avatar>
          </p>
          <div>
            <!-- login form start-->
            <el-form ref="form" :model="loginModel" v-if="isLogin">
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
            <!-- login form stop -->

            <!-- login info start-->
            <div v-if="!isLogin" class="text_align_left font14">
                 <div><i class="el-icon-time"></i> 您上次的登录时间：2020-12-1  </div>
                 <p></p>
                 <div><i class="el-icon-s-custom"></i> <router-link to="/account/space" class="link">进入我的中心</router-link></div>
                  <p></p>
                 <div><i class="el-icon-info"></i> 退出我的登录</div>

                  <div class="m_10_top">
                    <el-progress type="circle" :percentage="100" :width=60></el-progress>
                    <el-progress type="circle" :percentage="100"  :width=60></el-progress>
                    <el-progress type="circle" :percentage="100" :width=60></el-progress>
                    <el-progress type="circle" :percentage="100" :width=60></el-progress>
                  </div>
               
            </div>
            <!-- login info stop-->
          </div>
        </div>
         <!-- login area end -->

          <div class="block_panel">
          <!-- <p class="panel_title">
            <span><i class="el-icon-headset"></i> 众筹资源</span>
          </p> -->
         
        </div>

           <!-- activity user list start -->
         <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-headset"></i> 活跃用户</span>
          </p>
          <ActivityUserList listType="blockCustomList" :source="[1,2,3,45,1,2,3,45]">
             <template slot="item">
                   <el-avatar :src="account.photoPath" :size="40"></el-avatar>
                   <div>xx</div>
             </template>
          </ActivityUserList>
        </div>
          <!-- activity user list end -->

            <!-- activity user list start -->
         <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-chat-round"></i> 消息公告</span>
          </p>
           <div>
        <ul class="compact_list">
              <li>
                <a href="/Resource/View/1333"
                  ><span class="square">1</span> 公告1</a
                >
              </li>
              <li>
                <a href="/Resource/View/1334"
                  ><span class="square">2</span>
                 公告2</a
                >
              </li>
              <li>
                <a href="/Resource/View/1335"
                  ><span class="square">3</span> 公告3</a
                >
              </li>
              <li>
                <a href="/Resource/View/1332"><span>4</span> 公告4</a>
              </li>

            </ul>
    </div>
        </div>
          <!-- activity user list end -->

      </el-col>
    </el-row>
   

   <el-row class="p_30">
      <el-select style="width: 60%" v-model="friendSites">
        <el-option
          v-for="i in [1, 2, 3, 4, 5]"
          value-key="i"
          :key="i"
          :value="i"
        ></el-option>
      </el-select>
    </el-row> 
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { selectParam, orderPhrasesModel } from "@/app/models/resource";
import { list } from "@/app/api/resource";
import searchArea from "@/app/views/site/resource/components/SearchArea";
import { loginModel } from "@/app/models/account";
import { deepCopy } from "@/app/utils/objectHelper";
import { DESC } from "@/app/static/type";
import ResourceList from "@/app/views/site/resource/components/List";
import ResourceBlockList from "@/app/views/site/resource/components/BlockList";
import ActivityUserList from "@/components/List/index"

export default {
  components: { searchArea ,ResourceList,ResourceBlockList,ActivityUserList},
  data() {
    return {
      loginModel: loginModel,
      selectParam: selectParam,
      topFavoriteResources: [],
      topVisitResources: [],
      topCommentResources: [],
      topDownloadResources: [],
      value2: 5,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      friendSites:[]
    };
  },

   computed:{
    ...mapState({
        isLogin:(state)=>state.app.isAuth,
        account: (state)=>state.app.account
    })
  },

  mounted() {
    this.loadTopFavoriteResources();
    this.loadTopVisitResource();
  },

  methods: {
    loadTopFavoriteResources() {
      let param=deepCopy(selectParam);
      param.size = 6; // get top 12 favorite resources
      param.orderPhrases[orderPhrasesModel.favoriteCount] = DESC;
      list(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topFavoriteResources = data.listData ? data.listData : [];
        }
      });
    },

    loadTopCommentResource() {},

    loadTopVisitResource() {
      let param=deepCopy(selectParam);
      param.size = 4; // get top 12 favorite resources
      param.orderPhrases[orderPhrasesModel.viewCount] = DESC;
      list(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topVisitResources = data.listData ? data.listData : [];
        }
      });
    },

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