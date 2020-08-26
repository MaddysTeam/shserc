<template>
  <Table  :list="source" :columns="columns" :commands="commands"></Table>
</template>

<script>
import Table from "../../components/Tables/index";
import { userList } from "../../api/user";
export default {
  components: { Table },
  name: "user",
  data() {
    return {
      columns: [{ prop: 'id',label:'id' },{ prop: 'name',label:'name' }],
      source: [{id:1, name: "name" },{id:2, name: "name2" }],
      commands: [
        {
          id: 1,
          label: "编辑",
          type: "info",
          method: (index, row) => {
           console.log('编辑:'+index, row)
          },
        },
      ],
    };
  },
  mounted() {
  },
};
</script>

<style  scoped></style>