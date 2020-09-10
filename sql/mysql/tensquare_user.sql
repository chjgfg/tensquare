/*
 Navicat Premium Data Transfer

 Source Server         : aaa
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : tensquare_user

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 10/09/2020 16:33:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `loginname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('1302106818679541760', 'demoData', '$2a$10$xiiy2KCsdZB8HyFe8c2nEOGDVB0Us.RO0woxWNjm2whHbpccgOWo6', '1');
INSERT INTO `tb_admin` VALUES ('1302162366645014528', '111111', '$2a$10$l6bJH0vLzlha5LY6P7B79O1uvfspiMt2Eml947RQfCkiwGbdJRKGy', '0');
INSERT INTO `tb_admin` VALUES ('1302232241593782272', '000000', '$2a$10$ABQRJp21n8/3u.h.W0Rj4e8ug4XLXZL3wmJjauvYNCVx6LgTRTNB2', '0');

-- ----------------------------
-- Table structure for tb_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_follow`;
CREATE TABLE `tb_follow`  (
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `targetuser` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被关注用户ID',
  PRIMARY KEY (`userid`, `targetuser`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_follow
-- ----------------------------
INSERT INTO `tb_follow` VALUES ('1', '1');
INSERT INTO `tb_follow` VALUES ('1', '10');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `mobile` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生年月日',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'E-Mail',
  `regdate` datetime(0) NULL DEFAULT NULL COMMENT '注册日期',
  `updatedate` datetime(0) NULL DEFAULT NULL COMMENT '修改日期',
  `lastdate` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆日期',
  `online` bigint(20) NULL DEFAULT NULL COMMENT '在线时长（分钟）',
  `interest` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兴趣',
  `personality` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性',
  `fanscount` int(20) NULL DEFAULT NULL COMMENT '粉丝数',
  `followcount` int(20) NULL DEFAULT NULL COMMENT '关注数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1303291909770448896', ' 15993596767', '$2a$10$MGKnoKrQvTedLIWoUSJgZO2gjT26Xf3eKq9UNcvliltXTRbAOCPdC', '3', '0', NULL, NULL, NULL, '2020-09-08 11:19:40', '2020-09-08 11:19:40', '2020-09-08 11:19:40', 0, NULL, NULL, 0, 0);
INSERT INTO `tb_user` VALUES ('1303294002325491712', '15993596769', '$2a$10$gwTdJilwyrkRODmV3HT6LehF10uizNagiWdtVB10HN6dHKVK6m9Qm', '1', '0', NULL, NULL, NULL, '2020-09-08 11:27:59', '2020-09-08 11:27:59', '2020-09-08 11:27:59', 0, NULL, NULL, 0, 0);
INSERT INTO `tb_user` VALUES ('1303294180788932608', '15993596768', '$2a$10$0tqoUDbN/M6vxfY4nTX58OZLCV80tg6rjlrPdI4N65TsPSx3GE76i', '2', '0', NULL, NULL, NULL, '2020-09-08 11:28:41', '2020-09-08 11:28:41', '2020-09-08 11:28:41', 0, NULL, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
