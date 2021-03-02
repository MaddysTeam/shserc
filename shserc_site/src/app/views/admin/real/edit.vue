<template>
  <el-dialog
    title="新建实名卡"
    :visible.sync="visible"
    :before-close="handleClose"
  >
   <el-form>
       <el-form-item></el-form-item>
   </el-form>
  </el-dialog>
</template>

<script>
export default {
  components: {},
  props:{
      visible:{type:Boolean,required:true}
  },
  data() {
    return {};
  },
  mounted() {},
  methods: {
    handleClose() {
      this.$emit("close");
    },
  },
};
</script>

<style scoped>
</style>