<template>
  <div>
    <div class="block_panel">
      <SearchArea :isShowAdvHit="true"></SearchArea>
    </div>
    <div class="block_panel m_30_bottom">
      <p class="panel_title flex_space_between">
        <span><i class="el-icon-tickets"></i> 搜索结果</span>
        <span>
          <el-link :underline="false" :type="listButtonType" @click="handleShowList()"
            ><i class="el-icon-tickets font30"></i
          ></el-link>
          <el-link :underline="false" :type="blockListButtonType" @click="handleShowBlockList()"
            ><i class="el-icon-menu font30"></i
          ></el-link>
        </span>
      </p>
      <ResourceList v-if="isShowList"></ResourceList>
      <ResourceBlockList v-if="!isShowList"></ResourceBlockList>
    </div>
  </div>
</template>
<script>
import SearchArea from "@/app/views/site/resource/components/SearchArea";
import ResourceBlockList from "@/app/views/site/resource/components/BlockList";
import ResourceList from "@/app/views/site/resource/components/List";
export default {
  components: { SearchArea, ResourceBlockList, ResourceList },

  data() {
    return {
      isShowList: true,
      listButtonType: "primary",
      blockListButtonType:"info"
    };
  },

  methods: {
    handleShowBlockList() {
      this.isShowList = false;
      this.listButtonType="info";
      this.blockListButtonType="primary";
    },

    handleShowList() {
      this.isShowList = true;
      this.blockListButtonType="info";
      this.listButtonType="primary";
    },
  },
};
</script>

<style scoped>
</style>