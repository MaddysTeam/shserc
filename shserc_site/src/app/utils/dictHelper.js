import { dicList } from '@/app/api/dictionary';
import * as types from '@/app/static/type';

export function initDic(store) {
    dicList().then(res => {
        if (res && res.message && res.message == 'success' && store) {
            let data = JSON.parse(res.data);
            store.commit("app/"+types.DICTIONARY, data.listData);

            loadResourceDeformity(store, data.listData);
            laodResourceStatus(store,data.listData)
        }
    });
}

function getById(dict, id) {
    if (dict && dict instanceof Array) {
        return dict.find(o => o.id == id);
    }

    return null;
}

function getChildrenByParentId(parentId,dict){
    let children = [];
    dict.map(o => {
        if (o.parentId == parentId)
        children.push(o);
    });

    return children;
}

/**
 *  following function for dicionary business
 */

function loadResourceDeformity(store, dict) {
    let deformity = getChildrenByParentId(5,dict)
    store.commit("app/"+types.DEFORMITY, deformity)
}

function laodResourceStatus(store,dict){
    let resourceStatus = getChildrenByParentId(12,dict)
    store.commit("app/"+types.RESOURCE_STATUS, resourceStatus)
}

function bindResourceType(store,dict){}

function bindStage(store,dict){}

function bindGrade(store,dict){}

function bindDomain(store,dict){}

function bindImportSource(store,dict){}

function bindSubject(store,dict){}
