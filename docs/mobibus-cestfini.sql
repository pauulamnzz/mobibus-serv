-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 22-02-2024 a las 00:27:51
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
CREATE DATABASE mobibus;
USE mobibus;
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
(1, 'Casa-Centro', 17, 1),
(2, 'Gym-Casa', 10, 1),
(3, 'Trabajo', 25, 2),
(4, 'Casa de la Abuela', 18, 3),
(5, 'Parque Central', 12, 4),
(6, 'Supermercado', 8, 5),
(7, 'Centro Comercial', 4, 6),
(8, 'Casa de Ana', 21, 7),
(9, 'Parque de los Patos', 14, 8),
(10, 'Estación de Tren', 7, 9),
(11, 'Trabajo', 22, 10),
(12, 'Casa', 2, 11),
(13, 'Gimnasio', 5, 12),
(14, 'Cine', 13, 13),
(15, 'Biblioteca', 6, 14),
(16, 'Casa', 20, 15),
(17, 'Parque', 9, 16),
(18, 'Tienda de Mascotas', 16, 17),
(19, 'Casa de los Abuelos', 19, 18),
(20, 'Trabajo', 3, 19),
(21, 'Casa de María', 24, 20),
(22, 'Colegio', 11, 21),
(23, 'Gimnasio', 15, 22),
(24, 'Parque', 1, 23),
(25, 'Supermercado', 23, 24),
(26, 'Casa', 10, 25),
(27, 'Centro Comercial', 2, 26),
(28, 'Trabajo', 8, 27),
(29, 'Parque', 16, 28),
(31, 'Casa de la Playa', 15, 1),
(32, 'Gimnasio', 13, 2),
(33, 'Supermercado', 11, 3),
(34, 'Centro Comercial', 9, 4),
(35, 'Casa', 7, 5),
(36, 'Trabajo', 5, 6),
(37, 'Estación de Autobuses', 3, 7),
(38, 'Parque de la Ciudad', 1, 8),
(39, 'Cine', 17, 9),
(40, 'Casa', 16, 10),
(41, 'Biblioteca', 15, 11),
(42, 'Parque', 14, 12),
(43, 'Tienda de Mascotas', 13, 13),
(44, 'Casa de los Abuelos', 12, 14),
(45, 'Gimnasio', 11, 15),
(46, 'Parque', 10, 16);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL UNIQUE,
  `password` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL UNIQUE,
  `role` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`) VALUES
(1, 'Paula', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'asdd@gmail.com', 0),
(2, 'Hugo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'taric@gmail.com', 1),
(3, 'Ramón', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'ramon@gmail.com', 1),
(4, 'Eva', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'eva@hotmail.com', 0),
(5, 'Jorge', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'jorge@yahoo.com', 1),
(6, 'Isabel', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'isabel@gmail.com', 0),
(7, 'Paco', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'paco@hotmail.com', 1),
(8, 'Cristina', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'cristina@yahoo.com', 0),
(9, 'Manuela', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'manuela@gmail.com', 1),
(10, 'Antonio', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'antonio@hotmail.com', 0),
(11, 'Sandra', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'sandra@yahoo.com', 1),
(12, 'Diego', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'diego@gmail.com', 0),
(13, 'María José', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'mariajose@hotmail.com', 1),
(14, 'Pepe', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'pepe@yahoo.com', 0),
(15, 'Lucas', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'lucas@gmail.com', 1),
(16, 'Carmen', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'carmen@hotmail.com', 0),
(17, 'Alberto', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'alberto@yahoo.com', 1),
(18, 'Nuria', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'nuria@gmail.com', 0),
(19, 'Juan', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'juan@hotmail.com', 1),
(20, 'Laura', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'laura@yahoo.com', 0),
(21, 'Sergio', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'sergio@gmail.com', 1),
(22, 'Raquel', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'raquel@hotmail.com', 0),
(23, 'Pablo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'pablo@yahoo.com', 1),
(24, 'Celia', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'celia@gmail.com', 0),
(25, 'Antonia', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'antonia@hotmail.com', 1),
(26, 'Roberto', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'roberto@yahoo.com', 0),
(27, 'Marta', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'marta@gmail.com', 1),
(28, 'Javier', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'javier@hotmail.com', 0);

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
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;