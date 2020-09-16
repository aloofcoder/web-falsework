/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50724
Source Host           : 39.106.42.84:3306
Source Database       : system_manage

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2020-09-16 09:39:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(50) NOT NULL DEFAULT '' COMMENT '字典名称',
  `dict_mark` varchar(50) NOT NULL COMMENT '字典标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('2', '性别1', 'gender');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_id` int(11) NOT NULL,
  `label` varchar(50) NOT NULL,
  `value` varchar(50) NOT NULL,
  `sort` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', '1', '男', '1', '1');
INSERT INTO `sys_dict_item` VALUES ('2', '1', '女', '2', '1');
INSERT INTO `sys_dict_item` VALUES ('3', '1', '未知', '0', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '上级资源',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名',
  `menu_path` varchar(100) NOT NULL COMMENT '菜单路径',
  `menu_component` varchar(100) DEFAULT NULL,
  `menu_redirect` varchar(100) DEFAULT NULL COMMENT '重定向路径',
  `menu_mark` varchar(100) DEFAULT NULL COMMENT '权限标记',
  `menu_class` tinyint(4) NOT NULL COMMENT '菜单类型（1目录2菜单3按钮）',
  `menu_icon` varchar(50) DEFAULT NULL,
  `menu_desc` varchar(150) DEFAULT NULL COMMENT '菜单描述',
  `menu_sort` int(11) NOT NULL DEFAULT '1' COMMENT '显示排序号',
  `is_hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏（1隐藏0显示）',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1正常2禁用）',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `edit_by` varchar(50) NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '/sys', null, '/sys/users', null, '1', 'sys', '系统管理', '1', '0', '1', '7861948169', '7861948169', '2020-09-05 03:58:33', '2020-09-05 03:58:33');
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', 'users', 'sys/users/index', null, null, '2', 'user1', '用户管理', '1', '0', '1', '7861948169', '7861948169', '2020-09-05 02:26:25', '2020-09-05 02:26:25');
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', 'roles', 'sys/roles/role', '', null, '2', 'role', '角色管理', '2', '0', '1', '7861948169', '7861948169', '2020-09-05 02:31:22', '2020-09-05 02:31:22');
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', 'menus', 'sys/menus/menu', null, null, '2', 'menu', '菜单管理', '1', '0', '1', '7861948169', '7861948169', '2020-09-05 02:31:20', '2020-09-05 02:31:20');
INSERT INTO `sys_menu` VALUES ('11', '1', '部门管理', 'orgs', 'sys/orgs/org', '', '', '2', 'tree', '部门管理', '1', '0', '1', '1', '1', '2020-09-05 02:52:36', '2020-09-05 02:52:36');
INSERT INTO `sys_menu` VALUES ('21', '2', '添加用户', 'sys/users/add', null, null, 'sys:users:add', '3', 'table', '添加用户按钮', '2', '0', '1', '6633549909', '7861948169', '2020-09-15 08:32:40', '2020-09-15 08:32:40');
INSERT INTO `sys_menu` VALUES ('22', '2', '修改用户', 'sys/users/update', null, null, 'sys:users:update', '3', 'table', '修改用户', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 08:29:31', '2020-09-15 08:29:31');
INSERT INTO `sys_menu` VALUES ('23', '2', '删除用户', 'sys/users/delete', null, null, 'sys:users:delete', '3', 'table', '删除用户按钮', '3', '0', '1', '7861948169', '7861948169', '2020-09-15 08:31:05', '2020-09-15 08:31:05');
INSERT INTO `sys_menu` VALUES ('27', '2', '批量删除用户', 'sys/users/deletes', null, null, 'sys:users:deletes', '3', 'table', '批量删除用户按钮', '4', '0', '1', '7861948169', '7861948169', '2020-09-15 08:42:55', '2020-09-15 08:42:55');
INSERT INTO `sys_menu` VALUES ('28', '2', '角色分配', 'sys/users/role_assign', null, null, 'sys:users:role_assign', '3', 'table', '角色分配', '5', '0', '1', '7861948169', '7861948169', '2020-09-15 08:55:27', '2020-09-15 08:55:27');
INSERT INTO `sys_menu` VALUES ('29', '3', '添加角色', 'sys/roles/add', null, null, 'sys:roles:add', '3', 'table', '添加角色按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:13:37', '2020-09-15 09:13:37');
INSERT INTO `sys_menu` VALUES ('30', '3', '修改角色', 'sys/roles/update', null, null, 'sys:roles:update', '3', 'table', '修改角色按钮', '2', '0', '1', '7861948169', '7861948169', '2020-09-15 09:14:35', '2020-09-15 09:14:35');
INSERT INTO `sys_menu` VALUES ('31', '3', '删除角色', 'sys/roles/delete', null, null, 'sys:roles:delete', '3', 'table', '删除角色按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:15:28', '2020-09-15 09:15:28');
INSERT INTO `sys_menu` VALUES ('32', '3', '批量删除角色', 'sys/roles/deletes', null, null, 'sys:roles:deletes', '3', 'table', '批量删除角色按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:16:22', '2020-09-15 09:16:22');
INSERT INTO `sys_menu` VALUES ('33', '3', '角色授权', 'sys/roles/auth', null, null, 'sys:roles:auth', '3', 'table', '角色授权按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:17:07', '2020-09-15 09:17:07');
INSERT INTO `sys_menu` VALUES ('34', '4', '添加菜单', 'sys/menus/add', null, null, 'sys:menus:add', '3', 'table', '添加菜单按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:18:14', '2020-09-15 09:18:14');
INSERT INTO `sys_menu` VALUES ('35', '4', '修改菜单', 'sys/menus/update', null, null, 'sys:menus:update', '3', 'table', '修改菜单按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:18:47', '2020-09-15 09:18:47');
INSERT INTO `sys_menu` VALUES ('36', '4', '删除菜单', 'sys/menus/delete', null, null, 'sys:menus:delete', '3', 'table', '删除菜单按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:19:15', '2020-09-15 09:19:15');
INSERT INTO `sys_menu` VALUES ('37', '4', '批量删除菜单', 'sys/menus/deletes', null, null, 'sys:menus:deletes', '3', 'table', '批量删除菜单按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:28:09', '2020-09-15 10:28:09');
INSERT INTO `sys_menu` VALUES ('38', '11', '添加部门', 'sys/orgs/add', null, null, 'sys:orgs:add', '3', 'table', '添加部门按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:20:47', '2020-09-15 09:20:47');
INSERT INTO `sys_menu` VALUES ('39', '11', '修改部门', 'sys/orgs/update', null, null, 'sys:orgs:update', '3', 'table', '修改部门按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:21:47', '2020-09-15 09:21:47');
INSERT INTO `sys_menu` VALUES ('40', '11', '删除部门', 'sys/orgs/delete', null, null, 'sys:orgs:delete', '3', 'table', '删除部门', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:22:35', '2020-09-15 09:22:35');
INSERT INTO `sys_menu` VALUES ('41', '11', '批量删除部门', 'sys/orgs/deletes', null, null, 'sys:orgs:deletes', '3', 'table', '批量删除部门按钮', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:28:16', '2020-09-15 10:28:16');
INSERT INTO `sys_menu` VALUES ('42', '25', '添加栏目', 'article/type/add', null, null, 'article:type:add', '3', 'table', '添加栏目', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 09:53:33', '2020-09-15 09:53:33');
INSERT INTO `sys_menu` VALUES ('43', '25', '修改栏目', 'article/type/update', null, null, 'article:type:update', '3', 'table', '修改栏目', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:02:29', '2020-09-15 10:02:29');
INSERT INTO `sys_menu` VALUES ('44', '25', '删除栏目', 'article/type/delete', null, null, 'article:type:delete', '3', 'table', '删除栏目', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:04:56', '2020-09-15 10:04:56');
INSERT INTO `sys_menu` VALUES ('45', '25', '批量删除栏目', 'article/type/deletes', null, null, 'article:type:deletes', '3', 'table', '批量删除栏目', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:11:54', '2020-09-15 10:11:54');
INSERT INTO `sys_menu` VALUES ('46', '26', '添加文章', 'article/article/add', null, null, 'article:article:add', '3', 'table', '添加文章', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:14:21', '2020-09-15 10:14:21');
INSERT INTO `sys_menu` VALUES ('47', '26', '修改文章', 'article/article/update', null, null, 'article:article:update', '3', 'table', '修改文章', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:15:13', '2020-09-15 10:15:13');
INSERT INTO `sys_menu` VALUES ('48', '26', '删除文章', 'article/article/delete', null, null, 'article:article:delete', '3', 'table', '删除文章', '1', '0', '1', '7861948169', '7861948169', '2020-09-15 10:16:31', '2020-09-15 10:16:31');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NOT NULL DEFAULT '-1' COMMENT '父组织',
  `org_name` varchar(50) NOT NULL COMMENT '组织名',
  `org_desc` varchar(150) DEFAULT NULL COMMENT '组织描述',
  `org_sort` int(11) NOT NULL DEFAULT '1' COMMENT '组织排序',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1(正常）',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `edit_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='公司组织';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('2', '0', '办公室', '办公室', '2', '1', '7861948169', '7861948169', '2020-09-05 03:27:58', '2020-09-05 03:27:58');
INSERT INTO `sys_org` VALUES ('13', '0', '总经办', '总经办', '1', '1', '7861948169', '7861948169', '2020-09-14 02:11:58', '2020-09-14 02:11:58');
INSERT INTO `sys_org` VALUES ('14', '0', '软件部', '软件部', '3', '1', '7861948169', '7861948169', '2020-09-14 02:12:15', '2020-09-14 02:12:15');
INSERT INTO `sys_org` VALUES ('15', '0', '行政部', '行政部', '4', '1', '7861948169', '7861948169', '2020-09-14 02:12:47', '2020-09-14 02:12:47');

-- ----------------------------
-- Table structure for sys_org_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_user`;
CREATE TABLE `sys_org_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) NOT NULL,
  `user_num` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_org_user
