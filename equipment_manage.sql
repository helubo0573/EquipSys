/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : equipment_manage

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-08-24 00:13:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `employee_info`
-- ----------------------------
DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE `employee_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `emp_name` varchar(20) DEFAULT NULL COMMENT '工员名称',
  `emp_sex` tinyint(1) DEFAULT '0' COMMENT '别性',
  `emp_dept` int(11) DEFAULT '0' COMMENT '部门id',
  `emp_post` int(11) DEFAULT '0' COMMENT '岗位id',
  `emp_mobil` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `emp_date` date DEFAULT '1700-01-01' COMMENT '档建时间',
  `emp_status` tinyint(2) DEFAULT '0' COMMENT '岗位状态： -1:离职',
  `emp_remarks` varchar(100) DEFAULT NULL COMMENT '注备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_info
-- ----------------------------
INSERT INTO `employee_info` VALUES ('1', '系统管理员', '0', '1', '20', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('65', '胡美玲', '1', '5', '24', '13575272417', '2020-06-17', '0', '');
INSERT INTO `employee_info` VALUES ('66', '黄斌', '0', '5', '24', '15197490773', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('67', '黎飞', '0', '33', '36', '13723826414', '2020-06-20', '0', '');
INSERT INTO `employee_info` VALUES ('68', '李娟', '1', '5', '24', '18373465022', '2020-06-19', '0', '');
INSERT INTO `employee_info` VALUES ('70', '梁建', '0', '4', '24', '18216075605', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('71', '梁瑞勇', '0', '5', '24', '13875653939', '2020-06-20', '0', 'asdasdasd');
INSERT INTO `employee_info` VALUES ('72', '雷雅娇', '1', '6', '25', '15096050911', '2020-08-23', '0', '');
INSERT INTO `employee_info` VALUES ('73', '汪琴', '1', '13', '32', '13397676968', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('74', '谢鹏', '0', '1', '20', '17749657179', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('75', '袁平金', '1', '4', '32', '18274749618', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('76', '陈红梅', '1', '1', '20', '13974710436', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('77', '邓良维', '1', '7', '26', '17873509482', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('78', '黄玉丽', '1', '7', '26', '15273408212', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('79', '梁桃', '1', '4', '24', '13786405835', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('80', '梁智芳', '1', '7', '26', '15873482088', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('81', '潘舒怡', '1', '7', '26', '15526451557', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('82', '谭园园', '1', '7', '26', '18373431450', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('83', '杨元元', '1', '4', '26', '15073467909', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('84', '邓利芳', '1', '4', '32', '15607477295', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('85', '梁洁', '1', '2', '21', '13627340533', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('86', '梁志辉', '0', '2', '21', '13873485277', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('87', '梁智军', '0', '2', '21', '15116857888', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('88', '石俊阳', '0', '2', '21', '13973410101', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('89', '肖金伟', '0', '2', '21', '15211352568', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('90', '资元元', '0', '2', '21', '18274710212', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('91', '何利', '0', '3', '22', '18821826813', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('92', '刘虔', '0', '3', '22', '13667440315', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('93', '莫运华', '0', '3', '22', '13873411552', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('94', '徐陈', '0', '3', '22', '18674765158', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('95', '胡浩', '0', '6', '25', '18627504149', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('96', '刘小勇', '0', '6', '25', '18216004121', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('97', '王小明', '0', '6', '25', '15116837066', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('98', '谢洋平', '0', '6', '25', '18273402286', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('99', '朱洪忠', '0', '6', '31', '13875742338', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('100', '李旭', '1', '4', '32', '18187152056', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('101', '梁生武', '0', '4', '30', '15886452706', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('102', '石水萍', '0', '4', '30', '15773456499', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('103', '王爱军', '0', '4', '30', '18908449818', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('104', '王明', '0', '4', '30', '13929209802', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('105', '石仁章', '0', '8', '27', '13973427112', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('106', '陈总', '0', '10', '29', '18774239316', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('107', '石仁才', '0', '3', '22', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('108', '梁金凤', '1', '9', '28', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('110', '梁成连', '0', '4', '30', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('111', '梁成满', '0', '4', '30', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('112', '梁生文', '0', '4', '30', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('113', '伍优梅', '1', '4', '30', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('114', '朱延祥', '0', '6', '31', '13789389540', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('115', '梁伟', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('116', '贺正元', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('117', '曹小兵', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('118', '将传青', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('119', '王四成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('120', '刘冬文', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('121', '李运成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('122', '将有成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('123', '李主检', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('124', '陆从乃', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('125', '贺红菊', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('126', '谢延黄', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('127', '王小军', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('128', '杨纯义', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('129', '陈列斌', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('130', '刘清芽', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('131', '李小元', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('132', '曹传金', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('133', '王和成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('134', '王东', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('135', '刘功必', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('136', '王满生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('137', '王小付', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('138', '陆必乃', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('139', '王次保', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('140', '张六莲', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('141', '王文学', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('142', '曹梅生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('143', '梁育生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('144', '李选文', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('145', '陆爱乃', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('146', '曹利生', '0', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('147', '李佐仪', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('148', '张为满', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('149', '张定军', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('150', '张为荣', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('151', '胡烈修', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('152', '刘余金', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('153', '梁满桂', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('154', '梁运古', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('155', '张志云', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('156', '陈列康', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('157', '王莲芽', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('158', '陈新全', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('159', '张维香', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('160', '刘删莲', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('161', '黎家文', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('162', '刘显礼', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('163', '刘洪湘', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('164', '黄美琳', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('165', '资言生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('166', '王莲星', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('167', '陆什乃', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('168', '李华四', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('169', '朱文生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('170', '曾春成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('171', '郭义发', '0', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('172', '贺佐求', '0', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('173', '陆国乃', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('174', '刘运华', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('175', '梁中清', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('176', '曾祥春', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('177', '黄崇南', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('178', '将华香', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('179', '曾弘华', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('180', '伍桂花', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('181', '谢小明', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('182', '张冬毛', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('183', '刘波', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('184', '王晓林', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('185', '刘延满', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('186', '刘春生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('187', '张英华', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('188', '刘功恒', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('189', '彭满芽', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('190', '贺才发', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('191', '梁成凤', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('192', '张小民', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('193', '龙学文', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('194', '陆仁乃', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('195', '刘勇', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('196', '李海峰', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('197', '曹飘', '0', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('198', '周青生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('199', '刘秋芽', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('200', '李桂华', '1', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('201', '梁成华', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('202', '将子古', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('203', '文武', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('204', '胡辉辉', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('205', '陈四清', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('206', '刘梦珍', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('207', '李香英', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('208', '谢飞凤', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('209', '文孝成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('210', '李小林', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('211', '王洪武', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('212', '刘功进', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('213', '黄阳军', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('214', '刘世发', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('215', '伍永芽', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('216', '罗光族', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('217', '刘顺成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('218', '刘仁九', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('219', '谭运兰', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('220', '杨清平', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('221', '梁稳生', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('222', '李又专', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('223', '伍意英', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('224', '黄爱兰', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('225', '戴鸾英', '1', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('226', '刘元呈', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('227', '郭运成', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('228', '李万根', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('229', '钟国鹏', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('230', '资正延', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('231', '刘新冬', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('232', '王小改', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('233', '熊斌', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('234', '卫小华', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('235', '朱猛钢', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('236', '刘纪军', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('237', '赵菊莲', '1', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('238', '李运莲', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('239', '贺桂生', '0', '1', '20', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('240', '张满元', '0', '1', '20', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('241', '刘峰', '0', '1', '20', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('242', '谷鑫', '0', '1', '20', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('243', '李云华', '1', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('244', '刘新兰', '1', '1', '20', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('246', '石宏娇', '1', '12', '23', '15096026704', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('247', '廖枫华', '1', '7', '26', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('249', '刘天晴', '0', '4', '22', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('252', '贺莹', '1', '13', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('253', '刘小路', '0', '3', '22', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('254', '王汉军', '0', '3', '22', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('255', '谢三林', '1', '1', '20', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('256', '廖芳', '1', '1', '20', '18274758101', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('257', '曾国香', '1', '2', '21', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('258', '曹伍智', '0', '2', '21', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('259', '陈满云', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('260', '石宇', '0', '4', '24', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('261', '何路波', '0', '5', '24', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('262', '郑兴', '0', '4', '35', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('263', '段春亮', '0', '4', '32', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('264', '唐巧粤', '0', '13', '35', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('265', '邓国庆', '0', '4', '23', '', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('266', '谢红光', '0', '7', '26', '13434267756', '0000-00-00', '0', '');
INSERT INTO `employee_info` VALUES ('267', '张红兰', '1', '4', '23', '', '0000-00-00', '-1', '');
INSERT INTO `employee_info` VALUES ('273', '张三', '0', '10', '29', '121231', '2020-06-20', '-1', '');
INSERT INTO `employee_info` VALUES ('274', '张三', '0', '10', '29', '121231', '2020-06-20', '-1', '');
INSERT INTO `employee_info` VALUES ('275', '阿萨德', '0', '4', '23', '212123', '2020-06-20', '-1', '');

-- ----------------------------
-- Table structure for `equip_info`
-- ----------------------------
DROP TABLE IF EXISTS `equip_info`;
CREATE TABLE `equip_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `equip_name` varchar(30) NOT NULL COMMENT '设备名称',
  `equip_code` varchar(30) DEFAULT NULL COMMENT '备设编号',
  `parent_id` int(11) DEFAULT NULL COMMENT '所属设备id',
  `equip_level` tinyint(4) DEFAULT NULL,
  `enable_date` date DEFAULT '1700-01-01' COMMENT '启用日期',
  `attr_dept` int(11) DEFAULT NULL COMMENT '所属部门id',
  `supplier` varchar(30) DEFAULT NULL COMMENT '供应商',
  `location` varchar(50) DEFAULT NULL COMMENT '在所位置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='设备基础信息表';

