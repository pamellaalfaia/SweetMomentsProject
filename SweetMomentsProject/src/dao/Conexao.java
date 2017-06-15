/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pamella
 */
public class Conexao {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/doceira", "root", "1995");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
