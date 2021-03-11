export function getMenusData() {
    return [{
        id: 1,
        level: 1,
        name: '仪表板',
        path: '#',
        icon: 'el-icon-pie-chart',
        path: '/admin/dashboard/summary',
        children: []
    },
    {
        id: 2,
        level: 1,
        name: '用户管理',
        icon: 'el-icon-user',
        children: [
            {
                id: 4,
                level: 2,
                name: '实名管理',
                path: '/admin/real/list',
                icon: 'el-icon-user',
                children: []
            },
            {
                id: 5,
                level: 2,
                name: '用户查询',
                path: '/admin/user/list',
                icon: 'el-icon-user',
                children: []
            }
        ]
    }, {
        id: 3,
        level: 1,
        name: '角色管理',
        path: '/admin/role/list',
        icon: 'el-icon-s-custom',
        children: []
    },
    {
        id: 4,
        level: 1,
        name: '单位管理',
        path: '/admin/company/list',
        icon: 'el-icon-school',
        children: []
    }, {
        id: 5,
        level: 1,
        name: '资源管理',
        icon: 'el-icon-tickets',
        children: [
            {
                id: 9,
                level: 2,
                name: '入库资源',
                path: "/admin/resource/index",
                icon: 'el-icon-files',
                children: []
            }
        ]
    },
    {
        id: 6,
        level: 1,
        name: '资源包',
        path: '#',
        icon: 'el-icon-folder',
        children: []
    },
    {
        id: 7,
        level: 1,
        name: '公告管理',
        icon: 'el-icon-folder',
        children: [
            {
                id: 10,
                level: 2,
                name: '资源库公告',
                path: '/admin/bulletin/list',
                icon: 'el-icon-files',
                children: []
            }
        ]
    },
    {
        id: 8,
        level: 1,
        name: '评论管理',
        icon: 'el-icon-folder',
        children: [{
            id: 4,
            level: 2,
            name: '入库资源评论管理',
            path: "/admin/comment/list",
            icon: 'el-icon-files',
            children: []
        }]
    }
    ];
}
