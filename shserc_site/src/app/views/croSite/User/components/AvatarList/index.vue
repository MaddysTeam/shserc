<template>
  <!-- activity user list start -->
    <!-- title start -->
  <div class="block_panel">
    <p class="green_panel_title flex_space_between">
      <span><i class="el-icon-headset"></i> {{ title }}</span>
      <span>
        <router-link class="link font14" to=""
          ><i style="font-size: 32px" class="el-icon-more green"></i>
        </router-link>
      </span>
    </p>
 <!-- title end -->
 
    <UserAvatarList listType="blockCustomList" :source="source">
      <template slot="item" slot-scope="user">
        <el-avatar
          :src="user.item.photoPath"
          :size="100"
          @error="handleImageError"
        >
          <img :src="CDN.DEFAULT_HEADER_COVER" />
        </el-avatar>
        <div>{{ user.item.userName }}</div>
      </template>
    </UserAvatarList>

  </div>
  <!-- activity user list end -->
</template>

<script>
import { CDN } from "@/static/CDN"
import UserAvatarList from "@/components/List/index";

export default {
  components: { UserAvatarList },
  
  props:{
     title: {type:String,default:""},
     source: {type: Array ,default:[]}
  },

  data(){
    return {
      CDN:CDN
    }
  },

  methods:{
        handleImageError(){
      
    }
  }

};
</script>