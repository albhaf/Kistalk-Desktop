import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ShowImage extends JPanel {
	private static final long serialVersionUID = 887346353483336091L;

	// Variables
	private boolean pubSlide;
	/**
	 * The Rectangle containing the image
	 */
	protected ImgRect imgRect; //
	protected ImgRect logoRect;
	protected TextToDisplay imageUserTxtDsp;
	protected TextToDisplay[] imageCommentTxtDsp;
	protected TextToDisplay[][] comments;
	protected boolean outgoing;
	private BufferedImage slideImage;
	private BufferedImage logoImage;
	private float transperacy, fadingSpeed;
	private int textStopPosition;

	private ShowImageSet showImageSet;
	private ShowImageMovement showImageMovement;
	private ShowImageDrawing showImageDrawing;
	
	// Variables which are set in constructor
	private Rectangle monitorSize;

	private Dimension timeStill = new Dimension();
	private float imageStopPosition;
	
	public ShowImage(Rectangle monitorIn, int timeStillIn, int speedIn) throws IOException {
		logoRect = new ImgRect();
		File tmpFile = new File("logo48pix.png");
		try {
			logoImage = ImageIO.read(tmpFile);
		} catch (IOException e) {
		}
		logoRect.width=156;
		logoRect.height=50;
		logoRect.setX(10);
		logoRect.setY(10);
		monitorSize = monitorIn;
		imgRect = new ImgRect();

		outgoing = false;
		transperacy = 0;
		fadingSpeed = (float) (speedIn*0.005);

		imageUserTxtDsp = new TextToDisplay();

		timeStill.width = timeStillIn;
		timeStill.height = timeStill.width;
		showImageSet = new ShowImageSet(monitorSize);
		showImageMovement = new ShowImageMovement();
		showImageDrawing = new ShowImageDrawing();
	}
	
	public void setNewSlide(BufferedImage image){
		resetImage(image);
		pubSlide = true;
		imageStopPosition = showImageSet.setPicture(slideImage, imgRect, imageStopPosition, pubSlide);
	}
	
	public void setNewPicture(BufferedImage image, ImageXML tmpXML) {
		resetImage(image);
		pubSlide = false;		
		// Kommentarer
		comments = showImageSet.setComments(tmpXML.getComments(), comments);
		// Bildtexten
		imageCommentTxtDsp = showImageSet.setImageText(tmpXML.getImageText(), imageCommentTxtDsp);	
		textStopPosition = showImageSet.getTextStopPosition();
		// Image user
		showImageSet.setUserText(tmpXML.getUser(), imageUserTxtDsp);
		// Bilden
		imageStopPosition = showImageSet.setPicture(slideImage, imgRect, imageStopPosition, pubSlide);
	}
	
	private void resetImage(BufferedImage image){
		transperacy = 0;
		outgoing = false;
		timeStill.height = timeStill.width;
		comments = null;
		slideImage = image;
	}

	private void moveImageObjects() {
		timeStill.height = showImageMovement.moveImage(timeStill, imgRect, (int) imageStopPosition);
		transperacy = showImageMovement.setTransperacy(transperacy, outgoing, fadingSpeed);

		showImageMovement.moveUserText(imageUserTxtDsp, outgoing);
		showImageMovement.moveImageText(imageCommentTxtDsp, outgoing,textStopPosition);
	}
	
	private void movePubSlide(){
		if(transperacy >= 0.98)
			timeStill.height = showImageMovement.moveSlide(timeStill, imgRect, monitorSize.width, slideImage.getWidth());
		transperacy = showImageMovement.setTransperacy(transperacy, outgoing, fadingSpeed*3);
	}	
	
	public void MoveObjects() {
		if(pubSlide){
			movePubSlide();
		}else{
			moveImageObjects();
		}
		if (timeStill.height == 0) {
			outgoing = true;
		}
		if(transperacy <= 0.001){
			imgRect.addX((int) monitorSize.getWidth());
		}

		repaint();
	}

	/**
	 * Called every time the frame is drawn
	 */
	public void paint(Graphics g) {
		showImageDrawing.drawBackground(g, monitorSize, transperacy);
		if(pubSlide){
			showImageDrawing.paintSlide(slideImage, imgRect,logoImage,logoRect);
		}else{
			showImageDrawing.drawComments(comments);
			showImageDrawing.paintImage(imageCommentTxtDsp,imageUserTxtDsp, slideImage, imgRect,logoImage, logoRect);
		}
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
