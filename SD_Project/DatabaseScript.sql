CREATE DATABASE  IF NOT EXISTS `sd_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sd_project`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sd_project
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `store_id` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_admin_store_idx` (`store_id`),
  KEY `fk_admin_user_idx` (`id`),
  CONSTRAINT `fk_admin_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `fk_admin_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('1','5'),('2','10'),('2','6'),('3','7'),('4','8'),('4','9');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_customer_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('11','0755228943','student'),('13','',''),('14','','IT engineer'),('15','0748131220','secretary'),('16','','student'),('30','','');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design`
--

DROP TABLE IF EXISTS `design`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `design` (
  `id` varchar(255) NOT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `date_and_time` datetime DEFAULT NULL,
  `is_public` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_design_customer_idx` (`customer_id`),
  CONSTRAINT `fk_design_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design`
--

LOCK TABLES `design` WRITE;
/*!40000 ALTER TABLE `design` DISABLE KEYS */;
INSERT INTO `design` VALUES ('21','11','2017-01-23 00:00:00',_binary '','My first design'),('22','11','2017-01-23 00:00:00',_binary '\0','My second design'),('27','11','2022-05-14 00:00:00',_binary '','My second design'),('28','14','2022-05-14 00:00:00',_binary '\0','a'),('29','14','2022-05-14 00:00:00',_binary '','a design'),('31','30','2022-05-14 00:00:00',_binary '\0','maria\'s design');
/*!40000 ALTER TABLE `design` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_product`
--

DROP TABLE IF EXISTS `design_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `design_product` (
  `design_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  KEY `fk_to_design_idx` (`design_id`),
  KEY `fk_to_product_idx` (`product_id`),
  CONSTRAINT `fk_to_design` FOREIGN KEY (`design_id`) REFERENCES `design` (`id`),
  CONSTRAINT `fk_to_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_product`
--

LOCK TABLES `design_product` WRITE;
/*!40000 ALTER TABLE `design_product` DISABLE KEYS */;
INSERT INTO `design_product` VALUES ('21','20'),('22','20'),('27','20'),('28','20'),('21','18'),('29','18'),('31','18'),('21','19'),('22','19'),('27','19'),('28','19'),('29','19'),('31','19');
/*!40000 ALTER TABLE `design_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (32);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `store_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_store_idx` (`store_id`),
  CONSTRAINT `fk_product_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('18','Gresie interior/exterior portelanata, 30.8 x 61.5 cm, Artens Otta, tip parchet, alb, finisaj satinat',69.89,'gresie','https://www.leroymerlin.ro/produse/gresie/569/gresie-interiorexterior-portelanata-30.8-x-61.5-cm-artens-otta-tip-parchet-alb-finisaj-satinat/23230','2'),('19','Gresie interior/exterior portelanata, 30 x 60 cm, Artens Urban Silver, aspect beton, gri, mat',73.9,'gresie','https://www.leroymerlin.ro/produse/gresie/569/gresie-interiorexterior-portelanata-30-x-60-cm-artens-urban-silver-aspect-beton-gri-mat/49525','2'),('20','Oglinda baie Proste, L 50 x 70 cm',41.4,'oglinzi','https://www.leroymerlin.ro/produse/oglinzi-baie/530/oglinda-baie-proste-l-50-x-70-cm/101530','2'),('23','Oglinda baie Proste, L 50 x 70 cm',41.4,'oglinzi','https://www.leroymerlin.ro/produse/oglinzi-baie/530/oglinda-baie-proste-l-50-x-70-cm/101530','2'),('24','Parchet laminat Piccadilly, 8 mm, pin, clasa 31',67.7,'parchet','https://www.leroymerlin.ro/produse/parchet-laminat/556/parchet-laminat-piccadilly-8-mm-pin-clasa-31/78650','2'),('25','Vopsea lavabila colorata Luxens, de interior, 10 L, roz 2, aspect mat',134.1,'lavabile','https://www.leroymerlin.ro/produse/vopsea-lavabila-colorata-interior/782/vopsea-lavabila-colorata-luxens-de-interior-10-l-roz-2-aspect-mat/52614','2'),('26','aaa',12.9,'gresie','aaa','2');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES ('1','Dedeman','Strada Fabricii de Chibrituri nr 5-11, Cluj-Napoca'),('2','Leroy Merlin','Strada Aurel Vlaicu 182, Cluj-Napoca'),('3','Brico Depot','Calea Florești 157-159, Cluj-Napoca'),('4','Hornbach','Strada Plevnei 1-7, Cluj-Napoca');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('10','aleadminLeroyMerlin@yahoo.com','Ale','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('11','iulia@yahoo.com','iulia','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('13','ana@gmail.com','ana','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('14','luci@gmail.com','luci','7110eda4d09e062aa5e4a390b0a572ac0d2c0220'),('15','livia@gmail.com','livia','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('16','dan@gmail.com','dan','7110eda4d09e062aa5e4a390b0a572ac0d2c0220'),('30','maria@yahoo.com','maria','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('5','iuliaadminDedeman@yahoo.com','Iulia','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('6','anaanaadminLeroyMerlin@yahoo.com','Ana','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('7','luciadminBricoDepot@yahoo.com','Luci','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('8','ellaadminHornbach@yahoo.com','Ella','39dfa55283318d31afe5a3ff4a0e3253e2045e43'),('9','mariaadminHornbach@yahoo.com','Maria','39dfa55283318d31afe5a3ff4a0e3253e2045e43');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-14  1:55:14
