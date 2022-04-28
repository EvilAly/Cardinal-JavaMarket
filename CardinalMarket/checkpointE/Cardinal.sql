DROP DATABASE cardinalmarket;
CREATE DATABASE cardinalmarket;
USE cardinalmarket;

DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
  custID int(11) NOT NULL,
  arrivalTime int(11) NOT NULL,
  waitTime int(11) NOT NULL,
  serviceStart int(11) NOT NULL,
  serviceTime int(11) NOT NULL,
  finishTime int(11) NOT NULL,
  runID smallint(3) NOT NULL
);

DROP TABLE IF EXISTS fullcheckout;
CREATE TABLE fullcheckout (
  runID smallint(6) NOT NULL,
  numOfLanes smallint(6) NOT NULL,
  notInUseMins int(11) NOT NULL,
  totalWaitMins int(11) NOT NULL,
  custCount int(11) NOT NULL,
  suggestion int(11) NOT NULL
);

DROP TABLE IF EXISTS selfcheckout;
CREATE TABLE selfcheckout (
  runID smallint(6) NOT NULL,
  numOfLanes smallint(6) NOT NULL,
  notInUseMins int(11) NOT NULL,
  totalWaitMins int(11) NOT NULL,
  custCount int(11) NOT NULL,
  suggestion int(11) NOT NULL
);

