<template>
  <div>
    <el-breadcrumb>
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>资源详细</el-breadcrumb-item>
    </el-breadcrumb>
    <el-container>
      <el-main>
        <div class="grid text_align_left">
          <div class="grid-item">
            <div>
              <img class="cover" v-if=" resource.resourceTypeId != businessEnum.resourceVideoTypeKey" :src="resource.coverPath" />
            </div>
          </div>
          <div class="grid-item font14">
            <div class="font20">
              {{ resource.title }}
            </div>
            <div class="m_30_top">
              审核资源：
              <el-switch
                v-model="resource.stateId"
                active-color="#13ce66"
                inactive-color="#ff4949"
              ></el-switch>
            </div>
            <div class="m_30_top">
              资源下载：
              <a :href="resource.resourcePath"
                ><i class="el-icon-download font30"></i
              ></a>
            </div>
            <div class="m_30_top">
              上传时间：<i class="el-icon-time font30"></i> 2020-11-08
            </div>
          </div>

          <div class="grid-item">
            <hr />
            <div class="font20 m_30_top">
              <i class="el-icon-platform font20"></i><strong>资源描述</strong>
            </div>
            <div class="m_30_top">
              {{ resource.description }}
            </div>
          </div>

          <div class="grid-item">
            <hr />
            <div class="font20 m_30_top"><strong>资源信息</strong></div>
            <el-row class="m_30_top">
              <el-col :span="8">
                <el-row>
                  <el-col :span="12" class="font14">资源名称：</el-col>
                  <el-col :span="12" class="font14">{{
                    resource.title
                  }}</el-col>
                </el-row>
                <el-row class="m_20_top">
                  <el-col :span="12" class="font14">残疾类型：</el-col>
                  <el-col :span="12" class="font14">{{
                    resource.deformity
                  }}</el-col>
                </el-row>
              </el-col>
              <el-col :span="8">
                <el-row>
                  <el-col :span="12" class="font14">资源类型：</el-col>
                  <el-col :span="12" class="font14"></el-col>
                </el-row>
              </el-col>
              <el-col :span="8">
                <el-row>
                  <el-col :span="12" class="font14">媒体类型：</el-col>
                  <el-col :span="12" class="font14"></el-col>
                </el-row>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-main>
      <el-aside>
        <el-card>
          <div>作者信息</div>
          <div class="font14 t m_30_top text_align_left">
            姓名：{{ resource.author }}
          </div>
          <div class="font14 t m_30_top text_align_left">
            邮箱：{{ resource.authorEmail }}
          </div>
          <div class="font14 m_30_top text_align_left">
            电话：{{ resource.authorPhone }}
          </div>
          <div class="font14 m_30_top text_align_left">
            单位：{{ resource.authorCompany }}
          </div>
        </el-card>
      </el-aside>
    </el-container>
  </div>
</template>

<script>
import { resourceModel } from "@/models/resource";
import { resource } from "@/api/resource";
import { getById } from "@/utils/dictHelper";
import { businessEnum } from "@/views/enum";
import { mapState } from "vuex";

export default {
  data() {
    return {
      resource: resourceModel,
      businessEnum:businessEnum
    };
  },
  computed: {
    ...mapState({
      deformities: (state) => state.deformity,
    }),
  },
  mounted() {

  },
  methods: {
    getResource(id) {
      resource(id).then((res) => {
        this.resource = JSON.parse(res.data);
        //this.resource.deformity= getById(this.deformities,this.resource.deformityId)
      });
    },
  },
};
</script>

<style scoped>
.grid {
  width: 100%;
  height: auto;
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  flex-wrap: wrap;
}

.grid-item {
  margin: 5px;
  border-radius: 8px;
  background: #EDEDED;
  flex: 1;
  min-width: 500px;
  margin: 20px;
}

.cover {
  width: 100%;
  max-width: 600px;
  transition: all 0.5s;
}
.el-card {
  min-width: 280px;
  margin-right: 20px;
  transition: all 0.5s;
}
.el-card:hover,
.cover:hover {
  margin-top: -5px;
}
</style>