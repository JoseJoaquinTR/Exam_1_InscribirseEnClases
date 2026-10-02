package entidad;

import java.util.Date;
import java.util.List;

public class FichaPago {

    private String folio;
    private Date fecha;
    private List<Curso> cursos;
    private double total;

    public FichaPago(String folio, Date fecha, List<Curso> cursos, double total) {
        this.folio = folio;
        this.fecha = fecha;
        this.cursos = cursos;
        this.total = total;
    }

    public String getFolio() {
        return folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public double getTotal() {
        return total;
    }
}
