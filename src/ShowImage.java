import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

public class ShowImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 887346353483336091L;

	// Variables
	private boolean pubSlide;
	/**
	 * The Rectangle containing the image
	 */
	protected ImgRect imgRect; //
	protected TextToDisplay imageUserTxtDsp;
	protected TextToDisplay imageCommentTxtDsp;
	protected TextToDisplay[][] comments;
	protected boolean outgoing;
	private BufferedImage slideImage;
	private float transperacy;
	private int fadingSpeed;

	private ShowImageSet showImageSet;
	private ShowImageMovement showImageMovement;
	private ShowImageDrawing showImageDrawing;
	

	// Variables which are set in constructor
	private Rectangle monitorSize;

	private Dimension timeStill = new Dimension();
	private float imageStopPosition;
	
	public ShowImage(Rectangle monitorIn, int timeStillIn, int speedIn) throws IOException {
		monitorSize = monitorIn;
		imgRect = new ImgRect();
		outgoing = false;
		transperacy = 0;
		fadingSpeed = speedIn;

		imageCommentTxtDsp = new TextToDisplay();
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
	
	public void setNewPicture(BufferedImage image, ImageXML tmpXML/*BufferedImage image, String user, String imageText, List<CommentXML> commentsList*/) {
		resetImage(image);
		pubSlide = false;		
		// Kommentarer
		comments = showImageSet.setComments(/*commentsList*/tmpXML.getComments(), comments);
		// Bildtexten
		showImageSet.setImageText(tmpXML.getImageText(), imageCommentTxtDsp);				
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
		transperacy = showImageMovement.setTransperacy(transperacy, outgoing, fadingSpeed*0.01);

		showImageMovement.moveUserText(imageUserTxtDsp, outgoing);
		showImageMovement.moveImageText(imageCommentTxtDsp, outgoing);
	}
	
	private void movePubSlide(){
		if(transperacy >= 0.98)
			timeStill.height = showImageMovement.moveSlide(timeStill, imgRect, monitorSize.width, slideImage.getWidth());
		transperacy = showImageMovement.setTransperacy(transperacy, outgoing, fadingSpeed * 0.03);
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
			showImageDrawing.paintSlide(slideImage, imgRect);
		}else{
			showImageDrawing.drawComments(comments);
			showImageDrawing.paintImage(imageCommentTxtDsp,imageUserTxtDsp, slideImage, imgRect);
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
