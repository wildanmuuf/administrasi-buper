/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import common.controller.AlphaNumericCode;
import common.controller.ActivityController;
import common.model.Fasilitas;
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
public class FasilitasController {
    private final Fasilitas fasilitas = new Fasilitas();
    private boolean valid = false;
    private DefaultTableModel model;
    private final ActivityController activityController = new ActivityController();
    private String aktif;
    
    public DefaultTableModel getModel() {
        return model;
    }
    
     public String getAktif(){
        return aktif;
    }
    
    public boolean simpan(String op, String kd_fasilitas, String nama, String harga, String status, int stok, String aktif){
        if (kd_fasilitas.equalsIgnoreCase("") || nama.equals("") || harga.equals("") || status.equals("")){
            valid = false;
            JOptionPane.showMessageDialog(null, "Isi format dengan benar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            fasilitas.setKd_fasilitas(kd_fasilitas);
            fasilitas.setNama(nama);
            fasilitas.setHarga(harga);
            fasilitas.setStatus(status);
            fasilitas.setAktif(aktif);
            fasilitas.setStok(stok);
            if(op.equalsIgnoreCase("tambah")){
                if (fasilitas.tambah()){
                    valid = true;
                    activityController.dateActivity("fasilitas", "perubahan_terakhir", fasilitas.getKd_fasilitas(), "kode_fasilitas");
                } else {
                    if (fasilitas.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, fasilitas.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                if (fasilitas.update(kd_fasilitas)){
                    valid = true;
                    activityController.dateActivity("fasilitas", "perubahan_terakhir", fasilitas.getKd_fasilitas(), "kode_fasilitas");
                } else {
                    if (fasilitas.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, fasilitas.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        return valid;
    }
    
   public void readData(JTable table){
        if(fasilitas.bacaData()){
            this.model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            if((fasilitas.getList() != null) && (fasilitas.getList().size() > 0)){
                for(int i=0; i<fasilitas.getList().size(); i++){
                    
                    String kd_fasilitas = fasilitas.getList().get(i).getKd_fasilitas();
                    String nama = fasilitas.getList().get(i).getNama();
                    String harga = fasilitas.getList().get(i).getHarga();
                    String status = fasilitas.getList().get(i).getStatus();
                    String aktif = fasilitas.getList().get(i).getAktif();
                    int stok = fasilitas.getList().get(i).getStok();
                    String perubahan_terakhir = fasilitas.getList().get(i).getPerubahan_terakhir();
                    Object[] obj = {kd_fasilitas, nama, harga, status,stok, aktif, perubahan_terakhir};
                    model.addRow(obj);
                }
            }
        }else {
                JOptionPane.showMessageDialog(null, fasilitas.getPesan());
        }
    }
    
    public void hapus(String kd_fasilitas){
        if(fasilitas.hapus(kd_fasilitas)){
            JOptionPane.showMessageDialog(null, "Fasilitas berhasil dihapus!");
        }else{
            JOptionPane.showMessageDialog(null, fasilitas.getPesan());
        }
    }
    
    public void aktif(String kd_fasilitas, String aktif){
        if(fasilitas.aktif(kd_fasilitas, aktif)){
            if(aktif.equals("Y")){
                JOptionPane.showMessageDialog(null, "Fasilitas berhasil diaktifkan!");
            }else{
                JOptionPane.showMessageDialog(null, "Fasilitas berhasil dinonaktifkan!");
            }
        }else{
            JOptionPane.showMessageDialog(null, fasilitas.getPesan());
        }
    }
    
    public void bacaSingle(String kd_fasilitas, JTextField txtKodeFasilitas, JTextField txtNama, JTextField txtHarga, ButtonGroup statusGroup, JTextField txtStok){
        if(fasilitas.baca(kd_fasilitas,"baca_single")){
            txtKodeFasilitas.setText(fasilitas.getKd_fasilitas());
            txtNama.setText(fasilitas.getNama());
            txtHarga.setText(fasilitas.getHarga());
            Enumeration elements = statusGroup.getElements();
            while(elements.hasMoreElements()){
                AbstractButton button = (AbstractButton) elements.nextElement();
                if(button.getActionCommand().equals(fasilitas.getStatus())){
                    button.setSelected(true);
                }
            }
            aktif = fasilitas.getAktif();
            txtStok.setText(String.valueOf(fasilitas.getStok()));
        }
    }
    
    public void setKode(JTextField txtKodeFasilitas){
        String kode = fasilitas.kode();
        txtKodeFasilitas.setText(kode);
    }
}
