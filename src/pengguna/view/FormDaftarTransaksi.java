/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.view;

import admin.view.*;
import admin.model.User;
import common.controller.ActivityController;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pengguna.controller.TransaksiController;
import common.model.PesanDialog;
import common.model.UserLogin;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author WILDAN
 */
public class FormDaftarTransaksi extends javax.swing.JInternalFrame {
    
    private final TransaksiController transaksiController = new TransaksiController();
    private FormBayar formBayar;
    String kd_transaksi, status;
    String aktif;
    /**
     * Creates new form FormKelolaPengguna
     */
    public FormDaftarTransaksi() {
        initComponents();
        setVisible(true);
        setLocation(0,0);
        transaksiController.readData(tableTransaksi);
        tableTransaksi.removeColumn(tableTransaksi.getColumnModel().getColumn(2));
    }
    
     public JTable getTable() {
        return tableTransaksi;
    }
     
    private void filter(){
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableTransaksi.getModel());
        tableTransaksi.setRowSorter(sorter);
        String txt = txtFilter.getText();
        if(txt.length()==0){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter(txt));
        }
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

        jPanel2 = new javax.swing.JPanel();
        txtFilter = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnBatalkan = new javax.swing.JButton();
        btnBayar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTransaksi = new javax.swing.JTable();

        setTitle("Form Daftar Transaksi");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        txtFilter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnCari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(btnRefresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 995;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btnBatalkan.setBackground(new java.awt.Color(255, 0, 0));
        btnBatalkan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBatalkan.setForeground(new java.awt.Color(255, 255, 255));
        btnBatalkan.setText("Batalkan");
        btnBatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalkanActionPerformed(evt);
            }
        });
        jPanel1.add(btnBatalkan);

        btnBayar.setBackground(new java.awt.Color(0, 255, 51));
        btnBayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setText("Bayar");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBayar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 1317;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(jPanel1, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tableTransaksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Detail", "Kode Transaksi", "Kode Instansi", "Instansi", "Penanggung Jawab", "Status", "Tanggal Transaksi", "Tanggal Mulai", "Tanggal Berakhir", "Total Peserta", "Total Tagihan", "Penerima", "Status Bayar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTransaksi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableTransaksi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableTransaksiMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableTransaksi);
        if (tableTransaksi.getColumnModel().getColumnCount() > 0) {
            tableTransaksi.getColumnModel().getColumn(0).setPreferredWidth(70);
            tableTransaksi.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableTransaksi.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableTransaksi.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableTransaksi.getColumnModel().getColumn(4).setPreferredWidth(200);
            tableTransaksi.getColumnModel().getColumn(5).setPreferredWidth(120);
            tableTransaksi.getColumnModel().getColumn(6).setPreferredWidth(200);
            tableTransaksi.getColumnModel().getColumn(7).setPreferredWidth(200);
            tableTransaksi.getColumnModel().getColumn(8).setPreferredWidth(200);
            tableTransaksi.getColumnModel().getColumn(9).setPreferredWidth(100);
            tableTransaksi.getColumnModel().getColumn(10).setPreferredWidth(150);
            tableTransaksi.getColumnModel().getColumn(11).setPreferredWidth(200);
            tableTransaksi.getColumnModel().getColumn(12).setPreferredWidth(150);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1365;
        gridBagConstraints.ipady = 537;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
        transaksiController.readData(tableTransaksi);
    }//GEN-LAST:event_formFocusLost

    private void tableTransaksiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTransaksiMousePressed
        // TODO add your handling code here:
        if(tableTransaksi.getSelectedRow()>=0){
            kd_transaksi = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 1).toString();
            status = tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 4).toString();
        }
    }//GEN-LAST:event_tableTransaksiMousePressed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        transaksiController.readData(tableTransaksi);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        filter();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnBatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalkanActionPerformed
        // TODO add your handling code here:
        if(tableTransaksi.getSelectedRow()>=0){
            transaksiController.batal(kd_transaksi);
            transaksiController.readData(tableTransaksi);
        }else{
            JOptionPane.showMessageDialog(this, "Silahkan pilih transaksi untuk dibatalkan");
        }
    }//GEN-LAST:event_btnBatalkanActionPerformed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        // TODO add your handling code here:
        if(tableTransaksi.getSelectedRow()>=0){
            formBayar = new FormBayar(null,true, tableTransaksi, kd_transaksi, UserLogin.getNip(), status);
            formBayar.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Silahkan pilih transaksi untuk melakukan pembayaran");
        }
    }//GEN-LAST:event_btnBayarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatalkan;
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTransaksi;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
