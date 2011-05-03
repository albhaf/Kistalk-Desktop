import java.awt.Dimension;


public class ShowImageMovement {
	public ShowImageMovement(){	
	}
	
	protected void moveImageText(TextToDisplay imageCommentTxtDsp, boolean outgoing) {
		if (imageCommentTxtDsp.getY() > 750 && outgoing == false) {
			imageCommentTxtDsp.addY(-2);
		} else if (outgoing == true) {
			imageCommentTxtDsp.addY(2);
		}
	}
	
	protected void moveUserText(TextToDisplay imageUserTxtDsp, boolean outgoing) {
		if (outgoing == true) {
			imageUserTxtDsp.addY(-1);
		} else if (outgoing == false && imageUserTxtDsp.getY() < 80) {
			imageUserTxtDsp.addY(1);
		}
	}
	
	protected float setTransperacy(float transperacy, boolean outgoing) {
		if (outgoing) {
			if (transperacy > 0.01) {
				return (float) (transperacy - 0.01);
			}
			else{
				return (float) 0.001;
			}			
		} else {
			if (transperacy < 0.99) {
				return (float) (transperacy + 0.01);
			}
			else {
				return (float) 0.99;
			}
		}
	}

	protected int moveImage(Dimension timeStill, ImgRect imgRect, int imageStopPosition) {
		if (timeStill.height != 0 && imgRect.getX() == imageStopPosition) {

			return timeStill.height - 1;
		} else {
			imgRect.addX(5);
			return timeStill.height;
		}
	}
	
	protected int moveSlide(Dimension timeStill, ImgRect imgRect, int monitorWidth, int imageWidth){
		imgRect.setX((monitorWidth-imageWidth)/4);
		if (timeStill.height != 0) {
			return timeStill.height - 1;
		} else {
			return timeStill.height;
		}
	}
}
