<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>单位管理</el-breadcrumb-item>
    </el-breadcrumb>
      <div class="btn-group">
        <el-button class="el-button--primary" @click="dialogVisible=true">
          <i class="el-icon-edit"></i> 新增单位
        </el-button>
        <el-button class="el-button--primary">刷新列表</el-button>
      </div>
     <edit ref="companyEdit" @close="handleCloseEdit" :visible="dialogVisible"></edit>
    <el-tree :data="source" :default-expanded-keys="[26856]" :render-content="renderContent" :default-expand-all="true"></el-tree>
  </div>
</template>

<script>
import edit from './edit.vue';
import {list} from '@/app/api/company'

export default {
  components: { edit },
  data() {
    name: 'companyList';
    return {
      dialogVisible: false,
      source: [],
    };
  },
  mounted(){
    this.handleLoadList();
  },
  methods: {

    renderContent(h, { node, data, store }) {
      return (
        <div class="node" style="">
          <span
            class="tree-img"
            style="color:#409EFF;padding:30px;font-size:14px;transition:color .15s linear"
          >
            <i class="el-icon-location"></i>
          </span>
          <span class="tree-spt font12" style="padding-top:20px; color:333;">{node.label}</span>
        </div>
      );
    },

    handleCloseEdit(){
      this.dialogVisible=false;
      this.handleLoadList();
    },

    handleLoadList(){
      list().then((res)=>{
        if(res && res.data){
        console.log(res.data);
        this.source=[JSON.parse(res.data)];
        }
      })
    }

  }
};
</script>

<style scoped>
.btn-group {
  display: flex;
  flex-direction: row;
  text-justify: auto;
  margin: 20px 20px 20px 0;
}
.el-tree {
  padding: 20px;
  overflow: auto;
  border: 1px solid #e3e3e3;
}

</style>