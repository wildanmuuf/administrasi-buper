/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

/**
 *
 * @author WILDAN
 */
public class AlphaNumericCode {
    public static String AutoIncrementKode(String num, int beginIndex){
            String prefix = num.substring(1);
            int b = Integer.parseInt(prefix);
            b = b + 1;
            String fas = num.substring(0,beginIndex);
            num = fas + Integer.toString(b);
            return num;
    }
}
