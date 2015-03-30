create database YLDB;
use YLDB;

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  PRIMARY KEY (`userid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `movie` (
  `ID` varchar(20) NOT NULL,
  `title` varchar(20) DEFAULT NULL,
  `posterImage` varchar(200) DEFAULT NULL,
  `realeaseDate` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `comment` (
  `commentID` varchar(20) NOT NULL,
  `movieID` varchar(20) NOT NULL,
  `userid` int(11) NOT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`movieID`,`userid`),
  KEY `userConstrict` (`userid`),
  CONSTRAINT `movieConstrict` FOREIGN KEY (`movieID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userConstrict` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `actor` (
  `ID` varchar(20) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) DEFAULT NULL,
  `dateOfBirth` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cast` (
  `castID` varchar(20) NOT NULL,
  `actorID` varchar(20) NOT NULL,
  `movieID` varchar(20) NOT NULL,
  `characterName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`actorID`,`movieID`),
  KEY `moviecast` (`movieID`),
  CONSTRAINT `actorcast` FOREIGN KEY (`actorID`) REFERENCES `actor` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `moviecast` FOREIGN KEY (`movieID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;