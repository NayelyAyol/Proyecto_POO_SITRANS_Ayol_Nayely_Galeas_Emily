-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: bye3meozep3t8sfrhixc-mysql.services.clever-cloud.com:3306
-- Tiempo de generación: 31-07-2025 a las 12:15:26
-- Versión del servidor: 8.4.2-2
-- Versión de PHP: 8.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bye3meozep3t8sfrhixc`
--
CREATE DATABASE IF NOT EXISTS `bye3meozep3t8sfrhixc` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `bye3meozep3t8sfrhixc`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE `administradores` (
  `id` int NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `rol` varchar(13) NOT NULL DEFAULT 'Administrador',
  `estado` enum('activo','inactivo') DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`id`, `nombres`, `apellidos`, `correo`, `clave`, `telefono`, `rol`, `estado`) VALUES
(1, 'Admin', 'a', 'administrador@sitrans.com', 'admin123', '0991248723', 'Administrador', 'activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alertas`
--

CREATE TABLE `alertas` (
  `id` int NOT NULL,
  `tipo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` text NOT NULL,
  `estado` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Pendiente',
  `ruta_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `alertas`
--

INSERT INTO `alertas` (`id`, `tipo`, `fecha`, `descripcion`, `estado`, `ruta_id`) VALUES
(1, 'Retraso', '2025-07-30 18:41:26', 'Ruta San Carlos con retraso debido a accidente de tránsito en la Mariscal Sucre.', 'Atendida', 1),
(2, 'Retraso', '2025-07-30 18:50:26', 'Una llanta se daño', 'Atendida', 1),
(3, 'Estudiante sin asistir', '2025-07-30 23:41:43', 'Juana no se encontraba en la parada, por lo que no asistió al transporte.', 'Pendiente', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conductores`
--

CREATE TABLE `conductores` (
  `id` int NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `n_licencia` varchar(10) NOT NULL,
  `tipo_sangre` enum('A+','A-','B+','B-','AB+','AB-','O+','O-') NOT NULL,
  `rol` varchar(9) NOT NULL DEFAULT 'Conductor',
  `estado` enum('activo','inactivo') DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `conductores`
--

INSERT INTO `conductores` (`id`, `nombres`, `apellidos`, `cedula`, `telefono`, `correo`, `clave`, `n_licencia`, `tipo_sangre`, `rol`, `estado`) VALUES
(1, 'Juan', 'Pérez', '1733556789', '0987612456', 'juan.perez@sitrans.com', 'juan123', '1733556789', 'A+', 'Conductor', 'activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiantes`
--

CREATE TABLE `estudiantes` (
  `id` int NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `genero` enum('Masculino','Femenino') NOT NULL,
  `curso` varchar(20) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `ruta_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `estudiantes`
--

INSERT INTO `estudiantes` (`id`, `nombres`, `apellidos`, `cedula`, `genero`, `curso`, `telefono`, `correo`, `direccion`, `ruta_id`) VALUES
(1, 'Juana', 'García', '1798884652', 'Femenino', '1ro BGU \"A\"', '0998456321', 'juana.garcia@gmail.com', 'Av. América', 1),
(2, 'Pedro', 'Pérez', '1748652301', 'Masculino', '2do BGU \"B\"', '0994763125', 'juan.perez@gmail.com', 'Av. Mariscal Sucre y Melchor de Valdez', 2),
(3, 'Manuel', 'Pérez', '178465982', 'Masculino', '3ro BGU \"C\"', '066548123', 'manuel@gmail.com', 'Av. Inca', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monitores`
--

CREATE TABLE `monitores` (
  `id` int NOT NULL,
  `nombres` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(20) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `rol` varchar(7) NOT NULL DEFAULT 'Monitor',
  `estado` enum('activo','inactivo') DEFAULT 'activo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `monitores`
--

INSERT INTO `monitores` (`id`, `nombres`, `apellidos`, `correo`, `clave`, `telefono`, `rol`, `estado`) VALUES
(1, 'Ana', 'Garcia', 'ana.garcia@sitrans.com', 'ana123', '0987612456', 'Monitor', 'activo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutas`
--

CREATE TABLE `rutas` (
  `id` int NOT NULL,
  `nombre_ruta` varchar(50) NOT NULL,
  `placas_vehiculo` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `origen` varchar(50) NOT NULL,
  `destino` varchar(50) NOT NULL,
  `capacidad_max` int NOT NULL,
  `dia` varchar(10) DEFAULT NULL,
  `hora_salida` time NOT NULL,
  `hora_llegada` time NOT NULL,
  `estado_actual` enum('No iniciado','En progreso','Finalizada') NOT NULL DEFAULT 'No iniciado',
  `monitor_id` int NOT NULL,
  `conductor_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `rutas`
--

INSERT INTO `rutas` (`id`, `nombre_ruta`, `placas_vehiculo`, `origen`, `destino`, `capacidad_max`, `dia`, `hora_salida`, `hora_llegada`, `estado_actual`, `monitor_id`, `conductor_id`) VALUES
(1, 'San Carlos', 'PBT-1298', 'Av. Mariscal Sucre', 'Colegio', 15, 'Lunes', '06:00:00', '07:00:00', 'En progreso', 1, 1),
(2, 'Condado Shopping', 'PBB-9841', 'Centro Comercial Condado Shopping', 'Universidad', 25, 'Martes', '06:00:00', '07:30:00', 'No iniciado', 1, 1),
(3, 'Quitumbe', 'PAB-9845', 'Terminal Quitumbe', 'Colegio', 25, 'Miercoles', '05:00:00', '08:30:00', 'En progreso', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `alertas`
--
ALTER TABLE `alertas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_alertas_ruta` (`ruta_id`);

--
-- Indices de la tabla `conductores`
--
ALTER TABLE `conductores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `n_licencia` (`n_licencia`);

--
-- Indices de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `ruta_id` (`ruta_id`);

--
-- Indices de la tabla `monitores`
--
ALTER TABLE `monitores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `rutas`
--
ALTER TABLE `rutas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `monitor_id` (`monitor_id`),
  ADD KEY `conductor_id` (`conductor_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administradores`
--
ALTER TABLE `administradores`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `alertas`
--
ALTER TABLE `alertas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `conductores`
--
ALTER TABLE `conductores`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `monitores`
--
ALTER TABLE `monitores`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `rutas`
--
ALTER TABLE `rutas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alertas`
--
ALTER TABLE `alertas`
  ADD CONSTRAINT `fk_alertas_ruta` FOREIGN KEY (`ruta_id`) REFERENCES `rutas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD CONSTRAINT `estudiantes_ibfk_1` FOREIGN KEY (`ruta_id`) REFERENCES `rutas` (`id`);

--
-- Filtros para la tabla `rutas`
--
ALTER TABLE `rutas`
  ADD CONSTRAINT `rutas_ibfk_1` FOREIGN KEY (`monitor_id`) REFERENCES `monitores` (`id`),
  ADD CONSTRAINT `rutas_ibfk_2` FOREIGN KEY (`conductor_id`) REFERENCES `conductores` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
