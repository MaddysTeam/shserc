<template>
    <div>
    <ActivityUserAvatarList  title="活跃用户" :source="source" ></ActivityUserAvatarList>
    </div>
</template>

<script>
import { list as userList } from "@/app/api/user";
import {selectParam} from "@/app/model/selectParam"
import ActivityUserAvatarList from "@/app/user/components/AvataList/index"

export default {
    components:{ActivityUserAvatarList},
    
    data() {
      return {
          selectParam:deepCopy(selectParam),
          source:[]
      }
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
          this.topActivityUsers = data.listData ? data.listData : [];
        }
      });
    },
  },

}
</script>