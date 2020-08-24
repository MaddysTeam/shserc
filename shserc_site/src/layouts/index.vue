<template>
   <el-container class="container">
       <!-- top -->
       <el-header>
           <div>
            <span>特教资源库后台管理</span>
            
           </div>
           <el-dropdown :hide-timeout=300>
            <el-button type="danger" circle><i class="el-icon-setting"></i></el-button>
             <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>用户信息</el-dropdown-item>
                <el-dropdown-item>注销 </el-dropdown-item></el-dropdown-item>
             </el-dropdown-menu>
           </el-dropdown>  
          
       </el-header>

       <el-container>
           <!-- left aside -->
            <el-aside>
                <DynamicMenu ></DynamicMenu>
            </el-aside>
            <!--right side-->
            <el-main></el-main>
       </el-container>
   </el-container>
</template>

<script>
   import DynamicMenu from '../components/DynamicMenus/index';
   export default{
     name:"layout",
     components:{
         DynamicMenu
     },
     data(){
         return {}
     }
   }
</script>

<style scoped>
.container{
    height:100%;
}

.el-header{
    background:#373d41;
    display:flex;
    justify-content: space-between;
    align-items:center;
    font-size:20px;
    color:#fff;
}
.el-header > div{
    display: flex;
    align-items: center;
}
.el-aside{
    background:#333744;
}
.el-main{
    background: #eaedf1
}
</style>