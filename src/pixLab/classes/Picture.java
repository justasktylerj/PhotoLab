package pixLab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void zeroRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
      }
    }
  }
  
  public void zeroGreen()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
      }
    }
  }
  
  public void zeroRedHalf()
  {
	  Pixel[][] original = this.getPixels2D(); 
	  for(int row = 0; row < original.length/2; row++)
	  {
		  for(int col = 0; col < original[0].length; col++)
		  {
			  Pixel currentPixel = original[row][col];
			  currentPixel.setRed(0);
			  
			  original[row][col].setRed(0);
		  }
	  }
  }
  
  public void onlyBlue()
  {
	  zeroRed();
	  zeroGreen();
  }
  
  public void onlyRed()
  {
	  zeroBlue();
	  zeroGreen();
  }
  
  public void onlyGreen()
  {
	  zeroRed();
	  zeroBlue();
  }
  
  public void negate()
  {

	  Pixel[][] original = this.getPixels2D();
	  for(int row = 0; row < original.length; row++)
	  {
		  for(int col = 0; col < original[0].length; col++)
		  {
			  Pixel currentPixel = original[row][col];
			  
			  int nRed = (int)(255- currentPixel.getRed());
              int nGreen = (int)(255- currentPixel.getGreen());
              int nBlue = (int)(255- currentPixel.getBlue());
              
			  
              currentPixel.setRed(nRed);
              currentPixel.setBlue(nBlue);
              currentPixel.setGreen(nGreen);
			  original[row][col].setRed(nRed);
			  original[row][col].setBlue(nBlue);
			  original[row][col].setGreen(nGreen);
		  }
		  
		}
  }
  
  public void grayScale()
  {
	  Pixel[][] original = this.getPixels2D();
	  for(int row = 0; row < original.length; row++)
	  {
		  for(int col = 0; col < original[0].length; col++)
		  {
			  Pixel currentPixel = original[row][col];
			  
			  int red = (int)(currentPixel.getRed() * 0.299);
              int green = (int)(currentPixel.getGreen() * 0.587);
              int blue = (int)(currentPixel.getBlue() *0.114);
              int grey = red+blue+green;
			  
              currentPixel.setRed(grey);
              currentPixel.setBlue(grey);
              currentPixel.setGreen(grey);
			  original[row][col].setRed(grey);
			  original[row][col].setBlue(grey);
			  original[row][col].setGreen(grey);
		  }
	  }
  }
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void randomColor()
  {
	  Pixel[][] original = this.getPixels2D();
	  for(int row = 0; row < original[0].length; row++)
	  {
		  for(int col = 0; col < original[0].length; col++)
		  {
			  Pixel currentPixel = original[row][col];
			  
			  int randomRed, randomBlue, randomGreen;
			  randomRed = (int)(Math.random() * 256);
			  randomBlue = (int)(Math.random() * 256);
			  randomGreen = (int)(Math.random() * 256);
			  
			  currentPixel.setBlue(randomBlue);
			  currentPixel.setRed(randomRed);
			  currentPixel.setGreen(randomGreen);
		  }
	  }
  }

  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = pixels[0].length - 1; col > width / 2; col--)
      {
        rightPixel = pixels[row][col];
        leftPixel = pixels[row][(width / 2) - (col-width / 2)];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  public void mirrorVerticalLeftToRight()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = pixels[0].length; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel upPixel = null;
    Pixel downPixel = null;
    int height = pixels[0].length;
    for (int col = 0; col < pixels.length; col++)
    {
      for (int row = 0; row < height / 2; row++)
      {
        upPixel = pixels[row][col];
        downPixel = pixels[height - 1 - row][col];
        downPixel.setColor(upPixel.getColor());
      }
    } 
  }
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVerticalRightToLeft();
    this.write("collage.jpg");
  }

  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public void fixUnderwater()
  {
	  Pixel[][] original = this.getPixels2D();
	  for(int row = 0; row < original.length; row++)
	  {
		  for(int col = 0; col < original[0].length; col++)
		  {
			  Pixel currentPixel = original[row][col];
			  
			  int red = (int)(currentPixel.getRed() + 50);
              int green = (int)(currentPixel.getGreen() - 100);
              int blue = (int)(currentPixel.getBlue()  - 50);
			  
              currentPixel.setRed(red);
              currentPixel.setBlue(blue);
              currentPixel.setGreen(green);
			  original[row][col].setRed(red);
			  original[row][col].setBlue(blue);
			  original[row][col].setGreen(green);
		  }
	  }
  }
  
  public void mirrorGull()
  {
	  int mirrorPoint = 350;
	    Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    int count = 0;
	    Pixel[][] pixels = this.getPixels2D();
	    
	    // loop through the rows
	    for (int row = 230; row < 325; row++)
	    {
	      // loop from 13 to just before the mirror point
	      for (int col = 235; col < mirrorPoint; col++)
	      {
	        
	        leftPixel = pixels[row][col];      
	        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
	        rightPixel.setColor(leftPixel.getColor());
	      }
	    }
	    
  }
  
  public void mirrorArms()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  int mirrorPoint = 170;
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  int gap = 72;
	   for(int row = 160; row < 210; row++)
	   {
		   for(int col = 239; col < 350; col++)
		   {
			   leftPixel = pixels[row][col];
			   rightPixel = pixels[190 - row + 190][col];
			   rightPixel.setColor(leftPixel.getColor());
		   } 
	   }
	   
	   for(int row = 160; row < 210; row++)
	   {
		   for(int col = 239; col < 350; col++)
		   {
			   leftPixel = pixels[row][col];
			   rightPixel = pixels[row][mirrorPoint - col + mirrorPoint + gap];
			   rightPixel.setColor(leftPixel.getColor());
		   } 
	   }
  }
  
  public void mirrorDiagonal()
  {
	    Pixel[][] pixels = this.getPixels2D();
	    Pixel pixel1 = null;
	    Pixel pixel2 = null;
	    int width = pixels[0].length;
	    for (int row = 0; row < pixels.length; row++)
	    {
	      for (int col = 0; col < pixels[0].length; col++)
	      {
	        if (col < pixels.length)
	        {
	            pixel1 = pixels[row][col];
	            pixel2 = pixels[col][row];
	            pixel1.setColor(pixel2.getColor());
	        }
	      }
	  }
	  
  }
  
  public static void main(String[] args) 
  {
    Picture beach = new Picture("seagull.jpg");
    beach.explore();
//    beach.onlyGreen();
//    beach.onlyBlue();
//    beach.onlyRed();
//    snowman.mirrorArms();
//    beach.fixUnderwater();
//    beach.mirrorGull();
//    beach.();    
//    beach.negate();    
//    beach.mirrorTemple();
//    beach.mirrorVerticalRightToLeft();
//    beach.mirrorVerticalLeftToRight();
//    beach.mirrorHorizontal();
  beach.mirrorDiagonal();
//    beach.grayScale();
//    beach.zeroRed();
//    beach.randomColor();
//    beach.zeroBlue();
//    beach.zeroGreen();
    
    beach.explore();
    
  }
  
} // this } is the end of class Picture, put all new methods before this
