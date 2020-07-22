-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aidsys
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
                            `account_id` int(11) NOT NULL AUTO_INCREMENT,
                            `name` varchar(32) NOT NULL,
                            `pwd` varchar(32) NOT NULL,
                            `phone` varchar(11) NOT NULL,
                            PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'name1','123456','18768194932'),(2,'name2','123456','13912345677');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dev_ownerships`
--

DROP TABLE IF EXISTS `dev_ownerships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dev_ownerships` (
                                  `device_id` int(11) NOT NULL,
                                  `account_id` int(11) NOT NULL,
                                  PRIMARY KEY (`device_id`),
                                  KEY `dev_ownerships_accounts_id_fk` (`account_id`),
                                  CONSTRAINT `dev_ownerships_accounts_id_fk` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`),
                                  CONSTRAINT `dev_ownerships_devices_id_fk` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dev_ownerships`
--

LOCK TABLES `dev_ownerships` WRITE;
/*!40000 ALTER TABLE `dev_ownerships` DISABLE KEYS */;
INSERT INTO `dev_ownerships` VALUES (1,1);
/*!40000 ALTER TABLE `dev_ownerships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devices` (
                           `device_id` int(11) NOT NULL AUTO_INCREMENT,
                           `serial` varchar(32) NOT NULL,
                           `description` varchar(64) NOT NULL,
                           PRIMARY KEY (`device_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (1,'DEV001','设备1'),(2,'DEV002','设备2');
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fences`
--

DROP TABLE IF EXISTS `fences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fences` (
                          `device_id` int(11) NOT NULL,
                          `longitude` decimal(9,6) NOT NULL,
                          `latitude` decimal(9,6) NOT NULL,
                          `num` int(11) NOT NULL,
                          KEY `fences_devices_device_id_fk` (`device_id`),
                          CONSTRAINT `fences_devices_device_id_fk` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fences`
--

LOCK TABLES `fences` WRITE;
/*!40000 ALTER TABLE `fences` DISABLE KEYS */;
/*!40000 ALTER TABLE `fences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hearts`
--

DROP TABLE IF EXISTS `hearts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hearts` (
                          `device_id` int(11) NOT NULL,
                          `rate` int(11) NOT NULL,
                          `oxy` double NOT NULL,
                          `timestamp` timestamp NOT NULL,
                          KEY `hearts_devices_device_id_fk` (`device_id`),
                          CONSTRAINT `hearts_devices_device_id_fk` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hearts`
--

LOCK TABLES `hearts` WRITE;
/*!40000 ALTER TABLE `hearts` DISABLE KEYS */;
INSERT INTO `hearts` VALUES (1,65,0.2,'2020-06-30 13:22:30'),(1,66,0.18,'2020-06-30 13:22:31'),(1,67,0.15,'2020-06-30 13:22:31');
/*!40000 ALTER TABLE `hearts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
                             `device_id` int(11) NOT NULL,
                             `longitude` decimal(9,6) NOT NULL,
                             `latitude` decimal(9,6) NOT NULL,
                             `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             KEY `locations_devices_id_fk` (`device_id`),
                             CONSTRAINT `locations_devices_id_fk` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,120.041402,30.228604,'2020-06-24 02:29:52'),(1,122.123456,31.123456,'2020-06-24 02:35:30'),(1,121.384911,30.228604,'2020-06-25 02:29:52'),(1,121.524670,31.123456,'2020-06-25 02:35:30'),(2,100.100000,200.200000,'2020-06-25 11:22:34'),(2,100.100000,200.200000,'2020-06-25 11:23:01'),(1,111.111000,80.111000,'2020-06-25 11:27:46'),(1,111.111000,80.111000,'2020-06-25 11:31:09'),(2,100.100000,200.200000,'2020-06-25 11:33:21');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steps`
--

DROP TABLE IF EXISTS `steps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `steps` (
                         `device_id` int(11) NOT NULL,
                         `steps` int(11) NOT NULL,
                         PRIMARY KEY (`device_id`),
                         CONSTRAINT `steps_devices_device_id_fk` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steps`
--

LOCK TABLES `steps` WRITE;
/*!40000 ALTER TABLE `steps` DISABLE KEYS */;
INSERT INTO `steps` VALUES (1,104);
/*!40000 ALTER TABLE `steps` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-30 21:45:43
