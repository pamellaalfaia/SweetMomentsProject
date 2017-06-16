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
public class ModelEndereco {
    private final dao.DaoEndereco theDao = new dao.DaoEndereco();
    int id, idCliente, numero, tempoMedioParaEntrega;
    String descricao, rua, bairro, complemento;
    ArrayList<ModelEndereco> enderecos = new ArrayList<ModelEndereco>();

    public ModelEndereco() {
    }
    
    public ModelEndereco(int id, int idCliente, int numero, int tempoMedioParaEntrega, String descricao, String rua, String bairro, String complemento) {
        this.id = id;
        this.idCliente = idCliente;
        this.numero = numero;
        this.tempoMedioParaEntrega = tempoMedioParaEntrega;
        this.descricao = descricao;
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTempoMedioParaEntrega() {
        return tempoMedioParaEntrega;
    }

    public void setTempoMedioParaEntrega(int tempoMedioParaEntrega) {
        this.tempoMedioParaEntrega = tempoMedioParaEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public ArrayList<ModelEndereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<ModelEndereco> enderecos) {
        this.enderecos = enderecos;
    }
        
    public void adicionarEndereco(ArrayList<ModelEndereco> enderecos) {
        try {
            theDao.adiciona(enderecos);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int proximoCodigo(ModelCliente cliente) {
        int NovoId = 0;
        try {
            NovoId = theDao.getCodigoAtual(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NovoId;
    }
}
