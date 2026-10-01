package FICHEROS.Practica; // paquete de la práctica

import java.io.*; // clases para leer y escribir ficheros
import java.util.ArrayList; // listas dinámicas
import java.util.Scanner; // leer del teclado
import java.util.Collections; // para ordenar listas


public class PracticaFicheros { // clase principal
    static void main(String[] args) throws FileNotFoundException { // arranque del programa
        Scanner teclado = new Scanner(System.in); // lector del teclado
        String nombreFichero; // nombre del fichero elegido
        ArrayList<ArrayList<String>> lista = new ArrayList<>(); // todos los usuarios (cada uno es un arrayList)
        ArrayList<File> ficherosDisponibles = obtenerFicheros(); // ficheros de la carpeta
        int seleccion = 0; // número de fichero elegido

        do { // repetimos hasta elegir un fichero válido

            if (ficherosDisponibles.isEmpty()) { // si no hay ficheros
                System.out.println("\nNo se ha encontrado ningún fichero"); // avisamos
                return; // y salimos
            }

            for (int i = 0; i < ficherosDisponibles.size(); i++) { // recorremos los ficheros
                System.out.println(i + 1 + ". " + ficherosDisponibles.get(i).getName()); // los mostramos numerados
            }

            System.out.println("\nSelecciona el número del fichero que quieres cargar:"); // pedimos el número

            if (teclado.hasNextInt()) { // si ha escrito un número
                seleccion = teclado.nextInt(); // lo guardamos
                teclado.nextLine(); // limpiamos el salto de línea
            } else { // si no es un número
                teclado.nextLine(); // descartamos lo escrito
            }

            if (seleccion < 1 || seleccion > ficherosDisponibles.size()) { // si está fuera de rango
                System.out.println("Opción no válida. Inténtalo de nuevo.\n"); // avisamos
            }

        } while (seleccion < 1 || seleccion > ficherosDisponibles.size()); // repetimos si no es válido

        File ficheroSeleccionado = ficherosDisponibles.get(seleccion - 1); // fichero elegido
        nombreFichero = ficheroSeleccionado.getName(); // guardamos su nombre

        try (BufferedReader bf = new BufferedReader(new FileReader(ficheroSeleccionado))) { // abrimos el fichero para leer
            String usuarios; // línea leída
            while ((usuarios = bf.readLine()) != null) { // leemos línea a línea
                ArrayList<String> filaActual = convertirFicheroArray(usuarios); // convertimos la línea en lista
                lista.add(filaActual); // la añadimos a los usuarios
            }
            System.out.println("Fichero '" + nombreFichero + "' cargado correctamente."); // confirmamos la carga
        } catch (IOException e) { // si falla la lectura
            System.out.println("Error al leer el fichero"); // mostramos el error
        }


        int opcion; // opción del menú

        do { // repetimos el menú hasta salir
            System.out.println("\n========== MENÚ PRINCIPAL =========="); // título
            System.out.println("1. Añadir usuario"); // opción 1
            System.out.println("2. Mostrar usuarios introducidos"); // opción 2
            System.out.println("3. Generar fichero de concordancias"); // opción 3
            System.out.println("5. salir\n"); // opción 5
            System.out.println("¿Qué opción desea realizar?\n"); // pedimos la opción
            if (teclado.hasNextInt()) { // si ha escrito un número
                opcion = teclado.nextInt(); // lo guardamos
            } else { // si no es un número
                opcion = 0; // opción inválida para que salte el default
            }
            teclado.nextLine(); // limpiamos la entrada

            switch (opcion) { // según la opción
                case 1: // añadir usuario
                    String usuarioID = idAutomatico(lista); // calculamos el siguiente ID

                    ArrayList<String> soloAficiones = new ArrayList<>(); // aficiones del nuevo usuario

                    String aficion; // afición escrita

                    do { // pedimos aficiones hasta 'listo'
                        System.out.println("Introduce una afición (o escribe 'listo' para terminar):"); // pedimos afición
                        aficion = teclado.nextLine().toUpperCase().replaceAll("\\s+", ""); // en mayúsculas y sin espacios
                        if (!aficion.equals("LISTO")) { // si no ha terminado
                            if (!aficion.isEmpty()) { // si ha escrito algo
                                if (!soloAficiones.contains(aficion)) { // si no está repetida
                                    soloAficiones.add(aficion); // la añadimos
                                } else { // si está repetida
                                    System.out.println("No se ha podido añadir la afición, porque esta repetida"); // avisamos
                                }
                            } else { // si está vacía
                                System.out.println("No has introducido ninguna afición válida."); // avisamos
                            }
                        }
                    } while (!aficion.equals("LISTO")); // hasta que escriba 'listo'

                    Collections.sort(soloAficiones); // ordenamos alfabéticamente

                    ArrayList<String> usuarioFinal = new ArrayList<>(); // usuario completo

                    usuarioFinal.add(usuarioID); // primero el ID
                    usuarioFinal.addAll(soloAficiones); // luego las aficiones

                    lista.add(usuarioFinal); // lo guardamos con los demás

                    System.out.println("\nUsuario añadido correctamente con ID: " + usuarioID); // confirmamos

                    break;

                case 2: // mostrar usuarios
                    System.out.println(); // línea en blanco
                    for (ArrayList<String> usuario : lista) { // recorremos los usuarios
                        System.out.println(usuario); // mostramos cada uno
                    }
                    break;
                case 3: // concordancias
                    int minimo = 0; // mínimo de aficiones comunes
                    do { // pedimos hasta que sea válido
                        System.out.println("Introduce el número mínimo de aficiones comunes (1 o más):"); // pedimos el mínimo
                        if (teclado.hasNextInt()) { // si es un número
                            minimo = teclado.nextInt(); // lo guardamos
                        }
                        teclado.nextLine(); // limpiamos la entrada
                        if (minimo < 1) { // si no es válido
                            System.out.println("Debe ser un número mayor o igual que 1."); // avisamos
                        }
                    } while (minimo < 1); // repetimos si no es válido
                    generarConcordancias(lista, minimo); // buscamos parejas y creamos el fichero
                    break;
                case 5: // salir
                    System.out.println("\nHasta luego máquina"); // despedida
                    break;

                default: // cualquier otra
                    System.out.println("\nOpción no válida"); // avisamos
            }
        } while (opcion != 5); // hasta elegir salir
    }

