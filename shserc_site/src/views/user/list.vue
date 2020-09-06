<template>
  <div>
  <el-form>
    <el-form-item>
       <el-input  placeholder=""></el-input>
    </el-form-item> 
  </el-form>
  <Table  :list="source" :columns="columns" :commands="commands" :handleChange="pageChange" :pageSize="pageSize" :total="total"></Table>
</div>
</template>

<script>
import Table from "../../components/Tables/index";
import { userList } from "../../api/user";
export default {
  components: { Table },
  name: "user",
  data() {
    return {
      columns: [{ prop: 'id',label:'id' },{ prop: 'userName',label:'name' }],
      source: [],
      pageSize:2,
      index:1,
      total:0,
      commands: [
        {
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
           console.log('编辑:'+index, row)
          },
        },
      ],
    };
  },
  mounted() {
    this.pageChange(this.index);
  },
  methods:{
    pageChange(index){
      let result= userList(index,this.pageSize).then((res)=>{
        this.total=res.data.total;
        this.source= res.data.listData;
      });
    
    }
  }
};
</script>

<style  scoped></style>