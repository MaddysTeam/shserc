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
        @click="handleAddResource()"
        ><i class="el-icon-plus"></i> 新增资源</el-button
      >
    </div>
    <!-- searchbox end-->

    <div>
      <!-- card area start-->
      <el-row style="margin-top: 100px">
        <el-col :md="10" :lg="8" v-for="domain in domains" :key="domain.id" style="padding: 20px">
          <el-card :body-style="{ padding: '0px' , height:'300px' }" class="font12">
            <div class="widget">
              <!-- widget header start-->
              <div class="widget-header">
                <i class="el-icon-star-on"></i> {{domain.name}}
              </div>
              <!-- widget content end-->

              <!-- widget content start-->
              <div class="widget-content">
                <el-row style="padding: 20px">
                  <el-col v-for="type in domain.resourceTypes" :key="type.id" style="text-align: left">
                    <router-link :to="{path:'/admin/resource/list',query:{typeId:type.id}}">{{type.name}}</router-link>
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
import { getRelevantByRelevantId } from "@/app/utils/dictHelper";

export default {
  data() {
    return {
      searchPhrase: "",
      domains: [],
    };
  },

  mounted(){
    this.handleLoadDomains();
  },

  methods: {
    searchTextChanged() {},

    handleSearchButtonClick() {
      if (this.searchPhrase === "")
        this.$router.push({
          path: "/admin/resource/list",
        });
      else
        this.$router.push({
          path: "/admin/resource/list",
          query: { searchPhrase: this.searchPhrase },
        });
    },
      

    handleAddResource() {
      this.$router.push("/admin/resource/add");
    },

    handleLoadDomains() {
      let resourceDomains = this.$store.state.app.resourceDomains;
      //TODO: fix bugs for  async loading dic data  when browser refresh 
      console.log("----------------domain-----------------")
      console.log(resourceDomains);
      let dict=this.$store.state.app.dict;

      this.domains = resourceDomains.map((item) => {
        return {
          name: item.name,
          resourceTypes: getRelevantByRelevantId(item.id,dict), // get resource types by domain id
        };
      });
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
.widget-header {
  background: #eaedf1;
  font-size: 15px;
  padding: 20px;
}
</style>