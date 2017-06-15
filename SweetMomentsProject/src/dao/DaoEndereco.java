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
    
    public int getCodigoAtual(ModelCliente cliente) throws SQLException{
        int codigo = 0;
        codigoCliente = cliente.getId();
        String sql = "select max(id) from endereco e, cliente c where c.id = ?";
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
        if (codigo == 0)
            return 1;
        return codigo;
    }   

    public void adiciona(ModelEndereco endereco) throws SQLException, ParseException {
        String sql = "insert into endereco "
                + "(id,idCliente,numero,tempoMedioEntrega,descricao,rua,bairro,complemento)"
                + " values (?,?,?,?,?,?,?,?)";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, endereco.getId());
                stmt.setInt(2, codigoCliente);
                stmt.setInt(3, endereco.getNumero());
                stmt.setInt(4, endereco.getTempoMedioParaEntrega());
                stmt.setString(5, endereco.getDescricao());
                stmt.setString(6, endereco.getRua());
                stmt.setString(7, endereco.getBairro());
                stmt.setString(8, endereco.getComplemento());
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
