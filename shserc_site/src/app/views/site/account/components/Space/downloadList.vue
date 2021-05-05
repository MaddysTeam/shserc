<template>
  <div class="ranking">
   <List :source="source">
      <template slot="item" slot-scope="resourceOperation">
        <div class="ranking_square">
          <div class="details downwide">

            <router-link
              :to="{path:'/Resource/details/'+resourceOperation.item.resourceId}"
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
        </div>
      </template>
    </List>
  </div>
</template>

<script>
import List from "@/components/List/index";
import { downloadList } from "@/app/api/my";

export default {
  components: { List },

  data(){
	  return{
		  source:[]
	  }
  },

   mounted() {
    this.loadDownloadList();
  },

  methods: {
    loadDownloadList(current) {
      if (current) {
        this.selectParam.current = current;
      }
      downloadList().then((res) => {
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