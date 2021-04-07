<template>
    <div>
        <div class="block_panel">
            <searchArea></searchArea>
        </div>
        <div class="block_panel m_30_bottom">
            <p class="panel_title">
                <span><i class="el-icon-tickets"></i> 搜索结果</span>
            </p>
            <blockList></blockList>
        </div>
    </div>
</template>
<script>
import searchArea from "@/app/views/site/components/SearchArea";
import blockList from "@/app/views/site/components/BlockList"
export default {
    components:{searchArea,blockList}
}
</script>

<style scoped>

</style>