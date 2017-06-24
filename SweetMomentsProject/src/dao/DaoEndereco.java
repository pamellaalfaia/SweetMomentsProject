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
            PreparedStatement stmt = conexao.prepareStatement(sql);
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
        return codigo;
    }
    
    public ArrayList<ModelEndereco> getEnderecos(ModelCliente cliente) throws SQLException {
        Connection conexao = new Conexao().getConnection();
        
        ArrayList<ModelEndereco> enderecos = new ArrayList<ModelEndereco>();
        int id, numero;
        String descricao, logradouro, bairro, complemento, tipoLogradouro;
        Double custoEntrega, distanciaEntrega,tempoMedioParaEntrega;
        
        int codigoCliente = cliente.getId();
        
        String sql = "select * from endereco e, cliente c where c.id = e.idCliente and c.id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoCliente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                numero = rs.getInt("numero");
                descricao = rs.getString("descricao");
                logradouro = rs.getString("logradouro");
                bairro = rs.getString("bairro");
                complemento = rs.getString("complemento");
                tipoLogradouro = rs.getString("tipoDeLogradouro");
                custoEntrega = rs.getDouble("custoEntrega");
                distanciaEntrega = rs.getDouble("distanciaEntrega");
                tempoMedioParaEntrega = rs.getDouble("tempoMedioEntrega");
                ModelEndereco endereco = new ModelEndereco(id, codigoCliente, numero, tempoMedioParaEntrega, descricao, logradouro, bairro, complemento, tipoLogradouro, custoEntrega, distanciaEntrega);
                enderecos.add(endereco);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        conexao.close();
        //     if (codigo == 0) {
        //         return 1;
        //     }
        return enderecos;
    }
    
    public int verificaExistenciaEndereco(int id, int idCliente) throws SQLException {
        Connection con = new Conexao().getConnection();
      
        int quantidade = 0;
        
        String sql = "select count(*) from endereco e, cliente c where c.id = e.idCliente and c.id = ? and e.id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCliente);
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
    
    public void adiciona(ArrayList<ModelEndereco> enderecos) throws SQLException, ParseException {
        for (int i = 0; i < enderecos.size(); i++) {
            Connection con = new Conexao().getConnection();
            String sql = "insert into endereco "
                    + "(id,idCliente,numero,tempoMedioEntrega,descricao,logradouro,bairro,complemento,tipoDeLogradouro,custoEntrega,distanciaEntrega)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?)";
            try {
                try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    stmt.setInt(1, enderecos.get(i).getId());
                    stmt.setInt(2, enderecos.get(i).getIdCliente());
                    stmt.setInt(3, enderecos.get(i).getNumero());
                    stmt.setDouble(4, enderecos.get(i).getTempoMedioParaEntrega());
                    stmt.setString(5, enderecos.get(i).getDescricao());
                    stmt.setString(6, enderecos.get(i).getLogradouro());
                    stmt.setString(7, enderecos.get(i).getBairro());
                    stmt.setString(8, enderecos.get(i).getComplemento());
                    stmt.setString(9, enderecos.get(i).getTipoLogradouro());
                    stmt.setDouble(10, enderecos.get(i).getCustoEntrega());
                    stmt.setDouble(11, enderecos.get(i).getDistanciaEntrega());
                    stmt.execute();
                }
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void adiciona(ModelEndereco endereco) throws SQLException, ParseException {
    //    for (int i = 0; i < enderecos.size(); i++) {
            Connection con = new Conexao().getConnection();
            String sql = "insert into endereco "
                    + "(id,idCliente,numero,tempoMedioEntrega,descricao,logradouro,bairro,complemento,tipoDeLogradouro,custoEntrega,distanciaEntrega)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?)";
            try {
                try (PreparedStatement stmt = con.prepareStatement(sql)) {
                    stmt.setInt(1, endereco.getId());
                    stmt.setInt(2, endereco.getIdCliente());
                    stmt.setInt(3, endereco.getNumero());
                    stmt.setDouble(4, endereco.getTempoMedioParaEntrega());
                    stmt.setString(5, endereco.getDescricao());
                    stmt.setString(6, endereco.getLogradouro());
                    stmt.setString(7, endereco.getBairro());
                    stmt.setString(8, endereco.getComplemento());
                    stmt.setString(9, endereco.getTipoLogradouro());
                    stmt.setDouble(10, endereco.getCustoEntrega());
                    stmt.setDouble(11, endereco.getDistanciaEntrega());
                    stmt.execute();
                }
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        
    }
    
    public void exclui(int idCliente) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql =  "delete from endereco "
                    + "where idCliente = ?";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idCliente);
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void altera(ModelEndereco endereco, ModelCliente cliente) throws SQLException, ParseException {
        Connection conexao = new Conexao().getConnection();
        String sql =  "update endereco set "
                + "numero = ?,"
                + "tempoMedioEntrega = ?,"
                + "descricao = ?,"
                + "logradouro = ?,"
                + "bairro = ?,"
                + "complemento = ?,"
                + "tipoDeLogradouro = ?,"
                + "custoEntrega = ?,"
                + "distanciaEntrega = ?"
                + " where id = ? and idCliente = ?";
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, endereco.getNumero());
                stmt.setDouble(2, endereco.getTempoMedioParaEntrega());
                stmt.setString(3, endereco.getDescricao());
                stmt.setString(4, endereco.getLogradouro());
                stmt.setString(5, endereco.getBairro());
                stmt.setString(6, endereco.getComplemento());
                stmt.setString(7, endereco.getTipoLogradouro());
                stmt.setDouble(8, endereco.getCustoEntrega());
                stmt.setDouble(9, endereco.getDistanciaEntrega());
                stmt.setInt(10, endereco.getId());
                stmt.setInt(11, endereco.getIdCliente());
                stmt.execute();
            }
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
}
