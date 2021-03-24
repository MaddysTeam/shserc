import Mock from "mockjs"
import { userList, companyList, menuList,roleList } from "./mock.json"

Mock.mock("api/user/list", "post", (req, res) => {

    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify({ listData: userList })
    });

});

Mock.mock("api/company/search", "post", (req, res) => {

    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify(companyList)
    });

});

Mock.mock("api/role/list", "post", (req, res) => {
    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify({ listData: roleList })
    });
});

Mock.mock("api/menu/list", "post", (req, res) => {
    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify({ listData: menuList })
    });
});

Mock.mock("api/account/login","post",(req,res)=>{
    return Mock.mock({
        resultCode:200,
        data:JSON.stringify(userList[0])
    });
});

