<template>
  <list :source="[1,2,3,4,5]"  >
       <template v-slot:default="slotProps">
					<router-link to="/resource/details" title="对智障学生开展积极心理品质培养的实践研究"><img class="cover"  src="http://tjcdn.shec.edu.cn/demo/7.jpg"    style="height: 100px"></router-link>
					<div class="details">
						<a href="/resource/details" title="对智障学生开展积极心理品质培养的实践研究"><span class="restitle">对智障学生开展积极心理品质培养的实践研究</span></a><br>
                        <p class="snippet">随着特殊教育的“零拒绝”的发展，越来越多的中、重度甚至极重度智障儿童进入学校。由于智障学生认知水平和语言能力的缺陷，他们 ...</p>
						<div class="info">
							作者：<span>火惠慧</span>
							单位：<span>黄浦区阳光学校</span><br>
							上传日期：<span>2014/12/24</span>
							点击量：<span>20811</span>
							<el-tag type="danger"> 格式： pdf</el-tag>
						</div>
						
					</div>

				<!-- </div> -->
       </template>
   </list>
</template>

<script>
import list from "@/components/List";
export default {
  components: { list },
};
</script>

<style scoped>

</style>