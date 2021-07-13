<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>栏目管理</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="btn-group">
      <el-button class="el-button--primary" @click="dialogVisible = true">
        <i class="el-icon-edit"></i> 新增管理
      </el-button>
      <!-- <el-button class="el-button--primary">刷新列表</el-button> -->
    </div>
    <edit
      ref="columnEdit"
      @close="handleCloseEdit"
      :visible="dialogVisible"
      :model="editModel"
      :columns="source"
    ></edit>
    <el-tree :data="source" :default-expand-all="true" class="font12">
      <span class="custom-tree-node" slot-scope="{ node, data }" @click.stop>
        <span> <i class="el-icon-menu"></i> {{ data.title }}</span>
        <span>
          <el-button
            type="text"
            size="mini"
            @click.native.prevent="() => handleEdit(node, data)"
          >
            编辑
          </el-button>
          <!-- <el-button
            type="text"
            size="mini"
            @click="() => handleChangeState(node, data)"
          >
            禁用
          </el-button> -->
        </span>
      </span>
    </el-tree>
  </div>
</template>

<script>
import { messages } from "@/app_shsedu/static/message";
import { Notification } from "element-ui";
import edit from "./edit.vue";
import { list } from "@/app_shsedu/api/column";
import { deepCopy, buildHierarchy } from "@/app_shsedu/utils/objectHelper";
import { columnModel, selectParam } from "@/app_shsedu/models/column";
import { mapState } from "vuex";

export default {
  components: { edit },

  data() {
    name: "menuList";
    return {
      dialogVisible: false,
      originSource: [],
      source: [],
      editModel: deepCopy(columnModel),
      //stateModel: deepCopy(stateModel),
    };
  },

  computed: {
    ...mapState({
      //allRoles: (state) => state.app.roles,
    }),
  },

  mounted() {
    this.handleLoadColumns();
  },

  methods: {
    handleCloseEdit() {
      this.dialogVisible = false;
      this.editModel = deepCopy(menuModel);
    },

    handleLoadColumns() {
      list(selectParam).then((res) => {
        if (res && res.data) {
          let originSource = JSON.parse(res.data).listData;
          this.source = buildHierarchy(originSource);
          console.log("------------------------");
          console.log(this.source);
          this.originSource = originSource;
        }
      });
    },

    handleEdit(node, data) {
      this.editModel = data;
      this.dialogVisible = true;
      this.handleLoadColumns();
      return false;
    },

    // handleChangeState(node, data) {
    //   this.stateModel.targetId = data.id;
    //   //TODO:this.stateModel.stateId=
    //   changeState(this.stateModel).then((res) => {
    //     if (res) {
    //       Notification.success({ message: messages.SUCCESS });
    //     }
    //   });
    //   return false;
    // },
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
.el-select {
  width:400px;
}
</style>