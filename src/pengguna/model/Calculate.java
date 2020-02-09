/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.model;

import common.model.Fasilitas;
import common.model.Golongan;
import java.util.ArrayList;

/**
 *
 * @author WILDAN
 */
public class Calculate {
    private int days, jumlahPeserta;
    int total_tagihan=0;
    
    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getJumlahPeserta() {
        return jumlahPeserta;
    }

    public void setJumlahPeserta(int jumlahPeserta) {
        this.jumlahPeserta = jumlahPeserta;
    }

    
    
    public int totalTagihan(ArrayList<Fasilitas> listFasilitas, ArrayList<Golongan> listGolongan){
        if(days > 0){
            for(Fasilitas fasilitas : listFasilitas){
                total_tagihan += Integer.parseInt(fasilitas.getHarga())*days;
            }
            for(Golongan golongan : listGolongan){
                total_tagihan += Integer.parseInt(golongan.getHarga())*golongan.getJumlah_peserta()*days;
            }
        }
        return total_tagihan;
    }
}
