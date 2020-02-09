/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.model;

import common.controller.AlphaNumericCode;
import common.model.Fasilitas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.model.Koneksi;
import common.model.PesanDialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
public class Transaksi {
    
    private String kd_transaksi, status,tgl_transaksi, tgl_mulai, tgl_akhir, total_tagihan, nip, namaUser, status_bayar;
    private String kd_instansi, instansi, penanggung_jawab;
    private String pesan;
    private int total_peserta;
    private ArrayList<Transaksi> list;
    private ArrayList<Fasilitas> listFasilitas = new ArrayList<>();
   
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();
    
    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }
    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
    
    public String getKd_instansi() {
        return kd_instansi;
    }

    public void setKd_instansi(String kd_instansi) {
        this.kd_instansi = kd_instansi;
    }

    public String getInstansi() {
        return instansi;
    }

    public String getPenanggung_jawab() {
        return penanggung_jawab;
    }

    public void setPenanggung_jawab(String penanggung_jawab) {
        this.penanggung_jawab = penanggung_jawab;
    }
    
    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }
    
    public String getKd_transaksi() {
        return kd_transaksi;
    }

    
    public void setKd_transaksi(String kd_transaksi) {
        this.kd_transaksi = kd_transaksi;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTgl_transaksi() {
        return tgl_transaksi;
    }

    public void setTgl_transaksi(String tgl_transaksi) {
        this.tgl_transaksi = tgl_transaksi;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai = tgl_mulai;
    }

    public String getTgl_akhir() {
        return tgl_akhir;
    }

    public void setTgl_akhir(String tgl_akhir) {
        this.tgl_akhir = tgl_akhir;
    }

    public int getTotal_peserta() {
        return total_peserta;
    }

    public String getStatus_bayar() {
        return status_bayar;
    }

    public void setStatus_bayar(String status_bayar) {
        this.status_bayar = status_bayar;
    }
    
    public void setTotal_peserta(int total_peserta) {
        this.total_peserta = total_peserta;
    }

    public String getTotal_tagihan() {
        return total_tagihan;
    }

    public void setTotal_tagihan(String total_tagihan) {
        this.total_tagihan = total_tagihan;
    }

    public ArrayList<Transaksi> getList() {
        return list;
    }

    public ArrayList<Fasilitas> getListFasilitas() {
        return listFasilitas;
    }

    public void setListFasilitas(ArrayList<Fasilitas> listFasilitas) {
        this.listFasilitas = listFasilitas;
    }
   
    public String getPesan() {
        return pesan;
    }
    
    public boolean update(String nip){
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                simpan = true;
                SQLStatemen = "update transaksi set tanggal_transaksi=?, tanggal_mulai=?, tanggal_akhir=?, total_tagihan=?, total_peserta=?, status=?, kode_instansi=? where kode_transaksi=?";

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, tgl_transaksi);
                preparedStatement.setString(2, tgl_mulai);
                preparedStatement.setString(3, tgl_akhir);
                preparedStatement.setString(4, total_tagihan);
                preparedStatement.setInt(5, total_peserta);
                preparedStatement.setString(6, status);
                preparedStatement.setString(7, kd_instansi);
                preparedStatement.setString(8, kd_transaksi);

                jumlahSimpan = preparedStatement.executeUpdate(); 
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal update data transaksi";
                    }else{
                        if(!listFasilitas.isEmpty()){
                            SQLStatemen = "delete from detail_transaksi where kode_transaksi=?";
                            preparedStatement = connection.prepareStatement(SQLStatemen);
                            preparedStatement.setString(1, kd_transaksi);
                            preparedStatement.executeUpdate();
                            for(Fasilitas fasilitas : listFasilitas){
                                SQLStatemen = "insert into detail_transaksi (kode_transaksi, kode_fasilitas, jumlah_fasilitas,tmp_stok) values (?,?,?,?)";
                                preparedStatement = connection.prepareStatement(SQLStatemen);
                                preparedStatement.setString(1, kd_transaksi);
                                preparedStatement.setString(2, fasilitas.getKd_fasilitas());
                                preparedStatement.setInt(3, fasilitas.getJumlah_fasilitas());
                                preparedStatement.setInt(4, fasilitas.getStok());
                                preparedStatement.executeUpdate();
                            }
                        }
                    }
                }
                
                preparedStatement.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tb_user\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public boolean tambah(){
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                simpan = true;
                SQLStatemen = "insert into transaksi "
                        + "(kode_transaksi, tanggal_transaksi, tanggal_mulai, tanggal_akhir, total_tagihan, total_peserta, status, kode_instansi, nip, status_pembayaran)"
                        + "values (?,?,?,?,?,?,?,?,?,?)"; 

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_transaksi);
                preparedStatement.setString(2, tgl_transaksi);
                preparedStatement.setString(3, tgl_mulai);
                preparedStatement.setString(4, tgl_akhir);
                preparedStatement.setString(5, total_tagihan);
                preparedStatement.setInt(6, total_peserta);
                preparedStatement.setString(7, status);
                preparedStatement.setString(8, kd_instansi);
                preparedStatement.setString(9, nip);
                preparedStatement.setString(10, status_bayar);
                jumlahSimpan = preparedStatement.executeUpdate();
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data mahasiswa";
                    }else{
                        
                        for(Fasilitas fasilitas : listFasilitas){
                            SQLStatemen = "insert into detail_transaksi (kode_transaksi, kode_fasilitas, jumlah_fasilitas, tmp_stok) values (?,?,?,?)";
                            preparedStatement = connection.prepareStatement(SQLStatemen);
                            preparedStatement.setString(1, kd_transaksi);
                            preparedStatement.setString(2, fasilitas.getKd_fasilitas());
                            preparedStatement.setInt(3, fasilitas.getJumlah_fasilitas());
                            preparedStatement.setInt(4, fasilitas.getStok());
                            preparedStatement.executeUpdate();
                        }
                        
                    }
                }
                
                preparedStatement.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel transaksi\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
  
    public boolean baca(String kd_transaksi){
        boolean adaKesalahan = false;	
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select * from transaksi t join detail_transaksi d on t.kode_transaksi = d.kode_transaksi join fasilitas f on d.kode_fasilitas = f.kode_fasilitas where t.kode_transaksi=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_transaksi);
                rset = preparedStatement.executeQuery();
                rset.next();
                if (rset.getRow()>0){
                    this.kd_transaksi = rset.getString("kode_transaksi");
                    this.tgl_transaksi = rset.getString("tanggal_transaksi");
                    this.tgl_mulai = rset.getString("tanggal_mulai");
                    this.tgl_akhir = rset.getString("tanggal_akhir");
                    this.total_tagihan = rset.getString("total_tagihan");
                    this.total_peserta = rset.getInt("total_peserta");
                    this.status = rset.getString("status");
                    this.kd_instansi = rset.getString("kode_instansi");
                    
                    do{
                        Fasilitas fasilitas = new Fasilitas();
                        fasilitas.setKd_fasilitas(rset.getString("kode_fasilitas"));
                        fasilitas.setNama(rset.getString("nama_fasilitas"));
                        fasilitas.setHarga(rset.getString("harga_fasilitas"));
                        fasilitas.setStatus(rset.getString("status_fasilitas"));
                        fasilitas.setStok(rset.getInt("tmp_stok"));
                        fasilitas.setJumlah_fasilitas(rset.getInt("jumlah_fasilitas"));
                        this.listFasilitas.add(fasilitas);
                    }while(rset.next());
                } else {
                    adaKesalahan = true;
                    pesan = "Kode Transaksi \""+kd_transaksi+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tbmahasiswa\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public void status(String kd_transaksi, String status){
        int jumlahSimpan=0;
        Connection connection;
        boolean simpan = false; 
        if(baca(kd_transaksi)){
            if ((connection = koneksi.getConnection()) != null){
                PreparedStatement preparedStatement;
                String SQLStatemen="";
                try {
                    simpan = true;
                    SQLStatemen = "update transaksi set status=? where kode_transaksi=?";
                    preparedStatement = connection.prepareStatement(SQLStatemen);   
                    preparedStatement.setString(1, status);
                    preparedStatement.setString(2, kd_transaksi);

                    jumlahSimpan = preparedStatement.executeUpdate();
                    if (simpan) {
                        if (jumlahSimpan < 1){
                        pesan = "Gagal merubah status";
                        }
                    }
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex){
                    pesan = "Tidak dapat membuka tabel transaksi\n"+ex;
                } 
                
            } else {
                pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
            }
        }
    }
    
    public boolean bacaData(){
        boolean adaKesalahan = false;
        Connection connection;
        list = new ArrayList<>();
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select transaksi.*, instansi.kode_instansi, instansi.nama_instansi, instansi.penanggung_jawab, tb_user.nip, tb_user.nama from transaksi, instansi, tb_user where transaksi.kode_instansi = instansi.kode_instansi and transaksi.nip = tb_user.nip";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new ArrayList<>();

                if (rset.getRow()>0){
                    rset.first();
                    do {
                        Transaksi transaksi = new Transaksi();
                        transaksi.kd_transaksi = rset.getString("kode_transaksi");
                        transaksi.tgl_transaksi = rset.getString("tanggal_transaksi");
                        transaksi.tgl_mulai = rset.getString("tanggal_mulai");
                        transaksi.tgl_akhir = rset.getString("tanggal_akhir");
                        transaksi.total_tagihan = rset.getString("total_tagihan");
                        transaksi.total_peserta = rset.getInt("total_peserta");
                        transaksi.status = rset.getString("status");
                        transaksi.kd_instansi = rset.getString("kode_instansi");
                        transaksi.instansi = rset.getString("nama_instansi");
                        transaksi.penanggung_jawab = rset.getString("penanggung_jawab");
                        transaksi.namaUser = rset.getString("nama");
                        transaksi.status_bayar = rset.getString("status_pembayaran");
                        list.add(transaksi);
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
    
    public boolean hapus(String kd_transaksi){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from transaksi where kode_transaksi=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_transaksi);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data transaksi dengan kode "+kd_transaksi+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel transaksi\n"+ex;
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
                String SQLStatemen = "select MAX(kode_transaksi) from transaksi";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                rset.next();
                String getLastCode = rset.getString(1);
                if (getLastCode!=null){
                    SQLStatemen = "select count(kode_transaksi) from transaksi";
                    statment = connection.prepareStatement(SQLStatemen);
                    result = statment.executeQuery();
                    result.next();
                    int count = result.getInt(1);
                    if(count < 9){
                        this.kd_transaksi = AlphaNumericCode.AutoIncrementKode(getLastCode,3);
                    }else if(count<99){
                        this.kd_transaksi = AlphaNumericCode.AutoIncrementKode(getLastCode,2);
                    }else{
                        this.kd_transaksi = AlphaNumericCode.AutoIncrementKode(getLastCode,1);
                    }
                    statment.close();
                    result.close();
                }else {
                    this.kd_transaksi = AlphaNumericCode.AutoIncrementKode("T000",3);
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
       return this.kd_transaksi;
    }
    
    public boolean bacaTanggal(){
         boolean adaKesalahan = false;
        Connection connection;
        list = new ArrayList<>();
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select transaksi.tanggal_mulai, transaksi.tanggal_akhir from transaksi";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new ArrayList<>();

                if (rset.getRow()>0){
                    rset.first();
                    do {
                        Transaksi transaksi = new Transaksi();
                        transaksi.kd_transaksi = rset.getString("kode_transaksi");
                        transaksi.tgl_transaksi = rset.getString("tanggal_transaksi");
                        transaksi.tgl_mulai = rset.getString("tanggal_mulai");
                        transaksi.tgl_akhir = rset.getString("tanggal_akhir");
                        transaksi.total_tagihan = rset.getString("total_tagihan");
                        transaksi.total_peserta = rset.getInt("total_peserta");
                        transaksi.status = rset.getString("status");
                        transaksi.kd_instansi = rset.getString("kode_instansi");
                        transaksi.instansi = rset.getString("nama_instansi");
                        transaksi.penanggung_jawab = rset.getString("penanggung_jawab");
                        list.add(transaksi);
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
    
    public void statusBayar(String kd_transaksi, String status){
        int jumlahSimpan=0;
        Connection connection;
        boolean simpan = false; 
        if(baca(kd_transaksi)){
            if ((connection = koneksi.getConnection()) != null){
                PreparedStatement preparedStatement;
                String SQLStatemen="";
                try {
                    simpan = true;
                    SQLStatemen = "update transaksi set status_pembayaran=? where kode_transaksi=?";
                    preparedStatement = connection.prepareStatement(SQLStatemen);   
                    preparedStatement.setString(1, status);
                    preparedStatement.setString(2, kd_transaksi);

                    jumlahSimpan = preparedStatement.executeUpdate();
                    if (simpan) {
                        if (jumlahSimpan < 1){
                        pesan = "Gagal merubah status";
                        }
                    }
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex){
                    pesan = "Tidak dapat membuka tabel transaksi\n"+ex;
                } 
                
            } else {
                pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
            }
        }
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
                        " MONTHNAME(transaksi.`tanggal_transaksi`) AS nama_bulan," +
                        " year(transaksi.tanggal_transaksi) as tahun," +
                        " month(transaksi.tanggal_transaksi) as bulan" +
                        " FROM" +
                        " `transaksi` transaksi";
                    
                    if (tahun!=0){
                            map.put("Tahun", tahun);
                        if (bulan!=0){
                            map.put("Bulan", bulan);
                            SQLStatement = SQLStatement + " where year(transaksi.tanggal_transaksi)="+tahun+" and month(transaksi.tanggal_transaksi)='"+bulan+"' ";
                        }else{
                            
                            SQLStatement = SQLStatement + " where year(transaksi.tanggal_transaksi)="+tahun;
                        }
                    }
                   
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(SQLStatement);
                    if (resultSet != null){
                        
                        try {
                            JasperDesign disain = JRXmlLoader.load("src/pengguna/report/LaporanTransaksi.jrxml");
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
                        " MONTHNAME(transaksi.`tanggal_transaksi`) AS nama_bulan," +
                        " year(transaksi.tanggal_transaksi) as tahun," +
                        " month(transaksi.tanggal_transaksi) as bulan" +
                        " FROM" +
                        " `transaksi` transaksi";
                    
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
