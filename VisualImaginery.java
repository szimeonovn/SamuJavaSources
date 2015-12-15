 /*Szimeonov Nikolett, 2015.12.09.*/ 


 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontFormatException;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import java.text.*;
 import java.io.*;
 import java.util.*;
 import javax.imageio.*;
 import java.awt.image.BufferedImage;
 
private  QL ql;
private  Queue<SPOTriplet> program;
private  int stmt_counter = 0;
private static final int stmt_max = 7;
private static final int height = 512;
private static final int width = 512;

class VisualImagery
{
public VisualImagery(){
	program = new LinkedList<SPOTriplet>();
	ql = new QL();
  }

  public void putIn(List<SPOTriplet> triplets) {


    if ( triplets.size() ==0)
      return;

    for ( SPOTriplet triplet : triplets )
      {
        if ( program.size() >= stmt_max )
          program.poll();

        program.offer ( triplet );
      }

      
   try{  
   
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    File outputfile = new File("samu_vi_" + timeInName() + ".png");

    Graphics graphics = bi.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
    graphics.setColor(Color.BLACK);
    
    try {
      graphics.setFont(Font.createFont(Font.TRUETYPE_FONT,
	new File("/usr/share/fonts/truetype/dejavu/DejaVuSansMono-Bold.ttf")).deriveFont(18.0f));
	} catch (FontFormatException e) {
	graphics.setFont(new Font("Arial", Font.BOLD, 14));
	}

    Queue<SPOTriplet> run = program;


    String prg = "";
    stmt_counter = 0;
    while ( run.peek() != null )
      {
        SPOTriplet triplet = run.peek();

        prg += triplet.s;
        prg += triplet.p;
        prg += triplet.o;

        graphics.drawString("" + triplet.s + "." + triplet.p + "(" + triplet.o + ")", 5, 256 - (++stmt_counter) * 28);

        run.poll();
      }

    ImageIO.write(bi, "png", outputfile);
    
    double img_input[] = new double[height * width];

    for ( int i=0; i<height; ++i )
      for ( int j=0; j<width; ++j )
        {
          img_input[i*256+j] = dread ( i, j, bi );
        }

    	long start = System.nanoTime();

	System.out.println("QL start... ");
	System.out.println(ql.ThisWasAnOperator(triplets.get(0), prg, img_input));
	System.out.println("" + (System.nanoTime() - start) + " ms ");

  
  }catch(IOException e){
  System.err.print("IO Exception found: " + e.toString() + "\n");
  }
  
  }

  private double dread(int x, int y, BufferedImage im) {
    return im.getRGB(x, y) / 65535.0;
  }
  
  public double reward ()
  {
    return ql.reward();
  }
  
  private String timeInName() {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
	Date now = new Date();
	String strDate = sdf.format(now);
	return strDate;
  }

};