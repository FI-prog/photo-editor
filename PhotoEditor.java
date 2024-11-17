
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PhotoEditor {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java PhotoEditor <input_file> <effect> <output_file>");
            return;
        }

        String inputFile = args[0];
        String effect = args[1];
        String outputFile = args[2];

        try {
            BufferedImage image = ImageIO.read(new File(inputFile));
            BufferedImage editedImage;

            switch (effect.toLowerCase()) {
                case "grayscale":
                    editedImage = applyGrayscale(image);
                    break;
                case "sepia":
                    editedImage = applySepia(image);
                    break;
                default:
                    System.out.println("Unknown effect: " + effect);
                    return;
            }

            ImageIO.write(editedImage, "png", new File(outputFile));
            System.out.println("Image saved to: " + outputFile);

        } catch (Exception e) {
            System.out.println("Error processing image: " + e.getMessage());
        }
    }

    private static BufferedImage applyGrayscale(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                Color newColor = new Color(gray, gray, gray);
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        return result;
    }

    private static BufferedImage applySepia(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));
                int red = (int) (color.getRed() * 0.393 + color.getGreen() * 0.769 + color.getBlue() * 0.189);
                int green = (int) (color.getRed() * 0.349 + color.getGreen() * 0.686 + color.getBlue() * 0.168);
                int blue = (int) (color.getRed() * 0.272 + color.getGreen() * 0.534 + color.getBlue() * 0.131);

                red = Math.min(255, red);
                green = Math.min(255, green);
                blue = Math.min(255, blue);

                Color newColor = new Color(red, green, blue);
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        return result;
    }
}
