import java.util.ArrayList;
import java.util.List;


public class PubTextXML {
	private float xPos;
	private float yPos;
	private String fontSize;
	private String font;
	private List<String> text;
	private String alignment;
	
	public PubTextXML(){
		alignment = "center";
		font = "false";
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

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontSize() {
		return fontSize;
	}

	public String toString(){
		String tmp;
		StringBuffer tmpsb = new StringBuffer();
		tmpsb.append("Xpos: " + this.getxPos());
		tmpsb.append("Ypos: " + this.getyPos());
		tmpsb.append("Font: " + this.getFont());
		tmpsb.append("FontSize: " + this.getFontSize());
		tmpsb.append("text: "+ this.getText());
		
		tmp=tmpsb.toString();
		return tmp;
		
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}

	public String getAlignment() {
		return alignment;
	}
	
}
