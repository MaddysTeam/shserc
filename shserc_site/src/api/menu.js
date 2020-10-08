export function getMenusData() {
	return [{
        id:1,
		level:1,
		name: '用户管理',
        path:'/admin/users',
        children:[]
    },{
        id:2,
        level:1,
        name:'单位管理',
        path:'/admin/company',
        children:[]
    }];
}
