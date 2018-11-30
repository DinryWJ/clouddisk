/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : clouddisk

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-11-30 13:33:45
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
INSERT INTO `content` VALUES ('1', '冥王星', '/mydisk', '2', '0', '2018-11-12 10:07:11', '2018-11-13 10:07:17');
INSERT INTO `content` VALUES ('2', '新建', '/mydisk/newfolder', '2', '1', '2018-11-14 10:08:05', '2018-11-14 10:08:09');
INSERT INTO `content` VALUES ('3', '新建2', '/mydisk/newfolder2', '2', '2', '2018-11-14 10:08:32', '2018-11-14 10:08:37');
INSERT INTO `content` VALUES ('4', '海王星', '/haiwangxing', '2', '1', '2018-11-20 10:09:32', '2018-11-20 10:09:35');

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
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='文件资源表';

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('94', '21504-xls', 'E:/disk/home/1543300902-21504-xls.xls', '21504', '149056c6efd0f9be1b4744e37ba68deb', '2018-11-27 14:41:42', '2018-11-27 14:41:42', '0');
INSERT INTO `file` VALUES ('95', '10961-docx', 'E:/disk/home/1543301082-10961-docx.docx', '10961', '051cd746a071b60ed76c797f81c22fc2', '2018-11-27 14:44:42', '2018-11-27 14:44:42', '0');
INSERT INTO `file` VALUES ('96', '20992--xls', 'E:/disk/home/1543301376-20992--xls.xls', '20992', 'f21f8a74d72961737f51224573a8ecf8', '2018-11-27 14:49:36', '2018-11-27 14:49:36', '0');
INSERT INTO `file` VALUES ('97', '666719--20170417doc', 'E:/disk/home/1543301502-666719--20170417doc.doc', '666719', 'b8c10083ffface2eed33a9b9c97c7beb', '2018-11-27 14:51:42', '2018-11-27 14:51:42', '0');
INSERT INTO `file` VALUES ('98', '774652-Java20180205pdf', 'E:/disk/home/1543301556-774652-Java20180205pdf.pdf', '774652', '38767fdb47730db8e6cbca667150c0df', '2018-11-27 14:52:36', '2018-11-27 14:52:36', '0');
INSERT INTO `file` VALUES ('99', '170-Configini', 'E:/disk/home/1543301573-170-Configini.ini', '170', '2caecb4441b465aa2e76bfd1b4b560d6', '2018-11-27 14:52:53', '2018-11-27 14:52:53', '0');
INSERT INTO `file` VALUES ('100', '424448-FTPServerexe', 'E:/disk/home/1543301773-424448-FTPServerexe.exe', '424448', '25449645cb0c3e7f6fd3f7ec91ae2d8c', '2018-11-27 14:56:13', '2018-11-27 14:56:13', '0');
INSERT INTO `file` VALUES ('101', '4229105-rar', 'E:/disk/home/1543301801-4229105-rar.rar', '4229105', 'fa7601d4aeb116c3f7c008db6514b5be', '2018-11-27 14:56:41', '2018-11-27 14:56:41', '0');
INSERT INTO `file` VALUES ('102', '218248857-Spring4xpdf', 'E:/disk/home/1543301850-218248857-Spring4xpdf.pdf', '218248857', '55b42123bfc91cae294e334390925147', '2018-11-27 14:57:31', '2018-11-27 14:57:31', '0');
INSERT INTO `file` VALUES ('103', '21951985-EffectiveJava3rdEditionpdf', 'E:/disk/home/1543301936-21951985-EffectiveJava3rdEditionpdf.pdf', '21951985', 'de4a54eedc5825371a11e2ca07a51b3a', '2018-11-27 14:58:56', '2018-11-27 14:58:56', '0');
INSERT INTO `file` VALUES ('104', '301609789-jyptsql', 'E:/disk/home/1543302022-301609789-jyptsql.sql', '301609789', '83f59118fab2f59ee87df583d8d24d47', '2018-11-27 15:00:25', '2018-11-27 15:00:25', '0');
INSERT INTO `file` VALUES ('105', '1419-jypttxt', 'E:/disk/home/1543376136-1419-jypttxt.txt', '1419', '8139b7887f84562b069cdce3d79e01e0', '2018-11-28 11:35:36', '2018-11-28 11:35:36', '0');
INSERT INTO `file` VALUES ('106', '62286-avatarpng', 'E:/disk/home/1543473093-62286-avatarpng.png', '62286', 'ae6f2081b2bd3c319b55b681a49abbaa', '2018-11-29 14:31:33', '2018-11-29 14:31:33', '0');
INSERT INTO `file` VALUES ('107', '136795-2017zip', 'E:/disk/home/1543554446-136795-2017zip.zip', '136795', 'cd70903d5ca1e785fd99ffc3eeaa2bd3', '2018-11-30 13:07:26', '2018-11-30 13:07:26', '0');
INSERT INTO `file` VALUES ('108', '7483150-rar', 'E:/disk/home/1543554462-7483150-rar.rar', '7483150', '64b76aabc37ea908290d32baff54ca61', '2018-11-30 13:07:42', '2018-11-30 13:07:42', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='用户文件目录表';

-- ----------------------------
-- Records of file_content
-- ----------------------------
INSERT INTO `file_content` VALUES ('70', 'jypt.sql', '287.0MB', '0', 'unknown', '92', '2', '2018-11-27 14:39:39', '2018-11-27 14:39:39');
INSERT INTO `file_content` VALUES ('71', 'jypt连接.txt', '1KB', '0', 'text', '93', '2', '2018-11-27 14:40:25', '2018-11-27 14:40:25');
INSERT INTO `file_content` VALUES ('72', '宁一.xls', '21KB', '0', 'xls', '94', '2', '2018-11-27 14:41:58', '2018-11-27 14:41:58');
INSERT INTO `file_content` VALUES ('73', '我的.docx', '10KB', '0', 'doc', '95', '2', '2018-11-27 14:44:53', '2018-11-27 14:44:53');
INSERT INTO `file_content` VALUES ('74', '龙山-迪安.xls', '20KB', '0', 'xls', '96', '2', '2018-11-27 14:49:36', '2018-11-27 14:49:36');
INSERT INTO `file_content` VALUES ('75', '第一医院送检平台接口服务-接口规范文档（20170417).doc', '651KB', '0', 'doc', '97', '2', '2018-11-27 14:51:42', '2018-11-27 14:51:42');
INSERT INTO `file_content` VALUES ('76', '卓健科技 Java 技术开发规范 20180205.pdf', '756KB', '0', 'pdf', '98', '2', '2018-11-27 14:52:36', '2018-11-27 14:52:36');
INSERT INTO `file_content` VALUES ('77', 'Config.ini', '170B', '0', 'unknown', '99', '2', '2018-11-27 14:54:10', '2018-11-27 14:54:10');
INSERT INTO `file_content` VALUES ('78', 'Config(1).ini', '170B', '0', 'unknown', '99', '2', '2018-11-27 14:55:50', '2018-11-27 14:55:50');
INSERT INTO `file_content` VALUES ('79', 'FTPServer.exe', '414KB', '1', 'exe', '100', '2', '2018-11-27 14:56:24', '2018-11-27 14:56:24');
INSERT INTO `file_content` VALUES ('80', '入职包（员工手册等）.rar', '4.0MB', '1', 'unknown', '101', '2', '2018-11-27 14:56:42', '2018-11-27 14:56:42');
INSERT INTO `file_content` VALUES ('81', '精通Spring 4.x 企业应用开发实战.pdf', '208.0MB', '4', 'pdf', '102', '2', '2018-11-27 14:57:31', '2018-11-27 14:57:31');
INSERT INTO `file_content` VALUES ('82', 'Effective Java (3rd Edition).pdf', '20.0MB', '1', 'pdf', '103', '2', '2018-11-27 14:58:56', '2018-11-27 14:58:56');
INSERT INTO `file_content` VALUES ('83', 'jypt.sql', '287.0MB', '1', 'unknown', '92', '2', '2018-11-27 14:59:21', '2018-11-27 14:59:21');
INSERT INTO `file_content` VALUES ('84', 'jypt.sql', '287.0MB', '4', 'unknown', '92', '2', '2018-11-27 14:59:45', '2018-11-27 14:59:45');
INSERT INTO `file_content` VALUES ('85', 'jypt.sql', '287.0MB', '3', 'text', '104', '2', '2018-11-27 15:00:25', '2018-11-27 15:00:25');
INSERT INTO `file_content` VALUES ('86', 'jypt连接.txt', '1KB', '3', 'text', '105', '2', '2018-11-28 11:35:36', '2018-11-28 11:35:36');
INSERT INTO `file_content` VALUES ('87', 'avatar.png', '60KB', '0', 'image', '106', '2', '2018-11-29 14:31:33', '2018-11-29 14:31:33');
INSERT INTO `file_content` VALUES ('88', 'avatar(1).png', '60KB', '0', 'image', '106', '2', '2018-11-29 17:14:52', '2018-11-29 17:14:52');
INSERT INTO `file_content` VALUES ('89', 'avatar.png', '60KB', '1', 'image', '106', '2', '2018-11-30 10:37:30', '2018-11-30 10:37:30');
INSERT INTO `file_content` VALUES ('90', '软件工程专业工程实习文档模板（2017年）.zip', '133KB', '0', 'zip', '107', '2', '2018-11-30 13:07:26', '2018-11-30 13:07:26');
INSERT INTO `file_content` VALUES ('91', '网盘资料.rar', '7.0MB', '0', 'unknown', '108', '2', '2018-11-30 13:07:42', '2018-11-30 13:07:42');

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
