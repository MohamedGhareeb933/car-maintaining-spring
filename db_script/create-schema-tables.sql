
CREATE SCHEMA IF NOT EXISTS `maintaining-cars`;

USE  `maintaining-cars`;

CREATE TABLE IF NOT EXISTS `car` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `license_plate` VARCHAR(255) DEFAULT NULL,
    `convertible` BIT DEFAULT NULL, 
    `rating` SMALLINT DEFAULT NULL,
    `engine_type` VARCHAR(255) DEFAULT NULL,
    `manufacturer` VARCHAR(255) DEFAULT NULL, 
    `date_created` DATETIME DEFAULT NULL,
    PRIMARY KEY(`ID`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `driver`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `fname` VARCHAR(255) DEFAULT NULL,
    `lname` VARCHAR(255) DEFAULT NULL,
    `phone` VARCHAR(255) DEFAULT NULL,
	`date_created` DATETIME DEFAULT NULL,
    `car_id` BIGINT DEFAULT NULL, 
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_car_id` FOREIGN KEY (`car_id`) REFERENCES `car`(`id`)
)engine=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
