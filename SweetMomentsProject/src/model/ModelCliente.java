/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ControllerCliente;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pamella
 */
public class ModelCliente {
    private final dao.DaoCliente theDao = new dao.DaoCliente();
    int id;
    String nome, telefone, email;

    public ModelCliente() {
    }

    public ModelCliente(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    
    public int getId() {
        return id;
    }

    public void setNome(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void adicionarCliente(ModelCliente cliente) {
        try {
            theDao.adiciona(cliente);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int proximoCodigo() throws SQLException {
        int novoId = theDao.getCodigoAtual();
        return novoId;
    }
    
    public ArrayList<ModelCliente> getClientes() throws SQLException {
        return theDao.getClientes();
    }
    
    public ArrayList<ModelCliente> getClientes(String busca) throws SQLException {
        return theDao.getClientes(busca);
    }
    
    public ModelCliente getCliente(int busca) throws SQLException {
        return theDao.getCliente(busca);
    }
    
    public void excluir (int idCliente) throws SQLException, ParseException {
        theDao.exclui(idCliente);
    }
    
    public void alterar (ModelCliente cliente) throws SQLException, ParseException {
        theDao.altera(cliente);
    }
}
