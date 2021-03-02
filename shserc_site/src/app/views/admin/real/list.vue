<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>实名卡管理</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button class="el-button--danger" @click="dialogVisible = true">
        <i class="el-icon-edit"></i> 新增实名卡
      </el-button>
    </div>
    <edit @close="handleCloseEdit" :visible="dialogVisible"></edit>
    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="bindRealList"
      :pageSize="pageSize"
      :total="total"
    >
    </Table>
  </div>
</template>

<script>
import edit from "@/app/views/admin/real/edit"
import Table from "@/components/Tables/index";
import { list } from "@/app/api/real";

export default {
  components: {
    Table,edit
  },
  data() {
    return {
      columns: [
        { prop: "id", label: "实名编号" },
        { prop: "cardNo", label: "实名卡号" },
        { prop: "idCard", label: "身份证件号" },
        { prop: "realName", label: "真实姓名" },
      ],
      commands:[{
        id:1,
        label:'编辑',
        type:'primary',
        method:(index,row)=>{
          //this.$router.push('/admin/real/edit/'+row.id);
        }
      }],
      source:[],
      index:1,
      total:0,
      pageSize:10,
      dialogVisible:false
    };
  },
  mounted() {
    this.bindRealList()
  },
  methods: {
    bindRealList() {
      list(
        this.index,
        this.pageSize,
      ).then((res) => {
        if(res & res.data){
          let data=JSON.parse(res.data);
          this.total=data.total,
          this.index=data.current
          this.source=data.listData
        }
      });
    },

    handleCloseEdit(){
       this.dialogVisible=false;
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