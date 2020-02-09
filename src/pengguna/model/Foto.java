/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.model;

import common.controller.AlphaNumericCode;
import common.model.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author WILDAN
 */
public class Foto {
    private String kd_foto, kd_instansi,judul_foto, nama_file;
    private String pesan;
    private ArrayList<Foto> list;
    private final Koneksi koneksi = new Koneksi();

    public String getKd_foto() {
        return kd_foto;
    }

    public void setKd_foto(String kd_foto) {
        this.kd_foto = kd_foto;
    }

    
    public String getKd_instansi() {
        return kd_instansi;
    }

    public void setKd_instansi(String kd_instansi) {
        this.kd_instansi = kd_instansi;
    }

    public String getJudul_foto() {
        return judul_foto;
    }

    public void setJudul_foto(String judul_foto) {
        this.judul_foto = judul_foto;
    }

    public String getNama_file() {
        return nama_file;
    }

    public void setNama_file(String nama_file) {
        this.nama_file = nama_file;
    }
    
    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ArrayList<Foto> getList() {
        return list;
    }

    public void setList(ArrayList<Foto> list) {
        this.list = list;
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
                SQLStatemen = "insert into foto "
                        + "(judul_foto, nama_file, kode_instansi)"
                        + "values (?,?,?)"; 

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, judul_foto);
                preparedStatement.setString(2, nama_file);
                preparedStatement.setString(3, kd_instansi);
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
                pesan = "Tidak dapat membuka tabel transaksi\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
  
    
    public boolean update(String nip){
        boolean adaKesalahan = false;
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahSimpan=0;
            boolean simpan = false; 
            String SQLStatemen="";
            PreparedStatement preparedStatement;
            
            try {
                simpan = true;
                SQLStatemen = "update foto set judul_foto=?, nama_file=?, where kode_foto=?";

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, judul_foto);
                preparedStatement.setString(2, nama_file);
                preparedStatement.setString(3, kd_foto);

                jumlahSimpan = preparedStatement.executeUpdate(); 
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal update data instansi";
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
    
    
    public boolean baca(String kd_instansi){
        boolean adaKesalahan = false;	
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
            try {
                String SQLStatemen = "select * from foto where kode_instansi=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_instansi);
                rset = preparedStatement.executeQuery();
                rset.next();
                rset.last();
                if (rset.getRow()>0){
                    rset.first();
                    list = new ArrayList<>();
                    do{
                        Foto foto = new Foto();
                        foto.kd_foto = rset.getString("kode_foto");
                        foto.judul_foto = rset.getString("judul_foto");
                        foto.nama_file = rset.getString("nama_file");
                        foto.kd_instansi = rset.getString("kode_instansi");
                        list.add(foto);
                    }while(rset.next());
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
    
    
    
    public boolean hapus(String kd_foto){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from foto where kode_foto=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_foto);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data transaksi dengan kode "+kd_foto+" tidak ditemukan";
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
                String SQLStatemen = "select MAX(kode_foto) from foto";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                rset.next();
                String getLastCode = rset.getString(1);

                if (getLastCode==null){
                    this.kd_foto = AlphaNumericCode.AutoIncrementKode("L000",3);
                }else {
                    SQLStatemen = "select count(kode_transaksi) from transaksi";
                    statment = connection.prepareStatement(SQLStatemen);
                    result = statment.executeQuery();
                    
                    result.next();
                    int count = result.getInt(1);
                    if(count < 9){
                         this.kd_foto = AlphaNumericCode.AutoIncrementKode(getLastCode,3);
                    }else if(count<99){
                         this.kd_foto = AlphaNumericCode.AutoIncrementKode(getLastCode,2);
                    }else{
                        this.kd_foto = AlphaNumericCode.AutoIncrementKode(getLastCode,1);
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
       return this.kd_foto;
    }
}
