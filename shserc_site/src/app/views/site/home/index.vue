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
      <searchArea @handleSearch="handleSearchResource" :isForceSearch="false"></searchArea>
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
            <ResourceBlockList
              :coverWidth="184"
              :coverHeight="120"
              :source="bestFavoriteResources"
            ></ResourceBlockList>
          </div>
        </div>
        <div class="block_panel">
          <p class="panel_title flex_space_between">
            <span><i class="el-icon-s-order"></i> 资源排行</span>
            <section class="font15">
              <el-radio-group v-model="orderKey" >
                  <el-radio-button  label="hot" @click.native="handleChangeSearchOrder($event)"><i :class="arrowDirection" v-show="orderKey=='hot'"></i> 点击量 </el-radio-button>
                  <el-radio-button label="commentCount" @click.native="handleChangeSearchOrder($event)"><i :class="arrowDirection"  v-show="orderKey=='commentCount'"></i>评论次数</el-radio-button>
                  <el-radio-button label="downloadCount" @click.native="handleChangeSearchOrder($event)"><i :class="arrowDirection"  v-show="orderKey=='downloadCount'"></i>下载次数</el-radio-button>
                </el-radio-group>
             </section>
          </p>

          <div class="body">
            <ResourceList :source="topSource"></ResourceList>
          </div>
        </div>
      </el-col>
      <!-- recommand  resource area end -->

      <el-col class="right-side"> 
        <!-- login area start -->
        <div class="block_panel">
          <p class="panel_title flex_space_between">
            <span v-if="!isLogin"
              ><i class="el-icon-user-solid"></i> 用户登录</span
            >
            <span v-if="isLogin"
              ><i class="el-icon-user-solid"></i> 欢迎您，{{
                account.userName
              }}</span
            >
            <el-avatar v-if="isLogin" :size="50" :src="account.photoPath"></el-avatar>
          </p>
          <div>
            <!-- login form start-->
            <el-form ref="form" :model="loginModel" v-if="!isLogin">
              <el-form-item prop="userName">
                <el-input
                  prefix-icon="el-icon-search"
                  v-model="loginModel.name"
                  placeholder="请输入用户名、邮箱或者手机号"
                ></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input
                  type="password"
                  prefix-icon="el-icon-search"
                  v-model="loginModel.password"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <div class="flex_space_between">
                  <el-button
                    size="medium"
                    class="btn-login el-button el-button--primary"
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
            <div v-if="isLogin" class="text_align_left font14">
              <div>
                <i class="el-icon-time"></i> 您上次的登录时间：{{loginModel.lastLoginTime}}
              </div>
              <p></p>
              <div>
                <i class="el-icon-s-custom"></i>
                <router-link to="/account/space" class="link"
                  >进入我的中心</router-link
                >
              </div>
              <p></p>
              <div>
                <i class="el-icon-s-tools"></i>
                <router-link to="/admin" class="link orange"
                  >进入后台管理</router-link
                >
              </div>
              <p></p>
              <div>  <el-link type="danger" class="link" @click="handleLogout()"><i class="el-icon-info"></i> 退出我的登录</el-link></div>

            </div>
            <!-- login info end-->

          </div>
        </div>
        <!-- login area end -->

        <div class="block_panel">
          <p class="panel_title">
             <span><i class="el-icon-headset"></i> 众筹资源</span> 
          </p> 
           <router-link :to="{name:routeNames.CroSiteHomeIndex}"><img src="../../../assets/images/i_zy.jpg"  style="width:100%;height:100%"/></router-link>
        </div>

        <!-- activity user list start -->
        <div class="block_panel">
          <p class="panel_title flex_space_between">
            <span><i class="el-icon-headset"></i> 活跃用户</span>
            <span>
              <router-link class="link font14" to=""><i class="el-icon-d-arrow-right"></i> 浏览更多</router-link>
            </span>
          </p>
          <ActivityUserList
            listType="blockCustomList"
            :source="topActivityUsers"
          >
            <template slot="item" slot-scope="user">
              <el-avatar :src="user.item.photoPath" :size="40" @error="handleImageError">
                 <img :src="CDN.DEFAULT_HEADER_COVER" />
              </el-avatar>
              <div>{{user.item.userName}}</div>
            </template>
          </ActivityUserList>
        </div>
        <!-- activity user list end -->

        <!-- bulletin list start -->
        <div class="block_panel">
          <p class="panel_title flex_space_between">
            <span><i class="el-icon-chat-round"></i> 消息公告</span>
            <span>
              <router-link class="link font14" to=""><i class="el-icon-d-arrow-right"></i> 浏览更多</router-link>
            </span>
          </p>
          <div>

            <BulltinList listType="ulList" :source="topBulltins" containerClass="compact_list">
              <template slot="item" slot-scope="bulltin" >
                <li>
                  <router-link to="/Resource/View/1332"
                    ><span class="square">1</span> {{ bulltin.item.title }}</router-link
                  >
                </li>
              </template>
            </BulltinList>

          </div>
        </div>
         <!-- bulletin list end -->

      </el-col>
    </el-row>

    <el-row class="p_30">
      <el-select  class="widthPercent60" v-model="friendSites">
        <el-option
          v-for="site in friendSites"
          :value-key="site.name"
          :key="site.id"
          :value="site.url"
        ></el-option>
      </el-select>
    </el-row>
  </div>
