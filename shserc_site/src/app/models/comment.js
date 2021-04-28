import { CDN } from "@/static/CDN"

export const commentModel = {
    id: 0,
    userId: 0,
    auditorName: "",
    addUserName: "",
    resourceTitle: "",
    resourceId: 0,
    content: "",
    auditType: "",
    commentTime: "",
    userPhotoPath: CDN.DEFAULT_HEADER_COVER
}

export const selectParam = {
    current: 1,
    total: 0,
    size: 10,
    resourceId: 0,
    auditor: 0,
    auditTypeId: 0,
    stateId: 0,
    searchPhrase: "",
}