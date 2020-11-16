
import { regexs } from "./regex";

export function validateRequired(rule, value, callback,error) {
    if (value == '' || value == undefined || value == null) {
        callback(new Error(error));
    }
    else{
        callback();
    }
}

export function validateLessThan50(rule, value, callback,error) {
    if (!regexs.length50.test(value)) {
        callback(new Error(error));
    }
    else{
        callback();
    }
}

export function validateSelectValue(rule,value,callback,error){
    if(value==0){
         callback(new Error(error));
    }
    else{
        callback();
    }
}

export function validateCardNo(rule,value,callback,error) { 
    if(!regexs.idCard.test(value)){
        callback(new Error(error));
    }
    else{
        callback();
    }
}
