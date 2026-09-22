package GestionDeEmpleados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestionEmpleados {

    private ArrayList<Empleado> listaEmpleados;

    public GestionEmpleados() {
        this.listaEmpleados = new ArrayList<>();
    }

    public void agregarEmpleados(Empleado empleado) {
        listaEmpleados.add(empleado);
    }

    public void ordenarPorDni() {
        Collections.sort(listaEmpleados);// como hemos puesto en empleado: "implements comparable" y tenemos metodo compareTo, automáticamente compara usando los criterios establecidos en el metodo
    }

    public void ordenarPorNombre() {
        listaEmpleados.sort(Comparator.comparing(Empleado::getNombre));
    }

    public void ordenarPorApellido() {
        listaEmpleados.sort(Comparator.comparing(Empleado::getApellido));
    }

    public void ordenarPorTelefono() {
        listaEmpleados.sort(Comparator.comparing(Empleado::getTelefono));
    }

    public void ordenarPorSalario() {
        listaEmpleados.sort(Comparator.comparing(Empleado::getSalario));
    }

    public void mostrarEmpleados() {
        for (Empleado emp: listaEmpleados) {
            System.out.println("DNI: " + emp.getDni() + " Nombre: " + emp.getNombre() + " Apellido: " + emp.getApellido() + " Teléfono: " + emp.getTelefono() + " Salario : " + emp.getSalario());
        }
    }
}
