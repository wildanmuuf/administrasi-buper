/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.model;

import common.controller.AlphaNumericCode;
import common.model.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.model.PesanDialog;

/**
 *
 * @author WILDAN
 */
public class User {
    private String nip, username, password, nama, jenis_kelamin, alamat, no_telp, tgl_lahir, email, hak_akses, last_login, aktif;

    
    private String pesan;
    private ArrayList<User> list;
    private final Koneksi koneksi = new Koneksi();
    private final PesanDialog pesanDialog = new PesanDialog();
    
    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHak_akses() {
        return hak_akses;
    }

    public void setHak_akses(String hak_akses) {
        this.hak_akses = hak_akses;
    }
    
    public ArrayList<User> getList() {
        return list;
    }
    
    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
    public void setList(ArrayList<User> list) {
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
            
            try {
                simpan = true;
                SQLStatemen = "update tb_user set username=?, password=?, nama=?, jenis_kelamin=?, alamat=?, tgl_lahir=?, no_telp=?, email=?, hak_akses=?, aktif=? where nip=?";

                preparedStatement = connection.prepareStatement(SQLStatemen);   
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, nama);
                preparedStatement.setString(4, jenis_kelamin);
                preparedStatement.setString(5, alamat);
                preparedStatement.setString(6, tgl_lahir);
                preparedStatement.setString(7, no_telp);
                preparedStatement.setString(8, email);
                preparedStatement.setString(9, hak_akses);
                preparedStatement.setString(10, aktif);
                preparedStatement.setString(11, nip);
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
                SQLStatemen = "select * from tb_user where nip=?";
                
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, nip);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                if (rset.getRow()>0){
                    if (pesanDialog.tampilkanPilihan("NIP sudah ada\nApakah data diperbaharui?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                        simpan = true;
                        SQLStatemen = "update tb_user set username=?, password=?, nama=?, jenis_kelamin=?, alamat=?, tgl_lahir=?, no_telp=?, email=?, hak_akses=? where nip=?";
                        
                        preparedStatement = connection.prepareStatement(SQLStatemen);   
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        preparedStatement.setString(3, nama);
                        preparedStatement.setString(4, jenis_kelamin);
                        preparedStatement.setString(5, alamat);
                        preparedStatement.setString(6, tgl_lahir);
                        preparedStatement.setString(7, no_telp);
                        preparedStatement.setString(8, email);
                        preparedStatement.setString(9, hak_akses);
                        jumlahSimpan = preparedStatement.executeUpdate(); 
                    }
                } else {
                    simpan = true;
                    SQLStatemen = "insert into tb_user(nip, username, password, nama, jenis_kelamin, alamat, tgl_lahir, no_telp, email, hak_akses, aktif) "
                            + "values (?,?,?,?,?,?,?,?,?,?,?)"; 
                    
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(1, nip);
                    preparedStatement.setString(2, username);
                    preparedStatement.setString(3, password);
                    preparedStatement.setString(4, nama);
                    preparedStatement.setString(5, jenis_kelamin);
                    preparedStatement.setString(6, alamat);
                    preparedStatement.setString(7, tgl_lahir);
                    preparedStatement.setString(8, no_telp);
                    preparedStatement.setString(9, email);
                    preparedStatement.setString(10, hak_akses);
                    preparedStatement.setString(11, aktif);
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
                pesan = "Tidak dapat membuka tabel tb_user\n"+ex+"\n"+SQLStatemen;
            }            
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
  
    public boolean baca(String op, String nip){
        boolean adaKesalahan = false;	
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
            String SQLStatemen="";
            try {
                if(op.equals("login")){
                    SQLStatemen = "select * from tb_user where (nip=? or username=?) and aktif=?";
                     preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(1, nip);
                    preparedStatement.setString(2, nip);
                    preparedStatement.setString(3, "Y");
                    rset = preparedStatement.executeQuery();
                }else{
                    SQLStatemen = "select * from tb_user where nip=? or username=?";
                    preparedStatement = connection.prepareStatement(SQLStatemen);
                    preparedStatement.setString(1, nip);
                    preparedStatement.setString(2, nip);
                    rset = preparedStatement.executeQuery();
                }
                rset.next();
                if (rset.getRow()>0){
                    this.nip = rset.getString("nip");
                    this.username = rset.getString("username");
                    this.password = rset.getString("password");
                    this.nama = rset.getString("nama");
                    this.jenis_kelamin = rset.getString("jenis_kelamin");
                    this.alamat = rset.getString("alamat");
                    this.tgl_lahir = rset.getString("tgl_lahir");
                    this.no_telp = rset.getString("no_telp");
                    this.email = rset.getString("email");
                    this.hak_akses = rset.getString("hak_akses");
                    this.aktif = rset.getString("aktif");
                } else {
                    adaKesalahan = true;
                    pesan = "NIP \""+nip+"\" tidak ditemukan";
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
        if(baca("detail",nip)){
            if ((connection = koneksi.getConnection()) != null){
                PreparedStatement preparedStatement;
                ResultSet rset;
                String SQLStatemen;
                try {
                    SQLStatemen = "update tb_user set aktif=? where nip=?";
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
                SQLStatemen = "select * from tb_user";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new ArrayList<>();

                if (rset.getRow()>0){
                    rset.first();
                    do {
                        User user = new User();
                        user.nip = rset.getString("nip");
                        user.username = rset.getString("username");
                        user.nama = rset.getString("nama");
                        user.jenis_kelamin = rset.getString("jenis_kelamin");
                        user.alamat = rset.getString("alamat");
                        user.tgl_lahir = rset.getString("tgl_lahir");
                        user.no_telp = rset.getString("no_telp");
                        user.email = rset.getString("email");
                        user.hak_akses = rset.getString("hak_akses");
                        user.last_login = rset.getString("last_login");
                        user.aktif=rset.getString("aktif");
                        list.add(user);
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
    
    public boolean hapus(String nim){
        boolean adaKesalahan = false;	
        Connection connection; 
        
        if ((connection = koneksi.getConnection()) != null){
            int jumlahHapus;
            String SQLStatemen;
            PreparedStatement preparedStatement;
            
            try {
                SQLStatemen = "delete from tb_user where nip=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, nim);
                jumlahHapus = preparedStatement.executeUpdate();
                
                if (jumlahHapus < 1){
                    pesan = "Data user dengan Nomor Anggota "+nip+" tidak ditemukan";
                    adaKesalahan = true;
                }
                
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membuka tabel tb_user\n"+ex;
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
                String SQLStatemen = "select MAX(nip) from tb_user";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                rset.next();
                String getLastCode = rset.getString(1);
                if (getLastCode==null){
                    this.nip = AlphaNumericCode.AutoIncrementKode("P000",3);
                }else {
                    SQLStatemen = "select count(nip) from tb_user";
                    statment = connection.prepareStatement(SQLStatemen);
                    result = statment.executeQuery();
                    
                    result.next();
                    int count = result.getInt(1);
                    if(count < 9){
                         this.nip = AlphaNumericCode.AutoIncrementKode(getLastCode,3);
                    }else if(count<99){
                         this.nip = AlphaNumericCode.AutoIncrementKode(getLastCode,2);
                    }else{
                        this.nip = AlphaNumericCode.AutoIncrementKode(getLastCode,1);
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
       return this.nip;
    }
}
