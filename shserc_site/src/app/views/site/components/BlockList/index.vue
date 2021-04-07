<template>
  <div>
    <el-row>
      <el-col
        :md="10"
        :lg="8"
        v-for="(domain, index) in [1, 2, 3, 4, 5,6,7,8,9]"
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
              <i class="el-icon-star-on"></i> {{ domain.name }}
            </div>
            <!-- widget content end-->

            <!-- widget content start-->
            <div class="widget-content">
              <el-row style="padding: 20px">
                <el-col
                  v-for="j in [1, 2, 3]"
                  :key="j"
                  style="text-align: left"
                >
                  <!-- <router-link
                    :to="{
                      path: '/admin/resource/list',
                      query: { typeId: type.id },
                    }"
                    >{{ type.name }}</router-link
                  > -->
                  <p></p>
                </el-col>
              </el-row>
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