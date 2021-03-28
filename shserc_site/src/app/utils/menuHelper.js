import { list, } from "@/app/api/menu";
import * as types from '@/app/static/type';
import  {selectParam}  from "@/app/models/menu"
import {buildHierarchy} from "@/app/utils/objectHelper"

export function initMenus(store,roleId,callback) {

    selectParam.roleId=roleId;
    
    //means get all menus
    list(selectParam).then(res => {
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