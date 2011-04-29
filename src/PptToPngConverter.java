import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;


public class PptToPngConverter {
	
	public PptToPngConverter(){
		
	}
	Slide[] slide;
	FileInputStream is = new FileInputStream("C:\\Users\\Ludvig\\Documents\\asd.ppt");
	
	SlideShow ppt = new SlideShow(is);


	is.close();

	Dimension pgsize = ppt.getPageSize();

	 slide = ppt.getSlides();
	for (int i = 0; i < slide.length; i++) {

	BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
	BufferedImage.TYPE_INT_RGB);
	Graphics2D graphics = img.createGraphics();
	//clear the drawing area
	graphics.setPaint(Color.white);
	graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
	slide[0].draw(graphics);
	//render
	slide[i].draw(graphics);

	//save the output
	FileOutputStream out = new FileOutputStream("slide-" + (i+1) + ".png");
	javax.imageio.ImageIO.write(img, "png", out);
	out.close();
  
}}
}
