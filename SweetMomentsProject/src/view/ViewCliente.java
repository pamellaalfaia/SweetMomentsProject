/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 *
 * @author Pamella
 */
public class ViewCliente extends javax.swing.JFrame {

    /**
     * Creates new form ViewClientes
     */
    public ViewCliente() {
        initComponents();

        this.getContentPane().setBackground(Color.white);

        ImageIcon check = new ImageIcon("Images/success-menor.png");
        jButtonSalvarEndereco.setIcon(check);
        ImageIcon proximo = new ImageIcon("Images/next-azul-menor.png");
        jButtonProximoEndereco.setIcon(proximo);
        ImageIcon anterior = new ImageIcon("Images/previous-azul-menor.png");
        jButtonEnderecoAnterior.setIcon(anterior);
        ImageIcon novoEndereco = new ImageIcon("Images/plus-menor.png");
        jButtonNovoEndereco.setIcon(novoEndereco);
        ImageIcon editarEndereco = new ImageIcon("Images/edit.png");
        jButtonEditarEndereco.setIcon(editarEndereco);
        ImageIcon excluirEndereco = new ImageIcon("Images/delete.png");
        jButtonExcluirEndereco.setIcon(excluirEndereco);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbledPanelCliente = new javax.swing.JTabbedPane();
        PanelBusca = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        jButtonVisualizarTelaBusca = new javax.swing.JButton();
        jButtonLimparTelaBusca = new javax.swing.JButton();
        jButtonExcluirTelaBusca = new javax.swing.JButton();
        jButtonVoltarTelaBusca = new javax.swing.JButton();
        PanelCadastro = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonSalvarTelaCadastro = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonLimparTelaCadastro = new javax.swing.JButton();
        jButtonVoltarTelaCadastro = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtDescricao = new javax.swing.JTextField();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TxtBairro = new javax.swing.JTextField();
        TxtNome = new javax.swing.JTextField();
        TxtComplemento = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtNumero = new javax.swing.JTextField();
        jTextFieldTempoEntrega = new javax.swing.JTextField();
        jButtonProximoEndereco = new javax.swing.JButton();
        jButtonSalvarEndereco = new javax.swing.JButton();
        jButtonEnderecoAnterior = new javax.swing.JButton();
        TxtId = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        LabelPaginaEndereco = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jButtonNovoEndereco = new javax.swing.JButton();
        jButtonEditarEndereco = new javax.swing.JButton();
        jButtonExcluirEndereco = new javax.swing.JButton();
        jLabelMapa = new javax.swing.JLabel();
        jComboBoxTipoLogradouro = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldCustoEntrega = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldDistanciaEntrega = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Doceira - Clientes");

        TabbledPanelCliente.setBackground(new java.awt.Color(255, 255, 255));

        PanelBusca.setBackground(new java.awt.Color(255, 255, 255));

        jTextField9.setText("Digite sua pesquisa aqui...");

        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TableCliente);

        jButtonVisualizarTelaBusca.setBackground(new java.awt.Color(255, 204, 255));
        jButtonVisualizarTelaBusca.setText("Visualizar");
        jButtonVisualizarTelaBusca.setEnabled(false);

        jButtonLimparTelaBusca.setBackground(new java.awt.Color(102, 102, 255));
        jButtonLimparTelaBusca.setText("Limpar");

        jButtonExcluirTelaBusca.setText("Excluir");

        jButtonVoltarTelaBusca.setText("Voltar ao Menu");

