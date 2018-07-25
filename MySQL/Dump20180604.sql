-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `doc`
--

DROP TABLE IF EXISTS `doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doc` (
  `iddoc` int(11) NOT NULL AUTO_INCREMENT,
  `idproject` int(11) NOT NULL,
  `nazvanie` varchar(45) COLLATE utf8_bin NOT NULL,
  `nomer` varchar(45) COLLATE utf8_bin NOT NULL,
  `put` varchar(150) COLLATE utf8_bin NOT NULL,
  `idtip_doc` int(11) NOT NULL,
  PRIMARY KEY (`iddoc`),
  KEY `idproject2_idx` (`idproject`),
  KEY `ididproject3_idx` (`idproject`),
  KEY `idtip_doc_idx` (`idtip_doc`),
  CONSTRAINT `ididproject3` FOREIGN KEY (`idproject`) REFERENCES `project` (`idproject`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idtip_doc` FOREIGN KEY (`idtip_doc`) REFERENCES `tip_doc` (`idtip_doc`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Все документы хранящиеся в базе';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc`
--

LOCK TABLES `doc` WRITE;
/*!40000 ALTER TABLE `doc` DISABLE KEYS */;
INSERT INTO `doc` VALUES (1,2,'Договор','01','D:\\Проекты тест\\ПШ 3200\\Сопроводительная документация\\01 Договор.docx',1),(4,2,'Спецификация','1701.3.4.1','D:\\Проекты тест\\ПШ 3200\\Проектная документация\\1701.3.4.1 Спецификация.xlsx',2);
/*!40000 ALTER TABLE `doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finance`
--

DROP TABLE IF EXISTS `finance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finance` (
  `idfinance` int(11) NOT NULL AUTO_INCREMENT,
  `idproject` int(11) NOT NULL,
  `idtip_fin` int(11) NOT NULL,
  `date` date NOT NULL,
  `summa` decimal(7,2) NOT NULL,
  `comment` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idfinance`),
  KEY `idproject_fin_idx` (`idproject`),
  KEY `idtip_fin_idx` (`idtip_fin`),
  CONSTRAINT `idproject_fin` FOREIGN KEY (`idproject`) REFERENCES `project` (`idproject`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idtip_fin` FOREIGN KEY (`idtip_fin`) REFERENCES `tip_fin` (`idtip_fin`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Информация о финансовых операциях';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finance`
--

LOCK TABLES `finance` WRITE;
/*!40000 ALTER TABLE `finance` DISABLE KEYS */;
INSERT INTO `finance` VALUES (1,2,1,'2018-04-30',2000.00,NULL),(2,2,2,'2018-04-30',6000.00,'На оплату доставки материалов'),(4,2,1,'2018-04-30',3000.00,NULL);
/*!40000 ALTER TABLE `finance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `haracteristica`
--

DROP TABLE IF EXISTS `haracteristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `haracteristica` (
  `idharacteristica` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idharacteristica`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Названия характеристик проектов';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `haracteristica`
--

LOCK TABLES `haracteristica` WRITE;
/*!40000 ALTER TABLE `haracteristica` DISABLE KEYS */;
INSERT INTO `haracteristica` VALUES (1,'Класс судна'),(2,'Разработал'),(3,'Проверил'),(4,'Утвердил');
/*!40000 ALTER TABLE `haracteristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `podzadacha`
--

DROP TABLE IF EXISTS `podzadacha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `podzadacha` (
  `idpodzadacha` int(11) NOT NULL AUTO_INCREMENT,
  `idzadacha` int(11) NOT NULL,
  `nomer` int(11) NOT NULL,
  `nazvanie` varchar(45) COLLATE utf8_bin NOT NULL,
  `idstatus_podzadachi` int(11) NOT NULL DEFAULT '1',
  `idsotrudnik` int(11) NOT NULL,
  `datapostan` date NOT NULL,
  `datazaver` date DEFAULT NULL,
  `comment` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idpodzadacha`),
  KEY `idstatus_podzadachi_idx` (`idstatus_podzadachi`),
  KEY `idsotrudnik_idx` (`idsotrudnik`),
  KEY `idzadacha_idx` (`idzadacha`),
  CONSTRAINT `idsotrudnikpodz` FOREIGN KEY (`idsotrudnik`) REFERENCES `sotrudnik` (`idsotrudnik`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idstatus_podzadachi` FOREIGN KEY (`idstatus_podzadachi`) REFERENCES `status_podzadachi` (`idstatus_podzadachi`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idzadacha` FOREIGN KEY (`idzadacha`) REFERENCES `zadacha` (`idzadacha`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Подзадачи, находящиеся в задачах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `podzadacha`
--

LOCK TABLES `podzadacha` WRITE;
/*!40000 ALTER TABLE `podzadacha` DISABLE KEYS */;
INSERT INTO `podzadacha` VALUES (1,1,1,'Назначить дату',2,2,'2018-04-24','2018-04-24','Дата проведения переговоров: 30 апреля 2018'),(3,1,2,'Подписать договор',2,2,'2018-04-24','2018-04-24',NULL),(5,1,3,'Сдать на проверку',2,2,'2018-04-28','2018-05-01','Отчет отправлен на электронную почту'),(6,2,1,'Разработать черновой вариант',2,2,'2018-05-01','2018-05-05',NULL),(7,2,2,'Сдать на проверку',2,2,'2018-05-05','2018-05-06',NULL),(8,2,3,'Оформить итоговый вариант',1,2,'2018-05-06',NULL,NULL);
/*!40000 ALTER TABLE `podzadacha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `idproject` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(45) COLLATE utf8_bin NOT NULL,
  `kod` varchar(45) COLLATE utf8_bin NOT NULL,
  `put` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `ryad` char(10) COLLATE utf8_bin DEFAULT NULL,
  `stolb` char(10) COLLATE utf8_bin DEFAULT NULL,
  `polka` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `comment` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `idstatus` int(11) NOT NULL,
  `idzakazchik` int(11) NOT NULL,
  `data` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idproject`),
  UNIQUE KEY `kod_UNIQUE` (`kod`),
  KEY `idstatus_idx` (`idstatus`),
  KEY `idzakazchik_idx` (`idzakazchik`),
  CONSTRAINT `idstatus` FOREIGN KEY (`idstatus`) REFERENCES `status` (`idstatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idzakazchik` FOREIGN KEY (`idzakazchik`) REFERENCES `zakazchik` (`idzakazchik`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Информация о проектах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (2,'Яхта \"Верфь\"','ПШ 3200','D:\\Проекты тест\\ПШ 3200',NULL,NULL,NULL,'Первый проект организации.',2,1,'2018-04-24'),(6,'Яхта \"Мечта\"','ПШ 4040','D:\\Проекты тест\\ПШ 4040',NULL,NULL,NULL,'',2,1,'2018-04-24'),(7,'Катер \"Победа\"','ПШ 7069','D:\\Проекты тест\\ПШ 7069',NULL,NULL,NULL,'',1,1,'2018-05-01'),(8,'Шхуна \"1039\"','ПШ 1039','D:\\Проекты тест\\ПШ 1039',NULL,NULL,NULL,'',1,2,'2018-05-01'),(9,'Рыболовные судно \"ПШ 5090\"','ПШ 5090','D:\\Проекты тест\\ПШ 5090',NULL,NULL,NULL,'',1,2,'2018-05-01'),(10,'Паром \"Вдохновение\"','ПШ 9683','D:\\Проекты тест\\ПШ 9683',NULL,NULL,NULL,'Проект был завершен в августе 2015г.',1,3,'2018-05-01');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_haracteristica`
--

DROP TABLE IF EXISTS `project_haracteristica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_haracteristica` (
  `idproject_haracteristica` int(11) NOT NULL AUTO_INCREMENT,
  `idharacteristica` int(11) NOT NULL,
  `idproject` int(11) NOT NULL,
  `soderjanie` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idproject_haracteristica`),
  KEY `idharacteristica_idx` (`idharacteristica`),
  KEY `idproject_har_idx` (`idproject`),
  CONSTRAINT `idharacteristica` FOREIGN KEY (`idharacteristica`) REFERENCES `haracteristica` (`idharacteristica`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idproject_har` FOREIGN KEY (`idproject`) REFERENCES `project` (`idproject`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_haracteristica`
--

LOCK TABLES `project_haracteristica` WRITE;
/*!40000 ALTER TABLE `project_haracteristica` DISABLE KEYS */;
INSERT INTO `project_haracteristica` VALUES (1,1,2,'РРР'),(4,2,2,'Иванов И.И.'),(6,4,2,'Никитин');
/*!40000 ALTER TABLE `project_haracteristica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sotrudnik`
--

DROP TABLE IF EXISTS `sotrudnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sotrudnik` (
  `idsotrudnik` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `fam` varchar(45) COLLATE utf8_bin NOT NULL,
  `otch` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idsotrudnik`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Таблица, содержащая информацию о сотрудниках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sotrudnik`
--

LOCK TABLES `sotrudnik` WRITE;
/*!40000 ALTER TABLE `sotrudnik` DISABLE KEYS */;
INSERT INTO `sotrudnik` VALUES (1,'Дарья','Пилясова','Дмитриевна'),(2,'Иван','Иванов','Иванович'),(3,'Петр','Петров','Сергеевич');
/*!40000 ALTER TABLE `sotrudnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `idstatus` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Статусы проекта';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Завершен'),(2,'В работе');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_podzadachi`
--

DROP TABLE IF EXISTS `status_podzadachi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_podzadachi` (
  `idstatus_podzadachi` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idstatus_podzadachi`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_podzadachi`
--

LOCK TABLES `status_podzadachi` WRITE;
/*!40000 ALTER TABLE `status_podzadachi` DISABLE KEYS */;
INSERT INTO `status_podzadachi` VALUES (1,'Поставлена'),(2,'Завершена');
/*!40000 ALTER TABLE `status_podzadachi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_doc`
--

DROP TABLE IF EXISTS `tip_doc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tip_doc` (
  `idtip_doc` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idtip_doc`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Таблица типов документа';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_doc`
--

LOCK TABLES `tip_doc` WRITE;
/*!40000 ALTER TABLE `tip_doc` DISABLE KEYS */;
INSERT INTO `tip_doc` VALUES (1,'Сопроводительная документация'),(2,'Проектная документация');
/*!40000 ALTER TABLE `tip_doc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_fin`
--

DROP TABLE IF EXISTS `tip_fin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tip_fin` (
  `idtip_fin` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idtip_fin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Отображает информацию о типе финансовой операции';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_fin`
--

LOCK TABLES `tip_fin` WRITE;
/*!40000 ALTER TABLE `tip_fin` DISABLE KEYS */;
INSERT INTO `tip_fin` VALUES (1,'Приход'),(2,'Расход');
/*!40000 ALTER TABLE `tip_fin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tip_user`
--

DROP TABLE IF EXISTS `tip_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tip_user` (
  `id_tip` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_tip`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Типы пользователей';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tip_user`
--

LOCK TABLES `tip_user` WRITE;
/*!40000 ALTER TABLE `tip_user` DISABLE KEYS */;
INSERT INTO `tip_user` VALUES (1,'администратор'),(2,'инженер'),(3,'руководитель');
/*!40000 ALTER TABLE `tip_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) COLLATE utf8_bin NOT NULL,
  `pass` varchar(100) COLLATE utf8_bin NOT NULL,
  `idsotrudnik` int(11) NOT NULL,
  `id_tip` int(11) NOT NULL,
  PRIMARY KEY (`iduser`),
  KEY `idsotrudnik_idx` (`idsotrudnik`),
  KEY `id_tip_idx` (`id_tip`),
  CONSTRAINT `id_tip` FOREIGN KEY (`id_tip`) REFERENCES `tip_user` (`id_tip`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idsotrudnik` FOREIGN KEY (`idsotrudnik`) REFERENCES `sotrudnik` (`idsotrudnik`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Таблица, содержащая информацию о пользователях';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'daria','daria',1,1),(2,'ivan','ivan',2,3),(3,'petr','petr',3,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zadacha`
--

DROP TABLE IF EXISTS `zadacha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zadacha` (
  `idzadacha` int(11) NOT NULL AUTO_INCREMENT,
  `idproject` int(11) NOT NULL,
  `nomer` int(11) NOT NULL,
  `nazvanie` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idzadacha`),
  KEY `idproject_idx` (`idproject`),
  CONSTRAINT `idprojectzad` FOREIGN KEY (`idproject`) REFERENCES `project` (`idproject`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Задачи верхнего уровня, находящиеся в проекте';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zadacha`
--

LOCK TABLES `zadacha` WRITE;
/*!40000 ALTER TABLE `zadacha` DISABLE KEYS */;
INSERT INTO `zadacha` VALUES (1,2,1,'Провести переговоры с заказчиком'),(2,2,2,'Разработать ТЗ'),(5,2,3,'Разработать технический проект'),(6,2,4,'Получить оплату за разработанный проект');
/*!40000 ALTER TABLE `zadacha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zakazchik`
--

DROP TABLE IF EXISTS `zakazchik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zakazchik` (
  `idzakazchik` int(11) NOT NULL AUTO_INCREMENT,
  `nazvanie` varchar(100) COLLATE utf8_bin NOT NULL,
  `yr_adres` varchar(100) COLLATE utf8_bin NOT NULL,
  `inn` char(12) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idzakazchik`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Таблица, содержащая информацию о заказчиках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zakazchik`
--

LOCK TABLES `zakazchik` WRITE;
/*!40000 ALTER TABLE `zakazchik` DISABLE KEYS */;
INSERT INTO `zakazchik` VALUES (1,'ООО \"ССА\"','Архангельская обл., г. Северодвинск, ул. Ленина, офис 45','470298463718'),(2,'ООО \"Звезда\"','Архангельская обл. г. Северодвинск, пр. Ленина, д. 58','385039857463'),(3,'ООО \"Новые технологии\"','Архангельская обл. г. Архангельск, пр. Ленина, д. 60','583095827452');
/*!40000 ALTER TABLE `zakazchik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'project'
--

--
-- Dumping routines for database 'project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-04 13:33:59
