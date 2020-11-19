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

INSERT INTO `montohop` VALUES (11, 'KTTN'),
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

INSERT INTO `thisinh` VALUES ('2019001', 'Nguyễn Thảo An', 0, '2001-01-01', 'Hải Châu-Đà Nẵng', 'THPT Lê Qúy Đôn', 22, 2019),
							 ('2019002', 'Trần Văn Bảo', 1, '2001-05-04', 'Hạ Long-Hải Phòng', 'THPT Lê Hữu Trác', 13, 2019),
							 ('2019003', 'Lê Văn Cường', 1, '2001-09-20', 'Bắc Ninh-Bắc Ninh', 'THPT Nguyễn Trãi', 12, 2019),
							 ('2019004', 'Đặng Thảo Vi', 0, '2001-06-01', 'Huế-TT Huế', 'THPT Nguyễn Huệ', 22, 2019),
							 ('2019005', 'Nguyễn Đình Sáng', 1, '2001-11-10', 'Thạch Hà-Hà Tĩnh', 'THPT Phan Đình Phùng', 21, 2019),
							 ('2019006', 'Hồ Hoài Linh', 1, '2001-02-15', 'Khánh Hòa-Nha Trang', 'THPT Lý Tự Trọng', 23, 2019),
							 ('2019007', 'Nguyễn Đình Hiếu', 1, '2001-05-05', 'Bình Thủy-Cần Thơ', 'THPT Lý Tự Trọng', 33, 2019),
							 ('2019008', 'Phạm Nhật Minh', 0, '2001-12-31', 'Thủ Đức-Hồ Chí Minh', 'THPT Lê Hồng Phong', 31, 2019),
							 ('2019009', 'Dương Ngọc Lan', 0, '2001-10-20', 'Quy Nhơn-Bình Ðịnh', 'THPT Nguyễn Biểu', 23, 2019),
							 ('2019010', 'Phan Tấn Trung', 1, '2001-03-08', 'Biên Hòa-Đồng Nai', 'THPT Lê Lợi', 32, 2019),
                             ('2019011', 'Phan Đăng Khoa', 1, '2001-09-01', 'Khánh Hòa-Nha Trang', 'THPT Lý Tự Trọng', 23, 2019),
                             ('2019012', 'Đặng Nhật Nam', 1, '2001-12-31', 'Thạch Hà-Hà Tĩnh', 'THPT Phan Đình Phùng', 21, 2019),
                             ('2019013', 'Lê Huy Hoàng', 1, '2001-07-10', 'Ba Đình-Hà Nội', 'THPT Hai Bà Trưng', 11, 2019),
                             ('2019014', 'Nguyễn Cẩm Tú', 0, '2001-01-28', 'Biên Hòa-Đồng Nai', 'THPT Lê Lợi', 32, 2019),
                             ('2019015', 'Trần Anh Thư', 0, '2001-04-04', 'Biên Hòa-Đồng Nai', 'THPT Lê Lợi', 32, 2019),
                             ('2019016', 'Hoàng Nhật Nam', 1, '2001-11-12', 'Thủ Đức-Hồ Chí Minh', 'THPT Lê Hồng Phong', 31, 2019),
                             ('2019017', 'Phạm Khánh Huyền', 0, '2001-11-22', 'Vinh-Nghệ An', 'THPT Nguyễn Du', 21, 2019),
                             ('2019018', 'Nguyễn Thảo Mai', 0, '2001-09-08', 'Bắc Ninh-Bắc Ninh', 'THPT Nguyễn Trãi', 12, 2019),
                             ('2019019', 'Lê Ngọc Thúy', 0, '2001-04-11', 'Quy Nhơn-Bình Ðịnh', 'THPT Nguyễn Biểu', 23, 2019),
                             ('2019020', 'Hồ Chí Tài', 1, '2001-10-14', 'Cầu Giấy-Hà Nội', 'THPT Hai Bà Trưng', 11, 2019),
							 ('2020001', 'Nguyễn Bảo Ngọc', 0, '2002-07-07', 'Thạch Hà-Hà Tĩnh', 'THPT Phan Đình Phùng', 21, 2020),
							 ('2020002', 'Vương Thục Linh', 0, '2002-10-08', 'Tam Kỳ-Quảng Nam', 'THPT Tam Kỳ', 22, 2020),
							 ('2020003', 'Dương Văn Hoàng', 1, '2002-02-01', 'Cầu Giấy-Hà Nội', 'THPT Hai Bà Trưng', 11, 2020),
							 ('2020004', 'Nguyễn Quỳnh Nhi', 0, '2002-10-01', 'Ba Đình-Hà Nội', 'THPT Hai Bà Trưng', 11, 2020),
							 ('2020005', 'Đặng Quốc Huy', 1, '2002-05-15', 'Vinh-Nghệ An', 'THPT Nguyễn Du', 21, 2020),
                             ('2020006', 'Nguyễn Bảo Anh', 1, '2002-05-15', 'Khánh Hòa-Nha Trang', 'THPT Lý Tự Trọng', 23, 2020),
                             ('2020007', 'Đặng Đình Hoàng', 1, '2002-03-11', 'Thạch Hà-Hà Tĩnh', 'THPT Phan Đình Phùng', 21, 2020),
                             ('2020008', 'Phan Duy Khánh', 1, '2002-05-05', 'Cầu Giấy-Hà Nội', 'THPT Hai Bà Trưng', 11, 2020),
                             ('2020009', 'Mai Phương Thúy', 0, '2002-09-30', 'Thủ Đức-Hồ Chí Minh', 'THPT Lê Hồng Phong', 31, 2020),
                             ('2020010', 'Bùi Xuân Hương', 0, '2002-12-20', 'Tam Kỳ-Quảng Nam', 'THPT Tam Kỳ', 22, 2020),
                             ('2020011', 'Phạm Thái Bảo', 1, '2002-1-27', 'Thạch Hà-Hà Tĩnh', 'THPT Phan Đình Phùng', 21, 2020),
                             ('2020012', 'Võ Hoàng Yến', 0, '2002-03-13', 'Bắc Ninh-Bắc Ninh', 'THPT Nguyễn Trãi', 12, 2020),
                             ('2020013', 'Nguyễn Linh Chi', 0, '2002-09-18', 'Cầu Giấy-Hà Nội', 'THPT Hai Bà Trưng', 11, 2020),
                             ('2020014', 'Nguyễn Tuyết Nhi', 0, '2002-07-04', 'Hải Châu-Đà Nẵng', 'THPT Lê Qúy Đôn', 22, 2020),
                             ('2020015', 'Nguyễn Ngọc Linh', 0, '2002-05-25', 'Tam Kỳ-Quảng Nam', 'THPT Tam Kỳ', 22, 2020),
                             ('2020016', 'Lê Thái Vũ', 1, '2002-10-02', 'Vinh-Nghệ An', 'THPT Nguyễn Du', 21, 2020),
                             ('2020017', 'Đặng Hoàng Huy', 1, '2002-01-19', 'Quy Nhơn-Bình Ðịnh', 'THPT Nguyễn Biểu', 23, 2020),
                             ('2020018', 'Phạm Quốc Đạt', 1, '2002-11-30', 'Hạ Long-Hải Phòng', 'THPT Lê Hữu Trác', 13, 2020),
                             ('2020019', 'Hoàng Thùy Linh', 0, '2002-11-15', 'Vinh-Nghệ An', 'THPT Nguyễn Du', 21, 2020),
                             ('2020020', 'Đặng Như Anh', 0, '2002-05-17', 'Thạch Hà-Hà Tĩnh', 'THPT Phan Đình Phùng', 21, 2020);

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

