


public class CommentXML {

	private String content;
	private String created;
	private String user;
	
	public void setContent(String temp){
		content = temp;
	}
	
	public void setCreated(String temp){
		created = temp;
	}
	
	public void setUser(String temp){
		user = temp;
	}
	
	public String getContent(){
		return content;
	}
	
	public String getCreated(){
		return created;
	}
	
	public String getUser(){
		return user;
	}
		
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(user);
		sb.append(" wrote: ");
		sb.append(content);
		sb.append(", at  ");
		sb.append(created);
		return sb.toString();
	}
	
	
}
