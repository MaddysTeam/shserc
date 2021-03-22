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
          <!-- resource start -->

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

          <!-- resource end -->

          <!-- dropdown start -->

          <el-row>
            <el-col :span="12">
              <el-form-item label="领域" prop="domainId">
                <el-select
                  @change="domainSelectChanged"
                  v-model="resource.domain"
                  value-key="id"
                  placeholder="不分领域"
                  style="width: 100%"
                >
                  <el-option
                    v-for="item in domainOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="资源类型" prop="resourceTypeId">
                <el-select
                  @change="resourceTypeSelectChanged"
                  v-model="resource.resourceType"
                  value-key="id"
                  placeholder="不分资源类型"
                  style="width: 100%"
                >
                  <el-option
                    v-for="item in typeOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="学段" prop="stageId">
                <el-select
                  @change="stageSelectChanged"
                  v-model="resource.stage"
                  value-key="id"
                  placeholder="不分学段"
                  style="width: 100%"
                >
                  <el-option
                    v-for="item in stageOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="年级" prop="gradeId">
                <el-select
                  @change="gradeSelectChanged"
                  v-model="resource.grade"
                  value-key="id"
                  placeholder="不分年级"
                  style="width: 100%"
                >
                  <el-option
                    v-for="item in gradeOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="学科" prop="subjectId">
                <el-select
                  @change="subjectSelectChanged"
                  v-model="resource.subject"
                  value-key="id"
                  placeholder="不分学科"
                  style="width: 100%"
                >
                  <el-option
                    v-for="item in subjectOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="学校类型" prop="schoolTypeId">
                <el-select
                  @change="schoolTypeSelectChanged"
                  v-model="resource.schoolType"
                  value-key="id"
                  placeholder="不分学校类型"
                  style="width: 100%"
                >
                  <el-option
                    v-for="item in schoolTypeOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="残疾类型" prop="deformityId">
                <el-select
                  @change="deformitySelectChanged"
                  v-model="resource.deformity"
                  value-key="id"
                  placeholder="不分残疾类型"
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
            </el-col>
            <el-col :span="12">
              <el-form-item label="安置类型" prop="learnfromId">
                <el-select
                  @change="learnFromSelectChanged"
                  v-model="resource.learnFrom"
                  value-key="id"
                  placeholder="不分安置形式"
                  style="width: 100%"
                >
                  <el-option
                    v-for="item in learnFromOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- dropdown end -->

          <!-- author info start -->

          <el-form-item label="作者姓名">
            <el-input v-model="resource.author"></el-input>
          </el-form-item>
          <el-form-item label="作者单位" prop="authorCompany">
            <el-input v-model="resource.authorCompany"></el-input>
          </el-form-item>
          <el-row>
            <el-col :span="12">
              <el-form-item label="作者电话">
                <el-input v-model="resource.authorPhone"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="作者邮箱">
                <el-input v-model="resource.authorEmail"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <!-- author info end -->

          <el-form-item>
            <el-button type="primary" @click="handSubmit()">提交</el-button>
            <el-button type="info" @click="handCancel()">取消</el-button>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="10">
        <el-upload
          action="#"
          list-type="picture-card"
          :limit="1"
          :on-preview="handlePictureCardPreview"
          :on-exceed="uploadResourceCoverHandleExceed"
          :on-success="uploadResourceCoverSuccess"
          :http-request="uploadResourceCover"
          :auto-upload="true"
        >
          <i
            slot="default"
            class="el-icon-upload"
            style="font-size: 70px; margin-top: 80px"
            ><br />
            <div style="font-size: 22px">点击上传封面</div></i
          >

          <!-- <div slot="file" slot-scope="{ file }">
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
          </div> -->
        </el-upload>
        <el-dialog :visible.sync="dialogVisible" size="tiny">
          <img width="100%" :src="dialogImageUrl" alt="" />
        </el-dialog>
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
import { messages } from "@/app/static/message";
import { edit, info } from "@/app/api/resource";
import { uploadFile } from "@/app/api/file";
import { resourceModel } from "@/app/models/resource";
import { getRelevantByRelevantId } from "@/app/utils/dictHelper";
import { deepCopy } from "@/app/utils/objectHelper";

