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
import model.ModelIngrediente;

/**
 *
 * @author Pamella
 */
public class ControllerMenu {

    private final view.ViewMenu viewMenu;

    public ControllerMenu(view.ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.viewMenu.clientesListener(new ClientesListener());
        this.viewMenu.ingredientesListener(new IngredientesListener());
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
    
    class IngredientesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.ViewIngrediente theView = new view.ViewIngrediente();
            model.ModelIngrediente modelIngrediente = new model.ModelIngrediente();
            model.ModelMedida modelMedida = new model.ModelMedida();
            controller.ControllerIngrediente theController = new controller.ControllerIngrediente(theView, modelIngrediente, modelMedida);
            try {
                theController.setFrameVisible();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            String[] colunas = new String[4];
            colunas[0] = "Código";
            colunas[1] = "Nome";
            colunas[2] = "Unidade Padrão";
            colunas[3] = "Quantidade";

            ArrayList<ModelIngrediente> ingredientesListModelCliente = null;

            try {
                ingredientesListModelCliente = modelIngrediente.getIngredientes();
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMenu.class.getName()).log(Level.SEVERE, null, ex);
            }

            String[][] ingredientes = new String[ingredientesListModelCliente.size()][4];

            for (int i = 0; i < ingredientesListModelCliente.size(); i++) {
                ingredientes[i][0] = String.valueOf(ingredientesListModelCliente.get(i).getId());
                ingredientes[i][1] = ingredientesListModelCliente.get(i).getNome();
                ingredientes[i][2] = ingredientesListModelCliente.get(i).getUnidadePadrao();
                ingredientes[i][3] = String.valueOf(ingredientesListModelCliente.get(i).getQuantidade());

            }

            TableModel tableModel = new DefaultTableModel(ingredientes, colunas);
            theView.setTableModel(tableModel);
        }
    }
}
