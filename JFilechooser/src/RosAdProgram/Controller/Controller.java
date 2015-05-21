/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RosAdProgram.Controller;

import RosAdProgram.model.Model;
import RosAdProgram.view.ConfigGUI;
import RosAdProgram.view.MainGUI;
import RosAdProgram.view.StatisticsGUI;
import javax.swing.JFileChooser;

/**
 *
 * @author Вадим
 */
public class Controller {

    private final Model model;
    private final ConfigGUI configGUI;
    private final StatisticsGUI statisticsGUI;
    private final MainGUI mainGui;
    
    public Controller(Model model, MainGUI mainGui, ConfigGUI configGUI, StatisticsGUI statisticsGUI) {
        this.model = model;
        this.mainGui = mainGui;
        this.configGUI = configGUI;
        this.statisticsGUI = statisticsGUI;
        configGUI.setControllerReference(this); // передает ссылку на объект класса Controller объекту класса ConfigGUI 
    }
    
    // обработка нажатия кнопки выбора источника сигнала ИС
    public void IsButtonPressed() {
        JFileChooser fc = new JFileChooser(); // переменная выбора файла
        int o = fc.showOpenDialog(configGUI); // Открывает окно выбора пути. 
        
        if (o == JFileChooser.APPROVE_OPTION) { // Если пользователь подтвердил выбор кнопкой ОК
            model.setOpenPath( fc.getSelectedFile() ); // Метод сохраняет путь к выбранному файлу в переменной openPath класса Model            
            
            model.setFileSize(); // запускаем метод вычисления значения размера файла в переменной объекта класса Model
        }    
    }
    
    public void PsButtonPressed() {
        JFileChooser fc = new JFileChooser(); // переменная выбора файла
        int s = fc.showSaveDialog(configGUI);
         if (s == JFileChooser.APPROVE_OPTION) { // if "ok" is pressed
            model.setSavePath( fc.getSelectedFile() ); // 
        } 
    }
    
    public void usefulVoltageChanged(String stringInput){
        float numberInput = Float.valueOf( stringInput ); // перевод строки в число
        model.setUsefulVoltge(numberInput);
    }

    public void speedChanged(String stringInput) {
        int numberInput = Integer.parseInt( stringInput ); // converts srting to number
        model.setSpeed( numberInput );
    }

    // эффективное напряжение шума
    public void noiseVoltageChanged(float value) {
        model.setNoiseVoltage(value);
    }
    
    public void frequencyChanged(String stringInput) {
        int numberInput = Integer.parseInt( stringInput ); // converts srting to number
        model.setFrequency( numberInput );
    }
    
}
