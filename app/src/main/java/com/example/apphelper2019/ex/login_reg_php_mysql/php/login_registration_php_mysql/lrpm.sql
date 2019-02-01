/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.1.21-MariaDB : Database - login_registration_php_mysql
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`login_registration_php_mysql` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `login_registration_php_mysql`;

/*Table structure for table `users` */

CREATE TABLE `users` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(70) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`password`,`email`,`created_at`,`updated_at`) values (1,'name','1a1dc91c907325c69271ddf0c944bc72','xachikcell@mail.ru','2018-07-01 14:27:33','2018-07-01 14:27:33');
insert  into `users`(`id`,`username`,`password`,`email`,`created_at`,`updated_at`) values (2,'ttttt','e882b72bccfc2ad578c27b0d9b472a14','ddddd@mail.ru','2018-07-01 19:51:54','2018-07-01 19:51:54');
insert  into `users`(`id`,`username`,`password`,`email`,`created_at`,`updated_at`) values (3,'ppppp','a73f86ae408af70b67141843e7130723','ttt@tt.tt','2018-07-03 13:28:06','2018-07-03 13:28:06');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
