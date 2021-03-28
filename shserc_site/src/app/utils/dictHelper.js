import { dicList } from '@/app/api/dictionary';
import * as types from '@/app/static/type';

export function initDic(store) {
    dicList().then(res => {
        if (res && res.message && res.message == 'success' && store) {
            let data = JSON.parse(res.data);
            store.commit("app/" + types.DICTIONARY, data.listData);

            loadResourceDeformity(store, data.listData);
            laodResourceStatus(store, data.listData);
            bindResourceType(store, data.listData);
            bindStage(store, data.listData);
            bindGrade(store, data.listData);
            bindDomain(store, data.listData);
            bindImportSource(store, data.listData);
            bindSubject(store, data.listData);
            bindSchoolTypes(store, data.listData);
            bindLearnFrom(store, data.listData);
            //TODO:bindState(store,data.listData);
        }
    });
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

function loadResourceDeformity(store, dict) {
    let deformity = getChildrenByParentId(5, dict)
    store.commit("app/" + types.DEFORMITY, deformity)
}

function laodResourceStatus(store, dict) {
    let resourceStatus = getChildrenByParentId(12, dict)
    store.commit("app/" + types.RESOURCE_STATUS, resourceStatus)
}

function bindResourceType(store, dict) { 
    let resourceTypes = getChildrenByParentId(87, dict)
    store.commit("app/" + types.RESOURCE_TYPES, resourceTypes)
}

function bindStage(store, dict) { 
    let stages = getChildrenByParentId(35, dict)
    store.commit("app/" + types.STAGES, stages)
}

function bindGrade(store, dict) { 
    let grades = getChildrenByParentId(43, dict)
    store.commit("app/" + types.GRADES, grades)
}

 function bindDomain(store, dict) {
    let domains = getChildrenByParentId(15, dict)
    store.commit("app/" + types.RESOURCE_DOMAINS, domains)
 }

function bindImportSource(store, dict) { 
    let source = getChildrenByParentId(77, dict)
    store.commit("app/" + types.SOURCE, source)
}

function bindSubject(store, dict) { 
    let subjects = getChildrenByParentId(139, dict)
    store.commit("app/" + types.SUBJECTS, subjects)
}

function bindSchoolTypes(store, dict) { 
    let schoolTypes = getChildrenByParentId(27, dict)
    store.commit("app/" + types.SCHOOLTYPES, schoolTypes)
}

function bindLearnFrom(store, dict) { 
    let learnFrom = getChildrenByParentId(21, dict)
    store.commit("app/" + types.LEARNFROM, learnFrom)
}

function bindState(store,dict){
    let states = getChildrenByParentId(0, dict)
    store.commit("app/" + types.STATES, states)
}