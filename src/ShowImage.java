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
	private ShowImageSet showImageSet;
	private ShowImageMovement showImageMovement;
	private ShowImageDrawing showImageDrawing;

	// Variables which are set in constructor
	private Rectangle monitorSize;
	private Dimension textStartPosition = new Dimension();
	private BufferedImage image;
	private Dimension timeStill = new Dimension();
	private float imageStopPosition;
	private Dimension imageSize = new Dimension();

	// Konstanter
	private final int ImageUserFontSize = 50;


	public ShowImage(Rectangle tmpmonitor, int tmpTimeStill) {
		monitorSize = tmpmonitor;
		imgRect = new ImgRect();
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		imageCommentTxtDsp = new TextToDisplay();
		imageUserTxtDsp = new TextToDisplay();

		timeStill.width = tmpTimeStill;
		timeStill.height = timeStill.width;
		showImageSet = new ShowImageSet();
		showImageMovement = new ShowImageMovement();
		showImageDrawing = new ShowImageDrawing();
	}

	public void resetPicture() {
		transperacy = 0;
		outgoing = false;
		timeStill.height = timeStill.width;
		comments = null;
	}

	public void scalePositionImageAndText() {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.005);
		imageSize.width = (int) (imageSize.height * ration);
		correction = (imageUserTxtDsp.length() + 6) / 2;
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 3));
	}

	public void MoveObjects() {
		timeStill.height = showImageMovement.moveImage(timeStill, imgRect, (int) imageStopPosition);
		transperacy = showImageMovement.setTransperacy(transperacy, outgoing);

		// changes outgoing to true if the picture is supposed to move again
		// after standstill

		if (timeStill.height == 0) {
			outgoing = true;
		}

		showImageMovement.moveUserText(imageUserTxtDsp, outgoing);
		showImageMovement.moveImageText(imageCommentTxtDsp, outgoing);
		repaint();
	}

	/**
	 * Called every time the frame is drawn
	 */
	public void paint(Graphics g) {
		showImageDrawing.drawBackground(g, monitorSize, transperacy);
		showImageDrawing.drawComments(comments);
		showImageDrawing.paintImage(imageCommentTxtDsp,imageUserTxtDsp, slideImage, imgRect);
	}

	protected void setImageText(String tmpImageText) {
		imageCommentTxtDsp.setString(tmpImageText);
		imageCommentTxtDsp.resetPos();
		imageCommentTxtDsp.addX(100);
		imageCommentTxtDsp.addY(monitorSize.height + ImageUserFontSize);
	}

	protected void setUserText(String tmpUserString) {
		correction = (tmpUserString.length() + 6) / 2;
		imageUserTxtDsp.setString(tmpUserString + " posted: ");
		imageUserTxtDsp.resetPos();
		scalePositionImageAndText();
		imageUserTxtDsp.addX(textStartPosition.width);
		imageUserTxtDsp.addY(0);
	}

	protected void setImage(BufferedImage tmpImage) {
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

	protected void setComments(List<CommentXML> imageComments) {
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
	 * Scaling the image and text to fit the screen and position them right. /**
	 * Change the boolean "outgoing" to indicate that the Image is moving out,
	 * towards the end of screen.
	 */
	public void changeDirection() {
		outgoing = true;
	}

	/**
	 * Gets the image x-coordinate
	 * 
	 * @return returns a double with the x-coordinate
	 */
	public double getSlideImageX() {
		return imgRect.getX();
	}
}
