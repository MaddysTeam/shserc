<template>
  <div>


    <el-row :gutter="10">

      <!-- hot  cro resource area start -->
      <el-col :span="16" >

            <!-- bulletin list start -->
        <div class="block_panel green_edge">
          <p class="green_panel_title flex_space_between">
            <span><i class="el-icon-chat-round"></i> 消息公告</span>
            <span>
               <router-link class="link font14" :to="{name:routeNames.CroSiteBulletinList}"><i  style="font-size:32px;" class="el-icon-more green"  title="更多公告"></i> </router-link>
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


        <div class="block_panel green_edge">
          <p class="green_panel_title flex_space_between">
            <span ><i class="el-icon-star-on font20"></i> 热门资源   &nbsp; &nbsp;  
             <router-link :to="{name:routeNames.CroSiteRsourceMore,query:{createType:createType.origin, moreType:moreType.hot}}"><el-tag type="success" class="cursor_pointer" >原创</el-tag></router-link>
             <router-link :to="{name:routeNames.CroSiteRsourceMore,query:{createType:createType.recommend,moreType:moreType.hot}}"><el-tag type="success"  class="cursor_pointer"> 推荐</el-tag></router-link>
            </span>
             <span>
               <router-link class="link font14" :to="{name:routeNames.CroSiteRsourceMore,query:{moreType:moreType.hot}}"><i  style="font-size:32px;" class="el-icon-more green"></i> </router-link>
            </span>
          </p>

          <div class="body">
            <ResourceBlockHotList
              :coverHeight="150"
              :source="topHotResources"
            ></ResourceBlockHotList>
          </div>
        </div>
        <div class="block_panel green_edge">
          <p class="green_panel_title flex_space_between">
            <span><i class="el-icon-s-order"></i> 最新资源 &nbsp; &nbsp;  
            <router-link :to="{name:routeNames.CroSiteRsourceMore,query:{createType:createType.origin,moreType: moreType.latest}}"><el-tag type="success" class="cursor_pointer" >原创</el-tag></router-link>
             <router-link :to="{name:routeNames.CroSiteRsourceMore,query:{createType:createType.recommend,moreType:moreType.latest}}"><el-tag type="success"  class="cursor_pointer"> 推荐</el-tag></router-link>
            </span>
            <span>
               <router-link class="link font14" :to="{name:routeNames.CroSiteRsourceMore,query:{moreType:moreType.latest}}"><i  style="font-size:32px;" class="el-icon-more green" title="更多资源"></i> </router-link>
            </span>
          </p>

          <div class="body">
            <ResourceBlockLatestList 
             :coverHeight="150"
            :source="topLatestSource">
            </ResourceBlockLatestList>
          </div>
        </div>
      </el-col>
      <!-- hot  cro resource area end -->

      <el-col :span="7" class="right-side"> 
        <!-- login area start -->
        <div class="block_panel">
          <p class="green_panel_title flex_space_between">
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
                  prefix-icon="el-icon-search"
                  v-model="loginModel.password"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <div class="flex_space_between">
                  <el-button
                    size="medium"
                    type="success"
                    style="background:#578d38"
                    class="btn-login"
                    ><i class="el-icon-info"></i>  登 录</el-button
                  >
                 
                </div>
              </el-form-item>
            </el-form>
            <!-- login form stop -->

            <!-- login info start-->
            <div v-if="isLogin" class="text_align_left font14">
              <div>
                <i class="el-icon-s-custom"></i>
                <router-link to="/croSite/space" class="link"
                  >进入我的中心</router-link
                >
              </div>
              <p></p>
              <div>  <el-link type="danger" class="link" @click="handleLogout()"><i class="el-icon-info"></i> 退出我的登录</el-link></div>

            </div>
            <!-- login info end-->

          </div>
        </div>
        <!-- login area end -->

        <router-link to="/croSite/space/declare" v-if="isLogin">
        <div class="block_panel cursor_pointer" style="background:#578d38; color:white" >   
           <span class="font20 "><i class="el-icon-upload font30" style="font-size:25px;"></i>   我要上传</span>
        </div>
         </router-link>

        <!-- activity user list start -->
        <div class="block_panel">
          <p class="green_panel_title flex_space_between">
            <span><i class="el-icon-headset"></i> 活跃用户</span>
              <span>
               <router-link class="link font14" :to="{name:routeNames.CroSiteActivityUserList}"><i  style="font-size:32px;" class="el-icon-more green"></i> </router-link>
            </span>
          </p>
          <ActivityUserList
            listType="blockCustomList"
            :source="topActivityUsers"
          >
            <template slot="item" slot-scope="user">
              <el-avatar :src="user.item.photoPath" :size="40"  @error="handleImageError" >
                  <img :src="CDN.DEFAULT_HEADER_COVER" />
              </el-avatar>
              <div>{{user.item.userName}}</div>
            </template>
          </ActivityUserList>
        </div>
        <!-- activity user list end -->

      </el-col>
    </el-row>

  </div>
