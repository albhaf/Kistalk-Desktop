import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;


public class ShowImageSet {
	
	private BufferedImage image;
	private final int ImageUserFontSize = 50;
	private int correction;
	private Rectangle monitorSize;
	private Dimension textStartPosition = new Dimension();
	private Dimension imageSize = new Dimension();
	
	public ShowImageSet(Rectangle size){
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		monitorSize = size;
	}
	
	protected void setImageText(String tmpImageText, TextToDisplay imageCommentTxtDsp) {
		imageCommentTxtDsp.setString(tmpImageText);
		imageCommentTxtDsp.resetPos();
		imageCommentTxtDsp.addX(100);
		imageCommentTxtDsp.addY(monitorSize.height + ImageUserFontSize);
	}

	protected void setUserText(String tmpUserString, TextToDisplay imageUserTxtDsp) {
		correction = (tmpUserString.length() + 6) / 2;
		imageUserTxtDsp.setString(tmpUserString + " posted: ");
		imageUserTxtDsp.resetPos();
		scalePositionImageAndText(imageUserTxtDsp);
		imageUserTxtDsp.addX(textStartPosition.width);
		imageUserTxtDsp.addY(0);
	}

	protected float setImage(BufferedImage tmpImage, ImgRect imgRect, float imageStopPosition) {
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
		return imageStopPosition;
	}

	protected TextToDisplay[] setComments(List<CommentXML> imageComments, TextToDisplay[] comments) {
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
		return comments;
	}
	
	public void scalePositionImageAndText(TextToDisplay imageUserTxtDsp) {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.005);
		imageSize.width = (int) (imageSize.height * ration);
		correction = (imageUserTxtDsp.length() + 6) / 2;
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 3));
	}

}
