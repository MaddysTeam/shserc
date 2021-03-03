<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
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
    <edit
      @close="handleCloseEdit"
      :visible="dialogVisible"
      v-model="editModel"
    ></edit>

    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="loadUserList"
      @handleSearch="handleSearch"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    ></Table>
  </div>
</template>

<script>
import edit from "./edit.vue";
import Table from "@/components/Tables/index";
import { list, info } from "@/app/api/user";
import { selectParam, userModel } from "@/app/models/user";

export default {
  components: { Table, edit },
  name: "user",
  data() {
    return {
      columns: [
        { prop: "id", label: "用户编号" },
        { prop: "userName", label: "用户名" },
        { prop: "company", label: "所在单位" },
        { prop: "realName", label: "真实姓名" },
      ],
      commands: [
        {
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
            console.log("编辑:" + index, row);
             info(row.id).then((res) => {
              if (res && res.data) {
                this.editModel = JSON.parse(res.data);
                this.dialogVisible = true;
              }
            });
          },
        },
      ],
      source: [],
      selectParam: selectParam,
      dialogVisible: false,
      editModel: userModel,
    };
  },
  mounted() {
   // this.loadUserList();
  },
  methods: {
    // loadUserList() {
    //   let result = list(selectParam).then((res) => {
    //     if (res && res.data) {
    //       let data = JSON.parse(res.data);
    //       this.total = data.total;
    //       this.source = data.listData;
    //     }
    //   });
    // },

    handleCloseEdit() {
      this.dialogVisible = false;
        //this.loadUserList();
    },

    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      //this.loadUserList();
    },
  },
};
</script>

<style  scoped>
.btn-group {
  display: flex;
  flex-direction: row;
  text-justify: auto;
  margin: 20px 20px 20px 0;
}
</style>