package UI;

import Model.*;
import javax.swing.*;
import java.awt.*;


public class VentanaPrincipal extends JFrame {
    private GestorMusica gestorMusica;
    private JTextArea areaListas, areaArtistas, areaCanciones;

    public VentanaPrincipal() {
        // Inicialización del gestor y carga de datos
        gestorMusica = new GestorMusica();
        FileManager.cargarDatos(gestorMusica);

        setTitle("APOSpotify");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        areaListas = new JTextArea(10, 20);
        areaArtistas = new JTextArea(10, 20);
        areaCanciones = new JTextArea(10, 20);

        // Crear los paneles
        JPanel panelListas = crearPanelListas();
        JPanel panelArtistas = crearPanelArtistas();
        JPanel panelCanciones = crearPanelCanciones();

        // Agregar los paneles a la ventana
        add(panelListas, BorderLayout.WEST);
        add(panelArtistas, BorderLayout.CENTER);
        add(panelCanciones, BorderLayout.EAST);

        // Actualizar áreas de texto con datos iniciales
        actualizarAreaListas();
        actualizarAreaArtistas();
        actualizarAreaCanciones();

        // Guardar datos automáticamente al cerrar la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                FileManager.guardarDatos(gestorMusica);
            }
        });
    }

    private JPanel crearPanelListas() {
        JPanel panel = crearPanel("Mis Listas", areaListas);

        JButton btnCrearLista = new JButton("Crear Lista");
        btnCrearLista.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre de la nueva lista:");
            if (nombre != null && !nombre.isEmpty()) {
                ListaReproduccion nuevaLista = new ListaReproduccion(nombre);
                gestorMusica.agregarListaReproduccion(nuevaLista);
                actualizarAreaListas();
            }
        });

        panel.add(btnCrearLista, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelArtistas() {
        JPanel panel = crearPanel("Artistas", areaArtistas);

        JButton btnCrearArtista = new JButton("Crear Artista");
        btnCrearArtista.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del artista:");
            if (nombre != null && !nombre.isEmpty()) {
                Artista nuevoArtista = new Artista(nombre);
                gestorMusica.agregarArtista(nuevoArtista);
                actualizarAreaArtistas();
            }
        });

        panel.add(btnCrearArtista, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanelCanciones() {
        JPanel panel = crearPanel("Canciones", areaCanciones);

        JButton btnCrearCancion = new JButton("Crear Canción");
        btnCrearCancion.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog(this, "Título de la canción:");
            if (titulo == null || titulo.isEmpty()) return;

            // Seleccionar artista existente
            Object[] artistas = gestorMusica.getArtistas().toArray();
            if (artistas.length == 0) {
                JOptionPane.showMessageDialog(this, "No hay artistas disponibles.");
                return;
            }

            Artista artista = (Artista) JOptionPane.showInputDialog(this, "Seleccione un artista:",
                    "Artista", JOptionPane.QUESTION_MESSAGE, null, artistas, artistas[0]);
            if (artista == null) return;

            // Crear canción y asociarla a una lista
            Cancion nuevaCancion = new Cancion(titulo, artista);

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
                actualizarAreaCanciones();
                actualizarAreaListas();
            }
        });

        panel.add(btnCrearCancion, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearPanel(String titulo, JTextArea area) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    private void actualizarAreaListas() {
        areaListas.setText("");
        for (ListaReproduccion lista : gestorMusica.getListas()) {
            areaListas.append(lista.getNombre() + " (" + lista.getCanciones().size() + " canciones)\n");
        }
    }

    private void actualizarAreaArtistas() {
        areaArtistas.setText("");
        for (Artista artista : gestorMusica.getArtistas()) {
            areaArtistas.append(artista.getNombre() + "\n");
        }
    }

    private void actualizarAreaCanciones() {
        areaCanciones.setText("");
        for (ListaReproduccion lista : gestorMusica.getListas()) {
            for (Cancion cancion : lista.getCanciones()) {
                areaCanciones.append(cancion.getTitulo() + " - " + cancion.getArtista().getNombre() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
