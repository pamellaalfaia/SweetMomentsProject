/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pamella
 */
public class ControllerMenu {
    private final view.ViewMenu viewMenu;

    public ControllerMenu(view.ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.viewMenu.clientesListener(new ClientesListener());
    }

    public void setFrameVisible() {
        viewMenu.setVisible(true);
    }

    class ClientesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.ViewCliente theView = new view.ViewCliente();
            model.ModelCliente modelCliente = new model.ModelCliente();
            model.ModelEndereco modelEndereco = new model.ModelEndereco();
            controller.ControllerCliente theController = new controller.ControllerCliente(theView, modelCliente, modelEndereco);
            try {
                theController.setFrameVisible();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
