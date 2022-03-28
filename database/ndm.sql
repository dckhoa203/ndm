-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2022 at 08:54 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ndm`
--

-- --------------------------------------------------------

--
-- Table structure for table `credential`
--

CREATE TABLE `credential` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `id` int(11) NOT NULL,
  `credential_id` int(11) NOT NULL,
  `ntp_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `label` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `serial` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ip_address` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `version` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_operational` tinyint(1) NOT NULL DEFAULT 0,
  `is_resync` tinyint(1) NOT NULL DEFAULT 0,
  `type` int(11) NOT NULL,
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `interface`
--

CREATE TABLE `interface` (
  `id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT 0,
  `dchp` tinyint(1) NOT NULL DEFAULT 0,
  `ip_address` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `netmask` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gateway` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `info` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `npt_server`
--

CREATE TABLE `npt_server` (
  `id` int(11) NOT NULL,
  `ntp_id` int(11) NOT NULL,
  `clock_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ip_address` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ntp`
--

CREATE TABLE `ntp` (
  `id` int(11) NOT NULL,
  `ntp_client` tinyint(1) NOT NULL DEFAULT 0,
  `tai_offset` int(11) NOT NULL,
  `dscp` int(11) NOT NULL,
  `vlan_priority` int(11) NOT NULL,
  `sync_status` tinyint(1) NOT NULL DEFAULT 0,
  `time_interval` int(11) NOT NULL,
  `number_messages` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `port`
--

CREATE TABLE `port` (
  `id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connector` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT 0,
  `speed` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mtu` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mdi` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mac_address` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `credential`
--
ALTER TABLE `credential`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `ip_address` (`ip_address`);

--
-- Indexes for table `interface`
--
ALTER TABLE `interface`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ip_address` (`ip_address`);

--
-- Indexes for table `npt_server`
--
ALTER TABLE `npt_server`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ip_address` (`ip_address`);

--
-- Indexes for table `ntp`
--
ALTER TABLE `ntp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `port`
--
ALTER TABLE `port`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mac_address` (`mac_address`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `credential`
--
ALTER TABLE `credential`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `device`
--
ALTER TABLE `device`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `interface`
--
ALTER TABLE `interface`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `npt_server`
--
ALTER TABLE `npt_server`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ntp`
--
ALTER TABLE `ntp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `port`
--
ALTER TABLE `port`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
