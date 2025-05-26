package TCP.Cliente.Test;

import TCP.Cliente.Clase.ClienteGUI;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro de trabajadores");
        ClienteGUI gui = new ClienteGUI();
        frame.setContentPane(gui.panelRegistro);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
