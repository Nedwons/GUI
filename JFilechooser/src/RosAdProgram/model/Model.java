/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RosAdProgram.model;

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
import RosAdProgram.view.ConfigGUI;
import RosAdProgram.view.RosAdGUI;
import RosAdProgram.view.StatisticsGUI;
import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import static org.apache.commons.math3.special.Erf.erf;

/**
 *
 * @author Вадим
 */
public class Model {
    private RosAdGUI rosAdGUI;
    private StatisticsGUI statisticsGUI;
    
    private final double[] errorRatioArray = new double[3]; // массив коэффициентов ошибок 
    private long fileSize; // Размер файла
    private int blockError = 0; // количество битых блоков
    private int byteError = 0; // -- байт
    private int bitError = 0; // -- бит
    private boolean byteErrorFlag = false; // флаг появления ошибки в байте. Сбрасывается в методе mask().
    private int blockSize = 8; // размер блока (в байтах)
    
    private File openPath, savePath; // содержат ссылки на файлы ИС и ПС
    private double usefulVoltageValue  = 7.35; // Напряжение полезного сигнала по умолчанию
    private int speedValue = 50; // Скорость передачи по умолчанию
    private double noiseVoltageValue = 0.6; // эффективное напряжение шума
    private int frequencyValue =50; // Частота шумогенератора
    private double mistakeProbability = 0; // вероятность ошибки
    
    public void setRosAdGUIreference(RosAdGUI rosAdGUI) {
        this.rosAdGUI = rosAdGUI; // связывает ConfigGUI с Model
    }
    public void setStatisticsGUIreference(StatisticsGUI statistics) {
        this.statisticsGUI = statistics; // связывает StatisticsGUI с Model
    }
    
    public int getBlockSize(){
        return blockSize;
    }
    public void setBlockSize(int size){
       this.blockSize = size;
    }
    
    // Getter и Setter размера файла
    public void setFileSize() {
        fileSize = getOpenPath().length(); //определяем размер файла (вызывается при нажатии кнопки ИС)   
    }
    public long getFileSize() {
        return fileSize;
    }
    
    public void setOpenPath (File openPath) {  
        this.openPath = openPath;
    }
    public void setSavePath (File savePath) {
        this.savePath = savePath;
    }
    public File getOpenPath() {  
        return this.openPath;
    }
    public File getSavePath() {  
        return this.savePath;
    }
    
    // устанавливает значение напряжения полезного сигнала
    public void setUsefulVoltge(float numberInput) {
        usefulVoltageValue = numberInput; 
    }
    public double getUsefulVoltage() {
        return usefulVoltageValue; 
    }
    
    // Скорость передачи
    public void setSpeed(int value) {
        speedValue = value;
    }
    public int getSpeed() {
        return speedValue;
    }
    public String getStringSpeed() {
        String speed = String.valueOf( speedValue );
        return speed;
    }
    
    // эффективное напряжение шума
    public void setNoiseVoltage(float value) {
        noiseVoltageValue = value;
    }
    public double getNoiseVoltage() {
        return noiseVoltageValue;
    }
    // Getter и Setter добавления ошибок
    public void setProbability (double prbability) {
        mistakeProbability = (1 - prbability); 
    }
    public double getProbability() {
        return mistakeProbability;
    }
    
    // Метод возвращает процент считанного файла    
    public int getPercent(long number) { // number - количество считанных блоков  
        long tempFileSize = getFileSize(); 
        int result = (int) ( number  / (tempFileSize * 0.01)); // вычисление процента tempFileSize
        return result;
    }
    
    
    // ниже представлены методы для подсчета ошибок в битах байтах и блоках
    public void setBlockError(int number) {
        blockError = blockError + number;
    }
    public int getBlockError(){
        return blockError;
    }  
    
    public void setByteError(int number) {
        byteError = byteError + number;
    }
    public int getByteError(){
        return byteError;
    }
    
    public void setBitError(int number) {
        bitError = bitError + number;
    }
    public int getBitError(){
        return bitError;
    }
    
