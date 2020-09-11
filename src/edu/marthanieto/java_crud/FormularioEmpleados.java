package edu.marthanieto.java_crud;

import edu.marthanieto.java_crud.ConexionMySQL.conectarMiBD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioEmpleados extends JFrame {
    String data[];
    Connection todoBien = conectarMiBD.conectar();
    private JPanel rootPanel;
    private JPanel headPanel;
    private JTextField txNom;
    private JTextField txSueldo;
    private JTextField txEmail;
    private JTextField txDepto;
    private JButton guardarEnBDButton;
    private JButton borrarButton;
    private JLabel nomLabel;
    private JLabel sueldoLabel;
    private JLabel emailLabel;
    private JLabel compLabel;
    private JPanel inTxPanel;
    private JTable showTable;
    private JPanel tablePanel;
    private JScrollPane tablaCaptura;
    private JPanel botonPanel;
    private JButton actualizarButton1;
    private JTextField txID;
    private JLabel idLabel;
    private JButton modificarButton;
    private JButton buscarButton;
    private JPanel pnlBuscar;

    public FormularioEmpleados() {
        add(rootPanel);
        setSize(800, 600);
        setTitle("Formulario Empleados");
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

        showTable.setModel(tblModel);
        showTable.getTableHeader().setReorderingAllowed(false);
        showTable.setFillsViewportHeight(true);

        //BOTON BORRAR REGISTRO BD
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                PreparedStatement ps;
                try {
                    cn = conectarMiBD.conectar();
                    ps = cn.prepareStatement("DELETE FROM nuevoingreso WHERE Id_Ingreso=?");
                    ps.setInt(1, Integer.parseInt(txID.getText()));

                    int res = ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Registro eliminado");
                    limpiarTxt();
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception);
                    exception.printStackTrace();
                }
            }
        });

        //BOTON GUARDAR INFO DE EMPLEADO EN BASE DE DATOS
        guardarEnBDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement agrega = todoBien.prepareStatement("INSERT INTO nuevoingreso (Id_Ingreso,nombre_ingreso,sueldo_ingreso,email_ingreso,depto_ingreso) VALUES (?,?,?,?,?)");
                    agrega.setString(1, txID.getText());
                    agrega.setString(2, txNom.getText());
                    agrega.setString(3, txSueldo.getText());
                    agrega.setString(4, txEmail.getText());
                    agrega.setString(5, txDepto.getText());
                    agrega.executeUpdate();
                    JOptionPane.showMessageDialog(null, "El registro se agregó con éxito.");
                    limpiarTxt();
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, "Error, no se pudo guardar el registro.");
                    exception.printStackTrace();
                }
            }
        });

        //BOTON BUSCAR EN BD
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                PreparedStatement ps;
                try {
                    cn = conectarMiBD.conectar();
                    ps = cn.prepareStatement("SELECT * FROM nuevoingreso WHERE Id_Ingreso=?");
                    ps.setInt(1, Integer.parseInt(txID.getText()));
                    ResultSet res;
                    res = ps.executeQuery();
                    if (res.next()) {
                        txID.setText(res.getString("Id_Ingreso"));
                        txNom.setText(res.getString("nombre_ingreso"));
                        txSueldo.setText(res.getString("sueldo_ingreso"));
                        txEmail.setText(res.getString("email_ingreso"));
                        txDepto.setText(res.getString("depto_ingreso"));
                        JOptionPane.showMessageDialog(null, "Empleado encontrado en Base de Datos.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe registro con ese ID.");
                    }
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, "Error no se pudo buscar empleado");
                    exception.printStackTrace();
                }
            }
        });

        //BOTON MODIFICAR CONTACTO
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                PreparedStatement ps;
                try {
                    cn = conectarMiBD.conectar();
                    ps = cn.prepareStatement("UPDATE nuevoingreso SET nombre_ingreso=?,sueldo_ingreso=?,email_ingreso=?,depto_ingreso=? WHERE Id_Ingreso=?");
                    ps.setString(1, txNom.getText());
                    ps.setString(2, txSueldo.getText());
                    ps.setString(3, txEmail.getText());
                    ps.setString(4, txDepto.getText());
                    ps.setString(5, txID.getText());

                    int res = ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null, "La modificacion se realizo con éxito");
                        limpiarTxt();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error, no se pudo actualizar el registro.");
                        limpiarTxt();
                    }
                } catch (SQLException exception) {
                    JOptionPane.showMessageDialog(null, exception);
                    exception.printStackTrace();
                }
            }
        });
    }

    //metodo para limpiar cajas de texto
    private void limpiarTxt() {
        txID.setText("");
        txNom.setText("");
        txSueldo.setText("");
        txEmail.setText("");
        txDepto.setText("");
    }
}
