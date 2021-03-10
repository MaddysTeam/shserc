<template>
  <div class="tinymce-editor">
    <Editor
      :id="tinymceId"
      :init="init"
      v-model="myValue"
      @onClick="onClick"
    ></Editor>
  </div>
</template>

<script>
//import  {upload} from "../../app/api/file";
import tinymce from "tinymce/tinymce"; //tinymce默认hidden，不引入不显示
import Editor from "@tinymce/tinymce-vue"; //编辑器引入
import "tinymce/themes/silver/theme"; //编辑器主题
import "tinymce/skins/ui/oxide/skin.css";
// 引入编辑器插件（基本免费插件都在这儿了）
import "tinymce/plugins/advlist"; //高级列表
import "tinymce/plugins/autolink"; //自动链接
import "tinymce/plugins/link"; //超链接
import "tinymce/plugins/image"; //插入编辑图片
import "tinymce/plugins/lists"; //列表插件
import "tinymce/plugins/charmap"; //特殊字符
import "tinymce/plugins/media"; //插入编辑媒体
import "tinymce/plugins/wordcount"; // 字数统计
import "tinymce/plugins/table";
import "tinymce/langs/zh_CN.js";

// import Cookies from "js-cookie";

const fonts = [
  "宋体=宋体",
  "微软雅黑=微软雅黑",
  "新宋体=新宋体",
  "黑体=黑体",
  "楷体=楷体",
  "隶书=隶书",
  "Courier New=courier new,courier",
  "AkrutiKndPadmini=Akpdmi-n",
  "Andale Mono=andale mono,times",
  "Arial=arial,helvetica,sans-serif",
  "Arial Black=arial black,avant garde",
  "Book Antiqua=book antiqua,palatino",
  "Comic Sans MS=comic sans ms,sans-serif",
  "Courier New=courier new,courier",
  "Georgia=georgia,palatino",
  "Helvetica=helvetica",
  "Impact=impact,chicago",
  "Symbol=symbol",
  "Tahoma=tahoma,arial,helvetica,sans-serif",
  "Terminal=terminal,monaco",
  "Times New Roman=times new roman,times",
  "Trebuchet MS=trebuchet ms,geneva",
  "Verdana=verdana,geneva",
  "Webdings=webdings",
  "Wingdings=wingdings,zapf dingbats",
];
export default {
  components: {
    Editor,
  },
  props: {
    //内容
    value: {
      type: String,
      default: "",
    },
    //是否禁用
    disabled: {
      type: Boolean,
      default: false,
    },
    //插件
    plugins: {
      type: [String, Array],
      default:
        "advlist autolink link image lists charmap table media wordcount",
    },
    //工具栏
    toolbar: {
      type: [String, Array],
      default:
        "undo redo |  formatselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | lists image media table",
    },
    uploadImage: {
      type: Function,
    },
  },
  data() {
    return {
      //初始化配置
      tinymceId: "tinymce",
      myValue: this.value,
      init: {
        selector: "#tinymce",
        language_url: "tinymce/langs/zh_CN.js", //汉化路径是自定义的，一般放在public或static里面
        language: "zh_CN",
        plugins: this.plugins, //插件
        //工具栏
        toolbar: this.toolbar,
        toolbar_location: "/",
        fontsize_formats:
          "12px 14px 16px 18px 20px 22px 24px 28px 32px 36px 48px 56px 72px", //字体大小
        font_formats: fonts.join(";"),

        height: 500, //高度
        placeholder: "在这里输入文字",

        branding: false, //隐藏右下角技术支持
        //图片上传
        images_upload_handler:this.uploadFiles,
      },
    };
  },
  watch: {
    //监听内容变化
    value(newValue) {
      this.myValue = newValue == null ? "" : newValue;
    },
    myValue(newValue) {
      if (this.triggerChange) {
        this.$emit("change", newValue);
      } else {
        this.$emit("input", newValue);
      }
    },
  },
  mounted() {
    tinymce.init({});
  },
  methods: {
    onClick(e) {
      this.$emit("onClick", e, tinymce);
    },
    //可以添加一些自己的自定义事件，如清空内容
    clear() {
      this.myValue = "";
    },
    uploadFiles(blobInfo, success, failure){
      this.$emit("uploadImage",blobInfo, success, failure);
    }
  }
};
</script>