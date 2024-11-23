package image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageGenerator {
    public static byte[] createImageWithText(String text) throws IOException {
        int width = 500;
        int height = 200;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        // Configurar fundo branco
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // Configurar texto
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 24));

        // Centralizar texto
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int x = (width - fontMetrics.stringWidth(text)) / 2;
        int y = ((height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent();

        graphics.drawString(text, x, y);
        graphics.dispose();

        // Escrever a imagem em memória
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", baos);
            return baos.toByteArray();
        }
    }
}
