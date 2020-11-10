<template>
  <el-container>
    <el-main> 
        <div class="title">资源标题</div>
        <div class="content">
           <div class="cover"> aaaaaaaaaaaaaaaaa</div>
           <div style="clear:both"></div>
        </div>
        <div class="info">
            <el-row>
              <el-col :span="8">ccc</el-col>
              <el-col :span="8">ddd</el-col>
              <el-col :span="8">eee</el-col>
            </el-row>
        </div>
     </el-main>
    <el-aside>bbbbbbbbbbb</el-aside>
  </el-container>
</template>

<script>
export default {

  
};
</script>

<style scoped>
.title{
  float:left;
  font-size: 30px;
}

.info,.content{
  clear: both;
}

.content .cover{
  float:left;
  margin-top:50px;
}

.info{
  margin-top: 50px;
}
</style>