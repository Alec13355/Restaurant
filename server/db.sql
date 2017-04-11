-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: restaurant
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Table structure for table `CUSTOMER`
--

DROP TABLE IF EXISTS `CUSTOMER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CUSTOMER` (
  `USER_NAME` varchar(30) NOT NULL,
  `CUSTOMER_ID` mediumint(255) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `PASS` varchar(50) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CUSTOMER`
--

LOCK TABLES `CUSTOMER` WRITE;
/*!40000 ALTER TABLE `CUSTOMER` DISABLE KEYS */;
/*!40000 ALTER TABLE `CUSTOMER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEES`
--

DROP TABLE IF EXISTS `EMPLOYEES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEES` (
  `L_NAME` varchar(30) NOT NULL,
  `F_NAME` varchar(30) NOT NULL,
  `EMPLOYEE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERM_STRING` varchar(30) NOT NULL,
  `STATUS` int(10) NOT NULL,
  `PASS` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(50) DEFAULT NULL,
  `CELL` varchar(30) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  `PAY_HR` int(11) DEFAULT NULL,
  `ROUTING` varchar(50) NOT NULL,
  `SOCIAL` varchar(50) NOT NULL,
  `BANK_NUM` varchar(50) NOT NULL,
  PRIMARY KEY (`EMPLOYEE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEES`
--

LOCK TABLES `EMPLOYEES` WRITE;
/*!40000 ALTER TABLE `EMPLOYEES` DISABLE KEYS */;
INSERT INTO `EMPLOYEES` VALUES ('SMITH','BOB',1,'1111',0,'PASSWORD','3700 Hogge Dr, Parker, TX 75002','6415212565','6415212565',725,'','',''),('cat','man',2,'0000',0,'secret','booker st.','64141715','81616',725,'','',''),('drafman','san',3,'0000',0,'secret','booker st.','64141715','81616',725,'','',''),('bon','ron',4,'0000',0,'secret2','booker st.','64141715','81616',725,'','',''),('jon','ron',5,'0000',0,'secret2','booker st.','64141715','81616',725,'','',''),('jon','ron',6,'0000',0,'secret2','booker st.','64141715','81616',725,'','',''),('jon','ron',7,'0000',0,'secret2','booker st.','64141715','81616',725,'','',''),('jon','ron',8,'0000',0,'secret2','booker st.','64141715','81616',725,'','',''),('jon','ron',9,'0000',0,'secret2','booker st.','64141715','81616',725,'','',''),('jon','ron',10,'0000',0,'secret2','booker st.','64141715','81616',725,'','',''),('jon','ron',11,'0000',0,'secret2','booker st.','64141715','81616',725,'','','');
/*!40000 ALTER TABLE `EMPLOYEES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEE_LOG`
--

DROP TABLE IF EXISTS `EMPLOYEE_LOG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEE_LOG` (
  `EMPLOYEE_ID` mediumint(9) NOT NULL,
  `LOG_ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `CLOCK_IN` int(11) NOT NULL,
  `CLOCK_OUT` int(11) NOT NULL,
  `TIME` mediumtext NOT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEE_LOG`
--

LOCK TABLES `EMPLOYEE_LOG` WRITE;
/*!40000 ALTER TABLE `EMPLOYEE_LOG` DISABLE KEYS */;
INSERT INTO `EMPLOYEE_LOG` VALUES (1,1,1,0,'1430648562'),(1,2,0,1,'1430808667'),(1,3,1,0,'1430926184'),(1,4,0,1,'1431063916');
/*!40000 ALTER TABLE `EMPLOYEE_LOG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FOOD`
--

DROP TABLE IF EXISTS `FOOD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FOOD` (
  `NAME` varchar(30) NOT NULL,
  `FOOD_ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `QUANTITY` mediumint(9) NOT NULL,
  `PRICE` mediumint(9) NOT NULL,
  `DESCR` text NOT NULL,
  `OPTIONS` varchar(100) NOT NULL,
  PRIMARY KEY (`FOOD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FOOD`
--

LOCK TABLES `FOOD` WRITE;
/*!40000 ALTER TABLE `FOOD` DISABLE KEYS */;
INSERT INTO `FOOD` VALUES ('burger',1,50,1000,'a burger dumbass',''),('burger',2,50,1000,'a burger dumbass',''),('fries',3,50,1000,'potaotes stuff','');
/*!40000 ALTER TABLE `FOOD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDERS` (
  `DESCRIP` text NOT NULL,
  `ORDER_ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `FOODSTRING` varchar(30) DEFAULT NULL,
  `LOCATION` varchar(30) NOT NULL,
  `PRICE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ORDERS`
--

LOCK TABLES `ORDERS` WRITE;
/*!40000 ALTER TABLE `ORDERS` DISABLE KEYS */;
INSERT INTO `ORDERS` VALUES ('Buger',1,'','table 9',40),('Buger',2,'1-','table 9',40),('Buger',3,'1-','table 9',40),('Raw Cow',4,'1-','table 9',40),('burger and fries',5,'1-3-','table 9',40),('SPECIAL 9',6,'1(extra meat)-3(extra crispy)-','table 9',1500);
/*!40000 ALTER TABLE `ORDERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESERVATIONS`
--

DROP TABLE IF EXISTS `RESERVATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESERVATIONS` (
  `DESCRIPTION` text NOT NULL,
  `ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `TABLE_ID` mediumint(9) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `CUSTOMER_ID` mediumint(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESERVATIONS`
--

LOCK TABLES `RESERVATIONS` WRITE;
/*!40000 ALTER TABLE `RESERVATIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `RESERVATIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TABLES`
--

DROP TABLE IF EXISTS `TABLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TABLES` (
  `NAME` varchar(30) NOT NULL,
  `TABLE_ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `X_COORD` int(11) NOT NULL,
  `Y_COORD` int(11) NOT NULL,
  `NUM_SEATS` int(11) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `EMPLOYEE_ID` mediumint(50) NOT NULL,
  PRIMARY KEY (`TABLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TABLES`
--

LOCK TABLES `TABLES` WRITE;
/*!40000 ALTER TABLE `TABLES` DISABLE KEYS */;
/*!40000 ALTER TABLE `TABLES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-06 12:43:22