-- ----------------------------
-- Records of equip_info
-- ----------------------------
INSERT INTO `equip_info` VALUES ('50', '富氧侧吹炉系统', '101', '0', '0', '2020-06-02', '3', null, '二平台');
INSERT INTO `equip_info` VALUES ('51', '烟化炉系统', '102', '0', '0', '2020-06-06', '3', null, null);
INSERT INTO `equip_info` VALUES ('52', '制砖机组', '1021', '50', '1', '2020-06-01', '11', null, null);
INSERT INTO `equip_info` VALUES ('53', '输送系统', '10103', '52', '2', '2020-06-16', '11', null, null);
INSERT INTO `equip_info` VALUES ('54', '制砖机', '1010101', '52', '2', '2020-06-01', '11', null, '一平台');
INSERT INTO `equip_info` VALUES ('55', '鼓风机', '65464', '51', '2', '2020-06-22', '1', null, null);

-- ----------------------------
-- Table structure for `equip_mp`
-- ----------------------------
DROP TABLE IF EXISTS `equip_mp`;
CREATE TABLE `equip_mp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equip_id` int(11) NOT NULL COMMENT '备设id',
  `mp_id` int(11) NOT NULL COMMENT '修维人员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='设备维修人员映射表';

-- ----------------------------
-- Records of equip_mp
-- ----------------------------
INSERT INTO `equip_mp` VALUES ('67', '50', '118');
INSERT INTO `equip_mp` VALUES ('68', '50', '119');
INSERT INTO `equip_mp` VALUES ('69', '50', '120');
INSERT INTO `equip_mp` VALUES ('71', '52', '122');
INSERT INTO `equip_mp` VALUES ('75', '54', '102');
INSERT INTO `equip_mp` VALUES ('76', '54', '104');
INSERT INTO `equip_mp` VALUES ('77', '53', '102');

