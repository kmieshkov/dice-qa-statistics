-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 29, 2022 at 09:21 PM
-- Server version: 8.0.13-4
-- PHP Version: 7.2.24-0ubuntu0.18.04.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `l7KuvpmIc9`
--

-- --------------------------------------------------------

--
-- Table structure for table `dice`
--

CREATE TABLE `dice` (
  `id` int(11) NOT NULL,
  `job_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `period` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `result` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `week_day` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dice`
--

INSERT INTO `dice` (`id`, `job_title`, `location`, `period`, `result`, `date`, `time`, `week_day`) VALUES
(1, 'qa OR sqa OR quality OR testing OR tester', 'United States', '7', '11496', '2022-03-28', '17:39:14', 'MONDAY'),
(2, 'qa OR sqa OR quality OR testing OR tester', 'United States', '1', '1725', '2022-03-28', '17:39:32', 'MONDAY'),
(3, 'qa OR sqa OR quality OR testing OR tester', 'California, USA', '7', '1557', '2022-03-28', '17:39:50', 'MONDAY'),
(4, 'qa OR sqa OR quality OR testing OR tester', 'California, USA', '1', '199', '2022-03-28', '17:40:09', 'MONDAY'),
(5, 'qa OR sqa OR quality OR testing OR tester', 'New York, NY, USA', '7', '2791', '2022-03-28', '17:40:28', 'MONDAY'),
(6, 'qa OR sqa OR quality OR testing OR tester', 'New York, NY, USA', '1', '693', '2022-03-28', '17:40:46', 'MONDAY'),
(7, 'qa OR sqa OR quality OR testing OR tester', 'Mountain View, CA, USA', '7', '2574', '2022-03-28', '17:41:04', 'MONDAY'),
(8, 'qa OR sqa OR quality OR testing OR tester', 'Mountain View, CA, USA', '1', '650', '2022-03-28', '17:41:24', 'MONDAY'),
(9, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '1', '561', '2022-03-28', '17:41:42', 'MONDAY'),
(10, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '7', '2137', '2022-03-28', '17:41:42', 'MONDAY'),
(30, 'qa OR sqa OR quality OR testing OR tester', 'United States', '1', '1700', '2022-03-29', '15:54:09', 'TUESDAY'),
(31, 'qa OR sqa OR quality OR testing OR tester', 'United States', '7', '11063', '2022-03-29', '15:54:26', 'TUESDAY'),
(32, 'qa OR sqa OR quality OR testing OR tester', 'California, USA', '1', '244', '2022-03-29', '15:54:45', 'TUESDAY'),
(33, 'qa OR sqa OR quality OR testing OR tester', 'California, USA', '7', '1550', '2022-03-29', '15:55:03', 'TUESDAY'),
(34, 'qa OR sqa OR quality OR testing OR tester', 'New York, NY, USA', '1', '788', '2022-03-29', '15:55:23', 'TUESDAY'),
(35, 'qa OR sqa OR quality OR testing OR tester', 'New York, NY, USA', '7', '2843', '2022-03-29', '15:55:44', 'TUESDAY'),
(36, 'qa OR sqa OR quality OR testing OR tester', 'Mountain View, CA, USA', '1', '725', '2022-03-29', '15:56:05', 'TUESDAY'),
(37, 'qa OR sqa OR quality OR testing OR tester', 'Mountain View, CA, USA', '7', '2591', '2022-03-29', '15:56:23', 'TUESDAY'),
(38, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '1', '633', '2022-03-29', '15:56:45', 'TUESDAY'),
(39, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '7', '2246', '2022-03-29', '15:57:07', 'TUESDAY'),
(40, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '1', '594', '2022-03-29', '16:15:21', 'TUESDAY'),
(41, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '7', '2222', '2022-03-29', '16:15:40', 'TUESDAY');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dice`
--
ALTER TABLE `dice`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dice`
--
ALTER TABLE `dice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
