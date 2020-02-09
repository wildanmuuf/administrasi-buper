/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengguna.controller;

import java.util.ArrayList;
import javax.swing.JTextField;
import pengguna.model.Calculate;
import common.model.Fasilitas;
import common.model.Golongan;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author WILDAN
 */
public class CalculateController {
    Calculate calculate;
    NumberFormat format = NumberFormat.getNumberInstance(new Locale("in", "ID"));

    public void hitungTotal(int days, ArrayList<Fasilitas> listFasilitas, ArrayList<Golongan> listGolongan, JTextField txtTotal){
        if((!listFasilitas.isEmpty() || listFasilitas != null) && (!listGolongan.isEmpty() || listGolongan != null) ){
            calculate = new Calculate();
            calculate.setDays(days);
            txtTotal.setText("Rp. "+format.format(calculate.totalTagihan(listFasilitas, listGolongan)));
        }
    }
    
    
   
}
