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
public class ModelMedida {
    private final dao.DaoMedida theDao = new dao.DaoMedida();
    int id, idIngrediente;
    double quantidade;
    String nome, unidadeConversao;

    public ModelMedida(int idMedida, int idIngrediente, double quantidade, String nome, String unidadeConversao) {
        this.id = idMedida;
        this.idIngrediente = idIngrediente;
        this.quantidade = quantidade;
        this.nome = nome;
        this.unidadeConversao = unidadeConversao;
    }

    public ModelMedida() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int idMedida) {
        this.id = idMedida;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeConversao() {
        return unidadeConversao;
    }

    public void setUnidadeConversao(String unidadeConversao) {
        this.unidadeConversao = unidadeConversao;
    }
    
    public void adicionarMedida(ModelMedida medida) {
        try {
            theDao.adiciona(medida);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControllerIngrediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void adicionarMedida(ArrayList<ModelMedida> medidas) {
        try {
            theDao.adiciona(medidas);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControllerIngrediente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int proximoCodigo(ModelIngrediente ingrediente) {
        int NovoId = 0;
        try {
            NovoId = theDao.getCodigoAtual(ingrediente);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerIngrediente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NovoId;
    }
    
    public ArrayList<ModelMedida> getMedidas(ModelIngrediente ingrediente) throws SQLException {
        return theDao.getMedidas(ingrediente);
    }
    
    public void excluir (int idIngrediente) throws SQLException, ParseException {
        theDao.exclui(idIngrediente);
    }
    
    public void excluir (int idIngrediente, int idMedida) throws SQLException, ParseException {
        theDao.exclui(idIngrediente,idMedida);
    }
    
    public void alterar (ModelIngrediente ingrediente, ModelMedida medida) throws SQLException, ParseException {
        theDao.altera(medida,ingrediente);
    }
    
    public int verificaExistenciaMedida(int idMedida, int idIngrediente) throws SQLException {
        return theDao.verificaExistenciaMedida(idMedida, idIngrediente);
    }
}
