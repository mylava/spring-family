/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : seckill

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 10/23/2018 21:06:19 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `goods_name` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '商品图片',
  `goods_detail` longtext COLLATE utf8_bin COMMENT '商品详情',
  `goods_price` decimal(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT NULL COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `goods`
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES ('1', 'iphoneX', 'Apple iphone X(A1865) 64GB 银色 移动联通电信4G手机', '/img/iphonex.png', 'Apple iphone X(A1865) 64GB 银色 移动联通电信4G手机', '8765.00', '10000'), ('2', '华为Mate10 Pro', '华为 Mate 10 Pro 6G+64GB 黑曜 移动联通电信4G手机 双卡双待', '/img/mate10pro.png', '华为 Mate 10 Pro 6G+64GB 黑曜 移动联通电信4G手机 双卡双待', '6388.00', '-1');
COMMIT;

-- ----------------------------
--  Table structure for `order_info`
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` bigint(20) DEFAULT NULL COMMENT '收货地址ID',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `goods_price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '下单渠道 1pc 2android 3',
  `status` tinyint(4) DEFAULT '0' COMMENT '订单状态 0新建未支付 1已支付 2已发货 3已收货 4已退款 5已完成',
  `create_date` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_date` datetime DEFAULT NULL COMMENT '订单支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `order_info`
-- ----------------------------
BEGIN;
INSERT INTO `order_info` VALUES ('1', '13800000000', '1', '0', 'iphoneX', '1', '0.01', '1', '0', '2018-08-31 06:12:55', null);
COMMIT;

-- ----------------------------
--  Table structure for `seckill_goods`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_goods`;
CREATE TABLE `seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `seckill_price` decimal(10,2) DEFAULT NULL COMMENT '秒杀价',
  `stock_count` int(11) DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `seckill_goods`
-- ----------------------------
BEGIN;
INSERT INTO `seckill_goods` VALUES ('1', '1', '0.01', '9', '2018-08-28 15:42:28', '2018-09-27 18:50:31'), ('2', '2', '0.01', '10', '2018-08-27 18:51:01', '2018-09-27 18:51:08');
COMMIT;

-- ----------------------------
--  Table structure for `seckill_order`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_order`;
CREATE TABLE `seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `seckill_order`
-- ----------------------------
BEGIN;
INSERT INTO `seckill_order` VALUES ('1', '13800000000', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `seckill_user`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_user`;
CREATE TABLE `seckill_user` (
  `id` bigint(20) NOT NULL COMMENT '用户ID，手机号',
  `nickname` varchar(255) NOT NULL,
  `password` varchar(32) DEFAULT NULL COMMENT 'MD5(MD5( pass明文 + 固定salt ) + 随机salt )',
  `salt` varchar(10) DEFAULT NULL,
  `head` varchar(128) DEFAULT NULL COMMENT '头像，云存储ID',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '上次登录时间',
  `login_count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `seckill_user`
-- ----------------------------
BEGIN;
INSERT INTO `seckill_user` VALUES ('13800000000', ' mylava', '830243fa89b592c8275bd0ceb61febcd', '1a2b3c4d', null, '2018-08-22 20:34:04', '2018-08-22 20:34:33', '1');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '张三'), ('2', '李四'), ('3', '王五');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
