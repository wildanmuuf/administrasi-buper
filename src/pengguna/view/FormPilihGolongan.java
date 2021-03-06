/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.view;

import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import pengguna.controller.GolonganController;
import common.model.Golongan;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author WILDAN
 */
public class FormPilihGolongan extends javax.swing.JDialog {
    private final GolonganController golonganController = new GolonganController();
    DefaultTableModel model;
    JTable tableGol;
    ArrayList<Golongan> listGolongan;
    JTextField txtJumlahPeserta;
    JTextField txtTotalGolongan;
    int days;
    NumberFormat format = NumberFormat.getNumberInstance(new Locale("in", "ID"));

    /**
     * Creates new form FormPilihFasilitas
     */
    public FormPilihGolongan(java.awt.Frame parent, boolean modal, 
            JTable tableGol, 
            ArrayList<Golongan> listGolongan, JTextField txtJumlahPeserta, JTextField txtTotalGolongan, int days) {
        super(parent, modal);
        initComponents();
        this.tableGol = tableGol;
        this.txtTotalGolongan = txtTotalGolongan;
        this.listGolongan = listGolongan;
        this.txtJumlahPeserta = txtJumlahPeserta;
        this.days = days;
        setLocationRelativeTo(null);
        model = (DefaultTableModel) tableGolongan.getModel();
        golonganController.readData(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tablePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableGolongan = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        btnPilih = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        setTitle("Form Pilih Golongan");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tableGolongan.setAutoCreateRowSorter(true);
        tableGolongan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableGolongan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pilih", "Kode Golongan", "Golongan", "Harga", "Jumlah Peserta", "Jumlah Panitia", "Jumlah Pembina"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableGolongan.setColumnSelectionAllowed(true);
        tableGolongan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableGolonganMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableGolongan);
        tableGolongan.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableGolongan.getColumnModel().getColumnCount() > 0) {
            tableGolongan.getColumnModel().getColumn(0).setResizable(false);
            tableGolongan.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
            .addGroup(tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tablePanelLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        buttonPanel.setLayout(new java.awt.GridLayout(1, 0));

        btnPilih.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPilih.setText("Pilih");
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });
        buttonPanel.add(btnPilih);

        btnBatal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBatal.setText("Batal");
        buttonPanel.add(btnBatal);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 524, Short.MAX_VALUE)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 40, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableGolonganMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGolonganMousePressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_tableGolonganMousePressed

    private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        // TODO add your handling code here:
        for(int i = 0; i < tableGolongan.getRowCount(); i++){
            if(tableGolongan.getValueAt(i, 0) != null){
                Boolean pilih =  Boolean.valueOf(tableGolongan.getValueAt(i, 0).toString());
                Golongan golongan = new Golongan();
                String peserta = tableGolongan.getValueAt(i, 4).toString();
                int jumlah_peserta = Integer.parseInt(peserta);
                String panitia = tableGolongan.getValueAt(i, 5).toString();
                int jumlah_panitia = Integer.parseInt(panitia);
                String pembina = tableGolongan.getValueAt(i, 5).toString();
                int jumlah_pembina = Integer.parseInt(pembina);
                String kd_golongan = tableGolongan.getValueAt(i, 1).toString();
                String nama_golongan = tableGolongan.getValueAt(i, 2).toString();
                String harga = tableGolongan.getValueAt(i, 3).toString();
                if(pilih){
                    if(jumlah_peserta > 0){
                        golongan.setKd_golongan(kd_golongan);
                        golongan.setNama(nama_golongan);
                        golongan.setHarga(harga);
                        golongan.setJumlah_peserta(jumlah_peserta);
                        golongan.setJumlah_panitia(jumlah_panitia);
                        golongan.setJumlah_pembina(jumlah_pembina);
                        listGolongan.add(golongan);
                    }else{
                        JOptionPane.showMessageDialog(null, "Jumlah peserta pada "+nama_golongan+" tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        
        if(listGolongan.size() > 0){
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Golongan tidak boleh kosong!", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }

        
        
    }//GEN-LAST:event_btnPilihActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        DefaultTableModel tableGolModel = (DefaultTableModel)tableGol.getModel();
        if(!listGolongan.isEmpty()){
            tableGolModel.setRowCount(0);
            int jumlah_peserta = 0;
            int jumlah_panitia = 0;
            int jumlah_pembina = 0;
            int total = 0;
            int no = 0;
            for(Golongan golongan : listGolongan){
                jumlah_peserta += golongan.getJumlah_peserta();
                jumlah_panitia += golongan.getJumlah_panitia();
                jumlah_pembina += golongan.getJumlah_pembina();
                int harga = Integer.parseInt(golongan.getHarga())*golongan.getJumlah_peserta()*days;
                total += harga;
                no++;
                Object[] obj = {no,golongan.getNama(),"Rp. "+format.format(Integer.parseInt(golongan.getHarga())) , golongan.getJumlah_peserta(), jumlah_panitia, jumlah_pembina, "Rp. "+format.format(harga)};
                txtJumlahPeserta.setText(String.valueOf(jumlah_peserta));
                tableGolModel.addRow(obj);
            }
            txtTotalGolongan.setText("Rp. "+format.format(total));
        }
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnPilih;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable tableGolongan;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}
