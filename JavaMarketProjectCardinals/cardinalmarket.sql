-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2022 at 02:40 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cardinalmarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `custID` int(11) NOT NULL,
  `serviceType` varchar(25) NOT NULL,
  `lane` char(5) NOT NULL,
  `arrivalTime` int(11) NOT NULL,
  `waitTime` int(11) NOT NULL,
  `serviceStart` int(11) NOT NULL,
  `serviceTime` int(11) NOT NULL,
  `finishTime` int(11) NOT NULL,
  `runID` smallint(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `fullcheckout`
--

CREATE TABLE `fullcheckout` (
  `runID` smallint(6) NOT NULL,
  `numOfLanes` smallint(6) NOT NULL,
  `notInUseMins` int(11) NOT NULL,
  `totalWaitMins` int(11) NOT NULL,
  `custCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `selfcheckout`
--

CREATE TABLE `selfcheckout` (
  `runID` smallint(6) NOT NULL,
  `numOfLanes` smallint(6) NOT NULL,
  `notInUseMins` int(11) NOT NULL,
  `totalWaitMins` int(11) NOT NULL,
  `custCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`custID`),
  ADD KEY `runID` (`runID`);

--
-- Indexes for table `fullcheckout`
--
ALTER TABLE `fullcheckout`
  ADD PRIMARY KEY (`runID`);

--
-- Indexes for table `selfcheckout`
--
ALTER TABLE `selfcheckout`
  ADD PRIMARY KEY (`runID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fullcheckout`
--
ALTER TABLE `fullcheckout`
  MODIFY `runID` smallint(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `selfcheckout`
--
ALTER TABLE `selfcheckout`
  MODIFY `runID` smallint(6) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`runID`) REFERENCES `fullcheckout` (`runID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
