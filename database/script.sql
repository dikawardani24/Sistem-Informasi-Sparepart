-- MySQL Script generated by MySQL Workbench
-- Rab 16 Mei 2018 01:52:27  WIB
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema inventory_sparepart_recket_bekinser
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema inventory_sparepart_recket_bekinser
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `inventory_sparepart_recket_bekinser` DEFAULT CHARACTER SET utf8 ;
USE `inventory_sparepart_recket_bekinser` ;

-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`karyawan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`karyawan` (
  `idkaryawan` INT NOT NULL,
  `nama` VARCHAR(45) NOT NULL,
  `alamat` VARCHAR(45) NOT NULL,
  `jenis_kelamin` VARCHAR(45) NOT NULL,
  `no_ktp` VARCHAR(45) NOT NULL,
  `no_hp` VARCHAR(45) NOT NULL,
  `jabatan` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idkaryawan`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`user` (
  `iduser` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `idkaryawan` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`iduser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`sparepart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`sparepart` (
  `idsparepart` INT NOT NULL,
  `nama` VARCHAR(45) NOT NULL,
  `kategori` VARCHAR(45) NOT NULL,
  `stock` INT NOT NULL,
  `no_rak` VARCHAR(45) NOT NULL,
  `level_rak` VARCHAR(45) NOT NULL,
  `keterangan` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsparepart`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`supplier` (
  `idsupplier` INT NOT NULL,
  `nama` VARCHAR(45) NOT NULL,
  `no_telp1` VARCHAR(45) NOT NULL,
  `no_telp2` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `keterangan` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsupplier`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`transaksi_sparepart_masuk`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`transaksi_sparepart_masuk` (
  `idtransaksi_sparepart_masuk` INT NOT NULL,
  `tgl` TIMESTAMP NOT NULL,
  `idpenerima` INT NOT NULL,
  `no_purchase_order` VARCHAR(45) NOT NULL,
  `idsupplier` INT NOT NULL,
  PRIMARY KEY (`idtransaksi_sparepart_masuk`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`detail_transaksi_sparepart_masuk`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`detail_transaksi_sparepart_masuk` (
  `iddetail_transaksi_sparepart_masuk` INT NOT NULL,
  `idsparepart` INT NOT NULL,
  `idtransaksi_sparepart_masuk` INT NOT NULL,
  `jumlah` INT NOT NULL,
  PRIMARY KEY (`iddetail_transaksi_sparepart_masuk`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`permintaan_sparepart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`permintaan_sparepart` (
  `idpermintaan_sparepart` INT NOT NULL,
  `idteknisi` INT NOT NULL,
  `tgl` TIMESTAMP NOT NULL,
  PRIMARY KEY (`idpermintaan_sparepart`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`transaksi_sparepart_keluar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`transaksi_sparepart_keluar` (
  `idtransaksi_sparepart_keluar` INT NOT NULL,
  `tgl` TIMESTAMP NOT NULL,
  `idpemberi_izin` INT NOT NULL,
  `idpermintaan_sparepart` INT NOT NULL,
  PRIMARY KEY (`idtransaksi_sparepart_keluar`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`detail_transaksi_sparepart_keluar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`detail_transaksi_sparepart_keluar` (
  `iddetail_transaksi_sparepart_keluar` INT NOT NULL,
  `idsparepart` INT NOT NULL,
  `idtransaksi_sparepart_keluar` INT NOT NULL,
  `jumlah` INT NOT NULL,
  PRIMARY KEY (`iddetail_transaksi_sparepart_keluar`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_sparepart_recket_bekinser`.`detail_permintaan_sparepart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory_sparepart_recket_bekinser`.`detail_permintaan_sparepart` (
  `iddetail_permintaan_sparepart` INT NOT NULL,
  `idteknisi` INT NOT NULL,
  `idsparepart` INT NOT NULL,
  `idpermintaan_sparepart` INT NOT NULL,
  `jumlah` INT NOT NULL,
  PRIMARY KEY (`iddetail_permintaan_sparepart`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
