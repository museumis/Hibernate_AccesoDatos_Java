-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-02-2018 a las 12:02:11
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID` int(2) NOT NULL,
  `NOMBRE` varchar(20) NOT NULL,
  `DIRECCION` varchar(30) DEFAULT NULL,
  `POBLACION` varchar(30) DEFAULT NULL,
  `TELEF` varchar(10) DEFAULT NULL,
  `NIF` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID`, `NOMBRE`, `DIRECCION`, `POBLACION`, `TELEF`, `NIF`) VALUES
(1, 'PEPE', 'C/SOL', 'CACERES', '927563225', '12345678A'),
(2, 'JUAN', 'C/LUNA', 'BADAJOZ', '921301154', '78965412B'),
(3, 'SARA', 'C/JUPITER', 'SALAMANCA', '987654123', '36521472C'),
(1111, 'aaa', 'aa', 'aa', '1111', '1212');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `REF` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `PRECIO` decimal(6,2) DEFAULT NULL,
  `EXISTENCIAS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`REF`, `NOMBRE`, `PRECIO`, `EXISTENCIAS`) VALUES
(10, 'PATATAS', '2.00', 8),
(20, 'FANTA', '1.00', 6),
(30, 'TOMATE', '8.00', 25),
(40, 'TARTA', '25.00', 3),
(333, 'ejemplo', '1000.00', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `IDVENTA` int(2) NOT NULL,
  `FECHAVENTA` date NOT NULL,
  `IDCLIENTE` int(2) DEFAULT NULL,
  `IDPRODUCTO` int(2) DEFAULT NULL,
  `CANTIDAD` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`IDVENTA`, `FECHAVENTA`, `IDCLIENTE`, `IDPRODUCTO`, `CANTIDAD`) VALUES
(11, '2015-11-09', 1, 20, 5),
(22, '2015-11-13', 1, 40, 2),
(33, '2015-12-30', 2, 10, 10),
(44, '2015-11-29', 3, 20, 9),
(111, '2018-02-26', 1, 20, 10),
(222, '2018-02-25', 1, 20, 12),
(333, '2018-02-25', 1, 10, 100),
(11122, '2018-02-25', 3, 10, 1212);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`REF`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`IDVENTA`),
  ADD KEY `fk_cli` (`IDCLIENTE`),
  ADD KEY `fk_pro` (`IDPRODUCTO`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_cli` FOREIGN KEY (`IDCLIENTE`) REFERENCES `clientes` (`ID`),
  ADD CONSTRAINT `fk_pro` FOREIGN KEY (`IDPRODUCTO`) REFERENCES `productos` (`REF`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
