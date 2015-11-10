import java.awt.image.BufferedImage;
import java.awt.Color;
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


        if (!containsImage(largeImgName, smallImgName))
           System.out.print("The smaller image was not found in the larger image.");
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

        for (int i = 0; i < largeImg.getWidth() - smImgWidth; i++) {
            for (int j = 0; j < largeImg.getHeight() - smImgHeight; j++) {
                if (imageEquals(smallImg, largeImg.getSubimage(i, j, smImgWidth, smImgHeight))) {
                    System.out.println("height: " + j + " width: " + i);
                  
                    //make copy of master
                    resultImg = largeImg.getSubimage(0, 0, largeImg.getWidth(), largeImg.getHeight());
                  
                    //draw box, 2 pixels wide for visibility
                    for (int x = i; x < i + smImgWidth; x++) {
                        resultImg.setRGB(x, j, Color.GREEN.getRGB());
                        resultImg.setRGB(x, j + smImgHeight, Color.GREEN.getRGB());
                    }
                    for (int y = j; y < j + smImgHeight; y++) {
                        resultImg.setRGB(i, y, Color.GREEN.getRGB());
                        resultImg.setRGB(i + smImgWidth, y, Color.GREEN.getRGB());
                    }
                    
                  
                    System.out.println("Waldo has been boxed and saved as foundWaldo.png\n");
                  
                  
                    resultFile = new File("foundWaldo.png");
                  
                    try {
                        ImageIO.write(resultImg, "png", resultFile);
                    } catch (IOException e) {
                        System.out.println("shit happened\n");
                    }
                  
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
