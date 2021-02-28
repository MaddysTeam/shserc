<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{path:'/'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户查询</el-breadcrumb-item>
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
      :handleChange="bindUserList"
      :pageSize="pageSize"
      :total="total"
    ></Table>
  </div>
</template>

<script>
import edit from "./edit.vue";
import Table from "@/components/Tables/index";
import { userList } from "@/app/api/user";

export default {
  components: { Table, edit },
  name: "user",
  data() {
    return {
      columns: [
        { prop: "id", label: "用户编号" },
        { prop: "userName", label: "用户名" },
        { prop: "company", label: "所在单位" },
        { prop: "realName", label: "真实姓名"}
      ],
      source: [],
      pageSize: 2,
      total: 0,
      current:1,
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
    this.bindUserList(0);
  },
  methods: {
    bindUserList(index) {
      this.current=index;
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