/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : ms

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 04/03/2023 22:46:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for w_order
-- ----------------------------
DROP TABLE IF EXISTS `w_order`;
CREATE TABLE `w_order`  (
  `id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `wid` int(11) NULL DEFAULT NULL,
  `price` int(11) NULL DEFAULT NULL,
  `flag` int(2) NULL DEFAULT 1,
  `create_time` bigint(20) NULL DEFAULT NULL,
  `pay_time` bigint(20) NULL DEFAULT NULL,
  `refund` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
