/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.view;

import admin.view.*;
import common.model.PesanDialog;
import java.awt.Component;
import common.view.FormLogin;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseMotionListener;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

/**
 *
 * @author WILDAN
 */
public class FormUtamaPengguna extends javax.swing.JFrame {
    
    private FormDaftarTransaksi formTransaksi;
    private FormDaftarInstansi formInstansi;
    private FormTambahTransaksi formTambahTransaksi;
    private FormCetakLaporanPenyewaan formCetakLaporanPenyewaan;
    private final PesanDialog pesanDialog = new PesanDialog();
    private FormLogin formLogin;
    private String menu=""; 
    private String nip = "";
    /**
     * Creates new form FormUtama
     */
    public FormUtamaPengguna(String nip) {
        initComponents();
        this.nip = nip;
        setUkuranLokasiFrame(1, true);
        this.setResizable(false);
        //subMenuPanel.remove(cetakPanel);
        jPanel2.remove(subMenuPanel);
    }
    
    private void setUkuranLokasiFrame(double skala, boolean tengah){
        Dimension dimensi = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int)(skala*dimensi.getWidth()),(int)(skala*dimensi.getHeight()));
        if(tengah){
            this.setLocationRelativeTo(null);
        }
    }
    
    private void clearAllFrame(JInternalFrame frame){
        javax.swing.plaf.basic.BasicInternalFrameUI ui = (javax.swing.plaf.basic.BasicInternalFrameUI) frame.getUI();
        Component northPane = ui.getNorthPane();
        MouseMotionListener[] motionListeners = northPane.getListeners(MouseMotionListener.class);
        for (MouseMotionListener listener: motionListeners){
            northPane.removeMouseMotionListener(listener);
        }
        if(mdiDesktopPane.getAllFrames().length != 0){
            mdiDesktopPane.removeAll();
            revalidate();
            repaint();
        }
    }
   
    private void configFrame(JInternalFrame frame){
        Dimension desktopSize = mdiDesktopPane.getSize();
        frame.setSize((int)desktopSize.getWidth(), (int)desktopSize.getHeight());
        System.out.println(frame.getSize());
    }
    
    private void getMenu(JToggleButton button){
        menu = button.getText();
    }
    
    private void DaftarMenu(){
        toggleDaftar.setSelected(true);
        toggleTambah.setSelected(false);
        switch (menu) {
            case "Penyewaan":
                if(togglePenyewaan.isSelected()){
                    this.formTransaksi = new FormDaftarTransaksi();
                    toggleTambah.setVisible(true);
                    jPanel2.add(subMenuPanel);
                    subMenuPanel.add(panelCR);
                    subMenuPanel.remove(cetakPanel);
                    clearAllFrame(formTransaksi);
                    toggleInstansi.setSelected(false);
                    toggleKelCetakLaporan.setSelected(false);
                    configFrame(formTransaksi);
                    mdiDesktopPane.add(formTransaksi);
                }else{
                    menu = "";
                    jPanel2.remove(subMenuPanel);
                    clearAllFrame(formTransaksi);
                }   break;
            case "Instansi":
                if(toggleInstansi.isSelected()){
                    this.formInstansi = new FormDaftarInstansi();
                    toggleTambah.setVisible(false);
                    jPanel2.add(subMenuPanel);
                    subMenuPanel.add(panelCR);
                    subMenuPanel.remove(cetakPanel);
                    clearAllFrame(formInstansi);
                    togglePenyewaan.setSelected(false);
                    toggleKelCetakLaporan.setSelected(false);
                    configFrame(formInstansi);
                    mdiDesktopPane.add(formInstansi);
                }else{
                    menu = "";
                    jPanel2.remove(subMenuPanel);
                    clearAllFrame(formInstansi);
                }   break;
            case "Cetak Laporan":
                 if(toggleKelCetakLaporan.isSelected()){
                    jPanel2.add(subMenuPanel);
                    subMenuPanel.add(cetakPanel);
                    subMenuPanel.remove(panelCR);
                    togglePenyewaan.setSelected(false);
                    toggleInstansi.setSelected(false);
                    revalidate();
                    repaint();
                }else{
                    menu = "";
                    jPanel2.remove(subMenuPanel);
                }   break;
        }
    }
    
    private void TambahMenu(){
        if(toggleTambah.isSelected()){
            switch (menu) {
                case "Penyewaan":
                    this.formTambahTransaksi = new FormTambahTransaksi(this, true, toggleTambah, this.formTransaksi.getTable(), this.nip);
                    formTambahTransaksi.setVisible(true);
                          break;
            }
        }else{
            
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

        jPanel1 = new javax.swing.JPanel();
        mdiDesktopPane = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        subMenuPanel = new javax.swing.JPanel();
        panelCR = new javax.swing.JPanel();
        toggleDaftar = new javax.swing.JToggleButton();
        toggleTambah = new javax.swing.JToggleButton();
        cetakPanel = new javax.swing.JPanel();
        toggleCetakLaporan = new javax.swing.JToggleButton();
        toggleLaporanPembayaran = new javax.swing.JToggleButton();
        mainMenuPanel = new javax.swing.JPanel();
        togglePenyewaan = new javax.swing.JToggleButton();
        toggleInstansi = new javax.swing.JToggleButton();
        toggleKelCetakLaporan = new javax.swing.JToggleButton();
        logoutButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        aplikasiMenu = new javax.swing.JMenu();
        deskripsiMenuItem = new javax.swing.JMenuItem();
        aksesMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        keluarMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout mdiDesktopPaneLayout = new javax.swing.GroupLayout(mdiDesktopPane);
        mdiDesktopPane.setLayout(mdiDesktopPaneLayout);
        mdiDesktopPaneLayout.setHorizontalGroup(
            mdiDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mdiDesktopPaneLayout.setVerticalGroup(
            mdiDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(239, 219, 167));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(239, 219, 167));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        subMenuPanel.setBackground(new java.awt.Color(239, 219, 167));
        subMenuPanel.setPreferredSize(new java.awt.Dimension(245, 500));
        subMenuPanel.setLayout(new java.awt.GridLayout(0, 1));

        panelCR.setBackground(new java.awt.Color(239, 219, 167));

        toggleDaftar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        toggleDaftar.setText("Daftar");
        toggleDaftar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toggleDaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleDaftarActionPerformed(evt);
            }
        });

        toggleTambah.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        toggleTambah.setText("Tambah");
        toggleTambah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toggleTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCRLayout = new javax.swing.GroupLayout(panelCR);
        panelCR.setLayout(panelCRLayout);
        panelCRLayout.setHorizontalGroup(
            panelCRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toggleDaftar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(toggleTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelCRLayout.setVerticalGroup(
            panelCRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCRLayout.createSequentialGroup()
                .addComponent(toggleDaftar)
                .addGap(1, 1, 1)
                .addComponent(toggleTambah))
        );

        subMenuPanel.add(panelCR);

        cetakPanel.setBackground(new java.awt.Color(239, 219, 167));

        toggleCetakLaporan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        toggleCetakLaporan.setText("Cetak Laporan Penyewaan");
        toggleCetakLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toggleCetakLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleCetakLaporanActionPerformed(evt);
            }
        });

        toggleLaporanPembayaran.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        toggleLaporanPembayaran.setText("Cetak Laporan Pembayaran");
        toggleLaporanPembayaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toggleLaporanPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleLaporanPembayaranActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cetakPanelLayout = new javax.swing.GroupLayout(cetakPanel);
        cetakPanel.setLayout(cetakPanelLayout);
        cetakPanelLayout.setHorizontalGroup(
            cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toggleCetakLaporan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(toggleLaporanPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        cetakPanelLayout.setVerticalGroup(
            cetakPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cetakPanelLayout.createSequentialGroup()
                .addComponent(toggleCetakLaporan)
                .addGap(1, 1, 1)
                .addComponent(toggleLaporanPembayaran))
        );

        subMenuPanel.add(cetakPanel);

        jPanel2.add(subMenuPanel);

        jPanel3.add(jPanel2, new java.awt.GridBagConstraints());

        mainMenuPanel.setBackground(new java.awt.Color(150, 75, 0));
        mainMenuPanel.setLayout(new java.awt.GridLayout(0, 1));

        togglePenyewaan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        togglePenyewaan.setText("Penyewaan");
        togglePenyewaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        togglePenyewaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglePenyewaanActionPerformed(evt);
            }
        });
        mainMenuPanel.add(togglePenyewaan);

        toggleInstansi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        toggleInstansi.setText("Instansi");
        toggleInstansi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toggleInstansi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleInstansiActionPerformed(evt);
            }
        });
        mainMenuPanel.add(toggleInstansi);

        toggleKelCetakLaporan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        toggleKelCetakLaporan.setText("Cetak Laporan");
        toggleKelCetakLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        toggleKelCetakLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleKelCetakLaporanActionPerformed(evt);
            }
        });
        mainMenuPanel.add(toggleKelCetakLaporan);

        logoutButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        mainMenuPanel.add(logoutButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 25;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(mainMenuPanel, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(239, 219, 167));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asset/Webp.net-resizeimage (1).png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel1.setText("SISTEM ADMINISTRASI PENYEWAAN");
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        jLabel3.setText(" BUMI PERKEMAHAN KITRI BHAKTI");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(128, 128, 128)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mdiDesktopPane)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mdiDesktopPane))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        aplikasiMenu.setText("Aplikasi");

        deskripsiMenuItem.setText("Deskripsi");
        deskripsiMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deskripsiMenuItemActionPerformed(evt);
            }
        });
        aplikasiMenu.add(deskripsiMenuItem);

        aksesMenuItem.setText("Login");
        aksesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aksesMenuItemActionPerformed(evt);
            }
        });
        aplikasiMenu.add(aksesMenuItem);
        aplikasiMenu.add(jSeparator1);

        keluarMenuItem.setText("Keluar");
        keluarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarMenuItemActionPerformed(evt);
            }
        });
        aplikasiMenu.add(keluarMenuItem);

        jMenuBar1.add(aplikasiMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deskripsiMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deskripsiMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deskripsiMenuItemActionPerformed

    private void keluarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarMenuItemActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_keluarMenuItemActionPerformed

    private void togglePenyewaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglePenyewaanActionPerformed
       getMenu(togglePenyewaan);
       DaftarMenu();
        
    }//GEN-LAST:event_togglePenyewaanActionPerformed

    private void toggleInstansiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleInstansiActionPerformed
        // TODO add your handling code here:
        getMenu(toggleInstansi);
        DaftarMenu();
    }//GEN-LAST:event_toggleInstansiActionPerformed
    
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        // TODO add your handling code here:
        if(pesanDialog.tampilkanPilihan("Apakah anda yakin ingin keluar?", "Konfirmasi", new Object[]{"Ya","Tidak"}) == JOptionPane.YES_OPTION){
            dispose();
            formLogin = new FormLogin();
            formLogin.setVisible(true);
        }
        
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void toggleDaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleDaftarActionPerformed
        // TODO add your handling code here:
        DaftarMenu();
    }//GEN-LAST:event_toggleDaftarActionPerformed

    private void toggleTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleTambahActionPerformed
        // TODO add your handling code here:
        TambahMenu();
    }//GEN-LAST:event_toggleTambahActionPerformed

    private void toggleKelCetakLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleKelCetakLaporanActionPerformed
        // TODO add your handling code here:
        getMenu(toggleKelCetakLaporan);
        DaftarMenu();
    }//GEN-LAST:event_toggleKelCetakLaporanActionPerformed

    private void aksesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aksesMenuItemActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_aksesMenuItemActionPerformed

    private void toggleCetakLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleCetakLaporanActionPerformed
        // TODO add your handling code here:
        formCetakLaporanPenyewaan = new FormCetakLaporanPenyewaan(this, true, toggleCetakLaporan, "transaksi");
        formCetakLaporanPenyewaan.setVisible(true);
    }//GEN-LAST:event_toggleCetakLaporanActionPerformed

    private void toggleLaporanPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleLaporanPembayaranActionPerformed
        // TODO add your handling code here:
        formCetakLaporanPenyewaan = new FormCetakLaporanPenyewaan(this, true, toggleLaporanPembayaran, "pembayaran");
        formCetakLaporanPenyewaan.setVisible(true);
    }//GEN-LAST:event_toggleLaporanPembayaranActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if(pesanDialog.tampilkanPilihan("Apakah anda yakin menutup aplikasi ini?", "Konfirmasi", new Object[]{"Ya","Tidak"}) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aksesMenuItem;
    private javax.swing.JMenu aplikasiMenu;
    private javax.swing.JPanel cetakPanel;
    private javax.swing.JMenuItem deskripsiMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem keluarMenuItem;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainMenuPanel;
    private javax.swing.JDesktopPane mdiDesktopPane;
    private javax.swing.JPanel panelCR;
    private javax.swing.JPanel subMenuPanel;
    private javax.swing.JToggleButton toggleCetakLaporan;
    private javax.swing.JToggleButton toggleDaftar;
    private javax.swing.JToggleButton toggleInstansi;
    private javax.swing.JToggleButton toggleKelCetakLaporan;
    private javax.swing.JToggleButton toggleLaporanPembayaran;
    private javax.swing.JToggleButton togglePenyewaan;
    private javax.swing.JToggleButton toggleTambah;
    // End of variables declaration//GEN-END:variables
}
