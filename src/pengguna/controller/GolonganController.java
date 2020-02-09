/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.controller;

import javax.swing.JOptionPane;
import common.model.Fasilitas;
import common.model.Golongan;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author WILDAN
 */
public class GolonganController {
    private final Golongan golongan = new Golongan();
    private boolean valid = false;
    private DefaultTableModel model;
    private String aktif;
    
    public DefaultTableModel getModel() {
        return model;
    }
    
    public boolean pilih(JTable table){
        if((boolean)table.getValueAt(table.getSelectedRow(), 0) == true){
            valid = false;
        }else{
            valid = true;
        }
        return valid;
    }
    
    public void readData(DefaultTableModel model){
        if(golongan.bacaData()){
            model.setRowCount(0);
            this.model = model;
            if((golongan.getList() != null) && (golongan.getList().size() > 0)){
                for(int i=0; i<golongan.getList().size(); i++){
                    String kd_golongan = golongan.getList().get(i).getKd_golongan();
                    String nama = golongan.getList().get(i).getNama();
                    String harga = golongan.getList().get(i).getHarga();
                    Object[] obj = {null,kd_golongan, nama, harga, 0, 0, 0};
                    model.addRow(obj);
                }
            }
        }else {
                JOptionPane.showMessageDialog(null, golongan.getPesan());
        }
    }
}
