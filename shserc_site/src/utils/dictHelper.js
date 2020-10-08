import { dicList } from '../api/dictionary';
import * as types from '../static/type';

export function initDic(store) {
    // dicList().then(res=>{
    //     if(res.success && store){
    //         store.commit("setSex", res.result);
    //     }
    // });
    store.commit(types.GENDER, [{ label: '男', value: 1 }, { label: '女', value: 0 }]);
}