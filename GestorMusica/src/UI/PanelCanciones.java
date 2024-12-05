package UI;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class PanelCanciones extends JPanel {
    private GestorMusica GestorMusica;
    private DefaultListModel<Cancion> modeloCanciones;

    public PanelCanciones(GestorMusica gestorMusica) {
        this.GestorMusica = gestorMusica;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Canciones"));

        modeloCanciones = new DefaultListModel<>();
        JList<Cancion> listaCanciones = new JList<>(modeloCanciones);

        JButton btnCrearCancion = new JButton("Crear Canción");
        btnCrearCancion.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog(this, "Título de la canción:");
            if (titulo == null || titulo.isEmpty()) return;

            Object[] artistas = gestorMusica.getArtistas().toArray();
            if (artistas.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay artistas disponibles.");
                return;
            }

            Artista artista = (Artista) JOptionPane.showInputDialog(this, "Seleccione un artista:",
                    "Artista", JOptionPane.QUESTION_MESSAGE, null, artistas, artistas[0]);
            if (artista == null) return;

            Cancion nuevaCancion = new Cancion(titulo, artista);
            modeloCanciones.addElement(nuevaCancion);

            Object[] listas = gestorMusica.getListas().toArray();
            if (listas.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay listas disponibles.");
                return;
            }

            ListaReproduccion lista = (ListaReproduccion) JOptionPane.showInputDialog(this,
                    "Seleccione una lista para agregar la canción:", "Lista",
                    JOptionPane.QUESTION_MESSAGE, null, listas, listas[0]);

            if (lista != null) {
                lista.agregarCancion(nuevaCancion);
            }
        });

        add(new JScrollPane(listaCanciones), BorderLayout.CENTER);
        add(btnCrearCancion, BorderLayout.SOUTH);
    }
}
