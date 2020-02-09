/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.controller;

import common.controller.AlphaNumericCode;
import com.toedter.calendar.JDateChooser;
import common.model.ButtonRender;
import common.model.Golongan;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;
import pengguna.model.Foto;
import pengguna.view.FormDetailInstansi;


/**
 *
 * @author 4R135
 */
public class FotoController {
    private Foto foto = new Foto();
    private boolean valid = false;
    private String kodeInstansi;
    
    private DefaultTableModel model;
    //private FormDaftarPengguna formPengguna;
    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public DefaultTableModel getModel() {
        return model;
    }
    
    public String getKodeInstansi(){
        return kodeInstansi;
    }
    
    public void setMinDate(JDateChooser dateChooser, Date date){
        dateChooser.setMinSelectableDate(date);
    }
    
    public boolean simpan(String op, JLabel lblNamaFile, JTextField txtJudul, File file, String kd_instansi){
        if (lblNamaFile.getText().equals("") && txtJudul.getText().equals("")){
            valid = false;
            JOptionPane.showMessageDialog(null, "Isi format dengan benar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            foto.setKd_instansi(kd_instansi);
            foto.setJudul_foto(txtJudul.getText());
            foto.setNama_file(lblNamaFile.getText());
            if(op.equalsIgnoreCase("tambah")){
                if (foto.tambah()){
                    try {
                        String path=new File(".").getCanonicalPath();
                        if(!new File(path).exists()){
                            File createImage = new File(path+"/image/"+kd_instansi);
                            createImage.mkdir();
                        }
                        FileUtils.copyFileToDirectory(file, new File(path+"/image/"+kd_instansi));
                    } catch (IOException ex) {
                        Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    valid = true;
                } else {
                    if (foto.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, foto.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                foto.setKd_instansi(foto.getKd_foto());
                if (foto.update(foto.getKd_instansi())){
                    valid = true;
                } else {
                    if (foto.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, foto.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        }
        return valid;
    }
    
   public void readData(JTable table, String kd_instansi){
       foto = new Foto();
        if(foto.baca(kd_instansi)){
            model = (DefaultTableModel)table.getModel();
            this.model.setRowCount(0);
            if((foto.getList() != null) && (foto.getList().size() > 0)){
                for(Foto ft : foto.getList()){
                    String judul_foto = ft.getJudul_foto();
                    String nama_file = ft.getNama_file();
                    String kd_foto = ft.getKd_foto();
                    Object[] obj = {kd_foto,judul_foto, nama_file};
                    model.addRow(obj);
                }
                
            }
        }else {
                JOptionPane.showMessageDialog(null, foto.getPesan());
        }
    }
    
    public void hapus(String kd_foto, String nama_file, String kd_instansi){
        if(foto.hapus(kd_foto)){
            try {
                String path=new File(".").getCanonicalPath();
                File deleteFoto = FileUtils.getFile(path+"/image/"+kd_instansi+"/"+nama_file);
                FileUtils.touch(deleteFoto);
                boolean delete = FileUtils.deleteQuietly(deleteFoto);
                if(delete){
                    JOptionPane.showMessageDialog(null, "Foto berhasil dihapus!");
                }else{
                    JOptionPane.showMessageDialog(null, "Foto gagal dihapus!");
                }
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Foto gagal dihapus\n\n"+ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, foto.getPesan());
        }
    }
}
