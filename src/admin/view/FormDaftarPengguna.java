/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.view;

import admin.controller.PenggunaController;
import admin.model.User;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import common.model.PesanDialog;
import javax.swing.JTable;

/**
 *
 * @author WILDAN
 */
public class FormDaftarPengguna extends javax.swing.JInternalFrame {
    private final PesanDialog pesanDialog = new PesanDialog();
    private FormUpdatePengguna formUpdatePengguna;
    private final PenggunaController penggunaController = new PenggunaController();
    private User user = new User();
    String nip;
    String aktif;
    /**
     * Creates new form FormKelolaPengguna
     */
    public FormDaftarPengguna() {
        initComponents();
        setVisible(true);
        
        setLocation(0,0);
        btnAktif.setEnabled(false);
        penggunaController.readData(tableUser);
    }
    
     public JTable getTable() {
        return tableUser;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnAktif = new javax.swing.JButton();

        setTitle("Form Daftar Pengguna");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No Anggota", "Nama", "Username", "Jenis Kelamin", "Tanggal Lahir", "Alamat", "Email", "Telepon", "Hak Akses", "Terakhir Masuk", "Aktif"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUser.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUserMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableUserMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableUser);
        if (tableUser.getColumnModel().getColumnCount() > 0) {
            tableUser.getColumnModel().getColumn(0).setPreferredWidth(90);
            tableUser.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableUser.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableUser.getColumnModel().getColumn(3).setPreferredWidth(100);
            tableUser.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableUser.getColumnModel().getColumn(5).setPreferredWidth(200);
            tableUser.getColumnModel().getColumn(6).setPreferredWidth(150);
            tableUser.getColumnModel().getColumn(7).setPreferredWidth(150);
            tableUser.getColumnModel().getColumn(8).setPreferredWidth(150);
            tableUser.getColumnModel().getColumn(9).setPreferredWidth(150);
            tableUser.getColumnModel().getColumn(10).setPreferredWidth(80);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 932;
        gridBagConstraints.ipady = 503;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnUbah.setBackground(new java.awt.Color(0, 204, 0));
        btnUbah.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        jPanel1.add(btnUbah);

        btnHapus.setBackground(new java.awt.Color(255, 0, 0));
        btnHapus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btnHapus);

        btnAktif.setBackground(new java.awt.Color(0, 0, 255));
        btnAktif.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAktif.setForeground(new java.awt.Color(255, 255, 255));
        btnAktif.setText("Aktif");
        btnAktif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAktifActionPerformed(evt);
            }
        });
        jPanel1.add(btnAktif);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 1000;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
        penggunaController.readData(tableUser);
    }//GEN-LAST:event_formFocusLost

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if(tableUser.getSelectedRow()>=0){
            if(pesanDialog.tampilkanPilihan("Apakah anda yakin ingin menghapus?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                penggunaController.hapus(nip);
                penggunaController.readData(tableUser);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Silahkan pilih pengguna untuk dihapus");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnAktifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAktifActionPerformed
        // TODO add your handling code here:
        if(tableUser.getSelectedRow()>=0){
            if(aktif.equals("Y")){
                if(pesanDialog.tampilkanPilihan("Apakah anda yakin ingin mengaktifkan?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                    penggunaController.aktif(nip, aktif);
                    penggunaController.readData(tableUser);
                }
            }else{
                if(pesanDialog.tampilkanPilihan("Apakah anda yakin ingin menonaktifkan?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
                    penggunaController.aktif(nip, aktif);
                    penggunaController.readData(tableUser);
                }
            }
            btnAktif.setEnabled(false);
        }else{
            JOptionPane.showMessageDialog(this, "Silahkan pilih pengguna untuk diaktifkan");
        }
    }//GEN-LAST:event_btnAktifActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_tableUserMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here:
        if(tableUser.getSelectedRow() >= 0){
            this.formUpdatePengguna = new FormUpdatePengguna(null, true, tableUser, nip);
            this.formUpdatePengguna.setVisible(true);
        }else
            JOptionPane.showMessageDialog(this, "Silahkan pilih pengguna untuk diperbaharui");
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tableUserMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMousePressed
        // TODO add your handling code here:
        if(tableUser.getSelectedRow() >= 0){
            btnAktif.setEnabled(true);
            nip = tableUser.getValueAt(tableUser.getSelectedRow(), 0).toString();
            aktif = tableUser.getValueAt(tableUser.getSelectedRow(), 10).toString();
            if(aktif.equals("T") || aktif.equals("")){
                aktif = "Y";
                btnAktif.setText("Aktif");
            }else{
                aktif = "T";
                btnAktif.setText("Non Aktif");
            }
        }
    }//GEN-LAST:event_tableUserMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAktif;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnUbah;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    // End of variables declaration//GEN-END:variables
}
