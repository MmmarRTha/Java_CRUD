package edu.marthanieto.java_crud.ConexionMySQL;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectarMiBD {
    private static String url = "jdbc:mysql://localhost:3306/pruebasui1";
    private static String user = "root";
    private static String pass = "";

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
}
