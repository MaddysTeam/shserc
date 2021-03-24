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
    <el-container>
      <el-main>
        <div class="grid text_align_left">
          <div class="grid-item">
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
              <img :src="resource.coverPath" />
            </div>
          </div>
          <div class="grid-item font14">
            <div class="font20">
              {{ resource.title }}
            </div>
            <div class="m_30_top">
              审核资源：
              <el-button
                @click="handAudit(resource)"
                class="el-button--primary search"
              >
                <i class="el-icon-edit"></i> 查询
              </el-button>
              <!-- <el-switch
                v-model="resource.auditResult"
                active-color="#13ce66"
                inactive-color="#ff4949"
                @change="handleAudit"
              ></el-switch> -->
            </div>
            <div class="m_30_top">
              资源下载：
              <a :href="resource.resourcePath"
                ><i class="el-icon-download font30"></i
              ></a>
            </div>
            <div class="m_30_top">
              上传时间：<i class="el-icon-time font30"></i> 2020-11-08
            </div>
          </div>

          <div class="grid-item">
            <hr />
            <div class="font20 m_30_top">
              <i class="el-icon-platform font20"></i><strong>资源描述</strong>
            </div>
            <div class="m_30_top">
              {{ resource.description }}
            </div>
          </div>

          <div class="grid-item">
            <hr />
            <div class="font20 m_30_top"><strong>资源信息</strong></div>

            <el-row class="m_30_top">
              <el-col :span="8">
                <el-row>
                  <el-col :span="12" >资源名称：</el-col>
                  <el-col :span="12" >{{
                    resource.title
                  }}</el-col>
                </el-row>

                <el-row class="m_20_top">
                  <el-col :span="12" >残疾类型：</el-col>
                  <el-col :span="12">{{
                    resource.deformity
                  }}</el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row>
                  <el-col :span="12">资源类型：</el-col>
                  <el-col :span="12">
                    {{ resource.resourceType }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row>
                  <el-col :span="12" >媒体类型：</el-col>
                  <el-col :span="12" >
                    {{ resource.mediumType }}</el-col
                  >
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row>
                  <el-col :span="12" >安置类型：</el-col>
                  <el-col :span="12">
                    {{ resource.learnFrom }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row>
                  <el-col :span="12" >学段：</el-col>
                  <el-col :span="12" >
                    {{ resource.stage }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row>
                  <el-col :span="12" >年级：</el-col>
                  <el-col :span="12" >
                    {{ resource.grade }}
                  </el-col>
                </el-row>
              </el-col>

              <el-col :span="8">
                <el-row>
                  <el-col :span="12" >学科：</el-col>
                  <el-col :span="12" >
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
          <div>作者信息</div>
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
import { resourceModel, videoOptions, auditModel } from "@/app/models/resource";
import { info } from "@/app/api/resource";
import { appEnum } from "@/app/static/enum";
import audit from "@/app/views/admin/resource/audit";
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

      // playerOptions: {
      //   playbackRates: [0.5, 1.0, 1.5, 2.0], //可选择的播放速度
      //   autoplay: false, //如果true,浏览器准备好时开始回放。
      //   muted: false, // 默认情况下将会消除任何音频。
      //   loop: false, // 视频一结束就重新开始。
      //   preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
      //   language: "zh-CN",
      //   aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
      //   fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
      //   sources: [
      //     {
      //       type: "",
      //       src:  this.resource.resourcePath
      //         //"http://shlll.s.wcsapi.biz.matocloud.com/upload201909261929354303.mp4", //url地址
      //     },
      //   ],
      //   poster: this.resource.coverPath , //你的封面地址
      //   // width: document.documentElement.clientWidth,
      //   notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
      //   controlBar: {
      //     timeDivider: true, //当前时间和持续时间的分隔符
      //     durationDisplay: true, //显示持续时间
      //     remainingTimeDisplay: false, //是否显示剩余时间功能
      //     fullscreenToggle: true, //全屏按钮
      //   }
      // }
    };
  },

  computed: {
    ...mapState({
      deformities: (state) => state.app.deformity,
    }),
  },

  mounted() {
    console.log(this.deformities);
    this.getResource();
  },

  methods: {
    getResource() {
      let id = this.$router.currentRoute.params.id;
      info(id).then((res) => {
        this.resource = JSON.parse(res.data);
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
  border-radius: 8px;
  background: #fafafa;
  flex: 1;
  min-width: 500px;
  margin: 20px;
}

.cover img {
  width: 100%;
  max-width: 600px;
  transition: all 0.5s;
}
.el-card {
  min-width: 280px;
  margin-right: 20px;
  transition: all 0.5s;
}
</style>