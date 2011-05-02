import java.net.URL;
import java.util.LinkedList;


public class ImagesQueue extends LinkedList<ImageXML> {
	LinkedList<ImageXML> images;
	
	public ImagesQueue(){
		images=null;
	}
	
	public void push(ImageXML tmp){
		images.push(tmp);
	}
	
	public ImageXML peek(){
		return images.peekFirst();
	}
	
	
	public boolean contains(ImageXML tmpimg){
		return images.contains(tmpimg);
	}
	
	public boolean contains(URL tmpurl){
		for(int i =0; i< images.size();i++){
			if(images.get(i).getLink().equals(tmpurl)==true){
					return true;
			}
		}
		return false;
	}
	
	public int getSize(){
		return images.size();
	}
	
	
	public ImageXML pop(){
		
		return images.pop();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736384620617622538L;

	public void que() {
		
	}

}
