import java.awt.Rectangle;
import java.util.List;

public class ShowImageCommentsSet {
	int y, nrOfComments;

	public ShowImageCommentsSet(int tmp) {
		nrOfComments = tmp;
	}

	public TextToDisplay[][] setComments(List<CommentXML> imageComments,
			TextToDisplay[][] comments, Rectangle monitorSize) {
		if (imageComments.size() > 0) {
			int lines = 1;
			for (int i = 0; i < imageComments.size(); i++) {
				if (lines < imageComments.get(i).getContent().length() / 30 + 1)
					lines = (imageComments.get(i).getContent().length() / 30) + 1;
			}

			comments = new TextToDisplay[imageComments.size()][lines + 1];

			setCommentsText(imageComments, comments, monitorSize);
		}
		return comments;
	}

	private TextToDisplay[][] setComment(int i, String commentIn,
			TextToDisplay[][] comments, Rectangle monitorSize) {
		int j = 0;
		int breakLine = (int) comments[i][0].getX() / 30;
		String[] tmp;

		do {
			j++;
			comments[i][j] = new TextToDisplay();

			comments[i][j].resetPos();
			comments[i][j].addX(monitorSize.width - monitorSize.width / 3 + 5);
			y = y + 30;
			comments[i][j].addY(y);

			if (commentIn.length() > breakLine) {
				String nextLine = commentIn.substring(0, breakLine);
				commentIn = commentIn.substring(breakLine);
				tmp = commentIn.split(" ", 2);
				comments[i][j].setString(nextLine + tmp[0]);
				if (tmp.length > 1) {
					commentIn = tmp[1];
				} else {
					comments[i][0].setLines(j);
					break;
				}
			} else {
				comments[i][j].setString(commentIn);
				comments[i][0].setLines(j);
				break;
			}
		} while (true && comments[i][j].getY() < monitorSize.getHeight()
				- monitorSize.getHeight() / 7);
		return comments;
	}

	private TextToDisplay[][] setCommentsText(List<CommentXML> imageComments,
			TextToDisplay[][] comments, Rectangle monitorSize) {
		y = 100;
		int c;
		if (imageComments.size() <= nrOfComments){
			c = 0;
		}else{
			c = imageComments.size()-nrOfComments;
		}
		int i = 0;
		do{
			comments[i][0] = new TextToDisplay();
			comments[i][0]
					.setString(imageComments.get(c).getUser() + " wrote:");
			comments[i][0].resetPos();
			comments[i][0].addX(monitorSize.width - monitorSize.width / 3);
			y = y + 40;
			comments[i][0].addY(y);

			String tmpComment = imageComments.get(c).getContent();

			comments = setComment(i, tmpComment, comments, monitorSize);
			i++;
			c++;
		}while(c < imageComments.size() && 
				y < monitorSize.getHeight()/2);
		return comments;
	}
}
