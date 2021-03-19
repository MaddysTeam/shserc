import Mock from 'mockjs'
import { userList, companyList, menuList } from './mock.json'

Mock.mock('api/user/list', 'post', (req, res) => {

    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify({ listData: userList })
    });

});

Mock.mock('api/company/search', 'post', (req, res) => {

    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify(companyList)
    });

});

// Mock.mock('api/user/' + 10, 'post', (req, res) => {
//     return Mock.mock({
//         resultCode: 200,
//         data: JSON.stringify(companyList[0])
//     });

// });

Mock.mock('api/menu/list', 'post', (req, res) => {
    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify({ listData: menuList })
    });
});
