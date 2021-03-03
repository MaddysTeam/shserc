<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>入库资源</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- searchbox start-->
    <div class="search-Box">
        <el-input
          placeholder="请输入关键字"
          icon="search"
          class="search"
          v-model="searchPhrase"
          @change="searchTextChanged"
        ></el-input>
        <el-button class="el-button--primary"
          ><i class="el-icon-search"></i
        ></el-button>
      </div>
    <!-- searchbox end-->

  <div>

    <!-- card area start-->
  </div>

  </div>
   <!-- searchbox end-->
</template>

<script>
export default {
    methods:{
        searchTextChanged(){

        }
    }
};
</script>

<style scoped>
 .search{
     margin-top: 20px;
     max-width: 500px;
 }
</style>