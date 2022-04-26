package RendererTest;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

public class ImageWritterTest {

    @Test
    public  void testWriteImage(){
        ImageWriter imageWriter = new ImageWriter("test",800,500); // create 800x500 resolution
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % 50 == 0) { // every 50th pixel make a "grid" pixel
                    imageWriter.writePixel(i, j, new Color(100,200,111));
                }
                else if (j % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.RED);
                } else {
                    imageWriter.writePixel(i, j, Color.YELLOW);
                }
            }
        }
        imageWriter.writeToImage();
    }
}
