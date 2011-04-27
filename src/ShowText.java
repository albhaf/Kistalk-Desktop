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


public class ShowText extends Panel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7127218689760312106L;
	/**
	 * 
	 */
	Dimension size;
	Image castle;
	private int x=0;
	private int y =900/2;
    //AffineTransform at = new AffineTransform();
	
	public ShowText() {

        size = new Dimension();

        
    }
	
	public void UpdatePicture(Image tmp){
        castle = tmp/*.getScaledInstance(200, -1, Image.SCALE_SMOOTH)*/;
        size.width = castle.getWidth(null);
        size.height = castle.getHeight(null);
		this.setLocation(x, 0);
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
      //  g2d.setBackground(Color.BLACK);
 /*       AffineTransform toCenterAt = new AffineTransform();
        toCenterAt.concatenate(at);
        toCenterAt.translate(-(1440/2), -(900/2));
        g2d.setTransform(toCenterAt);*/
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //g2d.fillRect(0,0,1440,900);
        g2d.drawString("asdasd", 100, 100);
    }

}
