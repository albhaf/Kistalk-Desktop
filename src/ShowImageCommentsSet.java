import java.awt.Rectangle;
import java.util.List;


public class ShowImageCommentsSet {
	int y; 
	
	public ShowImageCommentsSet(){
		
	}
	
	public TextToDisplay[][] setComments(List<CommentXML> imageComments,
			TextToDisplay[][] comments, Rectangle monitorSize){
		if (imageComments.size() > 0) {
			int lines = 1;
			for (int i = 0; i < imageComments.size(); i++) {
				if (lines < imageComments.get(i).getContent().length()/30 + 1)
					lines = (imageComments.get(i).getContent().length() / 30) + 1;
			}

			comments = new TextToDisplay[imageComments.size()][lines+1];

			setCommentsText(imageComments, comments, monitorSize);
		}
		return comments;
	}
	
	private TextToDisplay[][] setComment(int i, String commentIn, TextToDisplay[][] comments, Rectangle monitorSize){
		int j = 0;
		int breakLine = (int) comments[i][0].getX()/30;
		String[] tmp;
		
		do {
			j++;
			comments[i][j] = new TextToDisplay();

			comments[i][j].resetPos();
			comments[i][j].addX(monitorSize.width
					- monitorSize.width / 3 + 5);
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
	
	private TextToDisplay[][] setCommentsText(List<CommentXML> imageComments,
			TextToDisplay[][] comments, Rectangle monitorSize){
		y = 200;
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
			
			comments = setComment(i, tmpComment, comments, monitorSize);
		}
		return comments;
	}
}