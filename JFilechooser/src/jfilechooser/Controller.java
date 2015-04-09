/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfilechooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Вадим
 */
public class Controller {
    
    private View view;
    public static int maskCounter = 0;
    public static int totalRead = 0;
    
    public Controller(View viewInstance) {
        this.view = viewInstance;
    } 
    
    public void copy (File open, File save, float probability) {
        FileInputStream in = null;
        FileOutputStream out = null;
        //System.out.println("До вызова потока");
        try {
            try {
                in = new FileInputStream(open);
            } catch (FileNotFoundException ex) {
                System.out.println("Открываемый файл не найден");
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                out = new FileOutputStream(save);
            } catch (FileNotFoundException ex) {
                System.out.println("Сохраняемый файл не найден");
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
            int c; 
            
            try { // Noise adding 
                while ((c = in.read()) != -1) {
                    totalRead++;
                    if ( ( Math.random()) > probability ) {
                        out.write(c ^ 8);
                        System.out.println("mask!" + maskCounter  );
                        maskCounter++;
                    } else {       
                        out.write(c);
                    }
                    //view.setQuantityLabel(totalRead);
            
                    view.setQuantityLabel( maskCounter );  
                    view.setProgress();
                }
            } catch (IOException ex) {
                System.out.println("Ошибка в наложении маски");
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {    
            
            maskCounter = 0;
            totalRead = 0; // обнуление счетчиков
            
            System.out.println("Все прошло успешно");    
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }           
        }
    }
    
    
    
}
