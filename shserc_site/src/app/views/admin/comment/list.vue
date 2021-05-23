<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>评论管理</el-breadcrumb-item>
      <el-breadcrumb-item>入库资源评论</el-breadcrumb-item>
    </el-breadcrumb>

     <Audit
      @close="handleCloseAudit"
      :visible="dialogVisible"
      :model="auditModel"
    ></Audit>

    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="loadCommentList"
      :handlePageSizeChange="handlePageSizeChange"
      @handleSearch="handleSearch"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    >
    </Table>
  </div>
</template>

<script>
import Table from "@/components/Tables/index";
import { selectParam, commentModel } from "@/app/models/comment";
import { list } from "@/app/api/comment";
import { auditModel } from "@/app/models/comment";
import { deepCopy } from "@/app/utils/objectHelper";

import Audit from "@/app/views/admin/comment/audit";

export default {
  components: {
    Table
  },
  data() {
    return {
      columns: [
        { prop: "id", label: "编号" },
        { prop: "addUserName", label: "评论人姓名" },
        { prop: "resourceTitle", label: "资源标题" },
        { prop: "content", label: "评论内容" },
        { prop: "commentTime", label: "评论时间" },
        { prop: "auditType", label: "审核状态" },
        { prop: "command", label: "操作", isCommand: true, align: "right" },
      ],
      commands: [
        {
          id: 1,
          label: "审核",
          type: "primary",
          method: (index, row) => {
              this.auditModel = {
              // resourceId: row.id,
              // resourceTitle: row.title,
              // auditOpinion: row.auditOpinion,
              // auditResult: row.stateId == this.resourceStatusOptions[2].id,
            };

            this.dialogVisible = true;
          },
        },
      ],
      source: [],
      selectParam: deepCopy(selectParam),
      dialogVisible:false,
      auditModel: deepCopy(auditModel)  
    };
  },
  mounted() {
    this.loadCommentList();
  },
  methods: {
    loadCommentList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      list(this.selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.selectParam.total = data.total;
          this.selectParam.current = data.current;
          this.source = data.listData;
        }
      });
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadCommentList();
    },

    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.source = [];
      this.loadCommentList();
    },
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