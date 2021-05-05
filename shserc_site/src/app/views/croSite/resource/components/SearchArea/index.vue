<template>
  <div>
    <div class="search">
      <div class="search bar1">
        <form>
          <input
            v-model="searchPhrase"
            type="text"
            placeholder="请输入您要搜索的内容..."
          />
          <el-button type="success" @click.prevent="handlePrevent" @click="handleSubmit()">
            <i class="el-icon-search" ></i>
          </el-button>
        </form>
      </div>
    </div>

    <div class="adv_hit" v-show="isShowAdvHit">
      <div class="dash"></div>
      <dl
        v-show="option.isShow"
        v-for="option in allOptions"
        :key="option.title"
      >
        <dt class="green">{{ option.title }}：</dt>
        <dd>
          <ul id="domainChoose" class="items">
            <li><el-button type="success" size="mini">全部</el-button></li>
            <li
              class="item"
              v-for="item in option.items"
              :key="item.id"
              @click="handleSelectItem(item, option)"
            >
              <el-tag class="cursor_pointer m_5 " type="success">{{ item.name }}</el-tag>
            </li>
          </ul>
        </dd>
      </dl>

      <dl class="selectItems hidden">
        <dt class="green">过滤条件：</dt>
        <dd>
          <ul id="selectItems">
            <li @click="handleUnSelectAllItems()">
              <el-button type="danger" size="mini"
                ><i class="el-icon-delete"></i
              ></el-button>
            </li>
            <li v-for="item in selectedItems" :key="item.id">
              <el-badge :value="'x'" class="item">
                <el-tag
                  class="cursor_pointer m_5"
                  type="success"
                  @click="handleUnSelectItem(item)"
                  >{{ item.name }}</el-tag
                >
              </el-badge>
            </li>
          </ul>
        </dd>
      </dl>
    </div>

    <div class="text-center more" v-show="!isShowMoreOptions && isShowAdvHit">
      <el-link @click="isShowMoreOptions = !isShowMoreOptions">
        <strong><i class="fa fa-arrow-down"></i> 更多条件</strong>
      </el-link>
    </div>
    <div class="text-center less" v-show="isShowMoreOptions && isShowAdvHit">
      <el-link @click="isShowMoreOptions = !isShowMoreOptions">
        <strong><i class="fa fa-arrow-up"></i> 收起</strong>
      </el-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  props: {
    handleSearch: {
      type: Function,
    },
    isShowAdvHit: { type: Boolean },
    isForceSearch: { type: Boolean },
    defaultSearchPhrase: { type: String, default: "" },
  },

  data() {
    return {
      selectedItems: [],
      isShowMoreOptions: false,
      searchPhrase: "",
    };
  },

  computed: {
    ...mapState({
      //  dict: (state) => state.app.dict,
      allOptions: (state) => [
        {
          title: "领 域",
          type: "domainId",
          isShow: true,
          items: state.app.resourceDomains,
        },
        {
          title: "分 类",
          type: "deformityId",
          isShow: true,
          items: state.app.deformity,
        },
      ],
      //  deformityOptions: (state) => state.app.deformity,
      //   domainOptions: (state) => state.app.resourceDomains,
      //   typeOptions: (state) => state.app.resourceTypes,
      //   stageOptions: (state) => state.app.stages,
      //   gradeOptions: (state) => state.app.grades,
      //   subjectOptions: (state) => state.app.subjects,
      //   schoolTypeOptions: (state) => state.app.schoolTypes,
      //   learnFromOptions: (state) => state.app.learnFrom,
    }),
  },

  mounted() {
    this.searchPhrase = this.defaultSearchPhrase;
    if (this.isForceSearch) this.handleSubmit();
  },

  methods: {
    handlePrevent() {},

    handleSubmit() {
      this.$emit("handleSearch", {
        searchPhrase: this.searchPhrase,
        selectItems: this.selectedItems,
      });
    },

    handleSelectItem(item, parent) {
      item["parent"] = parent;
      for (let i in this.selectedItems) {
        if (this.selectedItems[i].id == item.id) {
          return false;
        }
      }
      if (item && item.value > 0) {
        parent.isShow = false;
        this.selectedItems.push(item);
        this.handleSubmit();
      }
    },

    handleUnSelectAllItems() {
      this.selectedItems = [];
      for (let i in this.allOptions) {
        this.allOptions[i].isShow = true;
      }
      this.handleSubmit();
    },

    handleUnSelectItem(item) {
      for (var i = 0; i < this.selectedItems.length; i++) {
        let current = this.selectedItems[i];
        if (current.id == item.id) {
          current["parent"].isShow = true;
          this.selectedItems.splice(i, 1);
          i -= 1;
        }
      }
      this.handleSubmit();
    },
  },
};
</script>

<style>
</style>