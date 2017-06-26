/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ControllerIngrediente;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pichau
 */
public class ModelIngrediente {
    
    private final dao.DaoIngrediente theDao = new dao.DaoIngrediente();
    int id;
    String nome, unidadePadrao;
    double quantidade;

    public ModelIngrediente(int id, String nome, String unidadePadrao, double quantidade) {
        this.id = id;
        this.nome = nome;
        this.unidadePadrao = unidadePadrao;
        this.quantidade = quantidade;
    }

    public ModelIngrediente() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadePadrao() {
        return unidadePadrao;
    }

    public void setUnidadePadrao(String unidadePadrao) {
        this.unidadePadrao = unidadePadrao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
    public void adicionarIngrediente(ModelIngrediente ingrediente) {
        try {
            theDao.adiciona(ingrediente);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControllerIngrediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int proximoCodigo() throws SQLException {
        int novoId = theDao.getCodigoAtual();
        return novoId;
    }
    
    public ArrayList<ModelIngrediente> getIngredientes() throws SQLException {
        return theDao.getIngredientes();
    }
    
    public ArrayList<ModelIngrediente> getIngredientes(String busca) throws SQLException {
        return theDao.getIngredientes(busca);
    }
    
    public ModelIngrediente getIngrediente(int busca) throws SQLException {
        return theDao.getIngrediente(busca);
    }
    
    public void excluir (int idIngrediente) throws SQLException, ParseException {
        theDao.exclui(idIngrediente);
    }
    
    public void alterar (ModelIngrediente ingrediente) throws SQLException, ParseException {
        theDao.altera(ingrediente);
    }
   
    
}