INSERT INTO `diemthi` VALUES      ('2019001', 9.4, 7.0, 7.8, 11),
								  ('2019002', 6.8, 8.5, 7.6, 11),
								  ('2019003', 8.4, 7.0, 8.6, 12),
								  ('2019004', 8.8, 8.0, 6.6, 12),
								  ('2019005', 10.0, 10.0, 10.0, 11),
								  ('2019006', 8.4, 8.5, 8.6, 12),
								  ('2019007', 8.8, 7.4, 7.2, 11),
								  ('2019008', 9.0, 7.0, 8.8, 11),
								  ('2019009', 9.8, 8.5, 9.6, 11),
								  ('2019010', 7.4, 8.5, 8.6, 12),
                                  ('2019011', 9.4, 7.0, 7.8, 11),
								  ('2019012', 6.8, 8.5, 7.6, 11),
								  ('2019013', 8.4, 7.0, 8.6, 12),
								  ('2019014', 8.8, 8.0, 6.6, 12),
								  ('2019015', 10.0, 10.0, 10.0, 11),
								  ('2019016', 8.4, 8.5, 8.6, 12),
								  ('2019017', 8.8, 7.4, 7.2, 11),
								  ('2019018', 9.0, 7.0, 8.8, 11),
								  ('2019019', 9.8, 8.5, 9.6, 11),
								  ('2019020', 7.4, 8.5, 8.6, 12),
								  ('2020001', 10.0, 10.0, 10.0, 11),
								  ('2020002', 8.4, 8.5, 8.6, 12),
								  ('2020003', 8.6, 8.0, 8.6, 11),
								  ('2020004', 8.4, 8.2, 9.6, 12),
								  ('2020005', 8.4, 7.2, 8.6, 12),
								  ('2020006', 8.4, 8.5, 8.6, 12),
                                  ('2020007', 10.0, 10.0, 10.0, 11),
								  ('2020008', 8.6, 8.0, 8.6, 11),
								  ('2020009', 8.4, 8.2, 9.6, 12),
								  ('2020010', 8.4, 7.2, 8.6, 12),
                                  ('2020011', 9.0, 10.0, 9.0, 11),
								  ('2020012', 8.4, 8.5, 8.6, 12),
								  ('2020013', 8.8, 7.4, 7.2, 11),
								  ('2020014', 9.0, 7.0, 8.8, 11),
								  ('2020015', 9.8, 8.5, 9.6, 11),
                                  ('2020016', 8.8, 7.4, 7.2, 11),
								  ('2020017', 9.0, 7.0, 8.8, 11),
								  ('2020018', 9.8, 8.5, 9.6, 11),
								  ('2020019', 7.4, 8.5, 8.6, 12),
                                  ('2020020', 9.4, 7.0, 7.8, 11);

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

