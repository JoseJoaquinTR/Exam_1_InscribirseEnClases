package control;

import modelo.ModeloInscripcion;
import entidad.Curso;

public class Controlador {

    private ModeloInscripcion modelo;

    public Controlador(ModeloInscripcion modelo) {
        this.modelo = modelo;
    }

    public void obtenerCursosDisponibles() {
        modelo.obtenerCursosDisponibles();
    }

    public void seleccionarCurso(Curso curso) {
        modelo.seleccionarCurso(curso);
    }

    public void quitarCurso(Curso curso) {
        modelo.quitarCurso(curso);
    }

    public void finalizarInscripcion() {
        modelo.finalizarInscripcion();
    }
}
