import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
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
	protected TextToDisplay[] comments;
	protected boolean outgoing = false;
	private BufferedImage slideImage;
	private float transperacy = 0;

	private ShowImageSet showImageSet;
	private ShowImageMovement showImageMovement;
	private ShowImageDrawing showImageDrawing;
	

	// Variables which are set in constructor
	private Rectangle monitorSize;

	private Dimension timeStill = new Dimension();
	private float imageStopPosition;
	
	public ShowImage(Rectangle tmpmonitor, int tmpTimeStill) {
		monitorSize = tmpmonitor;
		imgRect = new ImgRect();

		imageCommentTxtDsp = new TextToDisplay();
		imageUserTxtDsp = new TextToDisplay();

		timeStill.width = tmpTimeStill;
		timeStill.height = timeStill.width;
		showImageSet = new ShowImageSet(monitorSize);
		showImageMovement = new ShowImageMovement();
		showImageDrawing = new ShowImageDrawing();
	}

	public void setNewSlide(BufferedImage image){
		resetImage(image);
		
		pubSlide = true;
		imageStopPosition = showImageSet.setImage(slideImage, imgRect, imageStopPosition);
	}
	
	private void resetImage(BufferedImage image){
		transperacy = 0;
		outgoing = false;
		timeStill.height = timeStill.width;
		comments = null;
		slideImage = image;
	}
	
	public void setNewPicture(BufferedImage image, String user, String imageText, List<CommentXML> commentsList) {
	
		resetImage(image);
		pubSlide = false;
		
		// Kommentarer
		comments = showImageSet.setComments(commentsList, comments);
		// Bildtexten
		showImageSet.setImageText(imageText, imageCommentTxtDsp);				
		// Image user
		showImageSet.setUserText(user, imageUserTxtDsp);
		// Bilden
		imageStopPosition = showImageSet.setSlide(slideImage, imgRect, monitorSize, imageStopPosition);
	}

	private void moveImageObjects() {
		timeStill.height = showImageMovement.moveImage(timeStill, imgRect, (int) imageStopPosition);
		transperacy = showImageMovement.setTransperacy(transperacy, outgoing);

		showImageMovement.moveUserText(imageUserTxtDsp, outgoing);
		showImageMovement.moveImageText(imageCommentTxtDsp, outgoing);
	}
	
	private void movePubSlide(){
		timeStill.height = showImageMovement.moveSlide(timeStill, imgRect, monitorSize.width, slideImage.getWidth());
		transperacy = showImageMovement.setTransperacy(transperacy, outgoing);
	}
	
	
	public void MoveObjects() {
		if(pubSlide){
			movePubSlide();
		}else{
			moveImageObjects();
		}
		if (transperacy >= 0.98 && timeStill.height == 0) {
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
