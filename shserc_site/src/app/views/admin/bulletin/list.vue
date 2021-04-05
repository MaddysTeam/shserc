<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源库公告</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="btn-group">
      <el-button class="el-button--danger" @click="handleAddBulletin()"
        >新增公告</el-button
      >
    </div>
    <Table
      :list="source"
      :columns="columns"
      :commands="commands"
      :handleChange="loadBulletinList"
      :handlePageSizeChange="handlePageSizeChange"
      @handleSearch="handleSearch"
      :pageSize="selectParam.pageSize"
      :total="selectParam.total"
    >
      <template slot-scope="scopes" slot="title">
        <div v-if="scopes.scope.row.top">
          <strong font="color:red">【置顶】</strong>
        </div>
        <div>{{ scopes.scope.row.title }}</div>
      </template>
      <template slot-scope="scopes" slot="content">
        <div v-html="scopes.scope.row.content"></div>
      </template>
    </Table>
  </div>
</template>
<script>
import { Notification } from "element-ui";
import { appEnum } from "@/app/static/enum";
import Table from "@/components/Tables/index";
import { list, del ,top} from "@/app/api/bulletin.js";
import { selectParam, setTopModel } from "@/app/models/bulletin.js";
import { deepCopy } from "@/app/utils/objectHelper";

export default {
  components: { Table },
  data() {
    return {
      columns: [
        {
          prop: "title",
          label: "公告标题",
          align: "center",
          width: "150px",
          isTemplate: true,
        },
        { prop: "content", label: "公告内容", isTemplate: true },
        {
          prop: "command",
          label: "操作",
          isCommand: true,
          width: "100px",
          align: "right",
        },
      ],
      commands: [
        {
          id: 1,
          label: "编辑",
          type: "primary",
          method: (index, row) => {
            this.$router.push("/admin/bulletin/edit/" + row.id);
          },
        },
        {
          id: 2,
          label: "置顶",
          type: "info",
          method: (index, row) => {
            //TODO:
            this.setTopModel.booleanState = !row.top;
            this.setTopModel.targetId = row.id;
            top(this.setTopModel).then((res) => {
              console.log(res);
             if (res && res.resultCode==appEnum.httpCode.success) {
                Notification.success({ message: res.message });
                this.loadBulletinList();
              }
            });
          },
        },
        {
          id: 3,
          label: "删除",
          type: "danger",
          method: (index, row) => {
            //TODO:
            del({ id: row.id }).then((res) => {
              if (res && res.data) {
                Notification.success({ message: res.message });
                this.loadBulletinList();
              }
            });
          },
        },
      ],

      source: [],
      selectParam: deepCopy(selectParam),
      setTopModel: setTopModel,
      selesctOptions: ["", ""],
    };
  },
  mounted() {
    this.loadBulletinList();
  },
  methods: {
    loadBulletinList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      let result = list(this.selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.selectParam.total = data.total;
          this.source = data.listData ? data.listData : [];
        }
      });
    },

    handlePageSizeChange(val) {
      this.selectParam.size = val;
      this.loadBulletinList();
    },

    handleSearch(val) {
      this.selectParam.searchPhrase = val;
      this.source = [];
      this.loadBulletinList();
    },

    handleAddBulletin() {
      this.$router.push("/admin/bulletin/add");
    },
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

.btn-group,
.filters {
  display: flex;
  flex-direction: row;
  text-justify: auto;
  margin: 20px 20px 20px 0;
}
</style>