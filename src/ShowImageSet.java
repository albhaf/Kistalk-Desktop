import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

public class ShowImageSet {


	private final int ImageUserFontSize = 50;
	private int correction;
	private Rectangle monitorSize;
	private Dimension textStartPosition = new Dimension();

	private ShowImageCommentsSet commentsSet;
	private ShowImagePicSet picSet;


	protected ShowImageSet(Rectangle size) {
		monitorSize = size;
		commentsSet = new ShowImageCommentsSet();
		picSet = new ShowImagePicSet(monitorSize);
	}

	protected float setPicture(BufferedImage tmpImage, ImgRect imgRect,
			float imageStopPosition, boolean pubSlide){
		if (pubSlide)
			imageStopPosition = picSet.setSlide(tmpImage, imgRect, imageStopPosition);
		else
			imageStopPosition = picSet.setImage(tmpImage, imgRect, imageStopPosition);
		
		return imageStopPosition;
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
	
	protected TextToDisplay[][] setComments(List<CommentXML> imageComments,
			TextToDisplay[][] comments) {
		if (imageComments != null) {
			comments = commentsSet.setComments(imageComments,comments, monitorSize);
		}
		return comments;
	}

	private void scalePositionText(TextToDisplay imageUserTxtDsp) {
		correction = (imageUserTxtDsp.length() + 6) / 2;
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 3));
	}

}
