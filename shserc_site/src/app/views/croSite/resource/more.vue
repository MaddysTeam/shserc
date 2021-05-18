<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="16">
        <div class="block_panel green_edge">
        <p class="green_panel_title flex_space_between">
           <span ><i class="el-icon-star-on font20"></i> 热门资源   &nbsp; &nbsp;  </span>
        </p>
          <ResourceList
            :isShowPage="true"
            :pageSize="selectParam.pageSize"
            :total="selectParam.total"
            :source="topHotResource"
          ></ResourceList>
        </div>
      </el-col>
      <el-col :span="7">
        <div class="block_panel">
          <TopHotList
           :isShowPage="false"
            
          ></TopHotList>
        </div>

        <div class="block_panel">
          <TopNewList
           :isShowPage="false"
          ></TopNewList>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import ResourceList from "@/app/views/croSite/resource/components/List" ;
import TopHotList from "@/app/views/croSite/resource/components/TopList";
import TopNewList from "@/app/views/croSite/resource/components/TopList";
import { selectParam, orderPhrasesModel } from "@/app/models/croResource";
import { list } from "@/app/api/croResource";
import { deepCopy } from "@/app/utils/objectHelper";
import { DESC } from "@/app/static/type";

export default {
    components:{ ResourceList,TopHotList,TopNewList},

    data(){
        return {
           topHotResource:[],
           topLatestSource: [],
           selectParam:deepCopy(selectParam)
        }
    },

    mounted(){
        this.loadTopHotList(this.selectParam.current);
    },

    methods:{
        loadTopHotList(current,size){
          let param= this.selectParam;
             if (current) {
                param.current = current;
            }
             param.createTypeId=this.$router.currentRoute.query.createType; // get create type , original or recommend
             param.orderPhrases[orderPhrasesModel.viewCount] = DESC;
             if(size && size>0){
                 param.size=size;
             }
             
             list(param).then((res) => {
                if (res && res.data) {
                let data = JSON.parse(res.data);
                this.selectParam.total = data.total;
                this.topHotResource = data.listData ? data.listData : [];
                }
            });
        },

        loadTopNewList(){
            //TODO:20210516
        }
    }
}
</script>
