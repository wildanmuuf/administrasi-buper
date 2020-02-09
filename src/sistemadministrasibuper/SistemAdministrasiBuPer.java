/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemadministrasibuper;

import common.view.FormLogin;
import admin.view.FormUtamaAdmin;

/**
 *
 * @author WILDAN
 */
public class SistemAdministrasiBuPer {
    private final static FormLogin formLogin = new FormLogin();
    private final static FormUtamaAdmin formAdmin = new FormUtamaAdmin();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        formLogin.setVisible(true);
    }
    
}
