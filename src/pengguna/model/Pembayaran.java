/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.model;

import common.controller.AlphaNumericCode;
import common.model.Golongan;
import common.model.Koneksi;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author WILDAN
 */
public class Pembayaran {
    private String kd_bayar, kd_transaksi, nama_instansi, penanggung_jawab, nip, tipe_bayar, tgl_bayar, penerima;
    private int jumlah_bayar, sisa_tagihan, total_tagihan;
    private final Koneksi koneksi = new Koneksi();
    private String pesan;
    private ArrayList<Pembayaran> list;
    public String getKd_bayar() {
        return kd_bayar;
    }

    public void setKd_bayar(String kd_bayar) {
        this.kd_bayar = kd_bayar;
    }

    public String getKd_transaksi() {
        return kd_transaksi;
    }

    public void setKd_transaksi(String kd_transaksi) {
        this.kd_transaksi = kd_transaksi;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
    
    public int getJumlah_bayar() {
        return jumlah_bayar;
    }

    public void setJumlah_bayar(int jumlah_bayar) {
        this.jumlah_bayar = jumlah_bayar;
    }

    public String getTipe_bayar() {
        return tipe_bayar;
    }

    public void setTipe_bayar(String tipe_bayar) {
        this.tipe_bayar = tipe_bayar;
    }

    public String getNama_instansi() {
        return nama_instansi;
    }

    public void setNama_instansi(String nama_instansi) {
        this.nama_instansi = nama_instansi;
    }

    public String getPenanggung_jawab() {
        return penanggung_jawab;
    }

    public void setPenanggung_jawab(String penanggung_jawab) {
        this.penanggung_jawab = penanggung_jawab;
    }

    public int getSisa_tagihan() {
        return sisa_tagihan;
    }

    public void setSisa_tagihan(int sisa_tagihan) {
        this.sisa_tagihan = sisa_tagihan;
    }

    public int getTotal_tagihan() {
        return total_tagihan;
    }

    public void setTotal_tagihan(int total_tagihan) {
        this.total_tagihan = total_tagihan;
    }
    
    public String getTgl_bayar() {
        return tgl_bayar;
    }

    public void setTgl_bayar(String tgl_bayar) {
        this.tgl_bayar = tgl_bayar;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }
    
    public ArrayList<Pembayaran> getList() {
        return list;
    }
    
    public void setList(ArrayList<Pembayaran> list) {
        this.list = list;
    }
    
    public String getPesan() {
        return pesan;
    }
    
    public boolean tambah(){
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            
            try {
                simpan = true;
                SQLStatemen = "insert into pembayaran "
                        + "(kode_pembayaran, kode_transaksi, jumlah_bayar, tipe_bayar, nip, tgl_bayar)"
                        + "values (?,?,?,?,?,?)"; 

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_bayar);
                preparedStatement.setString(2, kd_transaksi);
                preparedStatement.setInt(3, jumlah_bayar);
                preparedStatement.setString(4, tipe_bayar);
                preparedStatement.setString(5, nip);
                preparedStatement.setString(6, tgl_bayar);
                jumlahSimpan = preparedStatement.executeUpdate();
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data pembayaran";
                    }
                }
                
