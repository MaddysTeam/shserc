<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>编辑众筹资源</el-breadcrumb-item>
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

           <el-form-item label="资源类型" prop="createType" class="text_align_left" >
            <el-radio-group v-model="resource.createTypeId"  @change="createTypeSelectChange">
               <el-radio :label="10001" :key="10001" >原创</el-radio>
                <el-radio :label="10002" :key="10002">推荐</el-radio>
              </el-radio-group>
               <el-input v-model="resource.sourceUrl" placeholder="资源出处" v-show="isShowSourceUrl"></el-input>
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
            <el-col :span="24">
              <el-form-item label="领域" prop="domainId">
                <el-select
                  @change="domainSelectChanged"
                  v-model="resource.domain"
                  value-key="id"
                  placeholder="不分领域"
                   class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="资源类型" prop="resourceTypeId">
                <el-select
                  @change="resourceTypeSelectChanged"
                  v-model="resource.resourceType"
                  value-key="id"
                  placeholder="不分资源类型"
                   class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="学段" prop="stageId">
                <el-select
                  @change="stageSelectChanged"
                  v-model="resource.stage"
                  value-key="id"
                  placeholder="不分学段"
                   class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="年级" prop="gradeId">
                <el-select
                  @change="gradeSelectChanged"
                  v-model="resource.grade"
                  value-key="id"
                  placeholder="不分年级"
                  class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="学科" prop="subjectId">
                <el-select
                  @change="subjectSelectChanged"
                  v-model="resource.subject"
                  value-key="id"
                  placeholder="不分学科"
                  class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="学校类型" prop="schoolTypeId">
                <el-select
                  @change="schoolTypeSelectChanged"
                  v-model="resource.schoolType"
                  value-key="id"
                  placeholder="不分学校类型"
                  class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="残疾类型" prop="deformityId">
                <el-select
                  @change="deformitySelectChanged"
                  v-model="resource.deformity"
                  value-key="id"
                  placeholder="不分残疾类型"
                  class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="安置类型" prop="learnfromId">
                <el-select
                  @change="learnFromSelectChanged"
                  v-model="resource.learnFrom"
                  value-key="id"
                  placeholder="不分安置形式"
                  class="fullWidth"
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
            <el-col :span="24">
              <el-form-item label="作者电话">
                <el-input v-model="resource.authorPhone"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
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

        <!-- cover upload start -->
        <el-upload
          accept=".jpg, .bmp,.jpeg,.gif,.png"
          action="#"
          :on-success="uploadResourceCoverSuccess"
          :http-request="uploadResourceCover"
          :on-change="uploadCoverOnChange"
          :auto-upload="true"
          :show-file-list="false"
        >
          <div
            v-if="resource.coverPath && resource.coverPath != ''"
            slot="default"
          >
            <div >
              <img
                :src="resource.coverPath"
                class="cover-path"
              />
              <span class="el-upload-list__item-actions">
                <el-button class="el-button--danger fullWidth">
                   <i class="el-icon-upload2 font20"></i> 
                </el-button>
              </span>
            </div>
          </div>
          <i
            v-else
            slot="default"
            class="el-icon-upload"
            style="font-size: 70px; margin-top: 80px"
            ><br />
            <div class="font20">点击上传封面</div>
          </i>
        </el-upload>
        <!-- cover upload end -->

        <div>
          <el-button
            class="el-button--primary"
            v-if="false"
            @click="handlePictureCardPreview(resource)"
          >
            <i class="el-icon-zoom-in"></i> 图片预览
          </el-button>
        </div>
        <el-dialog :visible.sync="dialogVisible">
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
import { edit, info } from "@/app/api/croResource";
import { uploadFile } from "@/app/api/file";
import { resourceModel } from "@/app/models/croResource";
import { getRelevantByRelevantId } from "@/app/utils/dictHelper";
import { deepCopy } from "@/app/utils/objectHelper";

export default {
  data() {
    return {
      resource: deepCopy(resourceModel),

      keywords: [],
      inputKeywordsValue: "",
      fileList: [],
      coverList: [],

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
            let resourceTypes = getRelevantByRelevantId(domainId, this.dict);
            this.$store.dispatch("app/changeResourceTypes", resourceTypes);
          }
          let stageId = this.resource.stageId;
          if (stageId > 0) {
            // load cascade data
            let grades = getRelevantByRelevantId(stageId, this.dict);

            this.$store.dispatch("app/changeGrades", grades);
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
      this.resource.resourcePath = "";
      this.resource.fileName = "";;
      this.resource.fileExtName = "";;
      this.resource.fileSize = "";
      uploadFile(options);
    },

    uploadResourceHandleChange(file, fileList) {
      // var fileList = this.fileList;
      // if (fileList.length > 0) {
      //   fileList = [fileList[fileList.length - 1]];
      // }
      // this.fileList = fileList;
    },

    uploadResourceHandleExceed() {
      Notification.error({ message: messages.FILE_UPLOAD_COUNT_ALLOWED });
    },

    uploadResourceError(error) {
      console.log(error);
    },

    uploadResourceSuccess(response, file, fileList) {
      var data = JSON.parse(response.data);
      this.resource.resourcePath = data.filePath;
      this.resource.fileName = data.fileName;
      this.resource.fileExtName = data.fileExtName;
      this.resource.fileSize = data.fileSize;
    },

    uploadResourceOnRemoveTxt() {},

    uploadResourceOnBeforeUpload() {},

    // upload resource cover

    uploadResourceCover(options) {
      uploadFile(options);
    },


    uploadCoverOnChange(file, fileList) {
      let coverList = this.coverList;
      if (coverList.length > 0) {
        coverList = [coverList[coverList.length - 1]];
      }
      this.coverList = coverList;
    },

    uploadResourceCoverSuccess(response, file, fileList) {
      var data = JSON.parse(response.data);

      this.resource.coverPath = data.filePath;
      this.coverList.push(file);
    },

    handlePictureCardPreview(resource) {
      this.dialogImageUrl = resource.coverPath;
      this.dialogVisible = true;
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
      // relavent items
      let relavents = getRelevantByRelevantId(stageId, this.dict);
      this.$store.dispatch("app/changeGrades", relavents);
      // clean value of  grade dropdown
      this.resource.grade = relavents[0].name;
    },

    gradeSelectChanged(gradeId) {
      this.resource.gradeId = gradeId;
    },

    domainSelectChanged(domainId) {
      // set domain id in resource model
      this.resource.domainId = domainId;
      // relavent items
      let relavents = getRelevantByRelevantId(domainId, this.dict);
      // clean  value of resource type dropdown
      this.$store.dispatch("app/changeResourceTypes", relavents);
    },

    resourceTypeSelectChanged(resourceTypeId) {
      this.resource.resourceTypeId = resourceTypeId;
    },

    createTypeSelectChange(val){
        this.isShowSourceUrl=val==10002;
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

.el-form-item__label {
  font-size: 12px;
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
.el-upload-list--picture-card .el-upload-list__item {
  width: 100%;
  height: 300px;
}
.cover-path{
height: 300px; width: 100%;border: 1px dashed #a6a9ad;
}
</style>