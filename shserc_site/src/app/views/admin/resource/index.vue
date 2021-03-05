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
        <el-button class="el-button--primary" @click="handleSearchButtonClick"
          ><i class="el-icon-search"></i
        ></el-button>
      </div>
    <!-- searchbox end-->

  <div >
    <!-- card area start-->
      <el-row style="margin-top:100px">
        <el-col  v-for="(o) in 5" :key="o"  style="width:30%; padding:20px;">
            <el-card :body-style="{padding: '0px'}">
              <div class="widget">

                 <!-- widget header start-->
									<div class="widget-header">
										<!-- <h3><i class=""></i> 政策与文献</h3> -->
                    <img style="width:100%" src="https://gzmooc.edu.sh.cn/mooc/upload/cover/205062/20210126/20210126162545_878.jpg" />
									</div>
                   <!-- widget content end-->

                   <!-- widget content start-->
									<div class="widget-content">
                    <el-row style="padding:20px">
                      <el-col v-for="o in 5" :key="o" style="text-align:left">
                          <b>这里是链接</b>
                      </el-col>
                    </el-row>
									</div>
                  <!-- widget content end-->

								</div>
            </el-card>
        </el-col>
      </el-row>
     <!-- card area end-->
  </div>

  </div>
   <!-- searchbox end-->
</template>

<script>
export default {
    data(){
     return {
       searchPhrase:""
     }
    },
    methods:{
        searchTextChanged(){

        },
        handleSearchButtonClick(){
          this.$router.push('/admin/resource/list')
        }
    }
};
</script>

<style scoped>
 .search{
     margin-top: 20px;
     max-width: 500px;
 }
 .el-row{
   display:flex;
   flex-wrap: wrap;
 }
 .widget > .widget-header, .widget > .widget-content{
  
 }
 .el-card {
   height:100%;
   font-size:12px;
 }
</style>