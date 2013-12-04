-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2013 a las 01:08:52
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `timelineme`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agente`
--

CREATE TABLE IF NOT EXISTS `agente` (
  `iId` int(11) NOT NULL AUTO_INCREMENT,
  `cNombre` varchar(250) DEFAULT NULL,
  `cEmail` varchar(250) DEFAULT NULL,
  `cPassword` varchar(250) DEFAULT NULL,
  `fkEmpresa` int(11) DEFAULT NULL,
  `iActivo` int(11) DEFAULT NULL,
  `iAdmin` int(11) DEFAULT NULL,
  PRIMARY KEY (`iId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `agente`
--

INSERT INTO `agente` (`iId`, `cNombre`, `cEmail`, `cPassword`, `fkEmpresa`, `iActivo`, `iAdmin`) VALUES
(1, 'Daniel Rodriguez', 'drodriguez@test.com', 'drodriguez', 1, 1, 1),
(2, 'Christian Carruego', 'ccarruego@test.com', 'ccarruego', 1, 1, 0),
(3, 'Fernando Apaolaza', 'fapaolaza@test.com', 'fapaolaza', 2, 1, 0),
(4, 'Jorge Miranda', 'jmiranda@test.com', 'jmiranda', 2, 1, 0),
(5, 'Rodolfo Bais', 'rbais@test.com', 'rbais', 3, 1, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `iId` int(11) NOT NULL AUTO_INCREMENT,
  `cNombre` varchar(250) DEFAULT NULL,
  `cRazonsocial` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`iId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`iId`, `cNombre`, `cRazonsocial`) VALUES
(1, 'UNLaM', '999-999-999'),
(2, 'Caos', '888-888-888'),
(3, 'Bais S.A.', '777-777-777');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicacion`
--

CREATE TABLE IF NOT EXISTS `publicacion` (
  `iId` int(11) NOT NULL AUTO_INCREMENT,
  `cComentario` text,
  `fkEmpresa` int(11) DEFAULT NULL,
  `fkAgente` int(11) DEFAULT NULL,
  `dFecha` datetime DEFAULT NULL,
  PRIMARY KEY (`iId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=42 ;

--
-- Volcado de datos para la tabla `publicacion`
--

INSERT INTO `publicacion` (`iId`, `cComentario`, `fkEmpresa`, `fkAgente`, `dFecha`) VALUES
(1, 'Mi primer comentario', 1, 1, '2013-11-25 22:26:09'),
(2, 'Segundo Comentario', 1, 1, '2013-11-26 08:26:11'),
(3, 'sadasd', 1, 1, '2013-11-25 22:26:09'),
(4, 'dsad', 1, 1, '2013-11-25 22:26:09'),
(5, 'Otro comentario', 1, 1, '2013-11-25 22:26:09'),
(6, 'Más comentarios!!', 1, 1, '2013-10-27 01:27:25'),
(7, 'OTRO MÁAAS!!', 1, 1, '2013-10-27 01:33:31'),
(8, 'WIII', 1, 1, '2013-10-27 01:36:16'),
(9, 'asdasd', 1, 1, '2013-10-27 19:16:32'),
(10, 'asdasd', 1, 1, '2013-10-27 19:17:53'),
(11, 'asdasd', 1, 1, '2013-10-27 19:19:50'),
(12, 'asdasd', 1, 1, '2013-10-27 19:20:08'),
(13, 'asdasd', 1, 1, '2013-10-27 19:20:22'),
(14, 'asdasd', 1, 1, '2013-10-27 19:21:07'),
(15, 'asdasd', 1, 1, '2013-10-27 19:21:46'),
(16, 'asdasd', 1, 1, '2013-10-27 19:23:49'),
(17, 'asdasd', 1, 1, '2013-10-27 19:25:09'),
(18, 'asdasd', 1, 1, '2013-10-27 19:26:32'),
(19, 'asdasd', 1, 1, '2013-10-27 19:27:48'),
(20, 'asdasd', 1, 1, '2013-10-27 19:29:21'),
(21, 'asdasd', 1, 1, '2013-10-27 19:29:36'),
(22, 'asdasd', 1, 1, '2013-10-27 19:29:55'),
(23, 'adas', 1, 1, '2013-10-27 19:31:41'),
(24, 'último comentario', 1, 1, '2013-10-27 19:32:58'),
(25, 'último comentario', 1, 1, '2013-10-27 19:33:45'),
(26, 'A veer', 1, 1, '2013-10-27 20:03:30'),
(27, 'Christian firmando!', 1, 2, '2013-10-27 20:06:18'),
(28, 'Mi primer comentario', 2, 3, '2013-10-27 20:42:34'),
(29, 'Ultimo', 1, 1, '2013-10-27 20:58:44'),
(30, 'Mi primer comentario', 3, 5, '2013-10-27 23:15:04'),
(31, 'Otro comentario más, a mi empresa no la sigue nadie', 3, 5, '2013-10-27 23:15:17'),
(32, 'Otrom más', 1, 1, '2013-10-28 00:55:18'),
(33, 'escriboo', 1, 1, '2013-10-28 01:01:24'),
(34, 'ultimo comentario test', 1, 1, '2013-10-28 19:54:48'),
(35, 'Mi uñtimo comennt', 1, 1, '2013-11-03 00:15:57'),
(36, 'Mi uñtimo comennt', 1, 1, '2013-11-03 00:16:15'),
(37, 'a verrr', 1, 1, '2013-11-03 00:36:06'),
(38, 'a verrr', 1, 1, '2013-11-03 00:38:55'),
(39, 'vamos, uno más de test', 1, 1, '2013-11-03 00:56:24'),
(40, 'Otra comentario', 2, 3, '2013-11-03 01:07:10'),
(41, 'a ver otro', 1, 1, '2013-11-03 01:16:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguir`
--

CREATE TABLE IF NOT EXISTS `seguir` (
  `iId` int(11) NOT NULL AUTO_INCREMENT,
  `fkSeguidor` int(11) DEFAULT NULL,
  `fkSeguido` int(11) DEFAULT NULL,
  `dFecha` datetime DEFAULT NULL,
  PRIMARY KEY (`iId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `seguir`
--

INSERT INTO `seguir` (`iId`, `fkSeguidor`, `fkSeguido`, `dFecha`) VALUES
(1, 1, 2, '2013-11-27 04:10:12'),
(4, 5, 1, '2013-10-28 00:57:49'),
(7, 1, 2, '2013-10-28 20:11:36'),
(8, 1, 2, '2013-11-02 22:38:15'),
(9, 3, 3, '2013-11-14 00:58:09');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
