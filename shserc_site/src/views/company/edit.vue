<template>
  <el-dialog
    title="新增单位"
    :visible.sync="visible"
    :before-close="handleClose"
  >
    <el-form :model="company" ref="company">
      <el-form-item prop="companyName">
        <el-input
          prefix-icon="el-icon-search"
          v-model="company.name"
          placeholder="单位名称"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="model" placeholder="请选择" style="width: 100%">
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
import { getChildren } from "../../api/company.js";

export default {
  name: "edit",
  data() {
    return {
      company: {
        name: "",
      },
      model: {},
      options: [],
    };
  },
  props: {
    visible: { type: Boolean, required: true },
  },
  mounted() {
    this.bindCompany(1);
  },
  methods: {
    submitForm() {
      this.$emit("close");
    },
    handleClose() {
      this.$emit("close");
    },
    bindCompany(root) {
      getChildren(root).then((res) => {
        var array = JSON.parse(res.data).children;
        var resultArray = [];
        for (let index = 0; index < array.length; index++) {
          resultArray.push({
            value: array[index].id,
            label: array[index].label,
          });
        }
        this.options = resultArray;
      });
    },
  },
};
</script>

<style scoped>
</style>