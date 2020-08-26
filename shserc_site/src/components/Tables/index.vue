<template>
    <div>
        <el-table :data="list" style="width:100%" border>
            <template v-for="(item,index) in columns">
                <el-table-column :prop="item.prop" :width="item.width" :key="item.label" :align="item.align"  :label="item.label" >
                </el-table-column>
            </template>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <!-- scope.row相当于当前行的数据对象 -->
                    <el-button size="mini" :type="item.type" :key="index" v-for="(item,index) in commands" @click.native.prevent="item.method(index,scope.row)" >{{item.label}}</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
export default {
    props:{
        list:{type:Array,default:[]},
        columns:{type:Array, default:[]},
        commands:{}
    }
}
</script>

<style scoped>

</style>