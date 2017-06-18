/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sweetmomentsproject;

import javax.swing.UIManager;

/**
 *
 * @author Pamella
 */
public class SweetMomentsProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        view.ViewMenu viewMenu = new view.ViewMenu();
        controller.ControllerMenu theController = new controller.ControllerMenu(viewMenu);
        theController.setFrameVisible();

        //mapa
        for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())) {
                try {
                    UIManager.setLookAndFeel(laf.getClassName());
                } catch (Exception ex) {
                }
            }
        }
    }

}
