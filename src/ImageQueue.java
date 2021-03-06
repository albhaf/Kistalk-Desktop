import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ImageQueue{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736384620617622538L;

	LinkedList<BufferedImage> images;
	int maxSize;

	public ImageQueue(int tmpSize) {
		images = new LinkedList<BufferedImage>();
		maxSize = tmpSize;
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

	/**
	 * returns first element and places it last in the queue.
	 * @return
	 */
	public BufferedImage getFirst() {
		BufferedImage image = images.peekFirst();
		images.add(images.pop());

		return image;
	}

	public boolean addFirst(BufferedImage tmp) {
		if (images.size() < maxSize) {
			images.push(tmp);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addLast(BufferedImage tmp){
		if(images.size() < maxSize){
			images.addLast(tmp);
			return true;
		}else {
			return false;
		}
	}

	public BufferedImage peek() {
		return images.peekFirst();
	}

	public boolean contains(BufferedImage tmpimg) {
		return images.contains(tmpimg);
	}
	
	public boolean push(BufferedImage tmp){
		if(images.size() < maxSize){
			images.push((tmp));
			return true;
		}else {
			return false;
		}
	}

	public BufferedImage pop() {
		return images.pop();
	}

}
