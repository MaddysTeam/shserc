<template>
  <div>

    <List listType="blockList" 
    :source="source"
     :isShowPage="isShowPage" 
     :pageSize="pageSize" 
     :total="total"
    :handleChange="handleChange"
    :handlePageSizeChange="handlePageSizeChange"
     > 
      <template slot="item" slot-scope="resource">
        <div class="widget">
          <!-- widget header start-->
          <div
            class="widget-header"
            :style="{ backgroundColor: '#39b54a', color: 'white' }"
          ></div>
          <!-- widget content end-->

          <!-- widget content start-->
          <div class="widget-content">
            <router-link to="/resource/details">
              <img
                class="cover"
                :src="resource.item.coverPath"
                :style="{
                  width: coverWidth + 'px',
                  height: coverHeight + 'px',
                }"
            />
            </router-link>
            <div class="hot"></div>
            <div class="m_5 text_align_left">
              <div>标题：<router-link class="link" :to="{path:'/resource/details/'+resource.item.id}">{{ resource.item.title}} </router-link></div>
              <p>作者：{{resource.item.author}}</p>

              <p v-show="isShowStatistical">
                <el-tag>点击量：{{ resource.item.viewCount}}</el-tag>
                <el-tag type="info">下载量：{{ resource.item.viewCount}}</el-tag>
                <el-tag type="danger"> 格式： {{resource.item.fileExtName}}</el-tag>
              </p>
              <el-rate v-model="value2" :colors="colors"> </el-rate>
            </div>
          </div>
          <!-- widget content end-->
        </div>
      </template>
    </List>

  </div>
</template>

<script>
import List from "@/components/List";

export default {
  components: { List },

  props: {
    isShowStatistical: { type: Boolean },
    source:{type:Array, dufault: []},
    coverWidth: { type: Number },
    coverHeight: { type: Number },
    isShowPage:{type: Boolean, default:false},
    pageSize:{type:Number,default:0},
    total:{type:Number,default:0},
     handleChange: { type: Function },
    handlePageSizeChange:{type:Function},
  },

  data() {
    return {
      value1: null,
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    };
  },
  methods: {},
};
</script>

<style scoped>
</style>