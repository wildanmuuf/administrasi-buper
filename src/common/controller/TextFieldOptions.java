/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author WILDAN
 */
public class TextFieldOptions {
    public static void phoneNumber(JTextField tf){
        tf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent ke) {
            String value = tf.getText();
            char c = ke.getKeyChar();
            if ((value.isEmpty() && c == '0') || (!value.isEmpty() && Character.isDigit(c) ) || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                InputLimit(tf, 15);
                if(value.length() == 1 && (c != '2' && c!='8')){
                    ke.consume();
                }
            } else {
               ke.consume();
            }
         }
      });
    }
    public static void harga(JTextField tf){
        tf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent ke) {
            String value = tf.getText();
            char c = ke.getKeyChar();
            int length = value.length();
            if ((value.isEmpty() && c != '0' && Character.isDigit(c)) || (!value.isEmpty() && Character.isDigit(c)) || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                InputLimit(tf, 10);
            } else {
               ke.consume();
            }
         }
      });
    }
    
    public static void intOnly(JTextField tf){
        tf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent ke) {
            String value = tf.getText();
            char c = ke.getKeyChar();
            int length = value.length();
            if ((value.isEmpty() && Character.isDigit(c)) || (!value.isEmpty() && Character.isDigit(c)) || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_DELETE) {
                InputLimit(tf, 10);
            } else {
               ke.consume();
            }
         }
      });
    }
    
    public static void InputLimit(JTextField tf, int limit){
        tf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent ke) {
            String value = tf.getText();
            int length = value.length();
            if(length == limit){
              ke.consume();
             }
         }
      });
    }
}
