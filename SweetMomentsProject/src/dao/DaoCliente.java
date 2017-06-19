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

/**
 *
 * @author Pamella
 */
public class DaoCliente {

    private final Connection conexao;

    public DaoCliente() {
        this.conexao = new Conexao().getConnection();
    }

    public int getCodigoAtual() throws SQLException {
        Connection con = new Conexao().getConnection();

        int codigo = 0;
        String sql = "select max(id) from cliente";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("max(id)");
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return codigo + 1;
    }
    
    public ArrayList<ModelCliente> getClientes() throws SQLException {
        Connection con = new Conexao().getConnection();
        
        ArrayList<ModelCliente> clientes = new ArrayList<ModelCliente>();
         
        int codigo;
        String nome, telefone, email;
        
        String sql = "select id,nome,telefone,email from cliente";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("id");
                nome = rs.getString("nome");
                telefone = rs.getString("telefone");
                email = rs.getString("email");
                ModelCliente cliente = new ModelCliente(codigo,nome,telefone,email);
                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public void adiciona(ModelCliente cliente) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql = "insert into cliente "
                + "(id,nome,telefone,email)"
                + " values (?,?,?,?)";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, cliente.getId());
                stmt.setString(2, cliente.getNome());
                stmt.setString(3, cliente.getTelefone());
                stmt.setString(4, cliente.getEmail());
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
