<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>实名卡管理</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button class="el-button--danger">
        <i class="el-icon-edit"></i> 新增实名卡
      </el-button>
    </div>

    <Table :list="source" :columns="columns"> </Table>
  </div>
</template>

<script>
import Table from "@/components/Tables/index";
import { list } from "@/app/api/real";

export default {
  components: {
    Table,
  },
  data() {
    return {
      columns:[{prop:'id',label:'实名编号',},{prop:'cardId',label:'实名卡号',}]
    };
  },
  mounted() {},
  methods: {
    bindRealList() {},
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