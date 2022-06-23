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
-- Table `platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform` ;

CREATE TABLE IF NOT EXISTS `platform` (
  `id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `developer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `developer` ;

CREATE TABLE IF NOT EXISTS `developer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `img_url` VARCHAR(2000) NULL,
  `web_link` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `publisher` ;

CREATE TABLE IF NOT EXISTS `publisher` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `img_url` VARCHAR(2000) NULL,
  `web_link` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `esrb_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `esrb_rating` ;

CREATE TABLE IF NOT EXISTS `esrb_rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `multiplayer` TINYINT(1) NULL,
  `description` TEXT NULL,
  `img_url` TEXT NULL,
  `platform_id` INT NOT NULL,
  `developer_id` INT NOT NULL,
  `publisher_id` INT NOT NULL,
  `esrb_rating_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_game_platform1_idx` (`platform_id` ASC),
  INDEX `fk_game_developer1_idx` (`developer_id` ASC),
  INDEX `fk_game_publisher1_idx` (`publisher_id` ASC),
  INDEX `fk_game_esrb_rating1_idx` (`esrb_rating_id` ASC),
  CONSTRAINT `fk_game_platform1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_developer1`
    FOREIGN KEY (`developer_id`)
    REFERENCES `developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_publisher1`
    FOREIGN KEY (`publisher_id`)
    REFERENCES `publisher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_esrb_rating1`
    FOREIGN KEY (`esrb_rating_id`)
    REFERENCES `esrb_rating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_mod`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_mod` ;

CREATE TABLE IF NOT EXISTS `game_mod` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NULL,
  `description` TEXT NULL,
  `date_created` DATETIME NULL,
  `date_updated` DATETIME NULL,
  `version` VARCHAR(45) NULL,
  `requirements` VARCHAR(200) NULL,
  `img_url` TEXT NULL,
  `price` DECIMAL(6,2) NULL,
  `game_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `download_link` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mod_game1_idx` (`game_id` ASC),
  INDEX `fk_mod_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_mod_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mod_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `score` INT NULL,
  `opinion` TEXT NULL,
  `user_id` INT NOT NULL,
  `mod_id` INT NOT NULL,
  `review_date` DATETIME NULL,
  PRIMARY KEY (`user_id`, `mod_id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_review_mod1_idx` (`mod_id` ASC),
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_mod1`
    FOREIGN KEY (`mod_id`)
    REFERENCES `game_mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL,
  `title` VARCHAR(200) NULL,
  `comment` TEXT NULL,
  `mod_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `comment_date` DATETIME NULL,
  `reply_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_discussion_mod1_idx` (`mod_id` ASC),
  INDEX `fk_discussion_user1_idx` (`user_id` ASC),
  INDEX `fk_post_post1_idx` (`reply_to_id` ASC),
  CONSTRAINT `fk_discussion_mod1`
    FOREIGN KEY (`mod_id`)
    REFERENCES `game_mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_discussion_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_post1`
    FOREIGN KEY (`reply_to_id`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mod_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mod_order` ;

CREATE TABLE IF NOT EXISTS `mod_order` (
  `id` INT NOT NULL,
  `cc_owner_name` VARCHAR(200) NULL,
  `cc_number` VARCHAR(45) NULL,
  `cc_exp_date` DATETIME NULL,
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
    REFERENCES `game_mod` (`id`)
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
    REFERENCES `game_mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mod_has_order_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `mod_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game_has_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game_has_category` ;

CREATE TABLE IF NOT EXISTS `game_has_category` (
  `game_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`game_id`, `category_id`),
  INDEX `fk_game_has_category_category1_idx` (`category_id` ASC),
  INDEX `fk_game_has_category_game1_idx` (`game_id` ASC),
  CONSTRAINT `fk_game_has_category_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mod_media`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mod_media` ;

CREATE TABLE IF NOT EXISTS `mod_media` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `media_url` VARCHAR(2000) NULL,
  `description` TEXT NULL,
  `mod_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_mod_media_mod1_idx` (`mod_id` ASC),
  INDEX `fk_mod_media_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_mod_media_mod1`
    FOREIGN KEY (`mod_id`)
    REFERENCES `game_mod` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mod_media_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
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
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (1, 'John', 'Doe', 'admin', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'jdoe@email.com', 1, 'ROLE_ADMIN', '2022-06-21', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `platform`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `platform` (`id`, `name`) VALUES (1, 'PC');
INSERT INTO `platform` (`id`, `name`) VALUES (2, 'Xbox');
INSERT INTO `platform` (`id`, `name`) VALUES (3, 'PlayStation');

COMMIT;


-- -----------------------------------------------------
-- Data for table `developer`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `developer` (`id`, `name`, `img_url`, `web_link`) VALUES (1, 'Pony', 'test', 'test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `publisher`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `publisher` (`id`, `name`, `img_url`, `web_link`) VALUES (1, 'Cupcake', 'test', 'test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `esrb_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `esrb_rating` (`id`, `name`) VALUES (1, 'Everyone');
INSERT INTO `esrb_rating` (`id`, `name`) VALUES (2, 'Teen');
INSERT INTO `esrb_rating` (`id`, `name`) VALUES (3, 'Mature');

COMMIT;


-- -----------------------------------------------------
-- Data for table `game`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (1, 'Elden Ring', 1, 'Fun game.', 'test', 1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_mod`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (1, 'Rani Saves the Princess', 'Rani has special powers to save the princess.', '2022-01-02', '2022-02-03', '1.2', 'Processor Intel Potato Core', 'test', 10.99, 1, 1, 'test');

COMMIT;


-- -----------------------------------------------------
-- Data for table `review`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'Awesome!', 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `category` (`id`, `genre`) VALUES (1, 'Adventure');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (1, 'Mod Okay', 'I like the idea behind this mod, but I think there are better ones out there.', 1, 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mod_order`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `cc_number`, `cc_exp_date`, `cvc`, `billing_address`, `user_id`) VALUES (1, 'Joe Doe', '1234567891012', '2022-02-03', 'Joe Doe', '1234 Elm Lane', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_mod`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `user_has_mod` (`user_id`, `mod_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mod_has_order`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `mod_has_order` (`mod_id`, `order_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_has_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `game_has_category` (`game_id`, `category_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mod_media`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (1, 'test', 'test', 1, 1);

COMMIT;

