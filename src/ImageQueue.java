import java.net.URL;
import java.util.LinkedList;

public class ImageQueue{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736384620617622538L;

	LinkedList<ImageXML> images;
	int maxSize;

	public ImageQueue() {
		images = new LinkedList<ImageXML>();
		maxSize = 10;
	}

	public int Size() {
		return images.size();
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int tmpSize) {
		maxSize = tmpSize;
	}
	
	public void removeLast(){
		images.removeLast();
	}
	
	public void removeFirst(){
		images.removeFirst();
	}

	public void removeElement(int tmp) {
		images.remove(tmp);
	}

	public ImageXML getFirst() {
		ImageXML image = images.peekFirst();
		images.add(images.pop());

		return image;
	}

	public boolean addFirst(ImageXML tmp) {
		if (images.size() < maxSize) {
			images.push(tmp);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addLast(ImageXML tmp){
		if(images.size() < maxSize){
			images.addLast(tmp);
			return true;
		}else {
			return false;
		}
	}

	public ImageXML peek() {
		return images.peekFirst();
	}

	public boolean contains(ImageXML tmpimg) {
		return images.contains(tmpimg);
	}

	public boolean contains(URL tmpurl) {
		for (int i = 0; i < images.size(); i++) {
			if (images.get(i).getLink().equals(tmpurl) == true) {
				return true;
			}
		}
		return false;
	}

	public ImageXML pop() {
		return images.pop();
	}

}
