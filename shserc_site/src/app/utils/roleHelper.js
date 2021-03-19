import { list } from "@/app/api/role";
import {selectParam} from "@/app/models/role"

export function initRoles(store) {
    //means get all roles
    selectParam.size=10000;

    list(selectParam).then(res => {
        if (res && res.message && res.message == 'success' && store) {
            let data = JSON.parse(res.data);
            store.commit("app/" + types.ROLES, data.listData);
        }
    });
}