-- ----------------------------
-- Table structure for `equip_op`
-- ----------------------------
DROP TABLE IF EXISTS `equip_op`;
CREATE TABLE `equip_op` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equip_id` int(11) NOT NULL COMMENT '备设id',
  `op_id` int(11) NOT NULL COMMENT '操作员id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COMMENT='设备操作员映射表';

-- ----------------------------
-- Records of equip_op
-- ----------------------------
INSERT INTO `equip_op` VALUES ('74', '50', '103');
INSERT INTO `equip_op` VALUES ('76', '52', '116');
INSERT INTO `equip_op` VALUES ('77', '52', '117');
INSERT INTO `equip_op` VALUES ('80', '54', '101');
INSERT INTO `equip_op` VALUES ('81', '54', '102');

-- ----------------------------
-- Table structure for `org_dept`
-- ----------------------------
DROP TABLE IF EXISTS `org_dept`;
CREATE TABLE `org_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '增自ID',
  `dept_name` varchar(20) DEFAULT NULL COMMENT '门部名称',
  `parent_id` int(11) DEFAULT '0',
  `dept_type` tinyint(4) DEFAULT '0' COMMENT '0:部门 1:岗位',
  `dept_remarks` varchar(100) DEFAULT NULL COMMENT '注备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_dept
