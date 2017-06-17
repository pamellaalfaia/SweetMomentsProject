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
        try {
//            Desktop.getDesktop().browse(new URI("https://maps.google.es/maps?q=Espa%C3%B1a&output=embed"));
        } catch (Exception ex) {
        }
        for(UIManager.LookAndFeelInfo laf:UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(laf.getName()))
                try {
                UIManager.setLookAndFeel(laf.getClassName());
            } catch (Exception ex) {
            }
        }
     //   view.ViewCliente mainF=new view.ViewCliente();
     //   Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
     //   mainF.setLocation(d.width-(mainF.getWidth()+50), 50);
     //   mainF.setSize(550, 630);
     //   mainF.setVisible(true);
    }
    
}
