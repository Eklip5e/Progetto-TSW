-- MySQL dump 10.13  Distrib 9.3.0, for Win64 (x86_64)
--
-- Host: localhost    Database: unigame
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- Table structure for table `carrello`
--

DROP TABLE IF EXISTS `carrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrello` (
  `idUtente` int NOT NULL,
  `idVideogioco` int NOT NULL,
  PRIMARY KEY (`idUtente`),
  KEY `idVideogioco` (`idVideogioco`),
  CONSTRAINT `carrello_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `utente` (`IDUtente`),
  CONSTRAINT `carrello_ibfk_2` FOREIGN KEY (`idVideogioco`) REFERENCES `videogioco` (`idVideogioco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrello`
--

LOCK TABLES `carrello` WRITE;
/*!40000 ALTER TABLE `carrello` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chiave`
--

DROP TABLE IF EXISTS `chiave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chiave` (
  `GameKey` varchar(15) NOT NULL,
  `Venduto` tinyint(1) DEFAULT '0',
  `Riscattato` tinyint(1) DEFAULT '0',
  `IDVideogioco` int NOT NULL,
  PRIMARY KEY (`GameKey`),
  KEY `IDVideogioco` (`IDVideogioco`),
  CONSTRAINT `chiave_ibfk_1` FOREIGN KEY (`IDVideogioco`) REFERENCES `videogioco` (`idVideogioco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chiave`
--

LOCK TABLES `chiave` WRITE;
/*!40000 ALTER TABLE `chiave` DISABLE KEYS */;
/*!40000 ALTER TABLE `chiave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contiene`
--

DROP TABLE IF EXISTS `contiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contiene` (
  `Quantita` int DEFAULT '1',
  `DataInserimento` date DEFAULT NULL,
  `Bloccato` tinyint(1) DEFAULT '0',
  `IDGioco` int DEFAULT NULL,
  `IDUser` int DEFAULT NULL,
  `Totale` decimal(5,2) DEFAULT NULL,
  KEY `IDGioco` (`IDGioco`),
  KEY `IDUser` (`IDUser`),
  CONSTRAINT `contiene_ibfk_1` FOREIGN KEY (`IDGioco`) REFERENCES `videogioco` (`idVideogioco`),
  CONSTRAINT `contiene_ibfk_2` FOREIGN KEY (`IDUser`) REFERENCES `carrello` (`idUtente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contiene`
--

LOCK TABLES `contiene` WRITE;
/*!40000 ALTER TABLE `contiene` DISABLE KEYS */;
/*!40000 ALTER TABLE `contiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daticarta`
--

DROP TABLE IF EXISTS `daticarta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daticarta` (
  `NumCarta` varchar(16) NOT NULL,
  `DataScadenza` date NOT NULL,
  `CVV` int NOT NULL,
  PRIMARY KEY (`NumCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daticarta`
--

LOCK TABLES `daticarta` WRITE;
/*!40000 ALTER TABLE `daticarta` DISABLE KEYS */;
/*!40000 ALTER TABLE `daticarta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fattura`
--

DROP TABLE IF EXISTS `fattura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fattura` (
  `IDFatturazione` int NOT NULL AUTO_INCREMENT,
  `IDUser` int NOT NULL,
  `Via` varchar(40) NOT NULL,
  `CAP` int NOT NULL,
  `Citta` varchar(40) NOT NULL,
  `DataAcquisto` date NOT NULL,
  PRIMARY KEY (`IDFatturazione`),
  KEY `IDUser` (`IDUser`),
  CONSTRAINT `fattura_ibfk_1` FOREIGN KEY (`IDUser`) REFERENCES `utente` (`IDUtente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fattura`
--

LOCK TABLES `fattura` WRITE;
/*!40000 ALTER TABLE `fattura` DISABLE KEYS */;
/*!40000 ALTER TABLE `fattura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fatturazione`
--

DROP TABLE IF EXISTS `fatturazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fatturazione` (
  `IDUser` int DEFAULT NULL,
  `IDFatturazione` int DEFAULT NULL,
  KEY `IDUser` (`IDUser`),
  KEY `IDFatturazione` (`IDFatturazione`),
  CONSTRAINT `fatturazione_ibfk_1` FOREIGN KEY (`IDUser`) REFERENCES `utente` (`IDUtente`),
  CONSTRAINT `fatturazione_ibfk_2` FOREIGN KEY (`IDFatturazione`) REFERENCES `fattura` (`IDFatturazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fatturazione`
--

LOCK TABLES `fatturazione` WRITE;
/*!40000 ALTER TABLE `fatturazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `fatturazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genere`
--

DROP TABLE IF EXISTS `genere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genere` (
  `IDGenere` int NOT NULL AUTO_INCREMENT,
  `Genere` varchar(30) NOT NULL,
  `Descrizione` text NOT NULL,
  PRIMARY KEY (`IDGenere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genere`
--

LOCK TABLES `genere` WRITE;
/*!40000 ALTER TABLE `genere` DISABLE KEYS */;
/*!40000 ALTER TABLE `genere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `img`
--

DROP TABLE IF EXISTS `img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `img` (
  `Copertina` varchar(255) NOT NULL,
  `Banner` varchar(255) NOT NULL,
  `Video` varchar(255) NOT NULL,
  `IDVideogioco` int NOT NULL,
  PRIMARY KEY (`Copertina`),
  KEY `IDVideogioco` (`IDVideogioco`),
  CONSTRAINT `img_ibfk_1` FOREIGN KEY (`IDVideogioco`) REFERENCES `videogioco` (`idVideogioco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `img`
--

LOCK TABLES `img` WRITE;
/*!40000 ALTER TABLE `img` DISABLE KEYS */;
/*!40000 ALTER TABLE `img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentmethod`
--

DROP TABLE IF EXISTS `paymentmethod`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentmethod` (
  `IDUser` int DEFAULT NULL,
  `NumCarta` varchar(16) DEFAULT NULL,
  KEY `IDUser` (`IDUser`),
  KEY `NumCarta` (`NumCarta`),
  CONSTRAINT `paymentmethod_ibfk_1` FOREIGN KEY (`IDUser`) REFERENCES `utente` (`IDUtente`),
  CONSTRAINT `paymentmethod_ibfk_2` FOREIGN KEY (`NumCarta`) REFERENCES `daticarta` (`NumCarta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentmethod`
--

LOCK TABLES `paymentmethod` WRITE;
/*!40000 ALTER TABLE `paymentmethod` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentmethod` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rigacarrello`
--

DROP TABLE IF EXISTS `rigacarrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rigacarrello` (
  `idRiga` int NOT NULL AUTO_INCREMENT,
  `idUtente` int NOT NULL,
  `idVideogioco` int NOT NULL,
  PRIMARY KEY (`idRiga`),
  KEY `idUtente` (`idUtente`),
  KEY `idVideogioco` (`idVideogioco`),
  CONSTRAINT `rigacarrello_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `utente` (`IDUtente`),
  CONSTRAINT `rigacarrello_ibfk_2` FOREIGN KEY (`idVideogioco`) REFERENCES `videogioco` (`idVideogioco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rigacarrello`
--

LOCK TABLES `rigacarrello` WRITE;
/*!40000 ALTER TABLE `rigacarrello` DISABLE KEYS */;
/*!40000 ALTER TABLE `rigacarrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `IDUtente` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) DEFAULT NULL,
  `Nome` varchar(50) NOT NULL,
  `Cognome` varchar(50) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Propic` varchar(255) DEFAULT '/img/user/defaultUser.png',
  `IsAdmin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDUtente`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Username` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Eklip5e','Aniello','Del Prete','an86rty@gmail.com','cf720b7d9557243a0974b2226e260119554dae1f','img/user/userPropic/Eklip5e.png',0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vg`
--

DROP TABLE IF EXISTS `vg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vg` (
  `IDGenere` int NOT NULL,
  `IDVideogioco` int NOT NULL,
  PRIMARY KEY (`IDGenere`,`IDVideogioco`),
  KEY `IDVideogioco` (`IDVideogioco`),
  CONSTRAINT `vg_ibfk_1` FOREIGN KEY (`IDGenere`) REFERENCES `genere` (`IDGenere`),
  CONSTRAINT `vg_ibfk_2` FOREIGN KEY (`IDVideogioco`) REFERENCES `videogioco` (`idVideogioco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vg`
--

LOCK TABLES `vg` WRITE;
/*!40000 ALTER TABLE `vg` DISABLE KEYS */;
/*!40000 ALTER TABLE `vg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videogioco`
--

DROP TABLE IF EXISTS `videogioco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videogioco` (
  `idVideogioco` int NOT NULL AUTO_INCREMENT,
  `titolo` varchar(70) NOT NULL,
  `piattaforma` varchar(25) NOT NULL,
  `releaseDate` date NOT NULL,
  `descrizione` text,
  `produttore` varchar(50) NOT NULL,
  `appIdSteam` int NOT NULL,
  `prezzo` decimal(5,2) NOT NULL,
  `sconto` int DEFAULT NULL,
  PRIMARY KEY (`idVideogioco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videogioco`
--

LOCK TABLES `videogioco` WRITE;
/*!40000 ALTER TABLE `videogioco` DISABLE KEYS */;
/*!40000 ALTER TABLE `videogioco` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-17 14:03:37
