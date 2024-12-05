package UI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class PanelListas extends JPanel {
    private GestorMusica gestorMusica;
    private DefaultListModel<ListaReproduccion> modeloListas;
    private JList<ListaReproduccion> listaReproduccion;

    public PanelListas(GestorMusica gestorMusica) {
        this.gestorMusica = gestorMusica;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Listas de Reproducción"));

        modeloListas = new DefaultListModel<>();
        listaReproduccion = new JList<>(modeloListas);

        JButton btnCrearLista = new JButton("Crear Lista");
        btnCrearLista.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre de la nueva lista:");
            if (nombre != null && !nombre.isEmpty()) {
                ListaReproduccion nuevaLista = new ListaReproduccion(nombre);
                modeloListas.addElement(nuevaLista);
                gestorMusica.agregarListaReproduccion(nuevaLista);
            }
        });

        add(new JScrollPane(listaReproduccion), BorderLayout.CENTER);
        add(btnCrearLista, BorderLayout.SOUTH);
    }
}
