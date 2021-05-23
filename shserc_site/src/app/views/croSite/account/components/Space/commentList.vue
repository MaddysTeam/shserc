<template>
  <div class="ranking">
   <List :source="source">
      <template slot="item" slot-scope="croComments">
        <div class="ranking_square">
          <div class="details downwide">

            <router-link
              :to="{name: routeNames.CroSiteRsourceDetails, params:{id:croComments.item.resourceId}}"
              :title="croComments.item.title"
              ><span class="restitle">{{ croComments.item.resourceTitle }}</span>
            </router-link>

            <br />

            <div class="info">
              <!-- 作者：<span>{{ croComments.item.author }}</span> -->
              评论日期：<span>{{ croComments.item.commentTime }}</span>
            </div>
            <div>
                我的评论：<span>{{ croComments.item.content }}</span>
            </div>
          </div>
        </div>
      </template>
    </List>
  </div>
</template>

<script>
import { routeNames } from "@/app/routers/routeNames";
import List from "@/components/List/index";
import { commentList } from "@/app/api/croMy";

export default {
  components: { List },

  data(){
	  return{
          routeNames:routeNames,
		  source:[]
	  }
  },

   mounted() {
    this.loadDownloadList();
  },

  methods: {
    loadCommentList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      commentList().then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.source = data.listData;
          console.log(this.source);
        }
      });
    },
  },
};
</script>