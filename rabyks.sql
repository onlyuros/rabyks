-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2015 at 03:21 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rabyks`
--
CREATE DATABASE IF NOT EXISTS `rabyks` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `rabyks`;

-- --------------------------------------------------------

--
-- Table structure for table `partners`
--

DROP TABLE IF EXISTS `partners`;
CREATE TABLE IF NOT EXISTS `partners` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `number` varchar(20) DEFAULT NULL,
  `logo_url` varchar(100) NOT NULL,
  `layout_img_url` varchar(100) NOT NULL,
  `galery_img_1_url` varchar(100) DEFAULT NULL,
  `galery_img_2_url` varchar(100) DEFAULT NULL,
  `galery_img_3_url` varchar(100) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `details` varchar(100) DEFAULT NULL,
  `working_hours` varchar(100) NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `modified_at` bigint(20) NOT NULL DEFAULT '0',
  `longitude` decimal(11,8) NOT NULL,
  `latitude` decimal(10,8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `partners`
--

INSERT INTO `partners` (`id`, `name`, `address`, `number`, `logo_url`, `layout_img_url`, `galery_img_1_url`, `galery_img_2_url`, `galery_img_3_url`, `type`, `details`, `working_hours`, `created_at`, `modified_at`, `longitude`, `latitude`) VALUES
(1, 'drugstore', 'Mali Simanovci 200', '987654321', 'https://s3-us-west-2.amazonaws.com/klubovi/drugstore1.jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/drugstoreLayout.png', 'https://s3-us-west-2.amazonaws.com/klubovi/drugstore2.jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/drugstore3.jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/drugstore4.jpg', 1, 'Dodjite da prskamo zajedno', '22-06', 692299830, 0, '20.41379300', '44.81621800'),
(2, 'terrace', 'Idi mi dodji mi 3', '123456789', 'https://s3-us-west-2.amazonaws.com/klubovi/terrace+(1).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/terraceLayout.png', 'https://s3-us-west-2.amazonaws.com/klubovi/terrace+(2).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/terrace+(3).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/terrace+(4).jpg', 2, 'Ovo ce da glumi nesto drugo', '16-22', 628191030, 0, '20.41379300', '44.81621800'),
(3, 'djole', 'adresa', '123654789', 'https://s3-us-west-2.amazonaws.com/klubovi/dzukac+(1).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/dzukacLayout.png', 'https://s3-us-west-2.amazonaws.com/klubovi/dzukac+(2).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/dzukac+(3).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/dzukac+(4).jpg', 3, 'detalji', '22-24', 1434823440, 0, '20.41379300', '44.81621800'),
(4, 'mountain', 'planinska 5', '789654123', 'https://s3-us-west-2.amazonaws.com/klubovi/mountain+(1).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/dzukacLayout.png', 'https://s3-us-west-2.amazonaws.com/klubovi/mountain+(2).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/mountain+(3).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/dzukac+(4).jpg', 3, 'detaji detalji', '08-24', 1434894801, 0, '20.41379300', '44.81621800'),
(5, 'Lasta', 'Krs i lom 12bb', '453441241', 'https://s3-us-west-2.amazonaws.com/klubovi/lasta+(1).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/lastaLayout.png', 'https://s3-us-west-2.amazonaws.com/klubovi/lasta+(2).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/lasta+(3).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/lasta+(4).jpg', 1, 'Svasta se ovde dogadja', '00-24', 1436273860, 0, '20.41676900', '44.75384600'),
(7, 'Smokvica', 'Kralja Petra 44', '666-666', 'https://s3-us-west-2.amazonaws.com/klubovi/smokvica+(1).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/smokvica+Layout.png', 'https://s3-us-west-2.amazonaws.com/klubovi/smokvica+(2).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/smokvica+(3).jpg', 'https://s3-us-west-2.amazonaws.com/klubovi/smokvica+(4).jpg', 2, 'Smokvica kod nas samo belo', '16-24', 1436273860, 0, '20.41676900', '44.75384600'),
(8, 'Freestyler', 'Kosmarska 11', '9876543', 'https://s3-us-west-2.amazonaws.com/klubovi/free+(1).png', 'https://s3-us-west-2.amazonaws.com/klubovi/freeLayout.png', 'https://s3-us-west-2.amazonaws.com/klubovi/free+(2).png', 'https://s3-us-west-2.amazonaws.com/klubovi/free+(3).png', 'https://s3-us-west-2.amazonaws.com/klubovi/free+(4).png', 1, NULL, '19-23', 1436273860, 0, '20.41676900', '44.75384600');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `role` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role`,`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role`, `user_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `password` varchar(20) NOT NULL,
  `number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `number`) VALUES
(1, 'uros@hp.com', 'password', NULL),
(2, 'milos@gmail.com', 'password', '1234567'),
(3, 'user@user.com', 'password', '7654321'),
(4, 'djole@djole.com', 'password', '32456754');

-- --------------------------------------------------------

--
-- Table structure for table `user_partner`
--

DROP TABLE IF EXISTS `user_partner`;
CREATE TABLE IF NOT EXISTS `user_partner` (
  `user_id` int(11) NOT NULL,
  `partner_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`partner_id`),
  KEY `partner_id` (`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_partner`
--

INSERT INTO `user_partner` (`user_id`, `partner_id`) VALUES
(1, 1),
(2, 1),
(1, 2),
(2, 2),
(4, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_partner`
--
ALTER TABLE `user_partner`
  ADD CONSTRAINT `user_partner_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_partner_ibfk_2` FOREIGN KEY (`partner_id`) REFERENCES `partners` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
