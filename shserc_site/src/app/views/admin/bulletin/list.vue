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
      :handleChange="loadBulletinList"
      :handlePageSizeChange="handlePageSizeChange"
      @handleSearch="handleSearch"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    >
      <template slot-scope="scopes" slot="content">
        <div>{{scopes.scope.row.content}}</div>
      </template>
    </Table>
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
          align:"center",
          width:"100px"
        },
        { prop: "content", label: "公告内容" ,isTemplate:true,},
        { prop: "command", label: "操作" ,isCommand:true, width:'100px',align:"right"},
      ],
      commands: [ 
        {
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
            this.$router.push("/admin/bulletin/edit/" + row.id);
          },
        }, {
          id: 2,
          label: "删除",
          type: "danger",
          method: (index, row) => {
            //TODO:
          },
        }
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
      // if (current) {
      //   this.selectParam.current = current;
      // }
      // let result = list(selectParam).then((res) => {
      //   if (res && res.data) {
      //     let data = JSON.parse(res.data);
      //     this.selectParam.total = data.total;
      //     this.source = data.listData ? data.listData : [];
      //   }
      // });
      this.source=[{"title":"123","content":"1231231231231231231231231231"}]
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