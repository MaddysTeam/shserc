<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="btn-group">
      <el-button class="el-button--primary" @click="dialogVisible = true">
        <i class="el-icon-edit"></i> 新增菜单
      </el-button>
      <el-button class="el-button--primary">刷新列表</el-button>
    </div>
    <edit
      ref="menuEdit"
      @close="handleCloseEdit"
      :visible="dialogVisible"
    ></edit>
    <el-tree
      :data="source"
      :default-expanded-keys="[26856]"
      :render-content="renderContent"
    ></el-tree>
  </div>
</template>

<script>
import edit from "./edit.vue";
import { list } from "@/app/api/menu";

export default {
  components: { edit },

  data() {
    name: "menuList";
    return {
      dialogVisible: false,
      source: [],
    };
  },

  mounted() {
    this.bind();
  },

  methods: {
    renderContent(h, { node, data, store }) {
      console.log(node);
      return (
        <span style="flex: 1; display: flex; align-items: center; justify-content: space-between; font-size: 14px; padding-right: 8px;">
          <span>
            <span>{data.title}</span>
          </span>
          <span>
            <el-select v-model="id" value-key="id" size="mini" multiple>
              {data.roles.map(item => {
                return <el-option label={item.name} value={item.id} key={item.id} ></el-option>;
              })}
            </el-select>
            <el-button
              style="font-size: 12px;"
              type="text"
              on-click={() => this.append(data)}
            >
              绑定角色
            </el-button>
            <el-button
              style="font-size: 12px;"
              type="text"
              on-click={() => this.remove(node, data)}
            >
              编辑
            </el-button>
          </span>
        </span>
      );
    },

    handleCloseEdit() {
      this.dialogVisible = false;
    },

    bind() {
      list().then((res) => {
        if (res && res.data) {
          console.log(res.data);
          this.source = this.handleChangeSource(res.data);
        }
      });
    },

    handleEditMenuRole(data) {},

    handleEdit(data) {},

    handleChangeSource(source) {
      //TODO: bind menu
      return JSON.parse(source).listData;
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
.el-tree {
  padding: 20px;
  overflow: auto;
  border: 1px solid #e3e3e3;
}

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}

.el-input {
  height: 30px;
}
</style>