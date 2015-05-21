package RosAdProgram;

import RosAdProgram.model.Model;
import java.io.IOException; 
import RosAdProgram.view.MainGUI;
import RosAdProgram.view.ConfigGUI;
import RosAdProgram.Controller.Controller;
import RosAdProgram.view.StatisticsGUI;

/**
 *
 * @author Вадим
 */
public class ProgrammTest {
   public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfigGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                runApp();
            }

            private void runApp() {
                 Model model = new Model(); // в объекте model содератся данные о конфигурации стенда, а также описано его поведение
                 StatisticsGUI statisticsGUI = new StatisticsGUI(model);
                 ConfigGUI configGUI = new ConfigGUI(model);
                 MainGUI mainGui = new MainGUI(model,configGUI, statisticsGUI); // главно окно (со структурной схемой)
                 
                 Controller controller = new Controller(model, mainGui, configGUI, statisticsGUI); // в объекте controller хранятся методы обработки действий пользователя
                 StatisticsGUI statistics = new StatisticsGUI(model);
                 mainGui.setVisible(true);  
                 
            }
        });
    } 
}
