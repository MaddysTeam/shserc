<template>
  <!-- site page start -->
  <div class="site">
    <!-- site nav start -->
    <div class="site_nav">
      <div class="div_ml_mr95">
        <a
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

        <span class="fr">欢迎访问上海市特殊教育资源库</span>
      </div>
    </div>

    <!-- site nav end -->
    <div class="site_container bk_gray" >

      <div class="site_top">
        <img src="../../../assets/images/t_banner.jpg" />
      </div>

      <transition  name="fade" mode="out-in" appear>
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
</style>

