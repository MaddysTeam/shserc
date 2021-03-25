import {CDN} from "@/static/CDN"
export const loginModel = {
    name: "",
    password: "",
    isAble: true
} 

export const changePasswordModel={
    userName: "",
    oldPassword: "",
    newPassword: "",
    confirmPassword:""
}

export const accountModel={
    name:"",
    photoPath: CDN.DEFAULT_HEADER_COVER,
    realName: "",
    idCard:"",
    mobile:"",
    company:"",
    email:""
}