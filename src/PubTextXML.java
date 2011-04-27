import java.util.ArrayList;
import java.util.List;


public class PubTextXML {
	private float xPos;
	private float yPos;
	private String font;
	private List<String> text;
	
	public PubTextXML(){
		text = (new ArrayList<String>());
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public void setNextText(String tmp){
		text.add(tmp);
	}
	
	public void setIndexTest(String tmp, int index){
		text.add(index, tmp);
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public void setFont(String font) {
		this.font = font;
	}


	public float getxPos() {
		return xPos;
	}

	
	public String getFont() {
		return font;
	}
	
	public List<String> getText() {
		return text;
	}
	public float getyPos() {
		return yPos;
	}

	
}
