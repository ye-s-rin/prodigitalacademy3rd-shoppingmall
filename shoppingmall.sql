-- 쇼핑몰 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS shoppingmall;

-- 생성한 데이터베이스 사용
USE shoppingmall;

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