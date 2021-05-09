<template>
    <div>
        <el-row :gutter="10">
            <el-col :span="16">
                 <div class="block_panel">
                      <ResourceList></ResourceList>
                 </div>
            </el-col>
             <el-col :span="7">

                    <div class="block_panel">
                        <TopHotList></TopHotList>
                    </div>

                     <div class="block_panel">
                         <TopNewList></TopNewList>
                    </div>

             </el-col>
        </el-row> 
       
    </div>
    
</template>

<script>

import ResourceList from "@/app/views/croSite/resource/components/List"; 
import TopHotList from "@/app/views/croSite/resource/components/TopList"
import TopNewList from "@/app/views/croSite/resource/components/TopList"

export default {
    components:{ ResourceList,TopHotList,TopNewList},

    data(){
        return {

        }
    }
    
}
</script>
