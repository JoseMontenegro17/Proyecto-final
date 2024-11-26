package ProyectoFinal.Garajes;
import ProyectoFinal.Vehiculos.*;
import java.util.ArrayList;
import java.util.List;

public class RedDeGarajes {
// Lista para almacenar los garajes
    private List<Garaje> garajes;

    // Constructor para inicializar la lista de garajes
    public RedDeGarajes() {
        this.garajes = new ArrayList<>();
    }

    // Método para crear un nuevo garaje
    public void agregarGaraje(Garaje garaje) {
        garajes.add(garaje);
        System.out.println("Garaje agregado correctamente.");
    }

    // Método para eliminar un garaje por índice
    public void eliminarGaraje(int indice) {
        if (indice >= 0 && indice < garajes.size()) {
            garajes.remove(indice);
            System.out.println("Garaje eliminado correctamente.");
        } else {
            System.out.println("Índice inválido. No se pudo eliminar el garaje.");
        }
    }

    // Método para actualizar un garaje por índice
    public void actualizarGaraje(int indice, Garaje nuevoGaraje) {
        if (indice >= 0 && indice < garajes.size()) {
            garajes.set(indice, nuevoGaraje);
            System.out.println("Garaje actualizado correctamente.");
        } else {
            System.out.println("Índice inválido. No se pudo actualizar el garaje.");
        }
    }

    // Método para ingresar un vehículo en un garaje específico
    public boolean ingresarVehiculoAGaraje(Vehiculo vehiculo, int indiceGaraje) {
        if (vehiculoRegistradoEnOtroGaraje(vehiculo.getPlaca())) {
            System.out.println("El vehículo ya está registrado en otro garaje.");
            return false;
        }

        if (indiceGaraje >= 0 && indiceGaraje < garajes.size()) {
            try {
                garajes.get(indiceGaraje).alquilarEspacio(vehiculo);
                System.out.println("Vehículo ingresado correctamente en el garaje.");
                return true;
            } catch (Exception e) {
                System.out.println("Error al ingresar el vehículo: " + e.getMessage());
            }
        } else {
            System.out.println("Índice de garaje inválido.");
        }
        return false;
    }

    // Método para retirar un vehículo de un garaje específico
    public boolean retirarVehiculoDeGaraje(String placa, int indiceGaraje) {
        if (indiceGaraje >= 0 && indiceGaraje < garajes.size()) {
            if (garajes.get(indiceGaraje).retirarVehiculo(placa)) {
                System.out.println("Vehículo retirado correctamente del garaje.");
                return true;
            } else {
                System.out.println("El vehículo no se encuentra en el garaje.");
            }
        } else {
            System.out.println("Índice de garaje inválido.");
        }
        return false;
    }

    // Método para verificar si un vehículo ya está registrado en otro garaje
    private boolean vehiculoRegistradoEnOtroGaraje(String placa) {
        for (Garaje garaje : garajes) {
            if (garaje.buscarVehiculoPorMatricula(placa) != -99) {
                return true;
            }
        }
        return false;
    }

    // Generar informe de ocupación para todos los garajes o uno en particular
    public void informeOcupacion(int indiceGaraje) {
        if (indiceGaraje == -1) {
            // Ocupación para todos los garajes
            for (int i = 0; i < garajes.size(); i++) {
                Garaje garaje = garajes.get(i);
                System.out.println("Garaje " + i + " - Ocupación: " + (garaje.getVehiculos().size()) + "/" + garaje.plazasDisponibles());
            }
        } else if (indiceGaraje >= 0 && indiceGaraje < garajes.size()) {
            // Ocupación para un garaje en particular
            Garaje garaje = garajes.get(indiceGaraje);
            System.out.println("Garaje " + indiceGaraje + " - Ocupación: " + (garaje.getVehiculos().size()) + "/" + garaje.plazasDisponibles());
        } else {
            System.out.println("Índice de garaje inválido.");
        }
    }

    // Generar informe de ocupación por tipo de vehículo
    public void informeOcupacionPorTipo(Class<? extends Vehiculo> tipoVehiculo) {
        for (int i = 0; i < garajes.size(); i++) {
            Garaje garaje = garajes.get(i);
            int cantidad = garaje.calcularOcupacionPorTipoVehiculo(tipoVehiculo);
            System.out.println("Garaje " + i + " - Ocupación de " + tipoVehiculo.getSimpleName() + ": " + cantidad);
        }
    }

    // Generar informe de recaudo mensual por cada parqueadero y total
    public void informeRecaudoMensual() {
        double totalRecaudo = 0.0;
        for (int i = 0; i < garajes.size(); i++) {
            Garaje garaje = garajes.get(i);
            double recaudoGaraje = garaje.calcularIngresos();
            System.out.println("Garaje " + i + " - Recaudo mensual: $" + recaudoGaraje);
            totalRecaudo += recaudoGaraje;
        }
        System.out.println("Recaudo total de todos los garajes: $" + totalRecaudo);
    }
}

