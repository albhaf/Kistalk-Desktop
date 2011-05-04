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
	int y; 

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

	private TextToDisplay[][] setComment(int i, String commentIn, TextToDisplay[][] comments){
		int j = 0;
		int breakLine = (int) comments[i][0].getX()/30;
		String[] tmp;
		
		do {
			j++;
			comments[i][j] = new TextToDisplay();

			comments[i][j].resetPos();
			comments[i][j].addX(monitorSize.width
					- monitorSize.width / 3);
			y = y + 30;
			comments[i][j].addY(y);
			if(commentIn.length() > breakLine){
				String nextLine = commentIn.substring(0, breakLine);
				commentIn = commentIn.substring(breakLine);
				tmp = commentIn.split(" ", 2);
				comments[i][j].setString(nextLine + tmp[0]);
				if (tmp.length > 1){
					commentIn = tmp[1];
				}else{
					comments[i][0].setLines(j);
					break;
				}
			}else{
				comments[i][j].setString(commentIn);
				comments[i][0].setLines(j);
				break;
			}
		}while(true);
		return comments;
	}
	
	private TextToDisplay[][] writeComments(List<CommentXML> imageComments,
			TextToDisplay[][] comments){
		for (int i = 0; i < comments.length; i++) {
			comments[i][0] = new TextToDisplay();
			comments[i][0].setString(imageComments.get(i).getUser()
					+ " wrote:");
			comments[i][0].resetPos();
			comments[i][0].addX(monitorSize.width - monitorSize.width
					/ 3);
			y = y + 40;
			comments[i][0].addY(y);

			String tmpComment = imageComments.get(i).getContent();
			
			comments = setComment(i, tmpComment, comments);
		}
		return comments;
	}
	
	protected TextToDisplay[][] setComments(List<CommentXML> imageComments,
			TextToDisplay[][] comments) {
		if (imageComments != null) {
			y = 200;
			if (imageComments.size() > 0) {
				int lines = 1;
				for (int i = 0; i < imageComments.size(); i++) {
					if (lines < imageComments.get(i).getContent().length()/30 + 1)
						lines = (imageComments.get(i).getContent().length() / 30) + 1;
				}

				comments = new TextToDisplay[imageComments.size()][lines+1];
				writeComments(imageComments, comments);
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
