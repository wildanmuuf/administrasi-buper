/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.model;

import common.controller.AlphaNumericCode;
import common.model.Golongan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.model.Koneksi;
import common.model.PesanDialog;

/**
 *
 * @author WILDAN
 */
public class Instansi {
    private String kd_instansi, instansi,penanggung_jawab, alamat_instansi, no_telp, email;
    private String pesan;
    private ArrayList<Instansi> list;
    private final Koneksi koneksi = new Koneksi();
    private ArrayList<Golongan> listGolongan = new ArrayList<>();
    private final PesanDialog pesanDialog = new PesanDialog();

    public String getKd_instansi() {
        return kd_instansi;
    }

    public void setKd_instansi(String kd_instansi) {
        this.kd_instansi = kd_instansi;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getPenanggung_jawab() {
        return penanggung_jawab;
    }

    public void setPenanggung_jawab(String penanggung_jawab) {
        this.penanggung_jawab = penanggung_jawab;
    }

    public String getAlamat_instansi() {
        return alamat_instansi;
    }

    public void setAlamat_instansi(String alamat_instansi) {
        this.alamat_instansi = alamat_instansi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
    
    public ArrayList<Instansi> getList() {
        return list;
    }
    
    public void setList(ArrayList<Instansi> list) {
        this.list = list;
    }

    public ArrayList<Golongan> getListGolongan() {
        return listGolongan;
    }

    public void setListGolongan(ArrayList<Golongan> listGolongan) {
        this.listGolongan = listGolongan;
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
                SQLStatemen = "update instansi set nama_instansi=?, penanggung_jawab=?, alamat_instansi=?, nomor_telp=?, email=? where kode_instansi=?";

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, instansi);
                preparedStatement.setString(2, penanggung_jawab);
                preparedStatement.setString(3, alamat_instansi);
                preparedStatement.setString(4, no_telp);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, kd_instansi);

                jumlahSimpan = preparedStatement.executeUpdate(); 
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal update data instansi";
                    }else{
                        if(!listGolongan.isEmpty()){
                            SQLStatemen = "delete from detail_instansi where kode_instansi=?";
                            preparedStatement = connection.prepareStatement(SQLStatemen);
                            preparedStatement.setString(1, kd_instansi);
                            preparedStatement.executeUpdate();
                            for(Golongan golongan : listGolongan){
                                SQLStatemen = "insert into detail_instansi (kode_instansi, kode_golongan, jumlah_peserta, jumlah_panitia, jumlah_pembina) values (?,?,?,?,?)";
                                preparedStatement = connection.prepareStatement(SQLStatemen);
                                preparedStatement.setString(1, kd_instansi);
                                preparedStatement.setString(2, golongan.getKd_golongan());
                                preparedStatement.setInt(3, golongan.getJumlah_peserta());
                                preparedStatement.setInt(4, golongan.getJumlah_panitia());
                                preparedStatement.setInt(5, golongan.getJumlah_pembina());
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
                SQLStatemen = "insert into instansi "
                        + "(kode_instansi, nama_instansi, penanggung_jawab, alamat_instansi, nomor_telp, email)"
                        + "values (?,?,?,?,?,?)"; 

                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_instansi);
                preparedStatement.setString(2, instansi);
                preparedStatement.setString(3, penanggung_jawab);
                preparedStatement.setString(4, alamat_instansi);
                preparedStatement.setString(5, no_telp);
                preparedStatement.setString(6, email);
                jumlahSimpan = preparedStatement.executeUpdate();
                
                if (simpan) {
                    if (jumlahSimpan < 1){
                        adaKesalahan = true; 
                        pesan = "Gagal menyimpan data mahasiswa";
                    }else{
                        
                        for(Golongan golongan : listGolongan){
                            SQLStatemen = "insert into detail_instansi (kode_instansi, kode_golongan, jumlah_peserta, jumlah_panitia, jumlah_pembina) values (?,?,?,?,?)";
                            preparedStatement = connection.prepareStatement(SQLStatemen);
                            preparedStatement.setString(1, kd_instansi);
                            preparedStatement.setString(2, golongan.getKd_golongan());
                            preparedStatement.setInt(3, golongan.getJumlah_peserta());
                            preparedStatement.setInt(4, golongan.getJumlah_panitia());
                            preparedStatement.setInt(5, golongan.getJumlah_pembina());
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
  
    public boolean baca(String kd_instansi){
        boolean adaKesalahan = false;	
        Connection connection; 
        if ((connection = koneksi.getConnection()) != null){
            PreparedStatement preparedStatement;
            ResultSet rset;
            try {
                String SQLStatemen = "select * from instansi i join detail_instansi d on i.kode_instansi = d.kode_instansi join golongan g on d.kode_golongan = g.kode_golongan where i.kode_instansi=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                preparedStatement.setString(1, kd_instansi);
                rset = preparedStatement.executeQuery();
                rset.next();
                if (rset.getRow()>0){
                    this.kd_instansi = rset.getString("kode_instansi");
                    this.instansi = rset.getString("nama_instansi");
                    this.penanggung_jawab = rset.getString("penanggung_jawab");
                    this.no_telp = rset.getString("nomor_telp");
                    this.alamat_instansi = rset.getString("alamat_instansi");
                    this.email = rset.getString("email");
                    do{
                        Golongan golongan = new Golongan();
                        golongan.setKd_golongan(rset.getString("kode_golongan"));
                        golongan.setNama(rset.getString("nama_golongan"));
                        golongan.setHarga(rset.getString("harga"));
                        golongan.setJumlah_peserta(rset.getInt("jumlah_peserta"));
                        golongan.setJumlah_panitia(rset.getInt("jumlah_panitia"));
                        golongan.setJumlah_pembina(rset.getInt("jumlah_pembina"));
                        this.listGolongan.add(golongan);
                    }while(rset.next());
                } else {
                    adaKesalahan = true;
                    pesan = "Kode instansi \""+kd_instansi+"\" tidak ditemukan";
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
    
    
    public boolean bacaData(){
        boolean adaKesalahan = false;
        Connection connection;
        list = new ArrayList<>();
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select * from instansi";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new ArrayList<>();

                if (rset.getRow()>0){
                    rset.first();
                    do {
                        Instansi instansi = new Instansi();
                        instansi.kd_instansi = rset.getString("kode_instansi");
                        instansi.instansi = rset.getString("nama_instansi");
                        instansi.penanggung_jawab = rset.getString("penanggung_jawab");
                        instansi.alamat_instansi = rset.getString("alamat_instansi");
                        instansi.no_telp = rset.getString("nomor_telp");
                        instansi.email = rset.getString("email");
                        list.add(instansi);
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
                String SQLStatemen = "select MAX(kode_instansi) from instansi";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                rset.next();
                String getLastCode = rset.getString(1);
                if (getLastCode==null){
                    this.kd_instansi = AlphaNumericCode.AutoIncrementKode("I000",3);
                }else {
                    SQLStatemen = "select count(kode_instansi) from instansi";
                    statment = connection.prepareStatement(SQLStatemen);
                    result = statment.executeQuery();
                    
                    result.next();
                    int count = result.getInt(1);
                    if(count < 9){
                         this.kd_instansi = AlphaNumericCode.AutoIncrementKode(getLastCode,3);
                    }else if(count<99){
                         this.kd_instansi = AlphaNumericCode.AutoIncrementKode(getLastCode,2);
                    }else{
                        this.kd_instansi = AlphaNumericCode.AutoIncrementKode(getLastCode,1);
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
       return this.kd_instansi;
    }
    
}
