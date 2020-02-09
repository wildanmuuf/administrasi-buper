/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.view;

import common.controller.ActivityController;
import common.controller.TextFieldOptions;
import com.toedter.calendar.JTextFieldDateEditor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import pengguna.controller.CalculateController;
import pengguna.controller.TransaksiController;
import common.model.Fasilitas;
import common.model.Golongan;
import common.model.PesanDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.*;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pengguna.controller.InstansiController;

/**
 *
 * @author WILDAN
 */
public class FormDetailInstansi extends javax.swing.JDialog {
    private final InstansiController instansiController = new InstansiController();
    private UploadFoto uploadFoto;
    JTextFieldDateEditor editor1, editor2;
    JTable tableInstansi;
    ArrayList<Golongan> listGolongan = new ArrayList<>();
    long diff;
    int days = 0;
    Date awal = null;
    Date akhir = null;
    String kd_instansi;
    CalculateController calculateController = new CalculateController();
    PesanDialog pesanDialog = new PesanDialog();
    File file;
    JFileChooser jfc;
    /**
     * Creates new form FormTambahPengguna
     */
    public FormDetailInstansi(java.awt.Frame parent, boolean modal, 
            JTable tableInstansi, String kd_instansi) {
        super(parent, modal);
        this.tableInstansi = tableInstansi;
        this.kd_instansi = kd_instansi;
        initComponents();
        instansiController.bacaSingle(kd_instansi, txtInstansi, txtPenanggungJawab, txtEmail, txtTeleponInstansi, txtAreaAlamatInstansi, tableGolongan, txtJumlahPeserta, this.listGolongan);
        
        setLocationRelativeTo(null);
        TextFieldOptions.phoneNumber(txtTeleponInstansi);
    }
    
    private void clear(){
        txtInstansi.setText("");
        txtAreaAlamatInstansi.setText("");
        txtTeleponInstansi.setText("");
        txtEmail.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jkGroupRadio = new javax.swing.ButtonGroup();
        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel2 = new javax.swing.JLabel();
        txtInstansi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtJumlahPeserta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtTeleponInstansi = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaAlamatInstansi = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPenanggungJawab = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        uploadGambar = new javax.swing.JButton();
        btnUpddate = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableGolongan = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Tambah Pengguna");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Instansi");

        txtInstansi.setEditable(false);
        txtInstansi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Penanggung Jawab");

        txtJumlahPeserta.setEditable(false);
        txtJumlahPeserta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("No Telpon Instansi");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        txtEmail.setEditable(false);
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTeleponInstansi.setEditable(false);
        txtTeleponInstansi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Alamat Instansi");

        txtAreaAlamatInstansi.setEditable(false);
        txtAreaAlamatInstansi.setColumns(20);
        txtAreaAlamatInstansi.setRows(5);
        jScrollPane1.setViewportView(txtAreaAlamatInstansi);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Detail Instansi");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Jumlah Peserta");

        txtPenanggungJawab.setEditable(false);
        txtPenanggungJawab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Orang");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Golongan");

        uploadGambar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        uploadGambar.setText("Kelola Gambar");
        uploadGambar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadGambarActionPerformed(evt);
            }
        });

        btnUpddate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpddate.setText("Update");
        btnUpddate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpddateActionPerformed(evt);
            }
        });

        tableGolongan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Golongan", "Peserta", "Panitia", "Pembina"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableGolongan);
        if (tableGolongan.getColumnModel().getColumnCount() > 0) {
            tableGolongan.getColumnModel().getColumn(0).setPreferredWidth(5);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(252, 252, 252)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPenanggungJawab, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTeleponInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(175, 175, 175))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtJumlahPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpddate, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(uploadGambar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(txtPenanggungJawab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(txtTeleponInstansi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel3)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel4)
                                .addGap(14, 14, 14)
                                .addComponent(jLabel7)))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtJumlahPeserta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13))
                            .addComponent(jLabel10))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uploadGambar)
                            .addComponent(btnUpddate))
                        .addGap(32, 32, 32))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instansiController.readData(tableInstansi);
    }//GEN-LAST:event_formWindowClosed

    private void uploadGambarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadGambarActionPerformed
        // TODO add your handling code here:
        uploadFoto = new UploadFoto(null, true, kd_instansi);
        uploadFoto.setVisible(true);
    }//GEN-LAST:event_uploadGambarActionPerformed

    private void btnUpddateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpddateActionPerformed
        // TODO add your handling code here:
        if(btnUpddate.getText().equalsIgnoreCase("update")){
            btnUpddate.setText("Simpan");
            txtInstansi.setEditable(true);
            txtPenanggungJawab.setEditable(true);
            txtTeleponInstansi.setEditable(true);
            txtEmail.setEditable(true);
            txtAreaAlamatInstansi.setEditable(true);
        }else{
            if(pesanDialog.tampilkanPilihan("Apakah detail instansi sudah benar?", "Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                if(instansiController.simpan("update", txtInstansi, txtPenanggungJawab, txtTeleponInstansi, txtEmail, txtAreaAlamatInstansi, kd_instansi, listGolongan)){
                        JOptionPane.showMessageDialog(null, "Perbaharui Instansi Berhasil!");
                        dispose();
                        clear();
                }
            }
        }
    }//GEN-LAST:event_btnUpddateActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpddate;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.ButtonGroup jkGroupRadio;
    private javax.swing.JTable tableGolongan;
    private javax.swing.JTextArea txtAreaAlamatInstansi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtInstansi;
    private javax.swing.JTextField txtJumlahPeserta;
    private javax.swing.JTextField txtPenanggungJawab;
    private javax.swing.JTextField txtTeleponInstansi;
    private javax.swing.JButton uploadGambar;
    // End of variables declaration//GEN-END:variables
}