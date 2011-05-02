import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ImageXML {

	private URL link;
	private List<CommentXML> comments;
	private String user;
	private String imageText;
	private String date;
	
	public ImageXML(){
		this.comments = new ArrayList<CommentXML>();
	}
	
	public String getUser(){
		return this.user;
	}

	public URL getLink() {
		return this.link;
	}
	
	public List<CommentXML> getComments(){
		return this.comments;
	}
	
	public String getImageText(){
		return this.imageText;
	}
	
	public String getDate(){
		return this.date;
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
	
	public void setDate(String tmp){
		this.date = tmp;
	}
}
