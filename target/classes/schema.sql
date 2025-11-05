-- ===========================================
-- SCRIPT DE CRIAÇÃO DAS TABELAS
-- Projeto: Sistema de Rastreamento de Encomendas
-- ===========================================

CREATE TABLE IF NOT EXISTS orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tracking_code VARCHAR(20) UNIQUE NOT NULL,
    client_name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ocurrence (
    ocurrence_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tracking_code VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    ocurrence_time TIMESTAMP,
    FOREIGN KEY (tracking_code) REFERENCES orders(tracking_code)
);

