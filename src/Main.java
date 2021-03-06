import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the file name of the image to search within: ");
        String largeImgName = scanner.nextLine();
        System.out.print("Please enter the file name of the image to search for: ");
        String smallImgName = scanner.nextLine();

        long startTime = System.currentTimeMillis();

        if (containsImage("OneRow.png", "Waldo.png"))
            System.out.println("The smaller image was found in the larger image.");
        else
            System.out.println("The smaller image was not found in the larger image.");

        System.out.println("milliseconds: " +  (System.currentTimeMillis() - startTime));
    }

    public static boolean containsImage(String largeImgName, String smallImgName) {
        BufferedImage largeImg = null;
        BufferedImage smallImg = null;
        BufferedImage resultImg;
        File resultFile;
        int smImgWidth, smImgHeight;

        
        try {
            largeImg = ImageIO.read(new File(largeImgName));
            smallImg = ImageIO.read(new File(smallImgName));
        } catch (IOException e) {
            System.out.println("IOException Occurred");
        }
      
        //Dimensions of small image
        smImgWidth = smallImg.getWidth();
        smImgHeight = smallImg.getHeight();

        int heightDiff = largeImg.getWidth() - smallImg.getWidth();
        int widthDiff = largeImg.getHeight() - smallImg.getHeight();

        for (int i = 0; i < heightDiff; ++i) {
            for (int j = 0; j < widthDiff; ++j) {
                if (imageEquals(smallImg, largeImg, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean imageEquals(BufferedImage image1, BufferedImage image2, int image2x, int image2y) {
        for (int y = 0; y < image1.getHeight(); ++y) {
            for (int x = 0; x < image1.getWidth(); ++x) {
                if (image1.getRGB(x, y) != image2.getRGB(image2x + x, image2y + y)) {
                    return false;
                }
            }
        }

        return true;
    }
}
