/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : clouddisk

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-11-26 16:17:15
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
  `full_path` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('1', '我的网盘', '/mydisk', '2', '0', '2018-11-12 10:07:11', '2018-11-13 10:07:17');
INSERT INTO `content` VALUES ('2', '新建', '/mydisk/newfolder', '2', '1', '2018-11-14 10:08:05', '2018-11-14 10:08:09');
INSERT INTO `content` VALUES ('3', '新建2', '/mydisk/newfolder2', '2', '1', '2018-11-14 10:08:32', '2018-11-14 10:08:37');
INSERT INTO `content` VALUES ('4', '海王星', '/haiwangxing', '2', '0', '2018-11-20 10:09:32', '2018-11-20 10:09:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='文件资源表';

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('55', '236584206-wwwjava1234comJavaEESpringBootpdf', 'E:/disk/home/1542786728-236584206-wwwjava1234comJavaEESpringBootpdf.pdf', '236584206', '239abd04e0b016379310ea44bf0be4ac', '2018-11-21 15:52:09', '2018-11-21 15:52:09', '0');
INSERT INTO `file` VALUES ('56', '21951985-EffectiveJava3rdEditionpdf', 'E:/disk/home/1542786938-21951985-EffectiveJava3rdEditionpdf.pdf', '21951985', 'de4a54eedc5825371a11e2ca07a51b3a', '2018-11-21 15:55:38', '2018-11-21 15:55:38', '0');
INSERT INTO `file` VALUES ('57', '218248857-Spring4xpdf', 'E:/disk/home/1542787076-218248857-Spring4xpdf.pdf', '218248857', '55b42123bfc91cae294e334390925147', '2018-11-21 15:57:58', '2018-11-21 15:57:58', '0');
INSERT INTO `file` VALUES ('58', '4229105-rar', 'E:/disk/home/1542787242-4229105-rar.rar', '4229105', 'fa7601d4aeb116c3f7c008db6514b5be', '2018-11-21 16:00:42', '2018-11-21 16:00:42', '0');
INSERT INTO `file` VALUES ('60', '666719--20170417doc', 'E:/disk/home/1542850891-666719--20170417doc.doc', '666719', 'b8c10083ffface2eed33a9b9c97c7beb', '2018-11-22 09:41:31', '2018-11-22 09:41:31', '0');
INSERT INTO `file` VALUES ('61', '13750009-HTTPpdf', 'E:/disk/home/1542855066-13750009-HTTPpdf.pdf', '13750009', '2e9ef0754a48b9a5e7dbfb91f3585635', '2018-11-22 10:51:06', '2018-11-22 10:51:06', '0');
INSERT INTO `file` VALUES ('62', '4521217251-Xcode_82xip', 'E:/disk/home/1542856468-4521217251-Xcode_82xip.xip', '4521217251', 'f0031327974fd772369ce317f4972f04', '2018-11-22 11:15:16', '2018-11-22 11:15:16', '0');
INSERT INTO `file` VALUES ('63', '198-response_1542612343866json', 'E:/disk/home/1542856550-198-response_1542612343866json.json', '198', '2fc233dcd18513ecd98134758d7633c7', '2018-11-22 11:15:50', '2018-11-22 11:15:50', '0');
INSERT INTO `file` VALUES ('64', '37066-11102540981pdf', 'E:/disk/home/1542856564-37066-11102540981pdf.pdf', '37066', '14a90d90a88360b4185dbde48690f7e3', '2018-11-22 11:16:04', '2018-11-22 11:16:04', '0');
INSERT INTO `file` VALUES ('65', '36738-11102541596pdf', 'E:/disk/home/1542856571-36738-11102541596pdf.pdf', '36738', 'cd2d08c0d1c12ceda433029b4d760534', '2018-11-22 11:16:11', '2018-11-22 11:16:11', '0');
INSERT INTO `file` VALUES ('66', '3410-11102540982pdf', 'E:/disk/home/1542856576-3410-11102540982pdf.pdf', '3410', '4a00b5a8707e5893554278acb9b9d3e8', '2018-11-22 11:16:16', '2018-11-22 11:16:16', '0');
INSERT INTO `file` VALUES ('67', '285986-zip', 'E:/disk/home/1542856583-285986-zip.zip', '285986', 'a54946dcdfc577fc0e135bfe0fee35ef', '2018-11-22 11:16:23', '2018-11-22 11:16:23', '0');
INSERT INTO `file` VALUES ('68', '36738-11102541597pdf', 'E:/disk/home/1542856606-36738-11102541597pdf.pdf', '36738', 'bd6853c294f6d9db82a32cdb0a2a8678', '2018-11-22 11:16:46', '2018-11-22 11:16:46', '0');
INSERT INTO `file` VALUES ('69', '3-1txt', 'E:/disk/home/1542856629-3-1txt.txt', '3', '282ca9cd39954f46447c36c25cc6597c', '2018-11-22 11:17:09', '2018-11-22 11:17:09', '0');

-- ----------------------------
-- Table structure for file_content
-- ----------------------------
DROP TABLE IF EXISTS `file_content`;
CREATE TABLE `file_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `total_size` varchar(255) NOT NULL,
  `directory_id` int(11) NOT NULL COMMENT '0 目录 1 文件',
  `file_type` varchar(255) NOT NULL,
  `file_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='用户文件目录表';

-- ----------------------------
-- Records of file_content
-- ----------------------------
INSERT INTO `file_content` VALUES ('18', '第一医院送检平台接口服务-接口规范文档（20170417)(2).doc', '651KB', '2', 'application/msword', '60', '2', '2018-11-22 10:40:12', '2018-11-22 10:40:12');
INSERT INTO `file_content` VALUES ('19', '第一医院送检平台接口服务-接口规范文档（20170417).doc', '651KB', '2', 'application/msword', '60', '2', '2018-11-22 10:41:00', '2018-11-22 10:41:00');
INSERT INTO `file_content` VALUES ('20', '第一医院送检平台接口服务-接口规范文档（20170417)(1).doc', '651KB', '1', 'application/msword', '60', '2', '2018-11-22 10:41:12', '2018-11-22 10:41:12');
INSERT INTO `file_content` VALUES ('21', '第一医院送检平台接口服务-接口规范文档（20170417)(3).doc', '651KB', '1', 'application/msword', '60', '2', '2018-11-22 10:41:26', '2018-11-22 10:41:26');
INSERT INTO `file_content` VALUES ('27', '[www.java1234.com]JavaEE开发的颠覆者 Spring Boot实战  完整版.pdf', '225.0MB', '2', 'application/pdf', '55', '2', '2018-11-22 10:46:21', '2018-11-22 10:46:21');
INSERT INTO `file_content` VALUES ('30', '[www.java1234.com]JavaEE开发的颠覆者 Spring Boot实战  完整版(1).pdf', '225.0MB', '3', 'application/pdf', '55', '2', '2018-11-22 10:50:13', '2018-11-22 10:50:13');
INSERT INTO `file_content` VALUES ('31', '精通Spring 4.x 企业应用开发实战.pdf', '208.0MB', '3', 'application/pdf', '57', '2', '2018-11-22 10:50:45', '2018-11-22 10:50:45');
INSERT INTO `file_content` VALUES ('32', '图解HTTP.pdf', '13.0MB', '1', 'application/pdf', '61', '2', '2018-11-22 10:51:06', '2018-11-22 10:51:06');
INSERT INTO `file_content` VALUES ('33', '图解HTTP(1).pdf', '13.0MB', '0', 'application/pdf', '61', '2', '2018-11-22 10:51:21', '2018-11-22 10:51:21');
INSERT INTO `file_content` VALUES ('34', 'Xcode_8.2.xip', '4.20GB', '3', '', '62', '2', '2018-11-22 11:15:33', '2018-11-22 11:15:33');
INSERT INTO `file_content` VALUES ('35', 'response_1542612343866.json', '198B', '4', 'application/json', '63', '2', '2018-11-22 11:16:01', '2018-11-22 11:16:01');
INSERT INTO `file_content` VALUES ('36', '1110254098 (1).pdf', '36KB', '4', 'application/pdf', '64', '2', '2018-11-22 11:16:08', '2018-11-22 11:16:08');
INSERT INTO `file_content` VALUES ('37', '1110254159 (6).pdf', '35KB', '4', 'application/pdf', '65', '2', '2018-11-22 11:16:13', '2018-11-22 11:16:13');
INSERT INTO `file_content` VALUES ('38', '1110254098 (2).pdf', '3KB', '0', 'application/pdf', '66', '2', '2018-11-22 11:16:18', '2018-11-22 11:16:18');
INSERT INTO `file_content` VALUES ('39', '申请表导出清单.zip', '279KB', '0', 'application/zip', '67', '2', '2018-11-22 11:16:28', '2018-11-22 11:16:28');
INSERT INTO `file_content` VALUES ('40', '1110254159 (7).pdf', '35KB', '0', 'application/pdf', '68', '2', '2018-11-22 11:16:58', '2018-11-22 11:16:58');
INSERT INTO `file_content` VALUES ('41', '1.txt', '3B', '0', 'text/plain', '69', '2', '2018-11-22 11:17:14', '2018-11-22 11:17:14');

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
  `permission` varchar(255) NOT NULL,
  `role` varchar(10) NOT NULL,
  `class_id` int(11) DEFAULT NULL,
  `rank` int(11) NOT NULL DEFAULT '0',
  `sex` varchar(1) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '123', 'e63eb8bef1e18fb8f3c8c42366ec9757', 'vKWaWj', 'unknown', 'edit,view', 'admin', null, '0', null, null, null, '2018-11-19 17:02:51', '2018-11-19 17:02:51', '0');
INSERT INTO `user` VALUES ('3', '12', '79109fdac12384a02c4666baeeb17677', 'PWQkFh', 'unknown', 'edit,view', 'user', null, '0', null, null, null, '2018-11-20 09:57:50', '2018-11-20 09:57:50', '0');

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
