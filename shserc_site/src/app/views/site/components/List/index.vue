<template>
    <div class="list ">
        <div class="list_square" style="display:flex" v-for="i in [1,2,3,4,5]" :key="i">
					<a href="/Resource/View/2147" title="对智障学生开展积极心理品质培养的实践研究"><img class="cover"  src="http://tjcdn.shec.edu.cn/demo/7.jpg"    style="height: 100px"></a>
					<div class="details">
						<a href="/Resource/View/2147" title="对智障学生开展积极心理品质培养的实践研究"><span class="restitle">对智障学生开展积极心理品质培养的实践研究</span></a><br>
                        <p class="snippet">随着特殊教育的“零拒绝”的发展，越来越多的中、重度甚至极重度智障儿童进入学校。由于智障学生认知水平和语言能力的缺陷，他们 ...</p>
						<div class="info">
							作者：<span>火惠慧</span>
							单位：<span>黄浦区阳光学校</span><br>
							上传日期：<span>2014/12/24</span>
							点击量：<span>20811</span>
							<el-tag type="danger"> 格式： pdf</el-tag>
						</div>
						
					</div>

				</div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>

.list_square {
  border-bottom: 1px dotted #bfbfbf;
  padding-bottom: 20px;
  margin-bottom: 40px;
}

.list_square img.cover {
  width: 150px;
  height: 94px;
  vertical-align: top;
}
.list_square .details {
  /* width: 433px; */
  padding-left: 20px;
  position: relative;
  display: inline-block;
  text-align:left;
}
.list_square .details.wide {
  width: 733px;
}
.list_square .details.downwide {
  width: 890px;
}
.list_square .restitle {
  font-size: 16px;
  color: #0076f3;
}
.list_square .info {
  color: #666;
  margin-top: 6px;
}
.list_square .info span {
  /* color: #f39800; */
  margin-right: 8px;
}
.list_square .snippet {
  color: #666;
  font-size: 10px;
  margin-top: 6px;
}

</style>