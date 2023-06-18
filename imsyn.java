import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class imsyn
{
  static int width  = 4000;
  static int height = 4000;
  static Font   fnt = new Font ("OCRA", Font.PLAIN, 56);
  
  static Graphics2D setup(BufferedImage im, Color bg, Color fg)
  {
    Graphics2D g2 = im.createGraphics();
    g2.setPaint (bg);
    g2.fillRect (0, 0, width, height);
    g2.setPaint (fg);
    g2.setFont  (fnt);
    return g2;
  }
  
  static void gen(String fn, BufferedImage img)
  {
    try
    {
      File imf = new File(fn);
      ImageIO.write (img, "png", imf);
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
    }
  }
  
  public static void main(String... args)
  {
    BufferedImage bi  = new BufferedImage(width,  height,      BufferedImage.TYPE_INT_RGB);
    Graphics2D    g2  = setup            (bi,     Color.white, new Color(0, 64, 0));
    //
    ga.poster (g2,                   width, height, fnt);
    gen       ("out/tmp.png", bi);
  }
}

