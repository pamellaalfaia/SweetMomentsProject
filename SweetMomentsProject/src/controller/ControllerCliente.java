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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import maps.java.Route;
import maps.java.StaticMaps;
import model.ModelCliente;
import model.ModelEndereco;
import org.jsoup.Jsoup;

/**
 *
 * @author Pamella
 */
public class ControllerCliente {

    private view.ViewCliente theView;
    private model.ModelCliente modelCliente;
    private model.ModelEndereco modelEndereco;
    private StaticMaps ObjStaticMaps = new StaticMaps();
    private Route ObjRoute = new Route();
    private view.ViewClienteRota theViewRota;
    

    ArrayList<ModelEndereco> enderecos = new ArrayList<ModelEndereco>();

    public ControllerCliente(view.ViewCliente theView, model.ModelCliente modelCliente, model.ModelEndereco modelEndereco, view.ViewClienteRota theViewRota) {
        this.theView = theView;
        this.modelCliente = modelCliente;
        this.modelEndereco = modelEndereco;
        this.theViewRota = theViewRota;
        this.theView.salvarCliente(new CadastrarListener());
        this.theView.salvarEnderecoListener(new SalvarEnderecoListener());
        this.theView.jTextFieldComplementoListener(new ComplementoListener());
        this.theView.exibirRotaListener(new ExibirRotaListener());
    }

    public void setFrameVisible() throws SQLException {
        theView.setVisible(true);
        theView.setIdCliente(modelCliente.proximoCodigo());
    }

    class SalvarEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //capturando os valores inseridos para cliente e instanciando objeto cliente
            int idCliente = Integer.parseInt(theView.getIdCliente());
            String nome = theView.getNome();
            String telefone = theView.getTelefone();
            String email = theView.getEmail();
            modelCliente = new ModelCliente(idCliente, nome, telefone, email);

            //aapturando os valores inseridos para endereço e instanciando objeto endereço
            int numero = Integer.parseInt(theView.getNumero());
            int tempoMedioParaEntrega = Integer.parseInt(theView.getTempoEntrega());
            String descricao = theView.getDescricao();
            String logradouro = theView.getLogradouro();
            String bairro = theView.getBairro();
            String complemento = theView.getComplemento();
            String tipoLogradouro = theView.getTipoLogradouro();
            Double distanciaEntrega = Double.parseDouble(theView.getDistanciaEntrega());
            Double custoEntrega = Double.parseDouble(theView.getCustoEntrega());
            int idEndereco = modelEndereco.proximoCodigo(modelCliente);
            ModelEndereco endereco = new ModelEndereco(idEndereco, idCliente, numero, tempoMedioParaEntrega, descricao, logradouro, bairro, complemento, tipoLogradouro, custoEntrega, distanciaEntrega);

            //incluindo objeto endereço no arraylist que será salvo no banco
            enderecos.add(endereco);

            //habilitando botões
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
    
    class ExibirRotaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String busca = theView.getTipoLogradouro() + " " + theView.getLogradouro() + ", " + theView.getNumero() + " - " + theView.getBairro();
            String[][] arrayRoute = null;
            //montar rota
            try {
                arrayRoute = ObjRoute.getRoute("Rua Luiz Paulistano, 51", busca, null, Boolean.TRUE, Route.mode.driving, Route.avoids.nothing);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            String[] columnas = new String[3];
            columnas[0]="Indicação";columnas[1]="Distância";columnas[2]="Duração";
            for (int i = 0; i < arrayRoute.length; i++) { 
                try {
                arrayRoute[i][0] = Jsoup.parse(arrayRoute[i][0]).text();
                } catch (Exception ex) {
            }
            }
            TableModel tableModel = new DefaultTableModel(arrayRoute, columnas);
            theViewRota.setTableModel(tableModel);
            
            theViewRota.setVisible(true);
        }
    }

    class ComplementoListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {

            String busca = theView.getTipoLogradouro() + " " + theView.getLogradouro() + ", " + theView.getNumero() + " - " + theView.getBairro();
            String[][] arrayRoute = null;
            //montar rota
            try {
                arrayRoute = ObjRoute.getRoute("Rua Luiz Paulistano, 51", busca, null, Boolean.TRUE, Route.mode.driving, Route.avoids.nothing);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //setar mapa
            
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

            theView.enabledRota(true);

            ArrayList<Integer> tiempoTotal = ObjRoute.getTotalTime();
            int tiempoAux = 0;
            for (Integer item : tiempoTotal) {
                tiempoAux += item;
            }
            ArrayList<Integer> distanciaTotal = ObjRoute.getTotalDistance();
            int distanciaAux = 0;
            for (Integer item : distanciaTotal) {
                distanciaAux += item;
            }
            Double tiempo = (double) tiempoAux;
            tiempo = (tiempo / 60) ;

            tiempo = arredondamento(tiempo);
            Double distancia = (double) (distanciaAux);
            distancia = distancia / 1000;

            //  distancia/quantidade de quilometros que 1L roda * preço da gasolina
            theView.setCustoEntrega((distancia/16) * 4);
            theView.setTempoEntrega(String.valueOf(tiempo));
            theView.setDistanciaEntrega(String.valueOf(distancia));
        }

        @Override
        public void focusLost(FocusEvent e) {
            
        }
    }

    Double arredondamento(Double d) {
        return Math.rint(d * 1000) / 1000;
    }
}
