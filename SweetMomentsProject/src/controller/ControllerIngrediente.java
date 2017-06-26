/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.ModelIngrediente;
import model.ModelMedida;
import view.ViewIngrediente;

/**
 *
 * @author Pichau
 */
public class ControllerIngrediente {

    private view.ViewIngrediente theView;
    private model.ModelIngrediente modelIngrediente;
    private model.ModelMedida modelMedida;

    int paginaMax = 1;
    int idMedida = 1;

    ArrayList<ModelMedida> medidas = new ArrayList<ModelMedida>();
    ArrayList<ModelMedida> medidasASeremExcluidas = new ArrayList<ModelMedida>();

    public ControllerIngrediente(ViewIngrediente theView, ModelIngrediente modelIngrediente, ModelMedida modelMedida) {
        this.theView = theView;
        this.modelIngrediente = modelIngrediente;
        this.modelMedida = modelMedida;
        this.theView.salvarIngredienteTelaCadastroListener(new CadastrarListener());
        this.theView.jTextFieldPesquisaFocusListener(new PesquisarFocusListener());
        this.theView.jTextFieldPesquisaKeyListener(new PesquisarKeyListener());
        this.theView.voltarTelaBuscaListener(new VoltarTelaIngrediente());
        this.theView.voltarTelaCadastroListener(new VoltarTelaIngrediente());
        this.theView.tabelaIngredientesMouseListener(new TabelaIngredienteCliqueListener());
        this.theView.excluirIngredienteTelaBuscaListener(new ExcluirIngredienteBotaoBusca());
        this.theView.visualizarIngredienteListener(new VisualizarCadastroIngredientes());
        this.theView.novaMedidaListener(new NovaMedidaListener());
        this.theView.excluirMedidaListener(new ExcluirMedidaListener());
        this.theView.medidaAnteriorListener(new MedidaAnteriorListener());
        this.theView.proximaMedidaListener(new ProximaMedidaListener());
        this.theView.editarIngredienteListener(new AlterarIngredienteListener());
        this.theView.editarMedidaListener(new AlterarMedidaListener());
        this.theView.salvarMedidaListener(new SalvarMedidaListener());
    }

    public void setFrameVisible() throws SQLException {
        theView.setVisible(true);
        theView.setIdIngrediente(modelIngrediente.proximoCodigo());
        theView.setCodigoMedida(idMedida);
    }

