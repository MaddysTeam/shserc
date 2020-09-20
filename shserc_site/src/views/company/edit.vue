<template>
  <el-dialog title="新增单位" :visible.sync="visible" :before-close="handleClose">
    <el-form :model="company" ref="company">
      <el-form-item prop="companyName">
        <el-input prefix-icon="el-icon-search" v-model="company.name" placeholder="单位名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="value" placeholder="请选择" style="width:100%">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item class="btns">
        <el-button type="primary" @click="submitForm()">确定</el-button>
        <el-button type="info" @click="handleClose()">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: "edit",
  data() {
    return {
      company: {
        name: "",
      },
      options:[
        {
          value: '0',
          label: '全部状态'
        },
        {
          value: '1',
          label: '正常运行'
        },
        {
          value: '2',
          label: '离线或故障'
        },
        {
          value: '3',
          label: '超标报警'
        }
      ]
          
    };
  },
  props: {
    visible: { type: Boolean, required: true },
  },
  methods: {
    submitForm() {
      console.log("submit");
      this.$emit("close");
    },
    handleClose() {
      this.$emit("close");
    },
  },
};
</script>

<style scoped>
</style>