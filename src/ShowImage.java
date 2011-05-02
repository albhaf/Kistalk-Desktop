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
	protected ImgRect imgRect; //
	protected TextToDisplay imageUserTxtDsp;
	protected TextToDisplay imageCommentTxtDsp;
	protected TextToDisplay[] comments;
	protected boolean outgoing = false;
	private BufferedImage slideImage;
	private float transperacy = 0;
	private int correction;

	// Variables which are set in constructor
	private Rectangle monitorSize;
	private Dimension textStartPosition = new Dimension();
	private BufferedImage image;
	private Dimension timeStill = new Dimension();
	private float imageStopPosition;
	private Dimension imageSize = new Dimension();

	// Konstanter
	private final int ImageUserFontSize = 50;
	private Font font = new Font("Serif", Font.BOLD, 50);
	private Font commentfont = new Font("Serif", Font.BOLD, 30);

	public ShowImage(Rectangle tmpmonitor, int tmpTimeStill) {

		monitorSize = tmpmonitor;
		imgRect = new ImgRect();
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		imageCommentTxtDsp = new TextToDisplay();
		imageUserTxtDsp = new TextToDisplay();

		timeStill.width = tmpTimeStill;
		timeStill.height = timeStill.width;
	}


	
	/**
	 * Scaling the image and text to fit the screen and position them right.
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


	/**
	 * Gets the image x-coordinate
	 * 
	 * @return returns a double with the x-coordinate
	 */
	public double getSlideImageX() {
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

		// Paints the iamge rectangle
		TexturePaint tp = new TexturePaint(slideImage, imgRect);
		g2d.setPaint(tp);
		g2d.fill(imgRect);
	}

}
