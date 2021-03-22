export function clean(model) {
    for (var p in model) {
        model[p] = ''
    }
}

export function deepCopy(target) {
    return JSON.parse(JSON.stringify(target));
}

export function buildHierarchy(source) {
    var result = [];
    for (var i = 0; i < source.length; i++) {
        var current = source[i];
        current["children"]=[];
        if (current.parentId > 0) {
            let parent = searchHierarchyTarget(result, current.parentId);
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
    for (let idx in source) {
        let current = source[idx];
        if (current.id == targetId) {
            return current;
        } else {
            for (let idx2 in current.children) {
                return searchHierarchyTarget(current.children, targetId);
            }
        }
    }
}
