package ProyectoFinal.Vehiculos;

public abstract class Vehiculo {
// Atributos
private String placa = null;  // Se inicia por defecto con valor null
private String marca;
private double precio;
private int cilindraje;
private double impuestoCirculacion;
private double cuotaMesGaraje;

// Constante de clase para la cuota mensual
public static final double CUOTA_BASE = 100;

// Constructor
public Vehiculo(String marca, double precio, int cilindraje) {
    this.marca = marca;
    this.precio = precio;
    this.cilindraje = cilindraje;
    this.cuotaMesGaraje = CUOTA_BASE;
    calcularImpuestoCirculacion();  // Calcula el impuesto al crearse el objeto
}

// Getters y setters
public String getPlaca() {
    return placa;
}

public String getMarca() {
    return marca;
}

public void setMarca(String marca) {
    this.marca = marca;
}

public double getPrecio() {
    return precio;
}

public void setPrecio(double precio) {
    this.precio = precio;
}

public int getCilindraje() {
    return cilindraje;
}

public void setCilindraje(int cilindraje) {
    this.cilindraje = cilindraje;
}

public double getImpuestoCirculacion() {
    return impuestoCirculacion;
}
// Nuevo setter para impuestoCirculacion
public void setImpuestoCirculacion(double impuestoCirculacion) {
    this.impuestoCirculacion = impuestoCirculacion;
}

public double getCuotaMesGaraje() {
    return cuotaMesGaraje;
}

public void setCuotaMesGaraje(double cuotaMesGaraje) {
    if (cuotaMesGaraje >= 0) {
        this.cuotaMesGaraje = cuotaMesGaraje;
    } else {
        System.out.println("La cuota mensual no puede ser negativa.");
    }
}

// Métodos
public void calcularImpuestoCirculacion() {
    this.impuestoCirculacion = this.precio * 0.02;  // 2% del precio del vehículo
}

public boolean matricular(String matricula) {
    if (matricula != null && matricula.length() == 6) {
        this.placa = matricula;
        return true;
    }
    return false;
}
}
