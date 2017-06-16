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
import model.ModelCliente;
import model.ModelEndereco;

/**
 *
 * @author Pamella
 */
public class ControllerCliente {
    private view.ViewCliente theView;
    private model.ModelCliente modelCliente;
    private model.ModelEndereco modelEndereco;
    
    ArrayList<ModelEndereco> enderecos = new ArrayList<ModelEndereco>();
 //   ModelEndereco endereco[] = new (ModelEndereco[]);

    public ControllerCliente(view.ViewCliente theView, model.ModelCliente modelCliente, model.ModelEndereco modelEndereco) {
        this.theView = theView;
        this.modelCliente = modelCliente;
        this.modelEndereco = modelEndereco;
        this.theView.addClienteListener(new CadastrarListener());
        this.theView.novoEnderecoListener(new novoEnderecoListener());
    }

    public void setFrameVisible() throws SQLException {
        theView.setVisible(true);
        theView.setIdCliente(modelCliente.proximoCodigo());
    }
    
    class novoEnderecoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idCliente = Integer.parseInt(theView.getIdCliente());
            String nome = theView.getNome();
            String telefone = theView.getTelefone();
            modelCliente = new ModelCliente(idCliente, nome, telefone);
            
            int numero = Integer.parseInt(theView.getNumero());
            int tempoMedioParaEntrega = Integer.parseInt(theView.getEntrega());
            String descricao = theView.getDescricao();
            String rua = theView.getRua();
            String bairro = theView.getBairro();
            String complemento = theView.getComplemento();   
            int idEndereco = modelEndereco.proximoCodigo(modelCliente);
            ModelEndereco endereco = new ModelEndereco(idEndereco,idCliente,numero,tempoMedioParaEntrega,descricao,rua,bairro,complemento);
            enderecos.add(endereco);
// modelEndereco.adicionarEndereco(new ModelEndereco(idEndereco,idCliente,numero,tempoMedioParaEntrega,descricao,rua,bairro,complemento));
            //endereco.add();
        
     //       int idCliente = Integer.parseInt(theView.getIdCliente());
     //       String nome = theView.getNome();
     //       String telefone = theView.getTelefone();
     //       int numero = Integer.parseInt(theView.getNumero());
     //       int tempoMedioParaEntrega = Integer.parseInt(theView.getEntrega());
     //       String descricao = theView.getDescricao();
     //       String rua = theView.getRua();
     //       String bairro = theView.getBairro();
     //       String complemento = theView.getComplemento();   
     //       int idEndereco = Integer.parseInt(theView.getIdEndereco());
     //       ModelCliente cliente = new ModelCliente(idCliente, nome, telefone);
     //       modelCliente.adicionarCliente(cliente);
     //       theView.setIdEndereco(modelEndereco.proximoCodigo(cliente));
     //       modelEndereco.adicionarEndereco(new ModelEndereco(idEndereco,idCliente,numero,tempoMedioParaEntrega,descricao,rua,bairro,complemento));
        }
    }

    class CadastrarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        //    int idCliente = Integer.parseInt(theView.getIdCliente());
        //    String nome = theView.getNome();
        //    String telefone = theView.getTelefone();
            
            modelCliente.adicionarCliente(modelCliente);
            modelEndereco.adicionarEndereco(enderecos);
     //       int numero = Integer.parseInt(theView.getNumero());
     //       int tempoMedioParaEntrega = Integer.parseInt(theView.getEntrega());
     //       String descricao = theView.getDescricao();
     //       String rua = theView.getRua();
     //       String bairro = theView.getBairro();
     //       String complemento = theView.getComplemento();   
     //       int idEndereco = Integer.parseInt(theView.getIdEndereco());
     //       ModelCliente cliente = new ModelCliente(idCliente, nome, telefone);
     //       modelCliente.adicionarCliente(cliente);
     //       theView.setIdEndereco(modelEndereco.proximoCodigo(cliente));
     //       modelEndereco.adicionarEndereco(new ModelEndereco(idEndereco,idCliente,numero,tempoMedioParaEntrega,descricao,rua,bairro,complemento));
        }
    }
}
