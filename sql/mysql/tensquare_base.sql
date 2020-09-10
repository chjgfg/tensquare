/*
 Navicat Premium Data Transfer

 Source Server         : aaa
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : tensquare_base

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 10/09/2020 16:32:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_city
-- ----------------------------
DROP TABLE IF EXISTS `tb_city`;
CREATE TABLE `tb_city`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市名称',
  `ishot` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否热门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '城市' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_city
-- ----------------------------
INSERT INTO `tb_city` VALUES ('1', '北京', '1');
INSERT INTO `tb_city` VALUES ('1299564926523805696', '天津', '0');
INSERT INTO `tb_city` VALUES ('2', '上海', '1');
INSERT INTO `tb_city` VALUES ('3', '广州', '1');
INSERT INTO `tb_city` VALUES ('4', '深圳', '1');
INSERT INTO `tb_city` VALUES ('5', '天津', '0');
INSERT INTO `tb_city` VALUES ('6', '重庆', '0');
INSERT INTO `tb_city` VALUES ('7', '西安', '0');

-- ----------------------------
-- Table structure for tb_label
-- ----------------------------
DROP TABLE IF EXISTS `tb_label`;
CREATE TABLE `tb_label`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签ID',
  `labelname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `count` bigint(20) NULL DEFAULT NULL COMMENT '使用数量',
  `recommend` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否推荐',
  `fans` bigint(20) NULL DEFAULT NULL COMMENT '粉丝数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_label
-- ----------------------------
INSERT INTO `tb_label` VALUES ('1298999164520239104', 'sscscs', '0', 1, '1', 1);
INSERT INTO `tb_label` VALUES ('1298999820106731520', 'lolololo', '1', 1, '0', 1);
INSERT INTO `tb_label` VALUES ('1299001346162298880', 'ddddddd', '0', 1, '0', 1);
INSERT INTO `tb_label` VALUES ('1299311068824342528', 'sscscs', '0', 1, '0', 1);
INSERT INTO `tb_label` VALUES ('1299566218080030720', '删除三痴', '0', 1, '0', 1);

SET FOREIGN_KEY_CHECKS = 1;
