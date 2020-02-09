/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.controller;

import javax.swing.JOptionPane;
import common.model.Fasilitas;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pengguna.model.Transaksi;


/**
 *
 * @author WILDAN
 */
public class FasilitasController {
    private final Fasilitas fasilitas = new Fasilitas();
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
    
    public void readData(JTable table){
        if(fasilitas.listPilihFasilitas()){
            this.model = new DefaultTableModel(){
              @Override
              public boolean isCellEditable(int row, int column){
                  boolean wajib = false;
                  if(fasilitas.getList().size() > 0){
                    for(int i = 0; i<fasilitas.getList().size(); i++){
                        if(column == 0 && row==i){
                            if(!fasilitas.getList().get(i).getStatus().equals("Wajib")){
                                wajib = true;
                            }
                        }else if(column == 6 && row == i){
                            if(!fasilitas.getList().get(i).getStatus().equals("Wajib")){
                                wajib = true;
                            }
                        }
                    }
                  }
                  return wajib;
              }
              @Override
                public Class<?> getColumnClass(int column) {  
                    switch (column) {  
                        case 0: return Boolean.class;
                        case 6: return Integer.class;
                        default: return  String.class;
                    }  
                };    
            };
            model.addColumn("Pilih");
            model.addColumn("Kode Fasilitas");
            model.addColumn("Fasilitas");
            model.addColumn("Harga");
            model.addColumn("Status");
            model.addColumn("Stok");
            model.addColumn("Jumlah");
            if((fasilitas.getList() != null) && (fasilitas.getList().size() > 0)){
                for(int i=0; i<fasilitas.getList().size(); i++){
                    boolean pilih = false;
                    String kd_fasilitas = fasilitas.getList().get(i).getKd_fasilitas();
                    String nama = fasilitas.getList().get(i).getNama();
                    String harga = fasilitas.getList().get(i).getHarga();
                    String status = fasilitas.getList().get(i).getStatus();
                    int stok = fasilitas.getList().get(i).getStok();
                    int jumlah = 0;
                    if(status.equalsIgnoreCase("wajib")){
                        pilih = true;
                        jumlah = 1;
                    }else{
                        pilih = false;
                        jumlah = 1;
                    }
                    
                    Object[] obj = {pilih,kd_fasilitas, nama, harga, status,stok, jumlah};
                    model.addRow(obj);
                }
            }
            table.setModel(model);
        }else {
                JOptionPane.showMessageDialog(null, fasilitas.getPesan());
        }
    }
    
    
    
}
