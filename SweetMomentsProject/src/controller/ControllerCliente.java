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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
    int idEndereco = 1;

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
        this.theView.jTextFieldPesquisaKeyListener(new PesquisarKeyListener());
        this.theView.jTextFieldPesquisaFocusListener(new PesquisarFocusListener());
        this.theView.tabelaClientesMouseListener(new TabelaClienteCliqueListener());
        this.theView.visualizarClienteListener(new VisualizarCadastroCliente());
        this.theView.excluirListener(new ExcluirEnderecoListener());
        this.theView.voltarDaBuscaListener(new VoltarTelaCliente());
        this.theView.voltarListener(new VoltarTelaCliente());
        this.theView.excluirClienteListener(new ExcluirClienteBotaoBusca());
        this.theView.editarListener(new AlterarEnderecoListener());
        this.theView.editarClienteListener(new AlterarClienteListener());
        this.theView.excluirClienteTelaCadastroListener(new ExcluirClienteTelaCadastro());
    }

    public void setFrameVisible() throws SQLException {
        theView.setVisible(true);
        theView.setIdCliente(modelCliente.proximoCodigo());
        theView.setCodigoEndereco(idEndereco);
    }

    class VoltarDaTelaDeRotaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theViewRota.setVisible(false);
        }

    }

    class VoltarTelaCliente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setVisible(false);
        }

    }

    class TabelaClienteCliqueListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            theView.enabledVisualizarCliente(true);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    class ExcluirClienteBotaoBusca implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(theView.getIDSelecaoTabelaClientes());

            try {
                modelCliente.excluir(id);
                modelEndereco.excluir(id);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            atualizaTabelaDeClientes("");
            theView.enabledVisualizarCliente(false);
        }

    }

    class ExcluirClienteTelaCadastro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(theView.getIdCliente());

            try {
                modelCliente.excluir(id);
                modelEndereco.excluir(id);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            atualizaTabelaDeClientes("");
            theView.enabledVisualizarCliente(false);
            theView.painelTelaClientes(0);

        }

    }

    class VisualizarCadastroCliente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(theView.getIDSelecaoTabelaClientes());

            ModelCliente cliente = null;
            try {
                cliente = modelCliente.getCliente(id);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            theView.setIdCliente(id);
            theView.setNome(cliente.getNome());
            theView.setTelefone(cliente.getTelefone());
            theView.setEmail(cliente.getEmail());

            try {
                enderecos = modelEndereco.getEnderecos(cliente);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            theView.painelTelaClientes(1);

            recuperaDadosDeEndereco(1);

            if (enderecos.size() > 1) {
                theView.enabledProximoEndereco(true);
            }
            paginaMax = enderecos.size();

            enabledCamposCliente(false);
            enabledCamposEndereco(false);
            theView.enabledSalvarEndereco(false);
            theView.enabledLimparCamposCliente(false);
            theView.enabledEditar(true);
            theView.enabledNovoEndereco(true);
            theView.enabledExcluir(true);
            theView.enabledRota(true);
            theView.visibleEditarCliente(true);
            theView.visibleExcluirClienteTelaCliente(true);
        }

    }

    class NovoEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int maiorIndice = enderecos.size() - 1;
            int maiorIdEndereco = enderecos.get(maiorIndice).getId();

            theView.setCodigoEndereco(maiorIdEndereco + 1);
            limpaCamposEndereco();
            enabledCamposEndereco(true);

            //pagina atual
            //int paginaAtual = Integer.parseInt(theView.getPaginaEndereco());
            //próxima página
            theView.setPaginaEndereco(paginaMax + 1);

            //habilita botões
            theView.enabledEnderecoAnterior(true);
            theView.enabledSalvarEndereco(true);

            //desabilita botões
            theView.enabledEditar(false);
            theView.enabledExcluir(true);
            theView.enabledNovoEndereco(false);
            theView.enabledProximoEndereco(false);

            theView.enabledProximoEndereco(false);
            theView.enabledEnderecoAnterior(false);

            /*       //atualiza variável global paginaMax
            if (paginaMax < paginaAtual) {
                paginaMax = paginaAtual;
            }*/
        }
    }

    class ExcluirEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int paginaAtual = Integer.parseInt(theView.getPaginaEndereco());
            int paginaAtualizada = 0;

            for (int i = 0; i < enderecos.size(); i++) {
                if (enderecos.get(i).getId() == Integer.parseInt(theView.getCodigoEndereco())) {
                    enderecos.set(i, new ModelEndereco(enderecos.get(i).getIdCliente(), enderecos.get(i).getId(), 0, null, null, null, null, null, null, null, null));
                }
            }

            theView.enabledSalvarEndereco(false);
            theView.enabledNovoEndereco(true);
            theView.enabledEditar(true);

            if (paginaAtual > 1) {
                paginaAtualizada = paginaAtual - 1;
                theView.setPaginaEndereco(paginaAtualizada);
                recuperaDadosDeEndereco(paginaAtualizada);
                paginaMax--;
            } else {
                paginaAtualizada = 1;
                theView.setPaginaEndereco(paginaAtualizada);
                recuperaDadosDeEndereco(paginaAtualizada);
                paginaMax--;
            }

            /*        if (paginaAtualizada > 1) {
                theView.enabledEnderecoAnterior(true);
            } else if (paginaAtualizada < paginaMax) {
                theView.enabledProximoEndereco(true);
            }*/
            if ((paginaMax != 1) && (paginaAtualizada == paginaMax)) {
                theView.enabledProximoEndereco(false);
                theView.enabledEnderecoAnterior(true);
            } else if ((paginaAtualizada == 1) && (paginaAtualizada < paginaMax)) {
                theView.enabledEnderecoAnterior(false);
                theView.enabledProximoEndereco(true);
            } else if ((paginaAtualizada > paginaMax) || (paginaAtualizada < paginaMax)) {
                theView.enabledProximoEndereco(true);
                theView.enabledEnderecoAnterior(true);
            }

        }

    }

    class EnderecoAnteriorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int pagina = Integer.parseInt(theView.getPaginaEndereco()) - 1;

            //exibe na negação que fomos para a página anterior
            theView.setPaginaEndereco(pagina);
            theView.setCodigoEndereco(pagina);

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
            theView.setCodigoEndereco(pagina);

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

    class AlterarEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            enabledCamposEndereco(true);
            theView.enabledSalvarEndereco(true);
            theView.enabledNovoEndereco(false);
            theView.enabledExcluir(false);
            theView.enabledEditar(false);
        }
    }

    class AlterarClienteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            enabledCamposCliente(true);
            theView.enabledSalvarTelaCadastro(true);
        }
    }

    class SalvarEnderecoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String descricao = theView.getDescricao();
            String logradouro = theView.getLogradouro();
            String bairro = theView.getBairro();
            String complemento = theView.getComplemento();
            String tipoLogradouro = theView.getTipoLogradouro();

            if (descricao.equals("") || logradouro.equals("") || bairro.equals("") || complemento.equals("")) {
                JOptionPane.showMessageDialog(null, "Todas as informações, exceto complemento, são de preenchimento obrigatório.", "DoceIra", JOptionPane.WARNING_MESSAGE);
            } else {
                //captura os valores inseridos para cliente e instanciando objeto cliente
                int idCliente = Integer.parseInt(theView.getIdCliente());
                String nome = theView.getNome();
                String telefone = theView.getTelefone();
                String email = theView.getEmail();
                modelCliente = new ModelCliente(idCliente, nome, telefone, email);

                //captura os valores inseridos para endereço
                int numero = Integer.parseInt(theView.getNumero());
                Double tempoMedioParaEntrega = Double.parseDouble(theView.getTempoEntrega());

                Double distanciaEntrega = Double.parseDouble(theView.getDistanciaEntrega());
                Double custoEntrega = Double.parseDouble(theView.getCustoEntrega());
                int idEnderecoLabel = Integer.parseInt(theView.getCodigoEndereco());

                //se o endereco em questão já existe no banco, trata-se de uma atualização
                try {
                    if (modelEndereco.verificaExistenciaEndereco(idEnderecoLabel, idCliente) != 0) {

                        modelEndereco = new ModelEndereco(idEnderecoLabel, idCliente, numero, tempoMedioParaEntrega, descricao, logradouro, bairro, complemento, tipoLogradouro, custoEntrega, distanciaEntrega);

                        for (int i = 0; i < enderecos.size(); i++) {
                            if ((enderecos.get(i).getId() == idEnderecoLabel) && (enderecos.get(i).getIdCliente() == idCliente)) {
                                enderecos.set(i, modelEndereco);
                            }
                        }

                        /*        if (situacaoVetorDeEnderecos.equals(""))
                        situacaoVetorDeEnderecos = "valores alterados";
                    else if (situacaoVetorDeEnderecos.equals("valores inseridos"))
                        situacaoVetorDeEnderecos = "valores inseridos e alterados";
                    else if (situacaoVetorDeEnderecos.equals("valores alterados"))
                        situacaoVetorDeEnderecos = "valores alterados";*/
                    } else {
                        //proximoCodigo(modelCliente) retorna o max(idEndereco) que tem na tabela de endereco para aquele cliente
                        int idEnderecoBanco = modelEndereco.proximoCodigo(modelCliente);

                        //se não houver endereço nenhum, vou usar a varíavel local, senão vou usar a do banco + 1
                        if (idEnderecoBanco != 0) {
                            idEndereco = idEnderecoBanco + 1;
                        }
                        idEndereco = idEnderecoLabel;

                        //instancia objeto endereco
                        modelEndereco = new ModelEndereco(idEndereco, idCliente, numero, tempoMedioParaEntrega, descricao, logradouro, bairro, complemento, tipoLogradouro, custoEntrega, distanciaEntrega);

                        //inclui objeto endereço no arraylist que será salvo no banco
                        enderecos.add(modelEndereco);

                        /*     if (situacaoVetorDeEnderecos.equals(""))
                        situacaoVetorDeEnderecos = "valores inseridos";
                    else if (situacaoVetorDeEnderecos.equals("valores inseridos"))
                        situacaoVetorDeEnderecos = "valores inseridos";
                    else if (situacaoVetorDeEnderecos.equals("valores alterados"))
                        situacaoVetorDeEnderecos = "valores inseridos e alterados";*/
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

                //inclui objeto endereço no arraylist que será salvo no banco
                //enderecos.add(modelEndereco);
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

                if ((paginaMax != 1) && (paginaAtual == paginaMax)) {
                    theView.enabledProximoEndereco(false);
                    theView.enabledEnderecoAnterior(true);
                } else if ((paginaAtual == 1) && (paginaAtual < paginaMax)) {
                    theView.enabledEnderecoAnterior(false);
                    theView.enabledProximoEndereco(true);
                } else if ((paginaAtual > paginaMax) || (paginaAtual < paginaMax)) {
                    theView.enabledProximoEndereco(true);
                    theView.enabledEnderecoAnterior(true);
                }

            }
        }
    }

    class CadastrarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //captura os valores inseridos para cliente e instanciando objeto cliente
            int idCliente = Integer.parseInt(theView.getIdCliente());
            String nome = theView.getNome();
            String telefone = theView.getTelefone();
            String email = theView.getEmail();
            modelCliente = new ModelCliente(idCliente, nome, telefone, email);

            //nenhum endereço foi cadastrado
            if (enderecos.size() == 0) {
                try {
                    if (modelCliente.getCliente(modelCliente.getId()) == null) {
                        modelCliente.adicionarCliente(modelCliente);
                    }
                    modelCliente.alterar(modelCliente);
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Informações salvas no banco. Nenhum endereço foi cadastrado para o cliente " + nome, "DoceIra", JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    //    if (situacaoVetorDeEnderecos.equals("valores alterados") || situacaoVetorDeEnderecos.equals("valores inseridos e alterados")) {
                    if (modelCliente.getCliente(modelCliente.getId()) == null) {
                        //envia objeto cliente para o registro
                        modelCliente.adicionarCliente(modelCliente);
                        //envia objeto endereco para o registro
                        modelEndereco.adicionarEndereco(enderecos);
                    } else {
                        modelCliente.alterar(modelCliente);

                        for (int i = 0; i < enderecos.size(); i++) {
                            if (modelEndereco.verificaExistenciaEndereco(enderecos.get(i).getId(), enderecos.get(i).getIdCliente()) > 0) {
                                if (enderecos.get(i).getLogradouro() == null) {
                                    modelEndereco.excluir(enderecos.get(i).getId());
                                }
                                modelEndereco.alterar(modelCliente, enderecos.get(i));
                            } else {
                                modelEndereco.adicionarEndereco(enderecos.get(i));
                            }
                        }
                    }
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Informações salvas no banco." + nome, "DoceIra", JOptionPane.INFORMATION_MESSAGE);
            }
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

    class PesquisarFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (theView.getPesquisa().equals("Digite sua pesquisa aqui...")) {
                theView.setPaginaPesquisa("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {

        }

    }

    class PesquisarKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                String busca = theView.getPesquisa();
                atualizaTabelaDeClientes(busca);
            }
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
        theView.enabledDescricao(x);
        theView.enabledTipoLogradouro(x);
        theView.enabledLogradouro(x);
        theView.enabledNumero(x);
        theView.enabledBairro(x);
        theView.enabledComplemento(x);
    }

    private void enabledCamposCliente(boolean x) {
        theView.enabledNome(x);
        theView.enabledTelefone(x);
        theView.enabledEmail(x);
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
        theView.setCodigoEndereco(enderecos.get(pagina - 1).getId());
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
            Logger.getLogger(ControllerCliente.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControllerCliente.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerCliente.class
                    .getName()).log(Level.SEVERE, null, ex);
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

    private void atualizaTabelaDeClientes(String busca) {
        String[] colunas = new String[4];
        colunas[0] = "Código";
        colunas[1] = "Nome";
        colunas[2] = "Telefone";
        colunas[3] = "E-Mail";

        ArrayList<ModelCliente> clientesListModelCliente = null;

        try {
            clientesListModelCliente = modelCliente.getClientes(busca);

        } catch (SQLException ex) {
            Logger.getLogger(ControllerMenu.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        String[][] clientes = new String[clientesListModelCliente.size()][4];

        for (int i = 0; i < clientesListModelCliente.size(); i++) {
            clientes[i][0] = String.valueOf(clientesListModelCliente.get(i).getId());
            clientes[i][1] = clientesListModelCliente.get(i).getNome();
            clientes[i][2] = clientesListModelCliente.get(i).getTelefone();
            clientes[i][3] = clientesListModelCliente.get(i).getEmail();

        }

        TableModel tableModel = new DefaultTableModel(clientes, colunas);
        theView.setTableModel(tableModel);
    }
}
