import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the file name of the image to search within: ");
        String largeImgName = scanner.nextLine();
        System.out.print("Please enter the file name of the image to search for: ");
        String smallImgName = scanner.nextLine();

        if (containsImage(largeImgName, smallImgName))
            System.out.print("The smaller image was found in the larger image.");
        else
            System.out.print("The smaller image was not found in the larger image.");
    }

    public static boolean containsImage(String largeImgName, String smallImgName) {
        BufferedImage largeImg = null;
        BufferedImage smallImg = null;

        try {
            largeImg = ImageIO.read(new File(largeImgName));
            smallImg = ImageIO.read(new File(smallImgName));
        } catch (IOException e) {
            System.out.println("IOException Occurred");
        }

        for (int i = 0; i < largeImg.getWidth() - smallImg.getWidth(); i++) {
            for (int j = 0; j < largeImg.getHeight() - smallImg.getHeight(); j++) {
                if (imageEquals(smallImg, largeImg.getSubimage(i, j, smallImg.getWidth(), smallImg.getHeight()))) {
                    System.out.println("height: " + j + " width: " + i);
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean imageEquals(BufferedImage image1, BufferedImage image2) {
        for (int y = 0; y < image1.getHeight(); y++) {
            for (int x = 0; x < image1.getWidth(); x++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }
}