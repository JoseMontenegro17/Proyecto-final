package ProyectoFinal.Vehiculos;

public class Camion extends Vehiculo {
    private int numeroDeEjes;
    private String tipoDeCamion;
    private double capacidadDeCarga;
    
    public Camion(String marca, double precio, int cilindraje, int numeroDeEjes, String tipoDeCamion,
            double capacidadDeCarga) {
        super(marca, precio, cilindraje);
        this.numeroDeEjes = numeroDeEjes;
        this.tipoDeCamion = tipoDeCamion;
        this.capacidadDeCarga = capacidadDeCarga;
        calcularImpuestoCirculacion();
        calcularCuotaMesGaraje();
        
    }
    
    public int getNumeroDeEjes() {
        return numeroDeEjes;
    }
    
    public void setNumeroDeEjes(int numeroDeEjes) {
        this.numeroDeEjes = numeroDeEjes;
    }
    
    public String getTipoDeCamion() {
        return tipoDeCamion;
    }
    
    public void setTipoDeCamion(String tipoDeCamion) {
        this.tipoDeCamion = tipoDeCamion;
    }
    
    public double getCapacidadDeCarga() {
        return capacidadDeCarga;
    }
    
    public void setCapacidadDeCarga(double capacidadDeCarga) {

        this.capacidadDeCarga = capacidadDeCarga;
    }
    
    @Override
    public void calcularImpuestoCirculacion() {
        // Impuesto es el 9% del precio más $10 por cada 5 toneladas
        double impuesto = getPrecio() * 0.09 + (capacidadDeCarga / 5) * 10;
        setImpuestoCirculacion(impuesto);
    }
    
    // Calcula la cuota mensual del garaje dependiendo del tipo de camión
    public void calcularCuotaMesGaraje() {
        double cuotaBase = CUOTA_BASE; // Suponiendo que CUOTA_BASE está definida en Vehiculo como 100
        double incremento;
    
        if ("Doble".equals(tipoDeCamion)) {
            incremento = cuotaBase * 1.25;
        } else if ("Sencillo".equals(tipoDeCamion)) {
            incremento = cuotaBase * 0.75;
        } else {
            throw new IllegalArgumentException("Tipo de camión no válido.");
        }
    
        setCuotaMesGaraje(cuotaBase + incremento);
    }
}