    private static void generarConcordancias(ArrayList<ArrayList<String>> lista, int minimo) { // crea concordancias.txt
        ArrayList<String> parejas = new ArrayList<>(); // líneas del fichero de salida

        for (int user1 = 0; user1 < lista.size(); user1++) { // primer usuario de la pareja
            for (int user2 = user1 + 1; user2 < lista.size(); user2++) { // segundo usuario (j > i para no repetir parejas)
                ArrayList<String> usuario1 = lista.get(user1); // datos del primero
                ArrayList<String> usuario2 = lista.get(user2); // datos del segundo
                ArrayList<String> comunes = new ArrayList<>(); // aficiones en común

                for (int campo = 1; campo < usuario1.size(); campo++) { // aficiones del primero (la 0 es el ID)
                    String aficion = usuario1.get(campo); // afición actual
                    if (usuario2.subList(1, usuario2.size()).contains(aficion)) { // si el segundo también la tiene
                        comunes.add(aficion); // es común y lo añado al array
                    }
                }

                if (comunes.size() >= minimo) { // si llega al mínimo
                    Collections.sort(comunes); // ordenamos alfabéticamente
                    String linea = usuario1.get(0) + " " + usuario2.get(0); // empezamos con los dos IDs
                    for (String comun : comunes) { // recorremos las aficiones comunes
                        linea += " " + comun; // las vamos añadiendo separadas por espacio
                    }
                    parejas.add(linea); // guardamos la línea
                }
            }
        }

        if (parejas.isEmpty()) { // si no hay parejas
            System.out.println("No hay parejas con al menos " + minimo + " aficiones comunes. No se crea el fichero."); // avisamos
            return; // no creamos el fichero
        }

        int maximo = 0; // mayor nº de aficiones comunes de una pareja
        for (String pareja : parejas) { // recorremos las parejas
            int cantidad = pareja.split(" ").length - 2; // nº de aficiones (quitando los 2 IDs)
            if (cantidad > maximo) { // si supera al máximo actual
                maximo = cantidad; // lo guardamos como nuevo máximo
            }
        }

        ArrayList<String> ordenadas = new ArrayList<>(); // parejas ordenadas de más a menos aficiones
        for (int cantidad = maximo; cantidad >= minimo; cantidad--) { // desde el máximo hasta el mínimo
            for (String pareja : parejas) { // buscamos las parejas
                if (pareja.split(" ").length - 2 == cantidad) { // que tengan justo esa cantidad
                    ordenadas.add(pareja); // y las añadimos en orden
                }
            }
        }

        try (FileWriter fw = new FileWriter("concordancias.txt")) { // creamos el fichero (se cierra solo)
            for (String pareja : ordenadas) { // recorremos las parejas ya ordenadas
                fw.write(pareja + "\n"); // escribimos la pareja con su salto de línea
                System.out.println(pareja); // la mostramos también
            }
            System.out.println("\nFichero 'concordancias.txt' creado con " + parejas.size() + " parejas."); // nº de parejas
        } catch (IOException e) { // si no se puede crear
            System.out.println("No se puede crear el fichero de salida"); // avisamos
        }
    }

    private static ArrayList<File> obtenerFicheros() { // devuelve los ficheros de la carpeta
        File carpetaRaiz = new File("."); // carpeta actual
        File[] todosLosArchivos = carpetaRaiz.listFiles(); // todo lo que hay en ella
        ArrayList<File> ficheros = new ArrayList<>(); // aquí solo los ficheros

        if (todosLosArchivos != null) { // si se ha podido listar
            for (File fichero : todosLosArchivos) { // recorremos el contenido
                if (fichero.isFile()) { // si es un fichero (no carpeta)
                    ficheros.add(fichero); // lo guardamos
                }
            }
        }

        return ficheros; // devolvemos la lista
    }

    private static ArrayList<String> convertirFicheroArray(String usuarios) { // convierte una línea en lista
        ArrayList<String> filaActual = new ArrayList<>(); // lista del usuario
        String[] palabras = usuarios.trim().split("[,\\s]+"); // separamos por comas y/o espacios
        Collections.addAll(filaActual, palabras); // pasamos las palabras a la lista
        return filaActual; // devolvemos la lista
    }

    public static String idAutomatico(ArrayList<ArrayList<String>> lista) { // calcula el siguiente ID
        ArrayList<String> ultimoUsuario = lista.getLast(); // último usuario
        String ultimoID = ultimoUsuario.getFirst(); // su ID

        int id = Integer.parseInt(ultimoID.substring(1)) + 1; // quitamos la U y sumamos 1
        return "U" + id; // devolvemos el nuevo ID con la U
    }
}

