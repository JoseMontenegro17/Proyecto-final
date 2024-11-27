package ProyectoFinal.Garajes;

import ProyectoFinal.Vehiculos.*;
import java.util.ArrayList;
import java.util.List;
import ProyectoFinal.Excepciones.*;

public class RedDeParqueaderos {
    private List<Garajes> garajes;

    public RedDeParqueaderos() {
        this.garajes = new ArrayList<>();
    }

    // Método para agregar un garaje a la red
    public void agregarGaraje(Garajes garaje) {
        garajes.add(garaje);
        System.out.println("Garaje agregado correctamente.");
    }

    // Método para eliminar un garaje
    public boolean eliminarGaraje(int id) {
        for (Garajes garaje : garajes) {
            if (garaje.getId() == id) {
                garajes.remove(garaje);
                System.out.println("Garaje eliminado correctamente.");
                return true;
            }
        }
        System.out.println("No se encontró ningún garaje con el ID especificado.");
        return false;
    }

    // Método para actualizar un garaje
    public boolean actualizarGaraje(int id, Garajes nuevoGaraje) {
        for (int i = 0; i < garajes.size(); i++) {
            if (garajes.get(i).getId() == id) {
                // Obtén el garaje existente
                Garajes garajeExistente = garajes.get(i);

                // Solo actualiza los atributos del garaje sin reemplazar los vehículos
                garajeExistente.setDepartamento(nuevoGaraje.getDepartamento());
                garajeExistente.setCiudad(nuevoGaraje.getCiudad());
                garajeExistente.setDireccion(nuevoGaraje.getDireccion());
                garajeExistente.setTelefono(nuevoGaraje.getTelefono());
                garajeExistente.setEmail(nuevoGaraje.getEmail());
                garajeExistente.setNombreAdministrador(nuevoGaraje.getNombreAdministrador());
                garajeExistente.setPlazas(nuevoGaraje.getPlazas());

                System.out.println("Garaje actualizado correctamente.");
                return true;
            }
        }
        System.out.println("No se encontró ningún garaje con el ID especificado.");
        return false;
    }

    // Método para ingresar un vehículo en un garaje
    public boolean ingresarVehiculoAGaraje(String placa, int idGaraje, Vehiculo vehiculo) {
        if (placa.length() != 6) {
            System.out.println("Error: La placa no es válida.");
            return false;  // Si la placa no es válida, retornamos false
        }
        // Intentar establecer la placa en el vehículo
        vehiculo.setPlaca(placa);  // Esto llamará la validación interna


        // Verificar si el vehículo ya está registrado en otro garaje
        if (vehiculoRegistradoEnOtroGaraje(placa)) {
            System.out.println("El vehículo ya está registrado en otro garaje.");
            return false;
        }

        Garajes garaje = buscarGarajePorId(idGaraje);
        if (garaje != null) {
            // Verificar si hay espacio disponible
            if (garaje.plazasDisponibles() > 0) {
                // Asignar la placa al vehículo
                vehiculo.setPlaca(placa);

                try {
                    // Intentar ingresar el vehículo en el garaje
                    garaje.alquilarEspacio(vehiculo);
                    System.out.println("Vehículo ingresado correctamente en el garaje.");
                    return true;
                } catch (EspacioInsuficienteException | MaximoMotosException | MaximoCamionesException | VehiculoNoMatriculadoException e) {
                    System.out.println("Error al ingresar el vehículo: " + e.getMessage());
                }
            } else {
                System.out.println("No hay espacios disponibles en este garaje.");
            }
        } else {
            System.out.println("ID de garaje inválido.");
        }
        return false;
    }

    // Método para retirar un vehículo de un garaje
    public boolean retirarVehiculoDeGaraje(String placa, int idGaraje) {
        Garajes garaje = buscarGarajePorId(idGaraje);
        if (garaje != null) {
            if (garaje.retirarVehiculo(placa)) {
                System.out.println("Vehículo retirado correctamente del garaje.");
                return true;
            } else {
                System.out.println("El vehículo no se encuentra en el garaje.");
            }
        } else {
            System.out.println("ID de garaje inválido.");
        }
        return false;
    }

    // Verificar si el vehículo está registrado en otro garaje
    private boolean vehiculoRegistradoEnOtroGaraje(String placa) {
        for (Garajes garaje : garajes) {
            if (garaje.buscarVehiculoPorMatricula(placa) != -99) {
                return true;
            }
        }
        return false;
    }

    // Buscar un garaje por ID
    public Garajes buscarGarajePorId(int id) {
        for (Garajes garaje : garajes) {
            if (garaje.getId() == id) {
                return garaje;
            }
        }
        System.out.println("No se encontró ningún garaje con el ID especificado: " + id);
        return null;
    }

    // Método para calcular el total de recaudo de todos los garajes
    public double totalRecaudo() {
        double total = 0.0;
        for (Garajes garaje : garajes) {
            total += garaje.calcularIngresos();
        }
        System.out.println("Recaudo total de todos los garajes: $" + total);
        return total;
    }

    // Informe de ocupación para todos los garajes
    public void informeOcupacion() {
        for (Garajes garaje : garajes) {
            System.out.println("Garaje ID " + garaje.getId() + " - Ocupación: " + garaje.getVehiculos().size() + "/" + garaje.getPlazas());
        }
    }

    // Informe de ocupación para un garaje específico
    public void informeOcupacion(int idGaraje) {
        Garajes garaje = buscarGarajePorId(idGaraje);
        if (garaje != null) {
            System.out.println("Garaje ID " + garaje.getId() + " - Ocupación: " + garaje.getVehiculos().size() + "/" + garaje.getPlazas());
        }
    }

    // Informe de ocupación por tipo de vehículo
    public void informeOcupacionPorTipo(Class<? extends Vehiculo> tipoVehiculo) {
        for (Garajes garaje : garajes) {
            int cantidad = garaje.calcularOcupacionPorTipoVehiculo(tipoVehiculo);
            System.out.println("Garaje ID " + garaje.getId() + " - Ocupación de " + tipoVehiculo.getSimpleName() + ": " + cantidad);
        }
    }

    // Informe de recaudo mensual
    public void informeRecaudoMensual() {
        double totalRecaudo = 0.0;
        for (Garajes garaje : garajes) {
            double recaudoGaraje = garaje.calcularIngresos();
            System.out.println("Garaje ID " + garaje.getId() + " - Recaudo mensual: $" + recaudoGaraje);
            totalRecaudo += recaudoGaraje;
        }
        System.out.println("Recaudo total de todos los garajes: $" + totalRecaudo);
    }
}