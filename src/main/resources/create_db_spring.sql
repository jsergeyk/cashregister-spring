CREATE DATABASE  IF NOT EXISTS `cashspring` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cashspring`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cashspring
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chec`
--

DROP TABLE IF EXISTS `chec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `canceled` tinyint(1) NOT NULL DEFAULT '0',
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `discount` double NOT NULL,
  `registration` int(11) DEFAULT NULL,
  `total` double NOT NULL,
  `creator` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK85q06l8gxj53wli4a47kdgqw0` (`creator`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chec`
--

LOCK TABLES `chec` WRITE;
/*!40000 ALTER TABLE `chec` DISABLE KEYS */;
INSERT INTO `chec` VALUES (14,0,'2019-10-06 00:01:02',0,NULL,10,2),(23,0,'2019-10-07 21:39:56',0,NULL,90,2),(12,1,'2019-10-05 23:52:33',0,NULL,0,2),(10,0,'2019-10-05 23:41:32',0,NULL,40,2),(9,0,'2019-10-05 23:35:42',0,NULL,25,2),(24,0,'2019-10-07 21:41:50',0,NULL,90,2),(15,0,'2019-10-06 00:12:34',0,NULL,30,2),(16,0,'2019-10-06 00:13:21',0,NULL,5,2),(17,0,'2019-10-06 00:14:25',0,NULL,5,2),(18,0,'2019-10-06 00:15:19',0,NULL,5,2),(19,0,'2019-10-06 00:16:30',0,NULL,15,2),(20,0,'2019-10-06 00:44:28',0,NULL,30,2),(21,0,'2019-10-06 00:49:56',0,NULL,30,2),(25,0,'2019-10-07 21:52:50',0,NULL,140,2),(26,0,'2019-10-07 21:58:57',0,NULL,56,2),(27,0,'2019-10-07 22:00:01',0,NULL,28,2),(28,0,'2019-10-07 22:01:40',0,NULL,28,2),(29,0,'2019-10-07 22:04:39',0,NULL,280,2),(30,0,'2019-10-07 22:06:21',0,NULL,54,2);
/*!40000 ALTER TABLE `chec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkspec`
--

DROP TABLE IF EXISTS `checkspec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkspec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `canceled` tinyint(1) NOT NULL DEFAULT '0',
  `nds` int(11) DEFAULT NULL,
  `ndstotal` double NOT NULL,
  `price` double NOT NULL,
  `quant` double NOT NULL,
  `total` double NOT NULL,
  `id_check` bigint(20) NOT NULL,
  `id_good` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKps13kyji4j7vtfu5w4xgcriyw` (`id_check`),
  KEY `FKg6t1wt00bwllh571fm23lm1j` (`id_good`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkspec`
--

LOCK TABLES `checkspec` WRITE;
/*!40000 ALTER TABLE `checkspec` DISABLE KEYS */;
INSERT INTO `checkspec` VALUES (12,0,20,2,5,2,10,14,6),(11,1,20,3.92,9.8,2,19.6,12,7),(10,1,20,1,5,1,5,12,6),(9,0,20,3,5,3,15,10,6),(8,0,20,2,5,2,10,9,6),(7,0,20,3,5,3,15,9,6),(13,0,20,1,5,1,5,15,6),(14,0,20,2,5,2,10,15,6),(15,0,20,3,5,3,15,15,6),(16,0,20,1,5,1,5,16,6),(17,0,20,1,5,1,5,17,6),(18,0,20,1,5,1,5,18,6),(19,0,20,1,5,1,5,19,6),(20,0,20,2,5,2,10,19,6),(21,0,20,1,5,1,5,20,6),(22,0,20,2,5,2,10,20,6),(23,0,20,3,5,3,15,20,6),(24,0,20,1,5,1,5,21,6),(25,0,20,2,5,2,10,21,6),(26,0,20,3,5,3,15,21,6),(27,0,7,4.2,30,2,60,23,27),(28,0,7,2.1,30,1,30,23,27),(29,0,7,4.2,30,2,60,24,27),(30,0,7,2.1,30,1,30,24,27),(31,0,7,3.92,28,2,56,25,19),(32,0,7,5.88,28,3,84,25,19),(33,0,20,5.6,28,1,28,26,19),(34,0,7,1.96,28,1,28,26,19),(35,0,7,1.96,28,1,28,27,19),(36,0,20,5.6,28,1,28,28,19),(37,0,7,19.6,28,10,280,29,19),(38,0,20,10.8,18,3,54,30,9);
/*!40000 ALTER TABLE `checkspec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fiscal`
--

DROP TABLE IF EXISTS `fiscal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fiscal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fiscal`
--

LOCK TABLES `fiscal` WRITE;
/*!40000 ALTER TABLE `fiscal` DISABLE KEYS */;
/*!40000 ALTER TABLE `fiscal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `measure` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quant` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (2,33,'прим','кг','Лук белый',19.5,7),(3,20,'','кг','Петрушка',16,20),(4,12,'','кг','Сливы',20,50),(5,34,'','кг','Редис',41,100),(6,1,'','кг','Буряк',5,66),(7,2,'','кг','Морковь',9.8,174),(9,3,NULL,'кг','Огурцы',18,170),(10,4,NULL,'кг','Картошка',13,120),(11,5,NULL,'кг','Лук репчатый',8,110),(12,6,NULL,'кг','Чеснок',65,100),(13,7,NULL,'кг','Бананы',22,70),(14,8,NULL,'кг','Капуста цветная',25.6,240),(15,9,NULL,'кг','Капуста ранняя',12,240),(16,10,NULL,'кг','Капуста пекинская',21,80),(17,11,NULL,'кг','Дыня',5.2,135),(18,13,NULL,'кг','Арбуз',6,135),(19,14,NULL,'кг','Персик',28,116),(20,15,NULL,'кг','Киви',90,110),(21,16,NULL,'кг','Авокадо',31,75),(22,17,NULL,'кг','Ананас',25,90),(23,19,NULL,'кг','Укроп',15,10),(25,18,NULL,'кг','Горох',11,10),(26,21,NULL,'кг','Фасоль',12,10),(27,22,NULL,'кг','Персики',30,24),(28,23,NULL,'кг','Лук зеленый',63,13),(29,24,NULL,'кг','Апельсины',33,15),(30,25,NULL,'кг','Мандарины',35,15),(31,26,NULL,'кг','Кабачок',12,12),(33,27,NULL,'кг','Тыква',24.8,15),(34,35,NULL,'кг','Груши',23,15),(35,36,NULL,'кг','Яблоки',17.5,30),(36,37,NULL,'шт','Инжир',10,13),(58,38,'','шт','Инжир сушеный',16,45);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id_user_type` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm3y953fsjasj6ev13yabo5skm` (`id_user_type`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'a1@gmail.com','Сергей','1',4),(2,'a2@gmail.com','Александр','2',3),(16,'a3@gmail.com','Николай','3',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (4,'Товаровед','goods_spec'),(3,'Кассир','cashier'),(2,'Старший кассир','senior_cashier'),(1,'Администратор','admin');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-08  3:19:57
