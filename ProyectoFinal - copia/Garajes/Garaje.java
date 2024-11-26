package ProyectoFinal.Garajes;

import java.util.ArrayList;
import ProyectoFinal.Vehiculos.*;
import ProyectoFinal.IGaraje;
import ProyectoFinal.Excepciones.*;


public class Garaje implements IGaraje {
// Atributos de ubicación y administración
    private String departamento;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String nombreAdministrador;

    // Atributos para la gestión del garaje
    private int numeroEspacios;  // Número total de espacios del garaje
    private ArrayList<Vehiculo> vehiculos;

    // Constructor que inicializa la información del garaje
    public Garaje(String departamento, String ciudad, String direccion, String telefono, String email, String nombreAdministrador, int numeroEspacios) {
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreAdministrador = nombreAdministrador;
        this.numeroEspacios = numeroEspacios;

        // Inicializa la colección de vehículos en el garaje
        vehiculos = new ArrayList<>();
    }

    // Método para buscar un vehículo por matrícula y retornar su posición
    public int buscarVehiculoPorMatricula(String placa) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPlaca().equals(placa)) {
                System.out.println("Vehículo encontrado en la posición: " + i);
                return i;
            }
        }
        System.out.println("Vehículo no encontrado en el garaje.");
        return -99; // Si no encuentra el vehículo, retorna -99
    }

    // Método para alquilar espacio, controlando los porcentajes máximos de motos y camiones
    public boolean alquilarEspacio(Vehiculo vehiculo) throws EspacioInsuficienteException, MaximoMotosException, MaximoCamionesException, VehiculoNoMatriculadoException {
        // Verifica si hay espacios disponibles
        if (vehiculos.size() >= numeroEspacios) {
            throw new EspacioInsuficienteException("No hay más plazas disponibles.");
        }

        // Cuenta la cantidad actual de motos
        int motosCount = (int) vehiculos.stream().filter(v -> v instanceof Moto).count();
        if (vehiculo instanceof Moto && motosCount >= (0.2 * numeroEspacios)) {
            throw new MaximoMotosException("No se pueden ocupar más del 20% de las plazas con motos.");
        }

        // Cuenta la cantidad actual de camiones
        int camionesCount = (int) vehiculos.stream().filter(v -> v instanceof Camion).count();
        if (vehiculo instanceof Camion) {
            if (numeroEspacios < 100 && camionesCount >= 10) {
                throw new MaximoCamionesException("No se pueden ocupar más de 10 plazas con camiones en garajes pequeños.");
            } else if (numeroEspacios >= 100 && camionesCount >= 20) {
                throw new MaximoCamionesException("No se pueden ocupar más de 20 plazas con camiones en garajes grandes.");
            }
        }

        // Verifica que el vehículo esté matriculado
        if (vehiculo.getPlaca() == null) {
            throw new VehiculoNoMatriculadoException("El vehículo no está matriculado.");
        }

        // Si pasa todas las validaciones, agrega el vehículo al garaje
        vehiculos.add(vehiculo);
        return true;
    }

    // Método para retirar un vehículo por matrícula
    public boolean retirarVehiculo(String placa) {
        return vehiculos.removeIf(v -> v.getPlaca().equals(placa));
    }

    // Getter para obtener la lista de vehículos en el garaje
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    // Método para calcular la suma de ingresos mensuales
    @Override
    public double calcularIngresos() {
        return vehiculos.stream().mapToDouble(Vehiculo::getCuotaMesGaraje).sum();
    }

    // Método para calcular la ocupación por tipo de vehículo
    @Override
    public int calcularOcupacionPorTipoVehiculo(Class<? extends Vehiculo> tipoVehiculo) {
        return (int) vehiculos.stream().filter(tipoVehiculo::isInstance).count();
    }

    // Método para determinar la cantidad de plazas disponibles en el garaje
    public int plazasDisponibles() {
        return numeroEspacios - vehiculos.size();
    }

    // Método para listar los vehículos con su matrícula, cuota y tipo
    public void listarVehiculos() {
        vehiculos.forEach(v -> {
            System.out.println("Matrícula: " + v.getPlaca() + ", Cuota: " + v.getCuotaMesGaraje() + ", Tipo: " + v.getClass().getSimpleName());
        });
    }

    // Método para calcular la proporción Auto / Moto / Camión / Camioneta
    public void calcularProporcionVehiculos() {
        int autosCount = (int) vehiculos.stream().filter(v -> v instanceof Auto).count();
        int motosCount = (int) vehiculos.stream().filter(v -> v instanceof Moto).count();
        int camionesCount = (int) vehiculos.stream().filter(v -> v instanceof Camion).count();
        int camionetasCount = (int) vehiculos.stream().filter(v -> v instanceof Camioneta).count();

        System.out.printf("Proporción:\n Autos: %d, Motos: %d, Camiones: %d, Camionetas: %d%n", autosCount, motosCount, camionesCount, camionetasCount);
    }

    // Método para informar la cantidad de camiones por tipo (Sencillo/Doble)
    public void informarCantidadCamionesPorTipo() {
        int sencilloCount = (int) vehiculos.stream().filter(v -> v instanceof Camion && ((Camion) v).getTipoDeCamion().equals("Sencillo")).count();
        int dobleCount = (int) vehiculos.stream().filter(v -> v instanceof Camion && ((Camion) v).getTipoDeCamion().equals("Doble")).count();

        System.out.printf("Camiones: Sencillos: %d, Dobles: %d%n", sencilloCount, dobleCount);
    }

    // Método para informar la cantidad de camionetas por tipo (Suv/Pickup/Carga/Otro)
    public void informarCantidadCamionetasPorTipo() {
        int suvCount = (int) vehiculos.stream().filter(v -> v instanceof Camioneta && ((Camioneta) v).getTipoDeServicio().equals("Suv")).count();
        int pickupCount = (int) vehiculos.stream().filter(v -> v instanceof Camioneta && ((Camioneta) v).getTipoDeServicio().equals("Pickup")).count();
        int cargaCount = (int) vehiculos.stream().filter(v -> v instanceof Camioneta && ((Camioneta) v).getTipoDeServicio().equals("Carga")).count();
        int otroCount = (int) vehiculos.stream().filter(v -> v instanceof Camioneta && ((Camioneta) v).getTipoDeServicio().equals("Otro")).count();

        System.out.printf("Camionetas: Suv: %d, Pickup: %d, Carga: %d, Otro: %d%n", suvCount, pickupCount, cargaCount, otroCount);
    }

    public void mostrarInformacionGaraje() {
    }


    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void registrarVehiculo(Vehiculo auto1) {
    }
}
