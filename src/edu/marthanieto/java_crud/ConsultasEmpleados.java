package edu.marthanieto.java_crud;

import edu.marthanieto.java_crud.ConexionMySQL.conectarMiBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

public class ConsultasEmpleados extends JFrame {
    private JPanel pnlConsultas;
    private JButton consultarButton;
    private JButton imprimirButton;
    private JButton salirButton;
    private JPanel pnlBtn;
    private JPanel pnlTable;
    private JTable tableConsultas;

    public ConsultasEmpleados() {
        add(pnlConsultas);
        setSize(800, 600);
        setTitle("Consulta Empleados");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //tabla
        DefaultTableModel tblModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblModel.addColumn("ID:");
        tblModel.addColumn("Nombre:");
        tblModel.addColumn("Sueldo:");
        tblModel.addColumn("Email:");
        tblModel.addColumn("Departamento:");

        tableConsultas.setModel(tblModel);
        tableConsultas.getTableHeader().setReorderingAllowed(false);
        tableConsultas.setFillsViewportHeight(true);
        tableConsultas.setPreferredScrollableViewportSize(new Dimension(700, 250));

        //BOTON CONSULTAR TABLA DE BD
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT * FROM nuevoingreso";
                String datosp[] = new String[5];
                Statement st;
                conectarMiBD mysql = new conectarMiBD();
                java.sql.Connection cn = mysql.conectar();
                try {
                    st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        datosp[0] = rs.getString(1);
                        datosp[1] = rs.getString(2);
                        datosp[2] = rs.getString(3);
                        datosp[3] = rs.getString(4);
                        datosp[4] = rs.getString(5);
                        tblModel.addRow(datosp);
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });
        //BOTON PARA IMPRIMIR BD
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageFormat header = new MessageFormat("Empleados de Base de Datos");
                MessageFormat footer = new MessageFormat("Page{0,number,integer}");
                try {
                    tableConsultas.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                } catch (PrinterException printerException) {
                    printerException.printStackTrace();
                }
            }
        });
    }
}