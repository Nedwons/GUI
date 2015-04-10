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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Вадим
 */
public class Controller {
    
    private View view;
    
    public Controller(View viewInstance) {
        this.view = viewInstance;
    } 
    
    /**
     *
     * @param open - путь к открываемому файлу
     * @param save - путь к сохраняемому файлу
     * @param probability - вероятность ошибки
     */
    public void copy (final File open, final File save, final float probability) {
        SwingWorker<Void, Integer[]> worker = new SwingWorker<Void, Integer[]>() {  // поток обновления progressBar
           // a = new int[10]
            @Override
            protected Void doInBackground() throws Exception {
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

                    try { // Noise adding 
                        Integer[] mistakeAndProgress = new Integer[2];
                        mistakeAndProgress[1] = 0;
                        mistakeAndProgress[0] = 0;
  
                        while ((c = in.read()) != -1) {  
                            System.out.println("В цикле 1");
                            mistakeAndProgress[1]++;
                            System.out.println("В цикле 2");
                            if ((Math.random()) > probability) {
                                out.write(c ^ 8);
                                mistakeAndProgress[0]++;
                            } else {
                                out.write(c);
                            }                           
                            //System.out.println("Ошибка и прогресс" + mistakeAndProgress[0] + mistakeAndProgress[1]);
                            publish (mistakeAndProgress);
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
                return null;
            }

            @Override
            // This will be called if you call publish() from doInBackground()
            // Can safely update the GUI here.
            protected void process(List<Integer[]> chunks) { // динамический принимает выплевываемые значения
                Integer[] value = chunks.get(chunks.size() - 1); //магия
                //view.setQuantityLabel(maskCounter);
                view.setProgress(value[1]);  // обновление прогрессбара
                view.setQuantityLabel(value[0]); // label с количеством ошибок
            }
        };
        worker.execute();
    }
    
    
    
}
