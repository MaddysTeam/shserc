/*
Navicat MySQL Data Transfer

Source Server         : localdb
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : shserc

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-01-30 20:21:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parentId` bigint(20) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `sort` bigint(20) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', '字典表', '0', null, 'dict', null, null,null);
INSERT INTO `dictionary` VALUES ('2', '性别', '1', null, 'gender', null, null,null);
INSERT INTO `dictionary` VALUES ('3', '男', '2', null, 'mail', null, '1',null);
INSERT INTO `dictionary` VALUES ('4', '女', '2', null, 'femail', null, '0',null);
INSERT INTO `dictionary` VALUES ('5', '残疾类型', '1', null, 'deformity', null, null,null);
INSERT INTO `dictionary` VALUES ('6', '智力残疾', '5', null, 'intellegent', null, '1',null);
INSERT INTO `dictionary` VALUES ('7', '肢体残疾', '5', null, 'physical', null, '2',null);
INSERT INTO `dictionary` VALUES ('8', '视力残疾', '5', null, 'visual', null, '3',null);
INSERT INTO `dictionary` VALUES ('9', '听力残疾', '5', null, 'hearing', null, '4',null);
INSERT INTO `dictionary` VALUES ('10', '言语残疾', '5', null, 'speech', null, '5',null);
INSERT INTO `dictionary` VALUES ('11', '精神残疾', '5', null, 'mental', null, '6',null);
INSERT INTO `dictionary` VALUES ('12', '资源状态', '1', null, 'state', null, null,null);
INSERT INTO `dictionary` VALUES ('13', '可用', '12', null, 'enable', null, '1001',null);
INSERT INTO `dictionary` VALUES ('14', '禁用', '12', null, 'disable', null, '1002',null);

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
  `resource_path` varchar(500) NOT NULL,
  `file_ext_name` varchar(10) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `is_link` tinyint(4) NOT NULL DEFAULT '0',
  `author_company` varchar(100) DEFAULT NULL,
  `author_address` varchar(2000) DEFAULT NULL,
  `author_email` varchar(50) DEFAULT NULL,
  `author_phone` varchar(50) DEFAULT NULL,
  `medium_type_id` bigint(20) DEFAULT NULL,
  `resource_type_id` bigint(20) DEFAULT NULL,
  `deformity_id` bigint(20) NOT NULL,
  `state_id` bigint(20) DEFAULT '1001',
  `view_count` bigint(20) DEFAULT '0',
  `star_total` bigint(20) DEFAULT '0',
  `auditor` varchar(50) DEFAULT NULL,
  `auditor_time` datetime DEFAULT NULL,
  `add_user` bigint(20) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `file_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '资源标题2', '华超', '资源', null, 'http://cdncsj.sser.shdjg.net/2019/pics/20191010/互动数学.jpg', 'http://cdn.sser.shdjg.net/新建文本文档.txt', '.txt', '0', '0', '666666', '作者地址', '作者邮箱', '作者电话', '0', '0', '3', '0', '0', '0', '0', null, '0', '2020-10-30 21:36:04', '0', '2020-10-30 21:36:04', '0', '新建文本文档.txt');
INSERT INTO `resource` VALUES ('2', 'test38888', '33', '333', '333', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604985341462&di=0dc077c561a37951c21cd724aeb875d1&imgtype=0&src=http%3A%2F%2Fdingyue.ws.126.net%2FueBermhmQAPVio5YnFJWMaHkbgCQSXpEE8qpEYfAi8c%3Dh1551833241961.jpg', 'http://cdn.sser.shdjg.net/班会方案专家评审汇总表.xlsx', '.xlsx', '0', '0', '444', null, '3', '', '0', '0', '2', '0', '0', '0', '0', null, '0', null, '0', null, '0', '班会方案专家评审汇总表.xlsx');
INSERT INTO `resource` VALUES ('3', 'test4', '444', '33333,,4444', '4444', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604985341462&di=0dc077c561a37951c21cd724aeb875d1&imgtype=0&src=http%3A%2F%2Fdingyue.ws.126.net%2FueBermhmQAPVio5YnFJWMaHkbgCQSXpEE8qpEYfAi8c%3Dh1551833241961.jpg', 'http://shlll.s.wcsapi.biz.matocloud.com/upload201909261929354303.mp4', '.mp4', '0', '0', '444', null, '44', '', '0', '0', '2', '0', '0', '0', '0', null, '0', null, '0', null, '0', null);
INSERT INTO `resource` VALUES ('4', '5555547777', '55554', '999', '5555', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604985341462&di=0dc077c561a37951c21cd724aeb875d1&imgtype=0&src=http%3A%2F%2Fdingyue.ws.126.net%2FueBermhmQAPVio5YnFJWMaHkbgCQSXpEE8qpEYfAi8c%3Dh1551833241961.jpg', 'http://cdn.sser.shdjg.net/班会方案专家评审汇总表.xlsx', '.xlsx', '33970', '0', '44', null, '555', '', '0', '0', '1', '0', '0', '0', '0', null, '0', null, '0', null, '0', '班会方案专家评审汇总表.xlsx');

-- ----------------------------
-- Table structure for `res_company`
-- ----------------------------
DROP TABLE IF EXISTS `res_company`;
CREATE TABLE `res_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  `add_user` bigint(20) NOT NULL,
  `add_date` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of res_company
-- ----------------------------
INSERT INTO `res_company` VALUES ('1', '上海市', '0', '1', '2020-09-12 21:40:13', '1', '2020-09-12 21:40:22', '0');
INSERT INTO `res_company` VALUES ('2', '杨浦区', '1', '1', '2020-09-12 21:40:51', '1', '2020-09-26 21:40:56', '0');
INSERT INTO `res_company` VALUES ('3', '虹口区', '1', '1', '2020-09-12 21:41:46', '1', '2020-09-12 21:41:48', '0');
INSERT INTO `res_company` VALUES ('4', '凉城第二小学', '3', '1', '2020-09-12 22:32:21', '1', '2020-09-12 22:32:24', '0');
INSERT INTO `res_company` VALUES ('5', '333333', '2', '0', '2020-10-02 15:43:50', null, null, '0');
INSERT INTO `res_company` VALUES ('6', '666666', '2', '0', '2020-10-02 15:45:53', null, null, '0');
INSERT INTO `res_company` VALUES ('7', '4444444444', '2', '0', '2020-10-02 15:48:31', null, null, '0');
INSERT INTO `res_company` VALUES ('8', '333333333', '3', '0', '2020-10-02 15:51:52', null, null, '0');
INSERT INTO `res_company` VALUES ('9', '99999999999999999', '2', '0', '2020-10-02 15:53:29', null, null, '0');
INSERT INTO `res_company` VALUES ('10', '44433434343', '3', '0', '2020-10-02 15:56:05', null, null, '0');
INSERT INTO `res_company` VALUES ('11', '333222222222222222', '2', '0', '2020-10-02 15:56:42', null, null, '0');
INSERT INTO `res_company` VALUES ('12', '333222222222222222', '2', '0', '2020-10-02 15:57:37', null, null, '0');
INSERT INTO `res_company` VALUES ('13', 'ddddddddddddddd3232323', '3', '0', '2020-10-02 15:59:26', null, null, '0');

-- ----------------------------
-- Table structure for `res_role`
-- ----------------------------
DROP TABLE IF EXISTS `res_role`;
CREATE TABLE `res_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `add_user` bigint(20) NOT NULL,
  `add_date` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of res_role
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
  `email` varchar(255) NOT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `add_user` bigint(20) NOT NULL,
  `add_date` datetime NOT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_deleted` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of res_user
-- ----------------------------
INSERT INTO `res_user` VALUES ('1', 'jimmyPoor', 'Win@123', '1', '2', 'jimmyPoor', null, '1', 'kissnana3@163.com', null, '1', '0', '2020-09-06 00:00:00', null, null, '0');
INSERT INTO `res_user` VALUES ('2', 'jimmyPoor2', 'Win@123', '1', '2', 'jimmyPoor2', null, '1', 'kissnana4@163.com', null, '1', '0', '2020-09-06 00:00:00', null, null, '0');
INSERT INTO `res_user` VALUES ('3', 'jimmyPoor3', 'Win@123', '1', '2', 'jimmyPoor3', null, '1', 'kissnana5@163.com', null, '1', '0', '2020-09-06 00:00:00', null, null, '0');
