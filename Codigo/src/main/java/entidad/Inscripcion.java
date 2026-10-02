package entidad;

import java.util.ArrayList;
import java.util.List;

public class Inscripcion {

    private List<Curso> cursosInscritos;
    private double total;

    public Inscripcion() {
        this.cursosInscritos = new ArrayList<Curso>();
        this.total = 0.0;
    }

    public void agregarCurso(Curso curso) {
        cursosInscritos.add(curso);
        calcularTotal();
    }

    public void quitarCurso(Curso curso) {
        cursosInscritos.remove(curso);
        calcularTotal();
    }

    public double calcularTotal() {
        double suma = 0.0;
        for (int i = 0; i < cursosInscritos.size(); i++) {
            Curso curso = cursosInscritos.get(i);
            suma = suma + curso.getCosto();
        }
        this.total = suma;
        return this.total;
    }

    public List<Curso> getCursosInscritos() {
        return cursosInscritos;
    }

    public double getTotal() {
        return total;
    }
}
