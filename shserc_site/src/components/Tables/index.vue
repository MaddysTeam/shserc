<template>
  <div>
    <div class="outer">
      <div class="search-Box" >
        <el-input v-if="isShowSearchBox"
          placeholder="请输入关键字"
          icon="search"
          class="search"
          v-model="searchPhrase"
          @change="searchTextChanged"
        ></el-input>
        <el-button class="el-button--primary" v-if="isShowSearchBox"
          ><i class="el-icon-search"></i
        ></el-button>
      </div>
    </div>
    <el-table
      :data="list"
      :header-cell-style="tableHeaderColor"
      @selection-change="selectChanged"
      @cell-click="cellClick"
      @row-click="rowClick"
      ref= "inner"
      border
    >
    <el-table-column v-if="isMultiSelect" align="center"   type="selection" width="45"></el-table-column>
      <template v-for="(item, index) in columns">
        
        <el-table-column
          :prop="item.prop"
          :width="item.width"
          :key="item.label"
          :align="item.align"
          v-if="item.isLink"
          :label="item.label"
        >
          <template slot-scope="scope">
            <router-link
              :to="item.currentRoute"
              @click.native.prevent="item.method(index, scope.row)" style="color:blue"
              >{{ scope.row.title }}</router-link
            >
          </template>
        </el-table-column>
        <el-table-column
          :prop="item.prop"
          :width="item.width"
          :key="item.prop"
          :align="item.align"
          v-else-if="item.isTemplate"
          :label="item.label"
        >
          <template slot-scope="scope">
            <slot :name="item.prop" :scope="scope"></slot>
          </template>
        </el-table-column>
      <el-table-column
          :prop="item.prop"
          :width="item.width"
          :min-width="item.minWith"
          :key="item.prop"
          :align="item.align"
          v-else-if="item.isCommand && true"
          :label="item.label"
        >
         <template slot-scope="scope">
          <!-- scope.row相当于当前行的数据对象 -->
          <el-button
            size="mini"
            :type="item.type"
            :key="index"
            v-for="(item, index) in commands"
            @click.native.prevent="item.method(index, scope.row)"
            >{{ item.label }}</el-button
          >
        </template>
        </el-table-column>

        <el-table-column
          sortable
          :prop="item.prop"
          :width="item.width"
          :key="item.label"
          :align="item.align"
          v-else-if="true"
          :label="item.label"
          :show-overflow-tooltip="true"
        >
        </el-table-column>
      </template>
    </el-table>
    <div class="outer">
      <el-pagination v-if="isPagination"
        class="pagenation"
        background
        :current-page="current"
        :page-size="pageSize"
        @size-change="pageSizeChange"
        @current-change="currentChange"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    list: { type: Array, default: [] },
    columns: { type: Array, default: [] },
    // commandColumnWidth:{type:Number,default:'200px'},
    commands: {},
    isPagination:{type:Boolean,default: true},
    isMultiSelect:{type:Boolean,default:false},
    isShowSearchBox:{type:Boolean,default:true},
    total: { type: Number, default: 0 },
    pageSize: { type: Number, default: 0 },
    current: { type: Number, default: 0 },
    handleChange: { type: Function },
    handlePageSizeChange:{type:Function},
    handleSearch: { type: Function },
    handleRowSelectChange:{type:Function},
    handleCellClick:{type:Function},
    handleRowClick:{type:Function}
  },
  data() {
    return {
      searchPhrase: "",
    };
  },
  methods: {
    currentChange: function (val) {
      this.handleChange(val);
    },

    pageSizeChange:function(val){
      this.handlePageSizeChange(val);
    },

    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      return "font-weight:normal;font-size:12px;text-align:center";
    },

    searchTextChanged(){
      this.$emit("handleSearch",this.searchPhrase)
    },

    selectChanged(val){ 
      this.$emit("handleRowSelectChange",val);
    },

    cellClick(row, column, cell, event){
      this.$emit("handleCellClick",row, column, cell, event);
    },
    
    rowClick(	row, column, event){
       this.$emit("handleRowClick");
    }
    
  },
};
</script>

<style scoped>
.outer {  
  display: flex;
  align-items: center;
}
.search-Box { 
  width: 300px;
  display: flex;
  margin-left: auto;
  margin-bottom: 20px;
}
.pagenation {
  display: flex;
  margin-left: auto;
  margin-top: 20px;
}

.header-Row {
  font-weight: normal;
  text-align: center;
}
</style>