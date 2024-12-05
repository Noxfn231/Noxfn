package Model;

import java.util.ArrayList;
import java.util.List;

public class ListaReproduccion {
    private String nombre;
    private List<Cancion> canciones;

    public ListaReproduccion(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void agregarCancion(Cancion cancion) {
        canciones.add(cancion);
    }

    @Override
    public String toString() {
        return nombre + " (" + canciones.size() + " canciones)";
    }
}
