
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
// /* is number */
// export function validateNunmber() { }
// /* email */
// export function validateEmail() { }
// /* phone */
// export function validatePhone() { }
// /* card Number*/
// export function validateCardNo() { }
// /* number range check */
// export function validateMax20000(rule, value, callback) {
//     if (value == '' || value == undefined || value == null) {
//         callback();
//     } else if (!Number(value)) {
//         callback(new Error('请输入[1,20000]之间的数字'));
//     } else if (value < 1 || value > 20000) {
//         callback(new Error('请输入[1,20000]之间的数字'));
//     } else {
//         callback();
//     }
// }

