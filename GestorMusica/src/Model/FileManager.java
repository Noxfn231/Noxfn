package Model;

import java.io.*;

public class FileManager {
    private static final String ARCHIVO_LISTAS = "listas.txt";

    public static void cargarDatos(GestorMusica gestorMusica) {
        File archivo = new File(ARCHIVO_LISTAS);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            ListaReproduccion listaActual = null;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Lista:")) {
                    listaActual = new ListaReproduccion(linea.replace("Lista:", "").trim());
                    gestorMusica.agregarListaReproduccion(listaActual);
                } else if (listaActual != null && !linea.isBlank()) {
                    String[] partes = linea.split(" - ");
                    if (partes.length == 2) {
                        Artista artista = new Artista(partes[1]);
                        gestorMusica.agregarArtista(artista);
                        Cancion cancion = new Cancion(partes[0], artista);
                        listaActual.agregarCancion(cancion);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarDatos(GestorMusica gestorMusica) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_LISTAS))) {
            for (ListaReproduccion lista : gestorMusica.getListas()) {
                pw.println("Lista:" + lista.getNombre());
                for (Cancion cancion : lista.getCanciones()) {
                    pw.println(cancion.getTitulo() + " - " + cancion.getArtista().getNombre());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
