import { dicList } from '@/app_shsedu/api/dictionary';
import * as types from '@/app_shsedu/static/type';

/**
 *  init dict data , first get it from localstorage otherwise get from server
 */
export function initDic(store) {
    let dict = store.state.app.dict;
    if (dict) {
        bindDictData(store, dict);
    } else {
        dicList().then(res => {
            if (res && res.message && res.message == 'success' && store) {
                dict = JSON.parse(res.data).listData;
                store.commit(types.APP_DICTIONARY, dict);
                bindDictData(store, dict)
            }
        });
    }
}

/**
 *  get dict data by id
 */
export function getById(dict, id) {
    if (dict && dict instanceof Array) {
        return dict.find(o => o.id == id);
    }

    return null;
}

export function getChildrenByParentId(parentId, dict) {
    let children = [];
    dict.map(o => {
        if (o.parentId == parentId)
            children.push(o);
    });

    return children;
}

export function getRelevantByRelevantId(relevantId, dict) {
    let relevants = [];
    dict.map(o => {
        if (o.relevantId == relevantId)
            relevants.push(o);
    });

    return relevants;
}

export function getTargetRelevant(relevantId, dict) {
    let relevants = [];
    dict.map(o => {
        if (o.id == relevantId)
            relevants.push(o);
    });

    return relevants;
}



function bindDictData(store, dict) {
    for (var i in types.DICTIONARY_TYPES) {

        var children = getChildrenByParentId(types.DICTIONARY_TYPES[i].id, dict);
        store.commit(types.DICTIONARY_TYPES[i].type, children)
    }
}