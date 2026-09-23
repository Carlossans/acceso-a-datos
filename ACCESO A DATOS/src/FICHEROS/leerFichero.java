package FICHEROS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class leerFichero {
    public static void main() throws FileNotFoundException{
        String ruta = "ejemplo.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
