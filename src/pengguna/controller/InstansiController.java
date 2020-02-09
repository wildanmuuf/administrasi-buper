/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.controller;

import common.controller.ActivityController;
import common.controller.AlphaNumericCode;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import common.model.ButtonRender;
import common.model.Golongan;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pengguna.model.Instansi;
import pengguna.view.FormDetailInstansi;


/**
 *
 * @author 4R135
 */
public class InstansiController {
    private final Instansi instansi = new Instansi();
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
    
    public boolean simpan(String op, JTextField txtInstansi, JTextField txtPenanggungJawab, JTextField txtTelepon, JTextField txtEmail, JTextArea txtAreaAlamat, String kd_instansi, ArrayList<Golongan> listGolongan){
        if (listGolongan.isEmpty() && txtInstansi.getText().equals("") && txtPenanggungJawab.getText().equals("") && txtTelepon.getText().equals("") && txtEmail.getText().equals("") && txtAreaAlamat.getText().equals("")){
            valid = false;
            JOptionPane.showMessageDialog(null, "Isi format dengan benar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            instansi.setKd_instansi(kd_instansi);
            instansi.setInstansi(txtInstansi.getText());
            instansi.setPenanggung_jawab(txtPenanggungJawab.getText());
            instansi.setAlamat_instansi(txtAreaAlamat.getText());
            instansi.setNo_telp(txtTelepon.getText());
            instansi.setEmail(txtEmail.getText());
            instansi.setListGolongan(listGolongan);
            if(op.equalsIgnoreCase("tambah")){
                if(instansi.kode() == null){
                    kodeInstansi = "I000";
                }else{
                    kodeInstansi = instansi.kode();
                }
                //instansi.setKd_instansi(AlphaNumericCode.AutoIncrementKode(kodeInstansi));
                if (instansi.tambah()){
                    valid = true;
                } else {
                    if (instansi.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, instansi.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                instansi.setKd_instansi(instansi.getKd_instansi());
                if (instansi.update(instansi.getKd_instansi())){
                    valid = true;
                } else {
                    if (instansi.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, instansi.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        }
        return valid;
    }
    
   public void readData(JTable table){
        if(instansi.bacaData()){
            model = (DefaultTableModel)table.getModel();
            this.model.setRowCount(0);
            if((instansi.getList() != null) && (instansi.getList().size() > 0)){
                int i=0;
                for(Instansi ins : instansi.getList()){
                    String kd_instansi = ins.getKd_instansi();
                    String instansis = ins.getInstansi();
                    String penanggung_jawab = ins.getPenanggung_jawab();
                    String alamat = ins.getAlamat_instansi();
                    String no_telp = ins.getNo_telp();
                    String email = ins.getEmail();
                    Object[] obj = {"Detail",kd_instansi, instansis, penanggung_jawab, alamat, no_telp, email};
                    model.addRow(obj);
                    Action detail = new AbstractAction()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            JTable table = (JTable)e.getSource();
                            String kd = table.getValueAt(Integer.valueOf( e.getActionCommand() ), 1).toString();
                            FormDetailInstansi updateTransaksi = new FormDetailInstansi(null, true, table,table.getValueAt(Integer.valueOf( e.getActionCommand() ), 1).toString());
                            updateTransaksi.setVisible(true);
                        }
                    };
                     
                    ButtonRender buttonColumn = new ButtonRender(table, detail, 0);
                    buttonColumn.setMnemonic(KeyEvent.VK_ENTER);
                    
                }
                
            }
        }else {
                JOptionPane.showMessageDialog(null, instansi.getPesan());
        }
    }
    
    public void hapus(String kd_instansi){
        if(instansi.hapus(kd_instansi)){
            JOptionPane.showMessageDialog(null, "Pengguna berhasil dihapus!");
        }else{
            JOptionPane.showMessageDialog(null, instansi.getPesan());
        }
    }
    
     public void bacaSingle(String kd_instansi, 
            JTextField txtInstansi, 
            JTextField txtPenanggung_jawab, 
            JTextField txtTelepon, 
            JTextField txtEmail, 
            JTextArea txtAreaAlamat,
            JTable tableGolongan,
            JTextField txtJumlah_peserta,
            ArrayList<Golongan> listGolongan){
            DefaultTableModel model = (DefaultTableModel) tableGolongan.getModel();
            model.setRowCount(0);
            if(instansi.baca(kd_instansi)){
                txtInstansi.setText(instansi.getInstansi());
                txtPenanggung_jawab.setText(instansi.getPenanggung_jawab());
                txtAreaAlamat.setText(instansi.getAlamat_instansi());
                txtTelepon.setText(instansi.getNo_telp());
                txtEmail.setText(instansi.getEmail());
                listGolongan = instansi.getListGolongan();
                int totalPeserta = 0;
                int no = 0;
                for(Golongan golongan : listGolongan){
                    String nama_golongan = golongan.getNama();
                    int peserta = golongan.getJumlah_peserta();
                    int panitia = golongan.getJumlah_panitia();
                    int pembina = golongan.getJumlah_pembina();
                    no++;
                    Object[] obj = {no,nama_golongan, peserta, panitia, pembina};
                    model.addRow(obj);
                    totalPeserta += golongan.getJumlah_peserta();
                }
                txtJumlah_peserta.setText(String.valueOf(totalPeserta));

            }else{
                JOptionPane.showMessageDialog(null, instansi.getPesan());
            }
        
    }
    
    public void setKode(JTextField txtKodeFasilitas){
        String kode = instansi.kode();
        txtKodeFasilitas.setText(kode);
    }
}
