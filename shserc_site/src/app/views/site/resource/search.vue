<template>
    <div>
        <div class="block_panel">
            <searchArea></searchArea>
        </div>
        <div class="block_panel m_30_bottom">
            <p class="panel_title flex_space_between">
                <span><i class="el-icon-tickets"></i> 搜索结果</span>
                <span>
                    <el-link :underline="false" type="primary"><i  class="el-icon-tickets font30"></i></el-link>
                    <el-link :underline="false" ><i class="el-icon-menu font30"></i></el-link>
                 </span>
            </p>
            <list></list>
        </div>
    </div>
</template>
<script>
import searchArea from "@/app/views/site/components/SearchArea";
import blockList from "@/app/views/site/components/BlockList"
import list from "@/app/views/site/components/list"
export default {
    components:{searchArea,blockList,list}
}
</script>

<style scoped>

</style>