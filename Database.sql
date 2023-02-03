SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+07:00";

--
-- Cơ sở dữ liệu: `test`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bike`
--

CREATE TABLE `bike` (
  `id` int(11) NOT NULL,
  `idParking` int(11) NOT NULL,
  `price` bigint(20) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `img` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `bike`
--

INSERT INTO `bike` (`id`, `idParking`, `price`, `status`, `img`) VALUES
(1, 1000, 5000000, 0, 'img1.png'),
(2, 1001, 1200000, 0, 'img2.png'),
(3, 1002, 2000000, 0, 'img3.png'),
(4, 1000, 2500000, 0, 'img4.png'),
(5, 1001, 2000000, 0, 'img5.png'),
(6, 1002, 4000000, 0, 'img6.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `card`
--

CREATE TABLE `card` (
  `id` int(11) NOT NULL,
  `cardCode` varchar(45) DEFAULT NULL,
  `cvvCode` varchar(45) DEFAULT NULL,
  `blance` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `card`
--

INSERT INTO `card` (`id`, `cardCode`, `cvvCode`, `blance`) VALUES
(1, '00011', '123456', 5000000),
(2, '00012', '123456', 1000000),
(3, '00013', '123456', 3000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `double_bike`
--

CREATE TABLE `double_bike` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `double_bike`
--

INSERT INTO `double_bike` (`id`, `name`) VALUES
(1, 'Trek Electra Deluxe'),
(4, 'Thorn Raven Twin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `electric_bike`
--

CREATE TABLE `electric_bike` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `battery` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `electric_bike`
--

INSERT INTO `electric_bike` (`id`, `name`, `battery`) VALUES
(2, 'Tork Kratos', 100),
(5, 'Oben Rorr', 98);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `methodpayment`
--

CREATE TABLE `methodpayment` (
  `id` int(11) NOT NULL,
  `owner` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `methodpayment`
--

INSERT INTO `methodpayment` (`id`, `owner`) VALUES
(1, 'Toan'),
(2, 'Thang');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `parking`
--

CREATE TABLE `parking` (
  `id` int(11) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `maxSlot` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `parking`
--

INSERT INTO `parking` (`id`, `address`, `maxSlot`, `description`) VALUES
(1000, 'Bach Khoa, Hai Bai Trung, Ha Noi', 10, ''),
(1001, 'Yen Hoa, Cau Giay, Ha Noi', 15, ''),
(1002, 'Ha Dinh, Thanh Xuan, Ha Noi', 12, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `paymenttransaction`
--

CREATE TABLE `paymenttransaction` (
  `id` int(11) NOT NULL,
  `methodPaymentId` int(11) NOT NULL,
  `bikeId` int(11) NOT NULL,
  `cost` bigint(20) NOT NULL,
  `createAt` datetime NOT NULL,
  `command` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `paymenttransaction`
--

INSERT INTO `paymenttransaction` (`id`, `methodPaymentId`, `bikeId`, `cost`, `createAt`, `command`) VALUES
(100, 1, 1, 100000, '0000-00-00 00:00:00', NULL),
(101, 2, 3, 200000, '0000-00-00 00:00:00', NULL),
(102, 3, 6, 80000, '0000-00-00 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `renting_bike`
--

CREATE TABLE `renting_bike` (
  `id` int(11) NOT NULL,
  `startAt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `renting_bike`
--

INSERT INTO `renting_bike` (`id`, `startAt`) VALUES
(2, '2022-02-03 03:23:44'),
(5, '2022-02-03 02:20:10'),
(2, '2022-02-03 00:23:12');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `single_bike`
--

CREATE TABLE `single_bike` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `single_bike`
--

INSERT INTO `single_bike` (`id`, `name`) VALUES
(3, 'Zeus'),
(6, 'Raven');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bike`
--
ALTER TABLE `bike`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idParking` (`idParking`);

--
-- Chỉ mục cho bảng `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `parking`
--
ALTER TABLE `parking`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `single_bike`
--
ALTER TABLE `single_bike`
  ADD PRIMARY KEY (`id`);
COMMIT;