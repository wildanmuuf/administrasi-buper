/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.model;

import common.controller.AlphaNumericCode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author WILDAN
 */
public class Fasilitas {
    
    private String kd_fasilitas, nama, harga, status, perubahan_terakhir, aktif;
    private String pesan;
    private int stok, jumlah_fasilitas;
    private ArrayList<Fasilitas> list;
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();
    
    public String getKd_fasilitas() {
        return kd_fasilitas;
    }

    public void setKd_fasilitas(String kd_fasilitas) {
        this.kd_fasilitas = kd_fasilitas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPerubahan_terakhir() {
        return perubahan_terakhir;
    }

    public void setPerubahan_terakhir(String perubahan_terakhir) {
        this.perubahan_terakhir = perubahan_terakhir;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getJumlah_fasilitas() {
        return jumlah_fasilitas;
    }

    public void setJumlah_fasilitas(int jumlah_fasilitas) {
        this.jumlah_fasilitas = jumlah_fasilitas;
    }
    
    public ArrayList<Fasilitas> getList() {
        return list;
    }
    
    public void setList(ArrayList<Fasilitas> list) {
        this.list = list;
    }
    
    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
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
                SQLStatemen = "update fasilitas set kode_fasilitas=?, nama_fasilitas=?, harga_fasilitas=?, status_fasilitas=?,stok=?, aktif=? where kode_fasilitas=?";

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_fasilitas);
                preparedStatement.setString(2, nama);
                preparedStatement.setString(3, harga);
                preparedStatement.setString(4, status);
                preparedStatement.setInt(5, stok);
                preparedStatement.setString(6, aktif);
                preparedStatement.setString(7, kd_fasilitas);
                jumlahSimpan = preparedStatement.executeUpdate(); 
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data mahasiswa";
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
                SQLStatemen = "select * from fasilitas where kode_fasilitas=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_fasilitas);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("NIP sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update fasilitas set kode_fasilitas=?, nama_fasilitas=?, harga_fasilitas=?, status_fasilitas=?, stok=?, aktif=? where kode_fasilitas=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);
                        preparedStatement.setString(1, kd_fasilitas);
                        preparedStatement.setString(2, nama);
                        preparedStatement.setString(3, harga);
                        preparedStatement.setString(4, status);
                        preparedStatement.setInt(5, stok);
                        preparedStatement.setString(6, aktif);
                        preparedStatement.setString(7, kd_fasilitas);
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into fasilitas(kode_fasilitas, nama_fasilitas, harga_fasilitas, status_fasilitas, stok, aktif)"
                            + "values (?,?,?,?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(1, kd_fasilitas);
                    preparedStatement.setString(2, nama);
                    preparedStatement.setString(3, harga);
                    preparedStatement.setString(4, status);
                    preparedStatement.setInt(5, stok);
                    preparedStatement.setString(6, aktif);
                    
