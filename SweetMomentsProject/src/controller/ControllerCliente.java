/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import maps.java.StaticMaps;
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
    private StaticMaps ObjStaticMaps = new StaticMaps();

    ArrayList<ModelEndereco> enderecos = new ArrayList<ModelEndereco>();

    public ControllerCliente(view.ViewCliente theView, model.ModelCliente modelCliente, model.ModelEndereco modelEndereco) {
        this.theView = theView;
        this.modelCliente = modelCliente;
        this.modelEndereco = modelEndereco;
        this.theView.salvarCliente(new CadastrarListener());
        this.theView.salvarEnderecoListener(new salvarEnderecoListener());
        this.theView.jTextFieldComplementoListener(new ComplementoListener());
    }

    public void setFrameVisible() throws SQLException {
        theView.setVisible(true);
        theView.setIdCliente(modelCliente.proximoCodigo());
    }

    class salvarEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int idCliente = Integer.parseInt(theView.getIdCliente());
            String nome = theView.getNome();
            String telefone = theView.getTelefone();
            modelCliente = new ModelCliente(idCliente, nome, telefone);
            int numero = Integer.parseInt(theView.getNumero());
            int tempoMedioParaEntrega = Integer.parseInt(theView.getTempoEntrega());
            String descricao = theView.getDescricao();
            String rua = theView.getLogradouro();
            String bairro = theView.getBairro();
            String complemento = theView.getComplemento();
            int idEndereco = modelEndereco.proximoCodigo(modelCliente);
            ModelEndereco endereco = new ModelEndereco(idEndereco, idCliente, numero, tempoMedioParaEntrega, descricao, rua, bairro, complemento);
            enderecos.add(endereco);
            theView.enabledSalvarTelaCadastro(true);

        }
    }

    class CadastrarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCliente.adicionarCliente(modelCliente);
            modelEndereco.adicionarEndereco(enderecos);
        }
    }

    class ComplementoListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            //mapa
            String busca = theView.getTipoLogradouro() + " " + theView.getLogradouro() + ", " + theView.getNumero() + " - " + theView.getBairro();
            if (!theView.getLabelMapa().isEmpty()) {
                theView.setLabelMapa("");
            }
            Image imagenMapa = null;
            try {
                imagenMapa = ObjStaticMaps.getStaticMap(busca, 14, new Dimension(500, 500), 1, StaticMaps.Format.png, StaticMaps.Maptype.roadmap);
            } catch (MalformedURLException | UnsupportedEncodingException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (imagenMapa != null) {
                ImageIcon imgIcon = new ImageIcon(imagenMapa);
                Icon iconImage = (Icon) imgIcon;
                theView.setLabelMapa(iconImage);
            }

        }

        @Override
        public void focusLost(FocusEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
