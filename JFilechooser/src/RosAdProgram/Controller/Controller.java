/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RosAdProgram.Controller;

import RosAdProgram.model.Model;
import RosAdProgram.view.ConfigGUI;
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
    
    public Controller(Model model, ConfigGUI configGUI, StatisticsGUI statisticsGUI) {
        this.model = model;
        this.configGUI = configGUI;
        this.statisticsGUI = statisticsGUI;
        configGUI.setControllerReference(this); // передает ссылку на объект класса Controller объекту класса ConfigGUI 
    }
    
    // обработка нажатия кнопки выбора источника сигнала ИС
    public void IsButtonPressed() {
//        JFileChooser fc = new JFileChooser(); // переменная выбора файла
//        int o = fc.showOpenDialog(configGUI); // Открывает окно выбора пути. 
//        
//        if (o == JFileChooser.APPROVE_OPTION) { // Если пользователь подтвердил выбор кнопкой ОК
//            model.setOpenPath( fc.getSelectedFile() ); // Метод сохраняет путь к выбранному файлу в переменной openPath класса Model            
//            
//            model.setFileSize(); // запускаем метод вычисления значения размера файла в переменной объекта класса Model
//        }    
//        System.out.println("open = "+ fc.getSelectedFile());
        model.setFileSize();
    }
    
    public void PsButtonPressed() {
//        JFileChooser fc = new JFileChooser(); // переменная выбора файла
//        int s = fc.showSaveDialog(configGUI);
//         if (s == JFileChooser.APPROVE_OPTION) { // if "ok" is pressed
//            model.setSavePath( fc.getSelectedFile() ); // 
//        } 
//        System.out.println("save = "+ fc.getSelectedFile());
    }
    
    public void usefulVoltageChanged(float value){
        model.setUsefulVoltge(value);
    }

    public void speedChanged(int value) {
        model.setSpeed(value);
    }

    // эффективное напряжение шума
    public void noiseVoltageChanged(float value) {
        model.setNoiseVoltage(value);
    }
    
    public void frequencyChanged(int value) {
        model.setFrequency( value );
    }
    
}
