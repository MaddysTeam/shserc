<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源列表</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button
        class="el-button--primary"
        type="danger"
        size="large"
        @click="addResource()"
        >新增资源</el-button
      >
    </div>
    <div class="filters">
      <el-select
        v-model="selesctOptions[0]"
        value-key="id"
        placeholder="残疾类型选择"
        @change="handleDeformitySelectChange"
      >
        <el-option
          v-for="item in deformityOptions"
          :key="item.id"
          :label="item.name"
          :value="item.value"
        >
        </el-option>
      </el-select>

      <el-select
        v-model="selesctOptions[1]"
        value-key="id"
        placeholder="资源状态选择"
        @change="handleStatusSelectChange"
      >
        <el-option
          v-for="item in resourceStatusOptions"
          :key="item.id"
          :label="item.name"
          :value="item.value"
        >
        </el-option>
      </el-select>

      <el-button @click="loadResourceList()" class="el-button--primary search">
        <i class="el-icon-edit"></i> 查询
      </el-button>
    </div>
    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="loadResourceList"
      :handlePageSizeChange="handlePageSizeChange"
      @handleSearch="handleSearch"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    ></Table>
  </div>
</template>
<script>
import Table from "@/components/Tables/index";
import { list } from "@/app/api/resource.js";
import { selectParam } from "@/app/models/resource.js";
import { mapState } from "vuex";

export default {
  components: { Table },
  data() {
    return {
      columns: [
        { prop: "id", label: "资源编号" },
        {
          prop: "title",
          label: "资源标题",
          isLink: true,
          currentRoute: "/admin/resource/list",
          method: (index, row) => {
            this.$router.push("/admin/resource/detail/" + row.id);
          },
        },
        { prop: "mediumType", label: "媒体类型" },
        { prop: "author", label: "作者姓名" },
        { prop: "authorEmail", label: "作者邮箱" },
        // { prop: "resourceType", label: "资源类型" },
        // { prop: "deformity", label: "残疾类型" },
        { prop: "viewCount", label: "点击量" },
        { prop: "downloadCount", label: "下载量" },
        { prop: "favoriteCount", label: "收藏量" },
        { prop: "commentCount", label: "评论量" },
        { prop: "state", label: "资源状态" },
      ],
      commands: [
        {
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
            this.$router.push("/admin/resource/edit/" + row.id);
          },
        },
      ],

      source: [],
      selectParam: selectParam,
      selesctOptions: ["", ""],
    };
  },
  computed: {
    ...mapState({
      deformityOptions: (state) => state.app.deformity, // 残疾类型下拉框数据源
      resourceStatusOptions: (state) => state.app.resourceStatus, // 资源状态类型下拉框数据源
    }),
  },
  mounted() {
    this.loadResourceList();
  },
  methods: {
    loadResourceList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      let result = list(selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.selectParam.total = data.total;
          console.log(data.listData);
          this.source = data.listData ? data.listData : [];
        }
      });
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadResourceList();
    },

    handleDeformitySelectChange(val) {
      this.selectParam.deformityId = val;
    },

    handleStatusSelectChange(val) {
      this.selectParam.stateId = val;
    },

    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.source = [];
      this.loadResourceList();
    },

    addResource() {
      this.$router.push("/admin/resource/add");
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