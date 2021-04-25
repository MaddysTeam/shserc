<template>
  <List
    :source="source"
    :isShowPage="isShowPage"
    :pageSize="pageSize"
    :total="total"
    :handleChange="handleChange"
    :handlePageSizeChange="handlePageSizeChange"
  >
    <template slot="item" slot-scope="comment"> 
      <img
        class="cover"
        :src="comment.item.userPhotoPath"
        style="height: 100px"
      />
      <div class="details">
        <p class="font14">
          <el-tag>{{comment.item.addUserName}}</el-tag> 发表于：
          <el-tag type="success">{{comment.item.commentTime}}</el-tag>
        </p>
        <br />
        <p class="snippet">
          {{ comment.item.content }}
        </p>
      </div>
    </template>
  </List>
</template>
<script>
import List from "@/components/List";

export default {
  components: { List },
  props: {
    source: { type: Array, default: [] },
    isShowPage: { type: Boolean, default: false },
    pageSize: { type: Number, default: 0 },
    total: { type: Number, default: 0 },
    handleChange: { type: Function },
    handlePageSizeChange: { type: Function },
  },
};
</script>