INSERT INTO `diemthitohop` VALUES ('2019001', 9.4, 7.0, 7.8),
								  ('2019002', 6.8, 8.5, 7.6),
								  ('2019003', 8.4, 7.0, 8.6),
								  ('2019004', 8.8, 8.0, 6.6),
								  ('2019005', 10.0, 10.0, 10.0),
								  ('2019006', 8.4, 8.5, 8.6),
								  ('2019007', 8.8, 7.4, 7.2),
								  ('2019008', 9.0, 7.0, 8.8),
								  ('2019009', 9.8, 8.5, 9.6),
								  ('2019010', 7.4, 8.5, 8.6),
                                  ('2019011', 9.4, 7.0, 7.8),
								  ('2019012', 6.8, 8.5, 7.6),
								  ('2019013', 8.4, 7.0, 8.6),
								  ('2019014', 8.8, 8.0, 6.6),
								  ('2019015', 8.0, 9.0, 10.0),
								  ('2019016', 8.4, 8.5, 8.6),
								  ('2019017', 8.8, 7.4, 7.2),
								  ('2019018', 9.0, 7.0, 8.8),
								  ('2019019', 9.8, 8.5, 9.6),
								  ('2019020', 7.4, 8.5, 8.6),
								  ('2020001', 10.0, 10.0, 10.0),
								  ('2020002', 8.4, 8.5, 8.6),
								  ('2020003', 8.6, 8.0, 8.6),
								  ('2020004', 8.4, 8.2, 9.6),
								  ('2020005', 8.4, 7.2, 8.6),
                                  ('2020006', 8.8, 7.4, 7.2),
                                  ('2020007', 10.0, 10.0, 10.0),
								  ('2020008', 9.0, 7.0, 8.8),
								  ('2020009', 9.8, 8.5, 9.6),
                                  ('2020010', 8.8, 7.4, 7.2),
								  ('2020011', 9.0, 7.0, 8.8),
								  ('2020012', 9.8, 8.5, 9.6),
								  ('2020013', 7.4, 8.5, 8.6),
                                  ('2020014', 9.4, 7.0, 7.8),
                                  ('2020015', 8.4, 8.5, 8.6),
								  ('2020016', 8.6, 8.0, 8.6),
								  ('2020017', 8.4, 8.2, 9.6),
								  ('2020018', 8.4, 7.2, 8.6),
                                  ('2020019', 8.4, 7.0, 8.6),
								  ('2020020', 8.8, 8.0, 6.6);
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
