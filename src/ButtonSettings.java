
public class ButtonSettings extends AdminFrame {
	
	//	Disable buttons
	public void disable() {
		saveSetBtn.setEnabled(false); //BUGG! xmlPubPathTxt enablas, även om den inte ska vara enbled
		resetBtn.setEnabled(false);
		startBtn.setEnabled(false);
		exitBtn.setEnabled(false);
		yFoodRbtn.setEnabled(false);
		nFoodRbtn.setEnabled(false);
		yPubRbtn.setEnabled(false);
		nPubRbtn.setEnabled(false);
		screenDDLst.setEnabled(false);
		pubSlidesDDLst.setEnabled(false);
		nrOfImgsTxt.setEnabled(false);
		timeTxt.setEnabled(false);
		nrOfCommentsTxt.setEnabled(false);
		xmlPubPathTxt.setEnabled(false);
		
	}
	
	//	Enable buttons
	public void enable(){
		saveSetBtn.setEnabled(true);
		resetBtn.setEnabled(true);
		startBtn.setEnabled(true);
		exitBtn.setEnabled(true);
		yFoodRbtn.setEnabled(true);
		nFoodRbtn.setEnabled(true);
		yPubRbtn.setEnabled(true);
		nPubRbtn.setEnabled(true);
		screenDDLst.setEnabled(true);
		pubSlidesDDLst.setEnabled(true);
		nrOfImgsTxt.setEnabled(true);
		timeTxt.setEnabled(true);
		nrOfCommentsTxt.setEnabled(true);
		xmlPubPathTxt.setEnabled(true);
		
	}
}