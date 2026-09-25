package FICHEROS;

import java.io.*;
import java.util.Scanner;

public class PracticaFicheros {
    static void main(String[] args) throws FileNotFoundException {
        //usar split para meter cada fila a un array
        Scanner teclado = new Scanner(System.in);
        boolean existe = false;
        String nombreFichero;
        String nombreAuxiliar;

        do {
            System.out.println("Introduce el nombre del fichero que contiene los usuarios: \n");
            nombreFichero = teclado.nextLine().toLowerCase();
            nombreAuxiliar = nombreFichero;
            try (BufferedReader bf = new BufferedReader(new FileReader(nombreFichero))) {
                String linea;

                while () {}
                existe = true;
                System.out.println("\nEl fichero se ha encontrado");
                if (nombreFichero.exists) {

                }
            } catch (FileNotFoundException e) {
                System.out.println("\nEl fichero no existe, introduce el nombre de un fichero existente\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (!existe);

        int opcion;

        do {
            System.out.println("\n========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Mostrar usuarios introducidos");
            System.out.println("3. Generar fichero de concordancias");
            System.out.println("5. salir\n");
            System.out.println("¿Qué opción desea realizar?\n");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nIntroduce el usuario:\n");
                    String usuario = teclado.nextLine();

                    try (BufferedReader bf = new BufferedReader(new FileReader(nombreAuxiliar))) {
                        String linea;

                        while((linea = bf.readLine()) != null) {
                            if (linea.contains(usuario)) {
                                System.out.println("\nEl usuario ya existe\n");
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


//                    try (FileWriter fw = new FileWriter(nombreAuxiliar)) {
//
//                    } catch (Exception e ) {
//                        throw new RuntimeException(e);
//                    }
//                    break;
                case 2:
                    try (BufferedReader br = new BufferedReader(new FileReader(nombreAuxiliar))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            System.out.println("[({ " + linea + " })]");
                        }
                    }catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    break;
                case 5:
                    System.out.println("\nHasta luego máquina");
                    break;

                default:
                    System.out.println("\nOpción no válida");
            }
        } while (opcion != 5);
    }
}