        javax.swing.GroupLayout PanelBuscaLayout = new javax.swing.GroupLayout(PanelBusca);
        PanelBusca.setLayout(PanelBuscaLayout);
        PanelBuscaLayout.setHorizontalGroup(
            PanelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBuscaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PanelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelBuscaLayout.createSequentialGroup()
                        .addComponent(jButtonVisualizarTelaBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jButtonLimparTelaBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExcluirTelaBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jButtonVoltarTelaBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                    .addComponent(jTextField9))
                .addGap(22, 22, 22))
        );
        PanelBuscaLayout.setVerticalGroup(
            PanelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBuscaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addGroup(PanelBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVisualizarTelaBusca)
                    .addComponent(jButtonLimparTelaBusca)
                    .addComponent(jButtonExcluirTelaBusca)
                    .addComponent(jButtonVoltarTelaBusca))
                .addGap(25, 25, 25))
        );

        TabbledPanelCliente.addTab("Busca", PanelBusca);

        PanelCadastro.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Página");

        jSeparator2.setToolTipText("");
        jSeparator2.setName(""); // NOI18N

        jButtonSalvarTelaCadastro.setText("Salvar");
        jButtonSalvarTelaCadastro.setEnabled(false);

        jSeparator1.setToolTipText("");
        jSeparator1.setName(""); // NOI18N

        jLabel3.setText("Endereço");

        jLabel4.setText("Descrição");

        jButtonLimparTelaCadastro.setText("Limpar");

        jButtonVoltarTelaCadastro.setText("Voltar");

        jLabel5.setText("Logradouro");

        jLabel6.setText("Bairro");

        jLabel7.setText("Número");

        jLabel8.setText("Tempo");

        jLabel9.setText("Complemento");

        jLabel1.setText("Nome");

        jLabel2.setText("Telefone");

        jTextFieldTempoEntrega.setEnabled(false);

        TxtId.setEnabled(false);

        jLabel11.setText("ID");

        LabelPaginaEndereco.setText("1");

        jLabel12.setText("E-mail");

        jButtonNovoEndereco.setEnabled(false);

        jButtonEditarEndereco.setEnabled(false);

        jButtonExcluirEndereco.setEnabled(false);

        jComboBoxTipoLogradouro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aeroporto", "Alameda", "Área", "Avenida", "Campo", "Chácara", "Colônia", "Condomínio", "Conjunto", "Distrito", "Esplanada", "Estação", "Estrada", "Favela", "Fazenda", "Feira", "Jardim", "Ladeira", "Lago", "Lagoa", "Largo", "Loteamento", "Morro", "Núcleo", "Parque", "Passarela", "Pátio", "Praça", "Quadra", "Recanto", "Residencial", "Rodovia", "Rua", "Setor", "Sítio", "Travessa", "Trecho", "Trevo", "Vale", "Vereda", "Via", "Viaduto", "Viela", "Vila" }));

        jLabel14.setText("Custo");

        jTextFieldCustoEntrega.setEnabled(false);

        jLabel15.setText("Km");

        jTextFieldDistanciaEntrega.setEnabled(false);

        jLabel16.setText("Informações da entrega");

        jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("(##)#########"))));

        javax.swing.GroupLayout PanelCadastroLayout = new javax.swing.GroupLayout(PanelCadastro);
        PanelCadastro.setLayout(PanelCadastroLayout);
        PanelCadastroLayout.setHorizontalGroup(
            PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(PanelCadastroLayout.createSequentialGroup()
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel11))
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtId, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(jFormattedTextFieldTelefone))
                .addGap(75, 75, 75)
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(TxtNome))
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCadastroLayout.createSequentialGroup()
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(PanelCadastroLayout.createSequentialGroup()
                                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(PanelCadastroLayout.createSequentialGroup()
                                                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jComboBoxTipoLogradouro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(29, 36, Short.MAX_VALUE)
                                                .addComponent(jLabel6)
                                                .addGap(12, 12, 12)
                                                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                                    .addComponent(TxtBairro)))
                                            .addComponent(TxtComplemento)
                                            .addComponent(TxtDescricao)))
                                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldTempoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldDistanciaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldCustoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(10, 10, 10)
                                        .addComponent(jSeparator4)))
                                .addGap(33, 33, 33)
                                .addComponent(jLabelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButtonSalvarEndereco)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonNovoEndereco)
                        .addGap(33, 33, 33)
                        .addComponent(jButtonEditarEndereco)
                        .addGap(34, 34, 34)
                        .addComponent(jButtonExcluirEndereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEnderecoAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LabelPaginaEndereco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonProximoEndereco)))
                .addGap(30, 30, 30))
            .addComponent(jSeparator2)
            .addGroup(PanelCadastroLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jButtonSalvarTelaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(jButtonLimparTelaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addComponent(jButtonVoltarTelaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelCadastroLayout.setVerticalGroup(
            PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCadastroLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel1))))
                .addGap(27, 27, 27)
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(25, 25, 25)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxTipoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(PanelCadastroLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6))
                            .addComponent(TxtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(32, 32, 32)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTempoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jTextFieldCustoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jTextFieldDistanciaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCadastroLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonEnderecoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(LabelPaginaEndereco)
                                .addComponent(jLabel10))
                            .addComponent(jButtonProximoEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonEditarEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonExcluirEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelCadastroLayout.createSequentialGroup()
                        .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNovoEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSalvarEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(PanelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSalvarTelaCadastro)
                    .addComponent(jButtonLimparTelaCadastro)
                    .addComponent(jButtonVoltarTelaCadastro))
                .addGap(23, 23, 23))
        );

        TabbledPanelCliente.addTab("Cadastro", PanelCadastro);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TabbledPanelCliente)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbledPanelCliente)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCliente().setVisible(true);
            }
        });
    }

    public JTable getTableCliente() {
        return TableCliente;
    }

    public void setTableCliente(JTable TableCliente) {
        this.TableCliente = TableCliente;
    }

    public String getIdCliente() {
        return TxtId.getText();
    }

    public void setIdCliente(int idCliente) {
        TxtId.setText(String.valueOf(idCliente));
    }

    public String getNome() {
        return TxtNome.getText();
    }

    public void setNome(String nome) {
        TxtNome.setText(nome);
    }

    public String getTelefone() {
        return jFormattedTextFieldTelefone.getText();
    }

    public void setTelefone(String telefone) {
        jFormattedTextFieldTelefone.setText(telefone);
    }

    public String getEmail() {
        return jTextFieldEmail.getText();
    }

    public void setEmail(String email) {
        jTextFieldEmail.setText(email);
    }

    public String getDescricao() {
        return TxtDescricao.getText();
    }

    public void setDescricao(String descricao) {
        TxtDescricao.setText(descricao);
    }

    public String getTipoLogradouro() {
        return jComboBoxTipoLogradouro.getSelectedItem().toString();
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        jComboBoxTipoLogradouro.setSelectedItem(tipoLogradouro);
    }

    public String getLogradouro() {
        return jTextFieldLogradouro.getText();
    }

    public void setLogradouro(String rua) {
        jTextFieldLogradouro.setText(rua);
    }

    public String getNumero() {
        return TxtNumero.getText();
    }

    public void setNumero(String numero) {
        TxtNumero.setText(numero);
    }

    public String getBairro() {
        return TxtBairro.getText();
    }

    public void setBairro(String bairro) {
        TxtBairro.setText(bairro);
    }

    public String getComplemento() {
        return TxtComplemento.getText();
    }

    public void setComplemento(String complemento) {
        TxtComplemento.setText(complemento);
    }

    public String getTempoEntrega() {
        return jTextFieldTempoEntrega.getText();
    }

    public void setTempoEntrega(String entrega) {
        jTextFieldTempoEntrega.setText(entrega);
    }

    public String getDistanciaEntrega() {
        return jTextFieldDistanciaEntrega.getText();
    }

    public void setDistanciaEntrega(String entrega) {
        jTextFieldDistanciaEntrega.setText(entrega);
    }

    public String getCustoEntrega() {
        return jTextFieldCustoEntrega.getText();
    }

    public void setCustoEntrega(Float custoEntrega) {
        jTextFieldCustoEntrega.setText(custoEntrega.toString());
    }

    public String getPaginaEndereco() {
        return LabelPaginaEndereco.getText();
    }

    public void setPaginaEndereco(int idEndereco) {
        LabelPaginaEndereco.setText(String.valueOf(idEndereco));
    }

    public String getLabelMapa() {
        return jLabelMapa.getText();
    }

    public void setLabelMapa(String mapa) {
        jLabelMapa.setText(String.valueOf(mapa));
    }

    public void setLabelMapa(Icon mapa) {
        jLabelMapa.setIcon(mapa);
    }

    public void salvarCliente(ActionListener listenerForjButtonCadastrar) {
        jButtonSalvarTelaCadastro.addActionListener(listenerForjButtonCadastrar);
    }

    public void limparCamposClienteListener(ActionListener listenerForjButtonLimpar) {
        jButtonLimparTelaCadastro.addActionListener(listenerForjButtonLimpar);
    }

    public void voltarListener(ActionListener listenerForjButtonVoltar) {
        jButtonVoltarTelaCadastro.addActionListener(listenerForjButtonVoltar);
    }

    public void salvarEnderecoListener(ActionListener listenerForjButtonSalvarEndereco) {
        jButtonSalvarEndereco.addActionListener(listenerForjButtonSalvarEndereco);
    }

    public void novoEnderecoListener(ActionListener listenerForjButtonNovoEndereco) {
        jButtonNovoEndereco.addActionListener(listenerForjButtonNovoEndereco);
    }

    public void editarListener(ActionListener listenerForjButtonEditarEndereco) {
        jButtonEditarEndereco.addActionListener(listenerForjButtonEditarEndereco);
    }

    public void excluirListener(ActionListener listenerForjButtonExcluirEndereco) {
        jButtonExcluirEndereco.addActionListener(listenerForjButtonExcluirEndereco);
    }

    public void proximoEnderecoListener(ActionListener listenerForjButtonProximoEndereco) {
        jButtonProximoEndereco.addActionListener(listenerForjButtonProximoEndereco);
    }

    public void enderecoAnteriorListener(ActionListener listenerForjButtonEnderecoAnterior) {
        jButtonEnderecoAnterior.addActionListener(listenerForjButtonEnderecoAnterior);
    }

    public void visualizarClienteListener(ActionListener listenerForjButtonVisualizar) {
        jButtonVisualizarTelaBusca.addActionListener(listenerForjButtonVisualizar);
    }

    public void limparBuscaClienteListener(ActionListener listenerForjButtonLimpar) {
        jButtonLimparTelaBusca.addActionListener(listenerForjButtonLimpar);
    }

    public void excluirClienteListener(ActionListener listenerForjButtonExcluir) {
        jButtonExcluirTelaBusca.addActionListener(listenerForjButtonExcluir);
    }

    public void voltarDaBuscaListener(ActionListener listenerForjButtonVoltar) {
        jButtonVoltarTelaBusca.addActionListener(listenerForjButtonVoltar);
    }

    public void jTextFieldComplementoListener(FocusListener listenerForjFieldComplemento) {
        TxtComplemento.addFocusListener(listenerForjFieldComplemento);
    }

    public void enabledSalvarTelaCadastro(boolean x) {
        jButtonSalvarTelaCadastro.setEnabled(x);
    }

    public void enabledLimparCamposCliente(boolean x) {
        jButtonLimparTelaCadastro.setEnabled(x);
    }

    public void enabledVoltar(boolean x) {
        jButtonVoltarTelaCadastro.setEnabled(x);
    }

    public void enabledSalvarEndereco(boolean x) {
        jButtonSalvarEndereco.setEnabled(x);
    }

    public void enabledNovoEndereco(boolean x) {
        jButtonNovoEndereco.setEnabled(x);
    }

    public void enabledEditar(boolean x) {
        jButtonEditarEndereco.setEnabled(x);
    }

    public void enabledExcluir(boolean x) {
        jButtonExcluirEndereco.setEnabled(x);
    }

    public void enabledProximoEndereco(boolean x) {
        jButtonProximoEndereco.setEnabled(x);
    }

    public void enabledEnderecoAnterior(boolean x) {
        jButtonEnderecoAnterior.setEnabled(x);
    }

    public void enabledVisualizarCliente(boolean x) {
        jButtonVisualizarTelaBusca.setEnabled(x);
    }

    public void enabledLimparBuscaCliente(boolean x) {
        jButtonLimparTelaBusca.setEnabled(x);
    }

    public void enabledExcluirCliente(boolean x) {
        jButtonExcluirTelaBusca.setEnabled(x);
    }

    public void enabledVoltarDaBusca(boolean x) {
        jButtonVoltarTelaBusca.setEnabled(x);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelPaginaEndereco;
    private javax.swing.JPanel PanelBusca;
    private javax.swing.JPanel PanelCadastro;
    private javax.swing.JTabbedPane TabbledPanelCliente;
    private javax.swing.JTable TableCliente;
    private javax.swing.JTextField TxtBairro;
    private javax.swing.JTextField TxtComplemento;
    private javax.swing.JTextField TxtDescricao;
    private javax.swing.JTextField TxtId;
    private javax.swing.JTextField TxtNome;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JButton jButtonEditarEndereco;
    private javax.swing.JButton jButtonEnderecoAnterior;
    private javax.swing.JButton jButtonExcluirEndereco;
    private javax.swing.JButton jButtonExcluirTelaBusca;
    private javax.swing.JButton jButtonLimparTelaBusca;
    private javax.swing.JButton jButtonLimparTelaCadastro;
    private javax.swing.JButton jButtonNovoEndereco;
    private javax.swing.JButton jButtonProximoEndereco;
    private javax.swing.JButton jButtonSalvarEndereco;
    private javax.swing.JButton jButtonSalvarTelaCadastro;
    private javax.swing.JButton jButtonVisualizarTelaBusca;
    private javax.swing.JButton jButtonVoltarTelaBusca;
    private javax.swing.JButton jButtonVoltarTelaCadastro;
    private javax.swing.JComboBox<String> jComboBoxTipoLogradouro;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelMapa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldCustoEntrega;
    private javax.swing.JTextField jTextFieldDistanciaEntrega;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldTempoEntrega;
    // End of variables declaration//GEN-END:variables
}
