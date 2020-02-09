-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 18, 2019 at 06:12 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_buper`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_instansi`
--

CREATE TABLE IF NOT EXISTS `detail_instansi` (
  `kode_instansi` varchar(10) NOT NULL,
  `kode_golongan` varchar(10) NOT NULL,
  `jumlah_peserta` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE IF NOT EXISTS `detail_transaksi` (
  `kode_transaksi` varchar(10) NOT NULL,
  `kode_fasilitas` varchar(10) NOT NULL,
  `jumlah_fasilitas` int(3) NOT NULL,
  `tmp_stok` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `fasilitas`
--

CREATE TABLE IF NOT EXISTS `fasilitas` (
  `kode_fasilitas` varchar(10) NOT NULL DEFAULT '',
  `nama_fasilitas` varchar(20) DEFAULT NULL,
  `harga_fasilitas` int(10) NOT NULL,
  `status_fasilitas` varchar(15) NOT NULL,
  `aktif` varchar(1) NOT NULL,
  `perubahan_terakhir` varchar(20) DEFAULT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fasilitas`
--

INSERT INTO `fasilitas` (`kode_fasilitas`, `nama_fasilitas`, `harga_fasilitas`, `status_fasilitas`, `aktif`, `perubahan_terakhir`, `stok`) VALUES
('F001', 'Biaya Listrik', 100000, 'Wajib', 'Y', '10/12/19 3:21:25', 1),
('F002', 'Biaya Kebersihan', 20000, 'Wajib', 'Y', '10/12/19 3:21:46', 1),
('F003', 'Pendopo', 250000, 'Tidak Wajib', 'Y', '10/12/19 3:28:43', 4),
('F004', 'Tenda Alas', 110000, 'Tidak Wajib', 'Y', '10/12/19 3:31:10', 0),
('F005', 'Tenda Tanpa Alas', 80000, 'Tidak Wajib', 'T', '10/12/19 3:31:07', 5);

-- --------------------------------------------------------

--
-- Table structure for table `foto`
--

CREATE TABLE IF NOT EXISTS `foto` (
  `kode_foto` int(10) NOT NULL,
  `judul_foto` text NOT NULL,
  `nama_file` varchar(50) NOT NULL,
  `kode_instansi` varchar(10) NOT NULL,
  `perubahan_terakhir` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `golongan`
--

CREATE TABLE IF NOT EXISTS `golongan` (
  `kode_golongan` varchar(10) NOT NULL,
  `nama_golongan` varchar(15) NOT NULL,
  `harga` varchar(10) NOT NULL,
  `perubahan_terakhir` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `golongan`
--

INSERT INTO `golongan` (`kode_golongan`, `nama_golongan`, `harga`, `perubahan_terakhir`) VALUES
('G001', 'SD', '3000', '05/12/19 13:56:51'),
('G002', 'SMP', '3250', '05/12/19 13:57:06'),
('G003', 'SMA', '3500', '05/12/19 13:57:14'),
('G004', 'Mahasiswa', '4000', '05/12/19 13:57:23'),
('G005', 'Umum', '5000', '05/12/19 13:57:37');

-- --------------------------------------------------------

--
-- Table structure for table `instansi`
--

CREATE TABLE IF NOT EXISTS `instansi` (
  `kode_instansi` varchar(10) NOT NULL,
  `nama_instansi` varchar(20) NOT NULL,
  `penanggung_jawab` varchar(30) NOT NULL,
  `alamat_instansi` text NOT NULL,
  `nomor_telp` varchar(15) NOT NULL,
  `email` varchar(20) NOT NULL,
  `perubahan_terakhir` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE IF NOT EXISTS `pembayaran` (
  `kode_pembayaran` varchar(10) NOT NULL,
  `kode_transaksi` varchar(10) NOT NULL,
  `jumlah_bayar` int(12) NOT NULL,
  `status_bayar` varchar(15) NOT NULL,
  `nip` varchar(10) NOT NULL,
  `tgl_bayar` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE IF NOT EXISTS `tb_user` (
  `nip` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL,
  `alamat` text NOT NULL,
  `tgl_lahir` varchar(11) NOT NULL,
  `no_telp` varchar(16) NOT NULL,
  `email` varchar(25) NOT NULL,
  `last_login` varchar(20) DEFAULT NULL,
  `hak_akses` varchar(15) NOT NULL,
  `aktif` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`nip`, `username`, `password`, `nama`, `jenis_kelamin`, `alamat`, `tgl_lahir`, `no_telp`, `email`, `last_login`, `hak_akses`, `aktif`) VALUES
('P001', 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'Wildan', 'Laki-Laki', 'Jl. asdas', '15/01/1996', '081234567', 'asd@mail.com', '2019/12/18 11:52:08', 'Administrator', 'Y'),
('P002', 'pengguna1', '81dc9bdb52d04dc20036dbd8313ed055', 'Slamet', 'Laki-Laki', 'asdasdddd11', '17/10/2018', '21321', 'asdf', '2019/12/18 12:11:55', 'Pengguna', 'Y'),
('P003', 'sad', 'd41d8cd98f00b204e9800998ecf8427e', 'asd', 'Laki-Laki', 'asd', '15/01/2019', 'asd', '0888', NULL, 'Pengguna', 'T');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE IF NOT EXISTS `transaksi` (
  `kode_transaksi` varchar(10) NOT NULL,
  `tanggal_transaksi` varchar(20) NOT NULL,
  `tanggal_mulai` varchar(11) NOT NULL,
  `tanggal_akhir` varchar(11) NOT NULL,
  `total_tagihan` int(10) NOT NULL,
  `total_peserta` int(10) NOT NULL,
  `status` varchar(15) NOT NULL,
  `kode_instansi` varchar(10) NOT NULL,
  `nip` varchar(10) NOT NULL,
  `status_pembayaran` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_instansi`
--
ALTER TABLE `detail_instansi`
  ADD KEY `kode_instansi` (`kode_instansi`), ADD KEY `kode_golongan` (`kode_golongan`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `kode_fasilitas` (`kode_fasilitas`), ADD KEY `kode_transaksi` (`kode_transaksi`);

--
-- Indexes for table `fasilitas`
--
ALTER TABLE `fasilitas`
  ADD PRIMARY KEY (`kode_fasilitas`);

--
-- Indexes for table `foto`
--
ALTER TABLE `foto`
  ADD PRIMARY KEY (`kode_foto`), ADD KEY `kode_instansi` (`kode_instansi`);

--
-- Indexes for table `golongan`
--
ALTER TABLE `golongan`
  ADD PRIMARY KEY (`kode_golongan`);

--
-- Indexes for table `instansi`
--
ALTER TABLE `instansi`
  ADD PRIMARY KEY (`kode_instansi`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`kode_pembayaran`), ADD KEY `nip` (`nip`), ADD KEY `kode_transaksi` (`kode_transaksi`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`kode_transaksi`), ADD KEY `kode_instansi` (`kode_instansi`), ADD KEY `nip` (`nip`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `foto`
--
ALTER TABLE `foto`
  MODIFY `kode_foto` int(10) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_instansi`
--
ALTER TABLE `detail_instansi`
ADD CONSTRAINT `detail_instansi_ibfk_1` FOREIGN KEY (`kode_instansi`) REFERENCES `instansi` (`kode_instansi`),
ADD CONSTRAINT `detail_instansi_ibfk_2` FOREIGN KEY (`kode_golongan`) REFERENCES `golongan` (`kode_golongan`);

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`kode_fasilitas`) REFERENCES `fasilitas` (`kode_fasilitas`),
ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`kode_transaksi`) REFERENCES `transaksi` (`kode_transaksi`);

--
-- Constraints for table `foto`
--
ALTER TABLE `foto`
ADD CONSTRAINT `foto_ibfk_1` FOREIGN KEY (`kode_instansi`) REFERENCES `instansi` (`kode_instansi`);

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
ADD CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `tb_user` (`nip`),
ADD CONSTRAINT `pembayaran_ibfk_2` FOREIGN KEY (`kode_transaksi`) REFERENCES `transaksi` (`kode_transaksi`);

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`kode_instansi`) REFERENCES `instansi` (`kode_instansi`),
ADD CONSTRAINT `transaksi_ibfk_4` FOREIGN KEY (`nip`) REFERENCES `tb_user` (`nip`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
