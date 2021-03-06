/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.view;

import java.util.Locale;
import javax.swing.JToggleButton;
import pengguna.controller.PembayaranController;
import pengguna.controller.TransaksiController;

/**
 *
 * @author WILDAN
 */
public class FormCetakLaporanPenyewaan extends javax.swing.JDialog {
    JToggleButton toggleLaporan;
    String op;
    /**
     * Creates new form FormCetakLaporan
     */
    public FormCetakLaporanPenyewaan(java.awt.Frame parent, boolean modal, JToggleButton toggleLaporanPenyewaan, String op) {
        super(parent, modal);
        initComponents();
        this.toggleLaporan = toggleLaporanPenyewaan;
        this.op = op;
        setLocationRelativeTo(null);
        chooserBulan.setLocale(new Locale("id"));
        radioTahun.setSelected(true);
        chooserBulan.setEnabled(false);
        if(op.equals("transaksi")){
            titleLabel.setText("Cetak Laporan Transaksi");
        }else{
            titleLabel.setText("Cetak Laporan Pembayaran");
        }
    }
    
    private void setPeriode(String actionCommand){
        if(actionCommand.equals("Tahun")){
            chooserBulan.setEnabled(false);
        }else{
            chooserBulan.setEnabled(true);
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

        periodeGroup = new javax.swing.ButtonGroup();
        chooserBulan = new com.toedter.calendar.JMonthChooser();
        tahunChooser = new com.toedter.calendar.JYearChooser();
        radioTahun = new javax.swing.JRadioButton();
        radioBulan = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        checkRekapitulasi = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        panelTrx = new javax.swing.JPanel();
        checkBatal = new javax.swing.JCheckBox();
        checkProses = new javax.swing.JCheckBox();
        checkSedangDisewa = new javax.swing.JCheckBox();
        checkSelesai = new javax.swing.JCheckBox();
        panelBayar = new javax.swing.JPanel();
        checkLunas = new javax.swing.JCheckBox();
        checkBelumLunasOrDp = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        chooserBulan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        periodeGroup.add(radioTahun);
        radioTahun.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioTahun.setText("Tahun");
        radioTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTahunActionPerformed(evt);
            }
        });

        periodeGroup.add(radioBulan);
        radioBulan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioBulan.setText("Bulan");
        radioBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBulanActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Bulan");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tahun");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Periode");

        btnCetak.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Cetak Laporan");

        checkRekapitulasi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        checkRekapitulasi.setText("Rekapitulasi");
        checkRekapitulasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRekapitulasiActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opsi"));

        panelTrx.setBorder(javax.swing.BorderFactory.createTitledBorder("Status Transaksi"));
        panelTrx.setLayout(new java.awt.GridBagLayout());

        checkBatal.setText("Batal");
        checkBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBatalActionPerformed(evt);
            }
        });
        panelTrx.add(checkBatal, new java.awt.GridBagConstraints());

        checkProses.setText("Proses");
        panelTrx.add(checkProses, new java.awt.GridBagConstraints());

        checkSedangDisewa.setText("Sedang disewa");
        panelTrx.add(checkSedangDisewa, new java.awt.GridBagConstraints());

        checkSelesai.setText("Selesai");
        panelTrx.add(checkSelesai, new java.awt.GridBagConstraints());

        panelBayar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status Pembayaran", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelBayar.setLayout(new java.awt.GridBagLayout());

        checkLunas.setText("Lunas");
        checkLunas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkLunasActionPerformed(evt);
            }
        });
        panelBayar.add(checkLunas, new java.awt.GridBagConstraints());

        checkBelumLunasOrDp.setText("Belum Lunas");
        panelBayar.add(checkBelumLunasOrDp, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTrx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTrx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(btnCetak, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(12, 12, 12)
                                .addComponent(radioTahun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioBulan))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(24, 24, 24))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkRekapitulasi)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(chooserBulan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tahunChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(120, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioTahun)
                    .addComponent(radioBulan)
                    .addComponent(jLabel3))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooserBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tahunChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkRekapitulasi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetak)
                    .addComponent(btnBatal))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void radioTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTahunActionPerformed
        // TODO add your handling code here:
        setPeriode(evt.getActionCommand());
    }//GEN-LAST:event_radioTahunActionPerformed

    private void radioBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBulanActionPerformed
        // TODO add your handling code here:
        setPeriode(evt.getActionCommand());
    }//GEN-LAST:event_radioBulanActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        if(op.equals("transaksi")){
            new TransaksiController().cetakLaporan(chooserBulan, tahunChooser, checkRekapitulasi);
        }else{
            new PembayaranController().cetakLaporan(chooserBulan, tahunChooser, checkRekapitulasi);
        }
        
        dispose();
    }//GEN-LAST:event_btnCetakActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        toggleLaporan.setSelected(false);
    }//GEN-LAST:event_formWindowClosed

    private void checkRekapitulasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRekapitulasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkRekapitulasiActionPerformed

    private void checkBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBatalActionPerformed

    private void checkLunasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkLunasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkLunasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCetak;
    private javax.swing.JCheckBox checkBatal;
    private javax.swing.JCheckBox checkBelumLunasOrDp;
    private javax.swing.JCheckBox checkLunas;
    private javax.swing.JCheckBox checkProses;
    private javax.swing.JCheckBox checkRekapitulasi;
    private javax.swing.JCheckBox checkSedangDisewa;
    private javax.swing.JCheckBox checkSelesai;
    private com.toedter.calendar.JMonthChooser chooserBulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelBayar;
    private javax.swing.JPanel panelTrx;
    private javax.swing.ButtonGroup periodeGroup;
    private javax.swing.JRadioButton radioBulan;
    private javax.swing.JRadioButton radioTahun;
    private com.toedter.calendar.JYearChooser tahunChooser;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
