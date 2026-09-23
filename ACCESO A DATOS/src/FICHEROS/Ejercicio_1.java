package FICHEROS;

import java.io.*;
import java.util.Scanner;

public class Ejercicio_1 {
    static void main(String[] args) {
        // el usuario introduce el nombre de un fichero que el indique
        // y luego se le pregunta que es lo que quiere escribir en el fichero
        // una vez escrita la primera línea se le preguntará si quiere escribir otra linea, leer el fichero, borrar el fichero o terminar

        Scanner teclado = new Scanner(System.in);

        System.out.println("¿cómo quieres que se llame el fichero?\n");
        String nombreFichero = teclado.nextLine();

        try (FileWriter fw = new FileWriter(nombreFichero)) {

            System.out.println("\nEl fichero se ha creado correctamente");
            System.out.println("\nEscribe lo que quieras: \n");
            fw.write(teclado.nextLine());
            System.out.println("\nSe ha añadido el texto correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        int opcion;

        do {
            System.out.println("\nGestión de ficheros");
            System.out.println("1. Escribir más en el fichero creado.");
            System.out.println("2. Leer lo que pone actualmente en el fichero.");
            System.out.println("3. Borrar el fichero.");
            System.out.println("4. Salir del programa y no escribir más");
            System.out.println("¿Qué opción eliges?\n");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    try (FileWriter fw = new FileWriter(nombreFichero, true)) {
                        System.out.println("\n¿Qué es lo que quieres escribir?\n");
                        fw.write("\n" + teclado.nextLine());
                        System.out.println("Se ha añadido el texto correctamente");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.println("\nEsto es lo que pone en el fichero:\n");

                    try (BufferedReader bf = new BufferedReader(new FileReader(nombreFichero))) {
                        String linea;
                        while ((linea = bf.readLine()) != null) {
                            System.out.println(linea);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("\nEl fichero no existe o se ha borrado");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    break;

                case 3:
                    File ficheroBorrar = new File(nombreFichero);

                    if (ficheroBorrar.delete()) {
                        System.out.println("\nFichero eliminado correctamente");
                    } else {
                        System.out.println("No se ha borrado");
                    }
                    break;

                case 4:
                    System.out.println("\nHasta la próxima");
                    break;

                default:
                    System.out.println("\nOpción no válida");
            }

        } while (opcion != 4);
    }
}
