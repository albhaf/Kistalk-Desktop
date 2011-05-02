import java.awt.Font;


public class PubSlides extends AdminFrame {
	//	Set the path
	public void setPath(String name){
		if (name == "TMEIT"){
			xmlPubPathTxt.setText("C:\\TMEIT");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			xmlPubPathTxt.disable();
			statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "Qmisk"){
			xmlPubPathTxt.setText("C:\\Qmisk");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			xmlPubPathTxt.disable();
			statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "ITK"){
			xmlPubPathTxt.setText("C:\\ITK");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			xmlPubPathTxt.disable();
			statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "[Other Slideshow]"){
			xmlPubPathTxt.setText("C:\\...");
			xmlPubPathTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
			xmlPubPathTxt.enable();
			statusLbl.setText("Choose a Slideshow");
		}
		
	}
}
