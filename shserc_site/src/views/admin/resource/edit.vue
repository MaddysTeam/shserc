<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>编辑新资源</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :model="resource" ref="resourceForm">
      <el-col :span="12">
        <el-form-item label="资源名称">
          <el-input v-model="resource.title"></el-input>
        </el-form-item>
      </el-col>
      
      <el-form-item label="关键字">
        <el-tag
          :key="word"
          v-for="word in keywords"
          closable
          @close="handleKeywordsClose(word)"
          :disable-transitions="false"
          class="tag-keywords"
        >
          {{ word }}
        </el-tag>
        <el-input
          class="input-keywords"
          ref="inputKeywords"
          size="small"
          v-model="inputKeywordsValue"
          v-if="inputVisible"
          @blur="handleKeywordsConfirm()"
        ></el-input>
        <el-button v-else @click="showKeywordsInput()">新增关键字</el-button>
      </el-form-item>
       <el-col :span="12">
      <el-form-item label="残疾类型">
        <el-select
          v-model="deformity"
          value-key="id"
          placeholder="选择残疾类型"
        >
          <el-option
            v-for="item in deformityOptions"
            :key="item.id"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
       </el-col>
        <el-col :span="12">
      <el-form-item label="作者姓名">
        <el-input v-model="resource.author"></el-input>
      </el-form-item>
        </el-col>
         <el-col :span="12">
      <el-form-item label="作者邮箱">
        <el-input v-model="resource.authorEmail"></el-input>
      </el-form-item>
         </el-col>
       <el-col :span="12">
      <el-form-item label="作者单位">
        <el-input v-model="resource.authorCompany"></el-input>
      </el-form-item>
       </el-col>
    </el-form>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      resource: {
        title: "",
        deformityPKID: "",
        author: "",
        authorEmail: "",
        authorCompany: "",
        authorPhone: "",
        keywords: "key1,key2,key3",
        description: "",
        resourcePath: "",
      },

      inputVisible: false,
      keywords: [],
      inputKeywordsValue: "",
      deformity: { name: "请选择", id: 0, value: 0 },
    };
  },
  computed: {
    ...mapState({
      deformityOptions: (state) => state.deformity,
    }),
  },
  mounted() {
    this.bindKeywords();
  },

  methods: {
    bindKeywords() {
      this.keywords = this.resource.keywords.split(",");
    },

    handleKeywordsClose(word) {
      alert(this.keywords.indexOf(word));
      this.keywords.splice(this.keywords.indexOf(word), 1);
    },

    handleKeywordsConfirm() {
      this.keywords.push(this.inputKeywordsValue);
      this.resource.keywords = this.keywords.join(",");
      console.log(this.resource.keywords);
    },
    showKeywordsInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.inputKeywords.$refs.input.focus();
      });
    },
  },
};
</script>

<style scoped>
.tag-keywords {
}
.input-keywords {
  width: 90px;
}
</style>