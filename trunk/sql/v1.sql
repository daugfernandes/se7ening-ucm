-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51a-24+lenny4


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema S7UCM
--

-- CREATE DATABASE IF NOT EXISTS S7UCM;
USE S7UCM;

--
-- Definition of table `S7UCM`.`CIENTIFICAREA`
--

-- DROP TABLE IF EXISTS `S7UCM`.`CIENTIFICAREA`;
CREATE TABLE  `S7UCM`.`CIENTIFICAREA` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `S7UCM`.`CIENTIFICAREA`
--

/*!40000 ALTER TABLE `CIENTIFICAREA` DISABLE KEYS */;
LOCK TABLES `CIENTIFICAREA` WRITE;
INSERT INTO `S7UCM`.`CIENTIFICAREA` VALUES  (1,'** área científica desconhecida');
UNLOCK TABLES;
/*!40000 ALTER TABLE `CIENTIFICAREA` ENABLE KEYS */;


--
-- Definition of table `S7UCM`.`CLASS`
--

--  DROP TABLE IF EXISTS `S7UCM`.`CLASS`;
CREATE TABLE  `S7UCM`.`CLASS` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `CODE` varchar(20) default NULL,
  `NAME` varchar(255) NOT NULL,
  `ID_DEPARTMENT` int(10) unsigned NOT NULL,
  `ID_TEACHER` int(10) unsigned NOT NULL,
  `CREDITS` int(10) unsigned NOT NULL default '0',
  `CONTACT_HOURS` int(10) unsigned default '0',
  `WORK_HOURS` int(10) unsigned NOT NULL default '0',
  `WEBPAGE` varchar(255) NOT NULL,
  `SINOPSE` longtext,
  `ID_CIENTIFICAREA` int(10) unsigned NOT NULL,
  `CONTENT` longtext,
  `ID_TUTOR` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`ID`),
  KEY `FK_UC_DEPARTMENT` (`ID_DEPARTMENT`),
  KEY `FK_UC_TEACHER` (`ID_TEACHER`),
  KEY `FK_UC_CIENTIFICAREA` (`ID_CIENTIFICAREA`),
  KEY `FK_UC_TUTOR` (`ID_TUTOR`),
  CONSTRAINT `FK_UC_CIENTIFICAREA` FOREIGN KEY (`ID_CIENTIFICAREA`) REFERENCES `CIENTIFICAREA` (`ID`),
  CONSTRAINT `FK_UC_DEPARTMENT` FOREIGN KEY (`ID_DEPARTMENT`) REFERENCES `DEPARTMENT` (`ID`),
  CONSTRAINT `FK_UC_TEACHER` FOREIGN KEY (`ID_TEACHER`) REFERENCES `TEACHER` (`ID`),
  CONSTRAINT `FK_UC_TUTOR` FOREIGN KEY (`ID_TUTOR`) REFERENCES `TEACHER` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `S7UCM`.`CLASS`
--

