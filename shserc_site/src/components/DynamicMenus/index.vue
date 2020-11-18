<template>
	<div>
		<el-menu class="el-menu" router :collapse="isCllapse">
			<Menu :dataSource="dataSource"></Menu>
		</el-menu>
	</div>
</template>
<script>
	import Menu from './components/Menu.vue';
	export default {
		name: 'dynamicMenus',
		components: {
			Menu
		},
		props: ['isCllapse','dataSource'],
	};
</script>

<style scoped>

	.el-menu:not(.el-menu--collapse) {
		width:100%; 
	}
</style>
