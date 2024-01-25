-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 25-01-2024 a las 13:24:50
-- Versión del servidor: 8.2.0
-- Versión de PHP: 8.2.14

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
(1, 'Paula', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdd@gmail.com', 1),
(2, 'Hugo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'taric@gmail.com', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_parada_fav`
--

CREATE TABLE `user_parada_fav` (
  `id` bigint NOT NULL,
  `alias` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `id_parada` bigint NOT NULL,
  `id_user` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user_parada_fav`
--

INSERT INTO `user_parada_fav` (`id`, `alias`, `id_parada`, `id_user`) VALUES
(1, 'casita', 11, 1),
(2, 'gymmm', 11, 2),
(3, 'centro', 11, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user_parada_fav`
--
ALTER TABLE `user_parada_fav`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `user_parada_fav`
--
ALTER TABLE `user_parada_fav`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
