import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


	//	Listener
	public class ButtonListener extends AdminFrame implements ActionListener {
		AdminFrame adminFrame = new AdminFrame();
		public void actionPerformed(ActionEvent e) {
			LogInFrame login = new LogInFrame();
			ButtonSettings buttons = new ButtonSettings();
			ConfigSettings config = new ConfigSettings();
			PopupFrame pop = new PopupFrame(null);
			StartnStop slideShow = new StartnStop();
			Radiobtns rbtn = new Radiobtns();
			
			if (e.getSource() == saveSetBtn){ //Save settings
				config.setValues(confValues);
				statusLbl.setText("Status: Settings saved to Config");
			}else if (e.getSource() == resetBtn){ //Reset config
				config.resetValues();
				statusLbl.setText("Status: Config is back to normal");
			}else if (e.getSource() == startBtn){ //Start slideshow
				slideShow.start();
				statusLbl.setText("Status: Starting Slideshow...");
			}else if(e.getSource() == yFoodRbtn){ //Dinner served
				new PopupFrame("What is teh food?");
				statusLbl.setText("Dinner is served!");
			}else if(e.getSource() == nFoodRbtn){ //No dinner
				rbtn.nFood();
				statusLbl.setText("Food is no more");
			}else if(e.getSource() == yPubRbtn){ //Pub open
				rbtn.yPub();
				statusLbl.setText("The pub is open!");
			}else if(e.getSource() == nPubRbtn){ //Pub closed
				rbtn.nPub();
				statusLbl.setText("The pub is closed");
			}else if(e.getSource() == exitBtn){ //Exit
				slideShow.exit();
			}else if(e.getSource() == savePathBtn){
				new PopupFrame("Name your Slideshow: ");
			}else if(e.getSource() == remPathBtn){
				
			}else if(e.getSource() == pop.sbmBtn){ //Popup
				if (pop.label.getText() == "What is teh food?"){
					String food = popTxt.getText();
					rbtn.yFood(food);
				}else{
					confValues[9] = confValues[9] + "¤" + popTxt.getText();
					confValues[10] = confValues[10] + "¤" + xmlPubPathTxt.getText();
					pubSlidesDDLst.addItem(xmlPubPathTxt.getText());
					config.setValues(confValues);
				}
				popFrame.dispose();
				buttons.enable();
			}else if(e.getSource() == pop.clsBtn){
				popFrame.dispose();
				buttons.enable();
				nFoodRbtn.setSelected(true);
			}else if(e.getSource() == login.sbmBtn){
				logFrame.dispose();
				config.getValues();
				setupFrame();
			}else if(e.getSource() == login.clsBtn){
				logFrame.dispose();
			}else if(e.getSource() == null){
				
			}

		}
	}