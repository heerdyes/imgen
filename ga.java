import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.Font;

public class ga
{
  static final int      LEFT = 0;
  static final int    CENTER = 1;
  static final int     RIGHT = 2;
  static final int     SOLID = 0;
  static final int      NONE = 1;
  static final Color   DBLUE = new Color (  0,   0,  96);
  static final Color  DGREEN = new Color (  0,  84,  16);
  static final Color    DRED = new Color ( 84,   0,   0);
  static final Color   DGRAY = new Color ( 64,  64,  64);
  static final Color   LGRAY = new Color (192, 192, 192);
  static final int        NW = 10;
  static final int        NE = 11;
  static final int        SW = 12;
  static final int        SE = 13;
  static final int        WN = 14;
  static final int        EN = 15;
  static final int        WS = 16;
  static final int        ES = 17;
  
  static int wav (double amp, double frq, double ang, double pha)
  {
    return (int) Math.round (amp * Math.sin (frq * ang + pha));
  }
  
  static int wav (double amp, double frq, double ang)
  {
    return (int) Math.round (amp * Math.sin (frq * ang));
  }
  
  public static void art (Graphics2D g, int w, int h)
  {
    int   cx      = w/2;
    int   cy      = h/2;
    int   an_1    = 1;
    int   an      = 0;
    //
    g.setStroke (new BasicStroke (4f));
    for (int n = 1; n <= 100; n++)
    {
      g.fillRect (n*5, an, 3, 3);
      an   = an_1 + n - 1;
      an_1 = an;
    }
  }
  
  public static void txtbox(Graphics2D g, int x, int y, String s, Font f, int outline, int align, double angle, Color pc)
  {
    FontRenderContext frc = g.getFontRenderContext();
    int                 n = s.length();
    int               icw = 60;
    int                 h = 120;
    int               mlt = 28;
    int               mrt = 28;
    int               mbt = 40;
    int               mtp = 0;
    Rectangle2D      sbnd = f.getStringBounds (s, frc);
    int                tw =  (int) Math.round (sbnd.getWidth());
    int                th =  (int) Math.round (sbnd.getHeight());
    int             compx = align==CENTER ? (x - tw/2) : (align==RIGHT ? (x - tw) : x);
    // color
    g.setPaint  (pc);
    // push
    g.translate (compx, y);
    g.rotate    (angle);
    // 
    if (outline!=NONE)
    {
      g.drawRect (0, 0, mlt+tw+mrt, mtp+th+mbt);
    }
    g.drawString (s, mlt, mtp+th);
    // pop
    g.rotate     (-angle);
    g.translate  (-compx, -y);
  }
  
  static void ramp(Graphics2D g, Font f, String[] sa, int x, int y, int xc, int yc, Color pc, int align)
  {
    for (int i = 0; i < sa.length; i++)
    {
      txtbox (g, x+i*xc, y+i*yc, sa[i], f, NONE, align, 0, pc);
    }
  }
  
  static void antenna(Graphics2D g, Color pc, int x, int y, int dist, int dir, int n, int gap, int max, int dec)
  {
    g.setPaint (pc);
    int y2=0, yi=0;
    switch(dir)
    {
      case NE:
        y2 = y-dist;
        yi = y2;
        g.drawLine (x, y, x, y2);
        for (int i = 0; i < n; i++)
        {
          g.drawLine (x, yi, x+max-i*dec, yi);
          yi += gap;
        }
        break;
      case NW:
        y2 = y-dist;
        yi = y2;
        g.drawLine (x, y, x, y2);
        for (int i = 0; i < n; i++)
        {
          g.drawLine (x, yi, x-max+i*dec, yi);
          yi += gap;
        }
        break;
      case SW:
        y2 = y+dist;
        yi = y2;
        g.drawLine (x, y, x, y2);
        for (int i = 0; i < n; i++)
        {
          g.drawLine (x, yi, x-max+i*dec, yi);
          yi -= gap;
        }
        break;
      case SE:
        y2 = y+dist;
        yi = y2;
        g.drawLine (x, y, x, y2);
        for (int i = 0; i < n; i++)
        {
          g.drawLine (x, yi, x+max-i*dec, yi);
          yi -= gap;
        }
        break;
      default:
        throw new RuntimeException("unknown direction!");
    }
  }
  
  static void ramp(Graphics2D g, Font f, String[] sa, int x, int y, int xc, int yc, Color pc)
  {
    ramp (g, f, sa, x, y, xc, yc, pc, LEFT);
  }
  
  static void ramp(Graphics2D g, Font f, String[] sa, int x, int y, int xc, int yc)
  {
    ramp (g, f, sa, x, y, xc, yc, DGRAY, LEFT);
  }
  
