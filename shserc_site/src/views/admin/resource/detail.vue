<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源详细</el-breadcrumb-item>
    </el-breadcrumb>
    <el-container>
      <el-main>
        <div class="content">
          <el-row>
            <el-col :span="14">
              <div class="cover text_align_left">
                <img
                  src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604985341462&di=0dc077c561a37951c21cd724aeb875d1&imgtype=0&src=http%3A%2F%2Fdingyue.ws.126.net%2FueBermhmQAPVio5YnFJWMaHkbgCQSXpEE8qpEYfAi8c%3Dh1551833241961.jpg"
                />
              </div>
            </el-col>
            <el-col :span="10" class="text_align_left">
              <div class="font20  m_30_left">
                这是一个资源这是一个资源这是一个资源这是一个资源这是一个资源这是一个资源
              </div>
              <div class="font14 m_30_left m_30_top">
                审核资源：
                <el-switch v-mode="resource.stateId" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
              </div>
              <div class="font14 m_30_left m_30_top">
                资源下载： <i class="el-icon-download font30"></i>
              </div>
               <div class="font14 m_30_left m_30_top">
                上传时间：<i class="el-icon-time font30"></i> 2020-11-08
              </div>
            </el-col>
          </el-row>
        </div>

        <div class="description">
          <div class="text_align_left m_20_top font20">资源描述</div>
        </div>

        <div class="info">
          <div class="text_align_left m_20_top font20">资源详情</div>
          <div class="m_20_top">
            <el-row>
              <el-col :span="8">
                <el-row>
                  <el-col :span="12" class="font14">资源名称：</el-col>
                  <el-col :span="12" class="font14">aaaa</el-col>
                </el-row>
                <el-row class="m_20_top">
                  <el-col :span="12" class="font14">资源名称：</el-col>
                  <el-col :span="12" class="font14">aaaa</el-col>
                </el-row>
              </el-col>
              <el-col :span="8">
                <el-row>
                  <el-col :span="12" class="font14">资源名称：</el-col>
                  <el-col :span="12" class="font14 ">aaaa</el-col>
                </el-row>
              </el-col>
              <el-col :span="8">
                <el-row>
                  <el-col :span="12" class="font14">资源名称：</el-col>
                  <el-col :span="12" class="font14">aaaa</el-col>
                </el-row>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-main>
      <el-aside>
        <el-card>
          <div>作者信息</div>
          <div class="font14 t m_30_top text_align_left">邮箱：22@aaa.com</div>
          <div class="font14  m_30_top text_align_left">电话：1563333xxxx</div>
          <div class="font14  m_30_top text_align_left">单位：xxxxxx</div>
        </el-card>
      </el-aside>
    </el-container>
  </div>
</template>

<script>
import { resourceModel } from "@/models/resource";

export default {
  data() {
    return {
      resource: resourceModel,
    };
  },
  mounted() {},
  methods: {},
};
</script>

<style scoped>
.cover img {
  width: 100%;
  max-width: 600px;
  min-width: 400px;
  transition: all 0.5s;
}
.el-card {
  min-width: 280px;
  margin-right: 20px;
  transition: all 0.5s;
}
.el-card:hover,.cover img:hover{
  margin-top:-5px;
}
</style>