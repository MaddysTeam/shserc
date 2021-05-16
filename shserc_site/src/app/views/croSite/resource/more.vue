<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="16">
        <div class="block_panel">
          <ResourceList
            :isShowPage="true"
            :pageSize="selectParam.pageSize"
            :total="selectParam.total"
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

export default {
    components:{ ResourceList,TopHotList,TopNewList},

    data(){
        return {
           topHotResource:[],
           topLatestSource: [],
           selectParam:deepCopy(selectParam)
        }
    }
,
    methods:{
        loadTopHotList(current,size){
             if (current) {
                this.selectParam.current = current;
            }
            this.selectParam.createType=this.$router.currentRoute.params.createType; // get create type , original or recommend
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
