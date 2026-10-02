package presentacion;

import util.Colores;
import util.Fuentes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class FilaCurso extends JPanel {

    private static final NumberFormat FORMATO_MONEDA;
    static {
        FORMATO_MONEDA = NumberFormat.getCurrencyInstance(Locale.of("es", "MX"));
    }

    public FilaCurso(String nombre, double costo, boolean sinCupo, String textoBoton, ActionListener alAccionar) {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Colores.LINE),
                BorderFactory.createEmptyBorder(14, 4, 14, 4)));
        setPreferredSize(new Dimension(10, 64));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(Fuentes.SPACE_GROTESK_MEDIUM.deriveFont(15f));
        if (sinCupo) {
            lblNombre.setForeground(Colores.TEXT_MUTED);
        } else {
            lblNombre.setForeground(Colores.TEXT_PRIMARY);
        }

        GridBagConstraints cNombre = new GridBagConstraints();
        cNombre.gridx = 0;
        cNombre.weightx = 1;
        cNombre.anchor = GridBagConstraints.WEST;
        cNombre.fill = GridBagConstraints.HORIZONTAL;
        add(lblNombre, cNombre);

        GridBagConstraints cCosto = new GridBagConstraints();
        cCosto.gridx = 1;
        cCosto.insets = new Insets(0, 12, 0, 12);

        if (sinCupo) {
            JLabel badge = new JLabel("Sin cupo", SwingConstants.CENTER);
            badge.setFont(Fuentes.SPACE_GROTESK_MEDIUM.deriveFont(11f));
            badge.setForeground(Colores.ERROR);
            badge.setOpaque(true);
            badge.setBackground(Colores.ERROR_BG);
            badge.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
            add(badge, cCosto);
        } else {
            JLabel lblCosto = new JLabel(FORMATO_MONEDA.format(costo));
            lblCosto.setFont(Fuentes.IBM_PLEX_MONO_REGULAR.deriveFont(15f));
            lblCosto.setForeground(Colores.INDIGO_2);
            add(lblCosto, cCosto);
        }

        GridBagConstraints cBoton = new GridBagConstraints();
        cBoton.gridx = 2;

        if ("Quitar".equals(textoBoton)) {
            JButton boton = new JButton(textoBoton);
            boton.setFocusPainted(false);
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
            boton.setFont(Fuentes.SPACE_GROTESK_MEDIUM.deriveFont(12f));
            boton.setForeground(Colores.ERROR);
            boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (alAccionar != null) {
                boton.addActionListener(alAccionar);
            }
            add(boton, cBoton);
        } else {
            JButton boton = new JButton(textoBoton);
            boton.setFocusPainted(false);
            boton.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(13f));
            boton.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
            if (sinCupo) {
                boton.setBackground(new Color(0xE4, 0xE7, 0xEE));
                boton.setForeground(Colores.TEXT_MUTED);
            } else {
                boton.setBackground(Colores.INDIGO);
                boton.setForeground(Colores.WHITE);
            }
            if (alAccionar != null) {
                boton.addActionListener(alAccionar);
            }
            add(boton, cBoton);
        }
    }
}
