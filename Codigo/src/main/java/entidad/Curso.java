package entidad;

public class Curso {

    private String nombre;
    private double costo;
    private boolean disponible;

    public Curso(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
        this.disponible = true;
    }

    public void marcarNoDisponible() {
        this.disponible = false;
    }

    public void marcarDisponible() {
        this.disponible = true;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }

    public String toString() {
        return nombre;
    }
}
