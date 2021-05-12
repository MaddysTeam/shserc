<template>
  <div class="ranking">
    <List :source="source">
      <template slot="item" slot-scope="resourceOperation">
        <div class="ranking_square">
          <div class="details downwide">

            <router-link
              :to="{path:'/CroResource/details/'+resourceOperation.item.resourceId}"
              :title="resourceOperation.item.title"
              ><span class="restitle">{{ resourceOperation.item.title }}</span>
            </router-link>

            <br />

            <div class="info">
              作者：<span>{{ resourceOperation.item.author }}</span>
              收藏日期：<span>{{ resourceOperation.item.operationDate }}</span>
              格式：<span>{{ resourceOperation.item.fileExtName }}</span>
            </div>
          </div>
          <el-button
            type="danger"
            @click="handleCancelFavorite(resourceOperation.item.resourceId)"
            ><i class="el-icon-info"></i> 取消收藏</el-button
          >
        </div>
      </template>
    </List>
  </div>
</template>

<script>
import List from "@/components/List/index";
import { myFavoriteList } from "@/app/api/croMy";
import { favorite } from "@/app/api/croResource";
import { messages } from "@/app/static/message.js";

export default {
  components: { List },

  data() {
    return {
      source: [],
    };
  },

  mounted() {
    this.loadFavoriteList();
  },

  methods: {
    loadFavoriteList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      myFavoriteList().then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.source = data.listData;
          console.log(this.source);
        }
      });
    },

    handleCancelFavorite(resourceId) {
      this.$confirm(messages.WHETHER_IS_CONFIRM, messages.CONFIRM, {
        confirmButtonText: messages.IS_CONFIRM,
        cancelButtonText: messages.IS_CANCEL,
        type: "warning",
      })
        .then(() => {
          favorite(resourceId).then((res) => {
            this.$notification.success({ message: messages.SUCCESS });

            this.loadCommentList();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消操作",
          });
        });
    },
  },
};
</script>