/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : pmp

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-08-06 17:44:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attend_record
-- ----------------------------
DROP TABLE IF EXISTS `attend_record`;
CREATE TABLE `attend_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `start_time` datetime DEFAULT NULL COMMENT '打开开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '打卡结束时间',
  `total` decimal(11,1) DEFAULT NULL COMMENT '工时/小时',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态（1=已打卡;0=未打卡）',
  `create_time` datetime DEFAULT NULL COMMENT '日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='考勤记录';

-- ----------------------------
-- Records of attend_record
-- ----------------------------
INSERT INTO `attend_record` VALUES ('1', '6', '2', '2019-07-24 09:00:00', '2019-07-24 18:00:00', '8.0', '0', '2019-07-24 09:00:00', null);
INSERT INTO `attend_record` VALUES ('2', '11', '3', '2019-07-24 09:00:00', '2019-07-24 18:00:00', '8.0', '1', '2019-07-24 09:00:00', null);
INSERT INTO `attend_record` VALUES ('3', '12', '7', '2019-07-24 09:00:00', '2019-07-24 18:00:00', '8.0', '1', '2019-07-24 09:00:00', null);
INSERT INTO `attend_record` VALUES ('4', '13', '6', '2019-07-24 09:00:00', '2019-07-24 18:00:00', '8.0', '1', '2019-07-24 09:00:00', null);
INSERT INTO `attend_record` VALUES ('5', '14', '8', '2019-07-20 09:00:00', '2019-07-20 18:00:00', '8.0', '1', '2019-07-20 09:00:00', null);
INSERT INTO `attend_record` VALUES ('6', '15', '7', '2019-07-20 09:00:00', '2019-07-20 18:00:00', '8.0', '1', '2019-07-20 09:00:00', null);
INSERT INTO `attend_record` VALUES ('7', '16', '6', '2019-07-20 09:00:00', '2019-07-20 18:00:00', '8.0', '1', '2019-07-20 09:00:00', null);
INSERT INTO `attend_record` VALUES ('8', '26', '5', '2019-07-20 09:00:00', '2019-07-20 18:00:00', '8.0', '1', '2019-07-20 09:00:00', null);
INSERT INTO `attend_record` VALUES ('9', '22', '4', '2019-07-23 09:00:00', '2019-07-23 18:00:00', '8.0', '1', '2019-07-23 09:00:00', null);
INSERT INTO `attend_record` VALUES ('10', '24', '8', '2019-07-23 09:00:00', '2019-07-23 18:00:00', '8.0', '1', '2019-07-23 09:00:00', null);
INSERT INTO `attend_record` VALUES ('11', '25', '6', '2019-07-23 09:00:00', '2019-07-23 18:00:00', '8.0', '1', '2019-07-23 09:00:00', null);
INSERT INTO `attend_record` VALUES ('12', '6', '7', '2019-07-23 09:00:00', '2019-07-23 18:00:00', '8.0', '1', '2019-07-23 09:00:00', null);
INSERT INTO `attend_record` VALUES ('13', '9', '8', '2019-07-23 09:00:00', '2019-07-23 18:00:00', '8.0', '1', '2019-07-23 09:00:00', null);

-- ----------------------------
-- Table structure for item_type
-- ----------------------------
DROP TABLE IF EXISTS `item_type`;
CREATE TABLE `item_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类型编码',
  `type_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '类型名称',
  `order_num` int(11) DEFAULT '0' COMMENT '排序编号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态（1=启用；0=禁用）',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='商品类别';

-- ----------------------------
-- Records of item_type
-- ----------------------------
INSERT INTO `item_type` VALUES ('1', 'T1001', '母婴用品', '0', '1', '2019-07-23 17:55:35', null);
INSERT INTO `item_type` VALUES ('2', 'T1002', '日常用品', '1', '1', '2019-07-23 17:55:37', null);
INSERT INTO `item_type` VALUES ('3', 'T1003', '电子玩具', '2', '1', '2019-07-23 17:55:38', null);
INSERT INTO `item_type` VALUES ('4', 'T1004', '电子产品', '3', '1', '2019-07-23 17:55:39', null);
INSERT INTO `item_type` VALUES ('5', 'T1005', 'IT技术书籍', '4', '1', '2019-07-23 18:55:40', null);
INSERT INTO `item_type` VALUES ('6', 'T1006', '情感书籍', '5', '1', '2019-07-23 17:56:42', null);
INSERT INTO `item_type` VALUES ('7', 'T1007', '文化~教育~旅游书籍', '6', '1', '2019-07-23 17:56:45', null);
INSERT INTO `item_type` VALUES ('8', 'T1008', '外语教学图书', '7', '1', '2019-07-23 17:55:44', null);
INSERT INTO `item_type` VALUES ('9', 'T1009', '实战书籍', '8', '1', '2019-07-23 19:55:47', null);
INSERT INTO `item_type` VALUES ('10', 'T1010', '体育竞技用品', '9', '1', '2019-07-23 17:55:49', null);
INSERT INTO `item_type` VALUES ('11', 'T1011', '衣服', '10', '0', '2019-07-23 17:55:50', null);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '集团总部', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '1', '互联网技术部', '1', '0');
INSERT INTO `sys_dept` VALUES ('3', '1', '销售部', '2', '0');
INSERT INTO `sys_dept` VALUES ('4', '3', '市场调研部', '0', '0');
INSERT INTO `sys_dept` VALUES ('5', '3', '销售中心办公室', '1', '0');
INSERT INTO `sys_dept` VALUES ('6', '2', '开发部', '0', '0');
INSERT INTO `sys_dept` VALUES ('7', '2', '测试部', '1', '0');
INSERT INTO `sys_dept` VALUES ('8', '2', '运维部', '2', '0');
INSERT INTO `sys_dept` VALUES ('9', '2', '产品部', '3', '0');
INSERT INTO `sys_dept` VALUES ('10', '1', '财务部', '0', '0');
INSERT INTO `sys_dept` VALUES ('11', '10', '会计部', '0', '0');
INSERT INTO `sys_dept` VALUES ('12', '10', '审计部', '1', '0');
INSERT INTO `sys_dept` VALUES ('13', '10', '税务部', '2', '0');
INSERT INTO `sys_dept` VALUES ('15', '14', '测试1', '0', '-1');
INSERT INTO `sys_dept` VALUES ('16', '14', '测试2', '1', '-1');
INSERT INTO `sys_dept` VALUES ('17', '11', '测试部门1', '1', '-1');
INSERT INTO `sys_dept` VALUES ('18', '15', '1', '0', '-1');
INSERT INTO `sys_dept` VALUES ('19', '10', '1', '0', '-1');
INSERT INTO `sys_dept` VALUES ('20', '2', '总监部2', '1', '-1');
INSERT INTO `sys_dept` VALUES ('21', '10', '总经理办公室', '3', '0');
INSERT INTO `sys_dept` VALUES ('22', '10', '财务1部', '12', '-1');
INSERT INTO `sys_dept` VALUES ('23', '6', '开发一部', '1', '0');
INSERT INTO `sys_dept` VALUES ('24', '6', '开发二部', '2', '0');
INSERT INTO `sys_dept` VALUES ('25', '23', '前端部', '1', '0');
INSERT INTO `sys_dept` VALUES ('26', '23', '后端部', '1', '0');
INSERT INTO `sys_dept` VALUES ('27', '24', '前端部', '0', '0');
INSERT INTO `sys_dept` VALUES ('28', '24', '测试部', '1', '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('4', '博客类型', 'blogType', 'blogCode', '1', '1', '', '0');
INSERT INTO `sys_dict` VALUES ('5', '博客类型', 'blogType', 'blogCodeV2', '博客编码V2', '2', null, '0');
INSERT INTO `sys_dict` VALUES ('6', '商品类型', 'itemType', 'itemType', '婴儿', '1', '商品类型', '0');
INSERT INTO `sys_dict` VALUES ('7', '商品类型', 'itemType', 'itemType2', '洗发用品', '2', '商品类型', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('169', 'debug', '修改登录密码', 'com.debug.pmp.server.controller.SysUserController.updatePassword()', '\"123456\"', '53', '0:0:0:0:0:0:0:1', '2019-08-05 20:35:03');
INSERT INTO `sys_log` VALUES ('170', 'admin', '删除菜单', 'com.debug.pmp.server.controller.SysMenuController.delete()', '61', '47', '0:0:0:0:0:0:0:1', '2019-08-05 20:49:43');
INSERT INTO `sys_log` VALUES ('171', 'admin', '删除菜单', 'com.debug.pmp.server.controller.SysMenuController.delete()', '65', '47', '0:0:0:0:0:0:0:1', '2019-08-05 20:49:49');
INSERT INTO `sys_log` VALUES ('172', 'admin', '删除菜单', 'com.debug.pmp.server.controller.SysMenuController.delete()', '64', '45', '0:0:0:0:0:0:0:1', '2019-08-05 20:49:54');
INSERT INTO `sys_log` VALUES ('173', 'admin', '删除菜单', 'com.debug.pmp.server.controller.SysMenuController.delete()', '63', '41', '0:0:0:0:0:0:0:1', '2019-08-05 20:49:59');
INSERT INTO `sys_log` VALUES ('174', 'admin', '删除菜单', 'com.debug.pmp.server.controller.SysMenuController.delete()', '62', '42', '0:0:0:0:0:0:0:1', '2019-08-05 20:50:04');
INSERT INTO `sys_log` VALUES ('175', 'admin', '删除菜单', 'com.debug.pmp.server.controller.SysMenuController.delete()', '57', '104', '0:0:0:0:0:0:0:1', '2019-08-05 22:34:17');
INSERT INTO `sys_log` VALUES ('176', 'admin', '删除菜单', 'com.debug.pmp.server.controller.SysMenuController.delete()', '56', '79', '0:0:0:0:0:0:0:1', '2019-08-05 22:34:23');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'modules/sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '5');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '4');
INSERT INTO `sys_menu` VALUES ('15', '2', '查询', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '3', '查询', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '4', '查询', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `sys_menu` VALUES ('31', '1', '部门管理', 'modules/sys/dept.html', null, '1', 'fa fa-file-code-o', '2');
INSERT INTO `sys_menu` VALUES ('32', '31', '查询', null, 'sys:dept:list,sys:dept:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '31', '新增', null, 'sys:dept:save,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('34', '31', '修改', null, 'sys:dept:update,sys:dept:select', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('35', '31', '删除', null, 'sys:dept:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '1', '字典管理', 'modules/sys/dict.html', null, '1', 'fa fa-bookmark-o', '6');
INSERT INTO `sys_menu` VALUES ('37', '36', '查询', null, 'sys:dict:list,sys:dict:info', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('38', '36', '新增', null, 'sys:dict:save', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('39', '36', '修改', null, 'sys:dict:update', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('40', '36', '删除', null, 'sys:dict:delete', '2', null, '6');
INSERT INTO `sys_menu` VALUES ('41', '0', '商品模块', null, null, '0', 'fa fa-coffee', '1');
INSERT INTO `sys_menu` VALUES ('42', '41', '分类管理', 'modules/item/itemType.html', '', '1', 'fa fa-envelope-open', '0');
INSERT INTO `sys_menu` VALUES ('44', '42', '新增', null, 'item:type:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('45', '42', '查询', null, 'item:type:list,item:type:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('46', '42', '修改', null, 'item:type:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('47', '42', '删除', null, 'item:type:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('48', '29', '清除日志', null, 'sys:log:truncate', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('49', '1', '岗位管理', 'modules/sys/post.html', 'sys:post:list', '1', 'fa fa-podcast', '3');
INSERT INTO `sys_menu` VALUES ('50', '49', '查询', null, 'sys:post:list,sys:post:info', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('51', '49', '新增', null, 'sys:post:save', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('52', '49', '修改', null, 'sys:post:update', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('53', '49', '删除', null, 'sys:post:delete', '2', null, '0');
INSERT INTO `sys_menu` VALUES ('54', '0', '考勤管理', null, null, '0', 'fa fa-compass', '2');
INSERT INTO `sys_menu` VALUES ('55', '54', '考勤记录', 'modules/attend/attendRecord.html', 'attend:record:list', '1', 'fa fa-shower', '0');
INSERT INTO `sys_menu` VALUES ('60', '2', '重置密码', null, 'sys:user:resetPsd', '2', null, '0');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `order_num` int(4) NOT NULL DEFAULT '0' COMMENT '排序号',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态（1正常 0停用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `idx_post_code` (`post_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', 'Rank1001', '董事长', '0', '1', '2019-07-22 23:16:28', null, '董事长');
INSERT INTO `sys_post` VALUES ('2', 'Rank1002', '总经理', '1', '1', '2019-07-22 23:17:30', null, '总经理');
INSERT INTO `sys_post` VALUES ('3', 'Rank1003', '互联网技术总监', '2', '1', '2019-07-23 09:25:38', null, '互联网技术总监');
INSERT INTO `sys_post` VALUES ('4', 'Rank1004', '财务总监', '3', '1', '2019-07-23 09:25:55', null, '财务总监');
INSERT INTO `sys_post` VALUES ('5', 'Rank1005', '技术主管', '4', '1', '2019-07-23 09:26:17', null, '技术主管');
INSERT INTO `sys_post` VALUES ('6', 'Rank1006', '产品经理', '5', '1', '2019-07-23 09:27:02', null, '产品经理');
INSERT INTO `sys_post` VALUES ('7', 'Rank1007', '开发部经理', '6', '1', '2019-07-23 09:27:14', null, '开发部经理');
INSERT INTO `sys_post` VALUES ('8', 'Rank1008', '运维主管', '7', '1', '2019-07-23 09:27:23', null, '运维主管');
INSERT INTO `sys_post` VALUES ('9', 'Rank1009', '高级开发工程师', '8', '1', '2019-07-23 09:27:35', null, '高级开发工程师');
INSERT INTO `sys_post` VALUES ('10', 'R1010', 'HRBP', '9', '1', '2019-07-23 09:27:52', '2019-07-23 10:01:10', 'HRBP');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '市场调研部-普通管理员', '市场调研部-普通管理员', '2019-02-22 14:29:45');
INSERT INTO `sys_role` VALUES ('3', '互联网技术部-普通管理员', '互联网技术部-普通管理员', '2019-07-19 17:25:23');
INSERT INTO `sys_role` VALUES ('4', '销售部-普通管理员', '销售部-普通管理员', '2019-07-19 17:48:33');
INSERT INTO `sys_role` VALUES ('7', '开发部-管理员', '开发部-管理员', '2019-07-19 18:04:45');
INSERT INTO `sys_role` VALUES ('10', '财务部~超级管理员', '财务部~超级管理员', '2019-07-21 22:21:58');
INSERT INTO `sys_role` VALUES ('11', '测试部~超级管理员', '测试部~超级管理员', '2019-07-21 23:02:19');
INSERT INTO `sys_role` VALUES ('12', '数据视野管理员', '数据视野管理员', '2019-08-01 16:30:50');
INSERT INTO `sys_role` VALUES ('13', '博客管理~角色', '博客管理~角色', '2019-08-03 23:33:18');
INSERT INTO `sys_role` VALUES ('16', '操作权限角色', '操作权限角色', '2019-08-04 21:02:58');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('9', '3', '2');
INSERT INTO `sys_role_dept` VALUES ('10', '3', '6');
INSERT INTO `sys_role_dept` VALUES ('11', '3', '7');
INSERT INTO `sys_role_dept` VALUES ('12', '3', '8');
INSERT INTO `sys_role_dept` VALUES ('13', '3', '9');
INSERT INTO `sys_role_dept` VALUES ('17', '1', '3');
INSERT INTO `sys_role_dept` VALUES ('18', '1', '4');
INSERT INTO `sys_role_dept` VALUES ('19', '1', '5');
INSERT INTO `sys_role_dept` VALUES ('27', '4', '1');
INSERT INTO `sys_role_dept` VALUES ('28', '4', '3');
INSERT INTO `sys_role_dept` VALUES ('29', '4', '4');
INSERT INTO `sys_role_dept` VALUES ('30', '4', '5');
INSERT INTO `sys_role_dept` VALUES ('44', '7', '1');
INSERT INTO `sys_role_dept` VALUES ('45', '7', '2');
INSERT INTO `sys_role_dept` VALUES ('46', '7', '6');
INSERT INTO `sys_role_dept` VALUES ('84', '10', '1');
INSERT INTO `sys_role_dept` VALUES ('85', '10', '10');
INSERT INTO `sys_role_dept` VALUES ('86', '10', '11');
INSERT INTO `sys_role_dept` VALUES ('87', '10', '12');
INSERT INTO `sys_role_dept` VALUES ('88', '10', '13');
INSERT INTO `sys_role_dept` VALUES ('106', '11', '1');
INSERT INTO `sys_role_dept` VALUES ('107', '11', '2');
INSERT INTO `sys_role_dept` VALUES ('108', '11', '8');
INSERT INTO `sys_role_dept` VALUES ('130', '12', '1');
INSERT INTO `sys_role_dept` VALUES ('131', '12', '3');
INSERT INTO `sys_role_dept` VALUES ('132', '12', '4');
INSERT INTO `sys_role_dept` VALUES ('133', '12', '5');
INSERT INTO `sys_role_dept` VALUES ('134', '13', '1');
INSERT INTO `sys_role_dept` VALUES ('135', '13', '2');
INSERT INTO `sys_role_dept` VALUES ('136', '13', '6');
INSERT INTO `sys_role_dept` VALUES ('137', '13', '7');
INSERT INTO `sys_role_dept` VALUES ('138', '13', '8');
INSERT INTO `sys_role_dept` VALUES ('139', '13', '9');
INSERT INTO `sys_role_dept` VALUES ('140', '13', '3');
INSERT INTO `sys_role_dept` VALUES ('141', '13', '4');
INSERT INTO `sys_role_dept` VALUES ('142', '13', '5');
INSERT INTO `sys_role_dept` VALUES ('143', '13', '10');
INSERT INTO `sys_role_dept` VALUES ('144', '13', '11');
INSERT INTO `sys_role_dept` VALUES ('145', '13', '12');
INSERT INTO `sys_role_dept` VALUES ('146', '13', '13');
INSERT INTO `sys_role_dept` VALUES ('147', '13', '21');
INSERT INTO `sys_role_dept` VALUES ('278', '16', '1');
INSERT INTO `sys_role_dept` VALUES ('279', '16', '2');
INSERT INTO `sys_role_dept` VALUES ('280', '16', '6');
INSERT INTO `sys_role_dept` VALUES ('281', '16', '24');
INSERT INTO `sys_role_dept` VALUES ('282', '16', '28');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=650 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('55', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('56', '3', '2');
INSERT INTO `sys_role_menu` VALUES ('57', '3', '15');
INSERT INTO `sys_role_menu` VALUES ('58', '3', '16');
INSERT INTO `sys_role_menu` VALUES ('59', '3', '17');
INSERT INTO `sys_role_menu` VALUES ('60', '3', '18');
INSERT INTO `sys_role_menu` VALUES ('61', '3', '3');
INSERT INTO `sys_role_menu` VALUES ('62', '3', '19');
INSERT INTO `sys_role_menu` VALUES ('63', '3', '20');
INSERT INTO `sys_role_menu` VALUES ('64', '3', '21');
INSERT INTO `sys_role_menu` VALUES ('65', '3', '22');
INSERT INTO `sys_role_menu` VALUES ('70', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('71', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('72', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('73', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('96', '4', '1');
INSERT INTO `sys_role_menu` VALUES ('97', '4', '2');
INSERT INTO `sys_role_menu` VALUES ('98', '4', '15');
INSERT INTO `sys_role_menu` VALUES ('99', '4', '16');
INSERT INTO `sys_role_menu` VALUES ('100', '4', '17');
INSERT INTO `sys_role_menu` VALUES ('101', '4', '18');
INSERT INTO `sys_role_menu` VALUES ('102', '4', '3');
INSERT INTO `sys_role_menu` VALUES ('103', '4', '19');
INSERT INTO `sys_role_menu` VALUES ('104', '4', '20');
INSERT INTO `sys_role_menu` VALUES ('105', '4', '21');
INSERT INTO `sys_role_menu` VALUES ('106', '4', '22');
INSERT INTO `sys_role_menu` VALUES ('107', '4', '4');
INSERT INTO `sys_role_menu` VALUES ('108', '4', '23');
INSERT INTO `sys_role_menu` VALUES ('109', '4', '24');
INSERT INTO `sys_role_menu` VALUES ('110', '4', '25');
INSERT INTO `sys_role_menu` VALUES ('111', '4', '26');
INSERT INTO `sys_role_menu` VALUES ('151', '7', '1');
INSERT INTO `sys_role_menu` VALUES ('152', '7', '2');
INSERT INTO `sys_role_menu` VALUES ('153', '7', '15');
INSERT INTO `sys_role_menu` VALUES ('154', '7', '16');
INSERT INTO `sys_role_menu` VALUES ('155', '7', '17');
INSERT INTO `sys_role_menu` VALUES ('156', '7', '18');
INSERT INTO `sys_role_menu` VALUES ('241', '10', '1');
INSERT INTO `sys_role_menu` VALUES ('242', '10', '2');
INSERT INTO `sys_role_menu` VALUES ('243', '10', '15');
INSERT INTO `sys_role_menu` VALUES ('244', '10', '16');
INSERT INTO `sys_role_menu` VALUES ('245', '10', '17');
INSERT INTO `sys_role_menu` VALUES ('246', '10', '18');
INSERT INTO `sys_role_menu` VALUES ('247', '10', '3');
INSERT INTO `sys_role_menu` VALUES ('248', '10', '19');
INSERT INTO `sys_role_menu` VALUES ('249', '10', '20');
INSERT INTO `sys_role_menu` VALUES ('250', '10', '21');
INSERT INTO `sys_role_menu` VALUES ('251', '10', '22');
INSERT INTO `sys_role_menu` VALUES ('252', '10', '4');
INSERT INTO `sys_role_menu` VALUES ('253', '10', '23');
INSERT INTO `sys_role_menu` VALUES ('254', '10', '24');
INSERT INTO `sys_role_menu` VALUES ('255', '10', '25');
INSERT INTO `sys_role_menu` VALUES ('256', '10', '26');
INSERT INTO `sys_role_menu` VALUES ('257', '10', '31');
INSERT INTO `sys_role_menu` VALUES ('258', '10', '32');
INSERT INTO `sys_role_menu` VALUES ('259', '10', '33');
INSERT INTO `sys_role_menu` VALUES ('260', '10', '34');
INSERT INTO `sys_role_menu` VALUES ('261', '10', '35');
INSERT INTO `sys_role_menu` VALUES ('302', '11', '41');
INSERT INTO `sys_role_menu` VALUES ('303', '11', '42');
INSERT INTO `sys_role_menu` VALUES ('304', '11', '44');
INSERT INTO `sys_role_menu` VALUES ('305', '11', '45');
INSERT INTO `sys_role_menu` VALUES ('306', '11', '46');
INSERT INTO `sys_role_menu` VALUES ('307', '11', '47');
INSERT INTO `sys_role_menu` VALUES ('338', '12', '1');
INSERT INTO `sys_role_menu` VALUES ('339', '12', '31');
INSERT INTO `sys_role_menu` VALUES ('340', '12', '32');
INSERT INTO `sys_role_menu` VALUES ('341', '12', '33');
INSERT INTO `sys_role_menu` VALUES ('342', '12', '34');
INSERT INTO `sys_role_menu` VALUES ('343', '12', '35');
INSERT INTO `sys_role_menu` VALUES ('344', '12', '41');
INSERT INTO `sys_role_menu` VALUES ('345', '12', '42');
INSERT INTO `sys_role_menu` VALUES ('346', '12', '44');
INSERT INTO `sys_role_menu` VALUES ('347', '12', '45');
INSERT INTO `sys_role_menu` VALUES ('348', '12', '46');
INSERT INTO `sys_role_menu` VALUES ('349', '12', '47');
INSERT INTO `sys_role_menu` VALUES ('616', '16', '1');
INSERT INTO `sys_role_menu` VALUES ('617', '16', '2');
INSERT INTO `sys_role_menu` VALUES ('618', '16', '15');
INSERT INTO `sys_role_menu` VALUES ('619', '16', '16');
INSERT INTO `sys_role_menu` VALUES ('620', '16', '17');
INSERT INTO `sys_role_menu` VALUES ('621', '16', '18');
INSERT INTO `sys_role_menu` VALUES ('622', '16', '60');
INSERT INTO `sys_role_menu` VALUES ('623', '16', '3');
INSERT INTO `sys_role_menu` VALUES ('624', '16', '19');
INSERT INTO `sys_role_menu` VALUES ('625', '16', '20');
INSERT INTO `sys_role_menu` VALUES ('626', '16', '21');
INSERT INTO `sys_role_menu` VALUES ('627', '16', '22');
INSERT INTO `sys_role_menu` VALUES ('628', '16', '4');
INSERT INTO `sys_role_menu` VALUES ('629', '16', '23');
INSERT INTO `sys_role_menu` VALUES ('630', '16', '24');
INSERT INTO `sys_role_menu` VALUES ('631', '16', '25');
INSERT INTO `sys_role_menu` VALUES ('632', '16', '26');
INSERT INTO `sys_role_menu` VALUES ('633', '16', '29');
INSERT INTO `sys_role_menu` VALUES ('634', '16', '48');
INSERT INTO `sys_role_menu` VALUES ('635', '16', '31');
INSERT INTO `sys_role_menu` VALUES ('636', '16', '32');
INSERT INTO `sys_role_menu` VALUES ('637', '16', '33');
INSERT INTO `sys_role_menu` VALUES ('638', '16', '34');
INSERT INTO `sys_role_menu` VALUES ('639', '16', '35');
INSERT INTO `sys_role_menu` VALUES ('640', '16', '36');
INSERT INTO `sys_role_menu` VALUES ('641', '16', '37');
INSERT INTO `sys_role_menu` VALUES ('642', '16', '38');
INSERT INTO `sys_role_menu` VALUES ('643', '16', '39');
INSERT INTO `sys_role_menu` VALUES ('644', '16', '40');
INSERT INTO `sys_role_menu` VALUES ('645', '16', '49');
INSERT INTO `sys_role_menu` VALUES ('646', '16', '50');
INSERT INTO `sys_role_menu` VALUES ('647', '16', '51');
INSERT INTO `sys_role_menu` VALUES ('648', '16', '52');
INSERT INTO `sys_role_menu` VALUES ('649', '16', '53');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '姓名',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '系统管理员', 'admin', 'fa252a606d5f001c9a7e1eb6cd480ddde516da8e45d2366850c0d7d29004c5f0', 'YzcmCZNvbXocrsz9dm8e', 'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES ('2', '杰克', 'jack', '2a661ad095f8ab66247d4b8f7b92bde6d4cf69482b7b3b9cc4303882d92cfce7', 'UsyQ2c0TDvycqqD03HQP', '123456@qq.com', '15627284602', '1', '5', '2018-12-12 10:50:58');
INSERT INTO `sys_user` VALUES ('6', '稳杰', 'debug', '85b51e719034cf3ec4e242cb8f171de65105eb10efa0412fb673be512ca23f48', 'q8WwxUma2bqO4voBMNK4', 'linsen@126.com', '15627284602', '1', '25', '2019-07-17 15:19:52');
INSERT INTO `sys_user` VALUES ('9', '李小龙', 'linsen', '5cd23204759430105f0ab2d01a994a14a505abfe9d756c4a18884789f7483c29', '84iyPk1aBzEDwlH4g8js', 'linsen@126.com', '15627274605', '1', '6', '2019-07-18 21:00:10');
INSERT INTO `sys_user` VALUES ('11', '关羽', 'guangyu', 'c175159f48b444b2c48715efb13c3d422d75abb005cfaa1776c814f808682c17', 'g5ydS987tTCJ6Secyqss', 'guangyu@debug.com', '124', '1', '9', '2019-07-19 09:57:55');
INSERT INTO `sys_user` VALUES ('12', '张飞', 'zhangfei', '4b5ddc6e5fffe07af4fc5fb7e7561310d61bab7eb37c89f7b48057f33ce72890', 'jN2Rzdz8qpaZY3HdVdCm', 'zhangfei@debug.com', '125', '1', '7', '2019-07-19 09:58:27');
INSERT INTO `sys_user` VALUES ('13', '张郃', 'zhaoyun', '2d988811d1088f6983a894775618ba82e0565d3d21f71fdbb216f2d296f8dc64', 'AwYxFwyChX2bFqFktSxv', 'zhaoyun@debug.com', '126', '1', '4', '2019-07-19 09:58:54');
INSERT INTO `sys_user` VALUES ('14', '黄忠', 'huangzhong', '5f0dd2dc73500b718cc5ae5e1a46fb4881668f7eac4c13cadca7ca1ac57bee38', 'nGPS44vxZ5ZNQ9pereei', 'huangzhong@debug.com', '127', '1', '11', '2019-07-19 09:59:20');
INSERT INTO `sys_user` VALUES ('15', '马超', 'machao', '77676b7918fbf9eaa3f8bb681bebd2b2ec647e7eb7ed178d7fedda45afed0215', 'K8FaZhJtpkmzpQQgPTOg', 'machao@debug.com', '126', '1', '12', '2019-07-19 09:59:41');
INSERT INTO `sys_user` VALUES ('16', '曹操', 'caocao', '1ab317129c08560be2caeb6ddbf2da6c2ca40c584173b4e500826064375eace9', 'UAGLOslypmTVg02N51Ec', 'caocao@debug.com', '127', '1', '13', '2019-07-19 10:01:12');
INSERT INTO `sys_user` VALUES ('22', '曹仁', 'caoren', 'a32c62dbd7004d791b17631e27f8a03255025ed5c9c828983493b958e42887f3', '2NUJeh6LGZqIJKiq8Mwy', 'caoren@debug.com', '120', '1', '4', '2019-07-19 23:16:49');
INSERT INTO `sys_user` VALUES ('24', '张郃', 'zhanghe', 'f56a8ee03a634f935d056f82d2e2e8bfef0d1425ad409b4e8f5589c34b284988', 'dXkxQX2oIsQZ82iFJanK', 'zhanghe2@126.com', '18316960821', '1', '9', '2019-07-21 22:13:06');
INSERT INTO `sys_user` VALUES ('25', '张三', 'zhangsan', '2c3b83fe2a46967e89394aa8fe5cf409e0b9a8b00b66c7f244a5c08aea1b3bc6', 'wh1azbzB5GOuCvui3h8M', 'zhangsan@debug.com', '15627284801', '1', '21', '2019-07-23 14:47:16');
INSERT INTO `sys_user` VALUES ('26', '数据视野人', 'dataDebug', '9542eb60a53b69045a18adf8902333154426be61b8f841e64281729ee0e5c6a9', '5GkVcRR0d5jEli2wSlV9', 'dataDebug@126.com', '12345678911', '1', '2', '2019-08-01 16:31:31');

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `post_id` bigint(20) NOT NULL COMMENT '岗位Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES ('1', '2', '1');
INSERT INTO `sys_user_post` VALUES ('2', '2', '2');
INSERT INTO `sys_user_post` VALUES ('13', '26', '10');
INSERT INTO `sys_user_post` VALUES ('42', '6', '4');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('5', '9', '1');
INSERT INTO `sys_user_role` VALUES ('6', '11', '1');
INSERT INTO `sys_user_role` VALUES ('7', '12', '1');
INSERT INTO `sys_user_role` VALUES ('10', '15', '1');
INSERT INTO `sys_user_role` VALUES ('20', '19', '1');
INSERT INTO `sys_user_role` VALUES ('21', '2', '1');
INSERT INTO `sys_user_role` VALUES ('27', '22', '1');
INSERT INTO `sys_user_role` VALUES ('38', '24', '10');
INSERT INTO `sys_user_role` VALUES ('41', '25', '7');
INSERT INTO `sys_user_role` VALUES ('43', '26', '12');
INSERT INTO `sys_user_role` VALUES ('68', '6', '16');
