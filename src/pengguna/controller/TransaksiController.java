/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.controller;

import com.toedter.calendar.JCalendar;
import common.controller.ActivityController;
import common.controller.AlphaNumericCode;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import com.toedter.calendar.JYearChooser;
import common.model.ButtonRender;
import common.model.Fasilitas;
import common.model.Golongan;
import common.model.PesanDialog;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import pengguna.model.Instansi;
import pengguna.model.RangeEvaluator;
import pengguna.model.Transaksi;
import pengguna.view.FormDetailTransaksi;


/**
 *
 * @author 4R135
 */
public class TransaksiController {
    private final Transaksi transaksi = new Transaksi();
    private final Instansi instansis = new Instansi();
    private boolean valid = false;
    private String aktif;
    private final PesanDialog pesanDialog = new PesanDialog();
    private final Fasilitas fasilitas = new Fasilitas();
    private DefaultTableModel model;
    NumberFormat format = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    private static final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public DefaultTableModel getModel() {
        return model;
    }

    public String getAktif(){
        return aktif;
    }

    public void setDateChooser(JDateChooser chooser){
        try {
            if(transaksi.bacaData()){
                for(Transaksi trx : transaksi.getList()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    RangeEvaluator evaluator = new RangeEvaluator();
                    evaluator.setStartDate(dateFormat.parse(trx.getTgl_mulai()));
                    evaluator.setEndDate(dateFormat.parse(trx.getTgl_akhir()));
                    JCalendar calendar = chooser.getJCalendar();
                    calendar.getDayChooser().addDateEvaluator(evaluator);
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean simpan(String op, JTextField txtKodeTransaksi, JTextFieldDateEditor dateAwal, JTextFieldDateEditor dateAkhir, JTextField txtTotal, JTextField txtTotalPeserta, String status, String kd_instansi, ArrayList<Fasilitas> listFasilitas, String nip){

        if ((((((!listFasilitas.isEmpty() || txtKodeTransaksi.getText() != null) || dateAwal.getText() != null) || dateAkhir.getText() != null) || txtTotal.getText() != null) || txtTotalPeserta.getText() != null) ||
                kd_instansi!=null){
            transaksi.setKd_transaksi(txtKodeTransaksi.getText());
            transaksi.setTgl_transaksi(ActivityController.tampilkanTanggalDanWaktu(new Date(), "yyyy/MM/dd H:mm:ss"));
            transaksi.setTgl_mulai(dateAwal.getText());
            transaksi.setTgl_akhir(dateAkhir.getText());
            transaksi.setTotal_tagihan(txtTotal.getText());
            transaksi.setTotal_peserta(Integer.valueOf(txtTotalPeserta.getText()));
            transaksi.setKd_instansi(kd_instansi);
            transaksi.setNip(nip);
            transaksi.setListFasilitas(listFasilitas);
            transaksi.setStatus_bayar("Belum Lunas");
            if(op.equalsIgnoreCase("tambah")){
                transaksi.setStatus("Proses");
                if (transaksi.tambah()){
                    valid = true;
                } else {
                    if (transaksi.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, transaksi.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                if (transaksi.update(txtKodeTransaksi.getText())){
                    valid = true;
                } else {
                    if (transaksi.getPesan().length() > 0){
                        valid = false;
                        JOptionPane.showMessageDialog(null, transaksi.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } else {
            valid = false;
            JOptionPane.showMessageDialog(null, "Isi format dengan benar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        return valid;
    }

   public void readData(JTable table){
        rubahStatus();

        if(transaksi.bacaData()){
            model = (DefaultTableModel)table.getModel();
            this.model.setRowCount(0);
            if((transaksi.getList() != null) && (transaksi.getList().size() > 0)){
                int i=0;
                for(Transaksi trx : transaksi.getList()){
                    updateStok(trx.getKd_transaksi());
                    String kd_transaksi = trx.getKd_transaksi();
                    String instansi = trx.getInstansi();
                    String penanggung_jawab = trx.getPenanggung_jawab();
                    String status = trx.getStatus();
                    String tgl_transaksi = trx.getTgl_transaksi();
                    String tgl_mulai = trx.getTgl_mulai();
                    String tgl_akhir = trx.getTgl_akhir();
                    int total_peserta = trx.getTotal_peserta();
                    String total_tagihan = trx.getTotal_tagihan();
                    String kd_instansi = trx.getKd_instansi();
                    String nama_user = trx.getNamaUser();
                    String status_bayar = trx.getStatus_bayar();
                    Object[] obj = {"Detail",kd_transaksi, kd_instansi, instansi, penanggung_jawab, status, tgl_transaksi, tgl_mulai, tgl_akhir, total_peserta, total_tagihan, nama_user,status_bayar};
                    model.addRow(obj);
                    Action detail = new AbstractAction()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            JTable table = (JTable)e.getSource();
                            String kd = table.getValueAt(Integer.valueOf( e.getActionCommand() ), 1).toString();
                            FormDetailTransaksi updateTransaksi = new FormDetailTransaksi(null, true, table,table.getValueAt(Integer.valueOf( e.getActionCommand() ), 1).toString(),table.getModel().getValueAt(Integer.valueOf( e.getActionCommand() ), 2).toString(), table.getModel().getValueAt(Integer.valueOf( e.getActionCommand() ), 5).toString());
                            updateTransaksi.setVisible(true);
                        }
                    };

                    ButtonRender buttonColumn = new ButtonRender(table, detail, 0);
                    buttonColumn.setMnemonic(KeyEvent.VK_ENTER);

                }

            }
        }else {
                JOptionPane.showMessageDialog(null, transaksi.getPesan());
        }
    }

    public void batal(String kd_transaksi){
        if(transaksi.baca(kd_transaksi)){
            if(pesanDialog.tampilkanPilihan("Apakah anda yakin ingin dibatalkan?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                if(transaksi.getStatus().equals("Proses")){
                    transaksi.status(kd_transaksi, "Batal");
                }else{
                    JOptionPane.showMessageDialog(null, "Penyewaan tidak dapat dibatalkan");
                }
            }
        }
    }



    public void rubahStatus(){
        if(transaksi.bacaData()){
            transaksi.getList().forEach((trx) -> {
                    if(!"Batal".equals(trx.getStatus()) && !"Selesai".equals(trx.getStatus())){
                    try {

                        String status = "";
                        Date today = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        Date startDate = sdf.parse(trx.getTgl_mulai());
                        Date endDate = sdf.parse(trx.getTgl_akhir());
                        long diffStart = today.getTime()-startDate.getTime();
                        int daysStart = (int)(diffStart / (1000*60*60*24));
                        long diffEnd = today.getTime()-endDate.getTime();
                        int daysEnd = (int)(diffEnd / (1000*60*60*24));
                        if(today.compareTo(startDate) >= 0){
                            status = "Sedang disewa";

                        }else if(today.compareTo(startDate) < 0){
                            status = "Proses";
                        }

                        if(today.compareTo(endDate) >= 0 && trx.getStatus_bayar().equals("Lunas")){
                            status = "Selesai";
                        }
                        
                        if(daysEnd >= 1 && trx.getStatus_bayar().equals("Belum Lunas")){
                            status = "Belum Selesai";
                        }
//
                        transaksi.status(trx.getKd_transaksi(), status);
                        updateStok(trx.getKd_transaksi());
                        } catch (ParseException ex) {
                    Logger.getLogger(TransaksiController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }


            });

        }
    }

    public void bacaSingle(String nip,
            JTextField txtKodeTransaksi,
            JTextField txtInstansi,
            JTextField txtPenanggung_jawab,
            JTextField txtTelepon,
            JTextField txtEmail,
            JTextArea txtAreaAlamat,
            JTable tableFasilitas,
            JTextField txtTotalFasilitas,
            JTable tableGolongan,
            JTextField txtTotalGolongan,
            JDateChooser dateAwal,
            JDateChooser dateAkhir,
            JTextField txtJumlah_peserta,
            JTextField txtTotal,
            ArrayList<Fasilitas> listFasiltas,
            ArrayList<Golongan> listGolongan){
        if(transaksi.baca(nip)){
             try {
                DefaultTableModel tableGolModel = (DefaultTableModel)tableGolongan.getModel();
                tableGolModel.setRowCount(0);
                DefaultTableModel tableFasModel = (DefaultTableModel)tableFasilitas.getModel();
                tableFasModel.setRowCount(0);
                if(instansis.baca(transaksi.getKd_instansi())){
                    txtKodeTransaksi.setText(transaksi.getKd_transaksi());
                    txtInstansi.setText(instansis.getInstansi());
                    txtPenanggung_jawab.setText(instansis.getPenanggung_jawab());
                    txtAreaAlamat.setText(instansis.getAlamat_instansi());
                    txtTelepon.setText(instansis.getNo_telp());
                    Date date = new SimpleDateFormat("yyyy/MM/dd").parse(transaksi.getTgl_mulai());;
                    dateAwal.setDate(date);
                    Date date2 = new SimpleDateFormat("yyyy/MM/dd").parse(transaksi.getTgl_akhir());
                    dateAkhir.setDate(date2);

                    long diff = date2.getTime() - date.getTime();
                    int days = (int)(diff / (1000*60*60*24))+1;
                    
                    txtEmail.setText(instansis.getEmail());
                    txtJumlah_peserta.setText(String.valueOf(transaksi.getTotal_peserta()));
                    String total_tagihan = format.format(Integer.valueOf(transaksi.getTotal_tagihan()));
                    txtTotal.setText("Rp. "+total_tagihan);

                    listFasiltas = transaksi.getListFasilitas();
                    int no = 0;
                    int total_tagihan_fasilitas = 0;
                    
                    for(Fasilitas fasilitas : listFasiltas){
                        no++;
                        int totalHarga = Integer.parseInt(fasilitas.getHarga())*fasilitas.getJumlah_fasilitas()*days;
                        total_tagihan_fasilitas += Integer.parseInt(fasilitas.getHarga())*fasilitas.getJumlah_fasilitas();
                        String total = format.format(totalHarga);
                        Object[] obj = {no,fasilitas.getNama(),"Rp. "+format.format(Integer.parseInt(fasilitas.getHarga())),fasilitas.getStatus(),fasilitas.getJumlah_fasilitas(),"Rp. "+total};
                        tableFasModel.addRow(obj);
                    }
                    txtTotalFasilitas.setText(String.valueOf("Rp. "+format.format(total_tagihan_fasilitas)));

                    listGolongan = instansis.getListGolongan();
                    int total_tagihan_golongan = 0;
                    int jumlah_peserta = 0;
                    no = 0;
                    for(Golongan golongan : listGolongan){
                        no++;
                        jumlah_peserta += golongan.getJumlah_peserta();
                        int harga = Integer.parseInt(golongan.getHarga())*golongan.getJumlah_peserta()*days;
                        total_tagihan_golongan += harga;
                        String total = format.format(harga);
                        
                        Object[] obj = {no,golongan.getNama(), "Rp. "+format.format(Integer.parseInt(golongan.getHarga())), golongan.getJumlah_peserta(), golongan.getJumlah_panitia(), golongan.getJumlah_pembina(),"Rp. "+total};
                        tableGolModel.addRow(obj);
                        txtJumlah_peserta.setText(String.valueOf(jumlah_peserta));
                    }
                    txtTotalGolongan.setText(String.valueOf("Rp. "+format.format(total_tagihan_golongan)));

                }else{
                    JOptionPane.showMessageDialog(null, instansis.getPesan());
                }
                 } catch (ParseException ex) {
                     JOptionPane.showMessageDialog(null, ex.getMessage());
                 }
        }else{
            JOptionPane.showMessageDialog(null, instansis.getPesan());
        }
    }
    public void setKode(JTextField txtKodeFasilitas){
        String kode = transaksi.kode();
        txtKodeFasilitas.setText(kode);
    }

    public void updateStok(String kode_transaksi){
        if(transaksi.baca(kode_transaksi)){
            String status = transaksi.getStatus();
                transaksi.getListFasilitas().forEach((fas) -> {
                    if(fasilitas.baca(fas.getKd_fasilitas(), "stoking")){
                        int stok = fas.getStok();
                        int jml = fas.getJumlah_fasilitas();
                        int stokNow = 0;
                        if(status.equalsIgnoreCase("Sedang disewa")){
                            if(stok>0){
                                stokNow = stok - jml;
                                fasilitas.updateStok(fasilitas.getKd_fasilitas(), stokNow);
                            }
                        }else if(status.equalsIgnoreCase("Batal") || status.equalsIgnoreCase("Selesai")){

                            fasilitas.updateStok(fasilitas.getKd_fasilitas(), stok);
                        }
                    }
                });
        }
    }

    public void cetakLaporan(JMonthChooser bulanChooser, JYearChooser tahunChooser, JCheckBox checkRekapitulasi){
        int bulan, tahun;
        boolean rekapitulasi = false;
        if(bulanChooser.isEnabled()){
            bulan = bulanChooser.getMonth()+1;
        }else{
            bulan = 0;
        }
        tahun = tahunChooser.getYear();

        if(transaksi.cetakLaporan(bulan, tahun)){
           if(checkRekapitulasi.isSelected()){
               if(transaksi.cetakRekapitulasi(tahun));
           }
        }else{
            JOptionPane.showMessageDialog(null, transaksi.getPesan());
        }
    }
}
