package edu.marthanieto.java_crud;

import javax.swing.*;

public class Login extends JFrame {
    private JPanel pnlLogin;
    private JTextField txtUsua;
    private JPasswordField txtPass;
    private JButton ingresarButton;
    private JPanel pnlIngresar;

    public Login() {
        add(pnlLogin);
        setSize(400, 230);
        setLocationRelativeTo(getComponentAt(100, 100));

        //Boton Login
        ingresarButton.addActionListener(e -> {
            String userN = txtUsua.getText();
            String passW = String.valueOf(txtPass.getPassword());

            if (userN.contains("MarthaN") && passW.contains("pruebaBD")) {
                MainPanel mostrarP = new MainPanel();
                mostrarP.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectas. Intente de nuevo");
                txtUsua.setText(null);
                txtPass.setText(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}