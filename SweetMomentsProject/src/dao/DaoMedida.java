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
import model.ModelMedida;

/**
 *
 * @author Pichau
 */
public class DaoMedida {
    
    private final Connection conexao;
    int codigoIngrediente;

    public DaoMedida() {
        this.conexao = new Conexao().getConnection();
    }
    
    public int getCodigoAtual(ModelIngrediente ingrediente) throws SQLException {
        Connection conexao = new Conexao().getConnection();
        int codigo = 0;
        codigoIngrediente = ingrediente.getId();
        String sql = "select max(m.id) from ingrediente i, medida m where i.id = m.idIngrediente and i.id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoIngrediente);

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
        return codigo;
    }
    
    public ArrayList<ModelMedida> getMedidas(ModelIngrediente ingrediente) throws SQLException {
        Connection conexao = new Conexao().getConnection();
        
        ArrayList<ModelMedida> medidas = new ArrayList<ModelMedida>();
        int id;
        String nome, unidadeDeConversao;
        double quantidade;
        
        int codigoIngrediente = ingrediente.getId();
        
        String sql = "select * from medida m, ingrediente i where i.id = m.idIngrediente and i.id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoIngrediente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                nome = rs.getString("nome");
                unidadeDeConversao = rs.getString("unidadeDeConversao");
                quantidade = rs.getDouble("quantidade");
                ModelMedida medida = new ModelMedida(id, codigoIngrediente, quantidade, nome, unidadeDeConversao);
                medidas.add(medida);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conexao.close();
        return medidas;
    }
    
    public int verificaExistenciaMedida(int id, int idIngrediente) throws SQLException {
        Connection con = new Conexao().getConnection();
      
        int quantidade = 0;
        
        String sql = "select count(*) from medida m, ingrediente i where i.id = m.idIngrediente and i.id = ? and m.id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idIngrediente);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                quantidade = rs.getInt("count(*)");
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quantidade;
    }
    
    public void adiciona(ArrayList<ModelMedida> medidas) throws SQLException, ParseException {
        for (int i = 0; i < medidas.size(); i++) {
            Connection con = new Conexao().getConnection();
            String sql = "insert into medida "
                    + "(id,idIngrediente,nome,quantidade,unidadeDeConversao)"
                    + " values (?,?,?,?,?)";
            try {
                try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    stmt.setInt(1, medidas.get(i).getId());
                    stmt.setInt(2, medidas.get(i).getIdIngrediente());
                    stmt.setString(3, medidas.get(i).getNome());
                    stmt.setDouble(4, medidas.get(i).getQuantidade());
                    stmt.setString(5, medidas.get(i).getUnidadeConversao());
                    stmt.execute();
                }
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public void adiciona(ModelMedida medida) throws SQLException, ParseException {
            Connection con = new Conexao().getConnection();
            String sql = "insert into medida "
                    + "(id,idIngrediente,nome,quantidade,unidadeDeConversao)"
                    + " values (?,?,?,?,?)";
            try {
                try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    stmt.setInt(1, medida.getId());
                    stmt.setInt(2, medida.getIdIngrediente());
                    stmt.setString(3, medida.getNome());
                    stmt.setDouble(4, medida.getQuantidade());
                    stmt.setString(5, medida.getUnidadeConversao());
                    stmt.execute();
                }
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        
    }
    
    public void exclui(int idIngrediente) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql =  "delete from medida "
                    + "where idIngrediente = ?";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idIngrediente);
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void exclui(int idIngrediente, int idMedida) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql =  "delete from medida "
                    + "where idIngrediente = ? and id = ?";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idIngrediente);
                stmt.setInt(2, idMedida);
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void altera(ModelMedida medida, ModelIngrediente ingrediente) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql =  "update medida set "
                + "nome = ?,"
                + "quantidade = ?,"
                + "unidadeDeConversao = ?"
                + " where id = ? and idIngrediente = ?";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, medida.getNome());
                stmt.setDouble(2, medida.getQuantidade());
                stmt.setString(3, medida.getUnidadeConversao());
                stmt.setInt(4, medida.getId());
                stmt.setInt(5, medida.getIdIngrediente());
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
