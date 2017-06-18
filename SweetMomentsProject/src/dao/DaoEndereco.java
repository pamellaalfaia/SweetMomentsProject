/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import model.ModelCliente;
import model.ModelEndereco;

/**
 *
 * @author Pamella
 */
public class DaoEndereco {

    private final Connection conexao;
    int codigoCliente;

    public DaoEndereco() {
        this.conexao = new Conexao().getConnection();
    }

    public int getCodigoAtual(ModelCliente cliente) throws SQLException {
        Connection conexao = new Conexao().getConnection();
        int codigo = 0;
        codigoCliente = cliente.getId();
        String sql = "select max(e.id) from endereco e, cliente c where c.id = e.idCliente and c.id = ?";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1, codigoCliente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                codigo = rs.getInt(1);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conexao.close();
        if (codigo == 0) {
            return 1;
        }
        return codigo;
    }

    public void adiciona(ArrayList<ModelEndereco> enderecos) throws SQLException, ParseException {
        for (int i = 0; i < enderecos.size(); i++) {
            Connection conexao = new Conexao().getConnection();
            String sql = "insert into endereco "
                    + "(id,idCliente,numero,tempoMedioEntrega,descricao,logradouro,bairro,complemento,tipoDeLogradouro,custoEntrega,distanciaEntrega)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?)";
            try {
                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                    stmt.setInt(1, enderecos.get(i).getId());
                    stmt.setInt(2, enderecos.get(i).getIdCliente());
                    stmt.setInt(3, enderecos.get(i).getNumero());
                    stmt.setInt(4, enderecos.get(i).getTempoMedioParaEntrega());
                    stmt.setString(5, enderecos.get(i).getDescricao());
                    stmt.setString(6, enderecos.get(i).getLogradouro());
                    stmt.setString(7, enderecos.get(i).getBairro());
                    stmt.setString(8, enderecos.get(i).getComplemento());
                    stmt.setString(9, enderecos.get(i).getTipoLogradouro());
                    stmt.setDouble(10, enderecos.get(i).getCustoEntrega());
                    stmt.setDouble(11, enderecos.get(i).getDistanciaEntrega());
                    stmt.execute();  
                }
                conexao.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
