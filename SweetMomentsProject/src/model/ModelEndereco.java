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
    int id, idCliente, numero;
    String descricao, logradouro, bairro, complemento, tipoLogradouro;
    Double custoEntrega, distanciaEntrega,tempoMedioParaEntrega;

    public ModelEndereco() {
    }
    
    public ModelEndereco(int id, int idCliente, int numero, Double tempoMedioParaEntrega, String descricao, String logradouro, String bairro, String complemento, String tipoLogradouro, Double custoEntrega, Double distanciaEntrega) {
        this.id = id;
        this.idCliente = idCliente;
        this.numero = numero;
        this.tempoMedioParaEntrega = tempoMedioParaEntrega;
        this.descricao = descricao;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.complemento = complemento;
        this.tipoLogradouro = tipoLogradouro;
        this.custoEntrega = custoEntrega;
        this.distanciaEntrega = distanciaEntrega;
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

    public Double getTempoMedioParaEntrega() {
        return tempoMedioParaEntrega;
    }

    public void setTempoMedioParaEntrega(Double tempoMedioParaEntrega) {
        this.tempoMedioParaEntrega = tempoMedioParaEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public Double getCustoEntrega() {
        return custoEntrega;
    }

    public void setCustoEntrega(Double custoEntrega) {
        this.custoEntrega = custoEntrega;
    }

    public Double getDistanciaEntrega() {
        return distanciaEntrega;
    }

    public void setDistanciaEntrega(Double distanciaEntrega) {
        this.distanciaEntrega = distanciaEntrega;
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
    
    public ArrayList<ModelEndereco> getEnderecos(ModelCliente cliente) throws SQLException {
        return theDao.getEnderecos(cliente);
    }
}
