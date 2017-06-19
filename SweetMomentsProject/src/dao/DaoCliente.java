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
    
    public ArrayList<ModelCliente> getClientes(String busca) throws SQLException {
        Connection con = new Conexao().getConnection();
        
        ArrayList<ModelCliente> clientes = new ArrayList<ModelCliente>();
         
        String buscaLike = "%" + busca + "%";
        int codigo;
        String nome, telefone, email;
        
        String sql = "select id,nome,telefone,email from cliente where nome LIKE ? or telefone LIKE ? or email LIKE ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,buscaLike);
            stmt.setString(2,buscaLike);
            stmt.setString(3,buscaLike);
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
    
    public ModelCliente getCliente(int busca) throws SQLException {
        Connection con = new Conexao().getConnection();
       
        ModelCliente cliente = null;
        int codigo;
        String nome, telefone, email;
        
        String sql = "select id,nome,telefone,email from cliente where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, busca);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("id");
                nome = rs.getString("nome");
                telefone = rs.getString("telefone");
                email = rs.getString("email");
                cliente = new ModelCliente(codigo,nome,telefone,email);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
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
