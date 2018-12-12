/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : clouddisk

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-12-12 17:29:46
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
  `user_id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('63', 'apache-maven-3.5.4', '2', '0', '2018-12-12 17:21:34', '2018-12-12 17:21:34');
INSERT INTO `content` VALUES ('64', 'lib', '2', '63', '2018-12-12 17:21:34', '2018-12-12 17:21:34');
INSERT INTO `content` VALUES ('65', 'jansi-native', '2', '64', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('66', 'windows64', '2', '65', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('67', 'windows32', '2', '65', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('68', 'osx', '2', '65', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('69', 'linux64', '2', '65', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('70', 'linux32', '2', '65', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('71', 'freebsd64', '2', '65', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('72', 'freebsd32', '2', '65', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('73', 'ext', '2', '64', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('74', 'conf', '2', '63', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `content` VALUES ('75', 'logging', '2', '74', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `content` VALUES ('76', 'boot', '2', '63', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `content` VALUES ('77', 'bin', '2', '63', '2018-12-12 17:21:38', '2018-12-12 17:21:38');

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
  `res` int(11) NOT NULL DEFAULT '1' COMMENT '被引用的次数',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_delete` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8 COMMENT='文件资源表';

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('155', '1-test1txt', 'E:/disk/home/1544604567-1-test1txt.txt', '1', '94b498516fbd21999fd2e4d54b00c3af', '0', '2018-12-12 16:49:27', '2018-12-12 16:51:07', '1');
INSERT INTO `file` VALUES ('156', '1-test2txt', 'E:/disk/home/1544604567-1-test2txt.txt', '1', '354da72caa84e807e1691f9474b6e394', '0', '2018-12-12 16:49:27', '2018-12-12 16:51:07', '1');
INSERT INTO `file` VALUES ('157', 'empty.txt', 'E:/disk/home/4b36ad7244c64640b59029e3457ea3a2-empty.txt', '0', '', '0', '2018-12-12 16:49:28', '2018-12-12 16:51:07', '1');
INSERT INTO `file` VALUES ('158', '3-testtest22-1txt', 'E:/disk/home/1544604568-3-testtest22-1txt.txt', '3', '93161b6d26e47c80c85f39d8b2566d32', '0', '2018-12-12 16:49:28', '2018-12-12 16:51:07', '1');
INSERT INTO `file` VALUES ('159', '3-testtest11-1txt', 'E:/disk/home/1544604568-3-testtest11-1txt.txt', '3', '5415d3cc1b2b0e07df7320bdf020cf90', '0', '2018-12-12 16:49:28', '2018-12-12 16:51:07', '1');
INSERT INTO `file` VALUES ('160', '4-testtest1test1111-1txt', 'E:/disk/home/1544604568-4-testtest1test1111-1txt.txt', '4', '7556d9d9da6f77c9f30df2e764173fb0', '0', '2018-12-12 16:49:28', '2018-12-12 16:51:07', '1');
INSERT INTO `file` VALUES ('161', '1-test1txt', 'E:/disk/home/1544604705-1-test1txt.txt', '1', '94b498516fbd21999fd2e4d54b00c3af', '0', '2018-12-12 16:51:45', '2018-12-12 16:52:34', '1');
INSERT INTO `file` VALUES ('162', '1-test2txt', 'E:/disk/home/1544604706-1-test2txt.txt', '1', '354da72caa84e807e1691f9474b6e394', '0', '2018-12-12 16:51:46', '2018-12-12 16:52:34', '1');
INSERT INTO `file` VALUES ('163', 'empty.txt', 'E:/disk/home/991d5727aa1845e788b0f6ee68c1fbbc-empty.txt', '0', '', '0', '2018-12-12 16:51:46', '2018-12-12 16:52:34', '1');
INSERT INTO `file` VALUES ('164', '3-testtest22-1txt', 'E:/disk/home/1544604706-3-testtest22-1txt.txt', '3', '93161b6d26e47c80c85f39d8b2566d32', '0', '2018-12-12 16:51:46', '2018-12-12 16:52:34', '1');
INSERT INTO `file` VALUES ('165', '3-testtest11-1txt', 'E:/disk/home/1544604706-3-testtest11-1txt.txt', '3', '5415d3cc1b2b0e07df7320bdf020cf90', '0', '2018-12-12 16:51:46', '2018-12-12 16:52:34', '1');
INSERT INTO `file` VALUES ('166', '4-testtest1test1111-1txt', 'E:/disk/home/1544604706-4-testtest1test1111-1txt.txt', '4', '7556d9d9da6f77c9f30df2e764173fb0', '0', '2018-12-12 16:51:46', '2018-12-12 16:52:34', '1');
INSERT INTO `file` VALUES ('167', '1-test1txt', 'E:/disk/home/1544604929-1-test1txt.txt', '1', '94b498516fbd21999fd2e4d54b00c3af', '0', '2018-12-12 16:55:29', '2018-12-12 16:55:34', '1');
INSERT INTO `file` VALUES ('168', '1-test2txt', 'E:/disk/home/1544604929-1-test2txt.txt', '1', '354da72caa84e807e1691f9474b6e394', '0', '2018-12-12 16:55:29', '2018-12-12 16:55:34', '1');
INSERT INTO `file` VALUES ('169', 'empty.txt', 'E:/disk/home/6b52c6d4dce14b6da99e51388f563d8f-empty.txt', '0', '', '0', '2018-12-12 16:55:29', '2018-12-12 16:55:35', '1');
INSERT INTO `file` VALUES ('170', '3-testtest22-1txt', 'E:/disk/home/1544604929-3-testtest22-1txt.txt', '3', '93161b6d26e47c80c85f39d8b2566d32', '0', '2018-12-12 16:55:29', '2018-12-12 16:55:35', '1');
INSERT INTO `file` VALUES ('171', '3-testtest11-1txt', 'E:/disk/home/1544604929-3-testtest11-1txt.txt', '3', '5415d3cc1b2b0e07df7320bdf020cf90', '0', '2018-12-12 16:55:29', '2018-12-12 16:55:35', '1');
INSERT INTO `file` VALUES ('172', '4-testtest1test1111-1txt', 'E:/disk/home/1544604929-4-testtest1test1111-1txt.txt', '4', '7556d9d9da6f77c9f30df2e764173fb0', '0', '2018-12-12 16:55:29', '2018-12-12 16:55:35', '1');
INSERT INTO `file` VALUES ('173', '1-test1txt', 'E:/disk/home/1544604952-1-test1txt.txt', '1', '94b498516fbd21999fd2e4d54b00c3af', '0', '2018-12-12 16:55:52', '2018-12-12 16:56:51', '1');
INSERT INTO `file` VALUES ('174', '1-test2txt', 'E:/disk/home/1544604952-1-test2txt.txt', '1', '354da72caa84e807e1691f9474b6e394', '0', '2018-12-12 16:55:52', '2018-12-12 16:56:51', '1');
INSERT INTO `file` VALUES ('175', 'empty.txt', 'E:/disk/home/aa6af7a581e343f9ae84f8f36f00f59c-empty.txt', '0', '', '0', '2018-12-12 16:55:52', '2018-12-12 16:56:51', '1');
INSERT INTO `file` VALUES ('176', '3-testtest22-1txt', 'E:/disk/home/1544604952-3-testtest22-1txt.txt', '3', '93161b6d26e47c80c85f39d8b2566d32', '0', '2018-12-12 16:55:52', '2018-12-12 16:56:51', '1');
INSERT INTO `file` VALUES ('177', '3-testtest11-1txt', 'E:/disk/home/1544604952-3-testtest11-1txt.txt', '3', '5415d3cc1b2b0e07df7320bdf020cf90', '0', '2018-12-12 16:55:52', '2018-12-12 16:56:51', '1');
INSERT INTO `file` VALUES ('178', '4-testtest1test1111-1txt', 'E:/disk/home/1544604952-4-testtest1test1111-1txt.txt', '4', '7556d9d9da6f77c9f30df2e764173fb0', '0', '2018-12-12 16:55:52', '2018-12-12 16:56:51', '1');
INSERT INTO `file` VALUES ('179', '1-test1txt', 'E:/disk/home/1544605030-1-test1txt.txt', '1', '94b498516fbd21999fd2e4d54b00c3af', '0', '2018-12-12 16:57:10', '2018-12-12 17:21:12', '1');
INSERT INTO `file` VALUES ('180', '1-test2txt', 'E:/disk/home/1544605030-1-test2txt.txt', '1', '354da72caa84e807e1691f9474b6e394', '0', '2018-12-12 16:57:10', '2018-12-12 17:21:12', '1');
INSERT INTO `file` VALUES ('181', 'empty.txt', 'E:/disk/home/238337f3c72a42599b92a5623a605ef3-empty.txt', '0', '', '0', '2018-12-12 16:57:10', '2018-12-12 17:21:12', '1');
INSERT INTO `file` VALUES ('182', '3-testtest22-1txt', 'E:/disk/home/1544605030-3-testtest22-1txt.txt', '3', '93161b6d26e47c80c85f39d8b2566d32', '0', '2018-12-12 16:57:10', '2018-12-12 17:21:12', '1');
INSERT INTO `file` VALUES ('183', '3-testtest11-1txt', 'E:/disk/home/1544605030-3-testtest11-1txt.txt', '3', '5415d3cc1b2b0e07df7320bdf020cf90', '0', '2018-12-12 16:57:10', '2018-12-12 17:21:12', '1');
INSERT INTO `file` VALUES ('184', '4-testtest1test1111-1txt', 'E:/disk/home/1544605030-4-testtest1test1111-1txt.txt', '4', '7556d9d9da6f77c9f30df2e764173fb0', '0', '2018-12-12 16:57:10', '2018-12-12 17:21:12', '1');
INSERT INTO `file` VALUES ('185', '20965-apache-maven-354LICENSE', 'E:/disk/home/1544606494-20965-apache-maven-354LICENSE.', '20965', 'b9281f8cbde7c031bb310a0e1ef2e205', '1', '2018-12-12 17:21:34', '2018-12-12 17:21:34', '0');
INSERT INTO `file` VALUES ('186', '182-apache-maven-354NOTICE', 'E:/disk/home/1544606494-182-apache-maven-354NOTICE.', '182', 'c27359b682871b1951509e6fed4992ae', '1', '2018-12-12 17:21:34', '2018-12-12 17:21:34', '0');
INSERT INTO `file` VALUES ('187', '2530-apache-maven-354READMEtxt', 'E:/disk/home/1544606494-2530-apache-maven-354READMEtxt.txt', '2530', '98e48fe4b227965d5b382fce434102cb', '1', '2018-12-12 17:21:34', '2018-12-12 17:21:34', '0');
INSERT INTO `file` VALUES ('188', '4467-apache-maven-354libaopalliance-10jar', 'E:/disk/home/1544606494-4467-apache-maven-354libaopalliance-10jar.jar', '4467', '73e82ff8d5992407bf01b209d0d2516b', '1', '2018-12-12 17:21:34', '2018-12-12 17:21:34', '0');
INSERT INTO `file` VALUES ('189', '44908-apache-maven-354libcdi-api-10jar', 'E:/disk/home/1544606494-44908-apache-maven-354libcdi-api-10jar.jar', '44908', '3e31090d42a4c09515584e8e2f7f2aae', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('190', '23505-apache-maven-354libcdi-apilicense', 'E:/disk/home/1544606495-23505-apache-maven-354libcdi-apilicense.license', '23505', '87d1cfa640af53c3d963cb22f9d449ee', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('191', '53820-apache-maven-354libcommons-cli-14jar', 'E:/disk/home/1544606495-53820-apache-maven-354libcommons-cli-14jar.jar', '53820', '9d64edd850b9c0c352c81fa5a2343450', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('192', '11358-apache-maven-354libcommons-clilicense', 'E:/disk/home/1544606495-11358-apache-maven-354libcommons-clilicense.license', '11358', '5a3a190babfed18319079944adcd1ea3', '3', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('193', '208700-apache-maven-354libcommons-io-25jar', 'E:/disk/home/1544606495-208700-apache-maven-354libcommons-io-25jar.jar', '208700', 'b69ea282f8319df15fccf075c740fff1', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('194', '479881-apache-maven-354libcommons-lang3-35jar', 'E:/disk/home/1544606495-479881-apache-maven-354libcommons-lang3-35jar.jar', '479881', '81bac61501ccec9dfde310c4e2f44be8', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('195', '2442625-apache-maven-354libguava-200jar', 'E:/disk/home/1544606495-2442625-apache-maven-354libguava-200jar.jar', '2442625', '4d4af143a536efd4fcbf2c1f9031b2f4', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('196', '521057-apache-maven-354libguice-420-no_aopjar', 'E:/disk/home/1544606495-521057-apache-maven-354libguice-420-no_aopjar.jar', '521057', '005299ed01b5587463ee14d3dbc8bc9b', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('197', '283858-apache-maven-354libjansi-1171jar', 'E:/disk/home/1544606495-283858-apache-maven-354libjansi-1171jar.jar', '283858', '5e003626c00b6b4fb61f36fef63a3ed9', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('198', '2497-apache-maven-354libjavaxinject-1jar', 'E:/disk/home/1544606495-2497-apache-maven-354libjavaxinject-1jar.jar', '2497', 'f2e0bd255456817158eb40e3c617c20a', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('199', '16515-apache-maven-354libjcl-over-slf4j-1725jar', 'E:/disk/home/1544606495-16515-apache-maven-354libjcl-over-slf4j-1725jar.jar', '16515', 'bf4417375edd8da314cc226508f078af', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('200', '194-apache-maven-354libjcl-over-slf4jlicense', 'E:/disk/home/1544606495-194-apache-maven-354libjcl-over-slf4jlicense.license', '194', 'f231a0c8afd3b2d727fc43bcf67e1fe8', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('201', '5848-apache-maven-354libjsr250-api-10jar', 'E:/disk/home/1544606495-5848-apache-maven-354libjsr250-api-10jar.jar', '5848', '935f7bef454cb4609f7ae3af938323b8', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('202', 'jsr250-api.license', 'E:/disk/home/b3374170e4874d14b4af806573e1d0d2-jsr250-api.license', '0', '', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('203', '54893-apache-maven-354libmaven-artifact-354jar', 'E:/disk/home/1544606495-54893-apache-maven-354libmaven-artifact-354jar.jar', '54893', '16b0bbb78bc02c863e6fc382970aabbf', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('204', '11358-apache-maven-354libmaven-artifactlicense', 'E:/disk/home/1544606495-11358-apache-maven-354libmaven-artifactlicense.license', '11358', 'b4b6fcb2dbdf1d6322857672c773725c', '26', '2018-12-12 17:21:35', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('205', '14781-apache-maven-354libmaven-builder-support-354jar', 'E:/disk/home/1544606495-14781-apache-maven-354libmaven-builder-support-354jar.jar', '14781', '1375a33adac0ffff01ba9c1a396bdaff', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('206', '290368-apache-maven-354libmaven-compat-354jar', 'E:/disk/home/1544606495-290368-apache-maven-354libmaven-compat-354jar.jar', '290368', '1189c75fba66e46484aa18c251291b43', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('207', '630101-apache-maven-354libmaven-core-354jar', 'E:/disk/home/1544606495-630101-apache-maven-354libmaven-core-354jar.jar', '630101', '34ae86fed2612b41a2c7477a3f1b4012', '1', '2018-12-12 17:21:35', '2018-12-12 17:21:35', '0');
INSERT INTO `file` VALUES ('208', '98372-apache-maven-354libmaven-embedder-354jar', 'E:/disk/home/1544606496-98372-apache-maven-354libmaven-embedder-354jar.jar', '98372', 'f5ab0b4072af7708ace69e660f43bedf', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('209', '164976-apache-maven-354libmaven-model-354jar', 'E:/disk/home/1544606496-164976-apache-maven-354libmaven-model-354jar.jar', '164976', '2bd5bae034d4422c5d4bf85a826273aa', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('210', '177426-apache-maven-354libmaven-model-builder-354jar', 'E:/disk/home/1544606496-177426-apache-maven-354libmaven-model-builder-354jar.jar', '177426', 'cec35600e711eab44e95a093a66a5636', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('211', '47639-apache-maven-354libmaven-plugin-api-354jar', 'E:/disk/home/1544606496-47639-apache-maven-354libmaven-plugin-api-354jar.jar', '47639', '0842bd3ea229b2be173ddad4d3910d31', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('212', '27457-apache-maven-354libmaven-repository-metadata-354jar', 'E:/disk/home/1544606496-27457-apache-maven-354libmaven-repository-metadata-354jar.jar', '27457', '8e5bec59293a60b82d8f9ec125be4535', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('213', '146201-apache-maven-354libmaven-resolver-api-111jar', 'E:/disk/home/1544606496-146201-apache-maven-354libmaven-resolver-api-111jar.jar', '146201', 'c04c6af25030392b870fdfd341fc2895', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('214', '42804-apache-maven-354libmaven-resolver-connector-basic-111jar', 'E:/disk/home/1544606496-42804-apache-maven-354libmaven-resolver-connector-basic-111jar.jar', '42804', 'a3885a09a1eb7e71c3c24fb5a6afb942', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('215', '184592-apache-maven-354libmaven-resolver-impl-111jar', 'E:/disk/home/1544606496-184592-apache-maven-354libmaven-resolver-impl-111jar.jar', '184592', 'c8d97d0d2b8a54bc312a67fdf4c4f45f', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('216', '67004-apache-maven-354libmaven-resolver-provider-354jar', 'E:/disk/home/1544606496-67004-apache-maven-354libmaven-resolver-provider-354jar.jar', '67004', '6e3524306918e038d878037517dddb96', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('217', '35811-apache-maven-354libmaven-resolver-spi-111jar', 'E:/disk/home/1544606496-35811-apache-maven-354libmaven-resolver-spi-111jar.jar', '35811', '49162dfb02a6a6d0404a6295f832aea4', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('218', '30919-apache-maven-354libmaven-resolver-transport-wagon-111jar', 'E:/disk/home/1544606496-30919-apache-maven-354libmaven-resolver-transport-wagon-111jar.jar', '30919', 'aa78d217905f11120ef83f600e0690f7', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('219', '158902-apache-maven-354libmaven-resolver-util-111jar', 'E:/disk/home/1544606496-158902-apache-maven-354libmaven-resolver-util-111jar.jar', '158902', '2905da99e6f7875b7a9c383ae8ded87b', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('220', '44566-apache-maven-354libmaven-settings-354jar', 'E:/disk/home/1544606496-44566-apache-maven-354libmaven-settings-354jar.jar', '44566', 'bb500bb89647c69b61d2f4a37eb4be97', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('221', '43145-apache-maven-354libmaven-settings-builder-354jar', 'E:/disk/home/1544606496-43145-apache-maven-354libmaven-settings-builder-354jar.jar', '43145', 'bea4092824cf96b124bad85f5d61ef9e', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('222', '166562-apache-maven-354libmaven-shared-utils-321jar', 'E:/disk/home/1544606496-166562-apache-maven-354libmaven-shared-utils-321jar.jar', '166562', '659de5a6ad3ecad1fa50250a7ef2f18c', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('223', '24164-apache-maven-354libmaven-slf4j-provider-354jar', 'E:/disk/home/1544606496-24164-apache-maven-354libmaven-slf4j-provider-354jar.jar', '24164', '4e8fc94284b093206e6bc359bc5365fa', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('224', '379175-apache-maven-354liborgeclipsesisuinject-033jar', 'E:/disk/home/1544606496-379175-apache-maven-354liborgeclipsesisuinject-033jar.jar', '379175', '32affb8310e6cf824da21900c0f3a16e', '1', '2018-12-12 17:21:36', '2018-12-12 17:21:36', '0');
INSERT INTO `file` VALUES ('225', '12637-apache-maven-354liborgeclipsesisuinjectlicense', 'E:/disk/home/1544606497-12637-apache-maven-354liborgeclipsesisuinjectlicense.license', '12637', 'ee1a8669a141ac1d0ae8c6e7ac660ed7', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('226', '205307-apache-maven-354liborgeclipsesisuplexus-033jar', 'E:/disk/home/1544606497-205307-apache-maven-354liborgeclipsesisuplexus-033jar.jar', '205307', 'bfd5ff5971ed15fcac2d1607980403a0', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('227', '12637-apache-maven-354liborgeclipsesisuplexuslicense', 'E:/disk/home/1544606497-12637-apache-maven-354liborgeclipsesisuplexuslicense.license', '12637', 'd154580c7b19c4d2ff4e08f13eb05993', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('228', '13350-apache-maven-354libplexus-cipher-17jar', 'E:/disk/home/1544606497-13350-apache-maven-354libplexus-cipher-17jar.jar', '13350', '8701bb16588e0ca1146841e3559921db', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('229', '23505-apache-maven-354libplexus-cipherlicense', 'E:/disk/home/1544606497-23505-apache-maven-354libplexus-cipherlicense.license', '23505', 'a958267fc23b927fdff58a0b8f9b159c', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('230', '4288-apache-maven-354libplexus-component-annotations-171jar', 'E:/disk/home/1544606497-4288-apache-maven-354libplexus-component-annotations-171jar.jar', '4288', '860bf79c024ae5ee64b9500d7b07427e', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('231', '78876-apache-maven-354libplexus-interpolation-124jar', 'E:/disk/home/1544606497-78876-apache-maven-354libplexus-interpolation-124jar.jar', '78876', '53dbac0cda2e44b09f94b8a0069975f6', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('232', '27703-apache-maven-354libplexus-sec-dispatcher-14jar', 'E:/disk/home/1544606497-27703-apache-maven-354libplexus-sec-dispatcher-14jar.jar', '27703', 'd7191573eb9d497b62e798d12d805136', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('233', '261617-apache-maven-354libplexus-utils-310jar', 'E:/disk/home/1544606497-261617-apache-maven-354libplexus-utils-310jar.jar', '261617', 'd5cb6f577576f54be77c1343e7c4f50b', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('234', '41203-apache-maven-354libslf4j-api-1725jar', 'E:/disk/home/1544606497-41203-apache-maven-354libslf4j-api-1725jar.jar', '41203', '768bb1b729c3b5d2df7de46776f39ab9', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('235', '11561-apache-maven-354libwagon-file-310jar', 'E:/disk/home/1544606497-11561-apache-maven-354libwagon-file-310jar.jar', '11561', '08ae636491b79cce8365d0d43dc0741e', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('236', '2138907-apache-maven-354libwagon-http-310-shadedjar', 'E:/disk/home/1544606497-2138907-apache-maven-354libwagon-http-310-shadedjar.jar', '2138907', '9e519c0352543242cc4fc890656d9ada', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('237', '55282-apache-maven-354libwagon-provider-api-310jar', 'E:/disk/home/1544606497-55282-apache-maven-354libwagon-provider-api-310jar.jar', '55282', 'c6dbf4af8f65fb3481bbd9ab0d6c71db', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('238', '392-apache-maven-354libjansi-nativeREADMEtxt', 'E:/disk/home/1544606497-392-apache-maven-354libjansi-nativeREADMEtxt.txt', '392', '66382045161e1c0c214a3ae5377fd573', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('239', '26112-apache-maven-354libjansi-nativewindows64jansidll', 'E:/disk/home/1544606497-26112-apache-maven-354libjansi-nativewindows64jansidll.dll', '26112', 'd32f14070c65fd4e447fbf03730a360e', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('240', '21504-apache-maven-354libjansi-nativewindows32jansidll', 'E:/disk/home/1544606497-21504-apache-maven-354libjansi-nativewindows32jansidll.dll', '21504', '1a8562ba52a71d0d9e8429c12f121a49', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('241', '20676-apache-maven-354libjansi-nativeosxlibjansijnilib', 'E:/disk/home/1544606497-20676-apache-maven-354libjansi-nativeosxlibjansijnilib.jnilib', '20676', 'cc9a269423c2b900532c77291d87894f', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('242', '109048-apache-maven-354libjansi-nativelinux64libjansiso', 'E:/disk/home/1544606497-109048-apache-maven-354libjansi-nativelinux64libjansiso.so', '109048', '98cc6e311dd050541f57d245c13aebc4', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('243', '98876-apache-maven-354libjansi-nativelinux32libjansiso', 'E:/disk/home/1544606497-98876-apache-maven-354libjansi-nativelinux32libjansiso.so', '98876', '1e2e878a902aa99656981e1158b22202', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('244', '104088-apache-maven-354libjansi-nativefreebsd64libjansiso', 'E:/disk/home/1544606497-104088-apache-maven-354libjansi-nativefreebsd64libjansiso.so', '104088', 'f109a2a37e7b039454191c1ff3da660c', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('245', '98380-apache-maven-354libjansi-nativefreebsd32libjansiso', 'E:/disk/home/1544606497-98380-apache-maven-354libjansi-nativefreebsd32libjansiso.so', '98380', '971a67ca97fc5bee314b2e9a695a38c8', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('246', '152-apache-maven-354libextREADMEtxt', 'E:/disk/home/1544606497-152-apache-maven-354libextREADMEtxt.txt', '152', 'f57b9149c328ab70f38d359c018893ea', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('247', '14067-apache-maven-354confsettingsxml', 'E:/disk/home/1544606497-14067-apache-maven-354confsettingsxml.xml', '14067', 'c0170ce4dd5ac1dd028f4569aca82e04', '1', '2018-12-12 17:21:37', '2018-12-12 17:21:37', '0');
INSERT INTO `file` VALUES ('248', '3645-apache-maven-354conftoolchainsxml', 'E:/disk/home/1544606498-3645-apache-maven-354conftoolchainsxml.xml', '3645', 'b44e55abe58bb7cd4e42a22c792788a7', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('249', '1519-apache-maven-354confloggingsimpleloggerproperties', 'E:/disk/home/1544606498-1519-apache-maven-354confloggingsimpleloggerproperties.properties', '1519', 'c52adcd0978b272d8edc9787579e696d', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('250', '52684-apache-maven-354bootplexus-classworlds-252jar', 'E:/disk/home/1544606498-52684-apache-maven-354bootplexus-classworlds-252jar.jar', '52684', 'e6a27b127251a221bdaa9c28a0cc0312', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('251', '228-apache-maven-354binm2conf', 'E:/disk/home/1544606498-228-apache-maven-354binm2conf.conf', '228', 'b4a0c2b9cd6a1fd2ed9a90ab87046a12', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('252', '5741-apache-maven-354binmvn', 'E:/disk/home/1544606498-5741-apache-maven-354binmvn.', '5741', '4feebb1d7b267db77d67b65ee73a61a3', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('253', '6343-apache-maven-354binmvncmd', 'E:/disk/home/1544606498-6343-apache-maven-354binmvncmd.cmd', '6343', '50b1008d5e6c2148483d57272808343d', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('254', '1485-apache-maven-354binmvnDebug', 'E:/disk/home/1544606498-1485-apache-maven-354binmvnDebug.', '1485', '574b2b445d6c0dfccee5bd29243a2755', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('255', '1668-apache-maven-354binmvnDebugcmd', 'E:/disk/home/1544606498-1668-apache-maven-354binmvnDebugcmd.cmd', '1668', '5ba496c92efd721567ef6758897d5653', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');
INSERT INTO `file` VALUES ('256', '1532-apache-maven-354binmvnyjp', 'E:/disk/home/1544606498-1532-apache-maven-354binmvnyjp.', '1532', 'cffde892845df2665153129b8f043819', '1', '2018-12-12 17:21:38', '2018-12-12 17:21:38', '0');

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
  PRIMARY KEY (`id`),
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=364 DEFAULT CHARSET=utf8 COMMENT='用户文件目录表';

-- ----------------------------
-- Records of file_content
-- ----------------------------
INSERT INTO `file_content` VALUES ('263', 'LICENSE', '20KB', '63', 'unknown', '185', '2', '2018-12-12 17:21:34', '2018-12-12 17:21:34');
INSERT INTO `file_content` VALUES ('264', 'NOTICE', '182B', '63', 'unknown', '186', '2', '2018-12-12 17:21:34', '2018-12-12 17:21:34');
INSERT INTO `file_content` VALUES ('265', 'README.txt', '2KB', '63', 'text', '187', '2', '2018-12-12 17:21:34', '2018-12-12 17:21:34');
INSERT INTO `file_content` VALUES ('266', 'aopalliance-1.0.jar', '4KB', '64', 'unknown', '188', '2', '2018-12-12 17:21:34', '2018-12-12 17:21:34');
INSERT INTO `file_content` VALUES ('267', 'cdi-api-1.0.jar', '43KB', '64', 'unknown', '189', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('268', 'cdi-api.license', '22KB', '64', 'unknown', '190', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('269', 'commons-cli-1.4.jar', '52KB', '64', 'unknown', '191', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('270', 'commons-cli.license', '11KB', '64', 'unknown', '192', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('271', 'commons-io-2.5.jar', '203KB', '64', 'unknown', '193', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('272', 'commons-io.license', '11KB', '64', 'unknown', '192', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('273', 'commons-lang3-3.5.jar', '468KB', '64', 'unknown', '194', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('274', 'commons-lang3.license', '11KB', '64', 'unknown', '192', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('275', 'guava-20.0.jar', '2.0MB', '64', 'unknown', '195', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('276', 'guice-4.2.0-no_aop.jar', '508KB', '64', 'unknown', '196', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('277', 'jansi-1.17.1.jar', '277KB', '64', 'unknown', '197', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('278', 'javax.inject-1.jar', '2KB', '64', 'unknown', '198', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('279', 'jcl-over-slf4j-1.7.25.jar', '16KB', '64', 'unknown', '199', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('280', 'jcl-over-slf4j.license', '194B', '64', 'unknown', '200', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('281', 'jsr250-api-1.0.jar', '5KB', '64', 'unknown', '201', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('282', 'jsr250-api.license', '0B', '64', 'unknown', '202', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('283', 'maven-artifact-3.5.4.jar', '53KB', '64', 'unknown', '203', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('284', 'maven-artifact.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('285', 'maven-builder-support-3.5.4.jar', '14KB', '64', 'unknown', '205', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('286', 'maven-builder-support.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('287', 'maven-compat-3.5.4.jar', '283KB', '64', 'unknown', '206', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('288', 'maven-compat.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('289', 'maven-core-3.5.4.jar', '615KB', '64', 'unknown', '207', '2', '2018-12-12 17:21:35', '2018-12-12 17:21:35');
INSERT INTO `file_content` VALUES ('290', 'maven-core.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('291', 'maven-embedder-3.5.4.jar', '96KB', '64', 'unknown', '208', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('292', 'maven-embedder.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('293', 'maven-model-3.5.4.jar', '161KB', '64', 'unknown', '209', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('294', 'maven-model-builder-3.5.4.jar', '173KB', '64', 'unknown', '210', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('295', 'maven-model-builder.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('296', 'maven-model.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('297', 'maven-plugin-api-3.5.4.jar', '46KB', '64', 'unknown', '211', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('298', 'maven-plugin-api.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('299', 'maven-repository-metadata-3.5.4.jar', '26KB', '64', 'unknown', '212', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('300', 'maven-repository-metadata.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('301', 'maven-resolver-api-1.1.1.jar', '142KB', '64', 'unknown', '213', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('302', 'maven-resolver-api.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('303', 'maven-resolver-connector-basic-1.1.1.jar', '41KB', '64', 'unknown', '214', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('304', 'maven-resolver-connector-basic.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('305', 'maven-resolver-impl-1.1.1.jar', '180KB', '64', 'unknown', '215', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('306', 'maven-resolver-impl.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('307', 'maven-resolver-provider-3.5.4.jar', '65KB', '64', 'unknown', '216', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('308', 'maven-resolver-provider.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('309', 'maven-resolver-spi-1.1.1.jar', '34KB', '64', 'unknown', '217', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('310', 'maven-resolver-spi.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('311', 'maven-resolver-transport-wagon-1.1.1.jar', '30KB', '64', 'unknown', '218', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('312', 'maven-resolver-transport-wagon.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('313', 'maven-resolver-util-1.1.1.jar', '155KB', '64', 'unknown', '219', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('314', 'maven-resolver-util.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('315', 'maven-settings-3.5.4.jar', '43KB', '64', 'unknown', '220', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('316', 'maven-settings-builder-3.5.4.jar', '42KB', '64', 'unknown', '221', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('317', 'maven-settings-builder.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('318', 'maven-settings.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('319', 'maven-shared-utils-3.2.1.jar', '162KB', '64', 'unknown', '222', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('320', 'maven-shared-utils.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('321', 'maven-slf4j-provider-3.5.4.jar', '23KB', '64', 'unknown', '223', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('322', 'maven-slf4j-provider.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('323', 'org.eclipse.sisu.inject-0.3.3.jar', '370KB', '64', 'unknown', '224', '2', '2018-12-12 17:21:36', '2018-12-12 17:21:36');
INSERT INTO `file_content` VALUES ('324', 'org.eclipse.sisu.inject.license', '12KB', '64', 'unknown', '225', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('325', 'org.eclipse.sisu.plexus-0.3.3.jar', '200KB', '64', 'unknown', '226', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('326', 'org.eclipse.sisu.plexus.license', '12KB', '64', 'unknown', '227', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('327', 'plexus-cipher-1.7.jar', '13KB', '64', 'unknown', '228', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('328', 'plexus-cipher.license', '22KB', '64', 'unknown', '229', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('329', 'plexus-component-annotations-1.7.1.jar', '4KB', '64', 'unknown', '230', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('330', 'plexus-component-annotations.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('331', 'plexus-interpolation-1.24.jar', '77KB', '64', 'unknown', '231', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('332', 'plexus-interpolation.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('333', 'plexus-sec-dispatcher-1.4.jar', '27KB', '64', 'unknown', '232', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('334', 'plexus-sec-dispatcher.license', '22KB', '64', 'unknown', '229', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('335', 'plexus-utils-3.1.0.jar', '255KB', '64', 'unknown', '233', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('336', 'plexus-utils.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('337', 'slf4j-api-1.7.25.jar', '40KB', '64', 'unknown', '234', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('338', 'slf4j-api.license', '194B', '64', 'unknown', '200', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('339', 'wagon-file-3.1.0.jar', '11KB', '64', 'unknown', '235', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('340', 'wagon-file.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('341', 'wagon-http-3.1.0-shaded.jar', '2.0MB', '64', 'unknown', '236', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('342', 'wagon-http.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('343', 'wagon-provider-api-3.1.0.jar', '53KB', '64', 'unknown', '237', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('344', 'wagon-provider-api.license', '11KB', '64', 'unknown', '204', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('345', 'README.txt', '392B', '65', 'text', '238', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('346', 'jansi.dll', '25KB', '66', 'exe', '239', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('347', 'jansi.dll', '21KB', '67', 'exe', '240', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('348', 'libjansi.jnilib', '20KB', '68', 'unknown', '241', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('349', 'libjansi.so', '106KB', '69', 'unknown', '242', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('350', 'libjansi.so', '96KB', '70', 'unknown', '243', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('351', 'libjansi.so', '101KB', '71', 'unknown', '244', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('352', 'libjansi.so', '96KB', '72', 'unknown', '245', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('353', 'README.txt', '152B', '73', 'text', '246', '2', '2018-12-12 17:21:37', '2018-12-12 17:21:37');
INSERT INTO `file_content` VALUES ('354', 'settings.xml', '13KB', '74', 'text', '247', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('355', 'toolchains.xml', '3KB', '74', 'text', '248', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('356', 'simplelogger.properties', '1KB', '75', 'unknown', '249', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('357', 'plexus-classworlds-2.5.2.jar', '51KB', '76', 'unknown', '250', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('358', 'm2.conf', '228B', '77', 'unknown', '251', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('359', 'mvn', '5KB', '77', 'unknown', '252', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('360', 'mvn.cmd', '6KB', '77', 'unknown', '253', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('361', 'mvnDebug', '1KB', '77', 'unknown', '254', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('362', 'mvnDebug.cmd', '1KB', '77', 'unknown', '255', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');
INSERT INTO `file_content` VALUES ('363', 'mvnyjp', '1KB', '77', 'unknown', '256', '2', '2018-12-12 17:21:38', '2018-12-12 17:21:38');

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
