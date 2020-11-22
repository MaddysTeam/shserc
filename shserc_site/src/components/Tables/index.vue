<template>
    <div>
        <el-table :data="list" style="width:100%" border>
            <template v-for="(item,index) in columns">
                <el-table-column :prop="item.prop" :width="item.width" :key="item.label" :align="item.align" v-if="item.isLink"
                    :label="item.label">
                    <template slot-scope="scope">
                        <router-link :to="item.currentRoute" @click.native.prevent="item.method(index,scope.row)">{{scope.row.title}}</router-link>
                    </template>
                </el-table-column>
                <el-table-column :prop="item.prop" :width="item.width" :key="item.label" :align="item.align" v-else
                    :label="item.label">
                </el-table-column>
            </template>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <!-- scope.row相当于当前行的数据对象 -->
                    <el-button size="mini" :type="item.type" :key="index" v-for="(item,index) in commands"
                        @click.native.prevent="item.method(index,scope.row)">{{item.label}}</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination style="display: flex; margin:10px;" 
             background 
            :current-page="current" 
            :page-sizes="[2, 5, 10]" 
            :page-size="pageSize"
            @current-change="handleCurrentChange"
            layout="total, sizes, prev, pager, next, jumper" 
            :total="total" >
        </el-pagination>
    </div>
</template>
<script>
    export default {
        props: {
            list: { type: Array, default: [] },
            columns: { type: Array, default: [] },
            commands: {},
            total:{type:Number,default:0},
            pageSize:{type:Number,default:0},
            current:{type:Number,default:0},
            handleChange:{type:Function}
        },
        methods: {
            handleCurrentChange:function(val){
               this.handleChange(val)
            }
        },
    }
</script>

<style scoped>

</style>