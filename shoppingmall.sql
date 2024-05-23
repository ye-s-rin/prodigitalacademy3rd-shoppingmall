-- 쇼핑몰 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS shoppingmalldb;

-- 생성한 데이터베이스 사용
USE shoppingmalldb;

-- 사용자 테이블 생성
CREATE TABLE IF NOT EXISTS User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL UNIQUE,
    pw VARCHAR(50) NOT NULL,
    name VARCHAR(10) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    contact VARCHAR(13) NOT NULL
);

SELECT * FROM User;

-- 사용자 테이블 생성
CREATE TABLE IF NOT EXISTS Product (
    id INT PRIMARY KEY,
    name VARCHAR(10) NOT NULL,
    price INT NOT NULL,
    summary VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    category_id INT NOT NULL
);

SELECT * FROM Product;