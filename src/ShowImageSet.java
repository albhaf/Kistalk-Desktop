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

	protected TextToDisplay[] setImageText(String tmpImageText,
			TextToDisplay[] imageCommentTxtDsp) {
		
		int j = 0;
		int breakLine = (monitorSize.height - 150)/12;
		String[] tmp;
		imageCommentTxtDsp = new TextToDisplay[5];
		int y = monitorSize.height + 20;

		do {
			imageCommentTxtDsp[j] = new TextToDisplay();

			imageCommentTxtDsp[j].resetPos();
			imageCommentTxtDsp[j].addX(100);
			imageCommentTxtDsp[j].addY(y);
			y = y + 40;

			if (tmpImageText.length() > breakLine) {
				String nextLine = tmpImageText.substring(0, breakLine);
				tmpImageText = tmpImageText.substring(breakLine);
				tmp = tmpImageText.split(" ", 2);
				imageCommentTxtDsp[j].setString(nextLine + tmp[0]);
				if (tmp.length > 1) {
					tmpImageText = tmp[1];
				} else {					
					break;
				}
			} else {
				imageCommentTxtDsp[j].setString(tmpImageText);
				break;
			}
			j++;
		} while (true && j < 5);
		imageCommentTxtDsp[0].setLines(j);
		return imageCommentTxtDsp;
		
	}

	protected int getTextStopPosition(){
		return (int) monitorSize.getHeight() - 150;
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
