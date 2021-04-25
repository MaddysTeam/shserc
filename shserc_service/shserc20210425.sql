/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.13
Source Server Version : 50734
Source Host           : 192.168.1.13:30070
Source Database       : shserc

Target Server Type    : MYSQL
Target Server Version : 50734
File Encoding         : 65001

Date: 2021-04-25 21:56:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bulletin`
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` bigint(20) DEFAULT NULL,
  `content` longtext NOT NULL,
  `title` varchar(100) NOT NULL,
  `top` bit(1) NOT NULL DEFAULT b'0',
  `file_path` varchar(300) DEFAULT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  `file_ext_name` varchar(10) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT '0',
  `add_user` bigint(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bulletin
-- ----------------------------
INSERT INTO `bulletin` VALUES ('1', '0', '<p>eee</p>', '测试公告', '', '', '', '', '0', '0', '2021-04-22 15:43:31', '1', '2021-04-22 15:43:36', '0');

-- ----------------------------
-- Table structure for `cro_comment`
-- ----------------------------
DROP TABLE IF EXISTS `cro_comment`;
CREATE TABLE `cro_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `auditor` bigint(20) DEFAULT NULL,
  `auditor_opinion` varchar(300) DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `auditor_time` datetime DEFAULT NULL,
  `state_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cro_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `cro_download`
-- ----------------------------
DROP TABLE IF EXISTS `cro_download`;
CREATE TABLE `cro_download` (
  `cro_source_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cro_download
-- ----------------------------

-- ----------------------------
-- Table structure for `cro_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `cro_favorite`;
CREATE TABLE `cro_favorite` (
  `cro_source_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of cro_favorite
-- ----------------------------

-- ----------------------------
-- Table structure for `cro_resource`
-- ----------------------------
DROP TABLE IF EXISTS `cro_resource`;
CREATE TABLE `cro_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cro_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `cro_star`
-- ----------------------------
DROP TABLE IF EXISTS `cro_star`;
CREATE TABLE `cro_star` (
  `cro_source_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `Score` int(11) DEFAULT '0',
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of cro_star
-- ----------------------------

-- ----------------------------
-- Table structure for `cro_view`
-- ----------------------------
DROP TABLE IF EXISTS `cro_view`;
CREATE TABLE `cro_view` (
  `cro_source_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of cro_view
-- ----------------------------

-- ----------------------------
-- Table structure for `dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parentId` bigint(20) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `relevant_id` bigint(20) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', '字典表', '0', null, 'dict', null, null, '1');
INSERT INTO `dictionary` VALUES ('2', '性别', '1', null, 'gender', null, null, '2');
INSERT INTO `dictionary` VALUES ('3', '男', '2', null, 'mail', null, null, '3');
INSERT INTO `dictionary` VALUES ('4', '女', '2', null, 'femail', null, null, '4');
INSERT INTO `dictionary` VALUES ('5', '残疾类型', '1', null, 'deformity', null, null, '5');
INSERT INTO `dictionary` VALUES ('6', '智力残疾', '5', null, 'intellegent', null, null, '6');
INSERT INTO `dictionary` VALUES ('7', '肢体残疾', '5', null, 'physical', null, null, '7');
INSERT INTO `dictionary` VALUES ('8', '视力残疾', '5', null, 'visual', null, null, '8');
INSERT INTO `dictionary` VALUES ('9', '听力残疾', '5', null, 'hearing', null, null, '9');
INSERT INTO `dictionary` VALUES ('10', '言语残疾', '5', null, 'speech', null, null, '10');
INSERT INTO `dictionary` VALUES ('11', '精神残疾', '5', null, 'mental', null, null, '11');
INSERT INTO `dictionary` VALUES ('12', '资源状态', '1', null, 'state', null, null, '12');
INSERT INTO `dictionary` VALUES ('13', '可用', '12', null, 'enable', null, null, '13');
INSERT INTO `dictionary` VALUES ('14', '禁用', '12', null, 'disable', null, null, '14');
INSERT INTO `dictionary` VALUES ('15', '资源领域', '1', null, 'domain', null, null, '15');
INSERT INTO `dictionary` VALUES ('16', '政策与文献', '15', null, 'domaimItem1', null, null, '16');
INSERT INTO `dictionary` VALUES ('17', '诊断与评估', '15', null, 'domaimItem2', null, null, '17');
INSERT INTO `dictionary` VALUES ('18', '课程与教学', '15', null, 'domaimItem3', null, null, '18');
INSERT INTO `dictionary` VALUES ('19', '康复与干预', '15', null, 'domaimItem4', null, null, '19');
INSERT INTO `dictionary` VALUES ('20', '支持与服务', '15', null, 'domaimItem5', null, null, '20');
INSERT INTO `dictionary` VALUES ('21', '安置类型', '1', null, 'learnFrom', null, null, '21');
INSERT INTO `dictionary` VALUES ('22', '特殊学校', '21', null, 'learnFromItem1', null, null, '22');
INSERT INTO `dictionary` VALUES ('23', '特教班', '21', null, 'learnFromItem2', null, null, '23');
INSERT INTO `dictionary` VALUES ('24', '送教上门', '21', null, 'learnFromItem3', null, null, '24');
INSERT INTO `dictionary` VALUES ('25', '随班就读', '21', null, 'learnFromItem4', null, null, '25');
INSERT INTO `dictionary` VALUES ('26', '其他', '21', null, 'learnFromItem5', null, null, '26');
INSERT INTO `dictionary` VALUES ('27', '学校类型', '1', null, 'schoolType', null, null, '27');
INSERT INTO `dictionary` VALUES ('28', '聋校', '27', null, 'schoolTypeItem1', null, null, '28');
INSERT INTO `dictionary` VALUES ('29', '幼儿园', '27', null, 'schoolTypeItem2', null, null, '29');
INSERT INTO `dictionary` VALUES ('30', '辅读学校', '27', null, 'schoolTypeItem3', null, null, '30');
INSERT INTO `dictionary` VALUES ('31', '普通学校', '27', null, 'schoolTypeItem4', null, null, '31');
INSERT INTO `dictionary` VALUES ('32', '盲校', '27', null, 'schoolTypeItem5', null, null, '32');
INSERT INTO `dictionary` VALUES ('33', '职业技术学校', '27', null, 'schoolTypeItem6', null, null, '33');
INSERT INTO `dictionary` VALUES ('34', '大学', '27', null, 'schoolTypeItem7', null, null, '34');
INSERT INTO `dictionary` VALUES ('35', '学段', '1', null, 'stage', null, null, '35');
INSERT INTO `dictionary` VALUES ('36', '学前教育', '35', null, 'stageItem1', null, null, '36');
INSERT INTO `dictionary` VALUES ('37', '义务教育', '35', null, 'stageItem2', null, null, '37');
INSERT INTO `dictionary` VALUES ('38', '高中教育', '35', null, 'stageItem3', null, null, '38');
INSERT INTO `dictionary` VALUES ('39', '初职教育', '35', null, 'stageItem4', null, null, '39');
INSERT INTO `dictionary` VALUES ('40', '中职教育', '35', null, 'stageItem5', null, null, '40');
INSERT INTO `dictionary` VALUES ('41', '高等教育', '35', null, 'stageItem6', null, null, '41');
INSERT INTO `dictionary` VALUES ('42', '终身教育', '35', null, 'stageItem7', null, null, '42');
INSERT INTO `dictionary` VALUES ('43', '年级', '1', null, 'grade', null, null, '43');
INSERT INTO `dictionary` VALUES ('44', '一年级', '43', '', 'gradeitem1', null, '37', '44');
INSERT INTO `dictionary` VALUES ('45', '二年级', '43', '', 'gradeitem2', null, '37', '45');
INSERT INTO `dictionary` VALUES ('46', '三年级', '43', null, 'gradeitem3', null, '37', '46');
INSERT INTO `dictionary` VALUES ('47', '四年级', '43', null, 'gradeitem4', null, '37', '47');
INSERT INTO `dictionary` VALUES ('48', '五年级', '43', null, 'gradeitem5', null, '37', '48');
INSERT INTO `dictionary` VALUES ('49', '六年级', '43', null, 'gradeitem6', null, '37', '49');
INSERT INTO `dictionary` VALUES ('50', '七年级', '43', null, 'gradeitem7', null, '37', '50');
INSERT INTO `dictionary` VALUES ('51', '八年级', '43', null, 'gradeitem8', null, '37', '51');
INSERT INTO `dictionary` VALUES ('52', '九年级', '43', null, 'gradeitem9', null, '37', '52');
INSERT INTO `dictionary` VALUES ('53', '低年级', '43', null, 'gradeitem10', null, '37', '53');
INSERT INTO `dictionary` VALUES ('54', '中年级', '43', null, 'gradeitem11', null, '37', '54');
INSERT INTO `dictionary` VALUES ('55', '高年级', '43', null, 'gradeitem12', null, '37', '55');
INSERT INTO `dictionary` VALUES ('56', '小学段', '43', null, 'gradeitem13', null, '37', '56');
INSERT INTO `dictionary` VALUES ('57', '初中段', '43', null, 'gradeitem14', null, '37', '57');
INSERT INTO `dictionary` VALUES ('58', '高一', '43', null, 'gradeitem15', null, '38', '58');
INSERT INTO `dictionary` VALUES ('59', '高二', '43', null, 'gradeitem16', null, '38', '59');
INSERT INTO `dictionary` VALUES ('60', '高三', '43', null, 'gradeitem17', null, '38', '60');
INSERT INTO `dictionary` VALUES ('61', '高四', '43', null, 'gradeitem18', null, '38', '61');
INSERT INTO `dictionary` VALUES ('62', '初职一', '43', null, 'gradeitem19', null, '39', '62');
INSERT INTO `dictionary` VALUES ('63', '初职二', '43', null, 'gradeitem20', null, '39', '63');
INSERT INTO `dictionary` VALUES ('64', '初职三', '43', null, 'gradeitem21', null, '39', '64');
INSERT INTO `dictionary` VALUES ('65', '初职四', '43', null, 'gradeitem22', null, '39', '65');
INSERT INTO `dictionary` VALUES ('66', '中职一', '43', null, 'gradeitem23', null, '40', '66');
INSERT INTO `dictionary` VALUES ('67', '中职二', '43', null, 'gradeitem24', null, '40', '67');
INSERT INTO `dictionary` VALUES ('68', '中职三', '43', null, 'gradeitem25', null, '40', '68');
INSERT INTO `dictionary` VALUES ('69', '中职四', '43', null, 'gradeitem26', null, '40', '69');
INSERT INTO `dictionary` VALUES ('73', '来源类型', '1', null, 'importSource', null, null, '73');
INSERT INTO `dictionary` VALUES ('74', '系统导入', '73', null, 'importSourceItem1', null, null, '74');
INSERT INTO `dictionary` VALUES ('75', '用户上传', '73', null, 'importSourceItem2', null, null, '75');
INSERT INTO `dictionary` VALUES ('76', '资源类型', '1', null, 'resourceType', null, null, '76');
INSERT INTO `dictionary` VALUES ('77', '政策', '76', null, 'resourceTypeItem1', null, '16', '77');
INSERT INTO `dictionary` VALUES ('78', '评估工具与方法', '76', null, 'resourceTypeItem2', null, '17', '78');
INSERT INTO `dictionary` VALUES ('79', '评估报告', '76', null, 'resourceTypeItem3', null, '17', '79');
INSERT INTO `dictionary` VALUES ('80', '课程方案与标准', '76', null, 'resourceTypeItem4', null, '18', '80');
INSERT INTO `dictionary` VALUES ('81', '教学设计', '76', null, 'resourceTypeItem5', null, '18', '81');
INSERT INTO `dictionary` VALUES ('82', '教学课件', '76', null, 'resourceTypeItem6', null, '18', '82');
INSERT INTO `dictionary` VALUES ('83', '教学实录', '76', null, 'resourceTypeItem7', null, '18', '83');
INSERT INTO `dictionary` VALUES ('84', '个别化教育计划', '76', null, 'resourceTypeItem8', null, '18', '84');
INSERT INTO `dictionary` VALUES ('85', '康复训练设计', '76', null, 'resourceTypeItem9', null, '19', '85');
INSERT INTO `dictionary` VALUES ('86', '康复训练课件', '76', null, 'resourceTypeItem10', null, '19', '86');
INSERT INTO `dictionary` VALUES ('87', '康复训练实录', '76', null, 'resourceTypeItem11', null, '19', '87');
INSERT INTO `dictionary` VALUES ('88', '残疾人支持服务项目介绍', '76', null, 'resourceTypeItem12', null, '20', '88');
INSERT INTO `dictionary` VALUES ('89', '评估视频', '76', null, 'resourceTypeItem13', null, '17', '89');
INSERT INTO `dictionary` VALUES ('90', '教学案例', '76', null, 'resourceTypeItem14', null, '18', '90');
INSERT INTO `dictionary` VALUES ('91', '教学资源包', '76', null, 'resourceTypeItem15', null, '18', '91');
INSERT INTO `dictionary` VALUES ('92', '校本教材', '76', null, 'resourceTypeItem16', null, '18', '92');
INSERT INTO `dictionary` VALUES ('93', '辅助器具介绍', '76', null, 'resourceTypeItem17', null, '20', '93');
INSERT INTO `dictionary` VALUES ('94', '教具学具介绍', '76', null, 'resourceTypeItem18', null, '20', '94');
INSERT INTO `dictionary` VALUES ('95', '支持与服务个案报告', '76', null, 'resourceTypeItem19', null, '19', '95');
INSERT INTO `dictionary` VALUES ('96', '文献', '76', null, 'resourceTypeItem20', null, '16', '96');
INSERT INTO `dictionary` VALUES ('97', '康复训练案例', '76', null, 'resourceTypeItem21', null, '18', '97');
INSERT INTO `dictionary` VALUES ('98', '个别化康复训练计划', '76', null, 'resourceTypeItem22', null, '19', '98');
INSERT INTO `dictionary` VALUES ('99', '家庭教育', '76', null, 'resourceTypeItem23', null, null, '99');
INSERT INTO `dictionary` VALUES ('100', '学科', '1', null, 'subject', null, null, '100');
INSERT INTO `dictionary` VALUES ('101', '生活自理', '100', null, 'subjectItem1', null, null, '101');
INSERT INTO `dictionary` VALUES ('102', '语文', '100', null, 'subjectItem2', null, null, '102');
INSERT INTO `dictionary` VALUES ('103', '数学', '100', null, 'subjectItem3', null, null, '103');
INSERT INTO `dictionary` VALUES ('104', '英语', '100', null, 'subjectItem4', null, null, '104');
INSERT INTO `dictionary` VALUES ('105', '科学', '100', null, 'subjectItem5', null, null, '105');
INSERT INTO `dictionary` VALUES ('106', '生活', '100', null, 'subjectItem6', null, null, '106');
INSERT INTO `dictionary` VALUES ('107', '品德与社会', '100', null, 'subjectItem7', null, null, '107');
INSERT INTO `dictionary` VALUES ('108', '社会', '100', null, 'subjectItem8', null, null, '108');
INSERT INTO `dictionary` VALUES ('109', '信息技术', '100', null, 'subjectItem9', null, null, '109');
INSERT INTO `dictionary` VALUES ('110', '艺术', '100', null, 'subjectItem10', null, null, '110');
INSERT INTO `dictionary` VALUES ('111', '劳动技术', '100', null, 'subjectItem11', null, null, '111');
INSERT INTO `dictionary` VALUES ('112', '职业技术', '100', null, 'subjectItem12', null, null, '112');
INSERT INTO `dictionary` VALUES ('113', '体育与健康', '100', null, 'subjectItem13', null, null, '113');
INSERT INTO `dictionary` VALUES ('114', '言语沟通训练', '100', null, 'subjectItem14', null, null, '114');
INSERT INTO `dictionary` VALUES ('115', '感知运动训练', '100', null, 'subjectItem15', null, null, '115');
INSERT INTO `dictionary` VALUES ('116', '行为训练', '100', null, 'subjectItem16', null, null, '116');
INSERT INTO `dictionary` VALUES ('117', '定向行走训练', '100', null, 'subjectItem17', null, null, '117');
INSERT INTO `dictionary` VALUES ('118', '综合康复', '100', null, 'subjectItem18', null, null, '118');
INSERT INTO `dictionary` VALUES ('119', '拓展', '100', null, 'subjectItem19', null, null, '119');
INSERT INTO `dictionary` VALUES ('120', '综合实践活动', '100', null, 'subjectItem20', null, null, '120');
INSERT INTO `dictionary` VALUES ('121', '学前特教', '100', null, 'subjectItem21', null, null, '121');
INSERT INTO `dictionary` VALUES ('122', '物理', '100', null, 'subjectItem22', null, null, '122');
INSERT INTO `dictionary` VALUES ('123', '精细动作训练', '100', null, 'subjectItem23', null, null, '123');
INSERT INTO `dictionary` VALUES ('124', '认知', '100', null, 'subjectItem24', null, null, '124');
INSERT INTO `dictionary` VALUES ('125', '按摩职业教育', '100', null, 'subjectItem25', null, null, '125');
INSERT INTO `dictionary` VALUES ('126', '个训或小组训练', '100', null, 'subjectItem26', null, null, '126');
INSERT INTO `dictionary` VALUES ('127', '其他学科', '100', null, 'subjectItem27', null, null, '127');
INSERT INTO `dictionary` VALUES ('129', '审核通过', '12', null, 'auditStatusItem1', null, null, '129');
INSERT INTO `dictionary` VALUES ('130', '审核不通过', '12', null, 'auditStatusItem2', null, null, '130');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL DEFAULT '0',
  `level` bigint(20) NOT NULL,
  `path` varchar(100) NOT NULL,
  `title` varchar(50) NOT NULL,
  `state_id` bigint(20) DEFAULT NULL,
  `add_user` bigint(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='menus';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '0', '/admin/dashboard/summary', '仪表板', '0', '0', '2021-04-22 15:00:49', '0', null, '0');
INSERT INTO `menu` VALUES ('2', '0', '0', '/admin/user', '用户管理', '0', '0', '2021-04-22 15:01:41', '0', null, '0');
INSERT INTO `menu` VALUES ('3', '2', '0', '/admin/real/list', '实名管理', '0', '0', '2021-04-22 15:03:05', '0', null, '0');
INSERT INTO `menu` VALUES ('4', '2', '0', '/admin/user/list', '用户查询', '0', '0', '2021-04-22 15:03:33', '0', null, '0');
INSERT INTO `menu` VALUES ('5', '0', '0', '/admin/role/list', '角色管理', '0', '0', '2021-04-22 15:04:51', '0', null, '0');
INSERT INTO `menu` VALUES ('6', '0', '0', '/admin/company/list', '单位管理', '0', '0', '2021-04-22 15:05:06', '0', null, '0');
INSERT INTO `menu` VALUES ('7', '0', '0', '/admin/resource', '资源管理', '0', '0', '2021-04-22 15:05:19', '0', null, '0');
INSERT INTO `menu` VALUES ('8', '7', '0', '/admin/resource/index', '入库资源', '0', '0', '2021-04-22 15:06:38', '0', null, '0');
INSERT INTO `menu` VALUES ('9', '7', '0', '/admin/croResource/index', '众筹资源', '0', '0', '2021-04-22 15:07:30', '0', '2021-04-22 15:09:13', '0');
INSERT INTO `menu` VALUES ('10', '7', '0', '#', '资源包', '0', '0', '2021-04-22 15:09:39', '0', null, '0');
INSERT INTO `menu` VALUES ('11', '0', '0', '/admin/bulletin', '公告管理', '0', '0', '2021-04-22 15:35:17', '0', null, '0');
INSERT INTO `menu` VALUES ('12', '0', '0', '/admin/comment/list', '评论管理', '0', '0', '2021-04-22 15:35:45', '0', null, '0');
INSERT INTO `menu` VALUES ('13', '0', '0', '/admin/menu/list', '菜单管理', '0', '0', '2021-04-22 15:35:56', '0', null, '0');
INSERT INTO `menu` VALUES ('14', '11', '0', '/admin/bulletin/list', '资源库公告', '0', '0', '2021-04-22 15:36:34', '0', null, '0');
INSERT INTO `menu` VALUES ('15', '12', '0', '/admin/comment/list', '入库资源评论管理', '0', '0', '2021-04-22 15:36:51', '0', null, '0');

-- ----------------------------
-- Table structure for `menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for `package`
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of package
-- ----------------------------

-- ----------------------------
-- Table structure for `package_download`
-- ----------------------------
DROP TABLE IF EXISTS `package_download`;
CREATE TABLE `package_download` (
  `package_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of package_download
-- ----------------------------

-- ----------------------------
-- Table structure for `package_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `package_favorite`;
CREATE TABLE `package_favorite` (
  `package_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of package_favorite
-- ----------------------------

-- ----------------------------
-- Table structure for `package_star`
-- ----------------------------
DROP TABLE IF EXISTS `package_star`;
CREATE TABLE `package_star` (
  `package_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `Score` int(11) DEFAULT '0',
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of package_star
-- ----------------------------

-- ----------------------------
-- Table structure for `package_view`
-- ----------------------------
DROP TABLE IF EXISTS `package_view`;
CREATE TABLE `package_view` (
  `package_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of package_view
-- ----------------------------

-- ----------------------------
-- Table structure for `res_comment`
-- ----------------------------
DROP TABLE IF EXISTS `res_comment`;
CREATE TABLE `res_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `auditor` bigint(20) DEFAULT NULL,
  `auditor_opinion` varchar(300) DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `auditor_time` datetime DEFAULT NULL,
  `state_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_comment
-- ----------------------------
INSERT INTO `res_comment` VALUES ('1', '1', '1', 'hellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohello', null, null, '1', '2021-04-15 00:00:00', null, null, '0', null, '129');

-- ----------------------------
-- Table structure for `res_company`
-- ----------------------------
DROP TABLE IF EXISTS `res_company`;
CREATE TABLE `res_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  `add_user` bigint(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_company
-- ----------------------------
INSERT INTO `res_company` VALUES ('1', '上海市', '0', '1', '2020-09-12 21:40:13', '1', '2020-09-12 21:40:22', '0');
INSERT INTO `res_company` VALUES ('2', '杨浦区', '1', '1', '2020-09-12 21:40:51', '1', '2020-09-26 21:40:56', '0');
INSERT INTO `res_company` VALUES ('3', '虹口区', '1', '1', '2020-09-12 21:41:46', '1', '2020-09-12 21:41:48', '0');
INSERT INTO `res_company` VALUES ('4', '凉城第二小学', '3', '1', '2020-09-12 22:32:21', '1', '2020-09-12 22:32:24', '0');

-- ----------------------------
-- Table structure for `res_download_copy`
-- ----------------------------
DROP TABLE IF EXISTS `res_download_copy`;
CREATE TABLE `res_download_copy` (
  `resource_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_download_copy
-- ----------------------------

-- ----------------------------
-- Table structure for `res_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `res_favorite`;
CREATE TABLE `res_favorite` (
  `resource_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of res_favorite
-- ----------------------------
INSERT INTO `res_favorite` VALUES ('1', '1', null, '2021-04-24 09:57:11', null, null);

-- ----------------------------
-- Table structure for `res_package`
-- ----------------------------
DROP TABLE IF EXISTS `res_package`;
CREATE TABLE `res_package` (
  `packageId` bigint(20) NOT NULL,
  `resourceId` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_package
-- ----------------------------

-- ----------------------------
-- Table structure for `res_real`
-- ----------------------------
DROP TABLE IF EXISTS `res_real`;
CREATE TABLE `res_real` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `card_no` varchar(50) NOT NULL COMMENT '卡号',
  `id_card` varchar(50) NOT NULL DEFAULT '' COMMENT '身份证件号',
  `card_password` varchar(50) NOT NULL,
  `real_name` varchar(50) NOT NULL,
  `company_id` bigint(20) NOT NULL COMMENT '单位id',
  `state_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '状态id',
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_real
-- ----------------------------

-- ----------------------------
-- Table structure for `res_role`
-- ----------------------------
DROP TABLE IF EXISTS `res_role`;
CREATE TABLE `res_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `add_user` bigint(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_role
-- ----------------------------
INSERT INTO `res_role` VALUES ('1', '超级管理员', '', '1', '2021-04-22 15:05:48', '0', null, '');

-- ----------------------------
-- Table structure for `res_star`
-- ----------------------------
DROP TABLE IF EXISTS `res_star`;
CREATE TABLE `res_star` (
  `resource_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `Score` int(11) DEFAULT '0',
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of res_star
-- ----------------------------

-- ----------------------------
-- Table structure for `res_user`
-- ----------------------------
DROP TABLE IF EXISTS `res_user`;
CREATE TABLE `res_user` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) NOT NULL,
  `photo_path` varchar(500) DEFAULT NULL,
  `gender` smallint(1) NOT NULL DEFAULT '1',
  `mobile` varchar(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `id_card` varchar(255) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `state_id` bigint(20) NOT NULL DEFAULT '0',
  `login_count` int(20) NOT NULL DEFAULT '0',
  `add_user` bigint(20) NOT NULL,
  `add_time` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_user
-- ----------------------------
INSERT INTO `res_user` VALUES ('1', 'jimmypoor', 'e4g2hbni96faigt7d6kp77ag9tvnglt2', '1', '2', '华超', 'http://tjcdn.shec.edu.cn/timg (1).jpg', '1', '15618528215', 'kissnana3@163.com', '310115198210191992', '4', '2021-04-22 22:36:45', null, '0', '0', '0', '2021-04-22 22:37:54', '0', null, '0');
INSERT INTO `res_user` VALUES ('2', 'jimmypoor2', '-5h90tard7cujft53ubst0a4ta4vtkc5b', null, null, '华超', null, '1', '15617822234', 'kissnana5@1634.com', '310115198210201992', '1', null, null, '0', '0', '1', '2021-04-22 16:14:15', '0', null, '0');

-- ----------------------------
-- Table structure for `res_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `res_user_role`;
CREATE TABLE `res_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `add_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_user_role
-- ----------------------------
INSERT INTO `res_user_role` VALUES ('2', '1', null);

-- ----------------------------
-- Table structure for `res_view`
-- ----------------------------
DROP TABLE IF EXISTS `res_view`;
CREATE TABLE `res_view` (
  `resource_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='record view count of resource';

-- ----------------------------
-- Records of res_view
-- ----------------------------

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `title` varchar(50) NOT NULL COMMENT 'title for resource',
  `author` varchar(50) NOT NULL,
  `keywords` varchar(50) NOT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `cover_path` varchar(500) DEFAULT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  `resource_path` varchar(500) NOT NULL,
  `file_ext_name` varchar(10) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `is_link` tinyint(4) NOT NULL DEFAULT '0',
  `author_company` varchar(100) DEFAULT NULL,
  `author_address` varchar(2000) DEFAULT NULL,
  `author_email` varchar(50) DEFAULT NULL,
  `author_phone` varchar(50) DEFAULT NULL,
  `medium_type_id` bigint(20) DEFAULT '0',
  `resource_type_id` bigint(20) DEFAULT '0',
  `deformity_id` bigint(20) NOT NULL DEFAULT '0',
  `state_id` bigint(20) NOT NULL DEFAULT '0',
  `domain_id` bigint(20) NOT NULL DEFAULT '0',
  `school_type_id` bigint(20) NOT NULL DEFAULT '0',
  `import_source_id` bigint(20) NOT NULL DEFAULT '0',
  `stage_id` bigint(20) NOT NULL DEFAULT '0',
  `grade_id` bigint(20) NOT NULL DEFAULT '0',
  `subject_id` bigint(20) NOT NULL DEFAULT '0',
  `learn_from_id` bigint(20) NOT NULL DEFAULT '0',
  `view_count` int(11) NOT NULL DEFAULT '0',
  `download_count` int(11) NOT NULL DEFAULT '0',
  `favorite_count` int(11) NOT NULL DEFAULT '0',
  `comment_count` int(11) NOT NULL DEFAULT '0',
  `star_count` int(11) NOT NULL DEFAULT '0',
  `star_total` int(20) NOT NULL DEFAULT '0',
  `auditor` varchar(50) DEFAULT NULL,
  `auditor_opinion` varchar(300) DEFAULT NULL,
  `auditor_time` datetime DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '测试资源', '华超', '情不自禁', '测试资源', 'http://tjcdn.shec.edu.cn/timg.jpg', '1ux3oxidddc.jpg', 'http://tjcdn.shec.edu.cn/1ux3oxidddc.jpg', '.jpg', '0', '0', '某某', null, '33', 'd', '0', '77', '6', '129', '16', '0', '0', '36', '0', '101', '0', '0', '0', '1', '1', '0', '0', '0', null, null, '0', '2021-04-21 15:11:13', '1', '2021-04-21 15:32:31', '0');
INSERT INTO `resource` VALUES ('2', '测试资源2', 'aaa', '333', '', 'http://tjcdn.shec.edu.cn/2021/defaultCover.png', 'timg.jpg', 'http://tjcdn.shec.edu.cn/timg.jpg', '.jpg', '459929', '0', '333', null, '', '', '0', '0', '0', '10002', '16', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', null, null, '0', '2021-04-22 15:29:14', '0', null, '0');
INSERT INTO `resource` VALUES ('3', '资源名称3', '99', '7', '', 'http://tjcdn.shec.edu.cn/2021/defaultCover.png', 'timg.jpg', 'http://tjcdn.shec.edu.cn/timg.jpg', '.jpg', '0', '0', '99', null, '', '', '0', '0', '0', '129', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', null, null, '0', '2021-04-22 15:30:02', '0', null, '0');