                preparedStatement.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel pembayaran\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public String kode(){
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
            PreparedStatement statment;
            ResultSet result;
            try {
                String SQLStatemen = "select MAX(kode_pembayaran) from pembayaran";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                rset.next();
                String getLastCode = rset.getString(1);
                if (getLastCode==null){
                    this.kd_bayar = AlphaNumericCode.AutoIncrementKode("B000",3);
                }else {
                    SQLStatemen = "select count(kode_pembayaran) from pembayaran";
                    statment = connection.prepareStatement(SQLStatemen);
                    result = statment.executeQuery();
                    
                    result.next();
                    int count = result.getInt(1);
                    if(count < 9){
                         this.kd_bayar = AlphaNumericCode.AutoIncrementKode(getLastCode,3);
                    }else if(count<99){
                         this.kd_bayar = AlphaNumericCode.AutoIncrementKode(getLastCode,2);
                    }else{
                        this.kd_bayar = AlphaNumericCode.AutoIncrementKode(getLastCode,1);
                    }
                    statment.close();
                    result.close();
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                pesan = "Tidak dapat membuka tabel transaksi\n"+ex;
            }
        } else {
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
       return this.kd_bayar;
    }
    
    public boolean bacaTrx(String kd_transaksi){
        boolean adaKesalahan = false;
        Connection connection;
        list = new ArrayList<>();
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select transaksi.*, instansi.* from transaksi, instansi where transaksi.kode_instansi=instansi.kode_instansi and transaksi.kode_transaksi =?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_transaksi);
                rset = preparedStatement.executeQuery();
                rset.next();
                if (rset.getRow()>0){
                   this.kd_transaksi = rset.getString("kode_transaksi");
                   this.nama_instansi = rset.getString("nama_instansi");
                   this.penanggung_jawab = rset.getString("penanggung_jawab");
                   this.total_tagihan = rset.getInt("total_tagihan");
                   int total_bayar = total(rset.getString("kode_transaksi"));
                   this.sisa_tagihan = this.total_tagihan - total_bayar;
                }else{
                    adaKesalahan = true;
                    pesan = "Kode Transaksi \""+kd_transaksi+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data\n"+ex.getMessage();
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public int total(String kd_transaksi){
        Connection connection; 
        int total = 0;
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select sum(jumlah_bayar) from pembayaran where kode_transaksi = ?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_transaksi);
                rset = preparedStatement.executeQuery();
                rset.next();
                if (rset.getRow()>0){
                    total = rset.getInt(1);
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                pesan = "Tidak dapat membuka tabel pembayaran \n"+ex;
            }
        } else {
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
       return total;
    }
     public boolean bacaData(String kode_trx){
        boolean adaKesalahan = false;
        Connection connection;
        list = new ArrayList<>();
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select transaksi.*, tb_user.nip, tb_user.nama, pembayaran.* from transaksi, tb_user, pembayaran where pembayaran.kode_transaksi = transaksi.kode_transaksi and pembayaran.nip = tb_user.nip and transaksi.kode_transaksi =?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_transaksi);
                rset = preparedStatement.executeQuery();
                rset.next();
                rset.last();
                list = new ArrayList<>();
                if (rset.getRow()>0){
                    rset.first();
                    do {
                        Pembayaran pembayaran = new Pembayaran();
                        pembayaran.kd_bayar = rset.getString("kode_pembayaran");
                        pembayaran.penerima = rset.getString("nama");
                        pembayaran.tipe_bayar = rset.getString("tipe_bayar");
                        pembayaran.jumlah_bayar = rset.getInt("jumlah_bayar");
                        pembayaran.tgl_bayar = rset.getString("tgl_bayar");
                        list.add(pembayaran);
                    } while (rset.next());
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data\n"+ex.getMessage();
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
     
     public boolean cetakStruk(String kode_pembayaran){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = koneksi.getConnection()) != null){
                String SQLStatement;
                Statement statement;
                ResultSet resultSet = null; 
                String kode_trx= "";
                String kode_ins ="";
                HashMap<String, Object> map = new HashMap<>();
                try {
                    SQLStatement = "SELECT" +
                        " transaksi.`kode_transaksi` AS transaksi_kode_transaksi," +
                        " transaksi.`tanggal_transaksi` AS transaksi_tanggal_transaksi," +
                        " transaksi.`tanggal_mulai` AS transaksi_tanggal_mulai," +
                        " transaksi.`tanggal_akhir` AS transaksi_tanggal_akhir," +
                        " transaksi.`total_tagihan` AS transaksi_total_tagihan," +
                        " transaksi.`total_peserta` AS transaksi_total_peserta," +
                        " transaksi.`status` AS transaksi_status," +
                        " transaksi.`kode_instansi` AS transaksi_kode_instansi," +
                        " transaksi.`nip` AS transaksi_nip," +
                        " transaksi.`status_pembayaran` AS transaksi_status_pembayaran," +
                        " detail_transaksi.`kode_transaksi` AS detail_transaksi_kode_transaksi," +
                        " detail_transaksi.`kode_fasilitas` AS detail_transaksi_kode_fasilitas," +
                        " detail_transaksi.`jumlah_fasilitas` AS detail_transaksi_jumlah_fasilitas," +
                        " detail_transaksi.`tmp_stok` AS detail_transaksi_tmp_stok," +
                        " fasilitas.`kode_fasilitas` AS fasilitas_kode_fasilitas," +
                        " fasilitas.`nama_fasilitas` AS fasilitas_nama_fasilitas," +
                        " fasilitas.`harga_fasilitas` AS fasilitas_harga_fasilitas," +
                        " fasilitas.`status_fasilitas` AS fasilitas_status_fasilitas," +
                        " fasilitas.`aktif` AS fasilitas_aktif," +
                        " fasilitas.`perubahan_terakhir` AS fasilitas_perubahan_terakhir," +
                        " fasilitas.`stok` AS fasilitas_stok," +
                        " detail_instansi.`kode_instansi` AS detail_instansi_kode_instansi," +
                        " detail_instansi.`kode_golongan` AS detail_instansi_kode_golongan," +
                        " detail_instansi.`jumlah_peserta` AS detail_instansi_jumlah_peserta," +
                        " detail_instansi.`jumlah_panitia` AS detail_instansi_jumlah_panitia," +
                        " detail_instansi.`jumlah_pembina` AS detail_instansi_jumlah_pembina," +
                        " instansi.`kode_instansi` AS instansi_kode_instansi," +
                        " instansi.`nama_instansi` AS instansi_nama_instansi," +
                        " instansi.`penanggung_jawab` AS instansi_penanggung_jawab," +
                        " instansi.`alamat_instansi` AS instansi_alamat_instansi," +
                        " instansi.`nomor_telp` AS instansi_nomor_telp," +
                        " instansi.`email` AS instansi_email," +
                        " instansi.`perubahan_terakhir` AS instansi_perubahan_terakhir," +
                        " golongan.`kode_golongan` AS golongan_kode_golongan," +
                        " golongan.`nama_golongan` AS golongan_nama_golongan," +
                        " golongan.`harga` AS golongan_harga," +
                        " golongan.`perubahan_terakhir` AS golongan_perubahan_terakhir," +
                        " pembayaran.`kode_pembayaran` AS pembayaran_kode_pembayaran," +
                        " pembayaran.`kode_transaksi` AS pembayaran_kode_transaksi," +
                        " pembayaran.`jumlah_bayar` AS pembayaran_jumlah_bayar," +
                        " pembayaran.`tipe_bayar` AS pembayaran_tipe_bayar," +
                        " pembayaran.`nip` AS pembayaran_nip," +
                        " pembayaran.`tgl_bayar` AS pembayaran_tgl_bayar," +
                        " tb_user.`nip` AS tb_user_nip," +
                        " tb_user.`username` AS tb_user_username," +
                        " tb_user.`password` AS tb_user_password," +
                        " tb_user.`nama` AS tb_user_nama," +
                        " tb_user.`jenis_kelamin` AS tb_user_jenis_kelamin," +
                        " tb_user.`alamat` AS tb_user_alamat," +
                        " tb_user.`tgl_lahir` AS tb_user_tgl_lahir," +
                        " tb_user.`no_telp` AS tb_user_no_telp," +
                        " tb_user.`email` AS tb_user_email," +
                        " tb_user.`last_login` AS tb_user_last_login," +
                        " tb_user.`hak_akses` AS tb_user_hak_akses," +
                        " tb_user.`aktif` AS tb_user_aktif" +
                        " FROM" +
                        " `transaksi` transaksi INNER JOIN `detail_transaksi` detail_transaksi ON transaksi.`kode_transaksi` = detail_transaksi.`kode_transaksi`" +
                        " INNER JOIN `fasilitas` fasilitas ON detail_transaksi.`kode_fasilitas` = fasilitas.`kode_fasilitas`" +
                        " INNER JOIN `instansi` instansi ON transaksi.`kode_instansi` = instansi.`kode_instansi`" +
                        " INNER JOIN `pembayaran` pembayaran ON transaksi.`kode_transaksi` = pembayaran.`kode_transaksi`" +
                        " INNER JOIN `tb_user` tb_user ON transaksi.`nip` = tb_user.`nip`" +
                        " AND tb_user.`nip` = pembayaran.`nip`" +
                        " INNER JOIN `detail_instansi` detail_instansi ON instansi.`kode_instansi` = detail_instansi.`kode_instansi`\n" +
                        " INNER JOIN `golongan` golongan ON detail_instansi.`kode_golongan` = golongan.`kode_golongan`" +
                        " AND pembayaran.`nip` = tb_user.`nip`";

                    if (!kode_pembayaran.equals("")){
                        SQLStatement = SQLStatement + " where pembayaran.`kode_pembayaran`='"+kode_pembayaran+"'";
                    }
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(SQLStatement);
                    resultSet.next();
                    if(resultSet != null){
                        kode_trx = resultSet.getString("transaksi_kode_transaksi");
                        kode_ins = resultSet.getString("instansi_kode_instansi");
                        map.put("Kode Transaksi", kode_trx);
                        map.put("Kode Instansi", kode_ins);
                        map.put("Kode Pembayaran", kode_pembayaran);
                        
                        try {
                            JasperDesign disain = JRXmlLoader.load("src/pengguna/report/Struk.jrxml");
                            JasperReport nilaiLaporan = JasperCompileManager.compileReport(disain);
                            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                            JasperPrint cetak = JasperFillManager.fillReport(nilaiLaporan,map,connection);
                            JasperViewer jasperViewer = new JasperViewer(cetak, false);
                            jasperViewer.setLocationRelativeTo(null);
                            Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
                            jasperViewer.setSize((int)(1*dimensi.getWidth()),(int)(1*dimensi.getHeight()));
                            jasperViewer.setFocusableWindowState(true);
                            jasperViewer.setVisible(true);
                        } catch (JRException ex) {
                            adaKesalahan = true;
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }else{
                        
                    }
                } catch (SQLException ex) {
                    adaKesalahan = true;
                    pesan = "Tidak dapat membaca data"+ex;
                }
                
                
        }  else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
     public boolean cetakLaporan(int bulan, int tahun){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = koneksi.getConnection()) != null){
                String SQLStatement;
                Statement statement;
                ResultSet resultSet = null; 
                HashMap<String, Object> map = new HashMap();
                try {
                    SQLStatement = "SELECT" +
                        " MONTHNAME(pembayaran.`tgl_bayar`) AS nama_bulan," +
                        " year(pembayaran.tgl_bayar) as tahun," +
                        " month(pembayaran.tgl_bayar) as bulan" +
                        " FROM" +
                        " `pembayaran` pembayaran";
                    
                    if (tahun!=0){
                            map.put("Tahun", tahun);
                        if (bulan!=0){
                            map.put("Bulan", bulan);
                            SQLStatement = SQLStatement + " where year(pembayaran.tgl_bayar)="+tahun+" and month(pembayaran.tgl_bayar)='"+bulan+"' ";
                        }else{
                            
                            SQLStatement = SQLStatement + " where year(pembayaran.tgl_bayar)="+tahun;
                        }
                    }
                   
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(SQLStatement);
                    if (resultSet != null){
                        
                        try {
                            JasperDesign disain = JRXmlLoader.load("src/pengguna/report/LaporanPembayaran.jrxml");
                            JasperReport nilaiLaporan = JasperCompileManager.compileReport(disain);
                            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                            Locale locale = new Locale("id", "ID");
                            map.put(JRParameter.REPORT_LOCALE, locale);
                            map.put(JRParameter.REPORT_CONNECTION, connection);
                            JasperPrint cetak = JasperFillManager.fillReport(nilaiLaporan,map,resultSetDataSource);
                            JasperViewer jasperViewer = new JasperViewer(cetak,false);
                            jasperViewer.setLocationRelativeTo(null);
                            Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
                            jasperViewer.setSize((int)(1*dimensi.getWidth()),(int)(1*dimensi.getHeight()));
                            jasperViewer.setFocusableWindowState(true);
                            jasperViewer.setVisible(true);
                        } catch (JRException ex) {
                            adaKesalahan = true;
                            pesan = "Tidak dapat mencetak laporan\n"+ex;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Tidak ada laporan yang tersedia");
                    }
                } catch (SQLException ex) {
                    adaKesalahan = true;
                    pesan = "Tidak dapat membaca data"+ex;
                }
                
                
            
        }  else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
     
     public boolean cetakRekapitulasi(int tahun){
        boolean adaKesalahan = false;
        Connection connection;
        
        if ((connection = koneksi.getConnection()) != null){
                String SQLStatement;
                Statement statement;
                ResultSet resultSet = null; 
                HashMap<String, Object> map = new HashMap();
                try {
                    SQLStatement = "SELECT" +
                        " MONTHNAME(pembayaran.`tgl_bayar`) AS nama_bulan," +
                        " year(pembayaran.tgl_bayar) as tahun," +
                        " month(pembayaran.tgl_bayar) as bulan" +
                        " FROM" +
                        " `pembayaran` pembayaran";
                    
                    if (tahun!=0){
                            map.put("Tahun", tahun);
                            SQLStatement = SQLStatement + " where year(transaksi.tanggal_transaksi)="+tahun;
                        
                    }
                   
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(SQLStatement);
                    if (resultSet != null){
                        
                        try {
                            JasperDesign disain = JRXmlLoader.load("src/pengguna/report/LaporanRekapitulasiTransaksi.jrxml");
                            JasperReport nilaiLaporan = JasperCompileManager.compileReport(disain);
                            JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
                            Locale locale = new Locale("id", "ID");
                            map.put(JRParameter.REPORT_LOCALE, locale);
                            map.put(JRParameter.REPORT_CONNECTION, connection);
                            JasperPrint cetak = JasperFillManager.fillReport(nilaiLaporan,map,resultSetDataSource);
                            JasperViewer jasperViewer = new JasperViewer(cetak,false);
                            jasperViewer.setLocationRelativeTo(null);
                            Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
                            jasperViewer.setSize((int)(1*dimensi.getWidth()),(int)(1*dimensi.getHeight()));
                            jasperViewer.setFocusableWindowState(true);
                            jasperViewer.setVisible(true);
                        } catch (JRException ex) {
                            adaKesalahan = true;
                            pesan = "Tidak dapat mencetak laporan\n"+ex;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Tidak ada laporan yang tersedia");
                    }
                } catch (SQLException ex) {
                    adaKesalahan = true;
                    pesan = "Tidak dapat membaca data"+ex;
                }
                
                
            
        }  else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
}
