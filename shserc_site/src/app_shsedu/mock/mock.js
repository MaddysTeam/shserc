import Mock from "mockjs"
import { userList, menuList } from "./mock.json"

Mock.mock("api/shseduAccount/login","post",(req,res)=>{
    return Mock.mock({
        resultCode:200,
        data:JSON.stringify(userList[0])
    });
});

Mock.mock("api/shseduUser/list", "post", (req, res) => {
    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify({ listData: userList })
    });
});

Mock.mock("api/menu/list", "post", (req, res) => {
    return Mock.mock({
        resultCode: 200,
        data: JSON.stringify({ listData: menuList })
    });
});