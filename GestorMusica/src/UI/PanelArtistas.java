package UI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class PanelArtistas extends JPanel {
    private GestorMusica gestorMusica;
    private DefaultListModel<Artista> modeloArtistas;
    private JList<Artista> listaArtistas;

    public PanelArtistas(GestorMusica gestorMusica) {
        this.gestorMusica = gestorMusica;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Artistas"));

        modeloArtistas = new DefaultListModel<>();
        listaArtistas = new JList<>(modeloArtistas);

        JButton btnCrearArtista = new JButton("Crear Artista");
        btnCrearArtista.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del artista:");
            if (nombre != null && !nombre.isEmpty()) {
                Artista nuevoArtista = new Artista(nombre);
                modeloArtistas.addElement(nuevoArtista);
                gestorMusica.agregarArtista(nuevoArtista);
            }
        });

        add(new JScrollPane(listaArtistas), BorderLayout.CENTER);
        add(btnCrearArtista, BorderLayout.SOUTH);
    }
}
