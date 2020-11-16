import { dicList } from '../api/dictionary';
import * as types from '../business/static/type';

export function initDic(store) {
    dicList().then(res => {
        if (res && res.message && res.message == 'success' && store) {
            let data = JSON.parse(res.data);

            store.commit(types.DICTIONARY, data.listData);

            bindDeformityData(store, data.listData);

        }
    });
}

/**
 *  following function for dicionary business
 */

function bindDeformityData(store, dict) {
    let deformityData = [];
    dict.map(o => {
        if (o.parentId == 5)
            deformityData.push(o);
    });

    store.commit(types.DEFORMITY, deformityData)
}


function getById(dict, id) {
    if (dict && dict instanceof Array) {
        return dict.find(o => o.id == id);
    }

    return null;
}