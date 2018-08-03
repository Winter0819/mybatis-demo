-- Table structure for 192.168.3.162 db test demo_user
CREATE TABLE IF NOT EXISTS `demo_user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(18) NOT NULL COMMENT '用户姓名',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  `telephone` varchar(11) NOT NULL COMMENT '手机',
  `title` varchar(32) DEFAULT NULL COMMENT '职称',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `key_name_telephone_createTime` (`name`,`telephone`,createTime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


--Table structure for 192.168.3.166 db test demo_account 
CREATE TABLE IF NOT EXISTS `demo_user_account` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `userId` int(11) NOT NULL UNIQUE COMMENT '用户id',
  `amount` bigint(20) NOT NULL COMMENT '总金额（分）',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号';

-- Table structure for 192.168.3.166 db test demo_account_log
CREATE TABLE IF NOT EXISTS `demo_user_account_log` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `code` varchar(32) NOT NULL UNIQUE COMMENT '领取码',
  `amount` bigint(20) NOT NULL COMMENT '金额（分）',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `key_userId_createTime` (`userId`,createTime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号领取流水';
