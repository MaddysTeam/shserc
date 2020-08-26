export function getMenusData() {
	return [{
        id: 1,
        level:1,
		name: 'menu1',
		children: [{
				id: 2,
				name: '用户管理',
				path: '/users',
				children: []
			},
			{
				id: 3,
				name: 'menu1-2',
				path: '/login2',
				children: [{
						id: 4,
						name: '表单示例',
						path: '/form',
						children: []
					},
					{
						id: 5,
						name: 'menu1-2-2',
						path: '/login4',
						children: []
					}
				]
			}
		]
	},{
        id:2,
        level:1,
        name:'menu2',
        children:[]
    }];
}
