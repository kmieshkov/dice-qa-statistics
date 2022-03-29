-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 29, 2022 at 09:18 PM
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
-- Table structure for table `search_data`
--

CREATE TABLE `search_data` (
  `id` int(11) NOT NULL,
  `job_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `period` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `search_data`
--

INSERT INTO `search_data` (`id`, `job_title`, `location`, `period`) VALUES
(1, 'qa OR sqa OR quality OR testing OR tester', 'United States', '1'),
(2, 'qa OR sqa OR quality OR testing OR tester', 'United States', '7'),
(3, 'qa OR sqa OR quality OR testing OR tester', 'California, USA', '1'),
(4, 'qa OR sqa OR quality OR testing OR tester', 'California, USA', '7'),
(5, 'qa OR sqa OR quality OR testing OR tester', 'New York, NY, USA', '1'),
(6, 'qa OR sqa OR quality OR testing OR tester', 'New York, NY, USA', '7'),
(7, 'qa OR sqa OR quality OR testing OR tester', 'Mountain View, CA, USA', '1'),
(8, 'qa OR sqa OR quality OR testing OR tester', 'Mountain View, CA, USA', '7'),
(9, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '1'),
(10, 'qa OR sqa OR quality OR testing OR tester', 'Remote Only', '7');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `search_data`
--
ALTER TABLE `search_data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `search_data`
--
ALTER TABLE `search_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
