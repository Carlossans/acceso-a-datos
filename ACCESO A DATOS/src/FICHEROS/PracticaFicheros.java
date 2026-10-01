package FICHEROS;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


// ====================GUARDAR USUARIO REALMENTE EN EL FICHERO===================


public class PracticaFicheros {
    static void main(String[] args) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);
        String nombreFichero;
        ArrayList<ArrayList<String>> lista = new ArrayList<>(); // Array list que va a contener cada línea del fichero como un Array List de String

        File carpetaRaiz = new File("."); //Esto nos sirve para representar el directorio raiz
        File[] todosLosArchivos = carpetaRaiz.listFiles();

        do { // Hasta que no introduzca el usuario el nombre correcto del fichero
            System.out.println("Introduce el nombre del fichero que contiene los usuarios: \n");
            nombreFichero = teclado.nextLine().toLowerCase().replaceAll("\\s", ""); // el usuario introduce el nombre del fichero y se le quitan todos los espacios, tabulaciones ...

                try (BufferedReader bf = new BufferedReader(new FileReader(nombreFichero))) { // abrimos el fichero para leerlo
                    String usuarios; // esta variable contendrá la línea que se esté leyendo actualmente
                    while ((usuarios = bf.readLine()) != null) { // mientras que la variable usuarios contenga algo, se meterá en el array que hemos creado
                        ArrayList<String> filaActual = convertirFicheroArray(usuarios);

                        lista.add(filaActual);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("\nEl fichero '" + nombreFichero + "' no existe. Inténtalo de nuevo.\n");
                } catch (IOException e ) {
                    System.out.println("No se ha podido leer el fichero\n");
                }

        } while (!nombreFichero.equals("entrada.txt"));

        int opcion; // variable para guardar la opción que eliga el usuario, pero no se inicializa

        do {
            System.out.println("\n========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Mostrar usuarios introducidos");
            System.out.println("3. Generar fichero de concordancias");
            System.out.println("5. salir\n");
            System.out.println("¿Qué opción desea realizar?\n");
            opcion = teclado.nextInt();
            teclado.nextLine(); // limpiar el teclado

            switch (opcion) {
                case 1:
                    String usuarioID = idAutomatico(lista); // Obtener automáticamente el ID del siguiente usuario, usando como referencia el ID del último

                    ArrayList<String> soloAficiones = new ArrayList<>();

                    String aficion; // Aquí guardaremos la afición que meta el usuario para luego guardarlo en el array

                    do {
                        System.out.println("Introduce una afición (o escribe 'listo' para terminar):");
                        aficion = teclado.nextLine().toUpperCase().replaceAll("\\s+", ""); // guardamos en la variable lo que meta el usuario en mayúsculas porque todo lo que está en el fichero lo encontramos en mayúscula y le quitamos cualquier espacio que puede meter el torpe usuario
                        if (!aficion.equals("LISTO")) { // Mientras que la afición no sea 'LISTO' meterá lo introducido en el array del user
                            if (!aficion.isEmpty()) { // Comprueba que afición contiene algo, si no es el caso, tendrá que volver a introducir una afición válida
                                if (!soloAficiones.contains(aficion)) { // Comprueba que la afición no esté repetida
                                    soloAficiones.add(aficion); // Aquí añadimos las aficiones
                                } else {
                                    System.out.println("No se ha podido añadir la afición, porque esta repetida");
                                }
                            } else {
                                System.out.println("No has introducido ninguna afición válida.");
                            }
                        }
                    } while(!aficion.equals("LISTO")); // Se sigue repitiendo hasta que la aficion no sea 'listo' independientemente de si es minúscula o mayúscula

                    Collections.sort(soloAficiones); // De esta manera conseguimos que ordene el array de aficiones alfabéticamente

                    ArrayList<String> usuarioFinal = new ArrayList<>(); // creamos un arraylist para poner finalmente tanto el is de usuario como las aficiones ordenadas alfabéticamente

                    usuarioFinal.add(usuarioID); // Añadimos primero el ID
                    usuarioFinal.addAll(soloAficiones); // Y luego las aficiones

                    lista.add(usuarioFinal); // Finalmente, añadimos el usuario final a la lista de usuarios

                    System.out.println("\nUsuario añadido correctamente con ID: " + usuarioID);

                   break;

                case 2:
                    System.out.println();
                    for (ArrayList<String> usuario : lista) { // recorremos el arraylist principal para mostrar los usuarios
                        System.out.println(usuario);
                    }
                    break;
                case 3:
                    //FICHERO DE CONCORDANCIAS, QUE NO TENGO NI IDEA DE COMO HACERLO
                    break;
                case 5:
                    System.out.println("\nHasta luego máquina");
                    break;

                default:
                    System.out.println("\nOpción no válida");
            }
        } while (opcion != 5);
    }

    private static ArrayList<String> convertirFicheroArray(String usuarios) {
        ArrayList<String> filaActual = new ArrayList<>(); // creamos un arraylist para la fila actual
        String[] palabras = usuarios.trim().split("\\s"); // y luego creamos un array normal que irá dentro del arraylist de la fila actual y así tendremos cada usuario en un array list y luego dentro del array list metemos un array simple para dividir el ID de usuario, aficiones etc

        Collections.addAll(filaActual, palabras); // lo que hace es que añade el contenido del array simple de palabras al de la fila actual del usuario
        return filaActual; // y devolvemos el array
    }

    public static String idAutomatico(ArrayList<ArrayList<String>> lista) { // le pasamos como parámetro la lista que tiene los arraylist de string con arrays de string simples dentro
        ArrayList<String> ultimoUsuario = lista.getLast(); // usamos un arraylist para obtener el último usuario con el lista.getLast()
        String ultimoID = ultimoUsuario.getFirst(); // Con esto conseguimos el Id. getFirst()

        int id = Integer.parseInt(ultimoID.substring(1)) + 1; // Quitamos la U del ID con el: 'ultimoID.substring(1)' y lo envolvemos en un Integer.parseInt() para tratarlo como un número, ya que antes era un String y por último le sumamos 1 para obtener el id automáticamente.
        return "U" + id; // Ahora le volvemos a poner la U delante para formar completamente el nuevo ID.
    }
}