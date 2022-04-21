CREATE DATABASE CardinalMarket;
USE CardinalMarket;

CREATE TABLE FullCheckOut (
	runID smallint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	numOfLanes smallint NOT NULL,
	notInUseMins int NOT NULL,
	totalWaitMins int NOT NULL,
	custCount int NOT NULL
);

CREATE TABLE SelfCheckOut (
	runID	smallint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	numOfLanes smallint NOT NULL,
	notInUseMins int NOT NULL,
	totalWaitMins int NOT NULL,
	custCount int NOT NULL
);

CREATE TABLE customers (
	custID int NOT NULL PRIMARY KEY,
	serviceType varchar(25) NOT	NULL,
	lane char(5) NOT NULL,
	arrivalTime int NOT NULL,
	waitTime int NOT NULL,
	serviceStart int NOT NULL,
	serviceTime int NOT NULL,
	finishTime int NOT NULL
);