    public void setByteErrorFlag(boolean c) {
        byteErrorFlag = c;
    }
    public boolean getByteErrorFlag(){
        return byteErrorFlag;
    }
    
    public void setBlockErrorFlag(boolean c) {
        byteErrorFlag = c;
    }
    public boolean getBlockErrorFlag(){
        return byteErrorFlag;
    }
    
    public void setFrequency(int numberInput) {
        frequencyValue = numberInput;
    }
    public int getFrequency() {
        return frequencyValue;
    }
    public long getFileSizeInBits(){ // возвращает размре файла в битах
        return getFileSize() * 8;
    }
    public double getFileSizeInBlocks(){ // то же в блоках
        long tempFileSize = getFileSize(); // размер  файла в байтах
        int tempBlockSize = getBlockSize(); // размер блока в байтах 
        double blocks = ceil( tempFileSize / tempBlockSize); // деление с округлением в большую сторону
        return blocks;
    }
    
    public double ratioCalc(double error, double total){ // вычисляет коэффициент ошибки
        double ratio = error / total;
        return ratio;
    }
    
    public void resetErrors(){ // обнуляет количество ошибок перед началом сеанса связи
        bitError = 0;
        byteError = 0;
        blockError = 0;
    }
    
    public void fillErrorRatioArray() { // заполняет массив коэффициентов ошибок
        errorRatioArray[0] = ratioCalc( getBitError(), getFileSizeInBits()); // по битам
        System.out.println("По битам " + errorRatioArray[0]);
        errorRatioArray[1] = ratioCalc( getByteError(), getFileSize() ); // по битам
        System.out.println("По байтам " + errorRatioArray[1]);
        errorRatioArray[2] = ratioCalc( getBlockError(), getFileSizeInBlocks()); // по битам
        System.out.println("По блокам " + errorRatioArray[2]);
    }
    public double[] getErrorRatioArray() {
        return errorRatioArray;
    }
    
