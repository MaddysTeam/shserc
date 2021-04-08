<template>
  <div>
    <el-row>
      <el-col
        :md="10"
        :lg="8"
        v-for="(domain, index) in [1, 2, 3, 4, 5, 6, 7, 8, 9]"
        :key="index"
        class="p_20"
      >
        <el-card :body-style="{ padding: '0px' }" class="font12 height300">
          <div class="widget">
            <!-- widget header start-->
            <div
              class="widget-header"
              :style="{ backgroundColor: '#39b54a', color: 'white' }"
            >
              <!-- <i class="el-icon-star-on"></i> {{ domain.name }} -->
            </div>
            <!-- widget content end-->

            <!-- widget content start-->
            <div class="widget-content">
              <a href="/Resource/View/1158" title="让我们成为芭蕾演员吧">
                <img
                  class="cover"
                  src="http://tjcdn.shec.edu.cn/demo/7.jpg"
                  style="height: 150px"
              /></a>
              <div class="hot"></div>
              <div class="p_30_left_right">
                <p>
                  <el-link :underline="false" type="primary"
                    >让我们成为芭蕾演员吧</el-link>
                </p>
                
                <p>作者：上海高教电子音像出版社</p>

                <p>
                  <el-tag>点击量：80</el-tag>
                  <el-tag type="info">下载量：80</el-tag>
                  <el-tag type="danger"> 格式： pdf</el-tag>
                </p>
                <el-rate v-model="value2" :colors="colors"> </el-rate>
              </div>
            </div>
            <!-- widget content end-->
          </div>
        </el-card>
      </el-col>
    </el-row>
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
  data() {
    return {
      value1: null,
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    };
  },
  methods: {
    currentChange: function (val) {
      // val["searchPhrase"] = this.searchPhrase;
      // this.handleChange(val);
    },

    pageSizeChange: function (val) {
      // this.handlePageSizeChange(val);
    },
  },
};
</script>

<style scoped>
</style>