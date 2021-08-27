-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2021 at 09:04 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rrs`
--

-- --------------------------------------------------------

--
-- Table structure for table `passengers`
--

CREATE TABLE `passengers` (
  `PID` int(11) NOT NULL,
  `NAME` text NOT NULL,
  `AGE` int(11) NOT NULL,
  `GENDER` text NOT NULL,
  `TICKET_NO` int(11) NOT NULL,
  `SEAT_NO` int(11) NOT NULL,
  `QUOTA` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `TRAIN_NO` varchar(5) NOT NULL,
  `AC_CHAIR_CLASS` int(11) NOT NULL,
  `FIRST_AC` int(11) NOT NULL,
  `TWO_TIER_AC` int(11) NOT NULL,
  `THREE_TIER_AC` int(11) NOT NULL,
  `SLEEPER_CLASS` int(11) NOT NULL,
  `SECOND_SEATING` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stations`
--

CREATE TABLE `stations` (
  `STATION_ID` int(11) NOT NULL,
  `STATION_CODE` text NOT NULL,
  `STATION_NAME` text NOT NULL,
  `CITY` text NOT NULL,
  `STATE` text NOT NULL,
  `ZONE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `station_info`
--

CREATE TABLE `station_info` (
  `STATION_ID` int(11) NOT NULL,
  `SHORT_DESCRIPTION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `TICKET_NO` int(11) NOT NULL,
  `PNR_NO` text NOT NULL,
  `TRAIN_NO` char(5) NOT NULL,
  `SOURCE` int(11) NOT NULL,
  `DESTINATION` int(11) NOT NULL,
  `CLASS_TYPE` text NOT NULL,
  `DATE_OF_JOURNEY` text NOT NULL,
  `TOTAL_PERSONS` int(11) NOT NULL,
  `RESERVATION_STATUS` text NOT NULL,
  `BOOKED_BY` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE `trains` (
  `TRAIN_NO` varchar(5) NOT NULL,
  `TRAIN_NAME` text NOT NULL,
  `SOURCE_STATION` int(11) NOT NULL,
  `DESTINATION_STATION` int(11) NOT NULL,
  `TOTAL_COACHES` int(11) NOT NULL,
  `DURATION` text NOT NULL,
  `DISTANCE` text NOT NULL,
  `AC_CHAIR_CLASS` tinyint(4) NOT NULL,
  `FIRST_AC` tinyint(4) NOT NULL,
  `TWO_TIER_AC` tinyint(4) NOT NULL,
  `THREE_TIER_AC` tinyint(4) NOT NULL,
  `SLEEPER_CLASS` tinyint(4) NOT NULL,
  `SECOND_SEATING` tinyint(4) NOT NULL,
  `MON` tinyint(4) NOT NULL,
  `TUE` tinyint(4) NOT NULL,
  `WED` tinyint(4) NOT NULL,
  `THU` tinyint(4) NOT NULL,
  `FRI` tinyint(4) NOT NULL,
  `SAT` tinyint(4) NOT NULL,
  `SUN` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `trains_schedule`
--

CREATE TABLE `trains_schedule` (
  `SCHEDULE_ID` int(11) NOT NULL,
  `TRAIN_NO` varchar(5) NOT NULL,
  `DEPARTURE` time NOT NULL,
  `ARRIVAL` time NOT NULL,
  `HALT` text NOT NULL,
  `DISTANCE` int(11) NOT NULL,
  `DAY` int(11) NOT NULL,
  `STATION_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` varchar(14) NOT NULL COMMENT 'This column stores the UserID of Customers',
  `Name` text NOT NULL,
  `Email` text NOT NULL,
  `Contact` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_accounts`
--

CREATE TABLE `user_accounts` (
  `UserID` varchar(14) NOT NULL,
  `Password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `zones`
--

CREATE TABLE `zones` (
  `ZONE_ID` int(11) NOT NULL,
  `ZONE_NAME` text NOT NULL,
  `ZONE_ABBREVIATION` text NOT NULL,
  `HEADQUATER` text NOT NULL,
  `DIVISONS` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `passengers`
--
ALTER TABLE `passengers`
  ADD KEY `TICKET_NO` (`TICKET_NO`);

--
-- Indexes for table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`TRAIN_NO`);

--
-- Indexes for table `stations`
--
ALTER TABLE `stations`
  ADD PRIMARY KEY (`STATION_ID`),
  ADD UNIQUE KEY `STATION_CODE` (`STATION_CODE`) USING HASH;

--
-- Indexes for table `station_info`
--
ALTER TABLE `station_info`
  ADD PRIMARY KEY (`STATION_ID`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`TICKET_NO`),
  ADD KEY `TRAIN_NO` (`TRAIN_NO`),
  ADD KEY `SOURCE` (`SOURCE`),
  ADD KEY `DESTINATION` (`DESTINATION`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`TRAIN_NO`);

--
-- Indexes for table `trains_schedule`
--
ALTER TABLE `trains_schedule`
  ADD PRIMARY KEY (`SCHEDULE_ID`),
  ADD KEY `TRAIN_NO` (`TRAIN_NO`),
  ADD KEY `STATION_ID` (`STATION_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `user_accounts`
--
ALTER TABLE `user_accounts`
  ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `zones`
--
ALTER TABLE `zones`
  ADD PRIMARY KEY (`ZONE_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stations`
--
ALTER TABLE `stations`
  MODIFY `STATION_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `TICKET_NO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trains_schedule`
--
ALTER TABLE `trains_schedule`
  MODIFY `SCHEDULE_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `zones`
--
ALTER TABLE `zones`
  MODIFY `ZONE_ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `passengers`
--
ALTER TABLE `passengers`
  ADD CONSTRAINT `passengers_ibfk_1` FOREIGN KEY (`TICKET_NO`) REFERENCES `tickets` (`TICKET_NO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `station_info`
--
ALTER TABLE `station_info`
  ADD CONSTRAINT `station_info_ibfk_1` FOREIGN KEY (`STATION_ID`) REFERENCES `stations` (`STATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`TRAIN_NO`) REFERENCES `trains` (`TRAIN_NO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`SOURCE`) REFERENCES `stations` (`STATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tickets_ibfk_3` FOREIGN KEY (`DESTINATION`) REFERENCES `stations` (`STATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `trains_schedule`
--
ALTER TABLE `trains_schedule`
  ADD CONSTRAINT `trains_schedule_ibfk_1` FOREIGN KEY (`TRAIN_NO`) REFERENCES `trains` (`TRAIN_NO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `trains_schedule_ibfk_2` FOREIGN KEY (`STATION_ID`) REFERENCES `stations` (`STATION_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_accounts`
--
ALTER TABLE `user_accounts`
  ADD CONSTRAINT `user_accounts_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
