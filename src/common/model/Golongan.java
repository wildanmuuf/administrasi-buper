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
public class Golongan {
    
    private String kd_golongan, nama, harga, perubahan_terakhir;
    private int jumlah_peserta=0, jumlah_panitia=0, jumlah_pembina=0;
    private String pesan;
    private ArrayList<Golongan> list;
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();
    
    public String getKd_golongan() {
        return kd_golongan;
    }

    public void setKd_golongan(String kd_golongan) {
        this.kd_golongan = kd_golongan;
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

    public int getJumlah_peserta() {
        return jumlah_peserta;
    }

    public void setJumlah_peserta(int jumlah_peserta) {
        this.jumlah_peserta = jumlah_peserta;
    }

    public int getJumlah_panitia() {
        return jumlah_panitia;
    }

    public void setJumlah_panitia(int jumlah_panitia) {
        this.jumlah_panitia = jumlah_panitia;
    }

    public int getJumlah_pembina() {
        return jumlah_pembina;
    }

    public void setJumlah_pembina(int jumlah_pembina) {
        this.jumlah_pembina = jumlah_pembina;
    }
    
    public String getPerubahan_terakhir() {
        return perubahan_terakhir;
    }

    public void setPerubahan_terakhir(String perubahan_terakhir) {
        this.perubahan_terakhir = perubahan_terakhir;
    }

    public ArrayList<Golongan> getList() {
        return list;
    }
    
    public void setList(ArrayList<Golongan> list) {
        this.list = list;
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
                SQLStatemen = "update golongan set kode_golongan=?, nama_golongan=?, harga=? where kode_golongan=?";

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_golongan);
                preparedStatement.setString(2, nama);
                preparedStatement.setString(3, harga);
                preparedStatement.setString(4, kd_golongan);
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
                SQLStatemen = "select * from golongan where kode_golongan=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_golongan);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("NIP sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update golongan set kode_golongan=?, nama_golongan=?, harga=? where kode_golongan=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);
                        preparedStatement.setString(1, kd_golongan);
                        preparedStatement.setString(2, nama);
                        preparedStatement.setString(3, harga);
                        preparedStatement.setString(4, kd_golongan);
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into golongan(kode_golongan, nama_golongan, harga)"
                            + "values (?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(1, kd_golongan);
                    preparedStatement.setString(2, nama);
                    preparedStatement.setString(3, harga);
                    
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
                pesan = "Tidak dapat membuka tabel golongan\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
  
    public boolean baca(String kd_golongan){
        boolean adaKesalahan = false;	
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
                    
            try {
                String SQLStatemen = "select * from golongan where kode_golongan=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_golongan);
                rset = preparedStatement.executeQuery();
                rset.next();
                if (rset.getRow()>0){
                    this.kd_golongan = rset.getString("kode_golongan");
                    this.nama = rset.getString("nama_golongan");
                    this.harga = rset.getString("harga");
                } else {
                    adaKesalahan = true;
                    pesan = "Kode Golongan \""+kd_golongan+"\" tidak ditemukan";
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel golongan\n"+ex;
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
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
                SQLStatemen = "select * from golongan";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new ArrayList<>();

                if (rset.getRow()>0){
                    rset.first();
                    do {
                        Golongan golongan = new Golongan();
                        golongan.kd_golongan = rset.getString("kode_golongan");
                        golongan.nama = rset.getString("nama_golongan");
                        golongan.harga = rset.getString("harga");
                        golongan.perubahan_terakhir = rset.getString("perubahan_terakhir");
                        list.add(golongan);
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
    
    public boolean hapus(String kd_golongan){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from golongan where kode_golongan=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_golongan);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data golongan dengan kode "+kd_golongan+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel golongan\n"+ex;
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
                String SQLStatemen = "select MAX(kode_golongan) from golongan";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                rset.next();
                String getLastCode = rset.getString(1);
                if (getLastCode==null){
                    this.kd_golongan = AlphaNumericCode.AutoIncrementKode("G000",3);
                }else {
                    
                    SQLStatemen = "select count(kode_golongan) from golongan";
                    statment = connection.prepareStatement(SQLStatemen);
                    result = statment.executeQuery();
                    
                    result.next();
                    int count = result.getInt(1);
                    if(count < 9){
                         this.kd_golongan = AlphaNumericCode.AutoIncrementKode(getLastCode,3);
                    }else if(count<99){
                         this.kd_golongan = AlphaNumericCode.AutoIncrementKode(getLastCode,2);
                    }else{
                        this.kd_golongan = AlphaNumericCode.AutoIncrementKode(getLastCode,1);
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
       return this.kd_golongan;
    }
    
}
