/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.ModelCliente;

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
            view.ViewClienteRota theViewClienteRota = new view.ViewClienteRota();
            controller.ControllerCliente theController = new controller.ControllerCliente(theView, modelCliente, modelEndereco, theViewClienteRota);
            try {
                theController.setFrameVisible();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            String[] colunas = new String[4];
            colunas[0] = "Código";
            colunas[1] = "Nome";
            colunas[2] = "Telefone";
            colunas[3] = "E-Mail";

            ArrayList<ModelCliente> clientesListModelCliente = null;

            try {
                clientesListModelCliente = modelCliente.getClientes();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            String[][] clientes = new String[clientesListModelCliente.size()][4];

            for (int i = 0; i < clientesListModelCliente.size(); i++) {
                clientes[i][0] = String.valueOf(clientesListModelCliente.get(i).getId());
                clientes[i][1] = clientesListModelCliente.get(i).getNome();
                clientes[i][2] = clientesListModelCliente.get(i).getTelefone();
                clientes[i][3] = clientesListModelCliente.get(i).getEmail();

            }

            TableModel tableModel = new DefaultTableModel(clientes, colunas);
            theView.setTableModel(tableModel);
        }
    }
}
