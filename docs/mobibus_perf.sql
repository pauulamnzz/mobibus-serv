-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 28-01-2024 a las 13:39:43
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
  `alias` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_parada` bigint NOT NULL,
  `id_user` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `parada_fav`
--

INSERT INTO `parada_fav` (`id`, `alias`, `id_parada`, `id_user`) VALUES
(2, 'fuimmm', 22, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `role` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`) VALUES
(1, 'Paula', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdd@gmail.com', 0),
(2, 'Hugo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'taric@gmail.com', 1),
(3, 'asdasd', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdasd@asd.com', 0);

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
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `parada_fav`
--
ALTER TABLE `parada_fav`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
