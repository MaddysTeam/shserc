<template>
    <div>
    <ActivityUserAvatarList  title="活跃用户" :source="source" ></ActivityUserAvatarList>
    </div>
</template>

<script>

import { deepCopy } from "@/app/utils/objectHelper";
import { DESC } from "@/app/static/type";
import { list as userList } from "@/app/api/user";
import {selectParam,userOrderPhrasesModel} from "@/app/models/user"
import ActivityUserAvatarList from "@/app/views/croSite/user/components/AvatarList/index"

export default {
    components:{ActivityUserAvatarList},
    
    data() {
      return {
          selectParam:deepCopy(selectParam),
          source:[],
          size:16
      }
  },

  mounted(){
    this.loadTopActivityUsers();
  },

  methods: {
    loadTopActivityUsers(current) {
      if(current){
          this.selectParam.current=current;
      }

      this.selectParam.size = this.size; 
      this.selectParam.orderPhrases[userOrderPhrasesModel.activity] = DESC;

      userList(this.selectParam).then((res) => {
        if (res && res.data) {
          let data = JSON.parse(res.data);
          this.selectParam.total= data.total;
          this.source= data.listData ? data.listData : [];
        }
      });
    }
  },

}
</script>