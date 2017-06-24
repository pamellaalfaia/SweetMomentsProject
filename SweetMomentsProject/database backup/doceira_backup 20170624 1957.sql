-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.12-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema doceira
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ doceira;
USE doceira;

--
-- Table structure for table `doceira`.`cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL DEFAULT '',
  `telefone` varchar(45) NOT NULL DEFAULT '',
  `email` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `doceira`.`cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id`,`nome`,`telefone`,`email`) VALUES 
 (12,'popwo','(22)123456789','pamella.alfaia@vale.com'),
 (13,'popopo','(22)123456789','pamella.alfaia@vale.com'),
 (14,'kshkhx','(22)123456789','pamella.alfaia@vale.com'),
 (15,'skns','(22)123456789','pamella.alfaia@vale.com'),
 (16,'sndsmdn','(22)123456789','pamella.alfaia@vale.com'),
 (17,'skjdk','(22)123456789','pamella.alfaia@vale.com'),
 (18,'sk','(22)123456789','pamella.alfaia@vale.com'),
 (19,'popo','(22)123456789','pamella.alfaia@vale.com'),
 (22,'kjsdkteste','(22)123456789','pamella.alfaia@vale.com'),
 (23,'dnteste','(22)123456789','pamella.alfaia@vale.com'),
 (24,'dmn','(22)123456789','pamella.alfaia@vale.com'),
 (25,'lk','(22)123456789','pamella.alfaia@vale.com'),
 (26,'popopopol','(22)123456789','pamella.alfaia@vale.com'),
 (27,'teste27','(22)123456789','pamella.alfaia@vale.com'),
 (29,'pamella','(22)123456789','pamella.alfaia@vale.com'),
 (30,'smn','(22)123456789','pamella.alfaia@vale.com'),
 (31,'cmncn','(22)123456789','pamella.alfaia@vale.com'),
 (32,'Pamella','(22)123456789','pamella.alfaia@vale.com');
INSERT INTO `cliente` (`id`,`nome`,`telefone`,`email`) VALUES 
 (33,'pmzinh','(22)123456789','mdnsmn'),
 (34,'Maria da Penha','(22)123456789','mariadapenha@gmail.com'),
 (35,'pamella','(22)123456789','pamella@pamella'),
 (36,'vamos lá quiridinha','(22)123456789','eee@aaj'),
 (37,'teste37','(22)123456789','email'),
 (38,'dancing in the dark','(22)123456789','pamella@pamelal'),
 (40,'trabalho','(22)123456789','kjrek'),
 (41,'kdkdjf','(22)123456789','dejhdj'),
 (42,'jsh','(22)123456789','929478'),
 (43,'jdkc','(22)123456789','pamella@pamella'),
 (44,'l','(22)123456789','m'),
 (45,'palelm','(22)123456789','dim'),
 (46,'kj','(22)123456789','kjk'),
 (47,'Pamella','(22)123456789','psmlls'),
 (48,'pamel','(22)','pamella.alfaia@pamella.com'),
 (49,'Pammme','(22)','pamella'),
 (50,'Pamella50','(50)','pamella50@pamella50'),
 (51,'maria da penha','(22)','maria@maria'),
 (52,'pamella','(22)','aondkdn'),
 (54,'nome perfeito','(21)','telefone n tá perfeito'),
 (55,'cadastronome','(22)','cadastromeail@'),
 (56,'etdf,m','(21)','fdmnmf');
INSERT INTO `cliente` (`id`,`nome`,`telefone`,`email`) VALUES 
 (57,'nmnmn','(12)','nmnmn'),
 (58,'msnmsncteste58','(99)','teste58'),
 (59,'Maria da Penha','(99)','mariadapenha@maria'),
 (60,'fdrrrrreeeeeeeeeeeeee','(21)','sdddddddddddd');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Table structure for table `doceira`.`endereco`
--

DROP TABLE IF EXISTS `endereco`;
CREATE TABLE `endereco` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idCliente` int(10) unsigned NOT NULL DEFAULT '0',
  `descricao` varchar(45) NOT NULL DEFAULT '',
  `logradouro` varchar(45) NOT NULL DEFAULT '',
  `numero` int(10) unsigned NOT NULL DEFAULT '0',
  `complemento` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) NOT NULL DEFAULT '',
  `tempoMedioEntrega` int(10) unsigned NOT NULL DEFAULT '0',
  `tipoDeLogradouro` varchar(45) NOT NULL DEFAULT '',
  `distanciaEntrega` float NOT NULL DEFAULT '0',
  `custoEntrega` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `doceira`.`endereco`
