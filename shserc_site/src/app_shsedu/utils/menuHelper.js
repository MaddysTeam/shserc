// import { list, } from "@/app/api/menu";
import * as types from '@/app_shsedu/static/type';
// import  {selectParam}  from "@/app/models/menu"
import {buildHierarchy} from "@/app_shsedu/utils/objectHelper"
import  {menuList} from "@/app_shsedu/api/permission"


export function initMenus(store,roleId,callback) {

  //selectParam.roleId=roleId;
    
    //TODO:will remove later
    let selectParam={}

    menuList(selectParam).then(res => {
        if (res && store) {
            let data = JSON.parse(res.data);
            console.log( buildHierarchy(data.listData));
            store.commit("app/" + types.MENUS, buildHierarchy(data.listData));
            if(callback){
                callback();
            }
        }
    });
}