export default {
  data() {
    return {
      resource: deepCopy(resourceModel),

      keywords: [],
      inputKeywordsValue: "",
      fileList: [],

      inputVisible: false,
      dialogVisible: false,
      dialogImageUrl: "",
      disabled: false,

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

        // TODO: will remove later
        // deformityId: {
        //   validator: (rule, value, callback) => {
        //     validateSelectValue(
        //       rule,
        //       value,
        //       callback,
        //       messages.RESOURCE_DEFORMITY_SELECT_NOT_NULL
        //     );
        //   },
        //   trigger: "change",
        // },

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

        authorCompany: {
          validator: (rule, value, callback) => {
            validateRequired(
              rule,
              value,
              callback,
              messages.RESOURCE_AUTHOR_COMPANY_NOT_NULL
            );
          },
          trigger: "blur",
        },
      },
    };
  },
  computed: {
    ...mapState({
      dict: (state) => state.app.dict,
      deformityOptions: (state) => state.app.deformity,
      domainOptions: (state) => state.app.resourceDomains,
      typeOptions: (state) => state.app.resourceTypes,
      stageOptions: (state) => state.app.stages,
      gradeOptions: (state) => state.app.grades,
      subjectOptions: (state) => state.app.subjects,
      schoolTypeOptions: (state) => state.app.schoolTypes,
      learnFromOptions: (state) => state.app.learnFrom,
    }),
  },
  mounted() {
    this.loadResource();
  },

  methods: {
    loadResource() {
      let id = this.$router.currentRoute.params.id;
      if (id) {
        info(id).then((res) => {
          this.resource = JSON.parse(res.data);
          console.log(this.resource);
          this.fileList.push({
            name: this.resource.fileName,
            url: this.resource.resourcePath,
          });

          //fix bugs for cascade data  in edit condition
          let domainId = this.resource.domainId;
          if (domainId > 0) {
            // load cascade data 
            this.typeOptions = getRelevantByRelevantId(domainId, this.dict);
          }
          let stageId=this.resource.stageId;
          if (stageId > 0) {
            // load cascade data 
            this.gradeOptions = getRelevantByRelevantId(stageId, this.dict);
          }

          //bind key words
          this.bindKeywords();
        });
      }
    },

    // keywords

    bindKeywords() {
      if (this.resource.keywords) {
        this.keywords = this.resource.keywords.split(",");
      }
    },

    handleKeywordsClose(word) {
      this.keywords.splice(this.keywords.indexOf(word), 1);
      this.resource.keywords = this.keywords.join(",");
    },

    handleKeywordsConfirm() {
      // forbidden input duplicate keywords or empty keywords
      const find = this.keywords.find((key) => key == this.inputKeywordsValue);
      if (
        this.inputKeywordsValue &&
        this.inputKeywordsValue != "" &&
        !find &&
        find != ""
      ) {
        this.keywords.push(this.inputKeywordsValue);
      }
      this.resource.keywords = this.keywords.join(",");
      this.inputKeywordsValue = "";
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

    uploadResourceHandleChange(file, fileList) {
      if (fileList.length > 0) {
        //TODO: will force upload  one file which will replace previous one
      }
    },

    uploadResourceHandleExceed() {
      Notification.error({ message: messages.FILE_UPLOAD_COUNT_ALLOWED });
    },

    uploadResourceError(error) {
      console.log(error);
    },

    uploadResourceSuccess(response, file, fileList) {
      var data = JSON.parse(response.data);
      console.log(data);
      this.resource.resourcePath = data.filePath;
      this.resource.fileName = data.fileName;
      this.resource.fileExtName = data.fileExtName;
      this.resource.fileSize = data.fileSize;
    },

    uploadResourceOnRemoveTxt() {},

    uploadResourceOnBeforeUpload() {},

    uploadResourceCover(options) {
      uploadFile(options);
    },

    uploadResourceCoverHandleExceed() {
      Notification.error({ message: messages.FILE_UPLOAD_COUNT_ALLOWED });
    },

    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    uploadResourceCoverSuccess(response, file, fileList) {
      var data = JSON.parse(response.data);
      this.resource.coverPath = data.filePath;
    },

    // select select changed

    deformitySelectChanged(deformityId) {
      this.resource.deformityId = deformityId;
    },

    learnFromSelectChanged(learnFromId) {
      this.resource.learnFromId = learnFromId;
    },

    schoolTypeSelectChanged(schoolTypeId) {
      this.resource.schoolTypeId = schoolTypeId;
    },

    subjectSelectChanged(subjectId) {
      this.resource.subjectId = subjectId;
    },

    stageSelectChanged(stageId) {
      this.resource.stageId = stageId;
      // clean value of  grade dropdown
      this.resource.grade = "";
      // relavent items
      let relavents = getRelevantByRelevantId(stageId, this.dict);
      this.$store.dispatch("app/changeGrades", relavents);
    },

    gradeSelectChanged(gradeId) {
      this.resource.gradeId = gradeId;
    },

    domainSelectChanged(domainId) {
      // set domain id in resource model
      this.resource.domainId = domainId;
      // clean  value of resource type dropdown
      this.resource.resourceType = "";
      // relavent items
      let relavents = getRelevantByRelevantId(domainId, this.dict);
      this.$store.dispatch("app/changeResourceTypes", relavents);
    },

    resourceTypeSelectChanged(resourceTypeId) {
      this.resource.resourceTypeId = resourceTypeId;
    },

    // submit

    handSubmit() {
      let _this = this;
      _this.$refs["resourceForm"].validate((isValid) => {
        if (isValid) {
          edit(this.resource).then((res) => {
            this.cleanForm();
            this.redirectToList();
          });
        }
      });
    },

    handCancel() {
      this.cleanForm();
      this.redirectToList();
    },

    redirectToList() {
      this.$router.push("/admin/resource/list");
    },

    cleanForm() {
      this.resource = deepCopy(resourceModel);
    },
  },
};
</script>

<style>
.row {
  margin: 20px;
}

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

.el-upload-list {
  width: 70%;
}
</style>