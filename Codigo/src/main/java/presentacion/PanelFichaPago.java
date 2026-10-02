package presentacion;

import entidad.FichaPago;
import entidad.Curso;
import util.Colores;
import util.Fuentes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PanelFichaPago extends JPanel {

    private static final NumberFormat FORMATO_MONEDA = NumberFormat.getCurrencyInstance(Locale.of("es", "MX"));
    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd MMM yyyy", Locale.of("es", "MX"));

    private FrmInscripcion frm;
    private JPanel recibo;
    private JLabel etiquetaFecha;
    private JLabel etiquetaFolio;
    private JPanel listaItems;
    private JLabel etiquetaTotal;

    public PanelFichaPago(FrmInscripcion frm) {
        this.frm = frm;
        setOpaque(false);
        setLayout(new BorderLayout());

        recibo = new JPanel() {
            public Dimension getMaximumSize() {
                Dimension natural = super.getMaximumSize();
                return new Dimension(520, natural.height);
            }
            public Dimension getPreferredSize() {
                Dimension natural = super.getPreferredSize();
                return new Dimension(520, natural.height);
            }
        };
        recibo.setBackground(Colores.WHITE);
        recibo.setLayout(new BoxLayout(recibo, BoxLayout.Y_AXIS));
        recibo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(4, 0, 0, 0, Colores.LINE),
                BorderFactory.createEmptyBorder(30, 30, 28, 30)));

        JLabel marca = new JLabel("FICHA DE PAGO", SwingConstants.CENTER);
        marca.setFont(Fuentes.SPACE_GROTESK_MEDIUM.deriveFont(11f));
        marca.setForeground(Colores.TEXT_MUTED);
        marca.setAlignmentX(CENTER_ALIGNMENT);
        recibo.add(marca);

        JLabel tituloRecibo = new JLabel("Inscripción de Cursos", SwingConstants.CENTER);
        tituloRecibo.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(18f));
        tituloRecibo.setForeground(Colores.TEXT_PRIMARY);
        tituloRecibo.setAlignmentX(CENTER_ALIGNMENT);
        tituloRecibo.setBorder(BorderFactory.createEmptyBorder(6, 0, 18, 0));
        recibo.add(tituloRecibo);

        JPanel meta = new JPanel(new BorderLayout());
        meta.setOpaque(false);
        meta.setAlignmentX(LEFT_ALIGNMENT);
        meta.setBorder(BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, Colores.LINE),
                BorderFactory.createEmptyBorder(8, 0, 8, 0)));
        meta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));

        etiquetaFecha = new JLabel("—");
        etiquetaFecha.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(11f));
        etiquetaFecha.setForeground(Colores.TEXT_MUTED);
        meta.add(etiquetaFecha, BorderLayout.WEST);

        etiquetaFolio = new JLabel("Folio —");
        etiquetaFolio.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(11f));
        etiquetaFolio.setForeground(Colores.TEXT_MUTED);
        meta.add(etiquetaFolio, BorderLayout.EAST);

        JPanel envoltorioMeta = new JPanel(new BorderLayout());
        envoltorioMeta.setOpaque(false);
        envoltorioMeta.setAlignmentX(LEFT_ALIGNMENT);
        envoltorioMeta.setBorder(BorderFactory.createEmptyBorder(0, 0, 16, 0));
        envoltorioMeta.add(meta, BorderLayout.CENTER);
        recibo.add(envoltorioMeta);

        listaItems = new JPanel();
        listaItems.setOpaque(false);
        listaItems.setLayout(new BoxLayout(listaItems, BoxLayout.Y_AXIS));
        listaItems.setAlignmentX(LEFT_ALIGNMENT);
        recibo.add(listaItems);

        JPanel filaTotal = new JPanel(new BorderLayout());
        filaTotal.setOpaque(false);
        filaTotal.setAlignmentX(LEFT_ALIGNMENT);
        filaTotal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, Colores.INDIGO),
                BorderFactory.createEmptyBorder(12, 0, 0, 0)));
        filaTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel etiquetaLabelTotal = new JLabel("Total");
        etiquetaLabelTotal.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(14f));
        etiquetaLabelTotal.setForeground(Colores.TEXT_PRIMARY);
        filaTotal.add(etiquetaLabelTotal, BorderLayout.WEST);

        etiquetaTotal = new JLabel("$0.00");
        etiquetaTotal.setFont(Fuentes.IBM_PLEX_MONO_REGULAR.deriveFont(20f));
        etiquetaTotal.setForeground(Colores.INDIGO);
        filaTotal.add(etiquetaTotal, BorderLayout.EAST);

        JPanel envoltorioTotal = new JPanel(new BorderLayout());
        envoltorioTotal.setOpaque(false);
        envoltorioTotal.setAlignmentX(LEFT_ALIGNMENT);
        envoltorioTotal.setBorder(BorderFactory.createEmptyBorder(14, 0, 0, 0));
        envoltorioTotal.add(filaTotal, BorderLayout.CENTER);
        recibo.add(envoltorioTotal);

        JLabel folioTexto = new JLabel("Conserva esta ficha para tu proceso de pago", SwingConstants.CENTER);
        folioTexto.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(11f));
        folioTexto.setForeground(Colores.TEXT_MUTED);
        folioTexto.setAlignmentX(CENTER_ALIGNMENT);
        folioTexto.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        recibo.add(folioTexto);

        JButton botonNueva = new JButton("Nueva inscripción");
        botonNueva.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(13f));
        botonNueva.setBackground(Colores.INDIGO);
        botonNueva.setForeground(Colores.WHITE);
        botonNueva.setFocusPainted(false);
        botonNueva.setBorder(BorderFactory.createEmptyBorder(11, 0, 11, 0));
        botonNueva.setAlignmentX(LEFT_ALIGNMENT);
        botonNueva.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        botonNueva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frm.nuevaInscripcion();
            }
        });
        JPanel envoltorioBotonNueva = new JPanel(new BorderLayout());
        envoltorioBotonNueva.setOpaque(false);
        envoltorioBotonNueva.setAlignmentX(LEFT_ALIGNMENT);
        envoltorioBotonNueva.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        envoltorioBotonNueva.add(botonNueva, BorderLayout.CENTER);
        recibo.add(envoltorioBotonNueva);

        JPanel centrador = new JPanel();
        centrador.setOpaque(false);
        centrador.add(recibo);
        add(centrador, BorderLayout.NORTH);
    }

    public void mostrarFicha(FichaPago fichaPago) {
        listaItems.removeAll();

        List<Curso> cursos = fichaPago.getCursos();
        for (int i = 0; i < cursos.size(); i++) {
            Curso curso = cursos.get(i);
            FilaReciboItem item = new FilaReciboItem(curso.getNombre(), FORMATO_MONEDA.format(curso.getCosto()));
            item.setAlignmentX(LEFT_ALIGNMENT);
            listaItems.add(item);
        }

        etiquetaFecha.setText(FORMATO_FECHA.format(fichaPago.getFecha()));
        etiquetaFolio.setText("Folio " + fichaPago.getFolio());
        etiquetaTotal.setText(FORMATO_MONEDA.format(fichaPago.getTotal()));

        revalidate();
        repaint();
    }
    
    private static class FilaReciboItem extends JPanel {

        public FilaReciboItem(String nombre, String precioTexto) {
            setOpaque(false);
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createEmptyBorder(0, 0, 9, 0));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));

            JLabel lblNombre = new JLabel(nombre);
            lblNombre.setFont(Fuentes.SPACE_GROTESK_REGULAR.deriveFont(13f));
            lblNombre.setForeground(Colores.TEXT_PRIMARY);
            GridBagConstraints cNombre = new GridBagConstraints();
            cNombre.gridx = 0;
            cNombre.weightx = 0;
            cNombre.anchor = GridBagConstraints.WEST;
            add(lblNombre, cNombre);

            PuntosGuia puntos = new PuntosGuia();
            GridBagConstraints cPuntos = new GridBagConstraints();
            cPuntos.gridx = 1;
            cPuntos.weightx = 1;
            cPuntos.fill = GridBagConstraints.HORIZONTAL;
            cPuntos.insets = new Insets(0, 6, 0, 6);
            add(puntos, cPuntos);

            JLabel lblPrecio = new JLabel(precioTexto);
            lblPrecio.setFont(Fuentes.IBM_PLEX_MONO_REGULAR.deriveFont(13f));
            lblPrecio.setForeground(Colores.TEXT_PRIMARY);
            GridBagConstraints cPrecio = new GridBagConstraints();
            cPrecio.gridx = 2;
            cPrecio.weightx = 0;
            cPrecio.anchor = GridBagConstraints.EAST;
            add(lblPrecio, cPrecio);
        }
    }

    private static class PuntosGuia extends JPanel {

        public PuntosGuia() {
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0xC7, 0xCC, 0xDA));
            float[] patron = {1f, 3f};
            g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, patron, 0));
            int y = getHeight() / 2;
            g2.drawLine(2, y, getWidth() - 2, y);
        }
    }
}