                    jumlahSimpan = preparedStatement.executeUpdate();
                }
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data mahasiswa";
                    }
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();                
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel fasilitas\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
  
    public boolean baca(String kd_fasilitas, String op){
        boolean adaKesalahan = false;	
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "";
                if(op.equals("stoking")){
                    SQLStatemen = "select * from fasilitas where kode_fasilitas=? and status_fasilitas='Tidak Wajib'";
                }else{
                    SQLStatemen = "select * from fasilitas where kode_fasilitas=?";
                }
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_fasilitas);
                rset = preparedStatement.executeQuery();
                rset.next();
                if (rset.getRow()>0){
                    this.kd_fasilitas = rset.getString("kode_fasilitas");
                    this.nama = rset.getString("nama_fasilitas");
                    this.harga = rset.getString("harga_fasilitas");
                    this.stok = rset.getInt("stok");
                    this.status = rset.getString("status_fasilitas");
                    this.aktif = rset.getString("aktif");
                } else {
                    adaKesalahan = true;
                    pesan = "Kode Fasilitas \""+kd_fasilitas+"\" tidak ditemukan";
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
    
    public boolean aktif(String nip, String aktif){
        boolean adaKesalahan = false;
        int jumlahSimpan=0;
        Connection connection;
        boolean simpan = false; 
        if(baca(nip, "aktif")){
            if ((connection = koneksi.getConnection()) != null){
                PreparedStatement preparedStatement;
                ResultSet rset;
                String SQLStatemen="";
                try {
                    SQLStatemen = "update fasilitas set aktif=? where kode_fasilitas=?";
                    preparedStatement = connection.prepareStatement(SQLStatemen);   
                    preparedStatement.setString(1, aktif);
                    preparedStatement.setString(2, nip);

                    jumlahSimpan = preparedStatement.executeUpdate();
                    if (simpan) {
                        if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal mengaktifkan pengguna";
                        }
                    }
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException ex){
                    adaKesalahan = true;
                    pesan = "Tidak dapat membuka tabel tb\n"+ex;
                } 
                
            } else {
                adaKesalahan = true;
                pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
            }
        }
        return !adaKesalahan;
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
                SQLStatemen = "select * from fasilitas";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new ArrayList<>();

                if (rset.getRow()>0){
                    rset.first();
                    do {
                        Fasilitas fasilitas = new Fasilitas();
                        fasilitas.kd_fasilitas = rset.getString("kode_fasilitas");
                        fasilitas.nama = rset.getString("nama_fasilitas");
                        fasilitas.harga = rset.getString("harga_fasilitas");
                        fasilitas.status = rset.getString("status_fasilitas");
                        fasilitas.stok = rset.getInt("stok");
                        fasilitas.aktif = rset.getString("aktif");
                        fasilitas.perubahan_terakhir = rset.getString("perubahan_terakhir");
                        list.add(fasilitas);
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
    
    public boolean hapus(String kd_fasilitas){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from fasilitas where kode_fasilitas=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_fasilitas);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data fasilitas dengan kode "+kd_fasilitas+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel fasilitas\n"+ex;
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
                String SQLStatemen = "select MAX(kode_fasilitas) from fasilitas";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                rset.next();
                String getLastCode = rset.getString(1);
                if (getLastCode==null){
                    this.kd_fasilitas = AlphaNumericCode.AutoIncrementKode("F000",3);
                }else {
                    
                    SQLStatemen = "select count(kode_fasilitas) from fasilitas";
                    statment = connection.prepareStatement(SQLStatemen);
                    result = statment.executeQuery();
                    
                    result.next();
                    int count = result.getInt(1);
                    if(count < 9){
                         this.kd_fasilitas = AlphaNumericCode.AutoIncrementKode(getLastCode,3);
                    }else if(count<99){
                         this.kd_fasilitas = AlphaNumericCode.AutoIncrementKode(getLastCode,2);
                    }else{
                        this.kd_fasilitas = AlphaNumericCode.AutoIncrementKode(getLastCode,1);
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
       return this.kd_fasilitas;
    }
    
    public boolean listPilihFasilitas(){
        boolean adaKesalahan = false;
        Connection connection;
        list = new ArrayList<>();
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select * from fasilitas where aktif='Y'";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new ArrayList<>();

                if (rset.getRow()>0){
                    rset.first();
                    do {
                        Fasilitas fasilitas = new Fasilitas();
                        fasilitas.kd_fasilitas = rset.getString("kode_fasilitas");
                        fasilitas.nama = rset.getString("nama_fasilitas");
                        fasilitas.harga = rset.getString("harga_fasilitas");
                        fasilitas.stok = rset.getInt("stok");
                        fasilitas.status = rset.getString("status_fasilitas");
                        list.add(fasilitas);
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
    
    public boolean updateStok(String kode_fasilitas, int stokNow){
        boolean adaKesalahan = false;
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            SQLStatemen = "select * from fasilitas where kode_fasilitas=? and status_fasilitas";
            try {
                    simpan = true;
                    SQLStatemen = "update fasilitas set stok=? where kode_fasilitas=?";
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setInt(1, stokNow);
                    preparedStatement.setString(2, kode_fasilitas);
                    
                    jumlahSimpan = preparedStatement.executeUpdate(); 
                    if (simpan) {
                        if (jumlahSimpan < 1){
                            adaKesalahan = true;
                            pesan = "Gagal menyimpan data fasilitas";
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
}
