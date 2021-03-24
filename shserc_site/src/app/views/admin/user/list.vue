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
      @close="handleCloseEdit"
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
import edit from "./edit.vue";
import Table from "@/components/Tables/index";
import { list, info } from "@/app/api/user";
import { selectParam, userModel } from "@/app/models/user";
import {deepCopy} from "@/app/utils/objectHelper"

export default {
  components: { Table, edit },
  name: "user",
  data() {
    return {
      columns: [
        { prop: "id", label: "用户编号", align: "center" },
        { prop: "userName", label: "用户名", align: "center" },
        { prop: "companyName", label: "所在单位", align: "center" },
        { prop: "realName", label: "真实姓名", align: "center" },
         { prop: "roleName", label: "角色", align: "center" },
        { prop: "command", label: "操作", isCommand: true, align: "right" },
      ],
      commands: [
        {
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
            info(row.id).then((res) => {
              if (res && res.data) {
                this.editModel = JSON.parse(res.data);
                this.editModel.companyId = "" + this.editModel.companyId; //fix bugs for key node type is string
                this.dialogVisible = true;
              }
            });
          }
        },{
          id: 2,
          label: "设置角色",
          type: "danger",
        }
      ],
      source: [],
      selectParam: deepCopy(selectParam),
      dialogVisible: false,
      editModel: deepCopy(userModel),
    };
  },
  mounted() {
    this.loadUserList();
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

    handleCloseEdit() {
      this.dialogVisible = false;
      this.loadUserList();
      this.editModel=deepCopy(userModel);
    },

    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.loadUserList();
    },

    handleAdd(){
      this.dialogVisible = true;
    }
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