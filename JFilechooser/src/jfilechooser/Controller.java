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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Вадим
 */
public class Controller {
    
    private final View view;
    
    public Controller(View viewInstance) {
        this.view = viewInstance;
    } 
    
    /**
     *  Метод копирует файл с заданной вероятностью
     * @param open - путь к открываемому файлу
     * @param save - путь к сохраняемому файлу
     * @param probability - вероятность ошибки
     */
    public void copy (final File open, final File save, final double probability) {
        SwingWorker<Void, Integer[]> worker = new SwingWorker<Void, Integer[]>() {  // поток обновления progressBar
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
  
                        while ((c = in.read()) != -1) {  // сюда нужно впихнуть mask.
                            mistakeAndProgress[1]++;
                            if ((Math.random()) > probability) {
                                out.write(c ^ 8);
                                mistakeAndProgress[0]++;
                            } else {
                                out.write(c);
                            }                           
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
            protected void process(List<Integer[]> chunks) { // динамический принимает выдаваемые методом 
                                                               // publish значения
                Integer[] value = chunks.get(chunks.size() - 1); //магия
                view.setQuantityLabel(value[0]); //обновление label с количеством ошибок
                view.setProgress(value[1]);  // обновление прогрессбара
            }
        };
        worker.execute();
    }
    
    // метод складывает по модулю 2 32 битную маску из случайным образом искаженных битов на 32 битный участок файла. 
    // входные данные: probaility - вероятность искажения бита; data - 32 битный участок файла.
    // выходные - 32 участок файла с искажениями
    public int mask(int probaility, int data){
        int shiftVariable = 0;
        int tempMask = 0;
        
        Random randomNumberGenerator = new Random();
        for(int i = 30; i != 0; i--) {
            
            if(randomNumberGenerator.nextDouble() > probaility){
                if( shiftVariable == 0 ){ //если первая итерация
                    shiftVariable = 1; 
                    System.out.format("i = %d Накладываем маску %h ",i, tempMask );
                    tempMask = tempMask | shiftVariable; // побитовое сложение нулевой маски со сдвинутой
                    System.out.format("на %h . получаем %h\n", shiftVariable,tempMask );
                } 
                else {
                    System.out.format("i = %d Накладываем маску %h ",i, tempMask );
                    
                    shiftVariable = shiftVariable << 1;
                    tempMask = tempMask | shiftVariable;
                    
                    System.out.format("на %h . получаем %h\n", shiftVariable,tempMask );
                }
            }
        }
        System.out.format("\n\n\n финальная маска = %h\n", tempMask );
        System.out.format("\n\n\n файл = %h\n", data);
        System.out.format("\n\n\n файлова маска = %h\n", data ^ tempMask);
        return data ^ tempMask;
    } 
}