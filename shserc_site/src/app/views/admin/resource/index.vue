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
      <el-button
        class="el-button--primary"
        type="danger"
        size="large"
        @click="addResource()"
        ><i class="el-icon-plus"></i> 新增资源</el-button
      >
    </div>
    <!-- searchbox end-->

    <div>
      <!-- card area start-->
      <el-row style="margin-top: 100px">
        <el-col :md="10" :lg="8" v-for="o in 4" :key="o" style="padding: 20px">
          <el-card :body-style="{ padding: '0px' }">
            <div class="widget">
              <!-- widget header start-->
              <div class="widget-header">
                <i class="el-icon-star-on"></i> 政策与文献
                <!-- <img style="width:100%; height:100%;" src="https://gzmooc.edu.sh.cn/mooc/upload/cover/205062/20210126/20210126162545_878.jpg" /> -->
              </div>
              <!-- widget content end-->

              <!-- widget content start-->
              <div class="widget-content">
                <el-row style="padding: 20px">
                  <el-col v-for="o in 5" :key="o" style="text-align: left">
                    <b>这里是链接</b>
                    <p></p>
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
  data() {
    return {
      searchPhrase: "",
    };
  },
  methods: {
    searchTextChanged() {},
    handleSearchButtonClick() {
      if (this.searchPhrase === "")
        this.$router.push({
          path: "/admin/resource/list"
        });
      else
        this.$router.push({
          path: "/admin/resource/list",
          query: { searchPhrase: this.searchPhrase },
        });
    },
    addResource() {
      this.$router.push("/admin/resource/add");
    },
  },
};
</script>

<style scoped>
.search-Box {
  padding: 0px;
}
.search {
  margin-top: 20px;
  max-width: 500px;
  width: 50%;
}
.el-row {
  /* display:flex;
   flex-wrap: wrap; */
}
.widget > .widget-header,
.widget > .widget-content {
}
.widget-header {
  background: #eaedf1;
  font-size: 15px;
  padding: 20px;
}
.el-card {
  font-size: 12px;
}
</style>