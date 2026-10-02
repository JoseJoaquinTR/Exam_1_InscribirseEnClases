package presentacion;

import util.Colores;
import util.Fuentes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class DialogoError extends JDialog {

    public DialogoError(Frame padre, String titulo, String mensaje) {
        super(padre, true);
        setUndecorated(true);

        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Colores.WHITE);
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(4, 0, 0, 0, Colores.ERROR),
                BorderFactory.createEmptyBorder(26, 26, 22, 26)));

        JPanel icono = new IconoAdvertencia();
        icono.setAlignmentX(CENTER_ALIGNMENT);
        tarjeta.add(icono);

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(16f));
        lblTitulo.setForeground(Colores.TEXT_PRIMARY);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(14, 0, 8, 0));
        tarjeta.add(lblTitulo);

        JTextPane lblMensaje = new JTextPane();
        lblMensaje.setText(mensaje);
        lblMensaje.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(13f));
        lblMensaje.setForeground(Colores.TEXT_SECONDARY);
        lblMensaje.setEditable(false);
        lblMensaje.setFocusable(false);
        lblMensaje.setOpaque(false);
        lblMensaje.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);
        lblMensaje.setSize(300, Short.MAX_VALUE);
        Dimension tamanoNatural = lblMensaje.getPreferredSize();
        lblMensaje.setPreferredSize(new Dimension(300, tamanoNatural.height));
        lblMensaje.setMaximumSize(new Dimension(300, tamanoNatural.height));

        SimpleAttributeSet centrado = new SimpleAttributeSet();
        StyleConstants.setAlignment(centrado, StyleConstants.ALIGN_CENTER);
        StyledDocument documento = lblMensaje.getStyledDocument();
        documento.setParagraphAttributes(0, documento.getLength(), centrado, false);

        tarjeta.add(lblMensaje);

        JButton botonEntendido = new JButton("Entendido");
        botonEntendido.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(12f));
        botonEntendido.setBackground(Colores.INDIGO);
        botonEntendido.setForeground(Colores.WHITE);
        botonEntendido.setFocusPainted(false);
        botonEntendido.setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));
        botonEntendido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel filaBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filaBotones.setOpaque(false);
        filaBotones.setAlignmentX(CENTER_ALIGNMENT);
        filaBotones.add(botonEntendido);
        tarjeta.add(filaBotones);

        getContentPane().add(tarjeta);
        setSize(380, tarjeta.getPreferredSize().height + 30);
        setLocationRelativeTo(padre);
    }
    private static class IconoAdvertencia extends JPanel {

        public IconoAdvertencia() {
            setOpaque(false);
            setPreferredSize(new Dimension(44, 44));
            setMaximumSize(new Dimension(44, 44));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(Colores.ERROR_BG);
            g2.fillOval(0, 0, 44, 44);

            g2.setColor(Colores.ERROR);
            g2.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int[] xs = {22, 32, 12};
            int[] ys = {13, 31, 31};
            g2.drawPolygon(xs, ys, 3);
            g2.drawLine(22, 19, 22, 25);
            g2.fillOval(21, 27, 2, 2);
        }
    }
}
