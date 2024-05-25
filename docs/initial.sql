-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 14-05-2024 a las 20:49:17
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
(46, 'Parque', 10, 16),
(48, 'aaa', 13, 7),
(50, 'casita', 14, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci NOT NULL,
  `role` tinyint NOT NULL,
  `token_password` varchar(255) CHARACTER SET utf16 COLLATE utf16_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `email`, `role`, `token_password`) VALUES
(1, 'Paula', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'pauula.mnzz@gmail.com', 0, 'fdf9202e-bc67-4e79-a4a3-88a90ae686ae'),
(2, 'Hugo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'taric@gmail.com', 1, ''),
(3, 'Ramón', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'ramon@gmail.com', 1, ''),
(4, 'Eva', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'eva@hotmail.com', 0, ''),
(5, 'Jorge', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'jorge@yahoo.com', 1, ''),
(6, 'Isabel', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'isabel@gmail.com', 0, ''),
(7, 'Paco', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'paco@hotmail.com', 1, ''),
(8, 'Cristina', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'cristina@yahoo.com', 0, ''),
(9, 'Manuela', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'manuela@gmail.com', 1, ''),
(10, 'Antonio', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'antonio@hotmail.com', 0, ''),
(11, 'Sandra', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'sandra@yahoo.com', 1, ''),
(12, 'Diego', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'diego@gmail.com', 0, ''),
(13, 'María José', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'mariajose@hotmail.com', 1, ''),
(14, 'Pepe', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'pepe@yahoo.com', 0, ''),
(15, 'Lucas', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'lucas@gmail.com', 1, ''),
(16, 'Carmen', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'carmen@hotmail.com', 0, ''),
(17, 'Alberto', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'alberto@yahoo.com', 1, ''),
(18, 'Nuria', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'nuria@gmail.com', 0, ''),
(19, 'Juan', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'juan@hotmail.com', 1, ''),
(20, 'Laura', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'laura@yahoo.com', 0, ''),
(21, 'Sergio', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'sergio@gmail.com', 1, ''),
(22, 'Raquel', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'raquel@hotmail.com', 0, ''),
(23, 'Pablo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'pablo@yahoo.com', 1, ''),
(24, 'Celia', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'celia@gmail.com', 0, ''),
(25, 'Antonia', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'antonia@hotmail.com', 1, ''),
(26, 'Roberto', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'roberto@yahoo.com', 0, ''),
(27, 'Marta', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'marta@gmail.com', 1, ''),
(28, 'Javier', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'javier@hotmail.com', 0, ''),
(33, 'aaa', '27a104fe5c6970d18c073f686cca72ec9c4b1ec187b7537ed6144fe76baac8c1', 'luis@yahoo.com', 0, ''),
(35, 'aaaa--', '27a104fe5c6970d18c073f686cca72ec9c4b1ec187b7537ed6144fe76baac8c1', 'asdasd@asd', 0, ''),
(36, 'asdasq23.', '27a104fe5c6970d18c073f686cca72ec9c4b1ec187b7537ed6144fe76baac8c1', 'sdf@asdasd', 0, ''),
(37, 'achus', '27a104fe5c6970d18c073f686cca72ec9c4b1ec187b7537ed6144fe76baac8c1', 'asdasd@gma', 1, ''),
(42, 'Luca', 'paula123', 'luca@gmail.com', 1, ''),
(44, 'Pepox', 'aaaaa', 'asdasd12@asd', 1, ''),
(45, 'Luchia', 'pepito123', 'xd@gmail.com', 1, ''),
(46, 'Pepoxs', 'asdasd', 'csdasd@gmai', 1, ''),
(47, 'Pepioiooz', 'sasdasd', 'aaaaaasdasdasd@gmail.com', 1, ''),
(48, 'XUlitaaa', 'aaaaaaaaa', 'aaaaaaasdasd@gmail.com', 1, ''),
(49, 'Gato', 'asdasdasd', 'asdsdasdasd@gmail.com', 1, ''),
(50, 'EKISDE', 'AAAA123', 'aasdasdasd@gmail.com', 1, ''),
(51, 'pEPITTTTAA', 'paula1234', 'aasdasdasd@gmail.coma', 1, ''),
(52, 'Mesuicido', 'asdasd', 'aaa@as', 1, ''),
(53, 'aaasdasdasd', 'aaaaaaaaaaaaaaa', '00asdasdasd@gmail.com', 1, ''),
(54, 'Ppppp', 'asdasd', 'pepe123@gmail.com', 1, ''),
(55, 'AED', '123456', 'csdasd@gmaia', 1, ''),
(56, 'asdasd', 'paula2004', 'aaaaasdasdasd@gmail.com', 1, ''),
(57, 'AAAA', '12345123', 'aaaaadasdasd@gmail.co', 1, ''),
(58, 'ASDASDASD', 'ASDASD', 'pepe12345@gmail.com', 1, ''),
(59, 'LUCIII', 'AAAAAA', 'aaaaaasasddasdasd@gmail.com', 1, ''),
(60, 'ajulio', 'ajulio', 'ajulio@gmail.cm', 1, ''),
(61, 'apepe', 'apepe', 'apepe@gsd', 1, ''),
(62, 'sdaaaa', 'asdaasdasdasd', 'aasdasdasdsd@gmail.caaom', 1, ''),
(63, 'pene', 'penea', 'pnaasd2@asd', 1, ''),
(64, 'ssssssss', 'sssssssss', 'sssssss@gma', 1, ''),
(65, 'asdas', 'aasass', 'asdasd@gf', 1, ''),
(66, 'asdeerere', 'asderada', 'asdwere@gmail', 1, ''),
(67, 'puta', 'putaa', 'puta@gmail.com', 1, ''),
(68, 'aaaaaaaaaaaa', 'aaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaa@gma', 1, ''),
(69, 'aaaaa', 'aaaaaaaa', 'aaa@asa', 1, ''),
(70, 'aaaaaaa', 'aaaaaaaaaa', 'aaaaaaaaaa@asd', 1, ''),
(71, 'asda', 'asdaaaaas', 'aasdasd@gf', 1, ''),
(72, 'EEEEEE', 'EEEEEEE', 'EEEEEEEE@ASD', 1, ''),
(73, 'ASWWWWW', 'WWWWW', 'WWW@GMAI.COM', 1, ''),
(74, 'ASDASDASQWWW', 'WWWWW', 'WWASD@GF', 1, ''),
(75, 'AASDASD', 'AWSDEASDASD', 'SZSDASD@GMA', 1, ''),
(76, 'ASDASDASDQ3WW', 'AAAAAA', 'aaasdasdasd@gmail.com', 1, ''),
(77, 'UTAA', 'AAAUTAA', 'UTAA@GMAIL.COM', 1, ''),
(78, 'TUSMUELAS', 'TUSMULEAS', 'TUSMUELAS@GAS', 1, ''),
(79, 'ASDASDA', 'ASDASDASD', 'ASDASDASD@AS', 1, ''),
(80, 'AYUD', 'AYUDA', 'AYUDA@GMAIL.C', 1, ''),
(81, 'ayuayu', 'c6cb2ad2ec2606bc828b78aca4b80d514672c642c03abd34cd10434073a607b6', 'ayu@gsa', 1, ''),
(82, 'aaasdasd', 'c3056e9471c1420830792ee9deefa14196737fc47ca3d3a4da242f46b4cea88a', 'dasadas22asddasdas@az55', 1, ''),
(83, 'gorda', 'a101beba8fa6975ec394f124182427a4d83629f49226c3e71c1d5183ae22fe50', 'gorda@gfad', 1, ''),
(84, 'llorando', '57a09c6465bf491ba1caca7a47904e507d908856b562a81cb683ed3dafa41d99', 'llorando@gmail.com', 1, ''),
(85, 'Jose', '8cc6d99489b5b7168835743bdb42b2e8b89d6b97eb95a6033c0581ece863fb4d', 'josee@gmail.com', 1, ''),
(86, 'JUlio', '901be86d450c504e8555ffeeeab1e06b926c8785fd99ef382c1310b7c66bc167', 'julio@gmai.com', 1, ''),
(87, 'Jose123', '7f911f82c99bedf7706b049138310ffc523f385840357f0345694c0a413cb212', 'ppauulamg@gmail.com', 1, NULL);

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
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `parada_fav`
--
ALTER TABLE `parada_fav`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
