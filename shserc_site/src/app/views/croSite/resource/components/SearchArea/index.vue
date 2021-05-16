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
          <el-button
            type="success"
            @click.prevent="handlePrevent"
            @click="handleSubmit()"
          >
            <i class="el-icon-search"></i>
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
              <el-tag class="cursor_pointer m_5" type="success">{{
                item.name
              }}</el-tag>
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
      <el-link @click="handleShowMoreOptions()">
        <strong><i class="fa fa-arrow-down"></i> 更多条件</strong>
      </el-link>
    </div>
    <div class="text-center less" v-show="isShowMoreOptions && isShowAdvHit">
      <el-link @click="handleHideMoreOptions()">
        <strong><i class="fa fa-arrow-up"></i> 收起</strong>
      </el-link>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import {
  getRelevantByRelevantId,
  getChildrenByParentId,
  getTargetRelevant,
} from "@/app/utils/dictHelper";
import { DICTIONARY_TYPES } from "@/app/static/type";

export default {
  props: {
    handleSearch: {
      type: Function,
    },
    isShowAdvHit: { type: Boolean },
    isForceSearch: { type: Boolean },
    defaultSelectItems: { type: Array },
    defaultSearchPhrase: { type: String, default: "" },
  },

  data() {
    return {
      allOptions: [],
      selectedItems: [],
      isShowMoreOptions: false,
      searchPhrase: "",
    };
  },

  computed: {
    ...mapState({
      dict: (state) => state.app.dict,
      showOptions: (state) => [
        {
          title: "领 域",
          type: "domainId",
          childType: "resourceTypeId",
          typeId: DICTIONARY_TYPES[2].id,
          isShow: true,
          items: state.app.resourceDomains,
        },
        {
          title: "分 类",
          type: "deformityId",
          childType: "",
          typeId: 0,
          isShow: true,
          items: state.app.deformity,
        },
      ],

      moreOptions: (state) => [
        {
          title: "类 型",
          type: "resourceTypeId",
          parentType: "domainId",
          childType: "",
          typeId: DICTIONARY_TYPES[8].id,
          isShow: true,
          items: state.app.resourceTypes,
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

    this.allOptions = this.allOptions.concat(this.showOptions);

    this.handleSelectItems();
  },

  watch: {
    defaultSelectItems(val, oldVal) {
      this.handleSelectItems();
    },
  },

  methods: {
    handlePrevent() {},

    handleSubmit() {
      this.$emit("handleSearch", {
        searchPhrase: this.searchPhrase,
        selectItems: this.selectedItems,
      });
    },

    handleSelectItem(item, option) {
      item["parent"] = option; // save option data into item

      var options = this.showOptions.concat(this.moreOptions);
      if (option.childType) {
        let filterChildren = getRelevantByRelevantId(item.id, this.dict);
        for (let i in options) {
          if (options[i].type == option.childType) {
            options[i].items = filterChildren;
            break;
          }
        }
      }

      if (option.parentType) {
        let filterParnet = getTargetRelevant(item.relevantId, this.dict);
        console.log(filterParnet);
        for (let i in options) {
          if (options[i].type == option.parentType) {
            options[i].items = filterParnet;
            break;
          }
        }
      }

      for (let i in this.selectedItems) {
        if (this.selectedItems[i].id == item.id) {
          return false;
        }
      }
      if (item && item.value > 0) {
        option.isShow = false;

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
      let option = item["parent"];

      var options = this.showOptions.concat(this.moreOptions);
      if (option && option.childType) {
        for (let i in options) {
          if (options[i].type == option.childType) {
            options[i].items = getChildrenByParentId(
              options[i].typeId,
              this.dict
            ); // restore all child option items  when unselect parent option item
          }
        }
      }

      if (option && option.parentType) {
        for (let i in options) {
          if (options[i].type == option.parentType) {
            options[i].items = getChildrenByParentId(
              options[i].typeId,
              this.dict
            ); // restore all parent option items  when unselect child option item
          }
        }
      }

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

    handleShowMoreOptions() {
      this.isShowMoreOptions = true;
      this.allOptions = this.allOptions.concat(this.moreOptions);
    },

    handleHideMoreOptions() {
      this.isShowMoreOptions = false;
      this.allOptions = this.showOptions;
    },

    handleSelectItems() {
      this.selectedItems = [];
      for (let i in this.defaultSelectItems) {
        this.handleSelectItem(this.defaultSelectItems[i], this.allOptions[0]); //TODO will change asap
      }
    },

  },
};
</script>

<style>
</style>