-- ----------------------------
INSERT INTO `sys_org_user` VALUES ('45', '13', '7861948169');
INSERT INTO `sys_org_user` VALUES ('46', '14', '6633549909');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL COMMENT '角色名',
  `role_mark` varchar(50) NOT NULL COMMENT '角色标记',
  `role_desc` varchar(150) DEFAULT NULL COMMENT '角色描述',
  `role_sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序号',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态(1正常2禁用)',
  `create_by` varchar(50) NOT NULL,
  `edit_by` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `unique_role_name` (`role_name`),
  UNIQUE KEY `unique_role_mark` (`role_mark`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '系统维护及管理。。。', '1', '1', '7861948169', '7861948169', '2020-06-16 09:33:12', '2020-06-16 09:33:12');
INSERT INTO `sys_role` VALUES ('19', '开发工程师', 'coder', '软件开发工程师', '2', '1', '7861948169', '7861948169', '2020-09-15 08:03:38', '2020-09-15 08:03:38');
INSERT INTO `sys_role` VALUES ('20', '测试工程师', 'tester', '软件测试工程师', '3', '1', '7861948169', '7861948169', '2020-09-15 08:12:10', '2020-09-15 08:12:10');
INSERT INTO `sys_role` VALUES ('21', '产品工程师', 'producter', '软件产品工程师', '4', '1', '7861948169', '7861948169', '2020-09-15 08:12:41', '2020-09-15 08:12:41');
INSERT INTO `sys_role` VALUES ('22', '运维工程师', 'opman', '运维工程师', '7', '1', '7861948169', '7861948169', '2020-09-15 09:41:05', '2020-09-15 09:41:05');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=695 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('620', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('621', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('622', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('623', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('624', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('625', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('626', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('627', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('628', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('629', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('630', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('631', '1', '32');
INSERT INTO `sys_role_menu` VALUES ('632', '1', '33');
INSERT INTO `sys_role_menu` VALUES ('633', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('634', '1', '34');
INSERT INTO `sys_role_menu` VALUES ('635', '1', '35');
INSERT INTO `sys_role_menu` VALUES ('636', '1', '36');
INSERT INTO `sys_role_menu` VALUES ('637', '1', '37');
INSERT INTO `sys_role_menu` VALUES ('638', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('639', '1', '38');
INSERT INTO `sys_role_menu` VALUES ('640', '1', '39');
INSERT INTO `sys_role_menu` VALUES ('641', '1', '40');
INSERT INTO `sys_role_menu` VALUES ('642', '1', '41');
INSERT INTO `sys_role_menu` VALUES ('643', '22', '1');
INSERT INTO `sys_role_menu` VALUES ('644', '22', '2');
INSERT INTO `sys_role_menu` VALUES ('645', '22', '21');
INSERT INTO `sys_role_menu` VALUES ('646', '22', '22');
INSERT INTO `sys_role_menu` VALUES ('647', '22', '28');
INSERT INTO `sys_role_menu` VALUES ('672', '19', '1');
INSERT INTO `sys_role_menu` VALUES ('673', '19', '2');
INSERT INTO `sys_role_menu` VALUES ('674', '19', '21');
INSERT INTO `sys_role_menu` VALUES ('675', '19', '22');
INSERT INTO `sys_role_menu` VALUES ('676', '19', '23');
INSERT INTO `sys_role_menu` VALUES ('677', '19', '27');
INSERT INTO `sys_role_menu` VALUES ('678', '19', '28');
INSERT INTO `sys_role_menu` VALUES ('679', '19', '3');
INSERT INTO `sys_role_menu` VALUES ('680', '19', '29');
INSERT INTO `sys_role_menu` VALUES ('681', '19', '30');
INSERT INTO `sys_role_menu` VALUES ('682', '19', '31');
INSERT INTO `sys_role_menu` VALUES ('683', '19', '32');
INSERT INTO `sys_role_menu` VALUES ('684', '19', '33');
INSERT INTO `sys_role_menu` VALUES ('685', '19', '4');
INSERT INTO `sys_role_menu` VALUES ('686', '19', '34');
INSERT INTO `sys_role_menu` VALUES ('687', '19', '35');
INSERT INTO `sys_role_menu` VALUES ('688', '19', '36');
INSERT INTO `sys_role_menu` VALUES ('689', '19', '37');
INSERT INTO `sys_role_menu` VALUES ('690', '19', '11');
INSERT INTO `sys_role_menu` VALUES ('691', '19', '38');
INSERT INTO `sys_role_menu` VALUES ('692', '19', '39');
INSERT INTO `sys_role_menu` VALUES ('693', '19', '40');
INSERT INTO `sys_role_menu` VALUES ('694', '19', '41');

-- ----------------------------
-- Table structure for sys_role_source
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_source`;
CREATE TABLE `sys_role_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `source_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_source
-- ----------------------------
INSERT INTO `sys_role_source` VALUES ('167', '1', '1');
INSERT INTO `sys_role_source` VALUES ('168', '1', '2');
INSERT INTO `sys_role_source` VALUES ('169', '1', '3');
INSERT INTO `sys_role_source` VALUES ('170', '1', '4');
INSERT INTO `sys_role_source` VALUES ('171', '1', '5');
INSERT INTO `sys_role_source` VALUES ('172', '1', '6');
INSERT INTO `sys_role_source` VALUES ('173', '1', '7');
INSERT INTO `sys_role_source` VALUES ('174', '1', '8');
INSERT INTO `sys_role_source` VALUES ('175', '1', '9');
INSERT INTO `sys_role_source` VALUES ('176', '1', '10');
INSERT INTO `sys_role_source` VALUES ('177', '1', '11');
INSERT INTO `sys_role_source` VALUES ('178', '1', '12');
INSERT INTO `sys_role_source` VALUES ('179', '1', '13');
INSERT INTO `sys_role_source` VALUES ('180', '1', '14');
INSERT INTO `sys_role_source` VALUES ('181', '1', '15');
INSERT INTO `sys_role_source` VALUES ('182', '1', '16');
INSERT INTO `sys_role_source` VALUES ('183', '1', '17');
INSERT INTO `sys_role_source` VALUES ('184', '1', '18');
INSERT INTO `sys_role_source` VALUES ('185', '1', '19');
INSERT INTO `sys_role_source` VALUES ('186', '1', '20');
INSERT INTO `sys_role_source` VALUES ('187', '1', '21');
INSERT INTO `sys_role_source` VALUES ('188', '1', '22');
INSERT INTO `sys_role_source` VALUES ('189', '1', '23');
INSERT INTO `sys_role_source` VALUES ('190', '1', '24');
INSERT INTO `sys_role_source` VALUES ('191', '1', '25');
INSERT INTO `sys_role_source` VALUES ('192', '1', '26');
INSERT INTO `sys_role_source` VALUES ('193', '1', '27');
INSERT INTO `sys_role_source` VALUES ('194', '1', '28');
INSERT INTO `sys_role_source` VALUES ('195', '1', '29');
INSERT INTO `sys_role_source` VALUES ('196', '1', '30');
INSERT INTO `sys_role_source` VALUES ('197', '1', '31');
INSERT INTO `sys_role_source` VALUES ('198', '1', '32');
INSERT INTO `sys_role_source` VALUES ('199', '1', '33');
INSERT INTO `sys_role_source` VALUES ('200', '1', '34');
INSERT INTO `sys_role_source` VALUES ('201', '1', '35');
INSERT INTO `sys_role_source` VALUES ('202', '1', '36');
INSERT INTO `sys_role_source` VALUES ('203', '1', '37');
INSERT INTO `sys_role_source` VALUES ('204', '1', '38');
INSERT INTO `sys_role_source` VALUES ('205', '1', '39');
INSERT INTO `sys_role_source` VALUES ('206', '1', '40');
INSERT INTO `sys_role_source` VALUES ('207', '1', '41');
INSERT INTO `sys_role_source` VALUES ('208', '1', '42');
INSERT INTO `sys_role_source` VALUES ('209', '1', '43');

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统全局设置表';

