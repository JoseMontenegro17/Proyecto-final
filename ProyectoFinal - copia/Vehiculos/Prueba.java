package ProyectoFinal.Vehiculos;

/*public class Prueba {
    package Corte2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class Prueba {
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            Garaje garaje = new Garaje(20); // Crear un garaje con 20 plazas
            int opcion = 0;
            do {
                try{
                    System.out.println("Menu:");
                    System.out.println("1. Alquilar espacio");
                    System.out.println("2. Calcular proporcion de vehiculos");
                    System.out.println("3. Contar camiones por tipo");
                    System.out.println("4. Mostrar plazas disponibles");
                    System.out.println("5. Salir");
                    System.out.print("Selecciona una opcion: ");
                    if(!scanner.hasNextInt()){
                        throw  new Exception("Error el valor ingresado no es valido");
                    }
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    // Procesa la opción seleccionada
                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese tipo de vehiculo (Auto/Moto/Camion): ");
                            String tipo = scanner.nextLine().toLowerCase();
                            if (!tipo.matches("auto|moto|camion")) throw  new Exception("El valor ingresado no es valido");
                            System.out.print("Ingrese marca: ");
                            String marca = scanner.nextLine();
                            System.out.print("Ingrese modelo: ");
                            String modelo = scanner.nextLine();
                            System.out.print("Ingrese matrícula: ");
                            String matricula = scanner.nextLine();

                            if(!tipo.equals("camion")){
                                garaje.alquilarEspacio(new Auto(marca, modelo, matricula));
                                break;
                            }

                            System.out.print("Ingrese tipo de camión (Sencillo/Doble): ");
                            String tipoCamion = scanner.nextLine().toLowerCase();
                            if (!tipoCamion.matches("sencillo|doble")) throw  new Exception("El valor ingresado no es valido");
                            garaje.alquilarEspacio(new Camion(marca, modelo, matricula, tipoCamion));
                            break;
                        case 2:
                            garaje.calcularProporcionVehiculos();
                            break;
                        case 3:
                            garaje.contarCamionesPorTipo();
                            break;
                        case 4:
                            garaje.mostrarPlazasDisponibles();
                            break;
                        case 5:
                            System.out.println("Saliendo del programa...");
                            return;
                        default:
                            System.out.println("Opciin no válida. Intentalo de nuevo.");
                    }
                }catch (Exception ex)
                {
                    System.out.println("Error: " + ex.getMessage()+" presione para continuar....");
                    scanner.nextLine();
                }
            }while (opcion != 5);
            scanner.close();

        }
        /*Aclaro que ciertas cosas se quitaron */
public class Prueba{
    public static void main(String[] args) {

        //falta
    }
}


