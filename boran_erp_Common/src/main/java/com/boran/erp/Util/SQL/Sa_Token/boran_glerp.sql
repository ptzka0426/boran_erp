/*
 Navicat Premium Data Transfer

 Source Server         : boran_glerp
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 8.135.54.170:3306
 Source Schema         : boran_glerp

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 08/09/2021 10:28:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for powerinfo
-- ----------------------------
DROP TABLE IF EXISTS `powerinfo`;
CREATE TABLE `powerinfo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `PowerName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of powerinfo
-- ----------------------------
INSERT INTO `powerinfo` VALUES (1, 'Insert');
INSERT INTO `powerinfo` VALUES (2, 'Update');
INSERT INTO `powerinfo` VALUES (3, 'Delete');
INSERT INTO `powerinfo` VALUES (4, 'Select');

-- ----------------------------
-- Table structure for role_power
-- ----------------------------
DROP TABLE IF EXISTS `role_power`;
CREATE TABLE `role_power`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色权限表',
  `RoleId` int NOT NULL COMMENT '角色',
  `PowerId` int NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `RoleId`(`RoleId`) USING BTREE,
  INDEX `PowerId`(`PowerId`) USING BTREE,
  CONSTRAINT `role_power_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `roleinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_power_ibfk_2` FOREIGN KEY (`PowerId`) REFERENCES `powerinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_power
-- ----------------------------
INSERT INTO `role_power` VALUES (1, 1, 1);
INSERT INTO `role_power` VALUES (2, 1, 2);
INSERT INTO `role_power` VALUES (3, 1, 3);
INSERT INTO `role_power` VALUES (4, 1, 4);
INSERT INTO `role_power` VALUES (5, 2, 1);
INSERT INTO `role_power` VALUES (6, 2, 2);
INSERT INTO `role_power` VALUES (7, 2, 4);

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `RoleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES (1, '超级管理员');
INSERT INTO `roleinfo` VALUES (2, '普通用户');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户角色表',
  `UserId` int NOT NULL COMMENT '用户id',
  `RoleId` int NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `UserId`(`UserId`) USING BTREE,
  INDEX `RoleId`(`RoleId`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`RoleId`) REFERENCES `roleinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_yhm` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_pwd` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `user_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 'longtao', '123', '龙滔', '2021-06-16 16:05:02');
INSERT INTO `userinfo` VALUES (2, 'qiao', '123', '乔一峰', '2021-06-16 16:37:58');

SET FOREIGN_KEY_CHECKS = 1;
