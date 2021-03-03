export  function clean(model){
    for (var p in model){
        model[p]=''
    }
}