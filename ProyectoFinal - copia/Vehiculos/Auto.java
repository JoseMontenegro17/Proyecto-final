package ProyectoFinal.Vehiculos;

public class Auto extends Vehiculo {
    private boolean tieneRadio;
    private boolean tieneNavegador;

    // Constructor
    public Auto(String placa,String marca, double precio, int cilindraje, boolean tieneRadio, boolean tieneNavegador) {
        super(placa,marca, precio, cilindraje);
        this.tieneRadio = tieneRadio;
        this.tieneNavegador = tieneNavegador;
        ajustarValores();
    }

    private void ajustarValores() {
        if (tieneRadio) {
            setImpuestoCirculacion(getImpuestoCirculacion() * 0.01);  // Incremento del 1%
        }
        if (tieneNavegador) {
            setImpuestoCirculacion(getImpuestoCirculacion() * 0.02);  // Incremento del 2%
        }
        if (getCilindraje() > 2499) {
            setCuotaMesGaraje(getCuotaMesGaraje() * 0.2);  // Incremento del 20% en la cuota mensual
        }
    }

    public boolean isTieneRadio() {
        return tieneRadio;
    }

    public boolean isTieneNavegador() {
        return tieneNavegador;
    }
}