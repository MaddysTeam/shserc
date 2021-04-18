<template>
  <div>
    <p class="panel_title">
      <span><i class="el-icon-search"></i> 资源检索</span>
    </p>
    <div class="search">
      <div class="search bar1">
        <form>
          <input type="text" placeholder="请输入您要搜索的内容..." />
          <button @click.prevent="handlePrevent" @click="handleSubmit()"> 
            <i class="el-icon-search" style="font-size: 26px; color: #eee"></i>
          </button>
        </form>
      </div>
    </div>
    
    <div class="adv_hit" v-show="isShowAdvHit">
        <div style="border:1px #eee dashed; width:80%; margin: 0 auto"></div>
        <dl class="domain">
				<dt>领 域：</dt>
				<dd>
					<ul id="domainChoose" class="items">
						<li><el-button type="danger" size="mini">全部</el-button></li>
						<li class="item" v-for="(item) in domainOptions"  :key="item.id" @click="handleSelectItem(item)">
							 <el-tag>{{ item.name}}</el-tag>
						</li> 
					</ul>
				</dd>
			</dl>
			<dl class="deformity">
				<dt>分 类：</dt>
				<dd>
					<ul id="deformityChoose" class="items">
						<li><el-button type="danger" size="mini">全部</el-button></li>
						<li class="item" v-for="(item) in deformityOptions"  :key="item.id" @click="handleSelectItem(item)">
							 <el-tag>{{ item.name}}</el-tag>
						</li>
					</ul>
				</dd>
			</dl>
			<dl class="resourceType">
				<dt>类 型：</dt>
				<dd>
					<ul id="resourceTypeChoose" class="items">
						<li><el-button type="danger" size="mini">全部</el-button></li>
						<li class="item" v-for="(item) in typeOptions"  :key="item.id" @click="handleSelectItem(item)">
							 <el-tag>{{ item.name}}</el-tag>
						</li>
					</ul>
				</dd>
			</dl>
			<dl class="subject">
				<dt>学 科：</dt>
				<dd>
					<ul id="subjectChoose" class="items">
						<li><el-button type="danger" size="mini">全部</el-button></li>
						<li class="item" v-for="(item) in subjectOptions"  :key="item.id" @click="handleSelectItem(item)">
							 <el-tag>{{ item.name}}</el-tag>
						</li>
					</ul>
				</dd>
			</dl>
			<dl class="grade" style="display: none;">
				<dt>年 级：</dt>
				<dd>
					<ul id="gradeChoose" class="items">
						<li><el-button type="danger" size="mini">全部</el-button></li>
						<li class="item" v-for="(item) in gradeOptions"  :key="item.id" @click="handleSelectItem(item)">
							 <el-tag>{{ item.name}}</el-tag>
						</li>
					</ul>
				</dd>
			</dl>
			<dl class="schoolType" style="display: none;">
				<dt>学校类型：</dt>
				<dd>
					<ul id="schoolTypeChoose" class="items">
						<li><el-button type="danger" size="mini">全部</el-button></li>
						<li class="item" v-for="(item) in schoolTypeOptions"  :key="item.id" @click="handleSelectItem(item)">
							 <el-tag>{{ item.name}}</el-tag>
						</li>
					</ul>
				</dd>
			</dl>
			<dl class="learnFrom" style="display: none;">
				<dt>安置形式：</dt>
				<dd>
					<ul id="learnFromChoose" class="items">
						<li><el-button type="danger" size="mini">全部</el-button></li>
						<li class="item" v-for="(item) in learnFromOptions"  :key="item.id" @click="handleSelectItem(item)">
							 <el-tag>{{ item.name}}</el-tag>
						</li>
					</ul>
				</dd>
			</dl>
			<dl class="selectItems  hidden">
				<dt>过滤条件：</dt>
				<dd>
					<ul id="selectItems">
						<li @click="handleUnSelectItem()"><el-button type="danger" size="mini"><i class="el-icon-delete"></i></el-button></li>
						<li v-for="(item) in selectedItems"  :key="item.id">
							<el-tag type="danger">{{ item.name }}</el-tag>
						</li>
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
import { mapState } from "vuex";

export default {
	props:{
		handleSearch:{
			type:Function 
		},
		isShowAdvHit:{type:Boolean}
	},

	data(){
		return {
			selectedItems:[]
		}
	},

	 computed: {
    ...mapState({
      dict: (state) => state.app.dict,
      deformityOptions: (state) => state.app.deformity,
      domainOptions: (state) => state.app.resourceDomains,
      typeOptions: (state) => state.app.resourceTypes,
      stageOptions: (state) => state.app.stages,
      gradeOptions: (state) => state.app.grades,
      subjectOptions: (state) => state.app.subjects,
      schoolTypeOptions: (state) => state.app.schoolTypes,
      learnFromOptions: (state) => state.app.learnFrom,
    }),
  },

	methods:{
		handlePrevent(){},

		handleSubmit(){
			this.$emit("handleSearch");
		},

		handleSelectItem(item){
			if(item && item.value>0){
				this.selectedItems.push(item);
				handleSubmit();
			}
		},

		handleUnSelectItem(itemValue){
			if(itemValue){

			}
			else{
				this.selectedItems=[];
			}
		}
	}
};
</script>

<style>

</style>