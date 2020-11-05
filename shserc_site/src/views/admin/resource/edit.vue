<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>编辑新资源</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row :gutter="20" class="row">
      <el-col :span="14">
        <el-form :model="resource" ref="resourceForm" label-width="80px">
          <el-form-item label="资源名称">
            <el-input v-model="resource.title"></el-input>
          </el-form-item>
          <el-form-item label="上传资源">
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-change="handleChange"
              :file-list="fileList"
            >
              <el-button  type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">
                <!-- 只能上传jpg/png文件，且不超过500kb -->
              </div>
            </el-upload>
          </el-form-item>
          <el-form-item label="资源描述">
            <el-input type="textarea" v-model="resource.description"></el-input>
          </el-form-item>
          <el-form-item label="关键字">
            <el-input
              class="input-keywords"
              ref="inputKeywords"
              size="small"
              v-model="inputKeywordsValue"
              v-if="inputVisible"
              @blur="handleKeywordsConfirm()"
            ></el-input>
            <el-button class="btn-keywords" v-else @click="showKeywordsInput()"
              >新增关键字</el-button
            >
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
          </el-form-item>
          <el-form-item label="残疾类型">
            <el-select
              v-model="deformity"
              value-key="id"
              placeholder="选择残疾类型"
              style="width: 100%"
            >
              <el-option
                v-for="item in deformityOptions"
                :key="item.id"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="作者姓名">
            <el-input v-model="resource.author"></el-input>
          </el-form-item>
          <el-form-item label="作者邮箱">
            <el-input v-model="resource.authorEmail"></el-input>
          </el-form-item>
          <el-form-item label="作者单位">
            <el-input v-model="resource.authorCompany"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" >提交</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="10">
        <el-upload action="#" list-type="picture-card" :auto-upload="false">
          <i
            slot="default"
            class="el-icon-upload"
            style="font-size: 70px; margin-top: 80px"
            ><br />
            <div style="font-size: 22px">点击上传封面</div></i
          >
          <div slot="file" slot-scope="{ file }">
            <img
              class="el-upload-list__item-thumbnail"
              :src="file.url"
              alt=""
            />
            <span class="el-upload-list__item-actions">
              <span
                class="el-upload-list__item-preview"
                @click="handlePictureCardPreview(file)"
              >
                <i class="el-icon-zoom-in"></i>
              </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleDownload(file)"
              >
                <i class="el-icon-download"></i>
              </span>
              <span
                v-if="!disabled"
                class="el-upload-list__item-delete"
                @click="handleRemove(file)"
              >
                <i class="el-icon-delete"></i>
              </span>
            </span>
          </div>
        </el-upload>
      </el-col>
    </el-row>
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

      //demo

       fileList: [{
          name: 'food.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }, {
          name: 'food2.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }]
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
      this.keywords.splice(this.keywords.indexOf(word), 1);
    },

    handleKeywordsConfirm() {
      const find = this.keywords.find((key) => key == this.inputKeywordsValue);
      if (!find && find != "") {
        this.keywords.push(this.inputKeywordsValue);
      }
      this.resource.keywords = this.keywords.join(",");
      this.inputKeywordsValue = "";
      this.inputVisible = false;
      console.log(this.resource.keywords);
    },
    showKeywordsInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.inputKeywords.$refs.input.focus();
      });
    },

    //demo
     handleChange(file, fileList) {
        this.fileList = fileList.slice(-3);
      }
  },
};
</script>

<style>
.row {
  margin: 20px;
}
/* .el-form-item__content{
  display:flex;
} */
.input-keywords {
  display: flex;
}
.btn-keywords {
  display: flex;
  width: 100%;
}

.el-upload--picture-card {
  width: 100%;
  height: 250px;
}

.upload-demo{
  display: flex;
}
</style>