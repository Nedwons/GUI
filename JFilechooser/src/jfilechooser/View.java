/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfilechooser;

import java.io.File;
import javax.swing.*;


/**
 *
 * @author Вадим
 */
public class View extends javax.swing.JFrame {

    public View() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fc = new javax.swing.JFileChooser();
        OpenButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        MistakeProbability = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OpenButton.setText("Open");
        OpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        MistakeProbability.setText("0.1");
        MistakeProbability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MistakeProbabilityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(OpenButton)
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MistakeProbability)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77)
                .addComponent(SaveButton)
                .addGap(55, 55, 55))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 210, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 210, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(MistakeProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OpenButton)
                    .addComponent(SaveButton)
                    .addComponent(startButton))
                .addGap(39, 39, 39))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 135, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(0, 136, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    Controller controller = new Controller();
    private File openPath, savePath; // contain path to the files
    private float mistakeProbability;
    
    // File Getters and Setters
    private void setOpenPath (File openPath) {  
        this.openPath = openPath;
    }
    private void setSavePath (File savePath) {
        this.savePath = savePath;
    }
    public File getOpenPath () {  
        return this.openPath;
    }
    public File getSavePath () {  
        return this.savePath;
    }
    
    // Getter и Setter добавления ошибок
    public void setProbability (float prbability) {
        this.mistakeProbability = (1 - prbability); 
    }
    public float getProbability () {
        System.out.format("Вероятность = %f\n", mistakeProbability);
        return mistakeProbability;
    }
      
    
    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
                                                                                    //event holding
        int o = fc.showOpenDialog(this); /** Открывает окно выбора
         * пути. return APPROVE_OPTION if the user approved the operation and
         * CANCEL_OPTION if the user cancelled it **/
        if (o == JFileChooser.APPROVE_OPTION) { // if "ok" is pressed
            setOpenPath( fc.getSelectedFile() ); /** returns selected file
                                                 and set to openPath varable;                                                 and set to openPath varable;
*/
        }
    }//GEN-LAST:event_OpenButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
         int s = fc.showSaveDialog(this);
         if (s == JFileChooser.APPROVE_OPTION) { // if "ok" is pressed
            setSavePath( fc.getSelectedFile() ); /** returns selected file
             *                                         and store to savePath var
             */
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
       
        controller.copy(getOpenPath(), getSavePath(), getProbability());
    
    }//GEN-LAST:event_startButtonActionPerformed

    // Ввод вероятности ошибки
    private void MistakeProbabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MistakeProbabilityActionPerformed
        
        String stringInput = MistakeProbability.getText(); // 
        float numberInput = Float.valueOf(stringInput); // converts srting to number
        setProbability(numberInput);
   
    }//GEN-LAST:event_MistakeProbabilityActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField MistakeProbability;
    private javax.swing.JButton OpenButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JFileChooser fc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

}

