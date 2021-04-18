import { dicList } from '@/app/api/dictionary';
import * as types from '@/app/static/type';

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

/**
 *  following function for dictionary business
 */

function bindResourceDeformity(store, dict) {
    let deformity = getChildrenByParentId(5, dict)
    store.commit(types.APP_DEFORMITY, deformity)
}

function bindResourceStatus(store, dict) {
    let resourceStatus = getChildrenByParentId(12, dict)
    store.commit(types.APP_RESOURCE_STATUS, resourceStatus)
}

function bindResourceType(store, dict) {
    let resourceTypes = getChildrenByParentId(87, dict)
    store.commit(types.APP_RESOURCE_TYPES, resourceTypes)
}

function bindStage(store, dict) {
    let stages = getChildrenByParentId(35, dict)
    store.commit(types.APP_STAGES, stages)
}

function bindGrade(store, dict) {
    let grades = getChildrenByParentId(43, dict)
    store.commit(types.APP_GRADES, grades)
}

function bindDomain(store, dict) {
    let domains = getChildrenByParentId(15, dict)
    store.commit(types.APP_RESOURCE_DOMAINS, domains)
}

function bindImportSource(store, dict) {
    let source = getChildrenByParentId(77, dict)
    store.commit(types.APP_SOURCE, source)
}

function bindSubject(store, dict) {
    let subjects = getChildrenByParentId(139, dict)
    store.commit(types.APP_SUBJECTS, subjects)
}

function bindSchoolTypes(store, dict) {
    let schoolTypes = getChildrenByParentId(27, dict)
    store.commit(types.APP_SCHOOLTYPES, schoolTypes)
}

function bindLearnFrom(store, dict) {
    let learnFrom = getChildrenByParentId(21, dict)
    store.commit(types.APP_LEARNFROM, learnFrom)
}

function bindState(store, dict) {
    let states = getChildrenByParentId(0, dict)
    store.commit(types.APP_STATES, states)
}

function bindDictData(store, dict) {
    bindResourceDeformity(store, dict);
    bindResourceStatus(store, dict);
    bindResourceType(store, dict);
    bindStage(store, dict);
    bindGrade(store, dict);
    bindDomain(store, dict);
    bindImportSource(store, dict);
    bindSubject(store, dict);
    bindSchoolTypes(store, dict);
    bindLearnFrom(store, dict);
    bindState(store, dict);
}