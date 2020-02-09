/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import common.model.Koneksi;

/**
 *
 * @author WILDAN
 */
public class ActivityController {
    private String today;

    public String getToday() {
        return this.today;
    }

    public void setToday(String today) {
        
        this.today = today;
    }
    
    private final Koneksi koneksi = new Koneksi();
    
    public void dateActivity(String table, String column, String id, String idColumn){
        boolean adaKesalahan = false;
        Connection connection; 
        try{
            if ((connection = koneksi.getConnection()) != null){
                int jumlahSimpan=0;
                boolean simpan = false; 
                String SQLStatemen="";
                PreparedStatement preparedStatement;
                String pola = "yyyy/MM/dd H:mm:ss";
                Date date = new Date();
                this.setToday(tampilkanTanggalDanWaktu(date, pola));
                SQLStatemen = "update "+table+" set "+column+"=? where "+idColumn+"=?";
                preparedStatement = connection.prepareStatement(SQLStatemen);   
                preparedStatement.setString(1, getToday()); 
                preparedStatement.setString(2, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();            
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);

        }
       
    }
    
    public static String tampilkanTanggalDanWaktu(Date tanggalDanWaktu,
            String pola) {
        String tanggalStr = null;
        SimpleDateFormat formatter = null;
        formatter = new SimpleDateFormat(pola);
 
        tanggalStr = formatter.format(tanggalDanWaktu);
        return tanggalStr;
    }
}