</template>

<script>
import {routeNames} from "@/app/routers/routeNames";
import {CDN} from "@/static/CDN"
import { mapState } from "vuex";
import * as types from "@/app/static/type";
import { selectParam, orderPhrasesModel,createType ,moreType} from "@/app/models/croResource";
import { userOrderPhrasesModel } from "@/app/models/user";
import { accountModel } from "@/app/models/account";
import { rseoureBulletinList } from "@/app/api/bulletin";
import { list } from "@/app/api/croResource";
import { login, logout } from "@/app/api/account";
import { list as userList } from "@/app/api/user";
import { operationCount } from "@/app/api/croMy";
import { loginModel } from "@/app/models/account";
import { deepCopy } from "@/app/utils/objectHelper";
import { DESC } from "@/app/static/type";
import ResourceList from "@/app/views/croSite/resource/components/List";
import ResourceBlockHotList from "@/app/views/croSite/resource/components/BlockList/hot";
import ResourceBlockLatestList from "@/app/views/croSite/resource/components/BlockList/latest";
import ActivityUserList from "@/components/List/index";
import BulltinList from "@/components/List/index";


export default {
  components: {
    ResourceList,
    //ResourceBlockList,
    ActivityUserList,
    BulltinList,
    ResourceBlockHotList,
    ResourceBlockLatestList
  },
  data() {
    return {
      routeNames:routeNames,
      loginModel: loginModel,
      selectParam: selectParam,
      topHotResources: [],
      topLatestSource: [],
      topActivityUsers: [],
      createType:createType,
      moreType:moreType,

      topBulltins: [],
      value2: 5,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      friendSites: [],
      CDN:CDN
    };
  },

  computed: {
    ...mapState({
      isLogin: (state) => state.app.isAuth,
      account: (state) => (state.app.isAuth ? state.app.account : accountModel),
    }),
  },

  mounted() {
    this.loadTopHotResources();
    this.loadTopLatestResource();
    this.loadTopActivityUsers();
    this.loadTopBulltins();
  },

  methods: {
    
  /**
  * @description: load top hot resources
  * @param {number} createType (原创或推荐)
  * @return void
  */
    loadTopHotResources(createType) {
      let param = deepCopy(selectParam);
      param.size = 6; // get top 12 favorite resources
      param.orderPhrases[orderPhrasesModel.viewCount] = DESC;
      list(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topHotResources = data.listData ? data.listData : [];
        }
      });
    },

  /**
  * @description: load top latest resources
  * @param {number} createType (原创或推荐)
  * @return void
  */
    loadTopLatestResource(createType) {
      let param = deepCopy(selectParam);
      param.size = 4; // get top 12 favorite resources
      param.orderPhrases[orderPhrasesModel.lateset] = DESC;
      list(param).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.topLatestSource = data.listData ? data.listData : [];
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
  },
};
</script>

<style scoped>


.right-side{
  margin-left: 20px; 
  width: 381px; 
}
</style>