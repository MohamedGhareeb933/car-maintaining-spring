-- ------------------------------
--  PRE DEFINED DATA 
-- ---------------------------

-- -------------------------
-- USER PREDEFINED DATA 
-- -----------------------------
INSERT INTO `maintaining-cars`.`user` (`id`, `username`, `password`, `active`) VALUES ('1', 'mohamed', '$2a$10$dcOhvw004qHVSVMC6zT3/eHQx3uaDw/MaxT0PqKeBfzQOu2lVnY2e', 1);
-- username : mohamed , password : mohamed
INSERT INTO `maintaining-cars`.`user` (`id`, `username`, `password`, `active`) VALUES ('2', 'ghareeb', '$2a$10$nTEBNtIQajQ54nD/otxrjOzJco0lBdf6rLu8a0fS50oA8XRawBHj2', 1);
-- username : ghareeb , password : ghareeb 
-- --------------------------
-- PREDEFINED DATA FOR ROLE
-- --------------------------
INSERT INTO `maintaining-cars`.`role` (`id`, `name`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `maintaining-cars`.`role` (`id`, `name`) VALUES ('2', 'ROLE_USER');

-- ------------
-- USER ROLE 
-- -------------
INSERT INTO `maintaining-cars`.`users_roles` (`user_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `maintaining-cars`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '1');
