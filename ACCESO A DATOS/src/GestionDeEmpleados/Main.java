package GestionDeEmpleados;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        GestionEmpleados gestEmp = new GestionEmpleados();
        Empleado Carlos = new Empleado("12345678a", "Carlos", "Santos", 123456789, 2_000_000);
        Empleado Fran = new Empleado("12347678a", "Francisco Manuel", "Santos", 891234567, 1_000_000);
        Empleado Ana = new Empleado("12347678b", "Ana Isabel", "Jiménez", 666666666, 2_500_000);

        gestEmp.agregarEmpleados(Carlos);
        gestEmp.agregarEmpleados(Fran);
        gestEmp.agregarEmpleados(Ana);


        //gestEmp.mostrarEmpleados();
        gestEmp.ordenarPorDni();
        System.out.println("");
        gestEmp.mostrarEmpleados();

        int opcion;

        do {
            System.out.println("\nGESTIÓN DE EMPLEADOS");
            System.out.println("1. Ordenarpor DNI");
            System.out.println("2. Ordenar por Nombre");
            System.out.println("3. Ordenar por Apellido");
            System.out.println("4. Ordenar por Salario");
            System.out.println("5. Ordenar por Teléfono");
            System.out.println("6. Mostrar lista actual sin reordenar");
            System.out.println("0. Salir");
            System.out.println();
            System.out.print("Selecciona una opción: ");

            opcion = teclado.nextInt();
            System.out.println();

            switch (opcion) {
                case 1:
                    gestEmp.ordenarPorDni();
                    System.out.println("Ordenados por DNI");
                    gestEmp.mostrarEmpleados();
                    break;
                case 2:
                    gestEmp.ordenarPorNombre();
                    System.out.println("Ordenados por Nombre");
                    gestEmp.mostrarEmpleados();
                    break;
                case 3:
                    gestEmp.ordenarPorApellido();
                    System.out.println("Ordenados por Apellido");
                    gestEmp.mostrarEmpleados();
                    break;
                case 4:
                    gestEmp.ordenarPorSalario();
                    System.out.println("Ordenados por Salario");
                    gestEmp.mostrarEmpleados();
                    break;
                case 5:
                    gestEmp.ordenarPorTelefono();
                    System.out.println("Ordenados por Teléfono");
                    gestEmp.mostrarEmpleados();
                    break;
                case 6:
                    System.out.println("Lista de empleados actual");
                    gestEmp.mostrarEmpleados();
                    break;
                case 0:
                    System.out.println("Adiós");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        } while (opcion != 0);

    }
}
