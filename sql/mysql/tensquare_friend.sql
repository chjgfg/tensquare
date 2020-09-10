/*
 Navicat Premium Data Transfer

 Source Server         : aaa
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : tensquare_friend

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 10/09/2020 16:32:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_friend
-- ----------------------------
DROP TABLE IF EXISTS `tb_friend`;
CREATE TABLE `tb_friend`  (
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `friendid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '好友ID',
  `islike` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否互相喜欢',
  PRIMARY KEY (`userid`, `friendid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_friend
-- ----------------------------
INSERT INTO `tb_friend` VALUES ('1303294002325491712', '1303294180788932608', '0');

-- ----------------------------
-- Table structure for tb_nofriend
-- ----------------------------
DROP TABLE IF EXISTS `tb_nofriend`;
CREATE TABLE `tb_nofriend`  (
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `friendid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '好友ID',
  PRIMARY KEY (`userid`, `friendid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_nofriend
-- ----------------------------
INSERT INTO `tb_nofriend` VALUES ('1303294002325491712', '1303294180788932608');

SET FOREIGN_KEY_CHECKS = 1;
