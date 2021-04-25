<template>
  <div class="m_30_bottom">
    <el-row :gutter="10">
      <el-col :span="15">
        <!--   resource info start -->
        <div class="block_panel">
          <p class="panel_title">
            <span> 首页 > 浏览资源</span>
          </p>

          <div class="body">
            <div class="body res_details">
              <div v-if="resource.isVideo">
                <div class="title filetype">
                  <i class="el-icon-video-play link font30"></i>
                  {{ resource.title }}
                </div>
                <video-player
                  class="video-player vjs-custom-skin"
                  ref="videoPlayer"
                  :playsinline="true"
                  :options="
                    videoOptions(resource.resourcePath, resource.coverPath)
                  "
                ></video-player>
              </div>
              <div v-else>
                <div class="title filetype">
                  <i class="el-icon-document link font30"></i>
                  {{ resource.title }}
                </div>
                <div>
                  <img
                    :src="resource.coverPath"
                    @error="handleImageError()"
                    style="max-height: 600px; width: 100%"
                  />
                </div>
              </div>

              <div class="opbar">
                <div class="stars">
                  <b>点击评分：</b>
                  <el-rate v-model="value2" :colors="colors"> </el-rate>
                </div>
                <div class="buttons">
                  <el-button
                    size="larger"
                    type="danger"
                    id="favorite"
                    @click="handleFavorite()"
                  >
                    <i class="fa fa-download"></i> 收 藏
                  </el-button>
                  <el-button
                    size="larger"
                    type="primary"
                    id="favorite"
                    v-if="resource.isVideo"
                    @click="handleFavorite()"
                  >
                    <i class="fa fa-download"></i> 复制地址
                  </el-button>
                  <el-button
                    size="larger"
                    type="primary"
                    id="favorite"
                    @click="
                      handleDownload(resource.title, resource.resourcePath)
                    "
                    v-else
                  >
                    <i class="fa fa-download"></i> 下 载
                  </el-button>
                </div>
              </div>

              <div class="info">
                <div class="dzstyle">
                  <div class="boxes">
                    <div class="half left">
                      <div>
                        <p>共有 2 人参与了评价</p>
                      </div>
                    </div>
                    <div class="half right">
                      <el-progress
                        v-for="i in [0, 1, 2, 3, 4]"
                        :key="i"
                        :format="progressFormats[i]"
                        :text-inside="true"
                        :stroke-width="24"
                        :percentage="10"
                        class="m_10_bottom"
                        :color="progressColors[i]"
                      ></el-progress>
                    </div>
                  </div>
                  <div style="clear: both"></div>
                </div>

                <div class="board first">
                  <p>
                    <span>关 键 字： {{ resource.keywords }} </span>
                  </p>
                  <p>
                    <span>资源类型： {{ resource.resourceType }}</span
                    ><span>媒体格式： {{ resource.mediumType }}</span>
                  </p>
                  <p>
                    <span>上传日期： {{ resource.addTime }}</span
                    ><span>浏览次数： {{ resource.viewCount }} 次</span>
                  </p>

                  <p>
                    <span>收藏次数： {{ resource.favoriteCount }}</span
                    ><span>下载次数： {{ resource.downloadCount }} 次</span>
                  </p>

                  <p></p>
                </div>
                <div class="board">
                  <p>
                    {{ resource.description }}
                  </p>
                  <h3>详细属性</h3>
                </div>
                <div class="board">
                  <p>
                    <span>残疾类别： {{ resource.deformity }}</span
                    ><span>学　　段： {{ resource.stage }}</span>
                  </p>
                  <p>
                    <span>领　　域： {{ resource.domain }}</span
                    ><span>年　　级： {{ resource.grade }}</span>
                  </p>
                  <p>
                    <span>安置形式： {{ resource.learnFrom }}</span
                    ><span>学校类别： {{ resource.schoolType }}</span>
                  </p>
                  <p>
                    <span>学　　科： {{ resource.subject }}</span
                    ><span>作　　者： {{ resource.author }}</span>
                  </p>
                  <p>
                    <span>单　　位： {{ resource.authorCompany }}</span
                    ><span>地　　址： {{ resource.author }}</span>
                  </p>
                </div>
              </div>

              <div class="comment">
                <div class="comment_input">
                  <h3>您的评论</h3>
                  <textarea></textarea>
                  <div class="comment_num">240</div>
                  <el-button type="info" disabled="disabled" v-if="!isLogin">
                    登录后可发表评论
                  </el-button>
                  <el-button type="primary" v-if="isLogin">
                    提交评论
                  </el-button>
                </div>

                <div class="comment_count">
                  <el-tag type="danger" class="font14"
                    ><i class="el-icon-info"></i> 共
                    <strong>{{ resource.commentCount }}</strong> 条评论</el-tag
                  >
                </div>

                <div class="comment_list">
                  <CommentList :source="comments"></CommentList>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- resource  info  end -->
      </el-col>

      <el-col :span="8" :offset="1">
        <!--  relative  resource list start -->
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-user-solid"></i> 相关资源</span>
          </p>

          <div class="body">
            <TopList></TopList>
          </div>
        </div>
        <!--  relative  resource list end -->

        <!--  relative  resource list start -->
        <div class="block_panel">
          <p class="panel_title">
            <span><i class="el-icon-user-solid"></i> 热门资源</span>
          </p>
          <div class="body">
            <TopList></TopList>
          </div>
        </div>
        <!--  relative  resource list end -->
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { AUDIT_SUCCESS_ID } from "@/app/static/type";
import { messages } from "@/app/static/message.js";
import CommentList from "@/app/views/site/comment/components/List/index";
import TopList from "@/app/views/site/resource/components/TopList/index";
import { resourceModel, videoOptions } from "@/app/models/resource";
import { selectParam as commentSelectParam } from "@/app/models/comment";
import { appEnum } from "@/app/static/enum";
import { list as resourceList, info, favorite } from "@/app/api/resource";
import { list as commentList, edit as commentEdit } from "@/app/api/comment";
import { downloadFile } from "@/static/file";

