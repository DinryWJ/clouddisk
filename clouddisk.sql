/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : clouddisk

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-11-16 14:05:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `major_id` int(11) NOT NULL,
  `college_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级表';

-- ----------------------------
-- Records of class
-- ----------------------------

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学院表';

-- ----------------------------
-- Records of college
-- ----------------------------

-- ----------------------------
-- Table structure for concern
-- ----------------------------
DROP TABLE IF EXISTS `concern`;
CREATE TABLE `concern` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关注表';

-- ----------------------------
-- Records of concern
-- ----------------------------

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `directory` tinyint(1) NOT NULL COMMENT '0 目录 1 文件',
  `file_id` int(11) DEFAULT NULL,
  `index` int(11) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户文件目录表';

-- ----------------------------
-- Records of content
-- ----------------------------

-- ----------------------------
-- Table structure for declare
-- ----------------------------
DROP TABLE IF EXISTS `declare`;
CREATE TABLE `declare` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `field_name` int(1) NOT NULL COMMENT '覆盖的范围0 ：所有 1：学院 2：专业 3：班级',
  `field_id` int(11) NOT NULL COMMENT '覆盖范围对应的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告栏';

-- ----------------------------
-- Records of declare
-- ----------------------------

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `size` varchar(255) NOT NULL,
  `md5` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='文件资源表';

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '36738-11102541597pdf', 'E:/disk/home/1542346578-36738-11102541597pdf.pdf', '36738', 'bd6853c294f6d9db82a32cdb0a2a8678', '2018-11-16 13:36:18', '2018-11-16 13:36:18', '0');
INSERT INTO `file` VALUES ('2', '285986-zip', 'E:/disk/home/1542346092-285986-zip.zip', '285986', 'a54946dcdfc577fc0e135bfe0fee35ef', '2018-11-16 13:28:12', '2018-11-16 13:28:12', '0');
INSERT INTO `file` VALUES ('3', '36738-11102541596pdf', 'E:/disk/home/1542346672-36738-11102541596pdf.pdf', '36738', 'cd2d08c0d1c12ceda433029b4d760534', '2018-11-16 13:37:52', '2018-11-16 13:37:52', '0');
INSERT INTO `file` VALUES ('4', '3410-11102540982pdf', 'E:/disk/home/1542346849-3410-11102540982pdf.pdf', '3410', '4a00b5a8707e5893554278acb9b9d3e8', '2018-11-16 13:40:49', '2018-11-16 13:40:49', '0');
INSERT INTO `file` VALUES ('5', '37066-11102540981pdf', 'E:/disk/home/1542347175-37066-11102540981pdf.pdf', '37066', '14a90d90a88360b4185dbde48690f7e3', '2018-11-16 13:46:15', '2018-11-16 13:46:15', '0');
INSERT INTO `file` VALUES ('6', '666719--201704171doc', 'E:/disk/home/1542347565-666719--201704171doc.doc', '666719', 'b8c10083ffface2eed33a9b9c97c7beb', '2018-11-16 13:52:45', '2018-11-16 13:52:45', '0');
INSERT INTO `file` VALUES ('7', '316860-Project-1-soapui-projectxml', 'E:/disk/home/1542347707-316860-Project-1-soapui-projectxml.xml', '316860', 'ecbd47b65c99b1d55cfbb6a0ec37d92c', '2018-11-16 13:55:07', '2018-11-16 13:55:07', '0');
INSERT INTO `file` VALUES ('8', '19315456-TeamViewer_Setupexe', 'E:/disk/home/1542347915-19315456-TeamViewer_Setupexe.exe', '19315456', '03b69a2402904301663f05a833981f57', '2018-11-16 13:58:35', '2018-11-16 13:58:35', '0');
INSERT INTO `file` VALUES ('9', '2139648-1doc', 'E:/disk/home/1542347919-2139648-1doc.doc', '2139648', '3282e2f41dd47ea1dd9e023953915c9f', '2018-11-16 13:58:39', '2018-11-16 13:58:39', '0');
INSERT INTO `file` VALUES ('10', '170569-210-285mmpdf', 'E:/disk/home/1542347923-170569-210-285mmpdf.pdf', '170569', '80b93cc0ae75d99b305cf4e914dcb56e', '2018-11-16 13:58:43', '2018-11-16 13:58:43', '0');
INSERT INTO `file` VALUES ('11', '255937-docx', 'E:/disk/home/1542348164-255937-docx.docx', '255937', 'b17f32f6d6e006006f6c85338f1b9a2a', '2018-11-16 14:02:44', '2018-11-16 14:02:44', '0');
INSERT INTO `file` VALUES ('12', '12165-xlsx', 'E:/disk/home/1542348165-12165-xlsx.xlsx', '12165', '91ffe681a367bd9a98aea1243734f3b6', '2018-11-16 14:02:45', '2018-11-16 14:02:45', '0');
INSERT INTO `file` VALUES ('13', '4521217251-Xcode_82xip', 'E:/disk/home/1542348239-4521217251-Xcode_82xip.xip', '4521217251', 'f0031327974fd772369ce317f4972f04', '2018-11-16 14:04:47', '2018-11-16 14:04:47', '0');

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友情链接表';

-- ----------------------------
-- Records of link
-- ----------------------------

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专业表';

-- ----------------------------
-- Records of major
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for rank
-- ----------------------------
DROP TABLE IF EXISTS `rank`;
CREATE TABLE `rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(255) NOT NULL,
  `rank_low` varchar(255) NOT NULL,
  `rank_high` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分表';

-- ----------------------------
-- Records of rank
-- ----------------------------

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `file_id` int(11) NOT NULL,
  `context` varchar(255) NOT NULL,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Records of review
-- ----------------------------

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `file_id` int(11) NOT NULL,
  `key` varchar(255) NOT NULL,
  `expired_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件分享表';

-- ----------------------------
-- Records of share
-- ----------------------------

-- ----------------------------
-- Table structure for suggest
-- ----------------------------
DROP TABLE IF EXISTS `suggest`;
CREATE TABLE `suggest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `context` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='投诉建议表';

-- ----------------------------
-- Records of suggest
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `role` varchar(1) NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  `rank` int(11) NOT NULL DEFAULT '0',
  `sex` varchar(1) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) NOT NULL,
  `times` int(11) NOT NULL DEFAULT '0',
  `last_enter_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游客表';

-- ----------------------------
-- Records of visitor
-- ----------------------------
