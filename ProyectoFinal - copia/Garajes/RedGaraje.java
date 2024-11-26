package ProyectoFinal.Garajes;

import java.util.ArrayList;
import java.util.List;

public class RedGaraje {
    private List<Garaje> garajes;

    public RedGaraje() {
        garajes = new ArrayList<>();
    }

    public void agregarGaraje(Garaje garaje) {
        garajes.add(garaje);
    }

    public Garaje buscarPorCiudad(String ciudad) {
        for (Garaje garaje : garajes) {
            if (garaje.getCiudad().equalsIgnoreCase(ciudad)) {
                return garaje;
            }
        }
        return null;
    }

    public void mostrarTodosLosGarajes() {
        for (Garaje garaje : garajes) {
            garaje.mostrarInformacionGaraje();
        }
    }
}

