import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.List;


import javax.swing.JPanel;

public class ShowImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 887346353483336091L;

	// Variables
	/**
	 * The Rectangle containing the image
	 */
	private ImgRect ImgRect; //
	/**
	 * Image uploader
	 */
	private TextToDisplay imageUserTxtDsp;
	/**
	 * Image text
	 */
	private TextToDisplay imageCommentTxtDsp;
	/**
	 * Image comments 
	 */
	public TextToDisplay[] imageComments;
	/**
	 * false if picture is moving to middle and while still, true otherwise
	 */
	private boolean outgoing = false; 
	/**
	 * Current picture to display
	 */
	BufferedImage slideImage; 
	/**
	 * Value 0-1, 0 transparent
	 */
	float transperacy = 0;

	// Variables which are set in constructor
	/**
	 * screen resolution
	 */
	private Rectangle monitorSize;
	/**
	 *  width == Ypos, height == Xpos.
	 */
	private Dimension textStartPosition = new Dimension();
	/**
	 * A value to position imageUserTxtDsp right
	 */
	private int correction;
	/**
	 * Image shown on screen
	 */
	private BufferedImage image;

	/**
	 * height == time to stand still, width == time for current image
	 */
	private Dimension timeStill = new Dimension();
	/**
	 * what y coordinate the image is supposed to stop at
	 */
	private float imageStopPosition; 
	/**
	 * width == image size x, height == image size y.
	 */
	private Dimension imageSize = new Dimension(); 

	// Konstanter
	/**
	 * Font size for the text to display on screen
	 */
	private final int ImageUserFontSize = 50;
	/**
	 * font used to display text
	 */
	Font font = new Font("Serif", Font.BOLD, 50);
	
	/**
	 *font for the comments 
	 */
	Font commentfont = new Font("Serif", Font.BOLD, 30);

	
	/**
	 * 
	 * @param tmpImg The new picture which should be display.
	 * @param tmpUserString	String containing the user who uploaded the picture
	 * @param tmpImageText String containing the image text.
	 * @param tmpmonitor Which monitor to display the slideshow on.
	 * @param tmpTimeStill sets the time the picture is supposed to freeze on screen.
	 * @param ImageComments	CommentXML list that contains the comments to display
	 */
	public ShowImage(BufferedImage tmpImage, String tmpImageUserString, String tmpImageText,   
			Rectangle tmpmonitor, int tmpTimeStill, List<CommentXML> ImageComments) {

		monitorSize = tmpmonitor;

		float factor = (float) (tmpImage.getWidth()) / (float) (tmpImage.getHeight());;

	// FontMetrics fontmetrics;
	

		image = new BufferedImage(100, /* (int)(100*factor) */100,
				BufferedImage.TYPE_INT_RGB);

		timeStill.width = tmpTimeStill;
		timeStill.height = timeStill.width;

		correction = (tmpImageUserString.length() + 6) / 2;

		// Image comments
		// imageComments = new TextDisplay[ImageComments.size()];
		// for(int i =0;i<imageComments.length;i++){
		if (ImageComments.size() > 0) {

			imageComments = new TextToDisplay[ImageComments.size()];
			for (int i = 0; i < imageComments.length; i++) {
				imageComments[i] = new TextToDisplay(10, 10, 1000, 100);
				imageComments[i].setString(ImageComments.get(i).getContent());
				transperacy = 0;

				imageComments[i].addX(1000);
				imageComments[i].addY(200 + (i * 100));
			}
			// System.out.println(ImageComments.get(i).getContent());
			// }
		}
		// ImageCommentTxt setup
		imageCommentTxtDsp = new TextToDisplay(100, 1000, 2, 1);
		imageCommentTxtDsp.setString(tmpImageText);


		// ImageUser setup
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 2));

		imageUserTxtDsp = new TextToDisplay(textStartPosition.width, 0, 2, 1);
		imageUserTxtDsp.setString(tmpImageUserString + " posted: ");

		scalePositionImageAndText();

		slideImage = tmpImage;


		imageStopPosition = (monitorSize.width / 2) - (2 * imageSize.width / 3);
		imageStopPosition = imageStopPosition - (imageStopPosition % 5);
		setDoubleBuffered(true);
		ImgRect = new ImgRect(-200, 100, imageSize.width * factor,
				imageSize.height);
	}
	
	/**
	 * Scaling the image and text to fit the screen and position them right.
	 */
	public void scalePositionImageAndText() {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.005);
		imageSize.width = (int) (imageSize.height * ration);
		correction = (imageUserTxtDsp.length() + 6) / 2;
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 3));
	}

	/**
	 * Updating the Picture and text to display on screen. 
	 * Resets all the necessary variables.
	 * 
	 * @param tmpImg The new picture which should be display.
	 * @param tmpUserString	String containing the user who uploaded the picture
	 * @param tmpImageText String containing the image text
	 * @param ImageComments	CommentXML list that contains the comments to display
	 */
	public void UpdatePicture(BufferedImage tmpImg, String tmpUserString,
			String tmpImageText, List<CommentXML> ImageComments) {
		
		// Bilden
		slideImage = tmpImg;
		ImgRect.resetPos();
		// Rect.addHeight(h);

		float factor = (float) (tmpImg.getWidth()) / (float) (tmpImg.getHeight());
		ImgRect.height = imageSize.height;
		ImgRect.width = ImgRect.height * factor;
		ImgRect.addY(100);
		ImgRect.addX(-200);

		//resets the image comments
		imageComments = null;
		
		//sets the new comments if they exist 
		// imageComments = new TextDisplay[ImageComments.size()];
		// for(int i =0;i<imageComments.length;i++){
		if (ImageComments.size() > 0) {
			imageComments = new TextToDisplay[ImageComments.size()];
			for (int i = 0; i < imageComments.length; i++) {
				imageComments[i] = new TextToDisplay(100, 100, 1000, 100);

				imageComments[i].setString(ImageComments.get(i).getUser()
						+ " wrote: " + ImageComments.get(i).getContent());
				imageComments[i].resetPos();
				imageComments[i].addX(1000);
				imageComments[i].addY(200 + (i * 100));
			}

		}

		transperacy = 0;
		// imageComments[0].setString( ImageComments.get(0).getContent());
		// }

		// Bildtexten
		imageCommentTxtDsp.setString(tmpImageText);
		imageCommentTxtDsp.resetPos();
		imageCommentTxtDsp.addX(100);
		imageCommentTxtDsp.addY(monitorSize.height + ImageUserFontSize);

		// Image user
		imageUserTxtDsp.setString(tmpUserString + " posted: ");
		imageUserTxtDsp.resetPos();
		scalePositionImageAndText();
		imageUserTxtDsp.addX(textStartPosition.width);
		imageUserTxtDsp.addY(0);

		// Nollställning av variabler
		outgoing = false;
		timeStill.height = timeStill.width;
	}

	/**
	 * Change the boolean "outgoing" to indicate that the Image is moving out, towards the end of screen.
	 */
	public void changeDirection() {
		outgoing = true;
	}

	/**
	 * Handles moving and transparancy of the image and texts
	 */
	public void MoveObjects() {
		if (timeStill.height != 0 && ImgRect.getX() == (int) imageStopPosition) {

			timeStill.height = timeStill.height - 1;
		} else {

		 	ImgRect.addX(5);

		}

		if (outgoing == false) {
			// if(imageComments[0]!=null){
			if (transperacy < 1) {
				transperacy = (float) (transperacy + 0.008);
			}
			if (transperacy > 1) {
				transperacy = 1;
				// }
			}
		} else if (outgoing == true) {
			// if(imageComments[0]!=null){
			if (transperacy > 0.01) {
				transperacy = (float) (transperacy - 0.008);
			}
			if (transperacy < 0.01) {
				transperacy = (float) 0.01;
				// }
			}
		}

		//changes outgoing to true if the picture is supposed to move again after standstill

		if (timeStill.height == 0) {
			outgoing = true;
		}
		// bild user r;relse
		if (outgoing == true) {
			imageUserTxtDsp.addY(-1);
		} else if (outgoing == false && imageUserTxtDsp.getY() < 80) {
			imageUserTxtDsp.addY(1);
		}
		// bildtext r;relse
		if (imageCommentTxtDsp.getY() > 750 && outgoing == false) {
			imageCommentTxtDsp.addY(-2);
		} else if (outgoing == true) {
			imageCommentTxtDsp.addY(2);
		}
		repaint();
	}


	/**
	 * Gets the text x-coordinate
	 * @return returns a double with the x-coordinate
	 */

	public double getTextX() {
		return imageUserTxtDsp.getX();
	}

	/**
	 * Gets the text y-coordinate
	 * @return returns a double with the y-coordinate
	 */
	public double getTxtY() {
		return imageUserTxtDsp.getY();
	}

	/**
	 * Gets the image x-coordinate
	 * @return returns a double with the x-coordinate
	 */
	public double getSlideImageX() {
		return ImgRect.getX();
	}

	/**
	 * Gets the image y-coordinate
	 * @return returns a double with the y-coordinate
	 */
	public double getSlideImageY() {
		return ImgRect.getX();
	}


	/**
	 * Called every time the frame is drawn
	 */
	public void paint(Graphics g) {
		/*
		 *sets some values and settings.
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, monitorSize.width, monitorSize.height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transperacy));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		//Draws the image comments on screen if any.
		g2d.setFont(commentfont);
		g2d.setColor(Color.WHITE);

		if (imageComments != null) {

			for (int i = 0; i < imageComments.length; i++) {
				g2d.drawString(imageComments[i].getString(),
						imageComments[i].x, imageComments[i].y);
			}
		}
		
		//paints the image text and image user texts
		g2d.setFont(font);
		g2d.drawString(imageCommentTxtDsp.getString(), imageCommentTxtDsp.x,
				imageCommentTxtDsp.y);
		g2d.drawString(imageUserTxtDsp.getString(), imageUserTxtDsp.x,
				imageUserTxtDsp.y);
		// for(int i =0;i<imageComments.length;i++){

		//Paints the iamge rectangle
		TexturePaint tp = new TexturePaint(slideImage, ImgRect);
		g2d.setPaint(tp);
		g2d.fill(ImgRect);
		// g2d.setColor(Color.WHITE);
	}
	}
