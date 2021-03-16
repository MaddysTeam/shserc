<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
       <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      <el-breadcrumb-item>实名管理</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button class="el-button--danger" @click="handleEditButtonClick()">
        <i class="el-icon-edit"></i> 新增实名
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
      :handleChange="loadRealList"
      :handlePageSizeChange="handlePageSizeChange"
      @handleSearch="handleSearch"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    >
    </Table>
  </div>
</template>

<script>
import edit from "@/app/views/admin/real/edit";
import Table from "@/components/Tables/index";
import { selectParam, realModel } from "@/app/models/real";
import { list, info } from "@/app/api/real";
import {deepCopy} from "@/app/utils/objectHelper"

export default {
  components: {
    Table,
    edit,
  },
  data() {
    return {
      columns: [
        { prop: "id", label: "实名编号" },
        { prop: "cardNo", label: "实名卡号" },
        { prop: "idCard", label: "身份证件号" },
        { prop: "realName", label: "真实姓名" },
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
                this.editModel.companyId = "" + this.editModel.companyId; //fix bugs for key node type is string
                this.dialogVisible = true;
                  console.log(this.editModel)
              }
            });
          },
        },
      ],
      source: [],
      selectParam: selectParam,
      dialogVisible: false,
      editModel: deepCopy(realModel),
    };
  },
  mounted() {
    this.loadRealList();
  },
  methods: {
    loadRealList(current) {
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
      this.loadRealList();
    },

    handleCloseEdit() {
      this.dialogVisible = false;
      this.loadRealList();
      this.editModel=deepCopy(realModel);
    },

    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.source=[];
      this.loadRealList();
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