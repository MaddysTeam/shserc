import { list } from "@/app/api/role";
import {selectParam} from "@/app/models/role"
import * as types from '@/app/static/type';

export function initRoles(store) {
    //means get all roles
    selectParam.size=10000;
    list(selectParam).then(res => {
        if (res && store) {
            
            let data = JSON.parse(res.data);
            console.log(data.listData);
            store.commit("app/" + types.ROLES, data.listData);
        }
    });
}