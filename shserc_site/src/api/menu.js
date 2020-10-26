export function getMenusData() {
	return [{
        id:1,
		level:1,
		name: '用户管理',
        path:'/admin/user/list',
        children:[]
    },{
        id:2,
        level:1,
        name:'单位管理',
        path:'/admin/company/list',
        children:[]
    },{
        id:3,
        level:1,
        name:'资源管理',
        path:'/admin/resource/list',
        children:[]
    }];
}
