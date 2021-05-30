package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * tests for renderer.ImageWriter class
 */
class ImageWriterTest {

    @Test
    void testWriteToImage() {
        int nX=800;
        int nY =500;
        ImageWriter imageWriter = new ImageWriter("testblue",nX,nY);
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                // 800/16 = 50
                if (i % 50 == 0) {                 //To build a grid of lines (squares in the picture)
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                // 500/10 = 50
                else if (j % 50 == 0) {          //To build a grid of lines (squares in the picture)
                    imageWriter.writePixel(i, j, Color.BLACK);
                } else {
                    imageWriter.writePixel(i, j, Color.BLUE);
                }
            }
        }
        imageWriter.writeToImage();
    }

}