/*!40000 ALTER TABLE `CLASS` DISABLE KEYS */;
LOCK TABLES `CLASS` WRITE;
INSERT INTO `S7UCM`.`CLASS` VALUES  (1,'21002','Álgebra Linear',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=15','',1,'',1),
 (2,'21010','Arquitectura de Computadores',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=35','',1,'',1),
 (3,'21030','Elementos de Análise Infinitesimal I',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=81','',1,'',1),
 (4,'21090','Programação',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=286','',1,'',1),
 (5,'21110','Sistemas Multimédia',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=319','',1,'',1),
 (6,'21007','Análise de Sistemas',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=20','',1,'',1),
 (7,'21037','Elementos de Probabilidades e Estatística',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=88','',1,'',1),
 (8,'21082','Matemática Finita',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=254','',1,'',1),
 (9,'21093','Programação por Objectos',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=289','',1,'',1),
 (10,'21111','Sistemas Operativos',1,1,6,0,0,'http://www.univ-ab.pt/students/guia/detail_uc.php?uc=320','',1,'',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `CLASS` ENABLE KEYS */;


--
-- Definition of table `S7UCM`.`DEPARTMENT`
--

-- DROP TABLE IF EXISTS `S7UCM`.`DEPARTMENT`;
CREATE TABLE  `S7UCM`.`DEPARTMENT` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `NAME` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `S7UCM`.`DEPARTMENT`
--

/*!40000 ALTER TABLE `DEPARTMENT` DISABLE KEYS */;
LOCK TABLES `DEPARTMENT` WRITE;
INSERT INTO `S7UCM`.`DEPARTMENT` VALUES  (1,'** departamento desconhecido'),
 (2,'Departamento de Ciências e Tecnologia');
UNLOCK TABLES;
/*!40000 ALTER TABLE `DEPARTMENT` ENABLE KEYS */;


--
-- Definition of table `S7UCM`.`TEACHER`
--

-- DROP TABLE IF EXISTS `S7UCM`.`TEACHER`;
CREATE TABLE  `S7UCM`.`TEACHER` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `NAME` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PHONE` varchar(32) NOT NULL,
  `ID_CIENTIFICAREA` int(10) unsigned NOT NULL,
  `SCHEDULE` varchar(255) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_TEACHER_AREA` (`ID_CIENTIFICAREA`),
  CONSTRAINT `FK_TEACHER_AREA` FOREIGN KEY (`ID_CIENTIFICAREA`) REFERENCES `CIENTIFICAREA` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `S7UCM`.`TEACHER`
--

/*!40000 ALTER TABLE `TEACHER` DISABLE KEYS */;
LOCK TABLES `TEACHER` WRITE;
INSERT INTO `S7UCM`.`TEACHER` VALUES  (1,'*** desconhecido','','',1,'');
UNLOCK TABLES;
/*!40000 ALTER TABLE `TEACHER` ENABLE KEYS */;


--
-- Definition of function `S7UCM`.`CIENTIFICAREA_ADD`
--

-- DROP FUNCTION IF EXISTS `S7UCM`.`CIENTIFICAREA_ADD`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`CIENTIFICAREA_ADD`(name varchar(255)) RETURNS int(11)
begin

	declare RESULT int;

	INSERT INTO CIENTIFICAREA(NAME) VALUES(name);
	SELECT LAST_INSERT_ID() into RESULT;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`CIENTIFICAREA_DEL`
--

DROP FUNCTION IF EXISTS `S7UCM`.`CIENTIFICAREA_DEL`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`CIENTIFICAREA_DEL`(p_id INT) RETURNS int(11)
begin

	declare RESULT int;

	/* TODO: must exist a way to do this properly ("AFECTED ROWS?!?!?") */
	SELECT COUNT(*) INTO RESULT FROM CIENTIFICAREA WHERE ID=p_id;

	DELETE FROM CIENTIFICAREA WHERE ID=p_id;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`CIENTIFICAREA_UPD`
--

DROP FUNCTION IF EXISTS `S7UCM`.`CIENTIFICAREA_UPD`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`CIENTIFICAREA_UPD`(name varchar(255), p_id INT) RETURNS int(11)
begin

	declare RESULT int;

	/* TODO: must exist a way to do this properly ("AFECTED ROWS?!?!?") */
	SELECT COUNT(*) INTO RESULT FROM CIENTIFICAREA WHERE ID=p_id;

	UPDATE CIENTIFICAREA SET NAME=name WHERE ID=p_id;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`DEPARTMENT_ADD`
--

DROP FUNCTION IF EXISTS `S7UCM`.`DEPARTMENT_ADD`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`DEPARTMENT_ADD`(name varchar(255)) RETURNS int(11)
begin

	declare RESULT int;

	INSERT INTO DEPARTMENT(NAME) VALUES(name);
	SELECT LAST_INSERT_ID() into RESULT;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`DEPARTMENT_DEL`
--

DROP FUNCTION IF EXISTS `S7UCM`.`DEPARTMENT_DEL`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`DEPARTMENT_DEL`(p_id INT) RETURNS int(11)
begin

	declare RESULT int;

	/* TODO: must exist a way to do this properly ("AFECTED ROWS?!?!?") */
	SELECT COUNT(*) INTO RESULT FROM DEPARTMENT WHERE ID=p_id;

	DELETE FROM DEPARTMENT WHERE ID=p_id;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`DEPARTMENT_UPD`
--

DROP FUNCTION IF EXISTS `S7UCM`.`DEPARTMENT_UPD`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`DEPARTMENT_UPD`(name varchar(255), p_id INT) RETURNS int(11)
begin

	declare RESULT int;

	/* TODO: must exist a way to do this properly ("AFECTED ROWS?!?!?") */
	SELECT COUNT(*) INTO RESULT FROM DEPARTMENTO WHERE ID=p_id;

	UPDATE DEPARTMENT SET NAME=name WHERE ID=p_id;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`TEACHER_ADD`
--

DROP FUNCTION IF EXISTS `S7UCM`.`TEACHER_ADD`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`TEACHER_ADD`(name varchar(255), email varchar(255), phone varchar(32), id_cientificarea int, schedule varchar(255)) RETURNS int(11)
begin

	declare RESULT int;

	INSERT INTO TEACHER(NAME,EMAIL,PHONE,ID_CIENTIFICAREA,SCHEDULE) VALUES(name,email,phone,id_cientificarea,schedule);
	SELECT LAST_INSERT_ID() into RESULT;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`TEACHER_DEL`
--

DROP FUNCTION IF EXISTS `S7UCM`.`TEACHER_DEL`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`TEACHER_DEL`(p_id INT) RETURNS int(11)
begin

	declare RESULT int;

	/* TODO: must exist a way to do this properly ("AFECTED ROWS?!?!?") */
	SELECT COUNT(*) INTO RESULT FROM TEACHER WHERE ID=p_id;

	DELETE FROM TEACHER WHERE ID=p_id;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `S7UCM`.`TEACHER_UPD`
--

DROP FUNCTION IF EXISTS `S7UCM`.`TEACHER_UPD`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`s7ucm`@`localhost` FUNCTION  `S7UCM`.`TEACHER_UPD`(name varchar(255), email varchar(255), phone varchar(32), id_cientificarea int, schedule varchar(255), p_id INT) RETURNS int(11)
begin

	declare RESULT int;

	/* TODO: there must be a way to do this properly ("AFECTED ROWS?!?!?") */
	SELECT COUNT(*) INTO RESULT FROM TEACHER WHERE ID=p_id;

	UPDATE TEACHER SET NAME=name, EMAIL=email, PHONE=phone, ID_CIENTIFICAREA=id_cientificarea, SCHEDULE=schedule WHERE ID=p_id;

        RETURN RESULT;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
