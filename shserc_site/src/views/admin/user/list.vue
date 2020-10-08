<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{path:'/'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- <el-form>
      <el-form-item>
        <el-input placeholder=""></el-input>
      </el-form-item>
    </el-form> -->
    <div class="btn-group">
      <el-button class="el-button--primary" @click="dialogVisible = true">
        <i class="el-icon-edit"></i> 新增用户
      </el-button>
    </div>
    <edit @close="handleCloseEdit" :visible="dialogVisible"></edit>
    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="pageChange"
      :pageSize="pageSize"
      :total="total"
    ></Table>
  </div>
</template>

<script>
import edit from "./edit.vue";
import Table from "@/components/Tables/index";
import { userList } from "@/api/user";

export default {
  components: { Table, edit },
  name: "user",
  data() {
    return {
      columns: [
        { prop: "id", label: "id" },
        { prop: "userName", label: "name" },
      ],
      source: [],
      pageSize: 2,
      index: 1,
      total: 0,
      commands: [
        {
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
            console.log("编辑:" + index, row);
          },
        },
      ],

      dialogVisible: false,
    };
  },
  mounted() {
    this.pageChange(this.index);
  },
  methods: {
    pageChange(index) {
      let result = userList(index, this.pageSize).then((res) => {
        if (res && res.data) {
          let data=JSON.parse(res.data);
          this.total = data.total;
          this.source = data.listData;
        }
      });
    },

    handleCloseEdit() {
      this.dialogVisible = false;
    },
  },
};
</script>

<style  scoped>
.btn-group{
  display: flex;
  flex-direction: row;
  text-justify: auto;
  margin:20px 20px 20px 0
}
</style>