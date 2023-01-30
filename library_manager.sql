/*
 Navicat Premium Data Transfer

 Source Server         : 远程数据库
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : lxjhua.top:3306
 Source Schema         : library_manager

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 30/01/2023 22:25:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for library_book
-- ----------------------------
DROP TABLE IF EXISTS `library_book`;
CREATE TABLE `library_book`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0代表正常 1代表已删除',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图书名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图书编号',
  `desc_info` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `price` decimal(20, 2) NULL DEFAULT NULL COMMENT '价格',
  `book_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `version` int(11) NULL DEFAULT NULL COMMENT '乐观锁',
  `stock` int(11) NULL DEFAULT NULL COMMENT '书籍库存',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `state` tinyint(1) NULL DEFAULT 0 COMMENT '0代表正常 1代表被借出',
  `loan_out_user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '借阅人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of library_book
-- ----------------------------
INSERT INTO `library_book` VALUES (1, '2023-01-29 20:50:24', 1, '2023-01-30 16:04:37', '百年孤独4', 'BOOK_c5aaef2e3f014d9487a3107a0a283eac', NULL, 10.14, 'zw', 27, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (2, '2023-01-29 22:51:39', 0, '2023-01-30 17:24:26', '百年孤独3', 'BOOK_9060528453014132befd7c0fa900ccbb', NULL, 10.14, 'zw', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (3, '2023-01-30 00:08:09', 1, '2023-01-30 17:24:26', '百年孤独5', 'BOOK_033fe06fef414002bfbdd6acffd7094a', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (4, '2023-01-30 00:18:12', 0, '2023-01-30 17:24:26', '百年孤独5', 'BOOK_d28d84fd4246494d8be28044018694b5', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (5, '2023-01-30 00:18:17', 0, '2023-01-30 17:24:26', '百年孤独6', 'BOOK_492fbcf392d9454c83eb0065f54773a2', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (6, '2023-01-30 00:18:20', 0, '2023-01-30 17:24:26', '百年孤独7', 'BOOK_ae5431f1682044a9a9f1fd9c0414fb05', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (7, '2023-01-30 00:18:23', 0, '2023-01-30 17:24:26', '百年孤独8', 'BOOK_8b14572b8ac54457a3ea3c25b893d403', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (8, '2023-01-30 00:18:28', 0, '2023-01-30 18:55:21', '百年孤独9', 'BOOK_a7ba90a9fbd64006af454a67b6c98ab7', NULL, 10.14, 'we', 3, 0, '加西亚', 1, 4);
INSERT INTO `library_book` VALUES (9, '2023-01-30 00:18:31', 0, '2023-01-30 18:55:19', '百年孤独10', 'BOOK_d625dff0565f469cb437f1c6c9f2e78c', NULL, 10.14, 'we', 1, 0, '加西亚', 1, 4);
INSERT INTO `library_book` VALUES (10, '2023-01-30 00:18:35', 0, '2023-01-30 20:45:43', '百年孤独11', 'BOOK_fcacf991693e40588004b31dc7e6c980', NULL, 10.14, 'we', 4, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (11, '2023-01-30 00:18:37', 1, '2023-01-30 17:24:27', '百年孤独12', 'BOOK_ec145cd0e1a8460798fe88ad5150efc4', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (12, '2023-01-30 00:18:40', 1, '2023-01-30 17:24:27', '百年孤独13', 'BOOK_bd0656fdbda74faf8f7ae7b3c97c5ee7', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (13, '2023-01-30 11:16:28', 1, '2023-01-30 16:04:38', '逃离', 'BOOK_066894acd099449d8ae27379a6be6ba4', NULL, 10.00, 'zw', 0, 1, '艾丽丝', 0, NULL);
INSERT INTO `library_book` VALUES (14, '2023-01-30 15:23:06', 1, '2023-01-30 17:21:51', '百年孤独11', 'BOOK_7a99fd60eb4841e1b7c4538b7cda4209', NULL, 10.14, 'we', 1, 0, '加西亚--修改功能', 1, 2);
INSERT INTO `library_book` VALUES (15, '2023-01-30 15:23:43', 1, '2023-01-30 17:21:47', '百年孤独11--修改功能', 'BOOK_d750de0dbc2944f69f045b1b08a83102', NULL, 10.14, 'we', 4, 0, '加西亚--修改功能', 1, 2);
INSERT INTO `library_book` VALUES (16, '2023-01-30 15:37:48', 1, '2023-01-30 17:12:29', '百年孤独11--测试修改功能', 'BOOK_b49f0231edd64dacb7b3aa61ae247759', NULL, 10.14, 'we', 1, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (17, '2023-01-30 15:38:08', 1, '2023-01-30 16:14:52', '百年孤独5--测试修改功能1111', 'BOOK_f92aa6f375ff4789b1fd0d8bf92043c6', NULL, 10.14, 'we', 0, 1, '加西亚', 0, NULL);
INSERT INTO `library_book` VALUES (18, '2023-01-30 15:58:18', 1, '2023-01-30 16:14:39', '活在当下', 'BOOK_c1e95f875dc44251bbcd92d0781cf150', NULL, 100.00, 'we', 0, 1, '艾丽丝', 0, NULL);
INSERT INTO `library_book` VALUES (19, '2023-01-30 21:14:45', 1, '2023-01-30 21:18:27', '马克菠萝游记', 'BOOK_6b2ec6bb94bb4c4c88439d2bfbda2ac2', NULL, 100.00, 'zw', 2, 1, '马克菠萝', 0, NULL);

-- ----------------------------
-- Table structure for library_book_borrow_log
-- ----------------------------
DROP TABLE IF EXISTS `library_book_borrow_log`;
CREATE TABLE `library_book_borrow_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0代表正常 1代表已删除',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `book_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '书籍id',
  `type_flag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'LOAN_OUT代表借出 LOAN_IN代表归还',
  `book_info` json NULL COMMENT '书籍信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of library_book_borrow_log
-- ----------------------------
INSERT INTO `library_book_borrow_log` VALUES (2, '2023-01-29 23:07:48', 0, 2, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 0, \"author\": \"加西亚\", \"version\": 0, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1674996624000}');
INSERT INTO `library_book_borrow_log` VALUES (3, '2023-01-29 23:08:33', 0, 2, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 1, \"author\": \"加西亚\", \"version\": 1, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675004868000}');
INSERT INTO `library_book_borrow_log` VALUES (4, '2023-01-29 23:08:35', 0, 2, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 2, \"author\": \"加西亚\", \"version\": 2, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675004913000}');
INSERT INTO `library_book_borrow_log` VALUES (5, '2023-01-29 23:08:36', 0, 2, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 3, \"author\": \"加西亚\", \"version\": 3, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675004915000}');
INSERT INTO `library_book_borrow_log` VALUES (6, '2023-01-29 23:08:37', 0, 2, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 4, \"author\": \"加西亚\", \"version\": 4, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675004916000}');
INSERT INTO `library_book_borrow_log` VALUES (7, '2023-01-29 23:08:37', 0, 2, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 5, \"author\": \"加西亚\", \"version\": 5, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675004917000}');
INSERT INTO `library_book_borrow_log` VALUES (8, '2023-01-29 23:08:38', 0, 2, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 6, \"author\": \"加西亚\", \"version\": 6, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675004917000}');
INSERT INTO `library_book_borrow_log` VALUES (9, '2023-01-29 23:55:43', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 7, \"author\": \"加西亚\", \"version\": 7, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675004918000}');
INSERT INTO `library_book_borrow_log` VALUES (10, '2023-01-29 23:55:53', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 6, \"author\": \"加西亚\", \"version\": 8, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007743000}');
INSERT INTO `library_book_borrow_log` VALUES (11, '2023-01-29 23:55:54', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 7, \"author\": \"加西亚\", \"version\": 9, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007753000}');
INSERT INTO `library_book_borrow_log` VALUES (12, '2023-01-29 23:55:56', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 8, \"author\": \"加西亚\", \"version\": 10, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007754000}');
INSERT INTO `library_book_borrow_log` VALUES (13, '2023-01-29 23:56:06', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 9, \"author\": \"加西亚\", \"version\": 11, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007756000}');
INSERT INTO `library_book_borrow_log` VALUES (14, '2023-01-29 23:56:07', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 8, \"author\": \"加西亚\", \"version\": 12, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007766000}');
INSERT INTO `library_book_borrow_log` VALUES (15, '2023-01-29 23:56:07', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 7, \"author\": \"加西亚\", \"version\": 13, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007767000}');
INSERT INTO `library_book_borrow_log` VALUES (16, '2023-01-29 23:56:08', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 6, \"author\": \"加西亚\", \"version\": 14, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007767000}');
INSERT INTO `library_book_borrow_log` VALUES (17, '2023-01-29 23:56:08', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 5, \"author\": \"加西亚\", \"version\": 15, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007768000}');
INSERT INTO `library_book_borrow_log` VALUES (18, '2023-01-29 23:56:09', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 4, \"author\": \"加西亚\", \"version\": 16, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007768000}');
INSERT INTO `library_book_borrow_log` VALUES (19, '2023-01-29 23:56:10', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 3, \"author\": \"加西亚\", \"version\": 17, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007769000}');
INSERT INTO `library_book_borrow_log` VALUES (20, '2023-01-29 23:56:10', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 2, \"author\": \"加西亚\", \"version\": 18, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007770000}');
INSERT INTO `library_book_borrow_log` VALUES (21, '2023-01-29 23:56:11', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 1, \"author\": \"加西亚\", \"version\": 19, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007770000}');
INSERT INTO `library_book_borrow_log` VALUES (25, '2023-01-29 23:56:20', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 0, \"author\": \"加西亚\", \"version\": 20, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007771000}');
INSERT INTO `library_book_borrow_log` VALUES (26, '2023-01-29 23:56:21', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 1, \"author\": \"加西亚\", \"version\": 21, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007780000}');
INSERT INTO `library_book_borrow_log` VALUES (27, '2023-01-29 23:56:26', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 2, \"author\": \"加西亚\", \"version\": 22, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007781000}');
INSERT INTO `library_book_borrow_log` VALUES (28, '2023-01-29 23:56:26', 0, 4, 1, 'LOAN_OUT', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 1, \"author\": \"加西亚\", \"version\": 23, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007786000}');
INSERT INTO `library_book_borrow_log` VALUES (32, '2023-01-30 00:51:08', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 0, \"author\": \"加西亚\", \"version\": 24, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675007786000}');
INSERT INTO `library_book_borrow_log` VALUES (33, '2023-01-30 00:51:10', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 1, \"author\": \"加西亚\", \"version\": 25, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675011068000}');
INSERT INTO `library_book_borrow_log` VALUES (34, '2023-01-30 00:51:12', 0, 4, 1, 'LOAN_IN', '{\"id\": 1, \"code\": \"BOOK_c5aaef2e3f014d9487a3107a0a283eac\", \"price\": 10.14, \"stock\": 2, \"author\": \"加西亚\", \"version\": 26, \"bookName\": \"百年孤独4\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1674996624000, \"updateTime\": 1675011070000}');
INSERT INTO `library_book_borrow_log` VALUES (35, '2023-01-30 17:09:39', 0, 2, 16, 'LOAN_OUT', '{\"id\": 16, \"code\": \"BOOK_b49f0231edd64dacb7b3aa61ae247759\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚\", \"version\": 0, \"bookName\": \"百年孤独11--测试修改功能\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675064268000, \"updateTime\": 1675065879000}');
INSERT INTO `library_book_borrow_log` VALUES (36, '2023-01-30 17:09:43', 0, 2, 15, 'LOAN_OUT', '{\"id\": 15, \"code\": \"BOOK_d750de0dbc2944f69f045b1b08a83102\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚--修改功能\", \"version\": 0, \"bookName\": \"百年孤独11--修改功能\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675063423000, \"updateTime\": 1675065879000}');
INSERT INTO `library_book_borrow_log` VALUES (37, '2023-01-30 17:20:52', 0, 2, 15, 'LOAN_OUT', '{\"id\": 15, \"code\": \"BOOK_d750de0dbc2944f69f045b1b08a83102\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚--修改功能\", \"version\": 1, \"bookName\": \"百年孤独11--修改功能\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675063423000, \"updateTime\": 1675069974000}');
INSERT INTO `library_book_borrow_log` VALUES (38, '2023-01-30 17:20:55', 0, 2, 15, 'LOAN_IN', '{\"id\": 15, \"code\": \"BOOK_d750de0dbc2944f69f045b1b08a83102\", \"price\": 10.14, \"state\": 1, \"stock\": 0, \"author\": \"加西亚--修改功能\", \"version\": 2, \"bookName\": \"百年孤独11--修改功能\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675063423000, \"updateTime\": 1675070452000, \"loanOutUserId\": 2}');
INSERT INTO `library_book_borrow_log` VALUES (39, '2023-01-30 17:20:58', 0, 2, 14, 'LOAN_OUT', '{\"id\": 14, \"code\": \"BOOK_7a99fd60eb4841e1b7c4538b7cda4209\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚--修改功能\", \"version\": 0, \"bookName\": \"百年孤独11\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675063386000, \"updateTime\": 1675065878000}');
INSERT INTO `library_book_borrow_log` VALUES (40, '2023-01-30 17:21:01', 0, 2, 15, 'LOAN_OUT', '{\"id\": 15, \"code\": \"BOOK_d750de0dbc2944f69f045b1b08a83102\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚--修改功能\", \"version\": 3, \"bookName\": \"百年孤独11--修改功能\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675063423000, \"updateTime\": 1675070455000}');
INSERT INTO `library_book_borrow_log` VALUES (41, '2023-01-30 17:25:12', 0, 2, 10, 'LOAN_OUT', '{\"id\": 10, \"code\": \"BOOK_fcacf991693e40588004b31dc7e6c980\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚\", \"version\": 0, \"bookName\": \"百年孤独11\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009115000, \"updateTime\": 1675070667000}');
INSERT INTO `library_book_borrow_log` VALUES (42, '2023-01-30 17:25:27', 0, 2, 10, 'LOAN_IN', '{\"id\": 10, \"code\": \"BOOK_fcacf991693e40588004b31dc7e6c980\", \"price\": 10.14, \"state\": 1, \"stock\": 0, \"author\": \"加西亚\", \"version\": 1, \"bookName\": \"百年孤独11\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009115000, \"updateTime\": 1675070712000, \"loanOutUserId\": 2}');
INSERT INTO `library_book_borrow_log` VALUES (43, '2023-01-30 17:34:25', 0, 2, 10, 'LOAN_OUT', '{\"id\": 10, \"code\": \"BOOK_fcacf991693e40588004b31dc7e6c980\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚\", \"version\": 2, \"bookName\": \"百年孤独11\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009115000, \"updateTime\": 1675070727000}');
INSERT INTO `library_book_borrow_log` VALUES (44, '2023-01-30 18:51:41', 0, 4, 8, 'LOAN_OUT', '{\"id\": 8, \"code\": \"BOOK_a7ba90a9fbd64006af454a67b6c98ab7\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚\", \"version\": 0, \"bookName\": \"百年孤独9\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009108000, \"updateTime\": 1675070666000}');
INSERT INTO `library_book_borrow_log` VALUES (45, '2023-01-30 18:51:47', 0, 4, 8, 'LOAN_IN', '{\"id\": 8, \"code\": \"BOOK_a7ba90a9fbd64006af454a67b6c98ab7\", \"price\": 10.14, \"state\": 1, \"stock\": 0, \"author\": \"加西亚\", \"version\": 1, \"bookName\": \"百年孤独9\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009108000, \"updateTime\": 1675075901000, \"loanOutUserId\": 4}');
INSERT INTO `library_book_borrow_log` VALUES (46, '2023-01-30 18:55:19', 0, 4, 9, 'LOAN_OUT', '{\"id\": 9, \"code\": \"BOOK_d625dff0565f469cb437f1c6c9f2e78c\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚\", \"version\": 0, \"bookName\": \"百年孤独10\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009111000, \"updateTime\": 1675070659000}');
INSERT INTO `library_book_borrow_log` VALUES (47, '2023-01-30 18:55:21', 0, 4, 8, 'LOAN_OUT', '{\"id\": 8, \"code\": \"BOOK_a7ba90a9fbd64006af454a67b6c98ab7\", \"price\": 10.14, \"state\": 0, \"stock\": 1, \"author\": \"加西亚\", \"version\": 2, \"bookName\": \"百年孤独9\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009108000, \"updateTime\": 1675075907000}');
INSERT INTO `library_book_borrow_log` VALUES (48, '2023-01-30 20:45:43', 0, 2, 10, 'LOAN_IN', '{\"id\": 10, \"code\": \"BOOK_fcacf991693e40588004b31dc7e6c980\", \"price\": 10.14, \"state\": 1, \"stock\": 0, \"author\": \"加西亚\", \"version\": 3, \"bookName\": \"百年孤独11\", \"bookType\": \"we\", \"isDelete\": 0, \"createTime\": 1675009115000, \"updateTime\": 1675071265000, \"loanOutUserId\": 2}');
INSERT INTO `library_book_borrow_log` VALUES (49, '2023-01-30 21:15:25', 0, 2, 19, 'LOAN_OUT', '{\"id\": 19, \"code\": \"BOOK_6b2ec6bb94bb4c4c88439d2bfbda2ac2\", \"price\": 100, \"state\": 0, \"stock\": 1, \"author\": \"马克菠萝\", \"version\": 0, \"bookName\": \"马克菠萝游记\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1675084485000, \"updateTime\": 1675084485000}');
INSERT INTO `library_book_borrow_log` VALUES (50, '2023-01-30 21:15:54', 0, 2, 19, 'LOAN_IN', '{\"id\": 19, \"code\": \"BOOK_6b2ec6bb94bb4c4c88439d2bfbda2ac2\", \"price\": 100, \"state\": 1, \"stock\": 0, \"author\": \"马克菠萝\", \"version\": 1, \"bookName\": \"马克菠萝游记\", \"bookType\": \"zw\", \"isDelete\": 0, \"createTime\": 1675084485000, \"updateTime\": 1675084525000, \"loanOutUserId\": 2}');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0代表正常 1代表已删除',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单code',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单对应的路径',
  `file_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单对应的文件路径',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '菜单的类型 0代表页面 2代表按钮',
  `show_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否显示 0代表是 1代表否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '2023-01-29 23:36:19', 0, '2023-01-29 23:36:57', '添加权限', 'library:book:add', NULL, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (2, '2023-01-29 23:37:17', 0, '2023-01-29 23:37:17', '修改权限', 'library:book:edit', NULL, NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (3, '2023-01-29 23:38:46', 0, '2023-01-29 23:38:46', '查询权限', 'library:book:list', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0代表正常 1代表已删除',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色的code',
  `desc_info` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2023-01-29 10:44:41', 0, '2023-01-29 10:44:41', '管理员', 'admin', '管理员权限');
INSERT INTO `sys_role` VALUES (2, '2023-01-29 23:15:54', 0, '2023-01-29 23:29:07', '学生', 'student', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `menu_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 3, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0代表正常 1代表已删除',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `nick_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户的真实姓名',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '用户的性别 0代表男 1代表女 2代表未知',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户的头像地址',
  `country` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家',
  `province` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市',
  `town` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城镇',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `state` tinyint(1) NULL DEFAULT 0 COMMENT '账号的状态 0代表正常 1代表被冻结',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unic_username`(`username`) USING BTREE COMMENT '唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, '2022-10-16 11:40:28', 0, '2022-10-16 11:40:28', 'admin', '$2a$10$9DDUYA3Ns5a8iFo0JSjTZex2.xCcovw8GHO0gyTEeq7c6bzvx8BXS', NULL, '管理员', 'lxj', 1, '111', '11', '1', '1', '1', '1', 0);
INSERT INTO `sys_user` VALUES (7, '2023-01-30 20:59:30', 0, '2023-01-30 20:59:30', 'caitongbin', '$2a$10$SNc4tJtS/W6J/iiaxVWcDuZriEn/zNBx9ipC.6dNsg97NxTreifea', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 2, 1);
INSERT INTO `sys_user_role` VALUES (2, 3, 2);
INSERT INTO `sys_user_role` VALUES (3, 4, 2);
INSERT INTO `sys_user_role` VALUES (4, 5, 1);
INSERT INTO `sys_user_role` VALUES (5, 6, 2);
INSERT INTO `sys_user_role` VALUES (6, 7, 2);
INSERT INTO `sys_user_role` VALUES (7, 8, 2);

SET FOREIGN_KEY_CHECKS = 1;
