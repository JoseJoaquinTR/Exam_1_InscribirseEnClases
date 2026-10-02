package modelo;

import dominio.IDominio;
import entidad.Curso;
import entidad.Inscripcion;
import entidad.FichaPago;
import java.util.List;

public class ModeloInscripcion {

    private IObservador observador;
    private IDominio dominio;
    private List<Curso> catalogoDisponibles;
    private Inscripcion inscripcion;
    private FichaPago fichaPago;
    private String tituloMensaje;
    private String mensajeCursoNoseleccionado;

    public ModeloInscripcion(List<Curso> catalogoDisponibles, IDominio dominio) {
        this.catalogoDisponibles = catalogoDisponibles;
        this.dominio = dominio;
        this.inscripcion = new Inscripcion();
        this.fichaPago = null;
    }

    public void agregarObserver(IObservador obs) {
        this.observador = obs;
    }

    public void obtenerCursosDisponibles() {
        notificarCursos();
    }

    public void seleccionarCurso(Curso curso) {
        boolean agregado = dominio.agregarCurso(curso, inscripcion);
        if (agregado) {
            catalogoDisponibles.remove(curso);
            notificarCursos();
        } else {
            tituloMensaje = "Cupo lleno";
            mensajeCursoNoseleccionado = "\"" + curso.getNombre()
                    + "\" ya no tiene lugares disponibles. Elige otro curso de la lista.";
            notificarMensaje();
        }
    }

    public void quitarCurso(Curso curso) {
        dominio.quitarCurso(curso, inscripcion);
        catalogoDisponibles.add(curso);
        notificarCursos();
    }

    public void finalizarInscripcion() {
        fichaPago = dominio.generarFichaPago(inscripcion);
        if (fichaPago == null) {
            tituloMensaje = "No hay cursos que inscribir";
            mensajeCursoNoseleccionado = "Selecciona al menos un curso antes de finalizar tu inscripción.";
            notificarMensaje();
        } else {
            if (observador != null) {
                observador.updateFichaPago(this);
            }
        }
    }

    private void notificarCursos() {
        if (observador != null) {
            observador.updateDisponibles(this);
            observador.updateInscritos(this);
            observador.updateTotal(this);
        }
    }

    private void notificarMensaje() {
        if (observador != null) {
            observador.updateMensaje(this);
        }
    }

    public List<Curso> getCursosDisponibles() {
        return catalogoDisponibles;
    }

    public List<Curso> getCursosInscritos() {
        return inscripcion.getCursosInscritos();
    }

    public double getTotal() {
        return inscripcion.getTotal();
    }

    public FichaPago getFichaPago() {
        return fichaPago;
    }

    public String getMensajeCursoNoseleccionado() {
        return mensajeCursoNoseleccionado;
    }

    public String getTituloMensaje() {
        return tituloMensaje;
    }
}
