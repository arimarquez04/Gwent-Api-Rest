-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: gwent
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `carta`
--

DROP TABLE IF EXISTS `carta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `lema` varchar(100) DEFAULT NULL,
  `fuerza` int DEFAULT NULL,
  `posicion` varchar(25) DEFAULT NULL,
  `jugador_id` int DEFAULT NULL,
  `es_heroe` tinyint DEFAULT NULL,
  `habilidad` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_carta_jugador` (`jugador_id`),
  CONSTRAINT `fk_carta_jugador` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta`
--

LOCK TABLES `carta` WRITE;
/*!40000 ALTER TABLE `carta` DISABLE KEYS */;
INSERT INTO `carta` VALUES (134,'Vernon Roche','',10,'CUERPOACUERPO',14,NULL,NULL),(135,'John Natalis','',10,'CUERPOACUERPO',14,NULL,NULL),(136,'Esterad Thyssen','',10,'CUERPOACUERPO',14,NULL,NULL),(137,'Philippa Eilhart','',10,'DISTANCIA',14,NULL,NULL),(138,'Vesemir','',6,'CUERPOACUERPO',14,NULL,NULL),(139,'Zoltan Chivay','',5,'CUERPOACUERPO',14,NULL,NULL),(140,'Geralt de Rivia','',15,'CUERPOACUERPO',14,NULL,NULL),(141,'Cirilla Fiona Elen Riannon','',15,'CUERPOACUERPO',14,NULL,NULL),(142,'Yennefer de Vengerberg','',7,'DISTANCIA',14,NULL,NULL),(143,'Triss Merigold','',7,'CUERPOACUERPO',14,NULL,NULL),(144,'Catapulta','',8,'ASEDIO',14,NULL,NULL),(145,'Trabuquete','',6,'ASEDIO',14,NULL,NULL),(146,'Balista','',6,'ASEDIO',14,NULL,NULL),(147,'Torre de Asedio','',6,'ASEDIO',14,NULL,NULL),(148,'Ves','',5,'CUERPOACUERPO',14,NULL,NULL),(149,'Siegfried de Denesle','',5,'CUERPOACUERPO',14,NULL,NULL),(150,'Keira Metz','',5,'DISTANCIA',14,NULL,NULL),(151,'Sile de Tansarville','',5,'DISTANCIA',14,NULL,NULL),(152,'Principe Stennis','',5,'CUERPOACUERPO',14,NULL,NULL),(153,'Sigismund Dijkstra','',4,'CUERPOACUERPO',14,NULL,NULL),(154,'Vernon Roche','',10,'CUERPOACUERPO',15,NULL,NULL),(155,'John Natalis','',10,'CUERPOACUERPO',15,NULL,NULL),(156,'Esterad Thyssen','',10,'CUERPOACUERPO',15,NULL,NULL),(157,'Philippa Eilhart','',10,'DISTANCIA',15,NULL,NULL),(158,'Vesemir','',6,'CUERPOACUERPO',15,NULL,NULL),(159,'Zoltan Chivay','',5,'CUERPOACUERPO',15,NULL,NULL),(160,'Geralt de Rivia','',15,'CUERPOACUERPO',15,NULL,NULL),(161,'Cirilla Fiona Elen Riannon','',15,'CUERPOACUERPO',15,NULL,NULL),(162,'Yennefer de Vengerberg','',7,'DISTANCIA',15,NULL,NULL),(163,'Triss Merigold','',7,'CUERPOACUERPO',15,NULL,NULL),(164,'Catapulta','',8,'ASEDIO',15,NULL,NULL),(165,'Trabuquete','',6,'ASEDIO',15,NULL,NULL),(166,'Balista','',6,'ASEDIO',15,NULL,NULL),(167,'Torre de Asedio','',6,'ASEDIO',15,NULL,NULL),(168,'Ves','',5,'CUERPOACUERPO',15,NULL,NULL),(169,'Siegfried de Denesle','',5,'CUERPOACUERPO',15,NULL,NULL),(170,'Keira Metz','',5,'DISTANCIA',15,NULL,NULL),(171,'Sile de Tansarville','',5,'DISTANCIA',15,NULL,NULL),(172,'Principe Stennis','',5,'CUERPOACUERPO',15,NULL,NULL),(173,'Sigismund Dijkstra','',4,'CUERPOACUERPO',15,NULL,NULL);
/*!40000 ALTER TABLE `carta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carta_partida_jugador`
--

DROP TABLE IF EXISTS `carta_partida_jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta_partida_jugador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `partida_jugador_id` int DEFAULT NULL,
  `carta_id` int DEFAULT NULL,
  `ubicacion` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkcartapartidajugador_carta` (`carta_id`),
  KEY `fkcartapartidajugador_partidajugador` (`partida_jugador_id`),
  CONSTRAINT `fkcartapartidajugador_carta` FOREIGN KEY (`carta_id`) REFERENCES `carta` (`id`),
  CONSTRAINT `fkcartapartidajugador_partidajugador` FOREIGN KEY (`partida_jugador_id`) REFERENCES `partida_jugador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=552 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta_partida_jugador`
--

LOCK TABLES `carta_partida_jugador` WRITE;
/*!40000 ALTER TABLE `carta_partida_jugador` DISABLE KEYS */;
INSERT INTO `carta_partida_jugador` VALUES (472,48,135,'MAZO'),(473,48,138,'MAZO'),(474,48,140,'MAZO'),(475,48,142,'MAZO'),(476,48,143,'MAZO'),(477,48,144,'MAZO'),(478,48,145,'MAZO'),(479,48,146,'MAZO'),(480,48,150,'MAZO'),(481,48,153,'MAZO'),(482,48,134,'CEMENTERIO'),(483,48,141,'BARAJA'),(484,48,136,'BARAJA'),(485,48,148,'BARAJA'),(486,48,151,'BARAJA'),(487,48,152,'BARAJA'),(488,48,137,'BARAJA'),(489,48,139,'BARAJA'),(490,48,149,'BARAJA'),(491,48,147,'BARAJA'),(492,49,154,'MAZO'),(493,49,155,'MAZO'),(494,49,160,'MAZO'),(495,49,163,'MAZO'),(496,49,166,'MAZO'),(497,49,167,'MAZO'),(498,49,169,'MAZO'),(499,49,170,'MAZO'),(500,49,171,'MAZO'),(501,49,173,'MAZO'),(502,49,172,'CEMENTERIO'),(503,49,165,'BARAJA'),(504,49,164,'BARAJA'),(505,49,156,'BARAJA'),(506,49,168,'BARAJA'),(507,49,157,'BARAJA'),(508,49,158,'BARAJA'),(509,49,161,'BARAJA'),(510,49,162,'BARAJA'),(511,49,159,'BARAJA'),(512,51,155,'MAZO'),(513,51,158,'MAZO'),(514,51,160,'MAZO'),(515,51,164,'MAZO'),(516,51,165,'MAZO'),(517,51,166,'MAZO'),(518,51,168,'MAZO'),(519,51,169,'MAZO'),(520,51,171,'MAZO'),(521,51,172,'MAZO'),(522,51,154,'BARAJA'),(523,51,167,'BARAJA'),(524,51,159,'BARAJA'),(525,51,170,'BARAJA'),(526,51,163,'BARAJA'),(527,51,173,'BARAJA'),(528,51,157,'BARAJA'),(529,51,156,'BARAJA'),(530,51,162,'BARAJA'),(531,51,161,'BARAJA'),(532,50,134,'MAZO'),(533,50,135,'MAZO'),(534,50,136,'MAZO'),(535,50,138,'MAZO'),(536,50,139,'MAZO'),(537,50,141,'MAZO'),(538,50,142,'MAZO'),(539,50,145,'MAZO'),(540,50,146,'MAZO'),(541,50,151,'MAZO'),(542,50,152,'BARAJA'),(543,50,150,'BARAJA'),(544,50,137,'BARAJA'),(545,50,143,'BARAJA'),(546,50,153,'BARAJA'),(547,50,144,'BARAJA'),(548,50,148,'BARAJA'),(549,50,147,'BARAJA'),(550,50,149,'BARAJA'),(551,50,140,'BARAJA');
/*!40000 ALTER TABLE `carta_partida_jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apodo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `apodo_UNIQUE` (`apodo`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (15,'Ari'),(14,'Jaz<3'),(16,'Seba');
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(25) DEFAULT NULL,
  `ronda` int DEFAULT NULL,
  `ganador_id` int DEFAULT NULL,
  `perdedor_id` int DEFAULT NULL,
  `empate` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partida_jugador_ganador` (`ganador_id`),
  KEY `fk_partida_jugador_perdedor` (`perdedor_id`),
  CONSTRAINT `fk_partida_jugador_ganador` FOREIGN KEY (`ganador_id`) REFERENCES `jugador` (`id`),
  CONSTRAINT `fk_partida_jugador_perdedor` FOREIGN KEY (`perdedor_id`) REFERENCES `jugador` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida`
--

LOCK TABLES `partida` WRITE;
/*!40000 ALTER TABLE `partida` DISABLE KEYS */;
INSERT INTO `partida` VALUES (29,'TERMINADO',3,14,15,NULL),(30,'TERMINADO',3,14,15,1);
/*!40000 ALTER TABLE `partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida_jugador`
--

DROP TABLE IF EXISTS `partida_jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida_jugador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `partida_id` int DEFAULT NULL,
  `jugador_id` int DEFAULT NULL,
  `turno` tinyint DEFAULT NULL,
  `paso` tinyint DEFAULT NULL,
  `vida` int DEFAULT NULL,
  `descarto` tinyint DEFAULT NULL,
  `preparo_mazo` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partidajugador_partida` (`partida_id`),
  KEY `fk_partidajugador_jugador` (`jugador_id`),
  CONSTRAINT `fk_partidajugador_jugador` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`id`),
  CONSTRAINT `fk_partidajugador_partida` FOREIGN KEY (`partida_id`) REFERENCES `partida` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida_jugador`
--

LOCK TABLES `partida_jugador` WRITE;
/*!40000 ALTER TABLE `partida_jugador` DISABLE KEYS */;
INSERT INTO `partida_jugador` VALUES (48,29,14,1,0,0,1,1),(49,29,15,0,0,1,1,1),(50,30,14,0,0,0,1,1),(51,30,15,1,0,0,1,1);
/*!40000 ALTER TABLE `partida_jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida_ronda`
--

DROP TABLE IF EXISTS `partida_ronda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida_ronda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `partida_id` int NOT NULL,
  `numero_ronda` int DEFAULT NULL,
  `partida_jugador_uno_id` int DEFAULT NULL,
  `puntaje_jugador_uno` int DEFAULT NULL,
  `partida_jugador_dos_id` int DEFAULT NULL,
  `puntaje_jugador_dos` int DEFAULT NULL,
  `empate` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partida_ronda_partida` (`partida_id`),
  KEY `fk_partida_ronda_con_partida_jugador_uno` (`partida_jugador_uno_id`),
  KEY `fk_partida_ronda_con_partida_jugador_dos` (`partida_jugador_dos_id`),
  CONSTRAINT `fk_partida_ronda_con_partida_jugador_dos` FOREIGN KEY (`partida_jugador_dos_id`) REFERENCES `partida_jugador` (`id`),
  CONSTRAINT `fk_partida_ronda_con_partida_jugador_uno` FOREIGN KEY (`partida_jugador_uno_id`) REFERENCES `partida_jugador` (`id`),
  CONSTRAINT `fk_partida_ronda_partida` FOREIGN KEY (`partida_id`) REFERENCES `partida` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida_ronda`
--

LOCK TABLES `partida_ronda` WRITE;
/*!40000 ALTER TABLE `partida_ronda` DISABLE KEYS */;
INSERT INTO `partida_ronda` VALUES (9,29,1,49,5,48,0,0),(13,29,2,48,0,49,0,1),(14,30,1,50,0,51,0,1),(15,30,2,50,0,51,0,1);
/*!40000 ALTER TABLE `partida_ronda` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-25 22:42:44
