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
      <el-button class="el-button--primary" @click="handleAdd()" size="small">
        <i class="el-icon-edit"></i> 新增用户
      </el-button>
    </div>

    <edit
      @close="handleClose"
      :visible="dialogVisible"
      :model="editModel"
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
import Table from "@/components/Tables/index";
import { deepCopy } from "@/app_shsedu/utils/objectHelper";
import { list } from "@/app_shsedu/api/user";
import { selectParam, userModel, userRoleModel } from "@/app_shsedu/models/user";
import edit from "@/app_shsedu/views/admin/user/edit";

export default {
  components: { Table ,edit},
  data() {
    return {
      columns: [
        { prop: "id", label: "用户编号", align: "center" },
        { prop: "userName", label: "用户名", align: "center" },
        { prop: "district", label: "区县", align: "center" },
        { prop: "realName", label: "真实姓名", align: "center" },
        { prop: "roleName", label: "角色", align: "center" },
        { prop: "command", label: "操作", isCommand: true, align: "right" }
      ],
      commands: [],
      source:[],
      selectParam: deepCopy(selectParam),
      dialogVisible: false,
      editModel: deepCopy(userModel),
    };
  },
  mounted() {
    this.loadUserList(selectParam.current)
  },

  methods: {
    loadUserList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      let result = list(selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          selectParam.total = data.total;
          this.source = data.listData;
        }
      });
    },

    handleClose() {
      this.dialogVisible = false;
      this.loadUserList();
      this.editModel = deepCopy(userModel);
    },


    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.loadUserList();
    },

      handleAdd() {
      this.dialogVisible = true;
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