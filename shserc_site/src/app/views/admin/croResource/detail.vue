<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源详细</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- audit start -->
    <audit
      @close="handleCloseAudit"
      :visible="dialogVisible"
      :model="auditModel"
    ></audit>
    <!-- audit end -->

    <!-- details start -->
    <el-container class="font12">
      <el-main>
        <div class="grid text_align_left">
          <div class="grid-item" >
            <div
              class="video"
              v-if="
                resource.fileExtName.indexOf(appEnum.fileExtNames.video) >= 0
              "
            >
              <video-player
                class="video-player vjs-custom-skin"
                ref="videoPlayer"
                :playsinline="true"
                :options="
                 videoOptions(resource.resourcePath, resource.coverPath)
                "
              ></video-player>
            </div>
            <div class="cover" v-else>
              <img :src="resource.coverPath" @error="handleImageError()"  style="max-height:300px"/>
            </div>
          </div>
          <div class="grid-item font14">
            <div class="font20">
              {{ resource.title }}
            </div>
            <div class="m_30_top font12">
              审核资源：
              <el-button
                @click="handleAudit(resource)"
                class="el-button--primary search"
              >
                <i class="el-icon-edit"></i> 审核
              </el-button>
            </div>
            <div class="m_30_top font12">
              资源下载：
              <a :href="resource.resourcePath"
                ><i class="el-icon-download font14"></i
              ></a>
            </div>
            <div class="m_30_top font12">
              上传时间：<i class="el-icon-time font14"></i> 2020-11-08
            </div>
          </div>

          <div class="grid-item">
            <hr />
            <div class="m_30_top">
              <i class="el-icon-platform font14"></i><strong>资源描述</strong>
            </div>
            <div class="m_30_top">
              {{ resource.description }}
            </div>
          </div>

          <div class="grid-item">
            <hr />
            <div class="m_30_top "><strong>资源信息</strong></div>

            <el-row class="m_30_top">
              <el-col :span="8">
                <el-row>
                  <el-col :span="12">资源名称：</el-col>
                  <el-col :span="12">{{ resource.title }}</el-col>
                </el-row>

                <el-row class="m_30_top">
                  <el-col :span="12">残疾类型：</el-col>
                  <el-col :span="12">{{ resource.deformity }}</el-col>
                </el-row>
              </el-col>

              <el-col :span="8" >
                <el-row >
                  <el-col :span="12">资源类型：</el-col>
                  <el-col :span="12">
                    {{ resource.resourceType }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row>
                  <el-col :span="12">媒体类型：</el-col>
                  <el-col :span="12"> {{ resource.mediumType }}</el-col>
                </el-row>
              </el-col>

              <el-col :span="8" >
                <el-row class="m_30_top">
                  <el-col :span="12">安置类型：</el-col>
                  <el-col :span="12">
                    {{ resource.learnFrom }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row class="m_30_top">
                  <el-col :span="12">学段：</el-col>
                  <el-col :span="12">
                    {{ resource.stage }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row class="m_30_top">
                  <el-col :span="12">年级：</el-col>
                  <el-col :span="12">
                    {{ resource.grade }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row class="m_30_top"> 
                  <el-col :span="12">学科：</el-col>
                  <el-col :span="12">
                    {{ resource.subject }}
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-main>
      <el-aside>
        <el-card>
          <div><strong>作者信息</strong></div>
          <div class="m_30_top text_align_left">
            姓名：{{ resource.author }}
          </div>
          <div class="m_30_top text_align_left">
            邮箱：{{ resource.authorEmail }}
          </div>
          <div class="m_30_top text_align_left">
            电话：{{ resource.authorPhone }}
          </div>
          <div class="m_30_top text_align_left">
            单位：{{ resource.authorCompany }}
          </div>
        </el-card>
      </el-aside>
    </el-container>
    <!-- audit end -->
  </div>
</template>

<script>
import { CDN } from "@/static/CDN";
import { resourceModel, videoOptions, auditModel } from "@/app/models/croResource";
import { info } from "@/app/api/croResource";
import { appEnum } from "@/app/static/enum";
import audit from "@/app/views/admin/croResource/audit";
import { mapState } from "vuex";

export default {
  components: {
    audit,
  },

  data() {
    return {
      resource: resourceModel,
      appEnum: appEnum,
      dialogVisible: false,
      auditModel: auditModel,
      videoOptions:videoOptions,
    };
  },

  computed: {
    ...mapState({
      deformities: (state) => state.app.deformity,
    }),
  },

  mounted() {
    this.getResource();
  },

  methods: {
    getResource() {
      let id = this.$router.currentRoute.params.id;
      info(id).then((res) => {
        this.resource = JSON.parse(res.data);
        console.log("--------------------------------------");
        console.log(this.resource.coverPath);
      });
    },

    handleAudit(model) {
      this.auditModel = {
        resourceId: model.id,
        resourceTitle: model.title,
        auditOpinion: model.auditOpinion,
        auditResult: model.stateId == 0, //TODO: save to store this.$store.state.app.
      };
      this.dialogVisible = true;
    },

    handleCloseAudit() {
      this.dialogVisible = !this.dialogVisible;
    },

    handleImageError(){
      let img=event.srcElement;
      img.src = CDN.DEFAULT_COVER;
	    img.onerror = null; //防止闪图
    }
  },
};
</script>

<style scoped>
.video {
  width: 500px;
}

.grid {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  flex-wrap: wrap;
}

.grid-item {
  padding: 30px;
  margin: 5px;
  border-radius: 4px;
  background: #fafafa;
  flex: 1;
  min-width: 500px;
  margin: 20px;
  height: 300px;
}

.cover img {
  width: 100%;
  transition: all 1.5s;
}
.el-card {
  min-width: 280px;
  margin-right: 20px;
  transition: all 0.5s;
  margin-top:38px;
}
</style>