/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RosAdProgram.view;
import RosAdProgram.model.Model;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Вадим
 */
public class RosAdGUI extends javax.swing.JFrame {

    /**
     * Creates new form RosAdGUI
     */
    private final Model model;
    
    public RosAdGUI(Model model) {
        this.model = model;
        initComponents();
        UIManager.put("nimbusOrange", new Color(29, 72, 242)); //RGB цвет прогресбара 
        model.setRosAdGUIreference( this ); // при создании объекта RosADGUI в Model записывается его ссылка.
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        startButton = new javax.swing.JButton();
        MistakeQuantityText = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        blockTransmitTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        blockRecivedTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        bigTable = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        startButton.setText("Связь");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        MistakeQuantityText.setText("Количество ошибочных блоков");

        progressBar.setBackground(new java.awt.Color(0, 255, 0));
        progressBar.setForeground(java.awt.Color.orange);
        progressBar.setToolTipText("");
        progressBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        progressBar.setStringPainted(true);

        blockTransmitTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Правильных", null},
                {"Ошибочных", null},
                {null, null}
            },
            new String [] {
                "", "Передано блоков"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        blockTransmitTable.setShowHorizontalLines(true);
        blockTransmitTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(blockTransmitTable);
        if (blockTransmitTable.getColumnModel().getColumnCount() > 0) {
            blockTransmitTable.getColumnModel().getColumn(0).setResizable(false);
            blockTransmitTable.getColumnModel().getColumn(1).setResizable(false);
        }

        blockRecivedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Правильных", null},
                {"Ошибочных", null},
                {null, null}
            },
            new String [] {
                "", "Принято блоков"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        blockRecivedTable.setShowHorizontalLines(true);
        blockRecivedTable.setShowVerticalLines(true);
        jScrollPane2.setViewportView(blockRecivedTable);
        if (blockRecivedTable.getColumnModel().getColumnCount() > 0) {
            blockRecivedTable.getColumnModel().getColumn(0).setResizable(false);
            blockRecivedTable.getColumnModel().getColumn(1).setResizable(false);
        }

        bigTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Всего", null, null, null},
                {"Обнаружено ошибок", null, null, null},
                {"Необнаружено ошибок", null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Биты", "Байты", "Блоки"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bigTable.setShowHorizontalLines(true);
        bigTable.setShowVerticalLines(true);
        jScrollPane4.setViewportView(bigTable);
        if (bigTable.getColumnModel().getColumnCount() > 0) {
            bigTable.getColumnModel().getColumn(0).setResizable(false);
            bigTable.getColumnModel().getColumn(0).setHeaderValue("");
            bigTable.getColumnModel().getColumn(1).setResizable(false);
            bigTable.getColumnModel().getColumn(1).setHeaderValue("Биты");
            bigTable.getColumnModel().getColumn(2).setResizable(false);
            bigTable.getColumnModel().getColumn(2).setHeaderValue("Байты");
            bigTable.getColumnModel().getColumn(3).setResizable(false);
            bigTable.getColumnModel().getColumn(3).setHeaderValue("Блоки");
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(MistakeQuantityText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startButton)
                .addGap(109, 109, 109))
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startButton)
                    .addComponent(MistakeQuantityText))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        model.copy();
    }//GEN-LAST:event_startButtonActionPerformed

   
  
    
//    public void setValueBigTable(){
//        DefaultTableModel tableModel = (DefaultTableModel) bigTable.getModel();
//        tableModel.setValueAt(allBit, 0, 1);
//        tableModel.setValueAt(allByte, 0, 2);
//        tableModel.setValueAt(allBlock, 0, 3);
//        tableModel.setValueAt(detectedBit, 1, 1);
//        tableModel.setValueAt(detectedByte, 1, 2);
//        tableModel.setValueAt(detetedBlock, 1, 3);
//        tableModel.setValueAt(undetectedBit, 2, 1);
//        tableModel.setValueAt(undetectedByte, 2, 2);
//        tableModel.setValueAt(undetectedBlock, 2, 3);   
//    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MistakeQuantityText;
    private javax.swing.JTable bigTable;
    private javax.swing.JTable blockRecivedTable;
    private javax.swing.JTable blockTransmitTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}
