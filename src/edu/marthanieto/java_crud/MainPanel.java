package edu.marthanieto.java_crud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame implements ActionListener {
    JMenu empleados, consultas;
    JMenuItem itemEmpl, itemConsult;
    private JPanel mainPnl;

    public MainPanel() {
        setSize(820, 620);
        setTitle("Programa Java conexion a MySQL");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar barramenu = new JMenuBar();
        setJMenuBar(barramenu);

        JMenu menu = new JMenu("Menu");
        barramenu.add(menu);

        JMenu empleados = new JMenu("Empleados");
        menu.add(empleados);

        JMenuItem formempleados = new JMenuItem("Formulario Empleados");
        formempleados.addActionListener(this);
        formempleados.setActionCommand("Formulario Empleados");
        empleados.add(formempleados);

        JMenu consultas = new JMenu("Consultas");
        menu.add(consultas);

        JMenuItem impconsul = new JMenuItem("Consultas Empleados");
        impconsul.addActionListener(this);
        impconsul.setActionCommand("Consulta Empleados");
        consultas.add(impconsul);

        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(this);
        salir.setActionCommand("Salir");
        menu.add(salir);
    }

    //Eventos para menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if (null != e.getActionCommand()) switch (e.getActionCommand()) {
            case "Formulario Empleados":
                FormularioEmpleados mostrar = new FormularioEmpleados();
                mostrar.setVisible(true);
                break;
            case "Consulta Empleados":
                ConsultasEmpleados ver = new ConsultasEmpleados();
                ver.setVisible(true);
                break;
            case "Salir":
                System.exit(0);
            default:
                break;
        }

    }

}
