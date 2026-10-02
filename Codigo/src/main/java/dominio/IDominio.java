package dominio;

import entidad.Curso;
import entidad.Inscripcion;
import entidad.FichaPago;

public interface IDominio {

    boolean agregarCurso(Curso curso, Inscripcion inscripcion);

    void quitarCurso(Curso curso, Inscripcion inscripcion);

    FichaPago generarFichaPago(Inscripcion inscripcion);
}
