package util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
public class Fuentes {

    public static final Font SPACE_GROTESK_REGULAR;
    public static final Font SPACE_GROTESK_MEDIUM;
    public static final Font SPACE_GROTESK_BOLD;
    public static final Font IBM_PLEX_MONO_REGULAR;

    static {
        SPACE_GROTESK_REGULAR = cargar("/fonts/SpaceGrotesk-Regular.ttf");
        SPACE_GROTESK_MEDIUM = cargar("/fonts/SpaceGrotesk-Medium.ttf");
        SPACE_GROTESK_BOLD = cargar("/fonts/SpaceGrotesk-Bold.ttf");
        IBM_PLEX_MONO_REGULAR = cargar("/fonts/IBMPlexMono-Regular.ttf");
    }

    private static Font cargar(String ruta) {
        try {
            InputStream entrada = Fuentes.class.getResourceAsStream(ruta);
            if (entrada == null) {
                System.out.println("No se encontro el archivo de fuente: " + ruta);
                return new Font("SansSerif", Font.PLAIN, 12);
            }
            Font fuente = Font.createFont(Font.TRUETYPE_FONT, entrada);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fuente);
            return fuente;
        } catch (FontFormatException e) {
            return new Font("SansSerif", Font.PLAIN, 12);
        } catch (IOException e) {
            return new Font("SansSerif", Font.PLAIN, 12);
        }
    }
}
