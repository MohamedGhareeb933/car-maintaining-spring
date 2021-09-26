
CREATE SCHEMA IF NOT EXISTS `maintaining-cars`;

USE  `maintaining-cars`;

-- ----------------
-- CAR TABLE
-- ---------------
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

-- ------------------------
-- DRIVER TABLE 
-- ----------------------
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

-- -------------------------
-- USER TABLE FOR SECURITY 
-- ---------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `active` BIT NOT NULL, 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- --------------------
-- ROLE TABLE FOR AUTH
-- ---------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- --------------------------------
-- USER ROLE TABLE - MANY TO MANY 
-- ---------------------------------
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
