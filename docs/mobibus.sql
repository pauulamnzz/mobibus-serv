-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 14-01-2024 a las 16:05:52
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mobibus`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parada_fav`
--

CREATE TABLE `parada_fav` (
  `id` bigint NOT NULL,
  `denominacion` varchar(512) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_parada_api` bigint NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `parada_fav`
--

INSERT INTO `parada_fav` (`id`, `denominacion`, `id_parada_api`) VALUES
(1, 'casa', 20),
(2, 'casa', 0),
(3, 'casa', 0),
(4, 'casa', 0),
(5, 'casa', 0),
(6, 'casa', 4),
(7, 'casa', 4),
(8, 'casa', 7),
(9, 'casa', 4),
(10, 'casa', 4),
(11, 'casa', 1),
(12, 'casa', 1),
(13, 'casa', 0),
(14, 'casa', 0),
(15, 'casa', 1),
(16, 'casa', 1),
(20, 'casa', 1),
(21, 'casita', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(512) COLLATE utf16_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `role` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`) VALUES
(1, 'Pepe', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdas@gmail.com', 0),
(2, 'Pepa', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdas@gmail.com', 1),
(5, 'Ppoo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdas@gmail.com', 1),
(7, 'Peo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdas@gmail.com', 1),
(9, 'Pepo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdas@gmail.com', 1),
(10, 'Peeeeo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdas@gmail.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_parada_fav`
--

CREATE TABLE `user_parada_fav` (
  `id_parada_fav` bigint NOT NULL,
  `id_user` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_unicode_ci;

--
-- Volcado de datos para la tabla `user_parada_fav`
--

INSERT INTO `user_parada_fav` (`id_parada_fav`, `id_user`) VALUES
(20, 2),
(21, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `parada_fav`
--
ALTER TABLE `parada_fav`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `user_parada_fav`
--
ALTER TABLE `user_parada_fav`
  ADD KEY `FKiyvuykpereysupwflld23u8i1` (`id_user`),
  ADD KEY `FKqbowsxfq62eggus7dp07douwy` (`id_parada_fav`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `parada_fav`
--
ALTER TABLE `parada_fav`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `user_parada_fav`
--
ALTER TABLE `user_parada_fav`
  ADD CONSTRAINT `FKiyvuykpereysupwflld23u8i1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKqbowsxfq62eggus7dp07douwy` FOREIGN KEY (`id_parada_fav`) REFERENCES `parada_fav` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
