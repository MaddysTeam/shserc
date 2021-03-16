export const resourceModel = {
   id: 0,
   title: "",
   state: "",
   stateId: 0, // 未审核
   deformityId: "",
   deformity: "",
   author: "",
   authorEmail: "",
   authorCompany: "",
   authorPhone: "",
   keywords: null,
   description: "",
   resourcePath: "",
   fileName: "",
   fileExtName: "",
   fileSize: 0,
   mediumType: "",
   mediumTypeId: 0,
   resourceType: "",
   resourceTypeId: 0,
   domainId: 0,
   domain:"",
   learnFrom:"",
   learnFromId: 0,
   schoolType:"",
   schoolTypeId: 0,
   subject: "",
   subjectId: 0,
   stage:"",
   stageId: 0,
   grade: "",
   gradeId: 0,
   coverPath: "",
   auditOpinion: ""
};

export const auditModel = {
   resourceId: 0,
   resourceTitle:"",
   auditOpinion: "",
   auditResult: false
}

export const selectParam = {

   current: 1,
   total: 0,
   size: 10,
   searchPhrase: "",

   userId: 0,
   resourceId: 0,
   deformityId: 0,
   stateId: 0,
   subjectId: 0,
   domainId: 0,
   importSourceId: 0,
   gradeId: 0,
   stageId: 0
}
