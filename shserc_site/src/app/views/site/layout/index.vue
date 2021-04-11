<template>
  <!-- site page start -->
  <div class="site">
    <!-- site nav start -->
    <div class="site_nav">
      <div class="div_ml_mr95">
        <!-- <a
          class="top_s_text p_l0"
          href="/Account/Login?returnUrl=%2FResource%2FAdvanceSearch%3FKeywords%3D%26SearchType%3D1%26PackageType%3D%26Domain%3D%26Deformity%3D%26ResourceType%3D%26Subject%3D%26SchoolType%3D%26LearnFrom%3D%26Grade%3D%26Sort%3D%26ShowMoreOptions%3D"
          >登录</a
        >
        <span class="top_s_bo"></span>
        <a
          class="top_s_text"
          href="/Account/Register?returnUrl=%2FResource%2FAdvanceSearch%3FKeywords%3D%26SearchType%3D1%26PackageType%3D%26Domain%3D%26Deformity%3D%26ResourceType%3D%26Subject%3D%26SchoolType%3D%26LearnFrom%3D%26Grade%3D%26Sort%3D%26ShowMoreOptions%3D"
          >注册</a
        >

        <span class="fr">欢迎访问上海市特殊教育资源库</span> -->

        <header
          role="banner"
          class="Sticky AppHeader"
          data-za-module="TopNavBar"
        >
          <div class="AppHeader-inner">
           

            <ul role="navigation" >
              <li> <router-link to="/home/index" replace type="primary"  aria-label="上海特殊教育资源库" :underline="false">欢迎访问上海市特殊教育资源库</router-link></li>
              <li role="tab">
                <router-link to="/account/register" type="danger" class="font12" :underline="false">注册 </router-link>
              </li>
              <li role="tab">
               <router-link to="/account/login"    class="font12" :underline="false">登录</router-link>
              </li>
              <li role="tab" >
               <router-link to="/account/space"  class="font12" :underline="false">进入我的空间</router-link>
              </li>
              <li>
             
              </li>
            </ul>
            <div style="margin-right:20px;" class="m_5_top">
              
                 <el-dropdown :hide-timeout="300">
                  <el-button type="info" circle
                    ><el-image
                      class="img_header"
                      src="http://pic3.zhimg.com/v2-409c2376878382d891bfe8a294606933_xs.jpg"
                    ></el-image>
                  </el-button>
                  <!-- <el-link class="img-header">JimmyPoor</el-link> -->
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item @click.native="handleShowAccountInfo"
                      ><i class="el-icon-user-solid"></i
                      >个人空间</el-dropdown-item
                    >
                    <el-dropdown-item @click.native="handleChangePassword"
                      ><i class="el-icon-lock"></i>修改密码</el-dropdown-item
                    >
                    <el-dropdown-item @click.native="handleLogout"
                      ><i class="el-icon-info"></i>注销
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                jimmy
            </div>
          </div>
          <div></div>
        </header>
      </div>
    </div>

    <!-- site nav end -->
    <div class="site_container bk_gray">
      <div class="site_top">
        <img src="../../../assets/images/t_banner.jpg" />
      </div>

      <transition name="fade" mode="out-in" appear>
        <router-view class="p_30_left_right"></router-view>
      </transition>

      <div class="site_footer"></div>
    </div>
  </div>

  <!-- site page end -->
</template>

<script>
export default {};
</script>

<style>
@import "../../../assets/css/site.css";

.AppHeader {
  background-clip: content-box;
}
.AppHeader {
  -webkit-box-shadow: 0 1px 3px rgba(18, 18, 18, 0.1);
  box-shadow: 0 1px 3px rgba(18, 18, 18, 0.1);
}
.AppHeader {
  position: relative;
  z-index: 100;
  min-width: 800px;
  overflow: hidden;
  background: #fff;
  margin:0 auto;
}

.AppHeader-inner {
  display: flex;
  justify-content: space-between;
}

.AppHeader ul,
menu,
dir {
  list-style-type: none;
  -webkit-margin-before: 1em;
  -webkit-margin-after: 1em;
  -webkit-margin-start: 0px;
  -webkit-margin-end: 0px;
  -webkit-padding-start: 40px;
   margin-top:20px;
}

.AppHeader ul li {
  display: inline;
  margin-right: 20px;
}

.AppHeader .el-button {
  border: 0px;
  background: transparent;
}


.img_header {
  width: 38px;
  height: 38px;
  border-radius: 50%;
}
</style>

