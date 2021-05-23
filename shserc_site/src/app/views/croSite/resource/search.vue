<template>
    <div>
    <div class="block_panel green_edge">
     <p class="green_panel_title">
      <span><i class="el-icon-search"></i> 众筹资源检索</span>
    </p>
      <CroSourceSearchArea
        :isShowAdvHit="true"
        :isForceSearch="true"
        @handleSearch="handleSearch"
        :defaultSearchPhrase="this.$route.query.key"
        :defaultSelectItems="defaultSelectSearchItems"
      ></CroSourceSearchArea>
    </div>
    <div class="block_panel m_30_bottom">
      <p class="green_panel_title flex_space_between">
        <span><i class="el-icon-tickets"></i> 搜索结果</span>
          <section class="font15 m_30_left ">
              <el-radio-group v-model="orderKey" fill="#578d38">
                  <el-radio-button  :label="orderPhrasesModel.viewCount" @click.native="handleChangeSearchOrder($event)"><i :class="arrowDirection" v-show="orderKey==orderPhrasesModel.viewCount"></i> 点击量 </el-radio-button>
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
import { mapState } from "vuex";
import { list } from "@/app/api/croResource";
import { selectParam, orderPhrasesModel } from "@/app/models/resource.js";
import CroSourceSearchArea from "@/app/views/croSite/resource/components/SearchArea";
import ResourceBlockList from "@/app/views/croSite/resource/components/BlockList";
import ResourceList from "@/app/views/croSite/resource/components/List";
import { deepCopy } from "@/app/utils/objectHelper";
import { ASC, DESC } from "@/app/static/type";

export default {
  components: { ResourceBlockList, ResourceList, CroSourceSearchArea },

  data() {
    return {
      isShowList: true,
      listButtonType: "success",
      blockListButtonType: "info",
      selectParam: deepCopy(selectParam),
      source: [],
      defaultSelectSearchItems: [],
      orderPhrasesModel: orderPhrasesModel,
      orderKey: orderPhrasesModel.viewCount,
      orderDirection: "up",
      arrowDirection: "el-icon-top",
    };
  },

  computed: {
    ...mapState({
      domains: (state) => state.app.resourceDomains,
    }),
  },

  created() {
    this.handleDefaultSelectItem();
  },

  watch: {
    $route() {
      this.defaultSelectSearchItems = [];
      if (this.$route.query.domainId > 0) {
        this.handleDefaultSelectItem();
      } else {
        location.href = location.href;
      }
    },
  },

  methods: {
    handleShowBlockList() {
      this.isShowList = false;
      this.listButtonType = "info";
      this.blockListButtonType = "success";
    },

    handleShowList() {
      this.isShowList = true;
      this.blockListButtonType = "info";
      this.listButtonType = "success";
    },

    handleSearch(option) {
      let selectItems = option.selectItems;
      let selectParam2 = this.selectParam;
      if (selectItems.length <= 0){ }
      console.log('------------------');
      console.log(selectParam2);

      if (selectItems && selectItems.length>0) {
        for (let i in selectItems) {
          let item = selectItems[i];
          selectParam2[item.parent.type] = item.id;
        }
      }
      else{
      selectParam2 = deepCopy(selectParam);
      }

      if (this.orderKey == orderPhrasesModel.viewCount) {
        selectParam2.orderPhrases[orderPhrasesModel.viewCount] =
          this.orderDirection == "up" ? ASC : DESC;
      }

      selectParam2.searchPhrase = option.searchPhrase; // search keyword

      this.selectParam=selectParam2; 
      this.loadResourceList();
    },

    handleChangeSearchOrder(e, val) {
      // fix issue for click twice when click el-radio button
      if (e.target.tagName === "INPUT") {
        this.arrowDirection =
          this.arrowDirection == "el-icon-top"
            ? "el-icon-bottom"
            : "el-icon-top";

        this.orderDirection =
          this.arrowDirection == "el-icon-top" ? "up" : "down";

        if (this.orderKey == orderPhrasesModel.viewCount) {
          this.selectParam.orderPhrases[orderPhrasesModel.viewCount] =
            this.orderDirection == "up" ? ASC : DESC;
        }
        this.loadResourceList();
      }
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadResourceList();
    },

    handleDefaultSelectItem() {
      let domains = this.domains;
      let domainId = this.$route.query.domainId;
      for (let i in this.domains) {
        if (domains[i].id == domainId) {
          this.defaultSelectSearchItems.push(domains[i]);
        }
      }
    },

    loadResourceList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      console.log("----------------param -------------------");
      console.log(this.selectParam);
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

 