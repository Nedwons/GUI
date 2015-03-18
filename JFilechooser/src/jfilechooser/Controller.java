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
    
    public void copy (File open, File save) {
        FileInputStream in = null;
        FileOutputStream out = null;
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
            int maskCounter = 0;
            
            try { // Noise adding 
                while ((c = in.read()) != -1) {
                    if ( ( Math.random()) > 0.9 ) {
                        out.write(c ^ 8);
                        System.out.println("mask!" + maskCounter  );
                        maskCounter ++;
                    } else {       
                        out.write(c);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Ошибка в наложении маски");
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {    
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
