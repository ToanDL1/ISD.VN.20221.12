SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+07:00";

CREATE TABLE `parking` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `address` varchar(200) DEFAULT NULL,
  `maxSlot` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `users` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `methodpayment` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `userId` int(11) NOT NULL,
  CONSTRAINT `FK_METHODPAY_TO_USER` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `bike` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `idParking` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `img` varchar(200) NOT NULL,
  CONSTRAINT `FK_BIKE_TO_PARKING` FOREIGN KEY (`idParking`) REFERENCES `parking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `card` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `cardCode` varchar(45) DEFAULT NULL,
  `cvvCode` varchar(45) DEFAULT NULL,
  `blance` bigint(20) DEFAULT NULL,
  CONSTRAINT `FK_CARD_TO_PAYMENT` FOREIGN KEY (`id`) REFERENCES `methodpayment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `double_bike` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `name` varchar(45) DEFAULT NULL,
  CONSTRAINT `FK_DOUBLEBIKR_TO_BIKE` FOREIGN KEY (`id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `electric_bike` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `name` varchar(45) DEFAULT NULL,
  `battery` int(11) NOT NULL,
  CONSTRAINT `FK_ELECTRICBIKE_TO_BIKE` FOREIGN KEY (`id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `single_bike` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `name` varchar(45) DEFAULT NULL,
  CONSTRAINT `FK_SINGLEBIKE_TO_BIKE` FOREIGN KEY (`id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `paymenttransaction` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `methodPaymentId` int(11) NOT NULL,
  `bikeId` int(11) NOT NULL,
  `cost` bigint(20) NOT NULL,
  `createAt` datetime NOT NULL,
  `command` varchar(45) DEFAULT NULL,
  CONSTRAINT `FK_PAYMENTTRAN_TO_METHODPAYMENT` FOREIGN KEY (`id`) REFERENCES `methodpayment` (`id`),
  CONSTRAINT `FK_PAYMENTTRAN_TO_BIKE` FOREIGN KEY (`bikeId`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `renting_bike` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `startAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `endAt` DATETIME,
  CONSTRAINT `FK_TO_BIKE` FOREIGN KEY (`id`) REFERENCES `bike` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
--
-- Đang đổ dữ liệu 
--

INSERT INTO `parking` (`id`, `address`, `maxSlot`, `description`) VALUES
(1000, 'Bach Khoa, Hai Bai Trung, Ha Noi', 10, ''),
(1001, 'Yen Hoa, Cau Giay, Ha Noi', 15, ''),
(1002, 'Ha Dinh, Thanh Xuan, Ha Noi', 12, NULL);

INSERT INTO `users` (`id`, `name`,`address`) VALUES
(1, 'Thang','Ha Noi'),
(2, 'Toan','Ha Noi');

INSERT INTO `methodpayment` (`id`, `userId`) VALUES
(1, 1),
(2, 2);

INSERT INTO `bike` (`id`, `idParking`, `price`, `status`, `img`) VALUES
(1, 1000, 5000000, 0, '/assets/img/parking1/bike/double_bike.jpg'),
(2, 1000, 1200000, 0, '/assets/img/parking1/bike/electric_bike.jpg'),
(3, 1000, 2000000, 0, '/assets/img/parking1/bike/single_bike.jpg'),
(4, 1001, 2500000, 0, '/assets/img/parking2/bike/double_bike.jpg'),
(5, 1001, 2000000, 0, '/assets/img/parking2/bike/electric_bike.jpg'),
(6, 1001, 4000000, 0, '/assets/img/parking2/bike/single_bike.jpg'),
(7, 1002, 3000000, 0, '/assets/img/parking3/bike/double_bike.jpg'),
(8, 1002, 5000000, 0, '/assets/img/parking3/bike/electric_bike.jpg'),
(9, 1002, 1000000, 0, '/assets/img/parking3/bike/single_bike.jpg');

INSERT INTO `card` (`id`, `cardCode`, `cvvCode`, `blance`) VALUES
(1, '00011', '123456', 5000000),
(2, '00012', '123456', 1000000),
(3, '00013', '123456', 3000000);

INSERT INTO `double_bike` (`id`, `name`) VALUES
(1, 'Trek Electra Deluxe'),
(4, 'Thorn Raven Twin');

INSERT INTO `electric_bike` (`id`, `name`, `battery`) VALUES
(2, 'Tork Kratos', 100),
(5, 'Oben Rorr', 98);

INSERT INTO `single_bike` (`id`, `name`) VALUES
(3, 'Zeus'),
(6, 'Raven');

INSERT INTO `renting_bike` (`id`, `startAt`,`endAt`) VALUES
(00001, '2022-02-03 03:23:44','2022-02-03 05:21:45');

INSERT INTO `paymenttransaction` (`id`, `methodPaymentId`, `bikeId`, `cost`, `createAt`, `command`) VALUES
(100, 1, 1, 100000, '0000-00-00 00:00:00', NULL),
(101, 2, 3, 200000, '0000-00-00 00:00:00', NULL),
(102, 3, 6, 80000, '0000-00-00 00:00:00', NULL);

COMMIT;