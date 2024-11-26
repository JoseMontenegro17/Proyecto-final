package ProyectoFinal;

import  ProyectoFinal.Vehiculos.*;

public interface IGaraje {
    double calcularIngresos();
    int calcularOcupacionPorTipoVehiculo(Class<? extends Vehiculo> tipoVehiculo);/*Class<? extends Vehiculo> permite que el parámetro tipoVehiculo sea cualquier clase que extienda la clase Vehiculo.
    El método calcularOcupacionPorTipoVehiculo recorre los vehículos en el garaje y cuenta cuántos de ellos son del tipo específico que se pasa como parámetro (Auto.class, Moto.class, etc.).
    isInstance se usa para comprobar si un objeto es de un tipo específico o una de sus subclases. */
}
