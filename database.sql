-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 03, 2021 at 08:26 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `crsjdi02-struct`
--

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `courseid` varchar(255) NOT NULL,
  `coursename` varchar(255) NOT NULL,
  `professorid` varchar(255) DEFAULT NULL,
  `COURSECATALOGUEID` varchar(255) DEFAULT NULL,
  `fee` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 4096 kB';

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`courseid`, `coursename`, `professorid`, `COURSECATALOGUEID`, `fee`) VALUES
('201', 'OS', '11', '1', 100);

-- --------------------------------------------------------

--
-- Table structure for table `coursecatalogue`
--

CREATE TABLE `coursecatalogue` (
  `coursecatalogueid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coursecatalogue`
--

INSERT INTO `coursecatalogue` (`coursecatalogueid`, `name`, `description`) VALUES
('1', 'Catalog 1', 'Default catalogue');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `notificationid` int(11) NOT NULL,
  `studentid` varchar(255) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`notificationid`, `studentid`, `message`, `date`) VALUES
(8, '101', 'Student approved for registration.', '2021-02-03 12:44:29');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `studentid` varchar(255) NOT NULL,
  `feepaid` int(11) DEFAULT NULL,
  `paymentmethod` int(11) DEFAULT NULL,
  `paymentdate` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `professor`
--

CREATE TABLE `professor` (
  `userid` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `professor`
--

INSERT INTO `professor` (`userid`, `department`) VALUES
('11', 'S');

-- --------------------------------------------------------

--
-- Table structure for table `registeredcourse`
--

CREATE TABLE `registeredcourse` (
  `studentid` varchar(255) NOT NULL,
  `courseid` varchar(255) NOT NULL,
  `grade` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `userid` varchar(255) NOT NULL,
  `branch` varchar(255) NOT NULL,
  `approved` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`userid`, `branch`, `approved`) VALUES
('101', 'CS', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `name`, `email`, `password`, `role`) VALUES
('1', 'admin', 'admin@google.com', '1', 'admin'),
('101', 'Vani', 'vani@bits.com', '101', 'student'),
('11', 'Biju', 'biju@bits.com', '11', 'professor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`courseid`),
  ADD KEY `COURSECATALOGUEID` (`COURSECATALOGUEID`);

--
-- Indexes for table `coursecatalogue`
--
ALTER TABLE `coursecatalogue`
  ADD PRIMARY KEY (`coursecatalogueid`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`notificationid`),
  ADD KEY `studentid` (`studentid`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`studentid`);

--
-- Indexes for table `professor`
--
ALTER TABLE `professor`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `registeredcourse`
--
ALTER TABLE `registeredcourse`
  ADD PRIMARY KEY (`studentid`,`courseid`),
  ADD KEY `courseid` (`courseid`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `notificationid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`COURSECATALOGUEID`) REFERENCES `coursecatalogue` (`coursecatalogueid`) ON DELETE CASCADE;

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`userid`) ON DELETE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`userid`) ON DELETE CASCADE;

--
-- Constraints for table `professor`
--
ALTER TABLE `professor`
  ADD CONSTRAINT `professor_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE;

--
-- Constraints for table `registeredcourse`
--
ALTER TABLE `registeredcourse`
  ADD CONSTRAINT `registeredcourse_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`userid`) ON DELETE CASCADE,
  ADD CONSTRAINT `registeredcourse_ibfk_2` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseid`) ON DELETE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