-- ----------------------------
INSERT INTO `org_dept` VALUES ('1', '综合部', '0', '0', '');
INSERT INTO `org_dept` VALUES ('2', '业务部', '0', '0', '');
INSERT INTO `org_dept` VALUES ('3', '技术部', '0', '0', '');
INSERT INTO `org_dept` VALUES ('4', '生产工人', '0', '0', null);
INSERT INTO `org_dept` VALUES ('5', '化验室', '0', '0', '');
INSERT INTO `org_dept` VALUES ('6', '安环部', '0', '0', '');
INSERT INTO `org_dept` VALUES ('7', '财务部', '0', '0', '');
INSERT INTO `org_dept` VALUES ('8', '董事长', '0', '0', '');
INSERT INTO `org_dept` VALUES ('9', '总经理', '0', '0', '');
INSERT INTO `org_dept` VALUES ('10', '顾问', '0', '0', '1111');
INSERT INTO `org_dept` VALUES ('11', '生产管理', '0', '0', '');
INSERT INTO `org_dept` VALUES ('12', '仓储部', '0', '0', '');
INSERT INTO `org_dept` VALUES ('13', '计划部', '0', '0', '');
INSERT INTO `org_dept` VALUES ('20', '职员', '1', '1', null);
INSERT INTO `org_dept` VALUES ('21', '职员', '2', '1', null);
INSERT INTO `org_dept` VALUES ('22', '技术骨干', '3', '1', null);
INSERT INTO `org_dept` VALUES ('23', '普工', '4', '1', null);
INSERT INTO `org_dept` VALUES ('24', '化验员', '5', '1', null);
INSERT INTO `org_dept` VALUES ('25', '职员', '6', '1', null);
INSERT INTO `org_dept` VALUES ('26', '财务', '7', '1', null);
INSERT INTO `org_dept` VALUES ('27', '董事长', '8', '1', null);
INSERT INTO `org_dept` VALUES ('28', '总经理', '9', '1', null);
INSERT INTO `org_dept` VALUES ('29', '顾问1', '10', '1', '111211177');
INSERT INTO `org_dept` VALUES ('30', '生产管理', '11', '1', null);
INSERT INTO `org_dept` VALUES ('31', '仓管员', '12', '1', null);
INSERT INTO `org_dept` VALUES ('32', '计划专员', '13', '1', null);
INSERT INTO `org_dept` VALUES ('33', '品管部', '0', '0', null);
INSERT INTO `org_dept` VALUES ('34', '制样室', '33', '0', null);
INSERT INTO `org_dept` VALUES ('35', '采样员', '34', '1', null);
INSERT INTO `org_dept` VALUES ('36', '品管主任', '33', '1', null);
INSERT INTO `org_dept` VALUES ('37', '顾问子部', '10', '0', '111211199');

-- ----------------------------
-- Table structure for `org_post`
-- ----------------------------
DROP TABLE IF EXISTS `org_post`;
CREATE TABLE `org_post` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '增自ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '所在部门ID',
  `post_name` varchar(20) DEFAULT NULL COMMENT '位岗名称',
  `post_remarks` varchar(100) DEFAULT NULL COMMENT '注备',
  PRIMARY KEY (`Id`),
  KEY `FK_Reference_7` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_post
-- ----------------------------
INSERT INTO `org_post` VALUES ('12', '1', '职员', null);
INSERT INTO `org_post` VALUES ('13', '2', '职员', null);
INSERT INTO `org_post` VALUES ('14', '3', '技术骨干', null);
INSERT INTO `org_post` VALUES ('15', '4', '普工', null);
INSERT INTO `org_post` VALUES ('16', '5', '化验员', null);
INSERT INTO `org_post` VALUES ('17', '6', '职员', null);
INSERT INTO `org_post` VALUES ('18', '7', '财务', null);
INSERT INTO `org_post` VALUES ('19', '8', '董事长', null);
INSERT INTO `org_post` VALUES ('20', '9', '总经理', null);
INSERT INTO `org_post` VALUES ('21', '10', '顾问', null);
INSERT INTO `org_post` VALUES ('22', '11', '生产管理', null);
INSERT INTO `org_post` VALUES ('23', '12', '仓管员', null);
INSERT INTO `org_post` VALUES ('24', '13', '计划专员', null);

