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
import model.ModelIngrediente;

/**
 *
 * @author Pichau
 */
public class DaoIngrediente {
    
    private final Connection conexao;

    public DaoIngrediente() {
        this.conexao = new Conexao().getConnection();
    }
    
    public int getCodigoAtual() throws SQLException {
        Connection con = new Conexao().getConnection();

        int codigo = 0;
        String sql = "select max(id) from ingrediente";
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
    
    public ArrayList<ModelIngrediente> getIngredientes() throws SQLException {
        Connection con = new Conexao().getConnection();
        
        ArrayList<ModelIngrediente> ingredientes = new ArrayList<ModelIngrediente>();
         
        int codigo;
        String nome, unidadePadrao;
        double quantidade;
        
        String sql = "select id,nome,unidadePadrao,quantidade from ingrediente";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("id");
                nome = rs.getString("nome");
                unidadePadrao = rs.getString("unidadePadrao");
                quantidade = rs.getDouble("quantidade");
                ModelIngrediente ingrediente = new ModelIngrediente(codigo,nome,unidadePadrao,quantidade);
                ingredientes.add(ingrediente);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredientes;
    }
    
    public ArrayList<ModelIngrediente> getIngredientes(String busca) throws SQLException {
        Connection con = new Conexao().getConnection();
        
        ArrayList<ModelIngrediente> ingredientes = new ArrayList<ModelIngrediente>();
         
        String buscaLike = "%" + busca + "%";
        int codigo;
        String nome, unidadePadrao;
        double quantidade;
        
        String sql = "select id,nome,unidadePadrao,quantidade from ingrediente where nome LIKE ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,buscaLike);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("id");
                nome = rs.getString("nome");
                unidadePadrao = rs.getString("unidadePadrao");
                quantidade = rs.getDouble("quantidade");
                ModelIngrediente ingrediente = new ModelIngrediente(codigo,nome,unidadePadrao,quantidade);
                ingredientes.add(ingrediente);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingredientes;
    }
    
    public ModelIngrediente getIngrediente(int busca) throws SQLException {
        Connection con = new Conexao().getConnection();
       
        ModelIngrediente ingrediente = null;
        int codigo;
        String nome, unidadePadrao;
        double quantidade;
        
        String sql = "select id,nome,unidadePadrao,quantidade from ingrediente where id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, busca);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                codigo = rs.getInt("id");
                nome = rs.getString("nome");
                unidadePadrao = rs.getString("unidadePadrao");
                quantidade = rs.getDouble("quantidade");
                ingrediente = new ModelIngrediente(codigo,nome,unidadePadrao,quantidade);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ingrediente;
    }
    
    public void adiciona(ModelIngrediente ingrediente) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql = "insert into ingrediente "
                + "(id,nome,unidadePadrao,quantidade)"
                + " values (?,?,?,?)";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, ingrediente.getId());
                stmt.setString(2, ingrediente.getNome());
                stmt.setString(3, ingrediente.getUnidadePadrao());
                stmt.setDouble(4, ingrediente.getQuantidade());
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void exclui(int id) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql =  "delete from ingrediente "
                    + "where id = ?";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void altera(ModelIngrediente ingrediente) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql =  "update ingrediente set nome = ?, unidadePadrao = ?, quantidade = ? where id = ?";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, ingrediente.getNome());
                stmt.setString(2, ingrediente.getUnidadePadrao());
                stmt.setDouble(3, ingrediente.getQuantidade());
                stmt.setInt(4, ingrediente.getId());
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
