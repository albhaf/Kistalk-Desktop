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
	private ImgRect imgRect; //
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
	public TextToDisplay[] comments;
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
	/**
	 * A value to position imageUserTxtDsp right
	 */
	private int correction;

	// Variables which are set in constructor
	/**
	 * screen resolution
	 */
	private Rectangle monitorSize;
	/**
	 * width == Ypos, height == Xpos.
	 */
	private Dimension textStartPosition = new Dimension();
	/**
	 * A value to position imageUserTxtDsp right
	 * 
	 * private int correction; /** Image shown on screen
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
	 * @param tmpImg
	 *            The new picture which should be display.
	 * @param tmpUserString
	 *            String containing the user who uploaded the picture
	 * @param tmpImageText
	 *            String containing the image text.
	 * @param tmpmonitor
	 *            Which monitor to display the slideshow on.
	 * @param tmpTimeStill
	 *            sets the time the picture is supposed to freeze on screen.
	 * @param ImageComments
	 *            CommentXML list that contains the comments to display
	 */
	public ShowImage(Rectangle tmpmonitor, int tmpTimeStill) {

		monitorSize = tmpmonitor;
		imgRect = new ImgRect();
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		imageCommentTxtDsp = new TextToDisplay();
		imageUserTxtDsp = new TextToDisplay();

		timeStill.width = tmpTimeStill;
		timeStill.height = timeStill.width;
	}

	private void setComments(List<CommentXML> imageComments) {
		if (imageComments.size() > 0) {
			comments = new TextToDisplay[imageComments.size()];
			for (int i = 0; i < comments.length; i++) {
				comments[i] = new TextToDisplay();

				comments[i].setString(imageComments.get(i).getUser()
						+ " wrote: " + imageComments.get(i).getContent());
				comments[i].resetPos();
				comments[i].addX(monitorSize.width - monitorSize.width / 3);
				comments[i].addY(200 + (i * 100));
			}

		}
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

	private void setImageText(String tmpImageText) {
		imageCommentTxtDsp.setString(tmpImageText);
		imageCommentTxtDsp.resetPos();
		imageCommentTxtDsp.addX(100);
		imageCommentTxtDsp.addY(monitorSize.height + ImageUserFontSize);
	}

	private void setUserText(String tmpUserString) {
		imageUserTxtDsp.setString(tmpUserString + " posted: ");
		imageUserTxtDsp.resetPos();
		scalePositionImageAndText();
		imageUserTxtDsp.addX(textStartPosition.width);
		imageUserTxtDsp.addY(0);
	}

	private void setImage(BufferedImage tmpImage) {
		slideImage = tmpImage;
		imgRect.resetPos();

		float factor = (float) (tmpImage.getWidth())
				/ (float) (tmpImage.getHeight());
		imgRect.height = imageSize.height;
		imgRect.width = imgRect.height * factor;
		imgRect.addY(100);
		imgRect.addX(-200);
		imageStopPosition = ((monitorSize.width - imgRect.width
				- (monitorSize.width / 3) - 30));
		imageStopPosition = imageStopPosition - (imageStopPosition % 5);
	}

	/**
	 * Updating the Picture and text to display on screen. Resets all the
	 * necessary variables.
	 * 
	 * @param tmpImg
	 *            The new picture which should be display.
	 * @param tmpUserString
	 *            String containing the user who uploaded the picture
	 * @param tmpImageText
	 *            String containing the image text
	 * @param ImageComments
	 *            CommentXML list that contains the comments to display
	 */

	public void updatePicture(BufferedImage tmpImage, String tmpUserString,
			String tmpImageText, List<CommentXML> imageComments) {

		transperacy = 0;
		outgoing = false;
		timeStill.height = timeStill.width;
		correction = (tmpUserString.length() + 6) / 2;
	


		// resets the image comments
		comments = null;
		setComments(imageComments);

		// Bildtexten
		setImageText(tmpImageText);
				
		// Image user
		setUserText(tmpUserString);
		
		// Bilden
		setImage(tmpImage);
	
	}

	/**
	 * Change the boolean "outgoing" to indicate that the Image is moving out,
	 * towards the end of screen.
	 */
	public void changeDirection() {
		outgoing = true;
	}

	/**
	 * Handles moving and transparancy of the image and texts
	 */
	public void MoveObjects() {
		if (timeStill.height != 0 && imgRect.getX() == (int) imageStopPosition) {

			timeStill.height = timeStill.height - 1;
		} else {

			imgRect.addX(5);

		}

		if (outgoing == false) {
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

		// changes outgoing to true if the picture is supposed to move again
		// after standstill

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
	 * 
	 * @return returns a double with the x-coordinate
	 */
	public double getTextX() {
		return imageUserTxtDsp.getX();
	}

	/**
	 * Gets the text y-coordinate
	 * 
	 * @return returns a double with the y-coordinate
	 */
	public double getTxtY() {
		return imageUserTxtDsp.getY();
	}

	/**
	 * Gets the image x-coordinate
	 * 
	 * @return returns a double with the x-coordinate
	 */
	public double getSlideImageX() {
		return imgRect.getX();
	}

	/**
	 * Gets the image y-coordinate
	 * 
	 * @return returns a double with the y-coordinate
	 */
	public double getSlideImageY() {
		return imgRect.getX();
	}

	/**
	 * Called every time the frame is drawn
	 */
	public void paint(Graphics g) {
		/*
		 * sets some values and settings.
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, monitorSize.width, monitorSize.height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				transperacy));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Draws the image comments on screen if any.
		g2d.setFont(commentfont);
		g2d.setColor(Color.WHITE);

		if (comments != null) {

			for (int i = 0; i < comments.length; i++) {
				g2d.drawString(comments[i].getString(), comments[i].x,
						comments[i].y);
			}
		}

		// paints the image text and image user texts
		g2d.setFont(font);
		try {
			g2d.drawString(imageCommentTxtDsp.getString(),
					imageCommentTxtDsp.x, imageCommentTxtDsp.y);
			g2d.drawString(imageUserTxtDsp.getString(), imageUserTxtDsp.x,
					imageUserTxtDsp.y);
		} catch (NullPointerException e) {
		}
		// for(int i =0;i<imageComments.length;i++){

		// Paints the iamge rectangle
		TexturePaint tp = new TexturePaint(slideImage, imgRect);
		g2d.setPaint(tp);
		g2d.fill(imgRect);
		// g2d.setColor(Color.WHITE);

	}
}
