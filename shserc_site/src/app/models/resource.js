import { CDN } from "@/static/CDN"

export const resourceModel = {
    id: 0,
    title: "资源名称",
    state: "",
    stateId: 10002,
    deformityId: "",
    deformity: "",
    author: "",
    authorEmail: "",
    authorCompany: "",
    authorPhone: "",
    keywords: "",
    description: "",
    resourcePath: "",
    fileName: "",
    fileExtName: "",
    fileSize: 0,
    domain: "",
    domainId: 0,
    mediumType: "",
    mediumTypeId: 0,
    resourceType: "",
    resourceTypeId: 0,
    domainId: 0,
    learnFrom: "",
    learnFromId: 0,
    schoolType: "",
    schoolTypeId: 0,
    subject: "",
    subjectId: 0,
    stage: "",
    stageId: 0,
    grade: "",
    gradeId: 0,
    coverPath: CDN.DEFAULT_COVER,
    viewCount: 0,
    downloadCount: 0,
    favoriteCount: 0,
    commentCount: 0,
    isVedio: false,
    isFile: false

};

export const auditModel = {
    resourceId: 0,
    stateId: 0,
    auditorId: 0,
    auditOpinion: ""
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
    stageId: 0,
    resourceTypeId: 0,
    orderPhrases: {}
}

export const orderPhrasesModel = {
    favoriteCount: "favorite",
    viewCount: "view",
    downloadCount: "download"
}


//  video optons only for resouce 
export function videoOptions(videoPath, videoCover) {
    return {
        playbackRates: [0.5, 1.0, 1.5, 2.0], //可选择的播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 视频一结束就重新开始。
        preload: "auto", // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: "zh-CN",
        aspectRatio: "16:9", // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
            type: "",
            src: videoPath
                //"http://shlll.s.wcsapi.biz.matocloud.com/upload201909261929354303.mp4", //url地址
        }, ],
        poster: videoCover, //你的封面地址
        // width: document.documentElement.clientWidth,
        notSupportedMessage: "此视频暂无法播放，请稍后再试", //允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
            timeDivider: true, //当前时间和持续时间的分隔符
            durationDisplay: true, //显示持续时间
            remainingTimeDisplay: false, //是否显示剩余时间功能
            fullscreenToggle: true, //全屏按钮
        }
    }
}