    class VoltarTelaIngrediente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            theView.setVisible(false);
        }
    }

    class TabelaIngredienteCliqueListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            theView.enabledVisualizarIngrediente(true);
            theView.enabledExcluirIngredienteTelaBusca(true);
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

    class ExcluirIngredienteBotaoBusca implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(theView.getIDSelecaoTabelaIngrediente());

            try {
                modelIngrediente.excluir(id);
                modelMedida.excluir(id);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            atualizaTabelaDeIngredientes("");
            theView.enabledVisualizarIngrediente(false);
            theView.enabledExcluirIngredienteTelaBusca(false);
        }

    }

    class ExcluirIngredienteTelaCadastro implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(theView.getIdIngrediente());

            try {
                modelIngrediente.excluir(id);
                modelMedida.excluir(id);
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            atualizaTabelaDeIngredientes("");
            theView.enabledVisualizarIngrediente(false);
            theView.painelTelaIngrediente(0);

        }

    }

    class VisualizarCadastroIngredientes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(theView.getIDSelecaoTabelaIngrediente());

            ModelIngrediente ingrediente = null;
            try {
                ingrediente = modelIngrediente.getIngrediente(id);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            theView.setIdIngrediente(id);
            theView.setNomeIngrediente(ingrediente.getNome());
            theView.setUnidadePadrao(ingrediente.getUnidadePadrao());

            try {
                medidas = modelMedida.getMedidas(ingrediente);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (medidas.size() > 0) {

                theView.painelTelaIngrediente(1);

                recuperaDadosDeMedida(1);

                if (medidas.size() > 1) {
                    theView.enabledProximaMedida(true);
                }
                paginaMax = medidas.size();
                theView.enabledEditar(true);
                theView.enabledExcluirMedida(true);
            } else {
                limpaCamposMedida();
                theView.painelTelaIngrediente(1);
                theView.enabledEditar(false);
                theView.enabledExcluirMedida(false);
            }

            enabledCamposIngrediente(false);
            enabledCamposMedida(false);
            theView.enabledSalvarMedida(false);
            theView.enabledLimparCamposIngrediente(false);
            theView.enabledNovaMedida(true);
            theView.visibleEditarIngrediente(true);
            theView.visibleExcluirIngredienteTelaCadastro(true);
            theView.setPaginaMedida(1);
            theView.enabledMedidaAnterior(false);
        }

    }

    class NovaMedidaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (medidas.size() >= 1) {
                int maiorIndice = medidas.size() - 1;
                int maiorIdMedida = medidas.get(maiorIndice).getId();

                theView.setCodigoMedida(maiorIdMedida + 1);
                limpaCamposMedida();

                //próxima página
                theView.setPaginaMedida(paginaMax + 1);
            }

            limpaCamposMedida();
            enabledCamposMedida(true);

            //habilita botões
            theView.enabledSalvarMedida(true);
            theView.enabledExcluirMedida(true);

            //desabilita botões
            theView.enabledEditar(false);
            theView.enabledNovaMedida(false);
            theView.enabledProximaMedida(false);
            theView.enabledMedidaAnterior(false);

        }
    }

    class ExcluirMedidaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //pega em qual página estamos
            int paginaAtual = Integer.parseInt(theView.getPaginaMedida());

            //inaciliaza variavel que conterá a página que vamos
            int paginaAtualizada = 0;
            boolean achou = false;

            //remove do ArrayList o endereco que contém o mesmo id que o da tela           
            for (int i = 0; i < medidas.size(); i++) {
                if (medidas.get(i).getId() == Integer.parseInt(theView.getCodigoMedida())) {
                    medidasASeremExcluidas.add(medidas.get(i));         //adiciona em um ArrayList de enderecos que serão excluídos
                    medidas.remove(i);                                    //remove do ArrayList de enderecos
                    achou = true;
                }
            }

            if (achou) {
                //seta página atual
                if (paginaAtual > 1) {
                    paginaAtualizada = paginaAtual - 1;
                    theView.setPaginaMedida(paginaAtualizada);
                    recuperaDadosDeMedida(paginaAtualizada);
                    paginaMax--;
                } else {
                    paginaAtualizada = 1;
                    theView.setPaginaMedida(paginaAtualizada);
                    recuperaDadosDeMedida(paginaAtualizada);
                    paginaMax--;
                }
            } else {
                paginaAtualizada = paginaAtual - 1;
                enabledCamposMedida(false);
                theView.setPaginaMedida(paginaAtualizada);
                recuperaDadosDeMedida(paginaAtualizada);

            }

            //habilita/desabilita botões 
            theView.enabledSalvarMedida(false);
            theView.enabledNovaMedida(true);
            theView.enabledEditar(true);

            if ((paginaMax != 1) && (paginaAtual == paginaMax)) {
                theView.enabledProximaMedida(false);
                theView.enabledMedidaAnterior(true);
            } else if ((paginaAtual == 1) && (paginaAtual < paginaMax)) {
                theView.enabledMedidaAnterior(false);
                theView.enabledProximaMedida(true);
            } else if ((paginaAtual == 1) && (paginaAtual == paginaMax)) {
                theView.enabledMedidaAnterior(false);
                theView.enabledProximaMedida(false);
            } else if ((paginaAtual > paginaMax) || (paginaAtual < paginaMax)) {
                theView.enabledProximaMedida(true);
                theView.enabledMedidaAnterior(true);
            }

        }

    }

    class MedidaAnteriorListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int pagina = Integer.parseInt(theView.getPaginaMedida()) - 1;

            //exibe na negação que fomos para a página anterior
            theView.setPaginaMedida(pagina);
            theView.setCodigoMedida(pagina);

            //recuperando dados do endereco
            recuperaDadosDeMedida(pagina);

            //habilita botão de página posterior
            theView.enabledProximaMedida(true);

            //impede que o botão de anterior esteja habilitado quando o usuário já chegou na primeira página
            if (pagina == 1) {
                theView.enabledMedidaAnterior(false);
            }

            //desabilitando botões
            theView.enabledSalvarMedida(false);
        }
    }

    class ProximaMedidaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //pagina
            int pagina = Integer.parseInt(theView.getPaginaMedida()) + 1;

            //mostra que foi para a próxima página
            theView.setPaginaMedida(pagina);
            theView.setCodigoMedida(pagina);

            //recuperando dados do endereco
            recuperaDadosDeMedida(pagina);

            //habilita botão de página anterior
            theView.enabledMedidaAnterior(true);

            //impede que o botão de próximo esteja habilitado quando o usuário já chegou a última página
            if (pagina == paginaMax) {
                theView.enabledProximaMedida(false);
            }

        }
    }

    class AlterarMedidaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            enabledCamposMedida(true);
            theView.enabledSalvarMedida(true);
            theView.enabledNovaMedida(false);
            theView.enabledExcluirMedida(false);
            theView.enabledEditar(false);
        }
    }

    class AlterarIngredienteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            enabledCamposIngrediente(true);
            theView.enabledSalvarTelaCadastro(true);
        }
    }

    private void atualizaTabelaDeIngredientes(String busca) {
        String[] colunas = new String[4];
        colunas[0] = "Código";
        colunas[1] = "Nome";
        colunas[2] = "Unidade Padrão";
        colunas[3] = "Quantidade";

        ArrayList<ModelIngrediente> ingredientesListModelCliente = null;

        try {
            ingredientesListModelCliente = modelIngrediente.getIngredientes(busca);

        } catch (SQLException ex) {
            Logger.getLogger(ControllerMenu.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        String[][] clientes = new String[ingredientesListModelCliente.size()][4];

        for (int i = 0; i < ingredientesListModelCliente.size(); i++) {
            clientes[i][0] = String.valueOf(ingredientesListModelCliente.get(i).getId());
            clientes[i][1] = ingredientesListModelCliente.get(i).getNome();
            clientes[i][2] = ingredientesListModelCliente.get(i).getUnidadePadrao();
            clientes[i][3] = String.valueOf(ingredientesListModelCliente.get(i).getQuantidade());

        }

        TableModel tableModel = new DefaultTableModel(clientes, colunas);
        theView.setTableModel(tableModel);
    }

    class SalvarMedidaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //captura os valores inseridos para medida
            String nome = theView.getNomeMedida();
            Double quantidade = Double.parseDouble(theView.getQuantidadeConversao());
            int idMedidaLabel = Integer.parseInt(theView.getCodigoMedida());
            String unidadeConversao = theView.getUnidadeConversao();

            if (nome.equals("") || unidadeConversao.equals("")) {
                JOptionPane.showMessageDialog(null, "Todas as informações são de preenchimento obrigatório.", "DoceIra", JOptionPane.WARNING_MESSAGE);
            } else {

                //captura os valores inseridos para cliente e instanciando objeto cliente
                int idIngrediente = Integer.parseInt(theView.getIdIngrediente());
                String nomeIngrediente = theView.getNomeIngrediente();
                String unidadePadrao = theView.getUnidadePadrao();
                double quantidadeUnidadePadrao = Double.parseDouble(theView.getQuantidadeConversao());
                modelIngrediente = new ModelIngrediente(idIngrediente, nomeIngrediente, unidadePadrao, quantidadeUnidadePadrao);

                //se o endereco em questão já existe no banco, trata-se de uma atualização
                try {
                    if (modelMedida.verificaExistenciaMedida(idMedidaLabel, idIngrediente) != 0) {

                        modelMedida = new ModelMedida(idMedidaLabel, idIngrediente, quantidade, nome, unidadeConversao);

                        for (int i = 0; i < medidas.size(); i++) {
                            if ((medidas.get(i).getId() == idMedidaLabel) && (medidas.get(i).getIdIngrediente() == idIngrediente)) {
                                medidas.set(i, modelMedida);
                            }
                        }

                    } else {
                        //proximoCodigo(modelCliente) retorna o max(idEndereco) que tem na tabela de endereco para aquele cliente
                        int idMedidaBanco = modelMedida.proximoCodigo(modelIngrediente);

                        //se não houver endereço nenhum, vou usar a varíavel local, senão vou usar a do banco + 1
                        if (idMedidaBanco != 0) {
                            idMedida = idMedidaBanco + 1;
                        }
                        idMedida = idMedidaLabel;

                        //instancia objeto endereco
                        modelMedida = new ModelMedida(idMedida, idIngrediente, quantidade, nome, unidadeConversao);

                        //inclui objeto endereço no arraylist que será salvo no banco
                        medidas.add(modelMedida);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

                int paginaAtual = Integer.parseInt(theView.getPaginaMedida());
                //atualiza variável global paginaMax
                if (paginaMax < paginaAtual) {
                    paginaMax = paginaAtual;
                }

                //habilitando botões
                theView.enabledSalvarTelaCadastro(true);
                theView.enabledNovaMedida(true);
                theView.enabledEditar(true);
                theView.enabledExcluirMedida(true);

                //desabilita botão salvarEndereco
                theView.enabledSalvarMedida(false);

                //desabilita campos de endereço
                enabledCamposMedida(false);

                if ((paginaMax != 1) && (paginaAtual == paginaMax)) {
                    theView.enabledProximaMedida(false);
                    theView.enabledMedidaAnterior(true);
                } else if ((paginaAtual == 1) && (paginaAtual < paginaMax)) {
                    theView.enabledMedidaAnterior(false);
                    theView.enabledProximaMedida(true);
                } else if ((paginaAtual == 1) && (paginaAtual == paginaMax)) {
                    theView.enabledMedidaAnterior(false);
                    theView.enabledProximaMedida(false);
                } else if ((paginaAtual > paginaMax) || (paginaAtual < paginaMax)) {
                    theView.enabledProximaMedida(true);
                    theView.enabledMedidaAnterior(true);
                }

            }
        }
    }

    class CadastrarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //captura os valores inseridos para cliente e instanciando objeto cliente
            int idIngrediente = Integer.parseInt(theView.getIdIngrediente());
            String nome = theView.getNomeIngrediente();
            String unidadePadrao = theView.getUnidadePadrao();
            double quantidade = Double.parseDouble(theView.getQuantidadeConversao());
            modelIngrediente = new ModelIngrediente(idIngrediente, nome, unidadePadrao, quantidade);

            //nenhum endereço foi cadastrado
            if (medidas.size() == 0) {
                try {
                    if (modelIngrediente.getIngrediente(modelIngrediente.getId()) == null) {
                        modelIngrediente.adicionarIngrediente(modelIngrediente);
                    }
                    modelIngrediente.alterar(modelIngrediente);
                    if (medidasASeremExcluidas.size() > 0) {
                        excluiMedidas(idIngrediente);
                    }

                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Informações salvas no banco. Nenhuma medida foi \ncadastrada para o ingrediente " + nome, "DoceIra", JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    //    if (situacaoVetorDeEnderecos.equals("valores alterados") || situacaoVetorDeEnderecos.equals("valores inseridos e alterados")) {
                    if (modelIngrediente.getIngrediente(modelIngrediente.getId()) == null) {
                        //envia objeto cliente para o registro
                        modelIngrediente.adicionarIngrediente(modelIngrediente);
                        //envia objeto endereco para o registro
                        modelMedida.adicionarMedida(medidas);
                    } else {
                        modelIngrediente.alterar(modelIngrediente);

                        for (int i = 0; i < medidas.size(); i++) {
                            if (modelMedida.verificaExistenciaMedida(medidas.get(i).getId(), medidas.get(i).getIdIngrediente()) > 0) {
                                modelMedida.alterar(modelIngrediente, medidas.get(i));
                            } else {
                                modelMedida.adicionarMedida(medidas.get(i));
                            }
                        }
                    }
                    if (medidasASeremExcluidas.size() > 0) {
                        excluiMedidas(idIngrediente);
                    }
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Informações salvas no banco.", "DoceIra", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private void excluiMedidas(int idIngrediente) throws ParseException, SQLException {
        for (int i = 0; i < medidasASeremExcluidas.size(); i++) {
            if (modelMedida.verificaExistenciaMedida(medidasASeremExcluidas.get(i).getId(), idIngrediente) > 0) {
                modelMedida.excluir(idIngrediente, medidasASeremExcluidas.get(i).getId());
            }
        }
    }

    class PesquisarFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (theView.getPesquisa().equals("Digite sua pesquisa aqui...")) {
                theView.setPesquisa("");
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
                atualizaTabelaDeIngredientes(busca);
            }
        }

    }

    private void recuperaDadosDeMedida(int pagina) {

        //recuperando dados do endereco
        int indice = pagina - 1;

        if (medidas.size() > 0) {
            theView.setNomeMedida(medidas.get(indice).getNome());
            theView.setQuantidadeConversao(medidas.get(indice).getQuantidade());
            theView.setUnidadeConversao(medidas.get(indice).getUnidadeConversao());
            theView.setCodigoMedida(medidas.get(indice).getId());

        } else {
            limpaCamposMedida();
            enabledCamposMedida(true);
            theView.enabledSalvarMedida(true);
            theView.enabledNovaMedida(false);
            theView.enabledEditar(false);
            theView.enabledExcluirMedida(false);
        }
    }

    private void limpaCamposMedida() {
        //limpar campos de endereco
        theView.setNomeMedida("");
        theView.setUnidadeConversao("Gramas");
        theView.setQuantidadeConversao(1.0);
    }

    private void enabledCamposMedida(boolean x) {
        theView.enabledNomeMedida(x);
        theView.enabledUnidadeConversao(x);
        theView.enabledQuantidadeConversao(x);
    }

    private void enabledCamposIngrediente(boolean x) {
        theView.enabledNomeIngrediente(x);
        theView.enabledUnidadePadrao(x);
    }

}
