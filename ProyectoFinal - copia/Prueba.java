package ProyectoFinal;

import java.util.Scanner;
import ProyectoFinal.Garajes.*;
import ProyectoFinal.Vehiculos.*;

public class Prueba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedDeParqueaderos redDeGarajes = new RedDeParqueaderos();

        int opcion = -99;
        while (opcion != 0) {
            // Mostrar menú
            System.out.println("\nMenú de Gestión de la Red de Garajes:");
            System.out.println("1.- Crear un garaje");
            System.out.println("2.- Eliminar un garaje");
            System.out.println("3.- Actualizar información de un garaje");
            System.out.println("4.- Ingresar un vehículo a un garaje");
            System.out.println("5.- Retirar un vehículo de un garaje");
            System.out.println("6.- Consulta de ocupación para todos los garajes");
            System.out.println("7.- Consulta de ocupación de un garaje en particular");
            System.out.println("8.- Consulta de ocupación por tipo de vehículo en todos los garajes");
            System.out.println("9.- Consulta de recaudación mensual por garaje");
            System.out.println("10.- Consulta de recaudación mensual total");
            System.out.println("0.- Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:  // Crear un garaje
                        System.out.println("Ingrese los datos del nuevo garaje:");
                        System.out.print("Id: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Departamento: ");
                        String departamento = scanner.nextLine();
                        System.out.print("Ciudad: ");
                        String ciudad = scanner.nextLine();
                        System.out.print("Dirección: ");
                        String direccion = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Nombre del administrador: ");
                        String nombreAdmin = scanner.nextLine();
                        System.out.print("Número de plazas: ");
                        int plazas = Integer.parseInt(scanner.nextLine());

                        // Crear el garaje y agregarlo a la red
                        Garajes nuevoGaraje = new Garajes(id, departamento, ciudad, direccion, telefono, email, nombreAdmin, plazas);
                        redDeGarajes.agregarGaraje(nuevoGaraje);
                        System.out.println("Garaje creado y agregado a la red exitosamente.");
                        break;

                    case 2:  // Eliminar un garaje
                        System.out.print("Ingrese el ID del garaje a eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        if (redDeGarajes.eliminarGaraje(idEliminar)) {
                            System.out.println("Garaje eliminado exitosamente.");
                        } else {
                            System.out.println("Error: No se encontró el garaje con el ID especificado.");
                        }
                        break;

                    case 3:  // Actualizar información de un garaje
                        System.out.print("Ingrese el ID del garaje a actualizar: ");
                        int idActualizar = Integer.parseInt(scanner.nextLine());

                        System.out.println("Ingrese los nuevos datos del garaje:");
                        System.out.print("Nuevo Departamento: ");
                        String nuevoDepartamento = scanner.nextLine();
                        System.out.print("Nueva Ciudad: ");
                        String nuevaCiudad = scanner.nextLine();
                        System.out.print("Nueva Dirección: ");
                        String nuevaDireccion = scanner.nextLine();
                        System.out.print("Nuevo Teléfono: ");
                        String nuevoTelefono = scanner.nextLine();
                        System.out.print("Nuevo Email: ");
                        String nuevoEmail = scanner.nextLine();
                        System.out.print("Nuevo Nombre del administrador: ");
                        String nuevoAdmin = scanner.nextLine();
                        System.out.print("Número de plazas: ");
                        int nuevasPlazas = Integer.parseInt(scanner.nextLine());

                        Garajes garajeActualizado = new Garajes(idActualizar, nuevoDepartamento, nuevaCiudad, nuevaDireccion, nuevoTelefono, nuevoEmail, nuevoAdmin, nuevasPlazas);
                        if (redDeGarajes.actualizarGaraje(idActualizar, garajeActualizado)) {
                            System.out.println("Garaje actualizado exitosamente.");
                        } else {
                            System.out.println("Error: No se pudo actualizar el garaje.");
                        }
                        break;

                    case 4:  // Ingresar un vehículo a un garaje
                        System.out.print("Ingrese el ID del garaje: ");
                        int idGaraje = Integer.parseInt(scanner.nextLine());

                        System.out.print("Ingrese el tipo de vehículo (1: Auto, 2: Moto, 3: Camión, 4: Camioneta): ");
                        int tipoVehiculo = Integer.parseInt(scanner.nextLine());
                        Vehiculo vehiculo = null;

                        try {
                            switch (tipoVehiculo) {
                                case 1:
                                    System.out.print("Ingrese la placa del auto: ");
                                    String placaAuto = scanner.nextLine();
                                    System.out.print("Marca: ");
                                    String marcaAuto = scanner.nextLine();
                                    System.out.print("Precio: ");
                                    double precioAuto = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Cilindraje: ");
                                    int cilindrajeAuto = Integer.parseInt(scanner.nextLine());
                                    System.out.print("¿Tiene radio? (true/false): ");
                                    boolean tieneRadio = Boolean.parseBoolean(scanner.nextLine());
                                    System.out.print("¿Tiene navegador? (true/false): ");
                                    boolean tieneNavegador = Boolean.parseBoolean(scanner.nextLine());
                                    vehiculo = new Auto(placaAuto,marcaAuto, precioAuto, cilindrajeAuto, tieneRadio, tieneNavegador);
                                    break;

                                case 2:
                                    System.out.print("Ingrese la placa de la moto: ");
                                    String placaMoto = scanner.nextLine();
                                    System.out.print("Marca: ");
                                    String marcaMoto = scanner.nextLine();
                                    System.out.print("Precio: ");
                                    double precioMoto = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Cilindraje: ");
                                    int cilindrajeMoto = Integer.parseInt(scanner.nextLine());
                                    System.out.print("¿Tiene sidecar? (true/false): ");
                                    boolean tieneSidecar = Boolean.parseBoolean(scanner.nextLine());
                                    vehiculo = new Moto(placaMoto,marcaMoto, precioMoto, cilindrajeMoto, tieneSidecar);
                                    break;

                                case 3:
                                    System.out.print("Ingrese la placa del camion: ");
                                    String placaCamion = scanner.nextLine();
                                    System.out.print("Marca: ");
                                    String marcaCamion = scanner.nextLine();
                                    System.out.print("Precio: ");
                                    double precioCamion = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Cilindraje: ");
                                    int cilindrajeCamion = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Número de ejes: ");
                                    int numeroDeEjes = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Tipo de camión (Doble/Sencillo): ");
                                    String tipoDeCamion = scanner.nextLine();
                                    System.out.print("Capacidad de carga (toneladas): ");
                                    double capacidadDeCarga = Double.parseDouble(scanner.nextLine());
                                    vehiculo = new Camion(placaCamion,marcaCamion, precioCamion, cilindrajeCamion, numeroDeEjes, tipoDeCamion, capacidadDeCarga);
                                    break;
                                case 4:
                                    System.out.print("Ingrese la placa de la camioneta: ");
                                    String placaCamioneta = scanner.nextLine();
                                    System.out.print("Marca: ");
                                    String marcaCamioneta = scanner.nextLine();
                                    System.out.print("Precio: ");
                                    double precioCamioneta = Double.parseDouble(scanner.nextLine());
                                    System.out.print("Cilindraje: ");
                                    int cilindrajeCamioneta = Integer.parseInt(scanner.nextLine());
                                    System.out.print("Tipo de servicio (Suv/Pickup/Carga/Otro): ");
                                    String tipoDeServicio = scanner.nextLine();
                                    System.out.print("Número de pasajeros: ");
                                    int numeroDePasajeros = Integer.parseInt(scanner.nextLine());
                                    System.out.print("¿Tiene remolque? (true/false): ");
                                    boolean tieneRemolque = Boolean.parseBoolean(scanner.nextLine());
                                    vehiculo = new Camioneta(placaCamioneta,marcaCamioneta, precioCamioneta, cilindrajeCamioneta, tipoDeServicio, numeroDePasajeros, tieneRemolque);
                                    break;

                                default:
                                    System.out.println("Opción no válida.");
                            }

                            if (vehiculo != null) {
                                if (redDeGarajes.ingresarVehiculoAGaraje(vehiculo.getPlaca(), idGaraje, vehiculo)) {
                                    System.out.println("Vehículo ingresado al garaje exitosamente.");
                                } else {
                                    System.out.println("Error: No se pudo ingresar el vehículo.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error al ingresar el vehículo: " + e.getMessage());
                        }
                        break;

                    case 5:  // Retirar un vehículo de un garaje
                        System.out.print("Ingrese la placa del vehículo a retirar: ");
                        String placaRetiro = scanner.nextLine();
                        System.out.print("Ingrese el ID del garaje: ");
                        int idGarajeRetiro = Integer.parseInt(scanner.nextLine());

                        if (redDeGarajes.retirarVehiculoDeGaraje(placaRetiro, idGarajeRetiro)) {
                            System.out.println("Vehículo retirado del garaje exitosamente.");
                        } else {
                            System.out.println("Error: No se pudo retirar el vehículo.");
                        }
                        break;

                    case 6:  // Consulta de ocupación para todos los garajes
                        redDeGarajes.informeOcupacion();
                        break;

                    case 7:  // Consulta de ocupación de un garaje en particular

                        System.out.print("Ingrese el ID del garaje: ");
                        int idConsultaGaraje = Integer.parseInt(scanner.nextLine());
                        redDeGarajes.informeOcupacion(idConsultaGaraje);
                        break;

                    case 8:  // Consulta de ocupación por tipo de vehículo
                        try {
                            System.out.print("Ingrese el tipo de vehículo a consultar (1: Auto, 2: Moto, 3: Camión, 4: Camioneta): ");
                            int tipoConsulta = Integer.parseInt(scanner.nextLine());

                            switch (tipoConsulta) {
                                case 1:
                                    redDeGarajes.informeOcupacionPorTipo(Auto.class);
                                    break;
                                case 2:
                                    redDeGarajes.informeOcupacionPorTipo(Moto.class);
                                    break;
                                case 3:
                                    redDeGarajes.informeOcupacionPorTipo(Camion.class);
                                    break;
                                case 4:
                                    redDeGarajes.informeOcupacionPorTipo(Camioneta.class);
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Debe ingresar un número válido para el tipo de vehículo.");
                        }
                        break;

                    case 9:  // Consulta de recaudación mensual por garaje
                        redDeGarajes.informeRecaudoMensual();
                        break;

                    case 10:  // Consulta de recaudación mensual total
                        redDeGarajes.totalRecaudo();
                        break;

                    case 0:  // Salir
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida, por favor elija una opción correcta.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error de entrada: " + e.getMessage() + ". Por favor, ingrese un número válido.");
            }
        }

        scanner.close();
    }
}
