<template>
  <div>
    <List
      :source="source"
      :isShowPage="true"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
      :handleChange="loadResourceList"
      :handlePageSizeChange="handlePageSizeChange"
    >
      <template slot="item" slot-scope="resource">
        <router-link
          :to="{ path: '/croSite/resource/details/' + resource.item.id }"
          :title="resource.item.title"
          ><img
            class="cover"
            :src="resource.item.coverPath"
            style="height: 100px"
        /></router-link>
        <div class="details">
          <router-link
            :to="{ path: '/croSite/resource/details/' + resource.item.id }"
            :title="resource.item.title"
            ><span class="restitle">{{
              resource.item.title
            }}</span></router-link
          ><br />
          <p class="snippet">
            {{ resource.item.description }}
          </p>
          <div class="info">
            作者：<span>{{ resource.item.author }}</span> <p>单位：<span>{{
              resource.item.authorCompany
            }}</span></p>
            <p />
            <el-tag
              >上传日期：<span>{{ resource.item.addTime }}</span></el-tag
            >
            <el-tag type="success"
              >点击量：<span>{{ resource.item.viewCount }}</span></el-tag
            >
            <el-tag type="danger">
              格式： {{ resource.item.fileExtName }}</el-tag
            >
          </div>
        </div>
        <div class="">
          <el-button type="success" @click="handleNavToDetails(resource.item.id)"
            >预览</el-button
          >
          <el-button type="info" @click="handleModify(resource.item.id)"
            >修改</el-button
          >
          <el-button type="danger" @click="handleRemove()">删除</el-button>
        </div>
      </template>
    </List>
  </div>
</template>

<script>
import { mapState } from 'vuex';

import { routeNames } from "@/app/routers/routeNames";
import { deepCopy } from "@/app/utils/objectHelper";
import { list } from "@/app/api/croResource";
import { selectParam, orderPhrasesModel } from "@/app/models/croResource";
import List from "@/components/List";
import { DESC } from "@/app/static/type";

export default {
  components: { List },

  // props: {
  //   source: { type: Array, dufault: [] },
  //   isShowPage: { type: Boolean, default: false },
  //   pageSize: { type: Number, default: 0 },
  //   total: { type: Number, default: 0 },
  //   handleChange: { type: Function },
  //   handlePageSizeChange: { type: Function },
  // },

  data() {
    return {
      selectParam: deepCopy(selectParam),
      source:[]
    };
  },

  computed:{
     ...mapState({
         account: (state) => state.app.account
     })
  },

  mounted(){
     this.loadResourceList();
  },

  methods: {
    loadResourceList(current) {
      let param= this.selectParam;
      param.userId= this.account.id;
      param.orderPhrases[orderPhrasesModel.latest] = DESC;

      if (current) {
       param.current = current;
      }
      list(param).then((res) => {
          if(res && res.data){
             let data = JSON.parse(res.data);
             this.source = data.listData ? data.listData : [];
             param.total= data.total;
          }
      });
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadResourceList();
    },

    handleNavToDetails(id) {
      this.$router.push({name: routeNames.CroSiteRsourceDetails,params:{id:id}});
    },

    handleModify(id) {
      this.$router.push({ name: routeNames.CroMyUpload, params: { id: id } });
    },

    handleRemove() {},
  },
};
</script>

<style scoped>
</style>