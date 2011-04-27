import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ImageXML {

	private URL link;
	private String date;
	private List<CommentXML> comments;
	private String user;
	private String imageText;
	
	public ImageXML(){
		comments = new ArrayList<CommentXML>();
	}
	
	public ImageXML(URL link, String date, String age) {
		this.link = link;
		this.date  = date;
	}


	public String getDate() {
		return date;
	}
	
	public String getUser(){
		return user;
	}

	public URL getLink() {
		return link;
	}
	
	public List<CommentXML> getComments(){
		return comments;
	}
	
	public String getImageText(){
		return imageText;
	}
	
	
	public void setDate(String tmp) {
		this.date = tmp;
	}
	
	public void setUser(String tmp){
		this.user = tmp;
	}
	public void setLink(URL tmp) {
		this.link = tmp;
	}
	
	public void setComments(List<CommentXML> tmp){
		this.comments = tmp;
	}
	
	public void setImageText(String tmp) {
		this.imageText = tmp;
	}
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Image Details - ");
		sb.append("Source: " + getLink());
		sb.append(", ");
		sb.append("Created: " + getDate());
		sb.append(", ");
		
		Iterator<CommentXML> it = comments.iterator();
		while(it.hasNext()) {
			sb.append(it.next().toString());
			sb.append("   ");
		}
		

		sb.append("Number of comments:");
		sb.append(comments.size());
		return sb.toString();
	}
}
