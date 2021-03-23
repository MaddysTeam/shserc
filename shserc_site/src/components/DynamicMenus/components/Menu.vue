<template>
	<div>
		<template v-for="item in this.dataSource">
			<el-submenu v-if="item.children.length>0" :key="item.id" :index="item.title">
				<template slot="title" >
                    <i :class="item.icon"></i>
					<span slot="title" style="text-align:left"  >{{item.title}}</span>
				</template>
				<Menu :dataSource="item.children"></Menu>
            </el-submenu>
            <el-menu-item v-else-if="item.children.length==0" :index="item.path" :key="item.id"  >
                <template slot="title" >
                    <i :class="item.icon"></i>
					<span slot="title">{{ item.title}}</span>
				</template>
			</el-menu-item>
	 			<el-menu-item v-else :index="item.title" :key="item.id"  >
                <i :class="item.icon" ></i>
                <template slot="title" >
					<span slot="title">{{ item.title}}</span>
				</template>
			</el-menu-item>
		</template>
	</div>
</template>

<script>
	export default {
		name: 'Menu',
		data: function() {
			return {};
		},
		props: {
			dataSource: Array
		}
	}
</script>

<style>
	.el-menu--collapse span,
	.el-menu--collapse i.el-submenu__icon-arrow 
	{
		height:0;
		width:0;
		overflow:hidden;
		visibility: hidden;
	}

    .el-menu-item,.el-submenu,.el-submenu__title{
        background:#333744 !important;
        color:#fff;
		text-align: left;
    }

     .el-submenu:hover{
        background-color: rgba(14, 221, 240, 0.32) !important;
        color:#333744 !important;
    } 

     .el-submenu__title:hover {
        background-color: rgba(14, 221, 240, 0.32) !important;
        color:#333744!important;
	}
	
	.el-menu-item.is-active{
		color:#333744!important;
		background-color: rgba(14, 221, 240, 0.32) !important;
	}

    .el-menu-item:hover{
        background-color: rgba(14, 221, 240, 0.32) !important;
        color:#333744 !important;
    } 
</style>
