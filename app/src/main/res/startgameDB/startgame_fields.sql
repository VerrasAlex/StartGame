CREATE DATABASE  IF NOT EXISTS `startgame` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `startgame`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: startgame
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `fields`
--

DROP TABLE IF EXISTS `fields`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fields` (
  `type` int NOT NULL,
  `capacity` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `company` varchar(45) NOT NULL,
  `cost_per_hour` varchar(45) NOT NULL,
  `site_url` varchar(150) DEFAULT NULL,
  `picture_url` varchar(300) NOT NULL DEFAULT 'https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fields`
--

LOCK TABLES `fields` WRITE;
/*!40000 ALTER TABLE `fields` DISABLE KEYS */;
INSERT INTO `fields` VALUES (0,'5','Athens','aleo fc','45','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'5','Athens','aleo fc','45','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'5','Athens','aleo fc','45','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'5','Athens','aleo fc','45','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'7','Athens','aleo fc','70','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'7','Athens','aleo fc','70','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'8','Athens','aleo fc','80','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'10','Athens','aleo fc','100','http://www.aleofc.gr/','https://www.athlespot.com/wp-content/uploads/2018/03/400623_698041786883745_1112855617_n-e1540055612154.jpg'),(0,'5','Athens','renti soccer club','40','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'5','Athens','renti soccer club','40','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'5','Athens','renti soccer club','40','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'5','Athens','renti soccer club','40','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'5','Athens','renti soccer club','40','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'7','Athens','renti soccer club','60','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'7','Athens','renti soccer club','60','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'7','Athens','renti soccer club','60','https://www.rentisoccerclub.gr/soccer/','https://www.rentisoccerclub.gr/soccer/wp-content/media/Renti-Soccer-Logo-JPS-w80.png'),(0,'5','Athens','milon mini soccer','40','https://milon-mini-soccer.business.site/','https://4.bp.blogspot.com/-mR_GejBDWq4/XOumH73zGOI/AAAAAAAAIW4/6G9cRDn8OfYKeh9jFfhJthoJNsr44rufgCK4BGAYYCw/s1600/536942_558776220842871_639283013_n.jpg'),(0,'5','Athens','milon mini soccer','40','https://milon-mini-soccer.business.site/','https://4.bp.blogspot.com/-mR_GejBDWq4/XOumH73zGOI/AAAAAAAAIW4/6G9cRDn8OfYKeh9jFfhJthoJNsr44rufgCK4BGAYYCw/s1600/536942_558776220842871_639283013_n.jpg'),(0,'7','Athens','milon mini soccer','60','https://milon-mini-soccer.business.site/','https://4.bp.blogspot.com/-mR_GejBDWq4/XOumH73zGOI/AAAAAAAAIW4/6G9cRDn8OfYKeh9jFfhJthoJNsr44rufgCK4BGAYYCw/s1600/536942_558776220842871_639283013_n.jpg'),(0,'8','Athens','milon mini soccer','70','https://milon-mini-soccer.business.site/','https://4.bp.blogspot.com/-mR_GejBDWq4/XOumH73zGOI/AAAAAAAAIW4/6G9cRDn8OfYKeh9jFfhJthoJNsr44rufgCK4BGAYYCw/s1600/536942_558776220842871_639283013_n.jpg'),(0,'8','Athens','milon mini soccer','70','https://milon-mini-soccer.business.site/','https://4.bp.blogspot.com/-mR_GejBDWq4/XOumH73zGOI/AAAAAAAAIW4/6G9cRDn8OfYKeh9jFfhJthoJNsr44rufgCK4BGAYYCw/s1600/536942_558776220842871_639283013_n.jpg'),(0,'8','Athens','milon mini soccer','70','https://milon-mini-soccer.business.site/','https://4.bp.blogspot.com/-mR_GejBDWq4/XOumH73zGOI/AAAAAAAAIW4/6G9cRDn8OfYKeh9jFfhJthoJNsr44rufgCK4BGAYYCw/s1600/536942_558776220842871_639283013_n.jpg'),(0,'0','Athens','basketmania','50','',''),(0,'0','Athens','basketmania','50','',''),(1,'0','Athens','basketmania','70','',''),(0,'5','Thesalonniki','soccerino','60','',''),(0,'5','Thesalonniki','soccerino','60','',''),(0,'5','Thesalonniki','soccerino','60','',''),(0,'5','Thesalonniki','soccerino','60','',''),(0,'7','Thesalonniki','soccerino','80','',''),(0,'0','Thesalonniki','kalathi','50','',''),(0,'0','Thesalonniki','kalathi','50','',''),(1,'0','Athens','aleo fc','60','',''),(0,'10','Athens','aleo fc','100','',''),(0,'10','Athens','aleo fc','100','',''),(0,'10','Athens','aleo fc','100','',''),(0,'10','Athens','aleo fc','100','',''),(0,'10','Athens','aleo fc','100','',''),(0,'10','Athens','aleo fc','100','',''),(0,'0','Athens','basketmania','50','',''),(0,'10','Athens','aleo fc','100','',''),(0,'0','Athens','Basketoupolis','60','',''),(0,'5','Athens','Kickball','40','',''),(0,'5','Athens','Kickball','40','',''),(1,'0','Thesalonniki','Bbb','65','',''),(0,'8','Thesalonniki','Footballeria','55','',''),(0,'8','Thesalonniki','Soccerino','70','',''),(1,'0','Thesalonniki','kalathi','70','',''),(0,'0','Athens','Bbb','50','',''),(0,'5','Athens','aleo fc','50','',''),(0,'0','Athens','p3150010','50','',''),(0,'7','Athens','Kickball','70','',''),(0,'7','Athens','Kickball','70','',''),(0,'0','Athens','Basketoupolis','60','','');
/*!40000 ALTER TABLE `fields` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-04 18:55:28
