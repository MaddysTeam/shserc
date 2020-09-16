<template>
  <div>
      <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>单位管理</el-breadcrumb-item>
     </el-breadcrumb>
    <div class="btn-group">
        <el-button class="el-button--primary" @click="dialogVisible=true"><i class="el-icon-edit"></i> 新增单位</el-button>
        <el-button class="el-button--primary">刷新列表</el-button>
    </div>
    <el-dialog  title="提示" :visible.sync="dialogVisible">
        <span>这是一段信息</span>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
    </el-dialog>
    <el-tree 
    :data="source" 
    :default-expanded-keys="[26856]"
    :render-content="renderContent"></el-tree>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible:false,
      source: [
        {
          id: 1,
          label: "黄浦区",
          children: [
            {
              id: 4,
              label: "黄浦区第一小学",
              children: [],
            },
            {
              id: 5,
              label: "二级 1-2",
              children: [],
            },
          ],
        },
        {
          id: 2,
          label: "layer 1",
          children: [
            {
              id: 4,
              label: "二级 1-1",
              children: [],
            },
          ],
        }
      ],
    };
  },
  methods: {
    renderContent(h, { node, data, store }) {
      return <span class="node">
                <span class="tree-img" style="color:#409EFF;padding:20px;font-size:18px;transition:color .15s linear"><i class="el-icon-location"></i></span>
                <span class="tree-spt">{node.label}</span>
             </span>;
    },
  },
};
</script>

<style scoped>
.btn-group{
    display: flex;
    flex-direction: row;
    text-justify:auto;
    margin:20px 20px 20px 0;
}
.el-tree{
    padding:20px;
    overflow: auto;
    border:1px solid #e3e3e3; 
}


</style>