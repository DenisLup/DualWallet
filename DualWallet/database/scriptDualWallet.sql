DROP DATABASE DualWallet;
CREATE DATABASE IF NOT EXISTS DualWallet;

USE DualWallet;

-- 1. Tabla de usuarios (compañeros de piso)
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL
);

-- 2. Tabla de gastos generales
CREATE TABLE gasto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    concepto VARCHAR(150) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    fecha_gasto DATE NOT NULL,
    pagado_por INT NOT NULL, -- Usuario que adelantó el dinero
    CONSTRAINT fk_usuario_pago FOREIGN KEY (pagado_por) REFERENCES usuario(id) ON DELETE CASCADE
);

-- 3. Tabla intermedia para el reparto (Muchos a Muchos)
CREATE TABLE gasto_participante (
    gasto_id INT NOT NULL,
    usuario_id INT NOT NULL,
    monto_parte DECIMAL(10, 2) NOT NULL, -- Cuánto le toca pagar a este usuario en este gasto
    PRIMARY KEY (gasto_id, usuario_id),
    CONSTRAINT fk_gp_gasto FOREIGN KEY (gasto_id) REFERENCES gasto(id) ON DELETE CASCADE,
    CONSTRAINT fk_gp_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE pago (
	id_pago INT AUTO_INCREMENT PRIMARY KEY,
	pagador_id INT NOT NULL,
	receptor_id INT NOT NULL,
	monto DECIMAL (10,2) NOT NULL,
	fecha_pago DATE DEFAULT (CURRENT_DATE()),
	descripcion VARCHAR(100),
	CONSTRAINT fk_pagador FOREIGN KEY (pagador_id) REFERENCES usuario(id),
	CONSTRAINT fk_recibidor FOREIGN KEY (receptor_id) REFERENCES usuario(id)
);





