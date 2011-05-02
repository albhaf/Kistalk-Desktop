import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

public class PptToPng {

	/**
	 * String with the full path to the file + filename
	 */
	String filepath;
	int imgWidth;
	int imgHeight;

	/**
	 * constructor which sets the filepath to the passed argument.
	 * 
	 * @param tmp
	 */
	public PptToPng(String tmp) {
		filepath = tmp;
	}

	/**
	 * Constructor which only sets the dimension to scale the slides.
	 * 
	 * @param tmpDims
	 *            Rectangle which width and height is the the dimensions the
	 *            slides should be sclaed to.
	 */
	public PptToPng(Rectangle tmpDims) {
		imgWidth = tmpDims.width;
		imgHeight = tmpDims.height;
	}

	/**
	 * Constructor which sets both filepath and dimension.
	 * 
	 * @param tmpPath
	 *            constructor which sets the filepath to the passed argument.
	 * @param tmpDims
	 *            Rectangle which width and height is the the dimensions the
	 *            slides should be sclaed to
	 */
	public PptToPng(String tmpPath, Rectangle tmpDims) {
		filepath = tmpPath;
		imgWidth = tmpDims.width;
		imgHeight = tmpDims.height;
	}

	/**
	 * constructor.
	 */
	public PptToPng() {
	}

	/**
	 * Setter for height to scale to.
	 * 
	 * @param tmpH
	 *            int, height which the slide should be scaled to.
	 */
	public void setHeight(int tmpH) {
		imgHeight = tmpH;
	}

	/**
	 * Setter for the width.
	 * 
	 * @param tmpW
	 *            int, width which the slide should be scaled to.
	 */
	public void setWidth(int tmpW) {
		imgWidth = tmpW;
	}

	/**
	 * Method for setting the scaling dimensions
	 * 
	 * @param tmp
	 *            Rectangle, containing the width and height which the slides
	 *            should be scaled to
	 */
	public void setDimensions(Rectangle tmp) {
		imgWidth = tmp.width;
		imgHeight = tmp.height;
	}

	/**
	 * Method for setting all the parameters.
	 * 
	 * @param tmpPath
	 *            String, Filepath.
	 * @param tmpDims
	 *            Rectangle, containing the dimensions for scaling.
	 */
	public void setAll(String tmpPath, Rectangle tmpDims) {
		filepath = tmpPath;
		imgWidth = tmpDims.width;
		imgHeight = tmpDims.height;
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
		converter(filepath, imgWidth, imgHeight);
	}

	/**
	 * Method for extracting the slides with the passed parameters without
	 * setting the objects parameters.
	 * 
	 * @param tmpPath
	 *            String, filepath.
	 * @param tmpDims
	 *            Rectangle, Dimension which the slides should be scaled to.
	 */
	public void extract(String tmpPath, Rectangle tmpDims) {
		converter(filepath, tmpDims.width, tmpDims.height);
	}

	/**
	 *Method for extracting the slides with the passed parameters without
	 * setting the objects parameters.
	 * 
	 * @param tmpPath
	 *            String, filepath.
	 * @param tmpwidth
	 *            int, width which the slides should be scaled to.
	 * @param tmpHeight
	 *            int, height which the slides should be scaled to.
	 */
	public void extract(String tmpPath, int tmpwidth, int tmpHeight) {
		converter(filepath, tmpwidth, tmpHeight);
	}

	/**
	 * Mehtod to extract the slides without converting them.
	 */
	public void extractWoScale() {
		converter(filepath, 0, 0);
	}

	/**
	 * Opens the ppt file and returns it in a SlideShow.
	 * 
	 * @param tmpFilepath
	 *            Path to the .ppt file.
	 * @return SlideShow, the SlideShow from the ppt file.
	 * @throws IOException
	 */
	private SlideShow fileOpener(String tmpFilepath) throws IOException {
		SlideShow ppt;
		FileInputStream is = new FileInputStream(tmpFilepath);
		ppt = new SlideShow(is);

		is.close();

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
	private void pngWriter(BufferedImage tmpImg, String tmpName)
			throws IOException {
		FileOutputStream out = new FileOutputStream((tmpName) + ".hansimage");
		javax.imageio.ImageIO.write(tmpImg, "png", out);
		out.close();
	}

	/**
	 * 
	 * @param tmpFilepath
	 * @param tmpimgWidth
	 * @param tmpImgHeight
	 */
	private void converter(String tmpFilepath, int tmpimgWidth, int tmpImgHeight) {
		Slide[] slide;
		SlideShow ppt = null;

		try {
			ppt = fileOpener(tmpFilepath);
		} catch (IOException e) {
			System.out.println("Kunde inte hitta eller oppna filen");
		}

		Dimension dimension = ppt.getPageSize();

		BufferedImage bImg = null;
		slide = ppt.getSlides();
		for (int i = 0; i < slide.length; i++) {

			// if tmpimgWidth is 0 the slide shouldn't be scaled and therefore
			// is slideToImage called with the scaling values set to slide size.
			if (tmpimgWidth == 0) {
				bImg = slideToImage(slide[i], dimension, dimension.width,
						dimension.height);
			} else {
				bImg = slideToImage(slide[i], dimension, tmpimgWidth,
						tmpImgHeight);
			}

			// save the output
			try {
				pngWriter(bImg, "slide-" + i);
			} catch (IOException e) {
				System.out.println("Kunde inte skriva slide" + i
						+ "till ny fil");
			}
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
	private BufferedImage slideToImage(Slide tmpslide, Dimension dimension,
			int tmpimgWidth, int tmpImgHeight) {

		BufferedImage img = new BufferedImage(tmpimgWidth, tmpImgHeight,
				BufferedImage.TYPE_INT_RGB);

		// Creates and calculate the scaling properties
		AffineTransform at = new AffineTransform();
		at.scale((tmpimgWidth / dimension.getWidth()),
				(tmpImgHeight / dimension.getHeight()));

		// create the graphic "painter"
		Graphics2D graphics = img.createGraphics();

		// applies sclaing and backgroundcolor to the graphics
		graphics.setTransform(at);
		graphics.setPaint(Color.white);

		// fills the image with a square
		graphics.fill(new Rectangle2D.Float(0, 0, tmpimgWidth, tmpImgHeight));

		// render image in the square
		tmpslide.draw(graphics);

		return img;
	}
}
