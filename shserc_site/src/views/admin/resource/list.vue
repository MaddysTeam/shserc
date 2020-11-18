<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源列表</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button class="el-button--primary" type="danger" size="large" @click="addResource()">新增资源</el-button>
    </div>
    <div class="filters">
      <el-select
        v-model="deformity"
        value-key="id"
        placeholder="残疾类型选择"
        @change="deformitySelectChange"
      >
        <el-option
          v-for="item in deformityOptions"
          :key="item.id"
          :label="item.name"
          :value="item.value"
        >
        </el-option>
      </el-select>

      <el-button @click="bindResourceList()" class="el-button--primary search">
        <i class="el-icon-edit"></i> 查询
      </el-button>
    </div>
    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="bindResourceList"
      :pageSize="pageSize"
      :total="total"
    ></Table>
  </div>
</template>
<script>
import Table from "@/components/Tables/index";
import { resourceList } from "@/app/api/resource.js";
import { mapState } from "vuex";

export default {
  components: { Table },
  data() {
    return {
      columns: [
        { prop: "id", label: "id" },
        { prop: "title", label: "资源标题", isLink: true,method:(index,row)=>{
          this.$router.push('detail/'+row.id);
        }},
        { prop: "author", label: "作者姓名" },
        { prop: "author", label: "作者邮箱" },
        { prop: "resourceType", label: "资源类型" },
        { prop: "deformity", label: "残疾类型" },
        { prop: "state", label: "资源状态" },
        { prop: "mediumType", label: "媒体类型" },
        { prop: "viewCount", label: "访问次数" },
      ],
      source: [],
      pageSize: 10,
      index: 1,
      total: 0,
       commands: [{
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
            this.$router.push('/admin/resource/edit/'+row.id)
          },
      }],
      // deformityOptions: [],
      deformity: {  },
      deformityId: 0,
    };
  },
  computed: {
    ...mapState({
      deformityOptions: (state) => state.app.deformity,
    }),
  },
  mounted() {
    this.bindResourceList();
  },
  methods: {
    bindResourceList() {
      let result = resourceList(
        this.index,
        this.pageSize,
        this.deformityId
      ).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.total = data.total;
          this.source = data.listData;
        }
      });
    },

    deformitySelectChange(val) {
      this.deformityId = val;
    },

    addResource(){
      this.$router.push('/admin/resource/add');
    }
  },
};
</script>
<style scoped>
.filters {
  display: flex;
  margin: 20px;
}

.filters .search {
  margin-left: 10px;
}

.btn-group,.filters {
   display: flex;
   flex-direction: row;
   text-justify: auto;
   margin: 20px 20px 20px 0;
 }
</style>