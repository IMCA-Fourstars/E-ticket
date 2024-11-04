-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema hallticket
--

CREATE DATABASE IF NOT EXISTS hallticket;
USE hallticket;

--
-- Definition of table `hall_ticket_requests`
--

DROP TABLE IF EXISTS `hall_ticket_requests`;
CREATE TABLE `hall_ticket_requests` (
  `id` int(11) NOT NULL auto_increment,
  `year` varchar(20) NOT NULL,
  `student_id` int(11) NOT NULL,
  `unique_id` varchar(50) default NULL,
  `department` varchar(50) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `fees_status` varchar(10) NOT NULL,
  `reference_number` varchar(100) default NULL,
  `sname` varchar(45) NOT NULL,
  `smail` varchar(45) NOT NULL,
  `htstatus` varchar(45) NOT NULL default 'No',
  `rollno` varchar(45) NOT NULL,
  `image_data` longblob,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hall_ticket_requests`
--

/*!40000 ALTER TABLE `hall_ticket_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `hall_ticket_requests` ENABLE KEYS */;


--
-- Definition of table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `ustatus` varchar(45) NOT NULL,
  `regtime` varchar(45) NOT NULL,
  `lastlog` varchar(45) default '0',
  `ppic` longblob NOT NULL,
  `rollno` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

/*!40000 ALTER TABLE `students` DISABLE KEYS */;
/*!40000 ALTER TABLE `students` ENABLE KEYS */;


--
-- Definition of table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE `subjects` (
  `id` int(11) NOT NULL auto_increment,
  `year` varchar(20) NOT NULL,
  `department` varchar(50) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `subject1` varchar(100) default NULL,
  `subject1_date` varchar(20) default NULL,
  `subject1_start_time` varchar(20) default NULL,
  `subject1_end_time` varchar(20) default NULL,
  `subject2` varchar(100) default NULL,
  `subject2_date` varchar(20) default NULL,
  `subject2_start_time` varchar(20) default NULL,
  `subject2_end_time` varchar(20) default NULL,
  `subject3` varchar(100) default NULL,
  `subject3_date` varchar(20) default NULL,
  `subject3_start_time` varchar(20) default NULL,
  `subject3_end_time` varchar(20) default NULL,
  `subject4` varchar(100) default NULL,
  `subject4_date` varchar(20) default NULL,
  `subject4_start_time` varchar(20) default NULL,
  `subject4_end_time` varchar(20) default NULL,
  `subject5` varchar(100) default NULL,
  `subject5_date` varchar(20) default NULL,
  `subject5_start_time` varchar(20) default NULL,
  `subject5_end_time` varchar(20) default NULL,
  `lab1` varchar(100) default NULL,
  `lab1_date` varchar(20) default NULL,
  `lab1_start_time` varchar(20) default NULL,
  `lab1_end_time` varchar(20) default NULL,
  `lab2` varchar(100) default NULL,
  `lab2_date` varchar(20) default NULL,
  `lab2_start_time` varchar(20) default NULL,
  `lab2_end_time` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subjects`
--

/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`id`,`year`,`department`,`semester`,`subject1`,`subject1_date`,`subject1_start_time`,`subject1_end_time`,`subject2`,`subject2_date`,`subject2_start_time`,`subject2_end_time`,`subject3`,`subject3_date`,`subject3_start_time`,`subject3_end_time`,`subject4`,`subject4_date`,`subject4_start_time`,`subject4_end_time`,`subject5`,`subject5_date`,`subject5_start_time`,`subject5_end_time`,`lab1`,`lab1_date`,`lab1_start_time`,`lab1_end_time`,`lab2`,`lab2_date`,`lab2_start_time`,`lab2_end_time`) VALUES 
 (1,'1st Year','IT','1','IT001-Digital Graphics','2023-08-26','11:11','11:02','IT002-C Programming','2023-08-29','23:02','23:07','IT003-OOPS & Data Structure','2023-08-30','23:06','15:03','IT004-Internet Programming','2023-08-30','11:06','16:03','IT005-RDBMS','2023-08-30','11:08','16:03','ITLB01- C Programming','2023-09-01','11:04','15:04','ITLB02-RDBMS','2023-09-02','11:04','13:04');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
