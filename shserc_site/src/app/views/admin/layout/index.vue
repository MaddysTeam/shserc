<template>
   <el-container class="container">
       <!-- top -->
       <el-header>
           <div>
            <span>特教资源库后台管理</span>
           </div>
           <el-dropdown :hide-timeout=300>
            <el-button type="info" circle><el-image class="img_header" src="https://himg.bdimg.com/sys/portraitn/item/757c4a696d6d79576f6c6632303134ab4d"></el-image>
            </el-button>
            <el-link class="img-header">JimmyPoor</el-link>     
             <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>用户信息</el-dropdown-item>
                <el-dropdown-item @click.native="logout">注销 </el-dropdown-item>
             </el-dropdown-menu>
           </el-dropdown>  
       </el-header>

       <el-container>
           <!-- left aside -->
            <el-aside>
                <DynamicMenu :dataSource="menus"></DynamicMenu>
            </el-aside>
            <!--right side-->
            <el-main >
                <router-view></router-view>
            </el-main>
       </el-container>
   </el-container>
</template>

<script>
   import DynamicMenu from '@/components/DynamicMenus/index';
   import * as types from '@/app/static/type';
   //TODO: import {getMenusData} from '@/app/api/menu.js'
   import {menuRoutes} from '@/app/routers/routes'
   import { mapMutations } from "vuex";
   import {logout} from '@/app/api/account'

   export default{
     name:"layout",
     components:{
         DynamicMenu
     },
     data(){
         return {
             menus: menuRoutes //TODO:getMenusData()
         }
     }, 
     methods:{
         logout:function(){
            logout().then(res=>{
                this.$store.commit(types.APP+"/"+types.LOGOUT)
                this.$router.push({ path:'/admin/logout',replace:true});
            });
           
         }
     }
   }
</script>

<style scoped>
.container{
    height:100%;
    font-family: Microsoft YaHei;
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
.img-header{
    color:#fff; font-size:12px;;
}

.el-button.is-circle{
    padding: 1px;
}
.img_header{
    width:38px;
    height:38px;
    border-radius: 50%;
}
.el-aside{
    background:#333744;
}
.el-main{
    background: #eaedf1;
    width:60%;
}
</style>