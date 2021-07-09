export function clean(model) {
    for (var p in model) {
        model[p] = ''
    }
}

export function deepCopy(target) {
    return JSON.parse(JSON.stringify(target));
}

export function buildHierarchy(source) {
    for(i in source){
        source[i]["children"]=[];
        source[i]["value"]=source[i]["id"]
        source[i]["label"]=source[i]["title"]
    }
    var result = [];
    for (var i = 0; i < source.length; i++) {
        var current = source[i];
        if (current.parentId > 0) {
            var parent = searchHierarchyTarget(result, current.parentId);
            if (parent) {
                parent.children.push(current);
            }
        } else {
            result.push(current);
        }
    }

    return result;
}

export function searchHierarchyTarget(source, targetId) {
    for (var idx in source) {
        var current = source[idx];
        if (current.id == targetId) {
            return current;
        } else if(current.children.length>0) {
            for (var idx2 in current.children) {
                 searchHierarchyTarget(current.children, targetId);
            }
        }
    }
}
