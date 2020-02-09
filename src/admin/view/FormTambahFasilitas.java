/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.view;

import admin.controller.FasilitasController;
import common.controller.TextFieldOptions;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WILDAN
 */
public class FormTambahFasilitas extends javax.swing.JDialog {
    private final FasilitasController fasilitasController = new FasilitasController();
    JToggleButton toggleTambah;
    JTable tableFasilitas;
    /**
     * Creates new form FormTambahPengguna
     */
    public FormTambahFasilitas(java.awt.Frame parent, boolean modal, JToggleButton toggleTambah, JTable tableFasilitas) {
        super(parent, modal);
        this.toggleTambah = toggleTambah;
        this.tableFasilitas = tableFasilitas;
        initComponents();
        radioWajib.setActionCommand("Wajib");
        radioTdkWajib.setActionCommand("Tidak Wajib");
        setLocationRelativeTo(null);
        fasilitasController.setKode(txtKodeFasilitas);
        TextFieldOptions.harga(txtHarga);
        TextFieldOptions.intOnly(txtStok);
        radioWajib.setSelected(true);
    }
    
    private void clear(){
        fasilitasController.setKode(txtKodeFasilitas);
        txtNama.setText("");
        txtHarga.setText("");
    }
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusGroupRadio = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        txtKodeFasilitas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        radioWajib = new javax.swing.JRadioButton();
        radioTdkWajib = new javax.swing.JRadioButton();
        btnSimpan = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Tambah Pengguna");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Kode Fasilitas");

        txtKodeFasilitas.setEditable(false);
        txtKodeFasilitas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nama Fasilitas");

        txtNama.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Harga");

        txtHarga.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Status");

        statusGroupRadio.add(radioWajib);
        radioWajib.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioWajib.setText("Wajib");

        statusGroupRadio.add(radioTdkWajib);
        radioTdkWajib.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioTdkWajib.setText("Tidak Wajib");

        btnSimpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBersih.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBersih.setText("Bersihkan");
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Tambah Fasilitas");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Stok");

        txtStok.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnBersih, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(121, 121, 121))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5))
                                    .addGap(33, 33, 33)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                            .addComponent(txtHarga))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtStok, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(radioWajib, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(18, 18, 18)
                                            .addComponent(radioTdkWajib))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(37, 37, 37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(txtKodeFasilitas, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(69, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(69, 69, 69))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKodeFasilitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioWajib)
                        .addComponent(radioTdkWajib)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBersih))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if(fasilitasController.simpan("tambah",txtKodeFasilitas.getText(), 
                txtNama.getText(), 
                txtHarga.getText(),
                statusGroupRadio.getSelection().getActionCommand(),
                Integer.parseInt(txtStok.getText()),
                "T")){
            clear();
            dispose();
        }
        
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnBersihActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        clear();
        toggleTambah.setSelected(false);
        fasilitasController.readData(tableFasilitas);
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton radioTdkWajib;
    private javax.swing.JRadioButton radioWajib;
    private javax.swing.ButtonGroup statusGroupRadio;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKodeFasilitas;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