-- ----------------------------
-- Table structure for `store_goods_info`
-- ----------------------------
DROP TABLE IF EXISTS `store_goods_info`;
CREATE TABLE `store_goods_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(50) NOT NULL COMMENT '物料名称',
  `goods_type` bigint(20) NOT NULL COMMENT '物料类型',
  `remarks` varchar(255) DEFAULT '无',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='库存物料基础信息表';

-- ----------------------------
-- Records of store_goods_info
-- ----------------------------
INSERT INTO `store_goods_info` VALUES ('1', '轴承皮带1', '3', '备注1');
INSERT INTO `store_goods_info` VALUES ('2', '甲烷', '1', '');
INSERT INTO `store_goods_info` VALUES ('3', '轴承基座', '2', '轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座轴承基座');
INSERT INTO `store_goods_info` VALUES ('4', '氮气', '1', '奥术大师多');
INSERT INTO `store_goods_info` VALUES ('5', '大轴承', '2', '1111');
INSERT INTO `store_goods_info` VALUES ('6', '轴承', '2', '');

-- ----------------------------
-- Table structure for `store_goods_inventory_records`
-- ----------------------------
DROP TABLE IF EXISTS `store_goods_inventory_records`;
CREATE TABLE `store_goods_inventory_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_id` bigint(20) DEFAULT '0' COMMENT '规格id',
  `in_date` date DEFAULT NULL COMMENT '入库时间',
  `quantity` int(11) DEFAULT '0' COMMENT '数量',
  `price` float(11,2) DEFAULT '0.00' COMMENT '单价',
  `supplier` varchar(30) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT ' ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='物料型号规格库存信息';

-- ----------------------------
-- Records of store_goods_inventory_records
-- ----------------------------
INSERT INTO `store_goods_inventory_records` VALUES ('4', '1', '2020-08-20', '0', '5.00', 'a', '');
INSERT INTO `store_goods_inventory_records` VALUES ('5', '1', '2020-08-20', '0', '5.00', 'a', '');
INSERT INTO `store_goods_inventory_records` VALUES ('6', '1', '2020-08-20', '0', '6.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('7', '4', '2020-08-20', '0', '5.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('8', '4', null, '0', '5.20', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('9', '5', null, '0', '1.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('10', '1', null, '0', '1.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('11', '4', null, '0', '1.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('12', '1', '2020-08-20', '0', '10.00', '1', '');
INSERT INTO `store_goods_inventory_records` VALUES ('13', '1', '2020-08-21', '0', '9.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('14', '1', null, '0', '10.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('15', '4', null, '18', '12.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('16', '5', null, '0', '0.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('17', '5', null, '69', '10.00', '', '');
INSERT INTO `store_goods_inventory_records` VALUES ('19', '1', '2020-08-23', '0', '5.50', '999', '');
INSERT INTO `store_goods_inventory_records` VALUES ('24', '1', '2020-08-24', '0', '5.00', '1111', '');
INSERT INTO `store_goods_inventory_records` VALUES ('25', '1', null, '372', '5.00', '111', '111');

-- ----------------------------
-- Table structure for `store_goods_model_number_info`
-- ----------------------------
DROP TABLE IF EXISTS `store_goods_model_number_info`;
CREATE TABLE `store_goods_model_number_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) DEFAULT '0' COMMENT '物料id',
  `model_number_name` varchar(20) DEFAULT ' ' COMMENT '型号',
  `quantity` int(11) DEFAULT '0' COMMENT '数量',
  `total_value` float(11,2) DEFAULT '0.00' COMMENT '合计价格',
  `unit` varchar(10) DEFAULT ' ' COMMENT '单位',
  `remarks` varchar(255) DEFAULT ' ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='库存物料规格表';

-- ----------------------------
-- Records of store_goods_model_number_info
-- ----------------------------
INSERT INTO `store_goods_model_number_info` VALUES ('1', '1', '80*50', '372', '0.00', '个', ' ');
INSERT INTO `store_goods_model_number_info` VALUES ('2', '2', '500L', '0', '0.00', '瓶', ' ');
INSERT INTO `store_goods_model_number_info` VALUES ('3', '4', '500L', '0', '0.00', '瓶', ' ');
INSERT INTO `store_goods_model_number_info` VALUES ('4', '1', '100*50', '18', '0.00', '个', ' ');
INSERT INTO `store_goods_model_number_info` VALUES ('5', '1', '100*120', '69', '0.00', '个', ' ');

