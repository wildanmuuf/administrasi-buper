/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.controller;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import com.toedter.calendar.JYearChooser;
import common.controller.ActivityController;
import common.controller.AlphaNumericCode;
import common.model.ButtonRender;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pengguna.model.Instansi;
import pengguna.model.Pembayaran;
import pengguna.model.Transaksi;
import pengguna.view.FormDetailInstansi;

/**
 *
 * @author WILDAN
 */
public class PembayaranController {
     private final Pembayaran pembayaran = new Pembayaran();
     private final Transaksi transaksi = new Transaksi();
     private boolean valid = false;
     private DefaultTableModel model;
     public boolean simpan(String op, JTextField txtKodeBayar, JTextField txtKodeTrx, JTextField txtJumlahBayar, JTextField txtTotalBiaya, JTextField txtSisaTagihan, ButtonGroup statusGroup, String nip){
        boolean valids = txtJumlahBayar.getText()==null && statusGroup.getSelection().getActionCommand() == null;
        
        int jumlah_bayar = 0;
        int total_tagihan = 0;
        int sisa_tagihan = 0;
        if (valids){
            valid = false;
            JOptionPane.showMessageDialog(null, "Isi format dengan benar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            jumlah_bayar = Integer.parseInt(txtJumlahBayar.getText());
            sisa_tagihan = Integer.parseInt(txtSisaTagihan.getText());
            total_tagihan = Integer.parseInt(txtTotalBiaya.getText());
            if(jumlah_bayar <= sisa_tagihan){
                pembayaran.setKd_bayar(txtKodeBayar.getText());
                pembayaran.setNip(nip);
                pembayaran.setKd_transaksi(txtKodeTrx.getText());
                pembayaran.setJumlah_bayar(jumlah_bayar);
                pembayaran.setTipe_bayar(statusGroup.getSelection().getActionCommand());
                pembayaran.setTgl_bayar(ActivityController.tampilkanTanggalDanWaktu(new Date(), "yyyy/MM/dd H:mm:ss"));
                if(op.equalsIgnoreCase("tambah")){
                    if (pembayaran.tambah()){
                        valid = true;
                        int total_bayar = pembayaran.total(txtKodeTrx.getText());
                        if(total_bayar == total_tagihan){
                            transaksi.statusBayar(txtKodeTrx.getText(), "Lunas");
                        }else{
                            transaksi.statusBayar(txtKodeTrx.getText(), "Belum Lunas");
                        }
                    } else {
                        if (pembayaran.getPesan().length() > 0){
                            valid = false;
                            JOptionPane.showMessageDialog(null, pembayaran.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }else{
    //                if (user.update(txtNoAnggota.getText())){
    //                    valid = true;
    //                } else {
    //                    if (user.getPesan().length() > 0){
    //                        valid = false;
    //                        JOptionPane.showMessageDialog(null, user.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
    //                    }
    //                }
                }
            }else{
                JOptionPane.showMessageDialog(null, "Jumlah bayar tidak boleh lebih dari sisa tagihan", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        }
        return valid;
    }
     
     public void setKode(JTextField txtKodePembayaran){
        String kode = pembayaran.kode();
        txtKodePembayaran.setText(kode);
    }
     
    public void initTrx(String kd_transaksi, String status, JTextField txtKodeTrx, JTextField txtInstansi, JTextField txtPenanggungJawab, JTextField txtTotalTagihan, JTextField txtSisaTagihan, JTextField txtJumlahBayar, JButton btnCetakDanBayar, ButtonGroup statusGroup){
        if(pembayaran.bacaTrx(kd_transaksi)){
            if(pembayaran.getSisa_tagihan() == 0 || status.equals("Batal") || status.equals("Selesai")){
                txtJumlahBayar.setEditable(false);
                btnCetakDanBayar.setEnabled(false);
                Enumeration elements = statusGroup.getElements();
                while(elements.hasMoreElements()){
                    AbstractButton button = (AbstractButton) elements.nextElement();
                    button.setEnabled(false);
                }
            }else{
                int total = pembayaran.total(kd_transaksi);
                if(total != 0){
                    if(pembayaran.getSisa_tagihan() > 0 &&  total < pembayaran.getTotal_tagihan()){
                        txtJumlahBayar.setEditable(false);
                        txtJumlahBayar.setText(String.valueOf(pembayaran.getSisa_tagihan()));
                        btnCetakDanBayar.setEnabled(true);
                        Enumeration elements = statusGroup.getElements();
                        while(elements.hasMoreElements()){
                            AbstractButton button = (AbstractButton) elements.nextElement();
                            if(button.getActionCommand().equals("Lunas")){
                                button.setEnabled(true);
                                button.setSelected(true);
                            }else{
                               button.setEnabled(false);
                               button.setSelected(false);
                            }
                        }

                    }
                }
            }
            
            
            
            txtKodeTrx.setText(pembayaran.getKd_transaksi());
            txtInstansi.setText(pembayaran.getNama_instansi());
            txtPenanggungJawab.setText(pembayaran.getPenanggung_jawab());
            txtTotalTagihan.setText(String.valueOf(pembayaran.getTotal_tagihan()));
            txtSisaTagihan.setText(String.valueOf(pembayaran.getSisa_tagihan()));
        }else{
            JOptionPane.showMessageDialog(null, pembayaran.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void readData(JTable table, String kd_transaksi, JDialog dialog){
        if(pembayaran.bacaData(kd_transaksi)){
            model = (DefaultTableModel)table.getModel();
            this.model.setRowCount(0);
            if((pembayaran.getList() != null) && (pembayaran.getList().size() > 0)){
                int i=0;
                for(Pembayaran bayar : pembayaran.getList()){
                    String kd_bayar = bayar.getKd_bayar();
                    String tgl_bayar = bayar.getTgl_bayar();
                    String tipe_bayar = bayar.getTipe_bayar();
                    int jumlah_bayar = bayar.getJumlah_bayar();
                    String penerima = bayar.getPenerima();
                    Object[] obj = {"Cetak", kd_bayar, tipe_bayar, jumlah_bayar, tgl_bayar, penerima};
                    model.addRow(obj);
                    Action detail = new AbstractAction()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            JTable table = (JTable)e.getSource();
                            String kd = table.getValueAt(Integer.valueOf( e.getActionCommand() ), 1).toString();
                            cetakStruk(kd);
                            dialog.dispose();
                        }
                    };
                     
                    ButtonRender buttonColumn = new ButtonRender(table, detail, 0);
                    buttonColumn.setMnemonic(KeyEvent.VK_ENTER);
                    
                }
                
            }
        }else {
                JOptionPane.showMessageDialog(null, pembayaran.getPesan());
        }
    }
    
    public void cetakStruk(String kode_pembayaran){
        if(pembayaran.cetakStruk(kode_pembayaran)){}else{
            JOptionPane.showMessageDialog(null, pembayaran.getPesan());
        }
    }
    
    public void cetakLaporan(JMonthChooser bulanChooser, JYearChooser tahunChooser, JCheckBox checkRekapitulasi){
        int bulan, tahun;
        if(bulanChooser.isEnabled()){
            bulan = bulanChooser.getMonth()+1;
        }else{
            bulan = 0;
        }
        tahun = tahunChooser.getYear();
        if(pembayaran.cetakLaporan(bulan, tahun)){
            if(checkRekapitulasi.isSelected()){
               if(transaksi.cetakRekapitulasi(tahun));
           }
        }else{
            JOptionPane.showMessageDialog(null, pembayaran.getPesan());
        }
    }
}
