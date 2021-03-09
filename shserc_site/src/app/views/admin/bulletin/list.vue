<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源库公告</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button
        class="el-button--primary"
        type="danger"
        size="large"
        @click="addBulletin()"
        >新增公告</el-button
      >
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
import { list } from "@/app/api/bulletin.js";
import { selectParam } from "@/app/models/bulletin.js";
import { mapState } from "vuex";

export default {
  components: { Table },
  data() {
    return {
      columns: [
        {
          prop: "title",
          label: "公告标题",
        //   isLink: true,
        //   currentRoute: "/admin/resource/list",
        //   method: (index, row) => {
        //     this.$router.push("/admin/resource/detail/" + row.id);
        //   },
        },
        { prop: "content", label: "公告内容" },
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
      selectParam: [],
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
      let result = list(selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.selectParam.total = data.total;
          this.source = data.listData ? data.listData : [];
        }
      });
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadBulletinList();
    },


    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.source = [];
      this.loadBulletinList();
    },

    addBulletin() {
      this.$router.push("/admin/bulletin/add");
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