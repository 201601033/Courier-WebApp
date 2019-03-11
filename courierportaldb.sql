-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 11, 2019 at 10:13 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `courierportaldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `announcements`
--

CREATE TABLE `announcements` (
  `announcementID` int(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `announcementBody` varchar(255) NOT NULL,
  `file` longblob,
  `tags` varchar(255) NOT NULL,
  `dateposted` date NOT NULL,
  `timeposted` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `announcements`
--

INSERT INTO `announcements` (`announcementID`, `email`, `announcementBody`, `file`, `tags`, `dateposted`, `timeposted`) VALUES
(14, 'stevejobs@iacademy.edu.ph', 'Punyeta', NULL, 'SE', '2019-03-10', '00:49:38');

-- --------------------------------------------------------

--
-- Table structure for table `roleid`
--

CREATE TABLE `roleid` (
  `roleID` int(11) NOT NULL,
  `role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roleid`
--

INSERT INTO `roleid` (`roleID`, `role`) VALUES
(1, 'Student'),
(2, 'Professor'),
(3, 'Administrator'),
(4, 'System Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `profileImage` longblob,
  `roleID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`email`, `password`, `firstname`, `lastname`, `profileImage`, `roleID`) VALUES
('201901023@iacademy.edu.ph', 'somepassword', 'Some', 'User', NULL, 1),
('it.department@iacademy.edu.ph', 'activedirectory', 'IT', 'Department', NULL, 4),
('james.gosling@iacademy.edu.ph', 'javaislife', 'James', 'Gosling', NULL, 2),
('stevejobs@iacademy.edu.ph', 'appleornothing', 'Steve', 'Jobs', NULL, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `announcements`
--
ALTER TABLE `announcements`
  ADD PRIMARY KEY (`announcementID`);

--
-- Indexes for table `roleid`
--
ALTER TABLE `roleid`
  ADD PRIMARY KEY (`roleID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `announcements`
--
ALTER TABLE `announcements`
  MODIFY `announcementID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
