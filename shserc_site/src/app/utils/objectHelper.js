export  function clean(model){
    for (var p in model){
        model[p]=''
    }
}


export function deepCopy(target){
   return JSON.parse(JSON.stringify(target));
}
