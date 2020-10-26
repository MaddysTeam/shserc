<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源列表</el-breadcrumb-item>
    </el-breadcrumb>
    <div>
      <el-select v-model="deformity">
        <el-option
          v-for="item in deformityOptions"
          :key="item.value"
          :label="item.value"
          :value="item.value"
        >
        </el-option>
      </el-select>
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
import { resourceList } from "@/api/resource.js";
import { mapState } from "vuex";

export default {
  components: { Table },
  data() {
    return {
      columns: [
        { prop: 'id', label: 'id' },
        { prop: 'title', label: '资源标题' },
        { prop: 'author', label: '作者姓名'  }
      ],
      source: [],
      pageSize: 10,
      index: 1,
      total: 0,
      commands: [{}],
      //deformityOptions: [],
      deformity: { key: "请选择", value: 0 },
    };
  },
  computed: {
    ...mapState({
      deformityOptions: (state) => state.deformity,
    }),
  },
  mounted() {
    this.bindResourceList(this.index);
  },
  methods: {
    bindResourceList(index) {
      this.index = index;
      let result = resourceList(this.index, this.pageSize);
      this.source = result;
      this.total = 2;

      // let result = resourceList(this.index, this.pageSize).then((res) => {
      //   if (res && res.data) {
      //     let data = JSON.parse(res.data);
      //     this.total = data.total;
      //     this.source = data.listData;
      //   }
      // });
    },
  },
};
</script>
<style scoped>
</style>