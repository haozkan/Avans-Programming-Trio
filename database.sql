-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.26-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for netflix
CREATE DATABASE IF NOT EXISTS `netflix` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `netflix`;

-- Dumping structure for table netflix.account
CREATE TABLE IF NOT EXISTS `account` (
  `accountID` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(128) NOT NULL,
  `streetName` varchar(128) NOT NULL,
  `houseNumber` varchar(8) NOT NULL,
  `zipCode` varchar(21) NOT NULL,
  `residence` varchar(64) NOT NULL,
  PRIMARY KEY (`accountID`)
) ENGINE=InnoDB AUTO_INCREMENT=1020 DEFAULT CHARSET=latin1;

-- Dumping data for table netflix.account: ~1,006 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`accountID`, `accountName`, `streetName`, `houseNumber`, `zipCode`, `residence`) VALUES
	(0, 'Fam. van Raalte', 'Schopenhauerdijkje', '5', '3991 ML', 'Houten');
INSERT INTO `account` (`accountID`, `accountName`, `streetName`, `houseNumber`, `zipCode`, `residence`) VALUES
	(1018, 'J. van Betlehem', 'Nietzschestraat', '99', '8542 BE', 'Breda');
INSERT INTO `account` (`accountID`, `accountName`, `streetName`, `houseNumber`, `zipCode`, `residence`) VALUES
	(1019, 'F. de Kat', 'Kantlaan', '11', '8542 CD', 'Breda');
INSERT INTO `account` (`accountID`, `accountName`, `streetName`, `houseNumber`, `zipCode`, `residence`) VALUES
	(1020, 'M.C. van Poortvliet', 'Ten Ankerweg', '14', '4691 GW', 'Tholen');
INSERT INTO `account` (`accountID`, `accountName`, `streetName`, `houseNumber`, `zipCode`, `residence`) VALUES
	(1021, 'J.J. van Poortvliet', 'Ten Ankerweg', '16', '4691GW', 'Tholen');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table netflix.episode
