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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

    int paginaMax = 1;
    int idEndereco = 0;

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
        this.theView.novoEnderecoListener(new NovoEnderecoListener());
        this.theView.enderecoAnteriorListener(new EnderecoAnteriorListener());
        this.theView.proximoEnderecoListener(new ProximoEnderecoListener());
        this.theViewRota.voltarDaRotaListener(new VoltarDaTelaDeRotaListener());
    }

    public void setFrameVisible() throws SQLException {
        theView.setVisible(true);
        theView.setIdCliente(modelCliente.proximoCodigo());
    }

    class VoltarDaTelaDeRotaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theViewRota.setVisible(false);
        }
        
    }
    
    class NovoEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            limpaCamposEndereco();
            enabledCamposEndereco(true);

            //pagina atual
            int paginaAtual = Integer.parseInt(theView.getPaginaEndereco());
            //próxima página
            theView.setPaginaEndereco(paginaAtual + 1);

            //habilita botões
            theView.enabledEnderecoAnterior(true);
            theView.enabledSalvarEndereco(true);

            //desabilita botões
            theView.enabledEditar(false);
            theView.enabledExcluir(false);
            theView.enabledNovoEndereco(false);
            theView.enabledProximoEndereco(false);

            //atualiza variável global paginaMax
            if (paginaMax < paginaAtual) {
                paginaMax = paginaAtual;
            }
            
        }
    }

    class EnderecoAnteriorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int pagina = Integer.parseInt(theView.getPaginaEndereco()) - 1;

            //exibe na negação que fomos para a página anterior
            theView.setPaginaEndereco(pagina);

            //recuperando dados do endereco
            recuperaDadosDeEndereco(pagina);

            //habilita botão de página posterior
            theView.enabledProximoEndereco(true);

            //impede que o botão de anterior esteja habilitado quando o usuário já chegou na primeira página
            if (pagina == 1) {
                theView.enabledEnderecoAnterior(false);
            }

            //desabilitando botões
            theView.enabledSalvarEndereco(false);
        }
    }

    class ProximoEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //pagina
            int pagina = Integer.parseInt(theView.getPaginaEndereco()) + 1;

            //mostra que foi para a próxima página
            theView.setPaginaEndereco(pagina);

            //recuperando dados do endereco
            recuperaDadosDeEndereco(pagina);

            //habilita botão de página anterior
            theView.enabledEnderecoAnterior(true);

            //impede que o botão de próximo esteja habilitado quando o usuário já chegou a última página
            if (pagina == paginaMax) {
                theView.enabledProximoEndereco(false);
            }
        }
    }

    class SalvarEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //captura os valores inseridos para cliente e instanciando objeto cliente
            int idCliente = Integer.parseInt(theView.getIdCliente());
            String nome = theView.getNome();
            String telefone = theView.getTelefone();
            String email = theView.getEmail();
            modelCliente = new ModelCliente(idCliente, nome, telefone, email);

            //captura os valores inseridos para endereço
            int numero = Integer.parseInt(theView.getNumero());
            Double tempoMedioParaEntrega = Double.parseDouble(theView.getTempoEntrega());
            String descricao = theView.getDescricao();
            String logradouro = theView.getLogradouro();
            String bairro = theView.getBairro();
            String complemento = theView.getComplemento();
            String tipoLogradouro = theView.getTipoLogradouro();
            Double distanciaEntrega = Double.parseDouble(theView.getDistanciaEntrega());
            Double custoEntrega = Double.parseDouble(theView.getCustoEntrega());

            //proximoCodigo(modelCliente) retorna o max(idEndereco) que tem na tabela de endereco para aquele cliente
            int idEnderecoBanco = modelEndereco.proximoCodigo(modelCliente);

            //se não houver endereço nenhum, vou usar a varíavel local, senão vou usar a do banco + 1
            if (idEnderecoBanco == 0) {
                idEndereco++;
            } else {
                idEndereco = idEnderecoBanco + 1;
            }

            //instancia objeto endereco
            ModelEndereco endereco = new ModelEndereco(idEndereco, idCliente, numero, tempoMedioParaEntrega, descricao, logradouro, bairro, complemento, tipoLogradouro, custoEntrega, distanciaEntrega);

            //inclui objeto endereço no arraylist que será salvo no banco
            enderecos.add(endereco);

            //pega a página que está sendo exibida na tela
            int paginaAtual = Integer.parseInt(theView.getPaginaEndereco());
            //atualiza variável global paginaMax
            if (paginaMax < paginaAtual) {
                paginaMax = paginaAtual;
            }

            //habilitando botões
            theView.enabledSalvarTelaCadastro(true);
            theView.enabledNovoEndereco(true);
            theView.enabledEditar(true);
            theView.enabledExcluir(true);

            //desabilita botão salvarEndereco
            theView.enabledSalvarEndereco(false);
            
            //desabilita campos de endereço
            enabledCamposEndereco(false);

        }
    }

    class CadastrarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //envia objeto cliente para o registro
            modelCliente.adicionarCliente(modelCliente);

            //envia objeto endereco para o registro
            modelEndereco.adicionarEndereco(enderecos);
        }
    }

    class ExibirRotaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String tipoLogradouro = theView.getTipoLogradouro();
            String logradouro = theView.getLogradouro();
            String numero = theView.getNumero();
            String bairro = theView.getBairro();
            
            String[][] arrayRoute = montaRota(tipoLogradouro, logradouro, numero, bairro);

            String[] columnas = new String[3];
            columnas[0] = "Indicação";
            columnas[1] = "Distância";
            columnas[2] = "Duração";
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

            //captura informações necessárias para o mapa
            String tipoLogradouro = theView.getTipoLogradouro(); 
            String logradouro = theView.getLogradouro();
            String numero = theView.getNumero();
            String bairro = theView.getBairro();

            montaRota(tipoLogradouro, logradouro, numero, bairro);

            montaMapa(tipoLogradouro, logradouro, numero, bairro);

            //habilita botão de visualizarRota
            theView.enabledRota(true);

            //tempoTotal
            String tempo = arredondamento(calculaTempoTotal());

            //distanciaTotal
            String distancia = arredondamento(calculaDistanciaTotal());
            Double distanciaDouble = calculaDistanciaTotal();
            
            //setando valores calculados
            theView.setTempoEntrega(tempo);
            theView.setDistanciaEntrega(distancia);
            
            //  custo = (distancia)/(quantidade de quilometros que consegue rodar com 1L)*(preço da gasolina)
            Double custo = (distanciaDouble / 16) * 4;
            theView.setCustoEntrega(arredondamento(custo));
        }

        @Override
        public void focusLost(FocusEvent e) {
        }
    }

    String arredondamento(Double d) {
        BigDecimal bd = new BigDecimal(d).setScale(2, RoundingMode.HALF_EVEN);
        return String.valueOf(bd);
    }

    private void limpaCamposEndereco() {
        //limpar campos de endereco
        theView.setDescricao("");
        theView.setTipoLogradouro("Aeroporto");
        theView.setLogradouro("");
        theView.setNumero("");
        theView.setBairro("");
        theView.setComplemento("");
        theView.setTempoEntrega("");
        theView.setDistanciaEntrega("");
        theView.setCustoEntrega("");
        theView.setLabelMapa("");
    }
    
    private void enabledCamposEndereco(boolean x) {
        //limpar campos de endereco
        theView.enabledDescricao(x);
        theView.enabledTipoLogradouro(x);
        theView.enabledLogradouro(x);
        theView.enabledNumero(x);
        theView.enabledBairro(x);
        theView.enabledComplemento(x);
    }

    private void recuperaDadosDeEndereco(int pagina) {
        //recuperando dados do endereco
        theView.setDescricao(enderecos.get(pagina - 1).getDescricao());
        theView.setTipoLogradouro(enderecos.get(pagina - 1).getTipoLogradouro());
        theView.setLogradouro(enderecos.get(pagina - 1).getLogradouro());
        theView.setNumero(String.valueOf(enderecos.get(pagina - 1).getNumero()));
        theView.setBairro(enderecos.get(pagina - 1).getBairro());
        theView.setComplemento(enderecos.get(pagina - 1).getComplemento());
        theView.setTempoEntrega(String.valueOf(enderecos.get(pagina - 1).getTempoMedioParaEntrega()));
        theView.setDistanciaEntrega(String.valueOf(enderecos.get(pagina - 1).getDistanciaEntrega()));
        theView.setCustoEntrega(String.valueOf(enderecos.get(pagina - 1).getCustoEntrega()));
        montaMapa(enderecos.get(pagina - 1).getTipoLogradouro(), 
                enderecos.get(pagina - 1).getLogradouro(), 
                String.valueOf(enderecos.get(pagina - 1).getNumero()), 
                enderecos.get(pagina - 1).getBairro());
    }

    private void montaMapa(String tipoLogradouro, String logradouro, String numero, String bairro) {
        String busca = tipoLogradouro + " "
                    + logradouro + ", "
                    + numero + " - "
                    + bairro;

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
    }

    private String[][] montaRota(String tipoLogradouro, String logradouro, String numero, String bairro) {
        String busca = tipoLogradouro + " "
                    + logradouro + ", "
                    + numero + " - "
                    + bairro;
        String[][] arrayRoute = null;
        //montar rota
        try {
            arrayRoute = ObjRoute.getRoute("Rua Luiz Paulistano, 51", busca, null, Boolean.TRUE, Route.mode.driving, Route.avoids.nothing);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayRoute;
    }

    private Double calculaDistanciaTotal() {
        ArrayList<Integer> distanciaTotal = ObjRoute.getTotalDistance();
        int distanciaAux = 0;
        for (Integer item : distanciaTotal) {
            distanciaAux += item;
        }
        Double distancia = (double) (distanciaAux);
        distancia = distancia / 1000;
        return distancia;
    }

    private Double calculaTempoTotal() {
        ArrayList<Integer> tempoTotal = ObjRoute.getTotalTime();
        int tempoAux = 0;
        for (Integer item : tempoTotal) {
            tempoAux += item;
        }
        Double tempo = (double) tempoAux;
        tempo = (tempo / 60);
        return tempo;
    }
}
