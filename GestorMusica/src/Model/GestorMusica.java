package Model;

import java.util.ArrayList;
import java.util.List;

public class GestorMusica {
    private List<Artista> artistas;
    private List<ListaReproduccion> listas;

    public GestorMusica() {
        this.artistas = new ArrayList<>();
        this.listas = new ArrayList<>();
    }

    public void agregarArtista(Artista artista) {
        artistas.add(artista);
    }

    public void agregarListaReproduccion(ListaReproduccion lista) {
        listas.add(lista);
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public List<ListaReproduccion> getListas() {
        return listas;
    }
}
