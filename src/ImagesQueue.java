import java.net.URL;
import java.util.LinkedList;


public class ImagesQueue /*extends LinkedList<ImageXML> */{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736384620617622538L;
	
	LinkedList<ImageXML> images;
	int maxSize;
	
	public ImagesQueue(){
		images= new LinkedList<ImageXML>();
		maxSize =0;
	}
		
	public int Size(){
		return images.size();
	}
	
	public int getMaxSize(){
		return maxSize;
	}
	
	public void setMaxSize(int tmpSize){
		maxSize=tmpSize;
	}
	
	public boolean push(ImageXML tmp){
		if(images.size()<maxSize){
			images.push(tmp);
			return true;
		}else{
			return false;
		}
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
		
	public ImageXML pop(){
		
		return images.pop();
	}
	


}
