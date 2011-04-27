import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;


public class FrameBackground extends Panel{
	
    //AffineTransform at = new AffineTransform();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3760882042250243260L;

	public FrameBackground() {
        
    }
	
	public void UpdatePicture(Image tmp){

	}
	
/*	public int getY(){
		return y;
	}
	
	public int getX(){
		return x;
	}
	
	public void setY(int tmpy){
		y=tmpy;
	}
	
	public void setX(int tmpx){
		x=tmpx;
	}*/
	
	

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
g2d.fillRect(0, 0, 1440, 900);
g2d.setColor(Color.BLACK);
    }

}
