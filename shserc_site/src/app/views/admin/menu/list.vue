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
    <el-tree :data="source" :default-expand-all="true" class="font12">
      <span class="custom-tree-node" slot-scope="{ node, data }" @click.stop>
        <span> <i class="el-icon-menu"></i> {{ data.title }}</span>
        <span>
          <el-select
            v-model="data.roles"
            multiple
            size="mini"
            collapse-tags
            @visible-change="handleEditMenuRole($event, data)"
          >
            <el-option
              v-for="item in allRoles"
              :key="item.id"
              :label="item.roleName"
              :value="item.id"
            />
          </el-select>
          <el-button
            type="text"
            size="mini"
            @click.native.prevent="() => handleEdit(node, data)"
          >
            编辑
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => handleChangeState(node, data)"
          >
            禁用
          </el-button>
        </span>
      </span>
    </el-tree>
  </div>
</template>

<script>
import { messages } from "@/app/static/message";
import { Notification } from "element-ui";
import edit from "./edit.vue";
import { list, editMenuRole, changeState } from "@/app/api/menu";
import { deepCopy, buildHierarchy } from "@/app/utils/objectHelper";
import { stateModel, menuModel, selectParam } from "@/app/models/menu";
import { mapState } from "vuex";

export default {
  components: { edit },

  data() {
    name: "menuList";
    return {
      dialogVisible: false,
      originSource: [],
      source: [],
      editModel: deepCopy(menuModel),
      stateModel: deepCopy(stateModel),
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
      this.editModel = deepCopy(menuModel);
    },

    handleLoadMenus() {
      list(selectParam).then((res) => {
        if (res && res.data) {
          let originSource = JSON.parse(res.data).listData;
          for (let i in originSource) {
            let roles = originSource[i].roles;
            if (roles) {
              originSource[i].roles = roles.map((x) => x.id);
            }
          }
          this.source = buildHierarchy(originSource);
          console.log(this.allRoles);
          console.log("------------------------");
          console.log(this.source);
          this.originSource = originSource;
        }
      });
    },

    handleSelectMenuRole(data) {},

    handleEditMenuRole(callback, data) {
      // when menu disappear
      if (!callback) {
        //record current  menu roles and change parent menu roles, for example :
        /**
         *   parent              =>            parent + role
         *       child + role                        child +role
         */

        let currentMenuRoles = [];
        let result = [];

        if (data && data.roles) {
          data.roles.map((roleId) => {
            result.push({ menuId: data.id, roleId: roleId });
            currentMenuRoles.push({ menuId: data.id, roleId: roleId });
          });
        }

        while (data.parentId && data.parentId > 0) {
          let parent = this.originSource.find(
            (menu) => menu.id == data.parentId
          );
          if (parent && parent.roles) {
            currentMenuRoles.map((role) => {
              result.push({ menuId: parent.id, roleId: role.roleId });
            });
            parent.roles.map((roleId) => {
              if (
                !result.some((x) => x.menuId == parent.id && x.roleId == roleId)
              ) {
                result.push({ menuId: parent.id, roleId: roleId });
              }
            });
          }

          data = parent;
        }

        console.log(result);

        editMenuRole(result).then((res) => {
          Notification.success({ message: messages.SUCCESS });
        });
      }
    },

    handleEdit(node, data) {
      this.editModel = data;
      this.dialogVisible = true;
      this.handleLoadMenus();
      return false;
    },

    handleChangeState(node, data) {
      this.stateModel.targetId = data.id;
      //TODO:this.stateModel.stateId=
      changeState(this.stateModel).then((res) => {
        if (res) {
          Notification.success({ message: messages.SUCCESS });
        }
      });
      return false;
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
  padding-right: 8px;
}

.el-input {
  height: 30px;
}
</style>