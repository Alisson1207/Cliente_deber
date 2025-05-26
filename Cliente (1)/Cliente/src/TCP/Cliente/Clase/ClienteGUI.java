package TCP.Cliente.Clase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteGUI {
    public JPanel panelRegistro;
    private JTextField campoNombre;
    private JComboBox<String> comboRegistros;
    private JButton botonRegistrar;

    public ClienteGUI() {
        panelRegistro = new JPanel();
        panelRegistro.setLayout(new GridBagLayout());
        panelRegistro.setBackground(new Color(0, 51, 102));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelNombre = new JLabel("Nombre del empleado:");
        labelNombre.setForeground(Color.WHITE);
        campoNombre = new JTextField(20);

        JLabel labelRegistro = new JLabel("Tipo de registro:");
        labelRegistro.setForeground(Color.WHITE);
        comboRegistros = new JComboBox<>(new String[]{
                "Ingreso al trabajo", "Salida al almuerzo",
                "Ingreso del almuerzo", "Salida del trabajo"
        });

        botonRegistrar = new JButton("Registrar");
        botonRegistrar.setPreferredSize(new Dimension(120, 35));
        botonRegistrar.setBackground(new Color(33, 150, 243));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFocusPainted(false);

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelRegistro.add(labelNombre, gbc);
        gbc.gridx = 1;
        panelRegistro.add(campoNombre, gbc);

        // Registro
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelRegistro.add(labelRegistro, gbc);
        gbc.gridx = 1;
        panelRegistro.add(comboRegistros, gbc);

        // Botón
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelRegistro.add(botonRegistrar, gbc);

        // Acción del botón
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText().trim();
                String tipoRegistro = (String) comboRegistros.getSelectedItem();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del empleado.");
                    return;
                }

                try {
                    String respuesta = Cliente.enviarNombre(nombre);
                    String contenido = "Empleado: " + nombre + "\nTipo: " + tipoRegistro + "\n" + respuesta + "\n";

                    String ruta = "C:/Users/Personal/Documents/UNIVERSIDAD/EPN/QUINTO SEMESTRE/AP DISTRIBUIDAS/ReporteEmpleado.dat";
                    new Cliente().escribir(contenido, ruta);

                    JOptionPane.showMessageDialog(null, "Registro guardado con éxito.");

                    campoNombre.setText("");
                    comboRegistros.setSelectedIndex(0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar.");
                }
            }
        });
    }
}