-- ----------------------------
-- Table structure for `store_goods_stock_log`
-- ----------------------------
DROP TABLE IF EXISTS `store_goods_stock_log`;
CREATE TABLE `store_goods_stock_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_code` varchar(12) DEFAULT '' COMMENT '库存变更单号',
  `model_id` bigint(20) DEFAULT NULL COMMENT '物料规格库存id',
  `change_type` tinyint(2) DEFAULT NULL COMMENT '库存变更类型',
  `change_time` date DEFAULT NULL COMMENT '库存变更时间',
  `change_quantity` int(11) DEFAULT NULL COMMENT '变更数量',
  `price` float DEFAULT NULL COMMENT '入库参数 入库单价',
  `supplier` varchar(30) DEFAULT NULL COMMENT '入库参数 供应商',
  `employee_id` bigint(20) DEFAULT NULL COMMENT '出库参数 领料人',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '出库参数 领料部门',
  `use_for` varchar(30) DEFAULT NULL COMMENT '出库参数 用途',
  `overplus` int(11) DEFAULT NULL COMMENT '剩余数量',
  `op` varchar(20) DEFAULT NULL COMMENT '操作人',
  `remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='库存物料出入库记录';

-- ----------------------------
-- Records of store_goods_stock_log
-- ----------------------------
INSERT INTO `store_goods_stock_log` VALUES ('18', 'I20200823000', '1', '1', '2020-08-23', '50', '5.5', '999', null, null, null, '82', '何路波', '');
INSERT INTO `store_goods_stock_log` VALUES ('19', 'I20200824000', '1', '1', '2020-08-24', '50', '5', '1111', null, null, null, '132', '何路波', '');
INSERT INTO `store_goods_stock_log` VALUES ('20', 'I20200824001', '1', '1', '2020-08-24', '540', '5', '111', null, null, null, '672', '何路波', '111');
INSERT INTO `store_goods_stock_log` VALUES ('21', 'O20200824002', '1', '21', '2020-08-24', '300', '0', null, '246', '12', 'asdasdasd', '372', '何路波', 'asdasdasd');

-- ----------------------------
-- Table structure for `store_goods_type`
-- ----------------------------
DROP TABLE IF EXISTS `store_goods_type`;
CREATE TABLE `store_goods_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(30) NOT NULL COMMENT '类型名称',
  `quick_code` varchar(10) DEFAULT NULL COMMENT '速查编码',
  `type_order` int(11) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT '无' COMMENT '注备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='库存物品类型表';

-- ----------------------------
-- Records of store_goods_type
-- ----------------------------
INSERT INTO `store_goods_type` VALUES ('1', '工业气体类1', 'gyqt', '3', '');
INSERT INTO `store_goods_type` VALUES ('2', '轴承类1', 'zc', '3', '轴承类物料');
INSERT INTO `store_goods_type` VALUES ('3', '皮带类', '', '1', '');

