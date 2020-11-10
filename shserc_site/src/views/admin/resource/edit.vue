<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>编辑新资源</el-breadcrumb-item>
    </el-breadcrumb>

    <el-row :gutter="20" class="row">
      <el-col :span="14">
        <el-form
          :model="resource"
          ref="resourceForm"
          label-width="80px"
          :rules="rules"
        >
          <el-form-item label="资源名称" prop="title">
            <el-input v-model="resource.title"></el-input>
          </el-form-item>
          <el-form-item label="上传资源">
            <el-upload
              class="upload-file"
              action="https://jsonplaceholder.typicode.com/posts/"
              :limit="1"
              :on-change="uploadResourceHandleChange"
              :file-list="fileList"
              :on-exceed="uploadResourceHandleExceed"
              :on-error="uploadResourceError"
              :on-success="uploadResourceSuccess"
              :on-remove="uploadResourceOnRemoveTxt"
              :before-upload="uploadResourceOnBeforeUpload"
              :http-request="uploadResource"
            >
              <el-button type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip"></div>
            </el-upload>
          </el-form-item>
          <el-form-item label="资源描述">
            <el-input type="textarea" v-model="resource.description"></el-input>
          </el-form-item>
          <el-form-item label="关键字" prop="keywords">
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
          <el-form-item label="残疾类型" prop="deformityId">
            <el-select
              @change="deformitySelectChanged"
              v-model="resource.deformityId"
              value-key="id"
              placeholder="选择残疾类型"
              style="width: 100%"
            >
              <el-option
                v-for="item in deformityOptions"
                :key="item.id"
                :label="item.name"
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
            <el-button type="primary" @click="handSubmit()">提交</el-button>
            <el-button type="info" @click="handCancel()">取消</el-button>
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
import { Notification } from "element-ui";
import { mapState } from "vuex";
import {
  validateRequired,
  validateLessThan50,
  validateSelectValue,
} from "@/static/validator";
import { messages } from "@/static/message";
import { edit, uploadFile, uploadCover, resource } from "@/api/resource";

export default {
  data() {
    return {
      resource: {
        id: 0,
        title: "",
        deformityId: "",
        author: "",
        authorEmail: "",
        authorCompany: "",
        authorPhone: "",
        keywords: null,
        description: "",
        resourcePath: "",
        fileName: "",
      },

      inputVisible: false,
      keywords: [],
      inputKeywordsValue: "",
      deformity: { name: "请选择", id: 0, value: 0 },
      fileList: [
        // {
        //   name: "food.jpeg",
        //   url:
        //     "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
        // },
      ],

      rules: {
        title: [
          {
            validator: (rule, value, callback) => {
              validateRequired(
                rule,
                value,
                callback,
                messages.RESOURCE_TITLE_NOT_NULL
              );
            },
            trigger: "blur",
          },
          {
            validator: (rule, value, callback) => {
              validateLessThan50(
                rule,
                value,
                callback,
                messages.RESOURCE_TITLE_NOT_ALLOWED_MORE_THAN_50
              );
            },
            trigger: "blur",
          },
        ],
        deformityId: {
          validator: (rule, value, callback) => {
            validateSelectValue(
              rule,
              value,
              callback,
              messages.RESOURCE_DEFORMITY_SELECT_NOT_NULL
            );
          },
          trigger: "change",
        },

        keywords: {
          validator: (rule, value, callback) => {
            validateRequired(
              rule,
              value,
              callback,
              messages.RESOURCE_KEYWORD_NOT_NULL
            );
          },
          trigger: "blur",
        },
      },
    };
  },
  computed: {
    ...mapState({
      deformityOptions: (state) => state.deformity,
    }),
  },
  mounted() {
    this.loadResource();
  },

  methods: {
    loadResource() {
      let id = this.$router.currentRoute.params.id;
      if (id) {
        resource(id).then((res) => {
          this.resource = JSON.parse(res.data);
          this.fileList.push({
            name: this.resource.fileName,
            url: this.resource.resourcePath,
          });
          console.log(this.resource.keywords);
          this.bindKeywords();
        });
      }
    },

    bindKeywords() {
      if (this.resource.keywords) {
        this.keywords = this.resource.keywords.split(",");
      }
    },

    handleKeywordsClose(word) {
      this.keywords.splice(this.keywords.indexOf(word), 1);
      this.handleKeywordsConfirm();
    },

    handleKeywordsConfirm() {
      const find = this.keywords.find((key) => key == this.inputKeywordsValue);
      const isNotEmpty= !find && find != "" &&  this.inputKeywordsValue!=null && this.inputKeywordsValue.trim()!="";
      if (isNotEmpty) {
        this.keywords.push(this.inputKeywordsValue);
        this.resource.keywords = this.keywords.join(",");
      } else {
        this.resource.keywords = null;
      }   

      this.inputKeywordsValue = null;
      this.inputVisible = false;
    },

    showKeywordsInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.inputKeywords.$refs.input.focus();
      });
    },

    //upload resource file

    uploadResource(options) {
      uploadFile(options);
    },

    uploadResourceHandleChange(file, fileList) {},

    uploadResourceHandleExceed() {
      Notification.error({ message: "只能上传一个文件" });
    },

    uploadResourceError(error) {
      console.log(error);
    },

    uploadResourceSuccess(response, file, fileList) {
      var data = JSON.parse(response.data);
      console.log(data);
      this.resource.resourcePath = data.filePath;
      this.resource.fileName = data.fileName;
    },

    uploadResourceOnRemoveTxt() {},

    uploadResourceOnBeforeUpload() {},

    deformitySelectChanged(deformityId) {
      this.resource.deformityPKID = deformityId;
    },

    handSubmit() {
      let _this = this;
      _this.$refs["resourceForm"].validate((isValid) => {
        if (isValid) {
          edit(this.resource).then((res) => {
            if (res && res.data) {
              alert("success!");
            }
          });
        }
      });
    },

    handCancel() {
      this.$router.push("/admin/resource/list");
    },
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

.upload-file {
  display: flex;
}
</style>