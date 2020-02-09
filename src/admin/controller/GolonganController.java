/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import common.controller.AlphaNumericCode;
import common.controller.ActivityController;
import common.model.Golongan;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author WILDAN
 */
public class GolonganController {
    private final Golongan golongan = new Golongan();
    private boolean valid = false;
    private DefaultTableModel model;
    private final ActivityController activityController = new ActivityController();
    public DefaultTableModel getModel() {
        return model;
    }
    
    public boolean simpan(String op, JTextField txtKodeGolongan, JTextField txtNama, JTextField txtHarga){
        if (txtKodeGolongan.getText().equalsIgnoreCase("") || txtNama.getText().equals("") || txtHarga.getText().equals("")){
            valid = false;
            JOptionPane.showMessageDialog(null, "Isi format dengan benar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            golongan.setKd_golongan(txtKodeGolongan.getText());
            golongan.setNama(txtNama.getText());
            golongan.setHarga(txtHarga.getText());
           
            if(op.equalsIgnoreCase("tambah")){
                if (golongan.tambah()){
                    activityController.dateActivity("golongan", "perubahan_terakhir", golongan.getKd_golongan(), "kode_golongan");
                    valid = true;
                } else {
                    if (golongan.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, golongan.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                if (golongan.update(txtKodeGolongan.getText())){
                    activityController.dateActivity("golongan", "perubahan_terakhir", golongan.getKd_golongan(), "kode_golongan");
                    valid = true;
                } else {
                    if (golongan.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, golongan.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        return valid;
    }
    
   public void readData(JTable table){
        if(golongan.bacaData()){
            this.model = (DefaultTableModel)table.getModel();
            if((golongan.getList() != null) && (golongan.getList().size() > 0)){
                for(int i=0; i<golongan.getList().size(); i++){
                    
                    String kd_golongan = golongan.getList().get(i).getKd_golongan();
                    String nama = golongan.getList().get(i).getNama();
                    String harga = golongan.getList().get(i).getHarga();
                    String perubahan_terakhir = golongan.getList().get(i).getPerubahan_terakhir();
                    Object[] obj = {kd_golongan, nama, harga, perubahan_terakhir};
                    model.addRow(obj);
                }
            }
        }else {
                JOptionPane.showMessageDialog(null, golongan.getPesan());
        }
    }
    
    public void hapus(String kd_golongan){
        if(golongan.hapus(kd_golongan)){
            JOptionPane.showMessageDialog(null, "Golongan berhasil dihapus!");
        }else{
            JOptionPane.showMessageDialog(null, golongan.getPesan());
        }
    }
    
    public void bacaSingle(String kd_golongan, JTextField txtKodeFasilitas, JTextField txtNama, JTextField txtHarga){
        if(golongan.baca(kd_golongan)){
            txtKodeFasilitas.setText(golongan.getKd_golongan());
            txtNama.setText(golongan.getNama());
            txtHarga.setText(golongan.getHarga());
        }
    }
    
    public void setKode(JTextField txtKodeGolongan){
        String kode = golongan.kode();
        txtKodeGolongan.setText(kode);
    }
}
