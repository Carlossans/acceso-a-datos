package GestionDeEmpleados;

public class ComparadorCadenas {

    public ComparadorCadenas() {};

    public int compararCadenas(String dni1, String dni2) {
        int numDni1 = Integer.parseInt(dni1.substring(0,8));
        int numDni2 = Integer.parseInt(dni2.substring(0,8));
        char letraDni1 = dni1.charAt(dni1.length() - 1);
        char letraDni2 = dni2.charAt(dni2.length() - 1);

        if (numDni1 > numDni2) {
            return 1;
        } else if (numDni1 < numDni2) {
            return -1;
        }else {
            return Character.compare(letraDni1, letraDni2);
        }
    }
}

