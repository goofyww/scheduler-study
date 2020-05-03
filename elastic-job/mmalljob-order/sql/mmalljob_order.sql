/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 127.0.0.1:3306
 Source Schema         : mmall_order

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 02/05/2020 13:29:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collect_order
-- ----------------------------
DROP TABLE IF EXISTS `collect_order`;
CREATE TABLE `collect_order`  (
  `_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `third_order_id` int(11) NOT NULL COMMENT '第三方订单id',
  `type` int(1) NOT NULL COMMENT '订单类型：0：京东订单 1：天猫订单',
  `total_amount` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_state` int(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0：未删除 1：已删除',
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 286 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jd_order
-- ----------------------------
DROP TABLE IF EXISTS `jd_order`;
CREATE TABLE `jd_order`  (
  `_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0:未抓取 1:已抓取',
  `amount` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_state` int(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0:未删除 1:已删除',
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 154 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mmall_order
-- ----------------------------
DROP TABLE IF EXISTS `mmall_order`;
CREATE TABLE `mmall_order`  (
  `_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `amount` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `state` int(1) NOT NULL DEFAULT 1 COMMENT '订单状态 1:未支付 2:已支付 3:已取消',
  `receive_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人姓名',
  `receive_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人地址',
  `receive_mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人手机号',
  `create_user` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_user` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新人',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_state` int(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0:未删除 1:已删除',
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 383 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tm_order
-- ----------------------------
DROP TABLE IF EXISTS `tm_order`;
CREATE TABLE `tm_order`  (
  `_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_state` int(1) NOT NULL DEFAULT 0 COMMENT '状态 0:未抓取 1:已抓取',
  `money` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `create_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `del_state` int(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0:未删除 1:已删除',
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 143 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
