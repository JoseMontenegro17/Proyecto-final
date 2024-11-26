package ProyectoFinal.Vehiculos;

public class Moto extends Vehiculo {
    private boolean tieneSidecar;

    // Constructor
    public Moto(String marca, double precio, int cilindraje, boolean tieneSidecar) {
        super(marca, precio, cilindraje);
        this.tieneSidecar = tieneSidecar;
        ajustarValores();
    }

    private void ajustarValores() {
        if (tieneSidecar) {
            // Incremento del 10% en el impuesto de circulación
            setCuotaMesGaraje(getCuotaMesGaraje() * 0.5);  // Incremento del 50% en la cuota mensual
            calcularImpuestoCirculacion(); // Ajuste al impuesto con el sidecar
            setImpuestoCirculacion(getImpuestoCirculacion() * 0.1);
        }
    }

    public boolean isTieneSidecar() {

        return tieneSidecar;
    }                 
}
