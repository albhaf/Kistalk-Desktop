import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

public class PptToPng {

	/**
	 * String with the full path to the file + filename
	 */
	String filepath;
	int nrFiles;

	/**
	 * constructor which sets the filepath to the passed argument.
	 * 
	 * @param tmp
	 */
	public PptToPng(String tmp) {
		filepath = tmp;
		nrFiles = 0;
		extract();
	}

	public int getNrFiles() {
		return nrFiles;
	}

	/**
	 * Method for setting the filepath to the passed argument.
	 * 
	 * @param tmp
	 *            String, filepath
	 */
	public void setFilepath(String tmp) {
		filepath = tmp;
	}

	// Variety of method to start the conversion
	/**
	 * Method for extracting all of the slides in a .ppt file to png format
	 * stored in .hansimage files.
	 * 
	 */
	public void extract() {
		converter(filepath);
	}

	/**
	 * Opens the ppt file and returns it in a SlideShow.
	 * 
	 * @param tmpFilepath
	 *            Path to the .ppt file.
	 * @return SlideShow, the SlideShow from the ppt file.
	 * @throws IOException
	 */
	private SlideShow fileOpener(String tmpFilepath) {
		SlideShow ppt = null;
		FileInputStream is;
		try {
			is = new FileInputStream(tmpFilepath);
			ppt = new SlideShow(is);
			is.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}




		return ppt;
	}

	/**
	 * Writes one slide to a file in png format.
	 * 
	 * @param tmpImg
	 *            BufferedImage, Image to save as file.
	 * @param tmpName
	 *            String, the filename.
	 * @throws IOException
	 */
	private void pngWriter(BufferedImage tmpImg, String tmpName){
		FileOutputStream out;
		try {
			out = new FileOutputStream("Images//" + (tmpName) + ".hansimage");
			ImageIO.write(tmpImg, "png", out);
			out.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}


	}

	/**
	 * 
	 * @param tmpFilepath
	 * @param tmpimgWidth
	 * @param tmpImgHeight
	 */
	private void converter(String tmpFilepath){
		Slide[] slide;
		SlideShow ppt = null;

		File dir = new File("Images");
		dir.deleteOnExit();
		dir.mkdir();

		ppt = fileOpener(tmpFilepath);
	
		Dimension dimension = ppt.getPageSize();
		BufferedImage bImg = null;
		slide = ppt.getSlides();
		for (int i = 0; i < slide.length; i++) {

			bImg = slideToImage(slide[i], dimension);

			pngWriter(bImg, "slide-" + i);
			nrFiles = i + 1;


		}

		
	}

	/**
	 * Creates an BufferedImage of the passed slide.
	 * 
	 * @param tmpslide
	 *            slide to copied to a BufferedImage.
	 * @param dimension
	 *            Dimension, the dimension of the slide
	 * @param tmpimgWidth
	 *            int, width which the slide will be scaled to.
	 * @param tmpImgHeight
	 *            int, height which the slide will be scaled to.
	 * @return BufferedImage, the image scaled image copy of the slide.
	 */
	private BufferedImage slideToImage(Slide tmpslide, Dimension dimension) {

		BufferedImage img = new BufferedImage(dimension.width,
				dimension.height, BufferedImage.TYPE_INT_RGB);

		// create the graphic "painter"
		Graphics2D graphics = img.createGraphics();
		
		graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC  );
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setPaint(Color.white);

		// creates a square
		graphics.fill(new Rectangle2D.Float(0, 0, dimension.width,
				dimension.height));

		// render image in the square
		tmpslide.draw(graphics);

		return img;
	}
}
