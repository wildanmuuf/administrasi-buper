/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common.controller;

import javax.swing.JOptionPane;
import common.model.Enkripsi;
import admin.model.User;
import common.model.UserLogin;


/**
 *
 * @author WILDAN
 */
public class LoginController {
 
    private final User user = new User();
    private final Enkripsi enkripsi = new Enkripsi();
    private String userLogin = "";
    private String nip="";
    private final ActivityController activityController = new ActivityController();
    
    public String getUserLogin() {
        return userLogin;
    }
    
    public boolean validasi(javax.swing.JTextField userIdTextField, javax.swing.JPasswordField passwordField){
        boolean valid = false, userIdSalah=false;
        String hashedInputPassword = "";
        
        if (!userIdTextField.getText().equals("")){
            if (!valid){
                if (user.baca("login",userIdTextField.getText())){
                    try {
                        hashedInputPassword = enkripsi.hashMD5(new String(passwordField.getPassword()));
                    } catch (Exception ex){}
                    
                    if (user.getPassword().equalsIgnoreCase(hashedInputPassword)){
                        valid = true;
                        UserLogin.nip = user.getNip();
                        activityController.dateActivity("tb_user", "last_login", user.getNip(), "nip");
                        
                        if(user.getHak_akses().equalsIgnoreCase("Pengguna")){
                            userLogin = "Pengguna";
                        }else{
                            userLogin = "Administrator";
                        }
                    } else {
                        userIdSalah = true;
                    }
                } else {
                    if (user.getPesan().substring(0, 3).equalsIgnoreCase("NIM")){
                        userIdSalah = true;
                    }
                }
                
                if (!valid){
                    if (userIdSalah){
                        JOptionPane.showMessageDialog(null, "User Id atau password salah", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, user.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "User Id (NIM) tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        
        return valid;
    }
}
