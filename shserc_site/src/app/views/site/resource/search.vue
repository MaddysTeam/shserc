<template>
  <div>
    <div class="block_panel">
     <p class="panel_title ">
      <span><i class="el-icon-search"></i> 资源检索</span>
    </p>
      <SearchArea
        :isShowAdvHit="true"
        :isForceSearch="true"
        @handleSearch="handleSearch"
        :defaultSearchPhrase="this.$route.query.key"
      ></SearchArea>
    </div>
    <div class="block_panel m_30_bottom">
      <p class="panel_title flex_space_between">
        <span><i class="el-icon-tickets"></i> 搜索结果</span>
          <section class="font15 m_30_left ">
               <el-radio-group v-model="orderKey" >
                  <el-radio-button  label="hot" @click.native="handleChangeSearchOrder($event)"><i :class="arrowDirection" v-show="orderKey=='hot'"></i> 点击量 </el-radio-button>
                  <el-radio-button label="commentCount" @click.native="handleChangeSearchOrder($event)"><i :class="arrowDirection"  v-show="orderKey=='commentCount'"></i>评论次数</el-radio-button>
                  <el-radio-button label="downloadCount" @click.native="handleChangeSearchOrder($event)"><i :class="arrowDirection"  v-show="orderKey=='downloadCount'"></i>下载次数</el-radio-button>
                </el-radio-group>
             </section>
        <span>
          <el-link
            :underline="false"
            :type="listButtonType"
            @click="handleShowList()"
            ><i class="el-icon-tickets font30"></i
          ></el-link>
          <el-link
            :underline="false"
            :type="blockListButtonType"
            @click="handleShowBlockList()"
            ><i class="el-icon-menu font30"></i
          ></el-link>
        </span>
      </p>
      <ResourceList
        v-if="isShowList"
        :isShowPage="true"
        :source="source"
        :pageSize="selectParam.pageSize"
        :total="selectParam.total"
        :handlePageSizeChange="handlePageSizeChange"
      ></ResourceList>
      <ResourceBlockList
        v-if="!isShowList"
        :isShowPage="true"
        :source="source"
        :pageSize="selectParam.pageSize"
        :total="selectParam.total"
        :coverWidth="400"
        :coverHeight="300"
         :handlePageSizeChange="handlePageSizeChange"
      ></ResourceBlockList>
    </div>
  </div>
</template>
<script>
import { list } from "@/app/api/resource";
import { selectParam } from "@/app/models/resource.js";
import SearchArea from "@/app/views/site/resource/components/SearchArea";
import ResourceBlockList from "@/app/views/site/resource/components/BlockList";
import ResourceList from "@/app/views/site/resource/components/List";
import { deepCopy } from "@/app/utils/objectHelper";
export default {
  components: { SearchArea, ResourceBlockList, ResourceList },

  data() {
    return {
      isShowList: true,
      listButtonType: "primary",
      blockListButtonType: "info",
      selectParam: deepCopy(selectParam),
      source: [],
      orderKey:"hot",
      arrowDirection:"el-icon-top"
    };
  },

  mounted() {
   //this.loadResourceList();

  },

  methods: {
    handleShowBlockList() {
      this.isShowList = false;
      this.listButtonType = "info";
      this.blockListButtonType = "primary";
    },

    handleShowList() {
      this.isShowList = true;
      this.blockListButtonType = "info";
      this.listButtonType = "primary";
    },

    handleSearch(option) {
      let selectItems = option.selectItems;
      this.selectParam = deepCopy(selectParam);
      if (selectItems) {
        for (let i in selectItems) {
          let item = selectItems[i];
          this.selectParam[item.parent.type] = item.id;
        }
        console.log(this.selectParam);
      }

      this.selectParam.searchPhrase = option.searchPhrase;
      this.loadResourceList();
    },

       handleChangeSearchOrder(e,val){
      if(e.target.tagName==="INPUT"){
        this.arrowDirection= this.arrowDirection=="el-icon-top"? "el-icon-bottom":"el-icon-top";
      }
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadResourceList();
    },

    loadResourceList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      let result = list(this.selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.selectParam.total = data.total;
          this.source = data.listData ? data.listData : [];
        }
      });
    },
  },
};
</script>

<style scoped>
</style>