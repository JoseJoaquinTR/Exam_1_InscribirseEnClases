package presentacion;

import entidad.Curso;
import util.Colores;
import util.Fuentes;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelCursos extends JPanel {

    private FrmInscripcion frm;
    private JPanel listaContenedor;

    public PanelCursos(FrmInscripcion frm) {
        this.frm = frm;
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("CURSOS DISPONIBLES");
        titulo.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(12f));
        titulo.setForeground(Colores.TEXT_SECONDARY);
        titulo.setAlignmentX(LEFT_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));
        add(titulo);

        JLabel subtitulo = new JLabel("Selecciona los cursos en los que quieras inscribirte.");
        subtitulo.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(14f));
        subtitulo.setForeground(Colores.TEXT_SECONDARY);
        subtitulo.setAlignmentX(LEFT_ALIGNMENT);
        subtitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        add(subtitulo);

        listaContenedor = new JPanel();
        listaContenedor.setOpaque(false);
        listaContenedor.setLayout(new BoxLayout(listaContenedor, BoxLayout.Y_AXIS));
        listaContenedor.setAlignmentX(LEFT_ALIGNMENT);
        add(listaContenedor);
    }

    public void mostrarDisponibles(List<Curso> lista) {
        listaContenedor.removeAll();

        if (lista.isEmpty()) {
            JLabel vacio = new JLabel("Ya inscribiste todos los cursos disponibles.");
            vacio.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(14f));
            vacio.setForeground(Colores.TEXT_MUTED);
            vacio.setBorder(BorderFactory.createEmptyBorder(18, 4, 18, 4));
            listaContenedor.add(vacio);
        } else {
            for (int i = 0; i < lista.size(); i++) {
                final Curso curso = lista.get(i);
                boolean sinCupo = false;
                if (!curso.estaDisponible()) {
                    sinCupo = true;
                }
                ActionListener alClic = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frm.seleccionarCurso(curso);
                    }
                };
                FilaCurso fila = new FilaCurso(curso.getNombre(), curso.getCosto(), sinCupo, "Inscribir", alClic);
                fila.setAlignmentX(LEFT_ALIGNMENT);
                fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, fila.getPreferredSize().height));
                listaContenedor.add(fila);
            }
        }

        revalidate();
        repaint();
    }
}
