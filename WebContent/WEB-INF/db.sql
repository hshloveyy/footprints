CREATE DATABASE  IF NOT EXISTS `isbbsdatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `isbbsdatabase`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: isbbsdatabase
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pre_ucenter_members`
--

DROP TABLE IF EXISTS `pre_ucenter_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pre_ucenter_members` (
  `uid` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(15) NOT NULL DEFAULT '',
  `password` char(32) NOT NULL DEFAULT '',
  `email` char(32) NOT NULL DEFAULT '',
  `myid` char(30) NOT NULL DEFAULT '',
  `myidkey` char(16) NOT NULL DEFAULT '',
  `regip` char(15) NOT NULL DEFAULT '',
  `regdate` int(10) unsigned NOT NULL DEFAULT '0',
  `lastloginip` int(10) NOT NULL DEFAULT '0',
  `lastlogintime` int(10) unsigned NOT NULL DEFAULT '0',
  `salt` char(6) NOT NULL,
  `secques` char(8) NOT NULL DEFAULT '',
  `sex` varchar(2) DEFAULT NULL,
  `photo_img_id` varchar(255) DEFAULT NULL,
  `mobile_phone` varchar(20) DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `star` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`),
  KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=1395 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_about`
--

DROP TABLE IF EXISTS `t_about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_about` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `about_content` varchar(2000) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `millsecond` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_advert`
--

DROP TABLE IF EXISTS `t_advert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_advert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `page_id` int(11) DEFAULT NULL,
  `image_id` varchar(200) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_city`
--

DROP TABLE IF EXISTS `t_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(20) DEFAULT NULL,
  `en_name` varchar(20) DEFAULT NULL,
  `sort` int(10) DEFAULT '0',
  `province_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `shop_id` int(11) DEFAULT NULL COMMENT '商铺Id(被评论者)',
  `comment_content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `consume` varchar(255) DEFAULT NULL COMMENT '消费',
  `grade` int(255) DEFAULT NULL COMMENT '评分',
  `create_time` varchar(25) DEFAULT NULL,
  `create_millsecond` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_comment_image`
--

DROP TABLE IF EXISTS `t_comment_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment_image` (
  `comment_id` int(11) NOT NULL,
  `image_id` varchar(255) NOT NULL,
  `type` varchar(10) NOT NULL COMMENT '类型',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_comment_like`
--

DROP TABLE IF EXISTS `t_comment_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `last_time` varchar(255) DEFAULT NULL,
  `millsecond` varchar(50) DEFAULT NULL,
  `is_click` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_comment_user`
--

DROP TABLE IF EXISTS `t_comment_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_comment_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `from_user` varchar(30) DEFAULT NULL,
  `create_time` varchar(25) DEFAULT NULL,
  `millisecond` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_file_info`
--

DROP TABLE IF EXISTS `t_file_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_file_info` (
  `POID` varchar(80) NOT NULL,
  `ENCRYPTION` varchar(80) DEFAULT NULL,
  `FILE_NAME` varchar(160) DEFAULT NULL,
  `FILE_EXT` varchar(20) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `REMARK` varchar(160) DEFAULT NULL,
  `DESCR` varchar(240) DEFAULT NULL,
  `EXT1` varchar(64) DEFAULT NULL,
  `EXT2` varchar(160) DEFAULT NULL,
  `EXT3` varchar(80) DEFAULT NULL,
  `EXT4` mediumtext,
  `SAVE_PATH` varchar(80) DEFAULT NULL,
  `SAVE_TYPE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`POID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_kind`
--

DROP TABLE IF EXISTS `t_kind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kind_name` varchar(255) DEFAULT NULL,
  `sort` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_message`
--

DROP TABLE IF EXISTS `t_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `theme` varchar(50) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_notice`
--

DROP TABLE IF EXISTS `t_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_theme` varchar(50) DEFAULT NULL,
  `notice_content` varchar(2000) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `millsecond` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_page_city`
--

DROP TABLE IF EXISTS `t_page_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_page_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(20) DEFAULT NULL,
  `en_name` varchar(20) DEFAULT NULL,
  `sort` int(10) DEFAULT '0',
  `province_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_page_province`
--

DROP TABLE IF EXISTS `t_page_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_page_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `en_name` varchar(20) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `type` int(11) DEFAULT NULL COMMENT '1--足迹  2-黄页',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_phone_code`
--

DROP TABLE IF EXISTS `t_phone_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_phone_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_protocol`
--

DROP TABLE IF EXISTS `t_protocol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_protocol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `protocol_content` text,
  `create_time` varchar(20) DEFAULT NULL,
  `millsecond` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_province`
--

DROP TABLE IF EXISTS `t_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `en_name` varchar(20) DEFAULT NULL,
  `sort` int(11) DEFAULT '0',
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_report`
--

DROP TABLE IF EXISTS `t_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `report_reason` varchar(255) DEFAULT NULL,
  `report_type` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_shop_image`
--

DROP TABLE IF EXISTS `t_shop_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_image` (
  `shop_id` int(11) NOT NULL,
  `image_id` varchar(255) NOT NULL,
  `type` varchar(10) NOT NULL COMMENT '类型',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_shop_info`
--

DROP TABLE IF EXISTS `t_shop_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL COMMENT '纬度',
  `Longitude` varchar(20) DEFAULT NULL COMMENT '经度',
  `Altitude` varchar(10) DEFAULT NULL COMMENT '海拔高度',
  `Dist` varchar(20) DEFAULT NULL COMMENT '距离',
  `AvgSpd` varchar(20) DEFAULT NULL COMMENT '平均速度',
  `Description` varchar(255) DEFAULT NULL COMMENT '描述',
  `Icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `Icon_Scale` varchar(100) DEFAULT NULL COMMENT '图标比例',
  `IconAltitude` varchar(50) DEFAULT NULL COMMENT '图标高度',
  `IconHeading` varchar(50) DEFAULT NULL COMMENT '图标标题',
  `IconColor` varchar(50) DEFAULT NULL COMMENT '图标颜色',
  `LineStringColor` varchar(50) DEFAULT NULL COMMENT '线条颜色',
  `HideNameUntilMouseOver` varchar(50) DEFAULT NULL COMMENT '鼠标移动上去时才显示名称',
  `mark` varchar(50) DEFAULT NULL COMMENT '标签',
  `ISBBSPoints` varchar(50) DEFAULT NULL COMMENT '足记积分',
  `UploadPics` varchar(255) DEFAULT NULL,
  `ISBBSDiscounts` varchar(50) DEFAULT NULL COMMENT 'ISBBS优惠',
  `Address` varchar(200) DEFAULT NULL COMMENT '地址',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '电话',
  `Website` varchar(50) DEFAULT NULL COMMENT '网址',
  `MealStyle` varchar(50) DEFAULT NULL COMMENT '菜系',
  `DeliveryRange` varchar(50) DEFAULT NULL COMMENT '送餐范围',
  `Price` varchar(50) DEFAULT NULL COMMENT '价格',
  `Hours` varchar(50) DEFAULT NULL COMMENT '营业时间',
  `Comments` varchar(50) DEFAULT NULL COMMENT '备注',
  `Province` varchar(50) DEFAULT NULL COMMENT '省份',
  `City` varchar(50) DEFAULT NULL COMMENT '城市',
  `Class` varchar(50) DEFAULT NULL COMMENT '分类',
  `Subclass` varchar(50) DEFAULT NULL COMMENT '子分类',
  `isDiscount` int(10) DEFAULT NULL,
  `discount` double(10,0) DEFAULT NULL,
  `good` int(10) DEFAULT NULL,
  `is_ads` int(2) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `shopName` varchar(50) DEFAULT NULL COMMENT '店铺名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_shop_like`
--

DROP TABLE IF EXISTS `t_shop_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `last_time` varchar(255) DEFAULT NULL,
  `millsecond` varchar(50) DEFAULT NULL,
  `is_click` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_shop_worktime`
--

DROP TABLE IF EXISTS `t_shop_worktime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_shop_worktime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT NULL,
  `p_week` int(11) DEFAULT NULL,
  `start_time` varchar(10) DEFAULT NULL,
  `end_time` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sign`
--

DROP TABLE IF EXISTS `t_sign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `sign_count` int(11) DEFAULT NULL COMMENT '签到次数',
  `create_time` varchar(25) DEFAULT NULL,
  `last_time` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sub_category`
--

DROP TABLE IF EXISTS `t_sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sub_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_category_name` varchar(20) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_sub_kind`
--

DROP TABLE IF EXISTS `t_sub_kind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sub_kind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_kind_name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_yellow_page_info`
--

DROP TABLE IF EXISTS `t_yellow_page_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_yellow_page_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `kind_id` int(11) DEFAULT NULL,
  `sub_kind_id` int(11) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `province` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `more_msg` varchar(255) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL COMMENT '纬度',
  `Longitude` varchar(20) DEFAULT NULL COMMENT '经度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-05 20:03:13
