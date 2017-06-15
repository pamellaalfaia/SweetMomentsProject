/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ControllerCliente;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pamella
 */
public class ModelCliente {
    private final dao.DaoCliente theDao = new dao.DaoCliente();
    int id;
    String nome, telefone;

    public ModelCliente() {
    }

    public ModelCliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
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
}