  public static void poster(Graphics2D g, int w, int h, Font f)
  {
    int          cx = w/2;
    int          cy = h/2;
    String[]   uijs = new String[] {        "p5js",     "backbonejs",       "vuejs",   "reactjs",  "angularjs", "jQuery", "dojo",     "d3"};
    String[]   uijv = new String[] {         "gwt",         "vaadin",       "swing",        "fx",    "griffon"};
    String[]   uipy = new String[] {     "tkinter",           "kivy",      "pygame"};
    String[]   dbsq = new String[] {      "sqlite",          "mysql",      "oracle",       "db2", "postgresql"};
    String[]   dbjs = new String[] {   "cassandra",        "mongodb",   "couchbase"};
    String[]   mdjs = new String[] {   "expressjs",         "nodejs",      "nestjs",  "meteorjs",     "hapijs"};
    String[]   mdpy = new String[] {      "django",          "flask",      "bottle"};
    String[]   mdjv = new String[] {      "vaadin",            "jsf",      "spring",    "struts",     "grails",  "play"};
    String[]   aijs = new String[] {"tensorflowjs",        "brainjs"};
    String[]   aipy = new String[] {  "tensorflow",          "keras",      "scikit",   "seaborn", "matplotlib", "scipy", "numpy", "pandas", "spacy", "nltk"};
    String[]   aijv = new String[] {      "mahout", "deeplearning4j", "spark mllib"};
    //
    g.setStroke (new BasicStroke (6f));
    // areas
    txtbox (g,     w/2,     100,               "FRONTEND", f, SOLID, CENTER,          0, Color.black);
    txtbox (g,     200,     h/2,               "DATABASE", f, SOLID, CENTER, -Math.PI/2, Color.black);
    txtbox (g,     w/2,   h-200,                "BACKEND", f, SOLID, CENTER,          0, Color.black);
    txtbox (g,   w-100, h/2-400,               "AI/DS/ML", f, SOLID,   LEFT,  Math.PI/2, Color.black);
    txtbox (g,  w/2-50, h/2-300,                 "Python", f,  NONE, CENTER,          0,       DBLUE);
    txtbox (g, w/2+200,  h/2+50,                   "Java", f,  NONE, CENTER,          0,      DGREEN);
    txtbox (g, w/2-500,  h/2-50,             "Javascript", f,  NONE, CENTER,          0,        DRED);
    txtbox (g,    1500, h/2-450,                 "PL/SQL", f,  NONE, CENTER,          0,       DGRAY);
    txtbox (g,    1458, h/2-600,               "PROGLANG", f, SOLID, CENTER,          0, Color.black);
    // libraries
    ramp   (g, f, uijs, w/4-160,     100,  100,  100,   DRED);
    ramp   (g, f, uipy, w/2-100,     300,   50,  100,  DBLUE);
    ramp   (g, f, uijv, w/2+600,     200,  -50,  100, DGREEN);
    ramp   (g, f, dbsq,     400, h/2-600,  100,  100,  DGRAY, RIGHT);
    ramp   (g, f, dbjs,     600, h/2+200,  100, -100,   DRED, RIGHT);
    ramp   (g, f, mdjs, w/2-500,   h-800,  -50,  100,   DRED);
    ramp   (g, f, mdpy, w/2+100,   h-700,  -50,  100,  DBLUE);
    ramp   (g, f, mdjv, w/2+500,   h-800,   50,  100, DGREEN);
    ramp   (g, f, aijs,  w-1100, h/2-100,   60,  100,   DRED);
    ramp   (g, f, aipy,   w-600, h/3-500,  -60,  100,  DBLUE);
    ramp   (g, f, aijv,   w-950, h/2+100,   60,  100, DGREEN);
    // antennae, for labelling the above
    antenna    (g, DBLUE, 3*w/4-300,   1890-90, 1000-90, NE, aipy.length, 100, 650,  60);
    g.drawLine (w/2-50+200, h/2-300+50,  w/2+50+650, h/2-300+50);
    antenna    (g,  DRED,    w/4+50,     h-350,     400, NE, mdjs.length, 100, 450,  60);
    g.drawLine (   1000+50,    h/2+50,      1000+50,      h-750);
    antenna    (g,  DRED,   1000+50, h/2-40+50,  300-50, SW, dbjs.length, 100, 300,  60);
    g.drawLine (   1000+50,    h/2+10,     1000+280,     h/2+10);
    antenna    (g,  DRED,       700,    100+50, 800-100, SE, uijs.length, 100, 800, 100);
    g.drawLine (      1100,       850,         1100,       2010);
    antenna    (g, DGRAY,     1000-50, h/2-150,  450-50, NW, dbsq.length, 100, 450,  90);
    g.drawLine (       950,   h/2-400,         1350,    h/2-400);
    antenna    (g,  DRED,       w-1300, h/2-40,     100, SE, aijs.length, 100, 250,  90);
    g.drawLine (       1760,    h/2+0,         2700,    h/2+0);
    antenna    (g, DBLUE,      w/2-150,    360,     200, SE, uipy.length, 100, 150,  60);
    g.drawLine (     w/2-50,      560,       w/2-50,    1700);
    antenna    (g, DBLUE,       w/2-50,  h-450,     200, NE, mdpy.length, 100, 150,  50);
    g.drawLine (     w/2-50,      1800,       w/2-50,  h-650);
    antenna    (g, DGREEN,     w/2+370,h/2+110,    1850, NE, uijv.length, 100, 230,  50);
    antenna    (g, DGREEN,     w/2+470,h/2+110,    1650, SE, mdjv.length, 100, 280,  50);
    g.drawLine (    w/2+340,   h/2+110,      w/2+465, h/2+110);
    antenna    (g, DGREEN,     w/2+820,h/2+160,     200, SE, aijv.length, 100, 280,  50);
    g.drawLine (    w/2+470,   h/2+160,      w/2+820, h/2+160);
    // outlining
    g.setPaint (LGRAY);
    Stroke dashed = new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{32}, 0);
    g.setStroke(dashed);
    g.drawRect (     1278,     h/2-608,      1058,         800);
    g.drawLine (      100,         100,      1278,     h/2-608);
    g.drawLine (    h-100,         100, 1278+1058,     h/2-608);
    g.drawLine (      100,       h-100,      1278, h/2-608+800);
    g.drawLine (1278+1058, h/2-608+800,     w-100,       h-100);
  }
}

