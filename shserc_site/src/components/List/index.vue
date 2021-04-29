<template>
  <div>
    <!-- list start -->
    <div class="list" v-if="listType == 'list'">
      <div class="list_square" v-for="(item, index) in source" :key="index">
        <slot name="item" :item="item"></slot>
      </div>
    </div>
    <!-- list end -->


      <!-- ul list start -->
    <div class="list" v-if="listType == 'ulList'">
      <ul :class="containerClass"  v-for="(item, index) in source" :key="index">
        <slot name="item" :item="item"></slot>
      </ul>
    </div>
    <!-- ul list end -->

    <!-- block custom list start -->
    <div class="list" v-if="listType == 'blockCustomList'">
      <el-row :gutter="20">
        <el-col
          :span="6"
          v-for="(item, index) in source"
          :key="index"
          class="p_20"
        >
          <slot name="item" :item="item"></slot>
        </el-col>
      </el-row>
    </div>
    <!-- block  list end -->

    <!-- block list start -->
    <div class="list" v-if="listType == 'blockList'">
      <el-row :gutter="20">
        <el-col
          :span="8"
          v-for="(item, index) in source"
          :key="index"
          class="p_20"
        >
          <el-card :body-style="{ padding: '0px' }">
            <slot name="item" :item="item"></slot>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <!-- block list end -->

    <div class="outer">
      <el-pagination
        v-show="isShowPage"
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
    source: { type: Array, default: () => [] },
    total: { type: Number, default: 0 },
    pageSize: { type: Number, default: 0 },
    current: { type: Number, default: 0 },
    isShowPage: {type:Boolean,default:false},
    containerClass:{type:String,default:""},

    handleChange: { type: Function },
    handlePageSizeChange:{type:Function},
  },

  data() {
    return {};
  },

  methods: {
    currentChange: function (val) {
      // val["searchPhrase"] = this.searchPhrase;
      this.handleChange(val);
    },

    pageSizeChange: function (val) {
      this.handlePageSizeChange(val);
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