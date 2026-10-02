package dominio;

import entidad.Curso;
import entidad.Inscripcion;
import entidad.FichaPago;
import java.util.Date;

public class Dominio implements IDominio {

    public boolean agregarCurso(Curso curso, Inscripcion inscripcion) {
        boolean disponible = verificarDisponibilidad(curso);
        if (disponible) {
            curso.marcarNoDisponible();
            inscripcion.agregarCurso(curso);
            return true;
        } else {
            return false;
        }
    }

    private boolean verificarDisponibilidad(Curso curso) {
        return curso.estaDisponible();
    }

    public void quitarCurso(Curso curso, Inscripcion inscripcion) {
        curso.marcarDisponible();
        inscripcion.quitarCurso(curso);
    }

    public FichaPago generarFichaPago(Inscripcion inscripcion) {
        if (inscripcion.getCursosInscritos().isEmpty()) {
            return null;
        }
        String folio = "F-" + System.currentTimeMillis();
        Date fecha = new Date();
        double total = inscripcion.calcularTotal();
        FichaPago fichaPago = new FichaPago(folio, fecha, inscripcion.getCursosInscritos(), total);
        return fichaPago;
    }
}
