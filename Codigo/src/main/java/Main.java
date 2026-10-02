import entidad.Curso;
import dominio.Dominio;
import modelo.ModeloInscripcion;
import control.Controlador;
import presentacion.FrmInscripcion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        iniciarApp();
    }

    private static void iniciarApp() {
        List<Curso> catalogo = new ArrayList<Curso>();
        Curso calculo = new Curso("Cálculo Diferencial", 1200);
        calculo.marcarNoDisponible();
        catalogo.add(calculo);
        catalogo.add(new Curso("Programación Orientada a Objetos", 1500));
        catalogo.add(new Curso("Bases de Datos", 1350));
        catalogo.add(new Curso("Redes de Computadoras", 1100));
        catalogo.add(new Curso("Física II", 1250));
        catalogo.add(new Curso("Inglés III", 900));

        final ModeloInscripcion modelo = new ModeloInscripcion(catalogo, new Dominio());
        final Controlador controlador = new Controlador(modelo);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FrmInscripcion frm = new FrmInscripcion(modelo, controlador, new Runnable() {
                    public void run() {
                        iniciarApp();
                    }
                });
                frm.setVisible(true);
            }
        });
    }
}
