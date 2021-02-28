<template>
    <div>
        dashboard
        <!-- <Editor v-model="content" @contentData="change($event)" ></Editor> -->
    </div>
</template>

<script>
import Editor from '@/components/Editor/index'
export default {
    components:{
        Editor
    },
    data(){
        return{
            content: { html:'<strong style="color:red">hey!</strong>'}
        }
    },
    mounted(){

    },
    methods:{
        change(e){
            //this.content.value="<div>hello</div>"
        }
    }
}
</script>

<style scoped>

</style>