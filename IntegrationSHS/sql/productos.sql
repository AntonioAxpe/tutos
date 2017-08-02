-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-08-2017 a las 09:49:23
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `productos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `buy`
--

CREATE TABLE `buy` (
  `buy_id` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `buy_total` float NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `buy`
--

INSERT INTO `buy` (`buy_id`, `status`, `user_id`, `buy_total`, `date`) VALUES
(1, 'checked', 1, 56, '2017-07-27'),
(4, 'payed', 1, 35258.7, '2017-07-27'),
(5, 'payed', 1, 2, '2017-08-02'),
(6, 'payed', 1, 317.69, '2017-08-02'),
(7, 'payed', 1, 2, '2017-08-02'),
(8, 'payed', 1, 14.7, '2017-08-02'),
(9, 'payed', 1, 14.7, '2017-08-02'),
(10, 'active', 1, 0, '2017-08-02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detail_buy`
--

CREATE TABLE `detail_buy` (
  `buy_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(5) NOT NULL,
  `product_total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detail_buy`
--

INSERT INTO `detail_buy` (`buy_id`, `product_id`, `quantity`, `product_total`) VALUES
(4, 2, 27, 54),
(4, 3, 29, 348),
(4, 7, 35, 34720),
(4, 10, 1, 122),
(4, 11, 1, 14.7),
(5, 2, 1, 2),
(6, 2, 1, 2),
(6, 3, 2, 24),
(6, 7, 1, 30.99),
(6, 8, 1, 2),
(6, 10, 2, 244),
(6, 11, 1, 14.7),
(7, 2, 1, 2),
(8, 11, 1, 14.7),
(9, 11, 1, 14.7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` varchar(512) NOT NULL,
  `price` float NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`product_id`, `name`, `description`, `price`, `user_id`) VALUES
(2, 'Pc GAYmer', 'Sutíl ordenador para las princesas reinonas de la vida', 2, 1),
(3, 'Tractor', 'Es lo que se lleva ahora', 12, 1),
(7, 'Barbacoa', 'Como me gusta la barbequiu', 30.99, 1),
(8, 'Nada', 'Esto no es nada', 2, 1),
(9, 'Ventilador', 'Ergonomia total con varios niveles', 10.5, 1),
(10, 'Neymar', 'Bueno, bonito y con ansias de dinero', 122, 1),
(11, 'Bicho', 'Ay mi madre! Como defrauda hacienda el bichoooo!!', 14.7, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `name`) VALUES
(1, 'admin', 'admin1234', 'Administrator');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `buy`
--
ALTER TABLE `buy`
  ADD PRIMARY KEY (`buy_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `detail_buy`
--
ALTER TABLE `detail_buy`
  ADD PRIMARY KEY (`buy_id`,`product_id`),
  ADD KEY `detail_buy_ibfk_1` (`product_id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `buy`
--
ALTER TABLE `buy`
  MODIFY `buy_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `buy`
--
ALTER TABLE `buy`
  ADD CONSTRAINT `buy_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detail_buy`
--
ALTER TABLE `detail_buy`
  ADD CONSTRAINT `detail_buy_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_buy_ibfk_2` FOREIGN KEY (`buy_id`) REFERENCES `buy` (`buy_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
