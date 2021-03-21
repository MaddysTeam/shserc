import { list } from "@/app/api/menu";
import * as types from '@/app/static/type';

export function initMenus(store) {
    //means get all menus
    list().then(res => {
        if (res && store) {
            let data = JSON.parse(res.data);
            console.log(data.listData);
            store.commit("app/" + types.MENUS, data.listData);
        }
    });
}