CREATE TABLE IF NOT EXISTS `episode` (
  `episodeID` int(11) NOT NULL AUTO_INCREMENT,
  `videoID` int(11) NOT NULL,
  `serieID` int(11),
  `season` int(11) NOT NULL,
  PRIMARY KEY (`episodeID`),
  KEY `videoId` (`videoID`),
  KEY `serieID` (`serieID`),
  CONSTRAINT `serieID-serie` FOREIGN KEY (`serieID`) REFERENCES `serie` (`serieID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `videoID-episode` FOREIGN KEY (`videoID`) REFERENCES `video` (`videoID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- Dumping data for table netflix.episode: ~5 rows (approximately)
/*!40000 ALTER TABLE `episode` DISABLE KEYS */;
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(7, 22, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(8, 23, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(9, 24, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(10, 25, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(11, 26, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(12, 27, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(13, 28, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(14, 29, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(15, 30, 3, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(17, 32, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(18, 33, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(19, 34, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(20, 35, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(21, 36, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(22, 37, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(23, 38, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(24, 39, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(25, 40, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(26, 41, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(27, 42, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(28, 43, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(29, 44, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(30, 45, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(31, 46, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(32, 47, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(33, 48, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(34, 49, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(35, 50, 4, 1);
INSERT INTO `episode` (`episodeID`, `videoID`, `serieID`, `season`) VALUES
	(36, 51, 4, 1);
/*!40000 ALTER TABLE `episode` ENABLE KEYS */;

-- Dumping structure for table netflix.movie
CREATE TABLE IF NOT EXISTS `movie` (
  `movieID` int(11) NOT NULL AUTO_INCREMENT,
  `videoID` int(11) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `language` varchar(50) NOT NULL,
  `ageClassification` varchar(50) NOT NULL,
  PRIMARY KEY (`movieID`),
  KEY `videoID` (`videoID`),
  CONSTRAINT `videoID` FOREIGN KEY (`videoID`) REFERENCES `video` (`videoID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Dumping data for table netflix.movie: ~2 rows (approximately)
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(4, 11, 'Humor', 'Engels', '12');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(5, 15, 'Western', 'Engels-Amerikaans', '12');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(6, 10, 'Detective', 'Engels', '12');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(7, 14, 'Misdaad', 'Engels-Amerikaans', '16');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(8, 12, 'Misdaad', 'Engels-Amerikaans', '16');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(9, 13, 'Erotiek', 'Nederlands', '18');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(10, 17, 'Humor', 'Nederlands', '6');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(11, 18, 'Oorlog', 'Duits', '6');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(12, 19, 'Humor', 'Vlaams', '12');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(13, 16, 'Humor', 'Engels-Amerikaans', '16');
INSERT INTO `movie` (`movieID`, `videoID`, `genre`, `language`, `ageClassification`) VALUES
	(14, 20, 'SF', 'Engels', '16');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;

-- Dumping structure for table netflix.profile
CREATE TABLE IF NOT EXISTS `profile` (
  `profileID` int(11) NOT NULL AUTO_INCREMENT,
  `accountID` int(11) NOT NULL,
  `profileName` varchar(128) NOT NULL,
  `dateofBirth` varchar(128) NOT NULL,
  PRIMARY KEY (`profileID`),
  KEY `accountID` (`accountID`),
  CONSTRAINT `accountID` FOREIGN KEY (`accountID`) REFERENCES `account` (`accountID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- Dumping data for table netflix.profile: ~5 rows (approximately)
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(30, 0, 'Frank', '1968-01-25');
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(31, 0, 'Madelief', '2001-08-19');
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(32, 1018, 'Petrus', '1999-06-26');
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(33, 1018, 'Paulus', '1999-06-26');
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(34, 1019, 'Fritz', '1968-08-19');
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(35, 1019, 'Diana', '1988-12-25');
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(36, 1021, 'Julia', '1998-01-03');
INSERT INTO `profile` (`profileID`, `accountID`, `profileName`, `dateofBirth`) VALUES
	(37, 1020, 'Marco', '1998-05-05');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;

-- Dumping structure for table netflix.serie
CREATE TABLE IF NOT EXISTS `serie` (
  `serieID` int(11) NOT NULL AUTO_INCREMENT,
  `serieName` varchar(128) NOT NULL,
  `ageClassification` int(11) NOT NULL,
  `language` varchar(50) NOT NULL,
  `genre` varchar(50) NOT NULL,
  `suggestion` int(11) NOT NULL,
  PRIMARY KEY (`serieID`),
  KEY `suggestion` (`suggestion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table netflix.serie: ~0 rows (approximately)
/*!40000 ALTER TABLE `serie` DISABLE KEYS */;
INSERT INTO `serie` (`serieID`, `serieName`, `ageClassification`, `language`, `genre`, `suggestion`) VALUES
	(3, 'Sherlock', 12, 'Engels', 'Detective', 4);
INSERT INTO `serie` (`serieID`, `serieName`, `ageClassification`, `language`, `genre`, `suggestion`) VALUES
	(4, 'Breaking Bad', 16, 'Engels-Amerikaans', 'Spanning', 3);
/*!40000 ALTER TABLE `serie` ENABLE KEYS */;

-- Dumping structure for table netflix.video
CREATE TABLE IF NOT EXISTS `video` (
  `videoID` int(11) NOT NULL AUTO_INCREMENT,
  `videoTitle` varchar(128) NOT NULL,
  `durage` varchar(128) NOT NULL,
  PRIMARY KEY (`videoID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

-- Dumping data for table netflix.video: ~7 rows (approximately)
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(0, 'A study in Pink', '');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(10, 'The Abominable Bride', '89');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(11, 'The Life of Brian', '94');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(12, 'Pulp Fiction', '154');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(13, 'Pruimebloesem', '80');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(14, 'Reservoir Dogs', '99');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(15, 'The Good, the Bad and the Ugly', '161');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(16, 'Andy Warhol\'s Dracula', '180');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(17, 'Ober', '210');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(18, 'Der Untergang', '120');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(19, 'De helaasheid der dingen', '108');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(20, 'A Clockwork Orange', '136');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(22, 'A Study in Pink', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(23, 'The Blind Banker', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(24, 'The Great Game', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(25, 'A Scandal in Belgravia', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(26, 'The Hounds of Baskerville', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(27, 'The Reichenbach Fall', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(28, 'The Empty Hearse', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(29, 'The Sign of Three', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(30, 'His Last Vow', '88');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(32, 'Pilot', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(33, 'Cat’s in the Bag…', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(34, '…And the Bag’s in the River', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(35, 'Cancer Man', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(36, 'Gray Matter', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(37, 'Crazy Handful of Nothin’', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(38, 'A No-Rough-Stuff-Type Deal', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(39, 'Seven Thirty-Seven', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(40, 'Grilled', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(41, 'Bit by a Dead Bee', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(42, 'Down', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(43, 'Breakage', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(44, 'Peekaboo', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(45, 'Negro Y Azul', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(46, 'Better Call Saul', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(47, '4 Days Out', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(48, 'Over', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(49, 'Mandala', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(50, 'Phoenix', '48');
INSERT INTO `video` (`videoID`, `videoTitle`, `durage`) VALUES
	(51, 'ABQ', '48');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;

-- Dumping structure for table netflix.watched
CREATE TABLE IF NOT EXISTS `watched` (
  `watchedID` int(11) NOT NULL AUTO_INCREMENT,
  `profileID` int(11) DEFAULT NULL,
  `videoID` int(11) DEFAULT NULL,
  `percentage` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`watchedID`),
  KEY `profileID` (`profileID`),
  KEY `videoID` (`videoID`),
  CONSTRAINT `profile` FOREIGN KEY (`profileID`) REFERENCES `profile` (`profileID`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `video` FOREIGN KEY (`videoID`) REFERENCES `video` (`videoID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- Dumping data for table netflix.watched: ~3 rows (approximately)
/*!40000 ALTER TABLE `watched` DISABLE KEYS */;
INSERT INTO `watched` (`watchedID`, `profileID`, `videoID`, `percentage`) VALUES
	(16, 32, 12, 10);
INSERT INTO `watched` (`watchedID`, `profileID`, `videoID`, `percentage`) VALUES
	(17, 33, 12, 11);
INSERT INTO `watched` (`watchedID`, `profileID`, `videoID`, `percentage`) VALUES
	(18, 35, 18, 80);
INSERT INTO `watched` (`watchedID`, `profileID`, `videoID`, `percentage`) VALUES
	(19, 36, 27, 77);
/*!40000 ALTER TABLE `watched` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
