package FICHEROS;

import java.io.*;

public class crearFichero {
    public static void main(String[] args) {
        String ruta = "datos/otro.txt";

        //escribir en fichero
        try (FileWriter fw = new FileWriter(ruta, true)) {
            fw.write("¿Donde estoy?\n");
            //fw.write("acceso a datos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //leer fichero
        try (BufferedReader bf = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while (( linea = bf.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        //borrar fichero

        File fichero = new File(ruta);

        if (fichero.delete()) {
            System.out.println("Fichero eliminado correctamente");
        } else {
            System.out.println("No se ha borrado");
        }
    }
}
