import java.awt.Font;


public class PubSlides {
	//	Set the path
	public void setPath(String name, AdminFrame adminframe){
		if (name == "TMEIT"){
			adminframe.xmlPubPathTxt.setText("C:\\TMEIT");
			adminframe.xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			adminframe.xmlPubPathTxt.disable();
			adminframe.statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "Qmisk"){
			adminframe.xmlPubPathTxt.setText("C:\\Qmisk");
			adminframe.xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			adminframe.xmlPubPathTxt.disable();
			adminframe.statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "ITK"){
			adminframe.xmlPubPathTxt.setText("C:\\ITK");
			adminframe.xmlPubPathTxt.setFont(new Font("Algerian", Font.ITALIC, 12));
			adminframe.xmlPubPathTxt.disable();
			adminframe.statusLbl.setText(name + "s Slideshow is choosed");
		}else if (name == "[Other Slideshow]"){
			adminframe.xmlPubPathTxt.setText("C:\\...");
			adminframe.xmlPubPathTxt.setFont(new Font("Algerian", Font.PLAIN, 12));
			adminframe.xmlPubPathTxt.enable();
			adminframe.statusLbl.setText("Choose a Slideshow");
		}
		
	}
}
