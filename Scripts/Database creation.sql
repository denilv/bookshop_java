	-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bookshop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bookshop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookshop`;
DROP DATABASE IF EXISTS `bookshop`;
CREATE DATABASE `bookshop`;
USE `bookshop` ;

-- -----------------------------------------------------
-- Table `bookshop`.`Publ_house`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Publ_house` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `bookshop`.`Genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `bookshop`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(10) NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `price` INT NULL DEFAULT NULL,
  `publ_house_id` INT NULL DEFAULT NULL,
  `publication_date` DATE NULL DEFAULT NULL,
  `pages` INT NULL DEFAULT NULL,
  `available_amount` INT NULL DEFAULT NULL,
  `annotation` INT NULL DEFAULT NULL,
  `genre_id` INT NULL DEFAULT NULL,
  `writing_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_publ_house_id` (`publ_house_id` ASC),
  INDEX `fk_genre_id` (`genre_id` ASC),
  CONSTRAINT `fk_publ_house_id`
    FOREIGN KEY (`publ_house_id`)
    REFERENCES `bookshop`.`Publ_house` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_genre_id`
    FOREIGN KEY (`genre_id`)
    REFERENCES `bookshop`.`Genre` (`id`) ON DELETE SET NULL);


-- -----------------------------------------------------
-- Table `bookshop`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `f_name` VARCHAR(40) NULL DEFAULT NULL,
  `l_name` VARCHAR(40) NULL DEFAULT NULL,
  `m_name` VARCHAR(40) NULL DEFAULT NULL,
  `admin` INT NOT NULL DEFAULT 0,
  `email` VARCHAR(40) NULL DEFAULT NULL,
  `password` VARCHAR(20) NULL DEFAULT NULL,
  `contact_number` VARCHAR(12) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `bookshop`.`Shipping_method`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Shipping_method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `price` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `bookshop`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NULL DEFAULT NULL,
  `status` VARCHAR(40) NULL DEFAULT NULL,
  `date_time` DATE NULL DEFAULT NULL,
  `shipping_method` INT NULL DEFAULT NULL,
  `comment` VARCHAR(100) NULL DEFAULT NULL,
  `shipping_address` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_id` (`customer_id` ASC),
  INDEX `fk_shipping_method` (`shipping_method` ASC),
  CONSTRAINT `fk_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `bookshop`.`Customer` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_shipping_method`
    FOREIGN KEY (`shipping_method`)
    REFERENCES `bookshop`.`Shipping_method` (`id`) ON DELETE SET NULL);


-- -----------------------------------------------------
-- Table `bookshop`.`Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `f_name` VARCHAR(40) NULL DEFAULT NULL,
  `l_name` VARCHAR(40) NULL DEFAULT NULL,
  `m_name` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `bookshop`.`Book_to_Author`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Book_to_Author` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `book_id` INT NULL DEFAULT NULL,
  `author_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_book_id` (`book_id` ASC),
  INDEX `fk_author_id` (`author_id` ASC),
  CONSTRAINT `fk_book_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `bookshop`.`Book` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `bookshop`.`Author` (`id`) ON DELETE CASCADE);


-- -----------------------------------------------------
-- Table `bookshop`.`Order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookshop`.`Order_items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NULL DEFAULT NULL,
  `book_id` INT NULL DEFAULT NULL,
  `amount` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_id` (`order_id` ASC),
  INDEX `fk_OI_to_book_id` (`book_id` ASC),
  CONSTRAINT `fk_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `bookshop`.`Orders` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_OI_to_book_id`
    FOREIGN KEY (`book_id`) 
    REFERENCES `bookshop`.`Book` (`id`) ON DELETE SET NULL);
    


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
