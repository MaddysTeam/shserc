<template>
  <div>
    <list listType="blockList" :source="[1, 2, 3, 4, 5, 6]">
      <template>

        <div class="widget">
          <!-- widget header start-->
          <div
            class="widget-header"
            :style="{ backgroundColor: '#39b54a', color: 'white' }"
          >
          </div>
          <!-- widget content end-->

          <!-- widget content start-->
          <div class="widget-content">
            <a href="/Resource/View/1158" title="让我们成为芭蕾演员吧" >
              <img
                class="cover"
                src="http://tjcdn.shec.edu.cn/demo/7.jpg"
                :style="{width: coverWidth+'px', height: coverHeight+'px'}"
            /></a>
            <div class="hot"></div>
            <div class=" m_5 text_align_left">

                <el-link :underline="false" type="primary"
                  >让我们成为芭蕾演员吧</el-link
                >


              <p>作者：上海高教电子音像出版社</p>

              <p v-show="isShowStatistical">
                <el-tag>点击量：80</el-tag>
                <el-tag type="info">下载量：80</el-tag>
                <el-tag type="danger"> 格式： pdf</el-tag>
              </p>
              <el-rate v-model="value2" :colors="colors"> </el-rate>
            </div>
          </div>
          <!-- widget content end-->
        </div>

      </template>
    </list>
  </div>
</template>

<script>
import list from "@/components/List";

export default {
  components:{list},

  props:{
    isShowStatistical:{type:Boolean},
    coverWidth:{type:Number},
    coverHeight:{type:Number}
  },
  
  data() {
    return {
      value1: null,
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"], // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
    };
  },
  methods: {
  },
};
</script>

<style scoped>
</style>