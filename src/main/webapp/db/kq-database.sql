/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.24 : Database - kq-database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kq-database` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kq-database`;

/*Table structure for table `kq_menu` */

DROP TABLE IF EXISTS `kq_menu`;

CREATE TABLE `kq_menu` (
  `id` bigint(20) NOT NULL,
  `text` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单跳转路径',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  `icon` varchar(20) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_text_unique` (`text`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人考勤系统菜单表';

/*Data for the table `kq_menu` */

/*Table structure for table `kq_overtime` */

DROP TABLE IF EXISTS `kq_overtime`;

CREATE TABLE `kq_overtime` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `work_date` date DEFAULT NULL COMMENT '加班日期',
  `start_time` varchar(5) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(5) DEFAULT NULL COMMENT '结束时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否,1-是)',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  `total_house` double DEFAULT NULL COMMENT '总计(单位:小时)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤加班表';

/*Data for the table `kq_overtime` */

/*Table structure for table `kq_user` */

DROP TABLE IF EXISTS `kq_user`;

CREATE TABLE `kq_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `cname` varchar(20) DEFAULT NULL COMMENT '中文名',
  `ename` varchar(20) DEFAULT NULL COMMENT '花名',
  `password` varchar(36) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `last_login_ip` varchar(15) DEFAULT NULL COMMENT '最后登陆ip',
  `sex` int(1) DEFAULT '0' COMMENT '性别(0-保密,1男,2-女)',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kq_user_account_unique` (`account`),
  UNIQUE KEY `kq_user_mobile_unique` (`mobile`),
  UNIQUE KEY `kq_user_email_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='考勤用户表';

/*Data for the table `kq_user` */

insert  into `kq_user`(`id`,`account`,`cname`,`ename`,`password`,`create_time`,`last_login_time`,`last_login_ip`,`sex`,`mobile`,`email`) values (1,'wangjian','王翔','王翦','b2098805d78de8cc015a7ebd34046142','2016-07-08 11:31:20','2016-07-08 17:31:45','0:0:0:0:0:0:0:1',1,'18721472363','wangjian@baofoo.com');

/*Table structure for table `kq_working_log` */

DROP TABLE IF EXISTS `kq_working_log`;

CREATE TABLE `kq_working_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `work_date` date DEFAULT NULL COMMENT '工作日期',
  `note` text COMMENT '工作日志内容(富文本信息)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除(0-否,1-是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考勤工作日志表';

/*Data for the table `kq_working_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