</template>

<script>
import {CDN} from "@/static/CDN"
import {routeNames} from "@/app/routers/routeNames";
import { mapState } from "vuex";
import * as types from "@/app/static/type";
import { selectParam, orderPhrasesModel } from "@/app/models/resource";
import { userOrderPhrasesModel } from "@/app/models/user";
import { accountModel } from "@/app/models/account";
import { rseoureBulletinList } from "@/app/api/bulletin";
import { list } from "@/app/api/resource";
import { login, logout } from "@/app/api/account";
import { list as userList } from "@/app/api/user";
import { operationCount } from "@/app/api/my";
import searchArea from "@/app/views/site/resource/components/SearchArea";
import { loginModel } from "@/app/models/account";
import { deepCopy } from "@/app/utils/objectHelper";
import { DESC } from "@/app/static/type";
import ResourceList from "@/app/views/site/resource/components/List";
import ResourceBlockList from "@/app/views/site/resource/components/BlockList";
import ActivityUserList from "@/components/List/index";
import BulltinList from "@/components/List/index";

export default {
  components: {
    searchArea,
    ResourceList,
    ResourceBlockList,
    ActivityUserList,
    BulltinList,
  },
  data() {
    return {
      loginModel: loginModel,
      selectParam: selectParam,
      bestFavoriteResources: [],
      topSource: [],
      topActivityUsers: [],

      topBulltins: [],
      value2: 5,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      friendSites: [],
      routeNames:routeNames,
      CDN:CDN,

      orderKey:"hot",
      arrowDirection:"el-icon-top"
    };
  },

  computed: {
    ...mapState({
      isLogin: (state) => state.app.isAuth,
      account: (state) => (state.app.isAuth ? state.app.account : accountModel),
    }),
  },

  mounted() {
    this.loadBestFavoriteResources();
    this.loadTopRankResource(orderPhrasesModel.viewCount);
    this.loadTopActivityUsers();
    this.loadTopBulltins();
  },

  methods: {
    loadBestFavoriteResources() {
      let param = deepCopy(selectParam);
      param.size = 6; // get top 12 favorite resources
      param.orderPhrases[orderPhrasesModel.favoriteCount] = DESC;
      list(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.bestFavoriteResources = data.listData ? data.listData : [];
        }
      });
    },

    loadTopRankResource(sourceType) {
      let param = deepCopy(selectParam);
      param.size = 4; // get top 12 favorite resources
      param.orderPhrases[sourceType] = DESC;
      list(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topSource = data.listData ? data.listData : [];
        }
      });
    },

    loadTopActivityUsers() {
      let param = deepCopy(selectParam);
      param.size = 16; // get top 16 activity users
      param.orderPhrases[userOrderPhrasesModel.activity] = DESC;

      userList(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topActivityUsers = data.listData ? data.listData : [];
        }
      });
    },

    loadTopBulltins() {
      let param = deepCopy(selectParam);
      rseoureBulletinList(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topBulltins = data.listData ? data.listData : [];
        }
      });
    },

    handleSearchResource(param) {
      this.$router.push("/resource/search?key="+param.searchPhrase);
    },

    handleLogin() {
      login({
        userName: _this.form.name,
        password: encrypt(_this.form.password), // encrypt password and  de-encrypt in server side
      }).then((res) => {
        Notification.success({ message: "登录成功" });
        // save login info to store，save dynamic route info to store
        _this.$store.commit(
          types.APP + "/" + types.LOGIN,
          JSON.parse(res.data)
        );

        // get opeation count record
        this.account = this.$store.state.app.account;
        operationCount(account.id);
      });
    },

    handleLogout() {
      logout().then((res) => {
        this.$store.commit(types.APP + "/" + types.LOGOUT);
        this.$router.push({ path: "/admin/logout", replace: true });
      });
    },

    handleImageError(){
      return true;
    },

  handleChangeSearchOrder(e,val){
      if(e.target.tagName==="INPUT"){
        this.arrowDirection= this.arrowDirection=="el-icon-top"? "el-icon-bottom":"el-icon-top";
      }
    },

  },
};
</script>

<style scoped>
.btn-login {
  text-align: center;
  border: none;
  height: 40px;
}

.right-side{
  margin-left: 20px; 
  width: 411px;
}
</style>