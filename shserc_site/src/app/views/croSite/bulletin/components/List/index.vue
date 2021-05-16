<template>
  <div>
    <!-- <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>众筹资源公告</el-breadcrumb-item>
    </el-breadcrumb> -->

    <Table
      :list="source"
      :columns="columns"
      :handleChange="loadBulletinList"
      :handlePageSizeChange="handlePageSizeChange"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
      :isPagination="false"
      :isShowSearchBox="false"
    >
      <template slot-scope="scopes" slot="title">
        <div v-if="scopes.scope.row.top">
          <strong font="color:red">【置顶】</strong>
        </div>
        <div>{{ scopes.scope.row.title }}</div>
      </template>
      <template slot-scope="scopes" slot="content">
        <div v-html="scopes.scope.row.content"></div>
      </template>
      <!-- <template slot-scope="scopes" slot="content">
        <div v-html="scopes.scope.row.content"></div>
      </template> -->
    </Table>
  </div>
</template>
<script>
import Table from "@/components/Tables/index";
import { list } from "@/app/api/bulletin.js";
import { selectParam, setTopModel } from "@/app/models/bulletin.js";
import { deepCopy } from "@/app/utils/objectHelper";

export default {
  components: { Table },
  data() {
    return {
      columns: [
        {
          prop: "title",
          label: "公告标题",
          align: "center",
          width: "150px",
          isTemplate: true,
        },
        { prop: "content", label: "公告内容", isTemplate: true },
      ],
      source: [],
      selectParam: deepCopy(selectParam),
      setTopModel: setTopModel,
      selesctOptions: ["", ""],
    };
  },
  mounted() {
    this.loadBulletinList();
  },
  methods: {
    loadBulletinList(current) {
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
    handleSearch(){

    },
    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadBulletinList();
    },  
  },
};
</script>
<style scoped>
.filters {
  display: flex;
  margin: 20px;
}

.filters .search {
  margin-left: 10px;
}

.btn-group,
.filters {
  display: flex;
  flex-direction: row;
  text-justify: auto;
  margin: 20px 20px 20px 0;
}
</style>