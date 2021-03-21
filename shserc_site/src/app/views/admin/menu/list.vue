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
      <!-- <el-button class="el-button--primary">刷新列表</el-button> -->
    </div>
    <edit
      ref="menuEdit"
      @close="handleCloseEdit"
      :visible="dialogVisible"
      :model="editModel"
    ></edit>
    <el-tree :data="source" :default-expand-all="true">
      <span class="custom-tree-node" slot-scope="{ node, data }" @click.stop>
        <span> <i class="el-icon-menu"></i> {{ data.title }}</span>
        <span>
          <el-select
            v-model="data.roles"
            multiple
            size="mini"
            collapse-tags
            @change="handleEditMenuRole(data)"
          >
            <el-option
              v-for="item in allRoles"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          <el-button type="text" size="mini" @click.native.prevent="() => handleEdit(node,data)">
            编辑
          </el-button>
          <el-button type="text" size="mini" @click="() => handleForbidden(node, data)">
            禁用
          </el-button>
        </span>
      </span>
    </el-tree>
  </div>
</template>

<script>
import edit from "./edit.vue";
import { list } from "@/app/api/menu";
import {deepCopy,buildHierarchy} from "@/app/utils/objectHelper"
import{menuModel,selectParam} from "@/app/models/menu"
import { mapState } from "vuex";

export default {
  components: { edit },

  data() {
    name: "menuList";
    return {
      dialogVisible: false,
      originSource: [],
      source: [],
      editModel:deepCopy(menuModel)
    };
  },

  computed: {
    ...mapState({
      allRoles: (state) => state.app.roles,
    }),
  },

  mounted() {
    this.handleLoadMenus();
  },

  methods: {

    handleCloseEdit() {
      this.dialogVisible = false;
      this.editModel=deepCopy(menuModel);
    },

    handleLoadMenus() {
      list(selectParam).then((res) => {
        if (res && res.data) {
          let originSource = JSON.parse(res.data).listData;
          this.source = buildHierarchy(originSource);
          this.originSource = originSource;
        }
      });
    },

    handleEditMenuRole(data) {
      //record current  menu roles and change parent menu roles
      let currentMenuRoles = [];
      let result = [];

      if (data && data.roles) {
        data.roles.map((roleId) => {
          result.push({ menuId: data.id, roleId: roleId });
          currentMenuRoles.push({ menuId: data.id, roleId: roleId });
        });
      }

      while (data.parentId && data.parentId > 0) {
        let parent = this.originSource.find((menu) => menu.id == data.parentId);
        if (parent && parent.roles) {
          currentMenuRoles.map((role) => {
            result.push({ menuId: parent.id, roleId: role.roleId });
          });
          parent.roles.map((role) => {
            result.push({ menuId: parent.id, roleId: role });
          });
        }

        data = parent;
      }

      console.log(result);
    },

    handleEdit(node,data) {
      this.dialogVisible=true;
      return false;
    },

    handleForbidden(node,data){
      return false;
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