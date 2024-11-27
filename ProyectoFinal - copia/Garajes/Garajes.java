package ProyectoFinal.Garajes;

import java.util.ArrayList;
import ProyectoFinal.Vehiculos.*;
import ProyectoFinal.IGaraje;
import ProyectoFinal.Excepciones.*;

public class Garajes implements IGaraje {
    // Atributos de ubicación y administración
    private int id; // Identificador único, podría ser la dirección u otro valor único.
    private String departamento;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String nombreAdministrador;
    private int Plazas; // Número total de espacios del garaje

    // Atributos para la gestión del garaje
    private ArrayList<Vehiculo> vehiculos;

    // Constructor que inicializa la información del garaje
    public Garajes(int id, String departamento, String ciudad, String direccion, String telefono, String email, String nombreAdministrador, int Plazas) {
        this.id = id;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreAdministrador = nombreAdministrador;
        this.Plazas = Plazas;

        // Inicializa la colección de vehículos en el garaje
        vehiculos = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public int getPlazas() {
        return Plazas;
    }
    // Método setPlazas() para establecer el número de plazas
    public void setPlazas(int plazas) {
        if (plazas > 0) {  // Verificación opcional para asegurarse de que el número de plazas sea positivo
            this.Plazas = plazas;
        } else {
            System.out.println("El número de plazas debe ser mayor que 0.");
        }
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    // Método para determinar la cantidad de plazas disponibles en el garaje
    public int plazasDisponibles() {
        return Plazas - vehiculos.size();
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
        if (vehiculos.size() >= Plazas) {
            throw new EspacioInsuficienteException("No hay más plazas disponibles.");
        }

        // Cuenta la cantidad actual de motos
        int motosCount = (int) vehiculos.stream().filter(v -> v instanceof Moto).count();
        if (vehiculo instanceof Moto && motosCount >= (0.2 * Plazas)) {
            throw new MaximoMotosException("No se pueden ocupar más del 20% de las plazas con motos.");
        }

        // Cuenta la cantidad actual de camiones
        int camionesCount = (int) vehiculos.stream().filter(v -> v instanceof Camion).count();
        if (vehiculo instanceof Camion) {
            if (Plazas < 100 && camionesCount >= 10) {
                throw new MaximoCamionesException("No se pueden ocupar más de 10 plazas con camiones en garajes pequeños.");
            } else if (Plazas >= 100 && camionesCount >= 20) {
                throw new MaximoCamionesException("No se pueden ocupar más de 20 plazas con camiones en garajes grandes.");
            }
        }


        // Si pasa todas las validaciones, agrega el vehículo al garaje
        vehiculos.add(vehiculo);
        System.out.println("Vehículo ingresado correctamente al garaje.");
        return true;
    }

    // Método para retirar un vehículo por matrícula
    public boolean retirarVehiculo(String placa) {
        boolean removed = vehiculos.removeIf(v -> v.getPlaca().equals(placa));
        if (removed) {
            System.out.println("Vehículo retirado correctamente del garaje.");
        } else {
            System.out.println("No se encontró un vehículo con la matrícula especificada.");
        }
        return removed;
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
}
