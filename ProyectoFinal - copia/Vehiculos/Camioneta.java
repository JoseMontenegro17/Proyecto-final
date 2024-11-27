package ProyectoFinal.Vehiculos;

public class Camioneta extends Vehiculo {
    private String tipoDeServicio;
    private int numeroDePasajeros;
    private boolean tieneRemolque;

    public Camioneta(String placa,String marca, double precio, int cilindraje, String tipoDeServicio, int numeroDePasajeros,
                     boolean tieneRemolque) {
        super(placa,marca, precio, cilindraje);
        this.tipoDeServicio = tipoDeServicio;
        this.numeroDePasajeros = numeroDePasajeros;
        this.tieneRemolque = tieneRemolque;
        calcularImpuestoCirculacion();
        calcularCuotaMesGaraje();

        // Validar número de pasajeros según el tipo de servicio
        if (("Pickup".equals(tipoDeServicio) || "Carga".equals(tipoDeServicio)) && numeroDePasajeros > 2) {
            throw new IllegalArgumentException("Las camionetas Pickup y de carga no pueden tener más de 2 pasajeros.");
        } else if (numeroDePasajeros > 5) {
            throw new IllegalArgumentException("El número máximo de pasajeros para otras camionetas es 5.");
        }


    }

    // Getters y setters
    public String getTipoDeServicio() {
        return tipoDeServicio;
    }

    public void setTipoDeServicio(String tipoDeServicio) {
        this.tipoDeServicio = tipoDeServicio;
    }

    public int getNumeroDePasajeros() {
        return numeroDePasajeros;
    }

    public void setNumeroDePasajeros(int numeroDePasajeros) {
        this.numeroDePasajeros = numeroDePasajeros;
    }

    public boolean isTieneRemolque() {
        return tieneRemolque;
    }

    public void setTieneRemolque(boolean tieneRemolque) {
        this.tieneRemolque = tieneRemolque;
    }

    @Override
    public void calcularImpuestoCirculacion() {
        // El impuesto es el 5% del precio del vehículo
        double impuesto = getPrecio() * 0.05;
        setImpuestoCirculacion(impuesto);
    }


    public void calcularCuotaMesGaraje() {
        // Base definida en la clase Vehículo
        double cuotaBase = CUOTA_BASE; // Suponiendo que está definida en Vehículo como una constante
        double incremento = 0;

        // Incremento según el tipo de servicio
        if ("Suv".equals(tipoDeServicio)) {
            incremento += cuotaBase * 0.10; // Incremento del 10% para SUVs
        } else if ("Pickup".equals(tipoDeServicio) || "Carga".equals(tipoDeServicio) || "Otro".equals(tipoDeServicio)) {
            incremento += cuotaBase * 0.45; // Incremento del 45% para Pickup, Carga u Otro
        }

        // Incremento según el número de pasajeros
        if (numeroDePasajeros == 2) {
            incremento += cuotaBase * 0.50; // Incremento del 50% si tiene 2 pasajeros
        } else if (numeroDePasajeros > 2) {
            incremento += cuotaBase * 0.60; // Incremento del 60% si tiene más de 2 pasajeros
        }

        // Incremento si tiene remolque
        if (tieneRemolque) {
            incremento += cuotaBase * 0.10; // Incremento del 10% si tiene remolque
        }

        // Asignar la cuota mensual del garaje
        setCuotaMesGaraje(cuotaBase + incremento);
    }
}