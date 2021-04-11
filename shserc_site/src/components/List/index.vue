<template>
  <div>

    <!-- list start -->
    <div class="list" v-if="listType == 'list'">
      <div class="list_square" v-for="(item, index) in source" :key="index">
        <slot v-bind:item="item"></slot>
      </div>
    </div>
    <!-- list end -->

  <!-- block list start -->
    <div class="list" v-if="listType == 'blockList'">
      <el-row>
        <el-col
          :md="10"
          :lg="8"
          v-for="(item, index) in source"
          :key="index"
          class="p_20"
        >
          <el-card :body-style="{ padding: '0px' }" class="font12 height300">
            <slot v-bind:item="item"></slot>
          </el-card>
        </el-col> </el-row
      >f
    </div>
     <!-- block list end -->
     
    <div class="outer">
      <el-pagination
        class="pagenation"
        background
        :current-page="current"
        :page-size="pageSize"
        @size-change="pageSizeChange"
        @current-change="currentChange"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  components: {},

  props: {
    listType: { type: String, default: "list" },
    source: { type: Array, default: [] },
    total: { type: Number, default: 0 },
    pageSize: { type: Number, default: 0 },
    current: { type: Number, default: 0 },
  },

  data() {
    return {};
  },

  methods: {
    currentChange: function (val) {
      // val["searchPhrase"] = this.searchPhrase;
      //this.handleChange(val);
    },

    pageSizeChange: function (val) {
      //this.handlePageSizeChange(val);
    },
  },
};
</script>

<style scoped>
.list_square {
  border-bottom: 1px dotted #bfbfbf;
  padding-bottom: 20px;
  margin-bottom: 40px;
  display: flex;
}
</style>