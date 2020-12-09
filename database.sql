-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema database
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `database` ;

-- -----------------------------------------------------
-- Schema database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `database` DEFAULT CHARACTER SET utf8 ;
USE `database` ;

-- -----------------------------------------------------
-- Table `database`.`montohop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `database`.`montohop` ;

CREATE TABLE IF NOT EXISTS `database`.`montohop` (
  `MaTH` INT(2) NOT NULL,
  `TenTH` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`MaTH`))
ENGINE = InnoDB;

-- insert data `montohop`

INSERT INTO `montohop` VALUES (11, 'KHTN'),
							  (12, 'KHXH');

-- -----------------------------------------------------
-- Table `database`.`khuvuc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `database`.`khuvuc` ;

CREATE TABLE IF NOT EXISTS `database`.`khuvuc` (
  `MaKV` INT(1) NOT NULL,
  `TenKV` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`MaKV`))
ENGINE = InnoDB;

INSERT INTO `khuvuc` VALUES (1, 'Miền Bắc'),
							(2, 'Miền Trung'),
                            (3, 'Miền Nam');

-- -----------------------------------------------------
-- Table `database`.`cumthi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `database`.`cumthi` ;

CREATE TABLE IF NOT EXISTS `database`.`cumthi` (
  `MaCT` INT(2) NOT NULL,
  `TenCT` VARCHAR(45) NOT NULL,
  `MaKV` INT(1) NOT NULL,
  PRIMARY KEY (`MaCT`),
  INDEX `fk_cumthi_khuvuc_idx` (`MaKV` ASC) VISIBLE)
ENGINE = InnoDB;

INSERT INTO `cumthi` VALUES (11, 'Hà Nội', 1),
							(12, 'Bắc Ninh', 1),
                            (13, 'Hải Phòng', 1),
                            (21, 'Hà Tĩnh', 2),
                            (22, 'Đà Nẵng', 2),
                            (23, 'Khánh Hòa', 2),
                            (31, 'Hồ Chí Minh', 3),
                            (32, 'Đồng Nai', 3),
                            (33, 'Cần Thơ', 3);

-- -----------------------------------------------------
-- Table `database`.`thisinh`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `database`.`thisinh` ;

CREATE TABLE IF NOT EXISTS `database`.`thisinh` (
  `SBD` VARCHAR(7) NOT NULL,
  `TenTS` VARCHAR(45) NOT NULL,
  `GioiTinh` INT(1) NOT NULL,
  `NgaySinh` DATE NOT NULL,
  `QueQuan` VARCHAR(45) NOT NULL,
  `TruongTHPT` VARCHAR(45) NOT NULL,
  `MaCT` INT(2) NOT NULL,
  `NamThi` INT(4) NOT NULL,
  PRIMARY KEY (`SBD`),
  INDEX `fk_thisinh_cumthi1_idx` (`MaCT` ASC) VISIBLE)
ENGINE = InnoDB;

-- insert data `thisinh`




-- -----------------------------------------------------
-- Table `database`.`diemthi`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `database`.`diemthi` ;

CREATE TABLE IF NOT EXISTS `database`.`diemthi` (
  `SBD` VARCHAR(7) NOT NULL,
  `Toan` FLOAT NOT NULL,
  `Van` FLOAT NOT NULL,
  `TiengAnh` FLOAT NOT NULL,
  `MaTH` INT(2) NOT NULL,
  INDEX `fk_diemthi_thisinh1_idx` (`SBD` ASC) VISIBLE,
  PRIMARY KEY (`SBD`),
  INDEX `fk_diemthi_montohop1_idx` (`MaTH` ASC) VISIBLE)
ENGINE = InnoDB;

-- insert data `diemthi`



-- -----------------------------------------------------
-- Table `database`.`diemthitohop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `database`.`diemthitohop` ;

CREATE TABLE IF NOT EXISTS `database`.`diemthitohop` (
  `SBD` VARCHAR(7) NOT NULL,
  `Mon1` FLOAT NOT NULL,
  `Mon2` FLOAT NOT NULL,
  `Mon3` FLOAT NOT NULL,
  INDEX `fk_chitietcacmonthitohop_diemthi1_idx` (`SBD` ASC) VISIBLE,
  PRIMARY KEY (`SBD`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `database`.`monthitohop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `database`.`monthitohop` ;

CREATE TABLE IF NOT EXISTS `database`.`monthitohop` (
  `MaTH` INT(2) NOT NULL,
  `Monfirst` VARCHAR(45) NOT NULL,
  `Monsecond` VARCHAR(45) NOT NULL,
  `Monthird` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`MaTH`))
ENGINE = InnoDB;

-- insert data `monthitohop`

INSERT INTO `monthitohop` VALUES (11, 'Vật Lý', 'Hóa Học', 'Sinh Học'),
								 (12, 'Lịch Sử', 'Địa Lý', 'GDCD');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
