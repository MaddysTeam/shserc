<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button class="el-button--danger" @click="handleEditButtonClick()">
        <i class="el-icon-edit"></i> 新增角色
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
      :handleChange="loadRoleList"
      :handlePageSizeChange="handlePageSizeChange"
      @handleSearch="handleSearch"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    >
    </Table>
  </div>
</template>

<script>
import edit from "@/app/views/admin/role/edit";
import Table from "@/components/Tables/index";
import { selectParam, roleModel } from "@/app/models/role";
import { list, info } from "@/app/api/role";
import { deepCopy} from "@/app/utils/objectHelper"

export default {
  components: {
    Table,
    edit,
  },
  data() {
    return {
      columns: [
        { prop: "id", label: "角色编号" },
        { prop: "roleName", label: "角色名称" },
        { prop: "description", label: "角色描述" },
        { prop: "command", label: "操作" ,isCommand:true,align:"right"},
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
                this.dialogVisible = true;
              }
            });
          },
        },
      ],
      source: [],
      selectParam: selectParam,
      dialogVisible: false,
      editModel: deepCopy(roleModel),
    };
  },
  mounted() {
    this.loadRoleList();
  },
  methods: {
    loadRoleList(current) {
      if(current){
        this.selectParam.current=current;
      }
      list(this.selectParam).then((res) => {
        if (res && res.data) {        
          let data = JSON.parse(res.data);
          this.selectParam.total = data.total;
          this.selectParam.current = data.current;
         // this.selectParam.source = data.listData;
          this.source = data.listData;
        }
      });
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadRoleList();
    },

    handleCloseEdit() {
      this.dialogVisible = false;
      this.editModel=deepCopy(roleModel),
      this.loadRoleList();
    },

    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.source=[];
      this.loadRoleList();
    },

    handleEditButtonClick(){
      this.dialogVisible = true ;
    }
  },
};
</script>

<style scoped>
.btn-group {
  display: flex;
  flex-direction: row;
  text-justify: auto;
  margin: 20px 20px 20px 0;
}
</style>