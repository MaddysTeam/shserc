<template>
  <RList
    :source="source"
    :isShowPage="isShowPage"
    :pageSize="pageSize"
    :total="total"
    :handleChange="handleChange"
    :handlePageSizeChange="handlePageSizeChange"
  >
    <template slot="item" slot-scope="resource">
      <router-link :to="{path:'/croSite/resource/details/'+resource.item.id}" :title="resource.item.title"
        ><img
          class="cover"
          :src="resource.item.coverPath"
          style="height: 100px"
      /></router-link>
      <div class="details">
        <router-link  :to="{path:'/croSite/resource/details/'+resource.item.id}" :title="resource.item.title"
          ><span class="restitle">{{ resource.item.title }}</span></router-link
        ><br />
        <p class="snippet">
          {{ resource.item.description }}
        </p>
        <div class="info">
          作者：<span>{{ resource.item.author }}</span> 单位：<span>{{
            resource.item.authorCompany
          }}</span
          ><p/>
           <el-tag>上传日期：<span>{{ resource.item.addTime }}</span></el-tag> <el-tag type="success">点击量：<span>{{
            resource.item.viewCount
          }}</span></el-tag>
          <el-tag type="danger"> 格式： {{ resource.item.fileExtName }}</el-tag>
        </div>
      </div>
    </template>
  </RList>
</template>

<script>
import RList from "@/components/List";
export default {
  components: { RList },
  props: {
    source: { type: Array, dufault: [] },
    isShowPage: { type: Boolean, default: false },
    pageSize: { type: Number, default: 0 },
    total: { type: Number, default: 0 },
    handleChange: { type: Function },
    handlePageSizeChange: { type: Function },
  },
};
</script>

<style scoped>
</style>