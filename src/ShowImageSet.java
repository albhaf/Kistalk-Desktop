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

	public ShowImageSet(Rectangle size) {
		image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		monitorSize = size;
	}

	protected void setImageText(String tmpImageText,
			TextToDisplay imageCommentTxtDsp) {
		imageCommentTxtDsp.setString(tmpImageText);
		imageCommentTxtDsp.resetPos();
		imageCommentTxtDsp.addX(100);
		imageCommentTxtDsp.addY(monitorSize.height + ImageUserFontSize);
	}

	protected void setUserText(String tmpUserString,
			TextToDisplay imageUserTxtDsp) {
		correction = (tmpUserString.length() + 6) / 2;
		imageUserTxtDsp.setString(tmpUserString + " posted: ");
		imageUserTxtDsp.resetPos();
		scalePositionText(imageUserTxtDsp);
		imageUserTxtDsp.addX(textStartPosition.width);
		imageUserTxtDsp.addY(0);
	}

	protected float setImage(BufferedImage tmpImage, ImgRect imgRect,
			float imageStopPosition) {
		imgRect.resetPos();

		float factor = (float) (tmpImage.getWidth())
				/ (float) (tmpImage.getHeight());
		imgRect.height = imageSize.height;
		imgRect.width = imgRect.height * factor;
		imgRect.setX(((int) monitorSize.getWidth() - tmpImage.getWidth()) / 4);
		imageStopPosition = ((monitorSize.width - imgRect.width
				- (monitorSize.width / 3) - 30));
		imageStopPosition = imageStopPosition - (imageStopPosition % 5);
		scalePositionPicture();
		return imageStopPosition;
	}

	protected float setSlide(BufferedImage tmpImage, ImgRect imgRect,
			Rectangle monitorHeight, float imageStopPosition) {
		imgRect.resetPos();
		if (tmpImage.getWidth() / monitorSize.getWidth() < tmpImage.getHeight()
				/ monitorSize.getHeight()) {
			float factor = (float) (tmpImage.getWidth())
					/ (float) (tmpImage.getHeight());
			imgRect.height = imageSize.height;
			imgRect.width = imgRect.height * factor;
		} else {
			float factor = (float) (tmpImage.getHeight())
					/ (float) (tmpImage.getWidth());
			imgRect.width = imageSize.width;
			imgRect.height = imgRect.width * factor;
		}
		imgRect.addY(100);
		imgRect.addX(-200);
		imageStopPosition = ((monitorSize.width - imgRect.width
				- (monitorSize.width / 3) - 30));
		imageStopPosition = imageStopPosition - (imageStopPosition % 5);
		scalePositionSlide();
		return imageStopPosition;
	}

	protected TextToDisplay[][] setComments(List<CommentXML> imageComments,
			TextToDisplay[][] comments) {
		if (imageComments != null) {
			if (imageComments.size() > 0) {
				int lines = 1;
				for (int i = 0; i < imageComments.size(); i++) {
					if (lines < imageComments.get(i).getContent().length()/30 + 1)
						lines = (imageComments.get(i).getContent().length() / 30) + 1;
				}

				comments = new TextToDisplay[imageComments.size()][lines+1];
				for (int i = 0; i < comments.length; i++) {
					comments[i][0] = new TextToDisplay();
					comments[i][0].setString(imageComments.get(i).getUser()
							+ " wrote:");
					comments[i][0].resetPos();
					comments[i][0].addX(monitorSize.width - monitorSize.width
							/ 3);
					comments[i][0].addY(200 + (i * 100));

					int j = 0;
					do {
						j++;
						comments[i][j] = new TextToDisplay();

						comments[i][j].resetPos();
						comments[i][j].addX(monitorSize.width
								- monitorSize.width / 3);
						comments[i][j].addY(200 + (i * 100 + j*30));
						if(imageComments.get(i).getContent().length() - (j-1)*30 >29){
							comments[i][j].setString(imageComments.get(i)
									.getContent().substring((j-1)*30, j*30));
						}else{
							comments[i][j].setString(imageComments.get(i)
									.getContent().substring((j-1)*30, imageComments.get(i).getContent().length()));
							break;
						}
					}while(true);
				}

			}
		}
		return comments;
	}

	public void scalePositionText(TextToDisplay imageUserTxtDsp) {
		correction = (imageUserTxtDsp.length() + 6) / 2;
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 3));
	}

	public void scalePositionPicture() {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.006);
		imageSize.width = (int) (imageSize.height * ration);
	}

	public void scalePositionSlide() {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.01);
		imageSize.width = (int) (imageSize.height * ration);
	}
}