export default {
  components: { CommentList, TopList },

  computed: {
    ...mapState({
      isLogin: (state) => state.app.isAuth,
      account: (state) => state.app.account,
    }),
  },

  data() {
    return {
      value1: null,
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      commentSelectParam: commentSelectParam,
      resource: resourceModel,
      appEnum: appEnum,
      progressColors: ["#409eff", "#67c23a", "#e6a23c", "#f56c6c", "#6f7ad3"],
      progressIndex: 0,
      progressFormats: [],
      relativeResources: [],
      topVisitResources: [],
      comments: [],
    };
  },

  mounted() {
    this.loadResourceInfo();
    this.loadTopVisitResourceList();
    this.loadTopVisitResourceList();
    this.loadComments();

    let totalCount = 8;
    this.progressFormats.push(function (percentage) {
      return "5分：" + 1 + "人";
    });
    this.progressFormats.push(function (percentage) {
      return "4分：" + 1 + "人";
    });
    this.progressFormats.push(function (percentage) {
      return "3分：" + 0 + "人";
    });
    this.progressFormats.push(function (percentage) {
      return "2分：" + 0 + "人";
    });
    this.progressFormats.push(function (percentage) {
      return "1分：" + 0 + "人";
    });
  },

  methods: {
    loadResourceInfo() {
      let id = this.$router.currentRoute.params.id;
      info(id).then((res) => {
        if (res) {
          this.resource = JSON.parse(res.data);
          this.resource.isVideo =
            this.resource.fileExtName.indexOf(appEnum.fileExtNames.video) >= 0;
        }
      });
    },

    loadTopVisitResourceList() {},

    loadRelativeResourceList() {},

    loadComments(current) {
      let selectParam = this.commentSelectParam;
      selectParam.resourceId = this.$router.currentRoute.params.id;
      selectParam.stateId = AUDIT_SUCCESS_ID;
      if (current) {
        selectParam.current = current;
      }
      commentList(this.commentSelectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          selectParam.total = data.total;
          this.comments = data.listData ? data.listData : [];
        }
      });
    },

    handleImageError() {
      let img = event.srcElement;
      img.src = CDN.DEFAULT_COVER;
      img.onerror = null;
    },

    handleScore() {
      if (!this.isLogin) {
        this.$notification.error({ message: messages.MUST_LOGIN_FIRST });
      }
    },

    handleFavorite() {
      if (!this.isLogin) {
        this.$notification.error({ message: messages.MUST_LOGIN_FIRST });
      } else {
        let resourceId = this.$router.currentRoute.params.id;
        favorite(resourceId).then((res) => {
          this.$notification.success({ message: messages.SUCCESS });
        });
      }
    },

    handleSendComment() {
      let resourceId = this.$router.currentRoute.params.id;
      commentEdit(resourceId).then((res) => {
        if (res) {
        }
      });
    },

    handleDownload(fileName, path) {
      if (!this.isLogin) {
        this.$notification.error({ message: messages.MUST_LOGIN_FIRST });
      } else {
        downloadFile(fileName, path);
      }
    },
  },
};
</script>

<style scoped>
</style>