-- ----------------------------
-- Table structure for `store_info`
-- ----------------------------
DROP TABLE IF EXISTS `store_info`;
CREATE TABLE `store_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `stroe_name` varchar(30) NOT NULL DEFAULT '仓名',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父id',
  `store_order` int(11) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL COMMENT '注备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='仓库表：纪录仓库及仓位信息';

-- ----------------------------
-- Records of store_info
-- ----------------------------
INSERT INTO `store_info` VALUES ('1', '五金仓', '0', '1', '无');
INSERT INTO `store_info` VALUES ('2', 'A08', '1', '3', '无');
INSERT INTO `store_info` VALUES ('3', 'a09', '1', '4', '无');
INSERT INTO `store_info` VALUES ('4', 'a01', '1', '1', '无');

-- ----------------------------
-- Table structure for `sys_data_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dictionary`;
CREATE TABLE `sys_data_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_code` varchar(20) DEFAULT '' COMMENT '数据编码',
  `data_number` int(11) DEFAULT NULL COMMENT '数据编号',
  `data_name` varchar(30) DEFAULT NULL COMMENT '数据名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_data_dictionary
-- ----------------------------
INSERT INTO `sys_data_dictionary` VALUES ('1', 'income', '1', '采购入库');
INSERT INTO `sys_data_dictionary` VALUES ('2', 'income', '2', '退回入库');
INSERT INTO `sys_data_dictionary` VALUES ('3', 'income', '3', '回收入库');
INSERT INTO `sys_data_dictionary` VALUES ('4', 'outcome', '21', '领取出库');
INSERT INTO `sys_data_dictionary` VALUES ('5', 'outcome', '22', '损耗出库');
INSERT INTO `sys_data_dictionary` VALUES ('6', 'outcome', '23', '退货出库');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT '0' COMMENT '上级ID',
  `menu_name` varchar(20) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(50) DEFAULT NULL COMMENT '请求串',
  `perms` varchar(30) DEFAULT NULL COMMENT '授权标识',
  `menu_type` tinyint(4) DEFAULT '0' COMMENT '资源类型 0:目录 1:菜单 2:限权',
  `menu_icon` varchar(20) DEFAULT NULL COMMENT '标图代码',
  `menu_order` int(11) DEFAULT '0' COMMENT '排序编号',
  `remarks` varchar(50) DEFAULT NULL COMMENT '注备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', '', '0', null, '10', null);
INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '/system/usermanage.do', '', '1', null, '0', null);
INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '/system/RoleManage.do', '', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '/system/menumanage.do', '', '1', null, '2', null);
INSERT INTO `sys_menu` VALUES ('7', '4', '增加', '', 'sys:menu:add', '2', null, '0', null);
INSERT INTO `sys_menu` VALUES ('8', '4', '修改', '', 'sys:menu:update', '2', null, '2', null);
INSERT INTO `sys_menu` VALUES ('9', '4', '删除', '', 'sys:menu:delete', '2', null, '3', null);
INSERT INTO `sys_menu` VALUES ('10', '3', '保存', '', 'sys:role:save', '2', null, '1', null);
INSERT INTO `sys_menu` VALUES ('11', '0', '设备管理', '', '', '0', null, '1', null);
INSERT INTO `sys_menu` VALUES ('12', '11', '设备信息管理', '/equip/manageequip.do', '', '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('14', '0', '行政信息管理', null, null, '0', null, '3', null);
INSERT INTO `sys_menu` VALUES ('15', '14', '机构信息维护', '/dept/manage.do', '', '1', null, null, null);
INSERT INTO `sys_menu` VALUES ('16', '14', '员工管理', '/employee/manage.do', '', '1', null, null, null);
INSERT INTO `sys_menu` VALUES ('17', '16', '保存', '', 'org:employee:save', '2', null, '1', null);
INSERT INTO `sys_menu` VALUES ('18', '16', '删除', '', 'org:employee:delete', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('19', '15', '保存', '', 'org:dept:save', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('20', '15', '删除', '', 'org:dept:delete', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('21', '3', '删除', '', 'sys:role:delete', '2', null, '2', null);
INSERT INTO `sys_menu` VALUES ('22', '3', '角色资源维护', '', 'sys:role:setmenu', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('23', '2', '增加', '', 'sys:user:save', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('24', '2', '修改', '', 'sys:user:save', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('25', '2', '信息维护按钮组', '', 'sys:user:manage', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('26', '1', '管理员权限', null, 'sys:admin', '2', null, '0', null);
INSERT INTO `sys_menu` VALUES ('27', '12', '配件维护', null, 'equip:info:setparts', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('28', '12', '新增及修改', null, 'equip:info:save', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('29', '12', '删除', null, 'equip:info:delete', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('30', '0', '库存管理', null, null, '0', null, '2', null);
INSERT INTO `sys_menu` VALUES ('31', '30', '仓库仓位管理', '/storeinfo/manage.do', null, '1', null, '1', null);
INSERT INTO `sys_menu` VALUES ('32', '30', '物料类型管理', '/storegoods/manage.do', null, '1', null, null, null);
INSERT INTO `sys_menu` VALUES ('33', '31', '保存', null, 'store:info:save', '2', null, '1', null);
INSERT INTO `sys_menu` VALUES ('34', '31', '删除', null, 'store:info:delete', '2', null, '2', null);
INSERT INTO `sys_menu` VALUES ('35', '32', '保存', null, 'goodstype:info:save', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('36', '32', '删除', null, 'goodstype:info:delete', '2', null, null, null);
INSERT INTO `sys_menu` VALUES ('37', '30', '物料库存信息管理', '/storestockgoodsinfo/manage.do', null, '1', null, '4', null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) DEFAULT '0' COMMENT '所属部门id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `remarks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '0', '系统管理员', '系统管理员备注');
INSERT INTO `sys_role` VALUES ('2', '1', '测试2', '系统管理员备注');
INSERT INTO `sys_role` VALUES ('3', '5', '测试3', '系统管理员备注');
INSERT INTO `sys_role` VALUES ('4', '8', '系统测试', '系统管理员备注');
INSERT INTO `sys_role` VALUES ('5', '11', '信息员', '');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '单菜ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8 COMMENT='角色-菜单映射表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('107', '5', '11');
INSERT INTO `sys_role_menu` VALUES ('108', '5', '12');
INSERT INTO `sys_role_menu` VALUES ('109', '5', '27');
INSERT INTO `sys_role_menu` VALUES ('110', '5', '28');
INSERT INTO `sys_role_menu` VALUES ('111', '5', '29');
INSERT INTO `sys_role_menu` VALUES ('112', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('113', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('114', '1', '23');
INSERT INTO `sys_role_menu` VALUES ('115', '1', '24');
INSERT INTO `sys_role_menu` VALUES ('116', '1', '25');
INSERT INTO `sys_role_menu` VALUES ('117', '1', '26');
INSERT INTO `sys_role_menu` VALUES ('118', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('119', '1', '10');
INSERT INTO `sys_role_menu` VALUES ('120', '1', '21');
INSERT INTO `sys_role_menu` VALUES ('121', '1', '22');
INSERT INTO `sys_role_menu` VALUES ('122', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('123', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('124', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('125', '1', '9');
INSERT INTO `sys_role_menu` VALUES ('126', '1', '11');
INSERT INTO `sys_role_menu` VALUES ('127', '1', '12');
INSERT INTO `sys_role_menu` VALUES ('128', '1', '27');
INSERT INTO `sys_role_menu` VALUES ('129', '1', '28');
INSERT INTO `sys_role_menu` VALUES ('130', '1', '29');
INSERT INTO `sys_role_menu` VALUES ('131', '1', '14');
INSERT INTO `sys_role_menu` VALUES ('132', '1', '15');
INSERT INTO `sys_role_menu` VALUES ('133', '1', '19');
INSERT INTO `sys_role_menu` VALUES ('134', '1', '20');
INSERT INTO `sys_role_menu` VALUES ('135', '1', '16');
INSERT INTO `sys_role_menu` VALUES ('136', '1', '17');
INSERT INTO `sys_role_menu` VALUES ('137', '1', '18');
INSERT INTO `sys_role_menu` VALUES ('138', '1', '30');
INSERT INTO `sys_role_menu` VALUES ('139', '1', '31');
INSERT INTO `sys_role_menu` VALUES ('140', '1', '32');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(128) NOT NULL,
  `employee_id` int(50) DEFAULT NULL,
  `staus` tinyint(4) DEFAULT '1' COMMENT '1:正常 2:被锁定 3:逻辑删除',
  `login_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `isadmin` tinyint(1) DEFAULT '0' COMMENT '否是为管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'helubo0573', 'gbbbgf3w44navmo5d3hkyzmezhbwojf2k22sqkkiz7zswqd5yw2k2kmixfjvhlkukxtupwzfxkg4u', '261', '1', null, null, '1');
INSERT INTO `sys_user` VALUES ('4', 'test', '65faolo247ixyprl4nd3sigjclba35v322gqq36kgpe3tqwiygv22kmixfjvhlkukxtupwzfxkg4u', '239', '2', null, null, '0');
INSERT INTO `sys_user` VALUES ('5', 'zmy', '65faolo247ixyprl4nd3sigjclba35v322gqq36kgpe3tqwiygv22kmixfjvhlkukxtupwzfxkg4u', '240', '1', null, null, '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='用户-角色映射表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '3');
INSERT INTO `sys_user_role` VALUES ('2', '1', '1');
INSERT INTO `sys_user_role` VALUES ('32', '4', '0');
INSERT INTO `sys_user_role` VALUES ('33', '4', '1');
INSERT INTO `sys_user_role` VALUES ('34', '4', '1');
INSERT INTO `sys_user_role` VALUES ('35', '4', '2');
INSERT INTO `sys_user_role` VALUES ('36', '5', '11');
INSERT INTO `sys_user_role` VALUES ('37', '5', '5');

-- ----------------------------
-- View structure for `test`
-- ----------------------------
DROP VIEW IF EXISTS `test`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `test` AS select `emp`.`id` AS `id`,`emp`.`emp_name` AS `emp_name`,`dept`.`id` AS `dep_tid`,`dept`.`dept_name` AS `dept_name`,`post`.`id` AS `post_id`,`post`.`dept_name` AS `post_name`,`emp`.`emp_sex` AS `emp_sex`,`emp`.`emp_dept` AS `emp_dept`,`emp`.`emp_post` AS `emp_post`,`emp`.`emp_mobil` AS `emp_mobil`,`emp`.`emp_date` AS `emp_date`,`emp`.`emp_status` AS `emp_status`,`emp`.`emp_remarks` AS `emp_remarks` from ((`employee_info` `emp` join `org_dept` `post` on((`post`.`id` = `emp`.`emp_post`))) join `org_dept` `dept` on((`post`.`parent_id` = `dept`.`id`)));
