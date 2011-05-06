import java.awt.Dimension;

public class ShowImageMovement {
	public ShowImageMovement() {
	}

	protected void moveImageText(TextToDisplay[] imageCommentTxtDsp,
			boolean outgoing, int textStopPosition) {
		int lines = imageCommentTxtDsp[0].getLines(); 
		for(int i = 0; i < lines; i++){
			if (imageCommentTxtDsp[0].getY() > textStopPosition && outgoing == false) {
				imageCommentTxtDsp[i].addY(-2);
			} else if (outgoing == true) {
				imageCommentTxtDsp[i].addY(2);
			}
		}
	}

	protected void moveUserText(TextToDisplay imageUserTxtDsp, boolean outgoing) {
		if (outgoing == true) {
			imageUserTxtDsp.addY(-1);
		} else if (outgoing == false && imageUserTxtDsp.getY() < 80) {
			imageUserTxtDsp.addY(1);
		}
	}

	protected float setTransperacy(float transperacy, boolean outgoing, double i) {
		if (outgoing == true) {
			if (transperacy > 0) {
				transperacy = (float) (transperacy - i);
			}
		} else {
			if (transperacy < 1) {
				transperacy = (float) (transperacy + i);
			}
		}
		if (transperacy > 1) {
			return 1;
		} else if (transperacy < 0) {
			return 0;
		}

		return transperacy;
	}

	protected int moveImage(Dimension timeStill, ImgRect imgRect,
			int imageStopPosition) {
		if (timeStill.height != 0 && imgRect.getX() == imageStopPosition) {

			return timeStill.height - 1;
		} else {
			imgRect.addX(5);
			return timeStill.height;
		}
	}

	protected int moveSlide(Dimension timeStill, ImgRect imgRect,
			int monitorWidth, int imageWidth) {
		imgRect.setX((monitorWidth - imageWidth) / 4);
		if (timeStill.height != 0) {
			return timeStill.height - 1;
		} else {
			return timeStill.height;
		}
	}
}
