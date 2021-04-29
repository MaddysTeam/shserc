<template>
  <div>
    <div class="block_panel">
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
              <span class="m_10 orange cursor_pointer" @click="loadTopRankResource(orderPhrasesModel.viewCount)"><i class="el-icon-thumb"></i> 点击量 </span> 
              <span class="cursor_pointer" @click="loadTopRankResource(orderPhrasesModel.commentCount)"> <i class="el-icon-chat-line-round"></i> 评论次数 </span>
             <span class="m_10 cursor_pointer" @click="loadTopRankResource(orderPhrasesModel.downloadCount)"> <i class="el-icon-download"></i> 下载次数</span>
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