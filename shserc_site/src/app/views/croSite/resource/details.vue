<template>
  <div class="m_30_bottom">
    <el-row :gutter="10">
      <el-col :span="15">
        <!--   resource info start -->
        <div class="block_panel">
          <!-- <p class="green_panel_title">
            <span><el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>资源详细</el-breadcrumb-item>
                  </el-breadcrumb></span>
          </p> -->

          <div class="body">
            <div class="body res_details">
              <div v-if="resource.isVideo">
                <div class="title filetype">
                  <i class="el-icon-video-play link font30 green"></i>
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
                  <i class="el-icon-document link font30 green"></i>
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
                <div class="buttons">
                  <el-button
                    size="larger"
                    type="danger"
                    id="favorite"
                    @click="handleFavorite()"
                  >
                    <i class="fa fa-download"></i>
                    <span v-if="isFavorite">取消收藏</span>
                    <span v-else>收 藏</span>
                  </el-button>

                  <el-button
                    size="larger"
                    type="primary"
                    id="favorite"
                    @click="
                      handleDownload(resource.title, resource.resourcePath)
                    "
                  >
                    <i class="fa fa-download"></i> 下 载
                  </el-button>
                </div>

                <div class="stars">
                  <b>点击评分：</b>
                  <el-rate
                    v-model="starScore"
                    :colors="colors"
                    @change="handleStar()"
                  >
                  </el-rate>
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
                        type="line"
                        v-for="scoreObj in starScores"
                        :key="scoreObj.score"
                        :format="scoreObj.format"
                        :text-inside="true"
                        :stroke-width="24"
                        :percentage="scoreObj.percentage"
                        class="m_10_bottom"
                        :color="progressColors[scoreObj.score - 1]"
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

                  <el-form ref="commentForm" :model="commentForm">
                    <textarea v-model="commentForm.content"></textarea>
                  </el-form>

                  <div class="comment_num">240</div>
                  <el-button type="info" disabled="disabled" v-if="!isLogin">
                    登录后可发表评论
                  </el-button>
                  <el-button
                    type="primary"
                    v-if="isLogin"
                    @click="handleSendComment()"
                  >
                    提交评论
                  </el-button>
                </div>

                <div class="comment_count">
                  <el-tag type="danger" class="font14"
                    ><i class="el-icon-info"></i> 共
                    <strong>{{ comments.length }}</strong> 条评论</el-tag
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
          <p class="green_panel_title">
            <span><i class="el-icon-user-solid"></i> 相关资源</span>
          </p>

          <div class="body">
            <TopList></TopList>
          </div>
        </div>
        <!--  relative  resource list end -->

        <!--  hot  resource list start -->
        <div class="block_panel">
          <p class="green_panel_title">
            <span><i class="el-icon-user-solid"></i> 热门资源</span>
          </p>
          <div class="body">
            <TopList></TopList>
          </div>
        </div>
        <!--  hot  resource list end -->

        <!--  relative  resource list start -->
        <div class="block_panel">
          <p class="green_panel_title">
            <span><i class="el-icon-user-solid"></i> 最新资源</span>
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
import CommentList from "@/app/views/croSite/comment/components/List/index";
import TopList from "@/app/views/croSite/resource/components/TopList/index";
import {
  resourceModel,
  videoOptions,
  starScores,
} from "@/app/models/croResource";
import {
  selectParam as commentSelectParam,
  commentModel,
} from "@/app/models/comment";
import { appEnum } from "@/app/static/enum";
import {
  list as resourceList,
  info,
  favorite,
  star,
  listStarScores,
  download,
  addViewCount,
} from "@/app/api/croResource";
import { list as commentList, edit as commentEdit } from "@/app/api/croComment";
import { myFavoriteList } from "@/app/api/croMy";
import { downloadFile } from "@/static/file";
import { deepCopy } from "@/app/utils/objectHelper";

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
      starScore: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
      commentSelectParam: commentSelectParam,
      commentModel: commentModel,
      resource: resourceModel,
      videoOptions: videoOptions,
      appEnum: appEnum,

      progressColors: ["#409eff", "#67c23a", "#e6a23c", "#f56c6c", "#6f7ad3"],
      progressIndex: 0,
      progressFormats: [],
      starScores: [],
      starTotal: 1,

      relativeResources: [],
      topVisitResources: [],
      comments: [],
      isFavorite: false,
      commentForm: { content: "" },
    };
  },

  mounted() {
    let _this = this;
    this.starScores = [
      {
        score: 1,
        count: 0,
        percentage: 10,
        format: function (percentage) {
          return "1分：" + _this.starScores[0].count + "人";
        },
      },
      {
        score: 2,
        count: 0,
        percentage: 10,
        format: function (percentage) {
          return "2分：" + _this.starScores[1].count + "人";
        },
      },
      {
        score: 3,
        count: 0,
        percentage: 10,
        format: function (percentage) {
          return "3分：" + _this.starScores[2].count + "人";
        },
      },
      {
        score: 4,
        count: 0,
        percentage: 10,
        format: function (percentage) {
          return "4分：" + _this.starScores[3].count + "人";
        },
      },
      {
        score: 5,
        count: 0,
        percentage: 10,
        format: function (percentage) {
          return "5分：" + _this.starScores[4].count + "人";
        },
      },
    ];

    this.resourceId = this.$router.currentRoute.params.id;

    this.loadResourceInfo();
    this.loadTopVisitResourceList();
    this.loadRelativeResourceList();
    this.loadComments();
    this.checkIsFavorite();
    this.loadStarScores();
    this.handleAddViewCount();
  },

  methods: {
    loadResourceInfo() {
      info(this.resourceId).then((res) => {
        if (res) {
          this.resource = JSON.parse(res.data);
          this.resource.isVideo =
            this.resource.fileExtName.indexOf(appEnum.fileExtNames.video) >= 0;
          this.starScore =
            this.resource.starCount == 0
              ? 0
              : Math.floor(this.resource.starTotal / this.resource.starCount);
        }
      });
    },

    loadTopVisitResourceList() {},

    loadRelativeResourceList() {},

    loadComments(current) {
      let selectParam = this.commentSelectParam;
      selectParam.resourceId = this.resourceId;
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

    loadStarScores() {
      let id = this.resourceId;
      listStarScores(id).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          let source = data.listData ? data.listData : [];
          for (let i in source) {
            let score = source[i].score;
            let scoreObj = this.starScores[score - 1];
            if (score > 0) scoreObj.count += 1;
            scoreObj.percentage =
              Math.floor(scoreObj.count / source.length) == 0
                ? 10
                : Math.floor(scoreObj.count / source.length) * 100;
          }
        }

        console.log(this.starScores);
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
        let resourceId = this.resourceId; // this.$router.currentRoute.params.id;
        favorite(resourceId).then((res) => {
          this.$notification.success({ message: messages.SUCCESS });

          this.checkIsFavorite();
        });
      }
    },

    checkIsFavorite() {
      if (!this.isLogin) return false;

      this.isFavorite = false;
      let resourceId = this.resourceId; // this.$router.currentRoute.params.id;
      myFavoriteList().then((res) => {
        if (res && res.data) {
          let listData = JSON.parse(res.data).listData;
          for (let i in listData) {
            if (listData[i].resourceId == resourceId) {
              this.isFavorite = true;
              break;
            }
          }
        }
      });
    },

    handleSendComment() {
      let resourceId = this.resourceId; //this.$router.currentRoute.params.id;
      this.$refs["commentForm"].validate((vaild) => {
        if (vaild) {
          commentModel.resourceId = resourceId;
          commentModel.userId = this.account.id;
          commentModel.content = this.commentForm.content;
          commentEdit(commentModel).then((res) => {
            if (res) {
              this.$notification.success({ message: messages.SUCCESS });
              this.loadComments();
            }
          });
        }
      });
    },

    handleDownload(fileName, path) {
      let resourceId = this.resourceId;
      if (!this.isLogin) {
        this.$notification.error({ message: messages.MUST_LOGIN_FIRST });
      } else {
        download(resourceId).then((res) => {
          if (res) {
            downloadFile(fileName, path);
          }
        });
      }
    },

    handleStar() {
      if (!this.isLogin) {
        this.starScore = 0;
        this.$notification.error({ message: messages.MUST_LOGIN_FIRST });
      } else {
        let resourceId = this.resourceId; // this.$router.currentRoute.params.id;
        star(resourceId, this.starScore).then((res) => {
          this.$notification.success({ message: messages.SUCCESS });
        });
      }
    },

    handleAddViewCount() {
      if (this.isLogin) {
        addViewCount(this.resourceId).then((res)=>{ });
      }
    },
  },
};
</script>

<style scoped>
</style>