<template>
  <List :source="[1, 2, 3]">
    <template slot="item">
      <img
        class="cover"
        src="http://tjcdn.shec.edu.cn/demo/7.jpg"
        style="height: 100px"
      />
      <div class="details">
        <p class="font14">
          <el-tag>杨美玲</el-tag> 发表于：
          <el-tag type="success">2019/9/12</el-tag>
        </p>
        <br />
        <p class="snippet">
          随着特殊教育的“零拒绝”的发展，越来越多的中、重度甚至极重度智障儿童进入学校。由于智障学生认知水平和语言能力的缺陷，他们
          ...
        </p>
      </div>
    </template>
  </List>
</template>
<script>
import List from "@/components/List";

export default {
    components:{List}
};
</script>