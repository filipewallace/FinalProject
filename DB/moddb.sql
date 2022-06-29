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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `id` INT NOT NULL AUTO_INCREMENT,
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
  `id` INT NOT NULL AUTO_INCREMENT,
  `cc_owner_name` VARCHAR(200) NULL,
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
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (2, 'Jane', 'Doe', 'jane', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'jane@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (3, 'Tom', 'Smith', 'tom', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'tom@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (4, 'Ellen', 'Wells', 'ellen', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'ellen@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (5, 'Rob', 'Thompson', 'rob', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'rob@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (6, 'Jerry', 'Jones', 'jerry', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'jerry@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (7, 'Allen', 'Rodriguez', 'allen', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'allen@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (8, 'Mary', 'Polinsky', 'mary', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'mary@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (9, 'Sally', 'Groffner', 'sally', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'sally@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `password`, `email`, `enabled`, `role`, `date_created`, `img_url`) VALUES (10, 'Shelly', 'Bernstein', 'shelly', '$2a$10$jCdkeHwIQujWoQLvQv3YIuyCGwTYL0HF7oFsHRlhai5H.XYzC08Yq', 'shelly@email.com', 1, 'ROLE_STANDARD', '2022-06-21', NULL);

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
INSERT INTO `developer` (`id`, `name`, `img_url`, `web_link`) VALUES (2, 'DevSoft', 'https://staticdelivery.nexusmods.com/mods/641/images/thumbnails/102/102-1566084502-409746048.gif', NULL);
INSERT INTO `developer` (`id`, `name`, `img_url`, `web_link`) VALUES (3, 'FromSoft', 'https://staticdelivery.nexusmods.com/mods/1151/images/thumbnails/3731-0-1448877308.jpg', NULL);
INSERT INTO `developer` (`id`, `name`, `img_url`, `web_link`) VALUES (4, 'PotatoDev', 'https://staticdelivery.nexusmods.com/mods/130/images/thumbnails/76021/76021-1649186147-252485024.jpeg', NULL);
INSERT INTO `developer` (`id`, `name`, `img_url`, `web_link`) VALUES (5, 'Software Developer Company', 'https://staticdelivery.nexusmods.com/mods/2600/images/thumbnails/772/772-1622038515-1913732547.png', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `publisher`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `publisher` (`id`, `name`, `img_url`, `web_link`) VALUES (1, 'Cupcake', 'test', 'test');
INSERT INTO `publisher` (`id`, `name`, `img_url`, `web_link`) VALUES (2, 'Game Publisher Company', 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/5260/5260-1623835686-1516122282.png', NULL);
INSERT INTO `publisher` (`id`, `name`, `img_url`, `web_link`) VALUES (3, 'JetGames Publishing', 'https://staticdelivery.nexusmods.com/mods/3551/images/thumbnails/114/114-1613479112-941408801.png', NULL);
INSERT INTO `publisher` (`id`, `name`, `img_url`, `web_link`) VALUES (4, 'GamePlay Publishers', 'https://staticdelivery.nexusmods.com/mods/1894/images/thumbnails/234-0-1497041307.jpg', NULL);
INSERT INTO `publisher` (`id`, `name`, `img_url`, `web_link`) VALUES (5, 'Potato Co', 'https://staticdelivery.nexusmods.com/mods/120/images/thumbnails/12025-1-1270390193.jpg', NULL);

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
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (1, 'Elden Ring', 1, 'Fun game about a ring that’s Elden. Lots of action and adventure. Sure to bring excitement to the entire family.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_4333.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (2, 'Star Citizen', 0, 'Competitive game about escaping corporate slavery and building a new life delivering noodles. Very cool.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_2246.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (3, 'Rogue Legacy 2', 1, 'A cheerful and challenging dungeon crawler. Do you have what it takes to conquer the dragon. Do you?', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_4001.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (4, 'Joy Pony', 1, 'A complex strategy card game with a birding theme. Let Joy Pony help you find the birds and expand your mind.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_1524.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (5, 'Teardown', 0, 'A game about destruction. Lots of demolition. Make sure you place the dynamite in the right place.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3481.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (6, 'Rebel Galaxy', 1, 'Relaxing game about a galaxy that rebels against the dark forces of evil far, far away and a long time ago.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_2983.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (7, 'Diggles: The Myth of Fenris', 0, 'Lively little action game about an energetic elf who likes to chop down trees. Your job is to save the forest.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3608.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (8, 'My Garage', 1, 'Fascinating game about a garage. Choose your garage, clean it, and organize it; even park your car in it!', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_4081.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (9, 'Teenage Mutant Ninja Turtles', 1, 'Four turtles. Four Friends. Ninjas. They fight the bad guys so that the bad guys lose and the good guys win.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_4578.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (10, 'Football Manager 2020', 1, 'A game about managing football. (Football is a sport in which a players on a team use a foot to kick a ball.)', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3365.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (11, 'The Wonderful 101', 0, 'Wonderful game about wonder kids who have super powers and the ability to fly really, really, really fast.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3437.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (12, 'Wonder Boy: The Dragon\'s Trap', 0, 'Wonder Boy is back in an epic adventure to find the hidden treasure on a deserted island in Tarzania.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3820.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (13, 'Garden Paws', 1, 'Canadian game about garden paws in Canada. Use paws to make your mark in the garden. Fun, eh?', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_2680.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (14, 'Plant Tycoon', 0, 'A rich guy monopolizes all the plants and makes it hard for people to find food to eat. A farmer fights back.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_2163.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (15, 'SnowRunner', 1, 'A big truck has to drive in the snow to deliver massive pipes to a small town in Saskatchewan.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3245.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (16, 'Super Chicken Jumper', 0, 'This chicken is a real super jumper. Jumps over buildings. Jumps over trees. Jumps over rivers.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_4062.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (17, 'World of Warships', 1, 'Have you ever wondered what it\'s like to be on a warship? This game explores the world of warships.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3330.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (18, 'My Summer Car', 0, 'A game about a summer car. You can drive the car. You can wash it. You can buy gas to fill up the car’s tank.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_2600.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (19, 'Petz 5', 0, 'A game about pets. This is the perfect game for anyone who wants pets but just not real pets.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_3178.jpg', 1, 1, 1, 1);
INSERT INTO `game` (`id`, `name`, `multiplayer`, `description`, `img_url`, `platform_id`, `developer_id`, `publisher_id`, `esrb_rating_id`) VALUES (20, 'Farming Simulator 22', 1, 'A game that simulates farming on a small farm in Idaho where farmers farm potatoes.', 'https://staticdelivery.nexusmods.com/Images/games/4_3/tile_4163.jpg', 1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `game_mod`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (1, 'Ranni Saves the Princess', 'Ranni has special powers to save the princess.', '2022-01-02', '2022-02-03', '1.2', 'Processor Intel Potato Core', 'https://staticdelivery.nexusmods.com/mods/4333/images/thumbnails/561/561-1648970059-233694987.png', 10.99, 1, 1, 'test');
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (2, 'Couch Citizen Config', 'Win the game sitting on your big, fluffy couch.', '2020-02-03', '2020-03-14', '3.1', 'NVIDIA 8000', 'https://staticdelivery.nexusmods.com/mods/2246/images/thumbnails/4/4-1628463777-1465514037.png', 19.99, 2, 2, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (3, 'Rogue Mod', 'Get more firepower for the entire ship’s crew.', '2015-09-24', '2021-12-31', '8.9', 'Processor Turbo Speed', 'https://staticdelivery.nexusmods.com/mods/1064/images/thumbnails/2-2-1430566217.png', 9.98, 3, 3, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (4, 'Expand Pony\'s Power', 'Pony powers up much more than ever before.', '2019-11-11', '2022-01-05', '3.0', 'NVIDIA Turbo 5800', 'https://staticdelivery.nexusmods.com/mods/449/images/thumbnails/415-1-1406075539.jpg', 5.00, 4, 4, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (5, 'Dynamite for Rocks', 'Can use dynamite to destroy big granite rocks.', '2019-11-01', '2019-11-02', '1.1', 'Tomato Intel Processor', 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/13366/13366-1509892350-1415651772.jpeg', 14.99, 5, 5, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (6, 'Change the Emperor\'s Robe', 'Helps the emperor get a new change of clothes.', '2019-01-11', '2019-11-12', '1.0', 'NVIDIA Potato', 'https://staticdelivery.nexusmods.com/mods/110/images/thumbnails/51461-1-1393192701.jpg', 3.00, 6, 3, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (7, 'Upgrade Diggles\' Shack', 'Creates a luxurious palace for Diggle’s friends.', '2019-11-02', '2020-01-05', '1.7', 'Processor Really Fast', 'https://staticdelivery.nexusmods.com/mods/3629/images/thumbnails/284/284-1632726030-557068577.png', 2.00, 7, 3, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (8, 'Garage Organizer', 'Organizes the garage in a fun and better way.', '2019-11-03', '2020-12-11', '2.8', 'NVIDIA 2100', 'https://staticdelivery.nexusmods.com/mods/2600/images/thumbnails/555/555-1614102954-1924083739.jpeg', 18.47, 8, 2, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (9, 'Shell Color Changer', 'Turns the turtles’ shell to blue and and mauve.', '2019-11-04', '2021-01-11', '3.0', 'Processor i5 Core', 'https://staticdelivery.nexusmods.com/mods/2971/images/thumbnails/1912/1912-1653661361-442983821.png', 13.22, 9, 2, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (10, 'Play Matches on the Beach', 'Allows the manager to manage on the beach.', '2019-11-05', '2020-01-01', '4.0', 'NVIDIA 3000', 'https://staticdelivery.nexusmods.com/mods/2913/images/thumbnails/38/38-1577834940-370834789.png', 14.98, 10, 2, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (11, 'Gives Polymorphic Powers', 'Hero conquers the villain through polymorphism.', '2019-11-06', '2021-11-12', '2.2', 'F150', 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/56907/56907-1633994937-1136520641.jpeg', 12.20, 11, 3, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (12, 'Escape Booster', 'The duck escapes the bad guy with loud quacks.', '2019-11-07', '2020-11-13', '5.8', 'V8 TurboThrust', 'https://staticdelivery.nexusmods.com/mods/1151/images/thumbnails/20846-0-1482869170.png', 3.97, 12, 4, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (13, 'Sky Adjuster', 'Can change the color of the sky to deeper blue.', '2019-11-08', '2021-11-14', '7.2', 'Diesel Processor', 'https://staticdelivery.nexusmods.com/mods/256/images/thumbnails/84/84-1537701333-1179063632.jpeg', 4.02, 13, 5, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (14, 'Jumping Feature', 'Fred can jump like a red jet over taller buildings.', '2019-11-09', '2020-11-15', '1.3', 'QuadCore i10 Processor', 'https://staticdelivery.nexusmods.com/mods/2777/images/thumbnails/1110/1110-1619336844-222584810.png', 5.07, 14, 4, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (15, 'Skin Tone Adjuster', 'Can adjust skin tone without baking in the sun.', '2019-11-09', '2021-11-16', '2.2', 'Java 1.8', 'https://staticdelivery.nexusmods.com/mods/2943/images/thumbnails/187/187-1646250978-760317531.jpeg', 8.03, 15, 3, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (16, 'Dark Mode Mod', 'Run the game in dark mode with vivid display.', '2019-11-10', '2020-11-17', '1.0', 'Angular Potato', 'https://staticdelivery.nexusmods.com/mods/1151/images/thumbnails/15880-0-1467431652.jpg', 1.99, 16, 2, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (17, 'Tic Tac Toe Feature', 'Characters can play tic tac tow in the garage.', '2019-11-13', '2021-11-18', '1.5', 'Java Bean Graphics 2.8', 'https://staticdelivery.nexusmods.com/mods/2600/images/thumbnails/228/228-1589132268-1061756650.jpeg', 2.97, 17, 2, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (18, 'Blackjack', 'Fido and Selma play wonky blackjack together.', '2019-11-14', '2020-11-19', '5.5', 'NVIDIA 8900', 'https://staticdelivery.nexusmods.com/mods/301/images/thumbnails/27-1-1396136031.jpg', 3.00, 18, 3, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (19, 'MadLibs Mod', 'George goes quite crazy with radically mad libs.', '2019-11-15', '2021-11-20', '2.1', 'Processor JS 2.5', 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/21958/21958-1545089757-729964723.jpeg', 5.00, 19, 5, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (20, 'Force Thruster', 'Adds thrust to the orange thermonuclear thruster.', '2019-11-16', '2020-11-21', '3.2', 'TurboThrust 3000', 'https://staticdelivery.nexusmods.com/mods/1303/images/thumbnails/3798/3798-1555625144-2039190107.png', 10.00, 20, 6, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (21, 'Star Enhancer', 'Makes each star just a little bit brighter. Yes!!!', '2019-11-01', '2020-11-04', '1.0', 'NVIDIA Potato', 'https://staticdelivery.nexusmods.com/mods/234/images/thumbnails/41-0-1470558805.jpg', 11.00, 2, 2, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (22, 'Special Sword', 'Use a special sword to improve slicing ability.', '2019-11-02', '2020-11-05', '2.0', 'V8 TurboThrust', 'https://staticdelivery.nexusmods.com/mods/110/images/thumbnails/52700-1-1396717539.jpg', 9.99, 3, 3, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (23, 'Card Quality', 'Makes the cards bigger and better and funner.', '2019-11-03', '2020-11-06', '3.0', 'Diesel Processor', 'https://staticdelivery.nexusmods.com/mods/130/images/thumbnails/63666-0-1502086120.png', 5.99, 4, 4, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (24, 'Big Buildings Addition', 'Adding bigger buildings, this mod is something.', '2019-11-04', '2020-11-07', '4.0', 'QuadCore i10 Processor', 'https://staticdelivery.nexusmods.com/mods/1972/images/thumbnails/63-0-1500353791.png', 6.99, 5, 5, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (25, 'Asteroids', 'This asteroid mod majorly improves this game.', '2019-11-05', '2020-11-08', '5.1', 'Java 1.8', 'https://staticdelivery.nexusmods.com/mods/1634/images/thumbnails/1712/1712-1601785228-1054883935.jpeg', 7.99, 6, 6, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (26, 'New Hat for Diggles', 'Diggles can change his hat for improved looks.', '2019-11-06', '2020-11-09', '1.1', 'Angular Potato', 'https://staticdelivery.nexusmods.com/mods/130/images/thumbnails/58540-1-1427303347.jpg', 8.99, 7, 7, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (27, 'Color Change', 'Changes the color for a more colorized view.', '2019-11-07', '2020-11-10', '1.2', 'Java Bean Graphics 2.8', 'https://staticdelivery.nexusmods.com/mods/1392/images/thumbnails/741/741-1605859088-2126706738.png', 4.99, 8, 8, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (28, 'Beautiful Stars', 'Add beautiful stars to the clear, dark summer sky.', '2019-11-08', '2020-11-11', '1.3', 'NVIDIA 8900', 'https://staticdelivery.nexusmods.com/mods/1151/images/thumbnails/19378-2-1476878229.jpg', 5.99, 2, 9, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (29, 'Dragons!!!!', 'This friendly dragons mod adds nice dragons.', '2019-11-09', '2020-11-12', '1.4', 'Processor JS 2.5', 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/33022/33022-1582572473-1722966057.jpeg', 6.99, 3, 10, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (30, 'Cookies for Pony', 'Pony gets food that is oh so delicious.', '2019-11-10', '2020-11-13', '1.5', 'TurboThrust 3000', 'https://staticdelivery.nexusmods.com/mods/110/images/thumbnails/62665-1-1424529709.jpg', 7.99, 4, 10, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (31, 'Realistic Shell', 'Much more realistic look for the turtles.', '2019-11-11', '2020-11-14', '1.2', 'Java 1.8', 'https://staticdelivery.nexusmods.com/mods/257/images/thumbnails/93-0-1483625518.png', 8.99, 9, 9, NULL);
INSERT INTO `game_mod` (`id`, `title`, `description`, `date_created`, `date_updated`, `version`, `requirements`, `img_url`, `price`, `game_id`, `user_id`, `download_link`) VALUES (32, 'Snow Football', 'Play matches in the snow during wintertime.', '2019-11-12', '2020-11-15', '2.3', 'TurboThrust 3000', 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/80352-1-1479601356.jpg', 7.03, 10, 8, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'Awesome!', 2, 2, '2020-01-30');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (4, 'I really like this mod.', 2, 3, '2021-02-27');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (3, 'Just okay. Nothing special.', 3, 4, '2019-03-28');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (2, 'Not that great.', 4, 5, '2020-04-27');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (1, 'Horrible.', 5, 6, '2022-05-26');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (1, 'I don\'t like it.', 2, 1, '2020-06-27');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (2, 'Not worth your time.', 4, 2, '2021-07-28');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (3, 'Average at best.', 6, 3, '2020-08-29');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (4, 'Pretty good, but could be better.', 8, 4, '2021-09-25');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'Best ever.', 10, 5, '2022-10-24');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'I love this mod.', 3, 6, '2021-11-23');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'Super Duper Mod!', 4, 7, '2022-12-22');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (4, 'I like the graphics.', 2, 8, '2021-06-21');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (3, 'Kind of glitchy.', 3, 9, '2020-05-20');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (2, 'I wouldn\'t download this one.', 4, 10, '2021-03-19');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (1, 'Very, very bad!', 5, 11, '2022-02-18');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'Just the mod I was looking for!', 6, 12, '2021-01-17');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (4, 'This mod has real potential.', 7, 13, '2020-03-16');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (3, 'Just a really average mod.', 8, 15, '2022-04-15');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (2, 'I don\'t want to complain, but this mod is not good at all.', 9, 16, '2021-06-14');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (1, 'Why? I don\'t see the point of this mod.', 10, 17, '2019-06-13');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'Marvelous!', 3, 18, '2020-06-12');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (4, 'Definitely a good mod.', 4, 19, '2021-06-11');
INSERT INTO `review` (`score`, `opinion`, `user_id`, `mod_id`, `review_date`) VALUES (5, 'Fantastic mod. I hope the developers will work on others like this one.', 5, 20, '2022-06-10');

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
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (1, 'Mod Okay', 'I like the idea behind this mod, but I think there are better ones out there.', 1, 1, '2022-03-03', 1);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (2, 'Nice Mod', 'This is a pretty good mod, in my opinion.', 2, 2, '2022-01-13', 2);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (3, 'Cool Idea', 'The idea behind this mod is really cool. Of course, there are some improvements that could be made. Overall, it\'s a good mod.', 3, 8, '2021-10-17', 3);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (4, 'Very Innovative', 'I like the innovation of this mod.', 2, 9, '2022-08-05', 4);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (5, 'Best Mod Ever', 'By far the best mod I\'ve ever seen for this game.', 3, 7, '2020-01-02', 5);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (6, 'Don\'t Understand', 'I really don\'t understand the purpose of this mod.', 4, 6, '2019-03-08', 6);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (7, 'Worst Ever', 'Horrible. Just horrible. A real waste.', 5, 4, '2020-03-05', 7);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (8, 'Could Be Better', 'It\'s not bad, but it\'s not great either. Needs some work.', 6, 5, '2022-05-09', 8);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (9, 'Fantastic!', 'I am so pleased with this mod.', 7, 3, '2022-08-05', 9);
INSERT INTO `post` (`id`, `title`, `comment`, `mod_id`, `user_id`, `comment_date`, `reply_to_id`) VALUES (10, 'Awesome!!!!!!!!', 'This mod is awesome. By far my favorite.', 8, 2, '2022-03-06', 10);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mod_order`
-- -----------------------------------------------------
START TRANSACTION;
USE `moddb`;
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (1, 'Joe Doe', '1234 Elm Lane, Mayberry, NC 12345', 1);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (2, 'Shelly Bernstein', '8 Main St, Miami, FL, 98765', 10);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (3, 'Sally Groffner', '18 N Broad St, Uptown, NY 33345', 9);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (4, 'Mary Polinsky', '123 North St., Palm Beach, FL 33012', 8);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (5, 'Allen Rodriquez', '456 South St., Jacksonville, FL 33067', 7);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (6, 'Jerry Jones', '657 Hollywood Blvd, Hollywood, FL 12345 ', 6);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (7, 'Rob Thompson', '342 West St., Plano, TX 77574', 5);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (8, 'Ellen Wells', '2343 Beach St., Tampa Bay, FL 23434', 4);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (9, 'Tom Smith', '5523 East Beltline, Lansing, MI 54356', 3);
INSERT INTO `mod_order` (`id`, `cc_owner_name`, `billing_address`, `user_id`) VALUES (10, 'Jane Doe', '8 Lake Rd, Boston, MA, 23454', 2);

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
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (1, 'https://staticdelivery.nexusmods.com/mods/130/images/thumbnails/37513-1-1298749187.jpg', 'Schoolhouse base mod.', 1, 1);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (2, 'https://staticdelivery.nexusmods.com/mods/101/images/thumbnails/51884/51884-1649095311-1000563482.jpeg', 'A dear dear.', 2, 2);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (3, 'https://staticdelivery.nexusmods.com/mods/4265/images/thumbnails/1/1-1642489075-1313903333.png', 'Nice reshade.', 3, 3);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (4, 'https://staticdelivery.nexusmods.com/mods/4578/images/thumbnails/22/22-1655746608-709278357.png', 'Nice boots and red hat.', 4, 4);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (5, 'https://staticdelivery.nexusmods.com/mods/1303/images/thumbnails/5448/5448-1582354981-1635338681.png', 'Improved center.', 5, 5);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (6, 'https://staticdelivery.nexusmods.com/mods/101/images/thumbnails/7552-1-1251555907.jpg', 'Friendly places within the borders of the kingdom.', 6, 6);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (7, 'https://staticdelivery.nexusmods.com/mods/4103/images/thumbnails/111/111-1645219521-166617932.jpeg', 'Take a shortcut over the mountains.', 7, 7);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (8, 'https://staticdelivery.nexusmods.com/mods/120/images/thumbnails/24784/24784-1631421245-1152861217.jpeg', 'Add a nice sunset to the final scene.', 8, 8);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (9, 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/3805/3805-1545330884-2115047679.jpeg', 'Build a sweet gingerbread house.', 9, 8);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (10, 'https://staticdelivery.nexusmods.com/mods/110/images/thumbnails/68221-0-1437999415.jpg', 'Use a horse and carriage to get there faster.', 10, 8);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (11, 'https://staticdelivery.nexusmods.com/mods/1151/images/thumbnails/10157-0-1457727003.jpg', 'Lets you travel to settlements on a cool motorcycle.', 11, 2);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (12, 'https://staticdelivery.nexusmods.com/mods/1151/images/thumbnails/21184-0-1484039461.jpg', 'Change your car color to pink.', 12, 3);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (13, 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/8778-7-1488919331.jpg', 'Add lightning effects.', 13, 4);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (14, 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/50755/50755-1622706861-596411500.jpeg', 'Take a nice stroll through the meadow.', 14, 5);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (15, 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/8398-0-1500580058.jpg', 'Add a beautiful sunrise and weather changes.', 15, 6);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (16, 'https://staticdelivery.nexusmods.com/mods/952/images/thumbnails/3902/3902-1568016155-701733144.jpeg', 'Immersive lighting reshade.', 16, 2);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (17, 'https://staticdelivery.nexusmods.com/mods/1151/images/thumbnails/7258-0-1451465814.jpg', 'Bubble gum retexture.', 17, 3);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (18, 'https://staticdelivery.nexusmods.com/mods/2777/images/thumbnails/1107/1107-1618973286-524665390.jpeg', 'Add a yellow jet.', 18, 4);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (19, 'https://staticdelivery.nexusmods.com/mods/1704/images/thumbnails/51188-1-1392645526.jpg', 'Add beautiful snow scenes.', 19, 4);
INSERT INTO `mod_media` (`id`, `media_url`, `description`, `mod_id`, `user_id`) VALUES (20, 'https://staticdelivery.nexusmods.com/mods/110/images/thumbnails/8328-1-1328813289.jpg', 'More snow.', 20, 3);

COMMIT;

