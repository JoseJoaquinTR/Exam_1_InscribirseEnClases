package presentacion;

import entidad.Curso;
import java.awt.BorderLayout;
import java.awt.Dimension;
import util.Colores;
import util.Fuentes;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PanelCursosInscritos extends JPanel {

    private static final NumberFormat FORMATO_MONEDA = NumberFormat.getCurrencyInstance(Locale.of("es", "MX"));

    private FrmInscripcion frm;
    private JPanel listaContenedor;
    private JLabel etiquetaTotal;

    public PanelCursosInscritos(FrmInscripcion frm) {
        this.frm = frm;
        setBackground(Colores.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Colores.LINE, 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        listaContenedor = new JPanel();
        listaContenedor.setOpaque(false);
        listaContenedor.setLayout(new BoxLayout(listaContenedor, BoxLayout.Y_AXIS));
        listaContenedor.setAlignmentX(LEFT_ALIGNMENT);
        add(listaContenedor);

        JPanel filaTotal = new JPanel(new BorderLayout());
        filaTotal.setOpaque(false);
        filaTotal.setAlignmentX(LEFT_ALIGNMENT);
        filaTotal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, Colores.INDIGO),
                BorderFactory.createEmptyBorder(14, 0, 0, 0)));
        filaTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel etiquetaLabel = new JLabel("Total a pagar");
        etiquetaLabel.setFont(Fuentes.SPACE_GROTESK_MEDIUM.deriveFont(14f));
        etiquetaLabel.setForeground(Colores.TEXT_SECONDARY);
        filaTotal.add(etiquetaLabel, BorderLayout.WEST);

        etiquetaTotal = new JLabel("$0.00");
        etiquetaTotal.setFont(Fuentes.IBM_PLEX_MONO_REGULAR.deriveFont(22f));
        etiquetaTotal.setForeground(Colores.INDIGO);
        filaTotal.add(etiquetaTotal, BorderLayout.EAST);

        JPanel envoltorioTotal = new JPanel(new BorderLayout());
        envoltorioTotal.setOpaque(false);
        envoltorioTotal.setAlignmentX(LEFT_ALIGNMENT);
        envoltorioTotal.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        envoltorioTotal.add(filaTotal, BorderLayout.CENTER);
        add(envoltorioTotal);

        JButton botonFinalizar = new JButton("Finalizar inscripción");
        botonFinalizar.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(15f));
        botonFinalizar.setBackground(Colores.GOLD);
        botonFinalizar.setForeground(Colores.INDIGO);
        botonFinalizar.setFocusPainted(false);
        botonFinalizar.setBorder(BorderFactory.createEmptyBorder(13, 0, 13, 0));
        botonFinalizar.setAlignmentX(LEFT_ALIGNMENT);
        botonFinalizar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        botonFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frm.finalizarInscripcion();
            }
        });
        JPanel envoltorioBoton = new JPanel(new BorderLayout());
        envoltorioBoton.setOpaque(false);
        envoltorioBoton.setAlignmentX(LEFT_ALIGNMENT);
        envoltorioBoton.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));
        envoltorioBoton.add(botonFinalizar, BorderLayout.CENTER);
        add(envoltorioBoton);
    }

    public void mostrarInscritos(List<Curso> lista) {
        listaContenedor.removeAll();

        if (lista.isEmpty()) {
            JLabel vacio = new JLabel("No has inscrito ningún curso todavía.");
            vacio.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(14f));
            vacio.setForeground(Colores.TEXT_MUTED);
            vacio.setBorder(BorderFactory.createEmptyBorder(18, 4, 18, 4));
            listaContenedor.add(vacio);
        } else {
            for (int i = 0; i < lista.size(); i++) {
                final Curso curso = lista.get(i);
                ActionListener alClic = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frm.quitarCurso(curso);
                    }
                };
                FilaCurso fila = new FilaCurso(curso.getNombre(), curso.getCosto(), false, "Quitar", alClic);
                fila.setAlignmentX(LEFT_ALIGNMENT);
                fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, fila.getPreferredSize().height));
                listaContenedor.add(fila);
            }
        }

        revalidate();
        repaint();
    }

    public void mostrarTotal(double total) {
        etiquetaTotal.setText(FORMATO_MONEDA.format(total));
        revalidate();
        repaint();
    }
}
