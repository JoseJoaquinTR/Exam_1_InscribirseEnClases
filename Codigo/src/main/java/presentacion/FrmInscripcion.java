package presentacion;

import modelo.ModeloInscripcion;
import modelo.IObservador;
import control.Controlador;
import entidad.Curso;
import util.Colores;
import util.Fuentes;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrmInscripcion extends JFrame implements IObservador {

    private Controlador controlador;
    private Runnable alReiniciar;

    private PanelCursos panelCursos;
    private PanelCursosInscritos panelCursosInscritos;
    private PanelFichaPago panelFichaPago;

    private JPanel cuerpo;
    private CardLayout cardLayout;

    public FrmInscripcion(ModeloInscripcion modelo, Controlador controlador, Runnable alReiniciar) {
        this.controlador = controlador;
        this.alReiniciar = alReiniciar;
        modelo.agregarObserver(this);
        construirVentana();
        controlador.obtenerCursosDisponibles();
    }

    private void construirVentana() {
        setTitle("Inscripcion de Cursos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Colores.PAPER);

        add(construirEncabezado(), BorderLayout.NORTH);

        panelCursos = new PanelCursos(this);
        panelCursosInscritos = new PanelCursosInscritos(this);
        panelFichaPago = new PanelFichaPago(this);

        JPanel vistaInscripcion = construirVistaInscripcion();

        cardLayout = new CardLayout();
        cuerpo = new JPanel(cardLayout);
        cuerpo.setBackground(Colores.PAPER);
        cuerpo.add(vistaInscripcion, "inscripcion");
        cuerpo.add(construirVistaFicha(), "ficha");

        add(cuerpo, BorderLayout.CENTER);

        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel();
        encabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 14, 0));
        encabezado.setBackground(Colores.INDIGO);
        encabezado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 4, 0, Colores.GOLD),
                BorderFactory.createEmptyBorder(22, 32, 22, 32)));

        JLabel titulo = new JLabel("Inscripción de Cursos");
        titulo.setFont(Fuentes.SPACE_GROTESK_BOLD.deriveFont(19f));
        titulo.setForeground(Colores.WHITE);
        encabezado.add(titulo);

        JPanel envoltorio = new JPanel(new BorderLayout());
        envoltorio.setBackground(Colores.INDIGO);
        envoltorio.add(encabezado, BorderLayout.CENTER);
        return envoltorio;
    }

    private JPanel construirVistaInscripcion() {
        JPanel contenido = new JPanel(new GridBagLayout());
        contenido.setOpaque(false);

        GridBagConstraints cCursos = new GridBagConstraints();
        cCursos.gridx = 0;
        cCursos.gridy = 0;
        cCursos.weightx = 1.15;
        cCursos.weighty = 1;
        cCursos.fill = GridBagConstraints.BOTH;
        cCursos.anchor = GridBagConstraints.NORTH;
        cCursos.insets = new Insets(0, 0, 0, 28);
        contenido.add(panelCursos, cCursos);

        GridBagConstraints cInscritos = new GridBagConstraints();
        cInscritos.gridx = 1;
        cInscritos.gridy = 0;
        cInscritos.weightx = 1;
        cInscritos.fill = GridBagConstraints.HORIZONTAL;
        cInscritos.anchor = GridBagConstraints.NORTH;
        contenido.add(panelCursosInscritos, cInscritos);

        JPanel envoltorio = new JPanel(new BorderLayout());
        envoltorio.setOpaque(false);
        envoltorio.setBorder(new EmptyBorder(32, 0, 64, 0));

        JPanel centrado = new JPanel(new BorderLayout());
        centrado.setOpaque(false);
        centrado.setMaximumSize(new Dimension(1500, 900));
        centrado.setBorder(new EmptyBorder(0, 24, 0, 24));
        centrado.add(contenido, BorderLayout.CENTER);

        JPanel colChica = new JPanel();
        colChica.setOpaque(false);
        JPanel filaCentradora = new JPanel();
        filaCentradora.setOpaque(false);
        filaCentradora.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centrado.setPreferredSize(new Dimension(1500, 700));
        filaCentradora.add(centrado);

        envoltorio.add(filaCentradora, BorderLayout.NORTH);
        return envoltorio;
    }

    private JPanel construirVistaFicha() {
        JPanel envoltorio = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        envoltorio.setOpaque(false);
        envoltorio.setBorder(new EmptyBorder(32, 0, 64, 0));
        envoltorio.add(panelFichaPago);
        return envoltorio;
    }

    public void seleccionarCurso(Curso curso) {
        controlador.seleccionarCurso(curso);
    }

    public void quitarCurso(Curso curso) {
        controlador.quitarCurso(curso);
    }

    public void finalizarInscripcion() {
        controlador.finalizarInscripcion();
    }

    public void nuevaInscripcion() {
        dispose();
        if (alReiniciar != null) {
            alReiniciar.run();
        }
    }

    public void mostrarMensaje(String titulo, String mensaje) {
        DialogoError dialogo = new DialogoError(this, titulo, mensaje);
        dialogo.setVisible(true);
    }

    public void updateDisponibles(ModeloInscripcion modelo) {
        panelCursos.mostrarDisponibles(modelo.getCursosDisponibles());
    }

    public void updateInscritos(ModeloInscripcion modelo) {
        panelCursosInscritos.mostrarInscritos(modelo.getCursosInscritos());
    }

    public void updateTotal(ModeloInscripcion modelo) {
        panelCursosInscritos.mostrarTotal(modelo.getTotal());
    }

    public void updateMensaje(ModeloInscripcion modelo) {
        mostrarMensaje(modelo.getTituloMensaje(), modelo.getMensajeCursoNoseleccionado());
    }

    public void updateFichaPago(ModeloInscripcion modelo) {
        panelFichaPago.mostrarFicha(modelo.getFichaPago());
        cardLayout.show(cuerpo, "ficha");
    }
}
