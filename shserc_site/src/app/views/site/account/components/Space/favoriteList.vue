<template>
  <div class="ranking">
    <List :source="source"> 
        <template slot="item" slot-scope="resourceOperation">
               <div class="ranking_square">
						
						<div class="details downwide">
							<a href="/Resource/View/47" title="随班就读智力障碍学生同伴交往的案例研究"><span class="restitle">{{resourceOperation.item.title}}</span></a><br>

							<div class="info">
								作者：<span>曾凡林、陈奇</span>
								下载日期：<span>2020/12/21</span>
								格式：<span>.DOC</span>
							</div>
						</div>
	<el-button type="danger"><i class="el-icon-info"></i>  取消收藏</el-button>
	</div>        
        </template>
    </List>
  </div>
</template>

<script>
import List from "@/components/List/index";
import {myFavoriteList} from "@/app/api/my";

export default {
  components: { List },

  data(){
	  return{
		  source:[]
	  }
  },

  mounted(){
	  this.loadCommentList();
  },

  methods:{
	   loadCommentList(current) {
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
  }
};
</script>