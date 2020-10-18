/*
Navicat MySQL Data Transfer

Source Server         : localdb
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : shserc

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-10-18 22:39:58
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', '字典表', '0', null, null, null, null);
INSERT INTO `dictionary` VALUES ('2', '性别', '1', null, 'gender', null, null);
INSERT INTO `dictionary` VALUES ('3', '男', '2', null, '', null, '1');
INSERT INTO `dictionary` VALUES ('4', '女', '2', null, null, null, '0');

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
  `coverPath` varchar(500) DEFAULT NULL,
  `resourcePath` varchar(500) NOT NULL,
  `fileExtName` varchar(10) DEFAULT NULL,
  `fileSize` bigint(20) DEFAULT NULL,
  `isLink` tinyint(4) NOT NULL DEFAULT '0',
  `authorCompany` varchar(100) DEFAULT NULL,
  `authorAddress` varchar(2000) DEFAULT NULL,
  `authorEmail` varchar(50) DEFAULT NULL,
  `authorPhone` varchar(50) DEFAULT NULL,
  `deformityPKID` bigint(20) NOT NULL,
  `viewCount` bigint(20) DEFAULT NULL,
  `starTotal` bigint(20) DEFAULT NULL,
  `auditor` varchar(50) DEFAULT NULL,
  `auditorTime` datetime DEFAULT NULL,
  `addUser` bigint(20) DEFAULT NULL,
  `addTime` datetime DEFAULT NULL,
  `updateUser` bigint(20) NOT NULL,
  `updateTime` datetime NOT NULL,
  `isDeleted` tinyint(4) NOT NULL DEFAULT '0',
  `deformity` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------

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
