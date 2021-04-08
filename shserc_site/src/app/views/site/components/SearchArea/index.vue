<template>
  <div>
    <p class="panel_title">
      <span><i class="el-icon-search"></i> 资源检索</span>
    </p>
    <div class="search">
      <div class="search bar1">
        <form>
          <input type="text" placeholder="请输入您要搜索的内容..." />
          <button @click.prevent="btn" @click="handleSubmit()"> 
            <i class="el-icon-search" style="font-size: 26px; color: #eee"></i>
          </button>
        </form>
      </div>
    </div>
    
    <div class="adv_hit">
        <div style="border:1px #eee dashed; width:80%; margin: 0 auto"></div>
        <dl class="domain   ">
				<dt>领 域：</dt>
				<dd>
					<ul id="domainChoose" class="items">
						<li><a href="javascript:void(0)" class="actived" data-id="">全部</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="domain" data-id="10001">政策与文献</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="domain" data-id="10002">诊断与评估</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="domain" data-id="10003">课程与教学</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="domain" data-id="10004">康复与干预</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="domain" data-id="10005">支持与服务</a></li>
					</ul>
				</dd>
			</dl>
			<dl class="deformity">
				<dt>分 类：</dt>
				<dd>
					<ul id="deformityChoose" class="items">
						<li><a href="javascript:void(0)" class="actived" data-id=""><el-tag effect="dark" type="danger ">全部</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10031"><el-tag effect="light">视力残疾</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10032"><el-tag effect="light">听力残疾</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10033"><el-tag effect="light">智力残疾</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10034"><el-tag effect="light">肢体残疾（脑瘫）</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10035"><el-tag effect="light">肢体残疾（非脑瘫）</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10036"><el-tag effect="light">言语残疾</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10037"><el-tag effect="light">精神残疾</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10038"><el-tag effect="light">精神残疾（自闭症）</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10039"><el-tag effect="light">多重残疾</el-tag></a></li>
								<li><a href="javascript:void(0)" class="item" data-type="deformity" data-id="10069"><el-tag effect="light">其他残疾</el-tag></a></li>
					</ul>
				</dd>
			</dl>
			<dl class="resourceType ">
				<dt>类 型：</dt>
				<dd>
					<ul id="resourceTypeChoose" class="items">
						<li><a href="javascript:void(0)" class="actived" data-id="">全部</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10231">政策</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10232">评估工具与方法</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10234">课程方案与标准</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10235">教学设计</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10236">教学课件</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10237">教学实录</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10238">个别化教育计划</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10239">康复训练设计</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10240">康复训练课件</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10241">康复训练实录</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10242">残疾人支持服务项目介绍</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10244">教学案例</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10245">教学资源包</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10247">辅助器具介绍</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10248">教具学具介绍</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10249">支持与服务个案报告</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10250">文献</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="resourceType" data-id="10251">康复训练案例</a></li>
					</ul>
				</dd>
			</dl>
			<dl class="subject  ">
				<dt>学 科：</dt>
				<dd>
					<ul id="subjectChoose" class="items">
						<li><a href="javascript:void(0)" class="actived" data-id="">全部</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10271">生活自理</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10272">语文</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10273">数学</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10274">英语</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10275">科学</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10276">生活</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10277">品德与社会</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10278">社会</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10279">信息技术</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10280">艺术</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10281">劳动技术</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10282">职业技术</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10283">体育与健康</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10284">言语沟通训练</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10285">感知运动训练</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10286">行为训练</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10287">定向行走训练</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10288">综合康复</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10289">拓展</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10290">综合实践活动</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10291">学前特教</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10292">物理</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10293">精细动作训练</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10294">认知</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10295">按摩职业教育</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10296">个训或小组训练</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10349">其他学科</a></li>
					</ul>
				</dd>
			</dl>
			<dl class="Grade  " style="display: none;">
				<dt>年 级：</dt>
				<dd>
					<ul id="gradeChoose" class="items">
						<li><a href="javascript:void(0)" class="actived" data-id="">全部</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10121">一年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10122">二年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10123">三年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10124">四年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10125">五年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10126">六年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10127">七年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10128">八年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10129">九年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10130">低年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10131">中年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10132">高年级</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10133">小学段</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10134">初中段</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10141">高一</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10142">高二</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10143">高三</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10144">高四</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10151">初职一</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10152">初职二</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10153">初职三</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10154">初职四</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10161">中职一</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10162">中职二</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10163">中职三</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="subject" data-id="10164">中职四</a></li>
					</ul>
				</dd>
			</dl>
			<dl class="schoolType " style="display: none;">
				<dt>学校类型：</dt>
				<dd>
					<ul id="schoolTypeChoose" class="items">
						<li><a href="javascript:void(0)" class="actived" data-id="">全部</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="schoolType" data-id="10091">聋校</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="schoolType" data-id="10092">幼儿园</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="schoolType" data-id="10093">辅读学校</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="schoolType" data-id="10094">普通学校</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="schoolType" data-id="10095">盲校</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="schoolType" data-id="10096">职业技术学校</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="schoolType" data-id="10097">大学</a></li>
					</ul>
				</dd>
			</dl>
			<dl class="learnFrom " style="display: none;">
				<dt>安置形式：</dt>
				<dd>
					<ul id="learnFromChoose" class="items">
						<li><a href="javascript:void(0)" class="actived" data-id="">全部</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="learnFrom" data-id="10071">特殊学校</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="learnFrom" data-id="10072">特教班</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="learnFrom" data-id="10073">送教上门</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="learnFrom" data-id="10074">随班就读</a></li>
								<li><a href="javascript:void(0)" class="item" data-type="learnFrom" data-id="10089">其他</a></li>
					</ul>
				</dd>
			</dl>
			<dl class="selectItems  hidden">
				<dt>过滤条件：</dt>
				<dd>
					<ul id="selectItems">
						<li><a href="javascript:void(0)" class="clean"><font color="red">清空过滤条件</font></a></li>
					</ul>
				</dd>
			</dl>
    </div>

    <div class="text-center more" style="display: none">
      <a href="javascript:void(0)">
        <strong><i class="fa fa-arrow-down"></i> 更多条件</strong></a
      >
    </div>
    <div class="text-center less" style="display: none">
      <a href="javascript:void(0)">
        <strong><i class="fa fa-arrow-up"></i> 收起</strong></a
      >
    </div>
  </div>
</template>

<script>
export default {
	props:{
		handleSearch:{
			type:Function 
		}
	},

	methods:{
		handleSubmit(){
			this.$emit("handleSearch");
		}
	}
};
</script>

<style>

</style>