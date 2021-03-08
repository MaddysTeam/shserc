import { dicList } from '@/app/api/dictionary';
import * as types from '@/app/static/type';

export function initDic(store) {
    dicList().then(res => {
        if (res && res.message && res.message == 'success' && store) {
            let data = JSON.parse(res.data);
            console.log(store)
            store.commit("app/"+types.DICTIONARY, data.listData);

            bindDeformity(store, data.listData);

        }
    });
}

/**
 *  following function for dicionary business
 */

function bindDeformity(store, dict) {
    let deformityData = [];
    dict.map(o => {
        if (o.parentId == 5)
            deformityData.push(o);
    });

    store.commit("app/"+types.DEFORMITY, deformityData)
}

function bindResourceType(store,dict){}

function bindStage(store,dict){}

function bindGrade(store,dict){}

function bindDomain(store,dict){}

function bindImportSource(store,dict){}

function bindSubject(store,dict){}

function getById(dict, id) {
    if (dict && dict instanceof Array) {
        return dict.find(o => o.id == id);
    }

    return null;
}