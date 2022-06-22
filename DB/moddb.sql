-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema moddb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `moddb` ;

-- -----------------------------------------------------
-- Schema moddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `moddb` DEFAULT CHARACTER SET utf8 ;
USE `moddb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(200) NULL,
  `last_name` VARCHAR(200) NULL,
  `username` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(200) NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL,
  `date_created` DATE NULL,
  `img_url` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genre` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform` ;

CREATE TABLE IF NOT EXISTS `platform` (
  `id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `publisher` VARCHAR(200) NULL,
  `company` VARCHAR(200) NULL,
  `multiplayer` TINYINT(1) NULL,
  `description` TEXT NULL,
  `img_url` TEXT NULL,
  `esrb_rating` VARCHAR(200) NULL,
  `category_id` INT NULL,
  `platform_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_game_category1_idx` (`category_id` ASC),
  INDEX `fk_game_platform1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_game_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_platform1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mod`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mod` ;

CREATE TABLE IF NOT EXISTS `mod` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NULL,
  `description` TEXT NULL,
  `date_created` DATE NULL,
  `date_updated` DATE NULL,
  `version` VARCHAR(45) NULL,
  `requirements` VARCHAR(200) NULL,
  `img_url` TEXT NULL,
  `price` DECIMAL NULL,
  `game_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mod_game1_idx` (`game_id` ASC),
  CONSTRAINT `fk_mod_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `score` DECIMAL NULL,
  `opinion` TEXT NULL,
  `user_id` INT NULL,
  `mod_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_review_mod1_idx` (`mod_id` ASC),
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_mod1`
    FOREIGN KEY (`mod_id`)
    REFERENCES `mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `discussion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `discussion` ;

CREATE TABLE IF NOT EXISTS `discussion` (
  `id` INT NOT NULL,
  `title` VARCHAR(200) NULL,
  `comment` TEXT NULL,
  `mod_id` INT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_discussion_mod1_idx` (`mod_id` ASC),
  INDEX `fk_discussion_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_discussion_mod1`
    FOREIGN KEY (`mod_id`)
    REFERENCES `mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discussion_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order` ;

CREATE TABLE IF NOT EXISTS `order` (
  `id` INT NOT NULL,
  `cc_owner_name` VARCHAR(200) NULL,
  `cc_number` VARCHAR(45) NULL,
  `cc_exp_date` DATE NULL,
  `cvc` VARCHAR(45) NULL,
  `billing_address` TEXT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_mod`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_mod` ;

CREATE TABLE IF NOT EXISTS `user_has_mod` (
  `user_id` INT NOT NULL,
  `mod_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `mod_id`),
  INDEX `fk_user_has_mod_mod1_idx` (`mod_id` ASC),
  INDEX `fk_user_has_mod_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_mod_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_mod_mod1`
    FOREIGN KEY (`mod_id`)
    REFERENCES `mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mod_has_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mod_has_order` ;

CREATE TABLE IF NOT EXISTS `mod_has_order` (
  `mod_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`mod_id`, `order_id`),
  INDEX `fk_mod_has_order_order1_idx` (`order_id` ASC),
  INDEX `fk_mod_has_order_mod1_idx` (`mod_id` ASC),
  CONSTRAINT `fk_mod_has_order_mod1`
    FOREIGN KEY (`mod_id`)
    REFERENCES `mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mod_has_order_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS admin@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (1, 'John', 'Doe', 'admin', 'admin', 'jdoe@email.com', 1, 'ROLE_ADMIN', '2022-06-21', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `category` (`id`, `genre`) VALUES (1, 'Adventure');

COMMIT;


-- -----------------------------------------------------
-- Data for table `platform`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `platform` (`id`, `name`) VALUES (1, 'PC');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `game` (`id`, `name`, `publisher`, `company`, `multiplayer`, `description`, `img_url`, `esrb_rating`, `category_id`, `platform_id`) VALUES (1, 'Elden Ring', 'Bandai Namco', 'FromSoftware', 1, 'Fun game.', NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mod`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`) VALUES (1, 'Rani Saves the Princess', 'Rani has special powers to save the princess.', '2022-01-02', '2022-02-03', '1.2', 'Processor Intel Potato Core', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `review` (`id`, `score`, `opinion`, `user_id`, `mod_id`) VALUES (1, 5, 'Awesome!', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `discussion`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `discussion` (`id`, `title`, `comment`, `mod_id`, `user_id`) VALUES (1, 'Mod Okay', 'I like the idea behind this mod, but I think there are better ones out there.', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `order`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `order` (`id`, `cc_owner_name`, `cc_number`, `cc_exp_date`, `cvc`, `billing_address`, `user_id`) VALUES (1, 'Joe Doe', '1234567891012', '2022-02-03', 'Joe Doe', '1234 Elm Lane', NULL);

COMMIT;

