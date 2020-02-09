/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import common.controller.AlphaNumericCode;
import admin.model.User;
import admin.view.FormDaftarPengguna;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import common.model.Enkripsi;
import javax.swing.JTable;


/**
 *
 * @author 4R135
 */
public class PenggunaController {
    private final User user = new User();
    private final Enkripsi enkripsi = new Enkripsi();
    private boolean hashed = false;
    private boolean valid = false;
    private String aktif;
    
    private DefaultTableModel model;
    //private FormDaftarPengguna formPengguna;
    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public void setHashed(boolean hashed) {
        this.hashed = hashed;
    }
    public DefaultTableModel getModel() {
        return model;
    }
    
    public String getAktif(){
        return aktif;
    }
    public boolean simpan(String op, JTextField txtNoAnggota, JTextField txtNama, JTextField txtUsername, JPasswordField txtPassword, ButtonGroup jkGroup,  JTextArea txtAreaAlamat, JTextFieldDateEditor dateLahir, JTextField txtEmail, JTextField txtNoTelp, JComboBox cmbHakAkses, String aktif){
        
        if (txtNoAnggota.getText().equalsIgnoreCase("") && txtNama.getText().equals("") && txtUsername.getText().equals("") && dateLahir.getText().equals("") && txtEmail.getText().equals("") && txtEmail.getText().matches(EMAIL_PATTERN) || cmbHakAkses.getSelectedItem().equals("")){
            valid = false;
            JOptionPane.showMessageDialog(null, "Isi format dengan benar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            user.setNip(txtNoAnggota.getText());
            user.setNama(txtNama.getText());
            user.setUsername(txtUsername.getText());
            
            if (txtPassword.getPassword().length == 0){
                user.setPassword(user.getPassword());
            } else {
                try {
                    user.setPassword(enkripsi.hashMD5(new String(txtPassword.getPassword())));
                } catch (Exception ex){
                    user.setPassword("");
                }
            }
            
            user.setJenis_kelamin(jkGroup.getSelection().getActionCommand());
            user.setAlamat(txtAreaAlamat.getText());
            user.setTgl_lahir(dateLahir.getText());
            user.setEmail(txtEmail.getText());
            user.setNo_telp(txtNoTelp.getText());
            user.setHak_akses(cmbHakAkses.getSelectedItem().toString());
            user.setAktif(aktif);
            if(op.equalsIgnoreCase("tambah")){
                if (user.tambah()){
                    valid = true;
                } else {
                    if (user.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, user.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                if (user.update(txtNoAnggota.getText())){
                    valid = true;
                } else {
                    if (user.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, user.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        }
        return valid;
    }
    
   public void readData(JTable table){
        if(user.bacaData()){
            this.model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            if((user.getList() != null) && (user.getList().size() > 0)){
                for(int i=0; i<user.getList().size(); i++){
                    
                    String noAnggota = user.getList().get(i).getNip();
                    String nama = user.getList().get(i).getNama();
                    String username = user.getList().get(i).getUsername();
                    String jenis_kelamin = user.getList().get(i).getJenis_kelamin();
                    String tgl_lahir = user.getList().get(i).getTgl_lahir();
                    String alamat = user.getList().get(i).getAlamat();
                    String email = user.getList().get(i).getEmail();
                    String telepon = user.getList().get(i).getNo_telp();
                    String hakAkses = user.getList().get(i).getHak_akses();
                    String lastLogin = user.getList().get(i).getLast_login();
                    String aktif = user.getList().get(i).getAktif();
                    Object[] obj = {noAnggota, nama, username, jenis_kelamin, tgl_lahir, alamat, email, telepon, hakAkses, lastLogin, aktif};
                    model.addRow(obj);
                }
            }
        }else {
                JOptionPane.showMessageDialog(null, user.getPesan());
        }
    }
    
    public void hapus(String nip){
        if(user.hapus(nip)){
            JOptionPane.showMessageDialog(null, "Pengguna berhasil dihapus!");
        }else{
            JOptionPane.showMessageDialog(null, user.getPesan());
        }
    }
    
    public void aktif(String nip, String aktif){
        if(user.aktif(nip, aktif)){
            if(aktif.equals("Y")){
                JOptionPane.showMessageDialog(null, "Pengguna berhasil diaktifkan!");
            }else{
                JOptionPane.showMessageDialog(null, "Pengguna berhasil dinonaktifkan!");
            }
        }else{
            JOptionPane.showMessageDialog(null, user.getPesan());
        }
    }
    
    public void bacaSingle(String nip, JTextField txtNoAnggota, JTextField txtNama, JTextField txtUsername, ButtonGroup jkGroup,  JTextArea txtAreaAlamat, JDateChooser dateLahir, JTextField txtEmail, JTextField txtNoTelp, JComboBox cmbHakAkses){
        if(user.baca("detail",nip)){
            try {
                txtNoAnggota.setText(user.getNip());
                txtNama.setText(user.getNama());
                txtUsername.setText(user.getUsername());
                Enumeration elements = jkGroup.getElements();
                while(elements.hasMoreElements()){
                    AbstractButton button = (AbstractButton) elements.nextElement();
                    if(button.getActionCommand().equals(user.getJenis_kelamin())){
                        button.setSelected(true);
                    }
                }
                txtAreaAlamat.setText(user.getAlamat());
                Date date;
                date = new SimpleDateFormat("yyyy/MM/dd").parse(user.getTgl_lahir());
                dateLahir.setDate(date);
                txtEmail.setText(user.getEmail());
                txtNoTelp.setText(user.getNo_telp());
                cmbHakAkses.setSelectedItem(user.getHak_akses());
                aktif = user.getAktif();
                
            } catch (ParseException ex) {}
        }
    }
    public void setKode(JTextField txtKodeFasilitas){
        String kode = user.kode();
        txtKodeFasilitas.setText(kode);
    }
}
