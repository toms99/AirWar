package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MediaSources {


    public BufferedImage addImage(String path)  {
        BufferedImage mapa = null;
        try {
            mapa = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapa;
    }
}
