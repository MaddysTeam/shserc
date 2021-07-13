<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>新闻列表</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button class="el-button--primary" type="danger" @click="addResource()"
        >新增新闻</el-button
      >
    </div>
    <div class="filters">
      <!-- <el-select
        v-model="selesctOptions[0]"
        value-key="id"
        placeholder="残疾类型选择"
        @change="handleDeformitySelectChange"
      >
        <el-option
          v-for="item in deformityOptions"
          :key="item.id"
          :label="item.name"
          :value="item.value"
        >
        </el-option>
      </el-select> -->

      <!-- <el-select
        v-model="selesctOptions[1]"
        value-key="id"
        placeholder="资源状态选择"
        @change="handleStatusSelectChange"
      >
        <el-option
          v-for="item in resourceStatusOptions"
          :key="item.id"
          :label="item.name"
          :value="item.value"
        >
        </el-option>
      </el-select> -->

      <el-button @click="loadNewsList()" class="el-button--primary search">
        <i class="el-icon-edit"></i> 查询
      </el-button>
      <el-button @click="handleClearSearchOptoins()" class="el-button">
        <i class="el-icon-close"></i> 清除
      </el-button>
    </div>

    <audit
      @close="handleCloseAudit"
      :visible="dialogVisible"
      :model="auditModel"
    ></audit>

    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="loadResourceList"
      :handlePageSizeChange="handlePageSizeChange"
      @handleSearch="loadNewsList"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    ></Table>
  </div>
</template>
<script>
import { auditModel, selectParam } from "@/app_shsedu/models/news";
import { list } from "@/app_shsedu/api/news";
import edit from "@/app_shsedu/views/admin/news/edit";

export default {
  components: { Table, edit },
  data() {
    return {
      dialogVisible: false,
      auditModel: auditModel,
      source: [],
      commands: [],
      selectParam: deepCopy(selectParam),
      //editModel: deepCopy(userModel),
    };
  },
  methods: {
    loadNewsList() {
      list(selectParam).then(() => {
        if (res && res.data) {
          this.source = JSON.parse(res.data).listData;
        }
      });
    },

    handleCloseAudit() {},
  },
};
</script>