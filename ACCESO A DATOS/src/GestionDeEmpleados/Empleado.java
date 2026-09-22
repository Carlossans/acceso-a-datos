package GestionDeEmpleados;

public class Empleado implements Comparable<Empleado>{
    private String dni;
    private String nombre;
    private String apellido;
    private float telefono;
    private double salario;



    public Empleado(String dni, String nombre, String apellido, float telefono, double salario) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.salario = salario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Float getTelefono() {
        return telefono;
    }

    public void setTelefono(Float telefono) {
        this.telefono = telefono;
    }

    public int compareTo(Empleado otro) {
        ComparadorCadenas comp = new ComparadorCadenas();
        return comp.compararCadenas(this.dni, otro.getDni());
    }
}