--

/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` (`id`,`idCliente`,`descricao`,`logradouro`,`numero`,`complemento`,`bairro`,`tempoMedioEntrega`,`tipoDeLogradouro`,`distanciaEntrega`,`custoEntrega`) VALUES 
 (1,29,'sm','sm29',222,'sjm','sm29',111,'Rua',32.443,32.443),
 (1,30,'snd','sdmn',3,'dsn33','dskm',3,'Rua',32.443,32.443),
 (1,31,'ckn','ck',33,'ckd','ckdn',33,'Rua',32.443,32.443),
 (1,32,'Descricao endereco Pamella','Madagascar',346,'Segundo andar','Parque Columbia',46,'Rua',36.741,9.18525),
 (1,33,'dnfdescri','das Americas',500,'complemento barra apartamento','Barra da Tijuca',30,'Avenida',17.966,4.4915),
 (1,34,'maria da penha descricao','madagascar',346,'apenas para complementar','Parque Columbia',46,'Rua',36.741,9.18525),
 (1,36,'descricao6060','Automovel Clube',500,'complementei','são joão de meriti',50,'Avenida',37.789,9.44725),
 (1,54,'descricao perf','Automóvel Clube',500,'miriri','São João de Meriti',50,'Avenida',37.79,9.45),
 (1,55,'cadastro endereco 1','madagascar',346,'pav','Parque Columbia',46,'Rua',36.74,9.19),
 (1,57,'ekfknknknek','tom jobim',0,'knnkn','ilha',47,'Aeroporto',42.76,10.69);
INSERT INTO `endereco` (`id`,`idCliente`,`descricao`,`logradouro`,`numero`,`complemento`,`bairro`,`tempoMedioEntrega`,`tipoDeLogradouro`,`distanciaEntrega`,`custoEntrega`) VALUES 
 (1,58,'descricoa','tom jobim',0,'jdsnj','ilha',47,'Aeroporto',42.76,10.69),
 (1,59,'descricao1-59','tom jobim',0,'complemento1-59','ilha do governador',47,'Aeroporto',42.76,10.69),
 (1,60,'ojfdk','internacional tom jobim',0,'kcnkfndnk','ilha do governador',47,'Aeroporto',42.76,10.69),
 (2,49,'pamella descricao 1','Madagascar',346,'Pavuna','Parque Columbia',46,'Rua',36.741,9.18525),
 (2,50,'descricao50','Madagascar',346,'Pavuna','Parque Columbia',46,'Rua',36.741,9.18525),
 (2,51,'maria','madagascar',346,'compl','pavuna',46,'Rua',36.741,9.18525),
 (2,52,'descricao1','madagascar',346,'oavuna','parque columbia',46,'Rua',36.741,9.18525),
 (2,54,'td de bom','madagascar',346,'pavuna','parque columbia',46,'Rua',36.74,9.19),
 (2,58,'jsndjsdnjs','santos dumont',0,'smdndnsk','flamengo',57,'Aeroporto',45.94,11.48),
 (2,59,'descricao2-59','rua madagascar',346,'pavuna','parque columbia',46,'Rua',36.74,9.19),
 (2,60,'descricao2','santos dumont',0,'dvhjhf','flamengo',57,'Aeroporto',45.94,11.48);
INSERT INTO `endereco` (`id`,`idCliente`,`descricao`,`logradouro`,`numero`,`complemento`,`bairro`,`tempoMedioEntrega`,`tipoDeLogradouro`,`distanciaEntrega`,`custoEntrega`) VALUES 
 (3,54,'quero mais um  teste alteracao','Galeão',0,'international','Ilha do Governador',50,'Aeroporto',44.34,11.09),
 (3,60,'descricao3','automovel clube',500,'xhjjhdjh','sao joao de meriti',50,'Avenida',37.79,9.45),
 (4,54,'conteudo 4 vai dar merda no 2','dr arruda negreiros',0,'perto do centro','engenheiro belford',49,'Avenida',36.13,9.03),
 (4,60,'descricao6','das americas',500,'sjhjdhjs','barra da tijuca',30,'Avenida',17.97,4.49);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