-- ----------------------------
-- Records of sys_setting
-- ----------------------------

-- ----------------------------
-- Table structure for sys_source
-- ----------------------------
DROP TABLE IF EXISTS `sys_source`;
CREATE TABLE `sys_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `source_name` varchar(50) NOT NULL COMMENT '资源名称',
  `source_module` varchar(50) NOT NULL COMMENT '所属模块',
  `method` enum('GET','POST','PUT','DELETE') NOT NULL COMMENT '资源请求方法',
  `source_url` varchar(255) NOT NULL COMMENT '资源url',
  `source_code` varchar(50) NOT NULL COMMENT '系统生成的资源唯一号',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '(1正常2禁用)',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `edit_by` varchar(50) NOT NULL COMMENT '修改人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `edit_tme` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_source
-- ----------------------------
INSERT INTO `sys_source` VALUES ('1', '分页查询用户列表', '用户管理', 'GET', '/users/page', 'a1212b8f7de14230e5ce804611b61070', '1', '7861948169', '7861948169', '2020-06-17 15:55:17', '2020-06-17 15:55:17');
INSERT INTO `sys_source` VALUES ('2', '分页查询角色列表', '角色管理', 'GET', '/roles/page', '8c6363bd50478665c928473fce40662a', '1', '7861948169', '7861948169', '2020-06-17 16:23:01', '2020-06-17 16:23:01');
INSERT INTO `sys_source` VALUES ('3', '修改数据字典', '数据字典', 'PUT', '/dicts/*', 'f47e0e30badc281452f2ecbeffdda111', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('4', '删除数据字典', '数据字典', 'DELETE', '/dicts/*', 'a6536d32edfef3612bcb75cf048e08b1', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('5', '添加数据字典', '数据字典', 'POST', '/dicts', 'a7e82ff8fdb6084537357d103342a75c', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('6', '查询数据字典详情', '数据字典', 'GET', '/dicts/*', 'b3658728c6f8d28cfc941f98709dbebb', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('7', '分页查询数据字典列表', '数据字典', 'GET', '/dicts/page', '3dd81169e703e95553ea56e0adcd51d3', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('8', '登录授权', '登录', 'POST', '/login', '7ad67d9d9ac67620b1b2375ebac21900', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('9', '登出授权', '登录', 'POST', '/logout', '38dcec65c45719237820f81ee509735a', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('10', '删除菜单', '菜单管理', 'DELETE', '/menus/*', 'd253bb83bbb05b969a02ae1b3ea209d5', '1', '7861948169', '7861948169', '2020-06-20 00:24:39', '2020-06-20 00:24:39');
INSERT INTO `sys_source` VALUES ('11', '修改菜单', '菜单管理', 'PUT', '/menus/*', '5f41dbb11c79528b82c9ca30d9c1a4be', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('12', '查询菜单详情', '菜单管理', 'GET', '/menus/*', '9de505ca73070e1aedaa7d2ebfaefd75', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('13', '添加菜单', '菜单管理', 'POST', '/menus', '39150f8b18c98ed3f4fc7887bc2bb05a', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('14', '查询全部菜单列表', '菜单管理', 'GET', '/menus', 'c43c1c415d76268b67da2f9dee30483e', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('15', '添加组织', '组织管理', 'POST', '/orgs', 'e6d4eb7b821e27b60235aea52ad26a39', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('16', '删除组织', '组织管理', 'DELETE', '/orgs/*', 'e9f9006211d1abd75cc0b2704ccfa0be', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('17', '查询全部组织列表', '组织管理', 'GET', '/orgs', 'a12a26ada724b15118d12955d5512aa1', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('18', '修改组织', '组织管理', 'PUT', '/orgs/*', '08f1b118051c9dc597300e8c5bdacfc5', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('19', '查询组织详情', '组织管理', 'GET', '/orgs/*', 'b43a44ce61f2486e8fb33c62da75934b', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('20', '修改角色', '角色管理', 'PUT', '/roles/*', '03aef0f14189b97b373a431623a20b4d', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('21', '删除角色', '角色管理', 'DELETE', '/roles/*', 'a9ef7a25001209f614e3b5e6df8ea5cf', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('22', '添加角色', '角色管理', 'POST', '/roles', '46320c942a38d74362658bb4aaf940c6', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('23', '查询全部角色列表', '角色管理', 'GET', '/roles', '12752b7c4b45f15e93a48e836104ccc8', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('24', '查询角色详情', '角色管理', 'GET', '/roles/*', '953dff091941b3fe08e1d5900bbd7412', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('25', '查询未授权资源列表', '资源管理', 'GET', '/sources/unauth', 'ff378ba2fb0ab0d8d4e1656e496a56bf', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('26', '分页查询资源列表', '资源管理', 'GET', '/sources/page', 'e412b6ed8b8f261c757191c5626bfad1', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('27', '添加资源', '资源管理', 'POST', '/sources', '31115f13b64dc0d304b28ae7f8485fc5', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('28', '删除用户', '用户管理', 'DELETE', '/users/*', '2fb0681c05a096ca8b1e34e7bfe089de', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('29', '添加用户', '用户管理', 'POST', '/users', '00df7f6d51c30ee64fbd0a269f87c74b', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('30', '查询用户详情', '用户管理', 'GET', '/users/*', 'c710e0fd130a2f597acd8e7e1e6f5059', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('31', '修改用户', '用户管理', 'PUT', '/users/*', '143b9299ee595e4c4e847c4545b08465', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('32', '查询全部用户列表', '用户管理', 'GET', '/users', 'ebb39faee27ca6391f932c0387e5240e', '1', '7861948169', '7861948169', '2020-06-20 00:24:40', '2020-06-20 00:24:40');
INSERT INTO `sys_source` VALUES ('33', '查看角色已授权菜单信息', '角色管理', 'GET', '/roles/source/*', '55dc0dbc1c2c246bfe2ba31382f6ca5d', '1', '7861948169', '7861948169', '2020-06-24 07:32:26', '2020-06-24 07:32:26');
INSERT INTO `sys_source` VALUES ('34', '查看角色已授权菜单信息', '角色管理', 'GET', '/roles/menu/*', 'a151dd4e649b84ad2f46c06b09ab20c3', '1', '7861948169', '7861948169', '2020-06-24 09:24:36', '2020-06-24 09:24:36');
INSERT INTO `sys_source` VALUES ('35', '角色分配菜单', '角色管理', 'POST', '/roles/menu/assign', '38201b1ae1bac5431fdfac5346834f35', '1', '7861948169', '7861948169', '2020-06-24 22:51:58', '2020-06-24 22:51:58');
INSERT INTO `sys_source` VALUES ('36', '角色分配API资源', '角色管理', 'POST', '/roles/source/assign', 'c681c216660f47c1443495686ba6bd7f', '1', '7861948169', '7861948169', '2020-06-24 22:51:58', '2020-06-24 22:51:58');
INSERT INTO `sys_source` VALUES ('37', '查询树状菜单列表', '菜单管理', 'GET', '/menus/tree', 'f142cc21c31582bdc742dfbb0748d520', '1', '7861948169', '7861948169', '2020-07-04 15:43:57', '2020-07-04 15:43:57');
INSERT INTO `sys_source` VALUES ('38', '查询树状组织', '组织管理', 'GET', '/orgs/tree', '982f1fe6290e84c7b3716fbb881bd7d2', '1', '7861948169', '7861948169', '2020-07-04 15:44:37', '2020-07-04 15:44:37');
INSERT INTO `sys_source` VALUES ('39', '删除资源', '资源管理', 'DELETE', '/sources/*', '105e218600123e7b3b41dbbec345df29', '1', '7861948169', '7861948169', '2020-07-04 15:47:54', '2020-07-04 15:47:54');
INSERT INTO `sys_source` VALUES ('40', '角色分配', '角色管理', 'POST', '/users/role/assign', '40432c63c9845552203d7e4bc7e6f1ab', '1', '7861948169', '7861948169', '2020-07-04 15:48:27', '2020-07-04 15:48:27');
INSERT INTO `sys_source` VALUES ('41', '获取当前登录用户', '用户管理', 'GET', '/users/info', '22659cf81b3c4a437804d6b38be7eddd', '1', '7861948169', '7861948169', '2020-07-04 15:48:41', '2020-07-04 15:48:41');
INSERT INTO `sys_source` VALUES ('42', '修改资源', '资源管理', 'PUT', '/sources/*', 'a2f0bfad72f55e57982ee152f5518ad8', '1', '7861948169', '7861948169', '2020-07-04 15:53:05', '2020-07-04 15:53:05');
INSERT INTO `sys_source` VALUES ('43', '查询用户角色', '用户管理', 'GET', '/users/role/*', '2473ef38a578b01f63cd592451fb9a6a', '1', '7861948169', '7861948169', '2020-07-24 00:24:51', '2020-07-24 00:24:51');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_num` varchar(30) NOT NULL COMMENT '系统编号',
  `user_name` varchar(50) NOT NULL COMMENT '姓名',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `login_pwd` varchar(255) NOT NULL COMMENT '登录密码',
  `phone_num` varchar(11) DEFAULT NULL COMMENT '手机号',
  `user_email` varchar(150) DEFAULT NULL COMMENT '邮箱',
  `birth_date` date DEFAULT NULL COMMENT '生日',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1正常）',
  `create_by` varchar(50) NOT NULL,
  `edit_by` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_num` (`user_num`),
  UNIQUE KEY `unique_login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('14', '7861948169', '管理员', 'admin', '$2a$10$EDtWLCL7lvYsHa6HQtGHb.nkULljWm3NVwWf4rSG4jNcRew7GoCTS', '18149197030', 'hanl1946@163.com', '2020-03-30', '1', '7861948169', '7861948169', '2020-09-13 07:33:17', '2020-09-13 07:33:17');
INSERT INTO `sys_user` VALUES ('26', '6633549909', '韩乐', 'hanle', '$2a$10$og0ccNyytuWmo.mXInjPpeRqknYebfN9mAOZIDLiuGw.mP5GtlUwm', '18149197030', null, '2020-09-15', '1', '7861948169', '7861948169', '2020-09-15 08:13:45', '2020-09-15 08:13:45');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `user_num` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_user` (`role_id`,`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('42', '1', '7861948169');
INSERT INTO `sys_user_role` VALUES ('43', '19', '6633549909');