    //  Метод копирует файл с заданной вероятностью ошибки по битам
    public void copy(){
        final File open = getOpenPath();
        final File save = getSavePath();
        final double probability = getProbability();
        resetErrors(); 
        
        
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
                        Logger.getLogger(ConfigGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        out = new FileOutputStream(save); 
                    } catch (FileNotFoundException ex) {
                        System.out.println("Сохраняемый файл не найден");
                        Logger.getLogger(ConfigGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int c;

                    try { // Noise adding 
                        Integer[] data = new Integer[6];
                        data[0] = 0; // количество считанных байт
                        data[1] = 0; // количество ошибочных бит
                        data[2] = 0; // количество ошибочных байт
                        data[3] = 0; // количество ошибочных блоков
                        data[4] = 0; // количество считанных блоков
                        data[5] = 0; // количество считанных байт
                             
                        int currentBlockCount = 0; // текущее количество считанных блоков
                        int blockSize = getBlockSize();  // размер блока в байтах
                        int bytes = 0;  // количество считанных байт

                        for(int bytesInBlockCounter = 0; (c = in.read()) != -1; ) {    // считывание файла. (условие завершения цикла - достижение конца файла)
                            bytes++; // количество считанных байт
                            bytesInBlockCounter++; 
                            int result = mask(probability, c); // result - 8 бит файла с внесенными ошибками
                            out.write(result); // запись на диск                         
                            if( (bytesInBlockCounter == blockSize) || (getFileSize() == bytes - 1) ) { // при прочтении блока из заданного числа байт проверить
                                                       //его на ошибки и обнулить локальный счетчик ошибок (то же при достижении конца файла,
                                                        // когда последний блок не полный )
                                if ( getByteErrorFlag() ){
                                    setBlockError( 1 );
                                    setBlockErrorFlag( false ); // обнулить флаг ошибки в блоке
                                }
                                currentBlockCount++; // инкремент количества блоков
                                bytesInBlockCounter = 0; // сбросить счетчик байт в блоке
                            }
                            data[0] = getPercent( bytes + 1 ); 
                            data[1] = getBitError();
                            data[2] = getByteError();
                            data[3] = getBlockError();
                            data[4] = bytes;
                            data[5] = currentBlockCount;
                            
                            publish(data);
                        }
                        System.out.println("Количество ошибок:");
                        System.out.println("Бит "+getBitError());
                        System.out.println("Байт "+getByteError());
                        System.out.println("Блоки "+getBlockError());
                        
                    } catch (IOException ex) {
                        System.out.println("Ошибка в наложении маски");
                        Logger.getLogger(ConfigGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ConfigGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ConfigGUI.class.getName()).log(Level.SEVERE, null, ex);
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
                rosAdGUI.setProgress(value[0]);  // обновление прогрессбара
                statisticsGUI.setValueButtomTable(value[1], value[2], value[3], value[4], value[5] ); // обновление количества ошибок в окне статистики
                
                
            }
        };
        worker.execute();
    }
    
    // метод суммирует по модулю 2 8 битную маску ошибок в битах с участком файла. Т.е. генерирует т.н. поток ошибок
    // входные данные: probaility - вероятность искажения бита; data - 8 битный участок файла.
    // выходные - 8 участок файла с искажениями
    // также метод ведет подсчет ошибок по битам и байтам, выставляет флаг для подсчета ошибок по блокам.
    public int mask(double probability, int data){
        int shiftVariable = 1;
        int tempMask = 0;
        int tempBitError = 0; // обнуление счетчика ошибочных бит 
        Random randomNumberGenerator = new Random();
        for(int i = 0; i != 8;  shiftVariable = shiftVariable << 1,i++) {
            //System.out.println("Цикл "+i);
            if(randomNumberGenerator.nextDouble() > probability){
                if( i == 0 ){ //если первая итерация 
                    //System.out.format("\n i = %d Накладываем маску %h ",i, tempMask );
                    tempMask = tempMask | shiftVariable; // побитовое сложение нулевой маски со сдвинутой
                    tempBitError++; // изменяем количество битовых ошибок
                   // System.out.format("на %h . получаем %h\n", shiftVariable,tempMask );
                } 
                else { // если цикл не первый
                    //System.out.format("i = %d Накладываем маску %h ",i, tempMask );
          
                    tempMask = tempMask | shiftVariable;
                    tempBitError++;
                    
                    //System.out.format("на %h . получаем %h\n", shiftVariable,tempMask );
                }
            }
            else {
                //System.out.println("Без ошибок ("+i+")");
            }
        }
        setBitError( tempBitError ); // обновление количества искаженных бит
        if ( tempBitError != 0){ // если были ошибки в бите, то они есть и в байте.
                setBlockErrorFlag( true ); //есть ошибка в байте. Используется в подсчетче ошибочных блоков.
                setByteError( 1 ); // обновление количества искаженных байт
        }
//        System.out.format("\n\n\n финальная маска = %h\n", tempMask );
//        System.out.format("\n\n\n файл = %h\n", data);
//        System.out.format("\n\n\n файлова маска = %h\n", data ^ tempMask);
//        System.out.println("-----------------------------------------------");
        return data ^ tempMask;
    }
    
    public void calculate() {
        double uSignal = getUsefulVoltage(); // максимальное напряжение полезного сигнала, В
        int b = getSpeed();                // скорость передачи, Бод
        double c = getNoiseVoltage(); // эффективное напряжение шума, В
        int f = getFrequency();          // тактовая частота генератора шума, кГц 
        
        double e = uSignal * uSignal / b; // энергия единичного сигнала
        final float deltaF = f * 1000 / 20; // полоса частот, воспроизводимая генератором шума
        double n = c * c / deltaF;
        
        double argument = sqrt( ((2 * e) / n ));
        double probability = 0.5 * (1 - erf( argument / sqrt(2)));
        System.out.println("e = "+e );
        System.out.println("deltaF = "+deltaF );
        System.out.println("n = "+n );
        System.out.println("probability = "+ probability );
        setProbability(probability);
    }
}