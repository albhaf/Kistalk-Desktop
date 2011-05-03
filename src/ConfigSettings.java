import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;


public class ConfigSettings {
	final int nrOfConfigValues = 11; // Doesn't include regular textlines. If change, then change configHandler too
	String[] confValues = new String[nrOfConfigValues];
	ConfigHandler handler;
	
	//	Reads Config and saves values in 'confValues'
	public String[] getValues() {
		handler = new ConfigHandler();
		
		try {
			confValues = handler.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return confValues;
		
	}

	//	Saves current settings to Config (except Default Turtle, he only lives in config when config is defaultahrized)
	public void setValues(String[] confValues, AdminFrame adminframe) {
		String[] lines = new String[49];
		Date today = new Date();
		handler = new ConfigHandler();
		
		//	Standard text
		lines[0] = "";
		lines[1] = "_|    _|  _|           _|_|_|_|_|          _|  _|";
		lines[2] = "_|  _|          _|_|_|     _|      _|_|_|  _|  _|  _|";
		lines[3] = "_|_|      _|  _|_|         _|    _|    _|  _|  _|_|";
		lines[4] = "_|  _|    _|      _|_|     _|    _|    _|  _|  _|  _|";
		lines[5] = "_|    _|  _|  _|_|_|       _|      _|_|_|  _|  _|    _|";
		lines[6] = "";
		lines[7] = "";
		lines[8] = "    _      _      _      _      _";
		lines[9] = " __(')> __(')> __(')> __(')> __(^)<";
		lines[10] = " \\___)  \\___)  \\___)  \\___)  \\___)";
		lines[11] = "*'*'*'*'*'*'*Config.hans*'*'*'*'*'*'*";
		lines[12] = "*'*'* The official config file  *'*'*";
		lines[13] = "*'*'* of KisTalk. Please support*'*'*";
		lines[14] = "*'*'* our provider, Hans, by    *'*'*";
		lines[15] = "*'*'* sending a dollar to:      *'*'*";
		lines[16] = "*'*'*     Hans                  *'*'*";
		lines[17] = "*'*'*     17 Corey Dr Franklin  *'*'*";
		lines[18] = "*'*'*     TN 37067, USA         *'*'*";
		lines[19] = "*'*'* Mark your letter with     *'*'*";
		lines[20] = "*'*'* 'KisTalk-Hans'            *'*'*";
		lines[21] = "*'*'*                    //Hans *'*'*";
		lines[22] = "*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*";
		lines[23] = "*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*'*";
		lines[24] = "";
		lines[25] = "//	Date of last revision: " + today.getHours() + ":" + today.getMinutes() + " den " + today.getDate() + "/" + today.getMonth();
		lines[26] = "";
		lines[27] = "";
		lines[29] = "";
		lines[31] = "";
		lines[33] = "";
		lines[35] = "";
		lines[37] = "";
		lines[39] = "";
		lines[41] = "";
		lines[43] = "";
		lines[45] = "";
		lines[47] = "";
		
		//	Defined values
		lines[28] = "Max_number_of_Images %" + adminframe.nrOfImgsTxt.getText();
		lines[30] = "Number_of_Hans %-";
		lines[32] = "Timer_interval %" + adminframe.timeTxt.getText();
		lines[34] = "Number_of_Jimmys %-";
		lines[36] = "supported_image_formats %.jpg .png .gif .bnp";
		lines[38] = "Screen_index %" + confValues[5];
		lines[40] = "XMLURL %http://www.kistalk.com/desktop_images.xml";
		lines[42] = "Number_of_comments %2";
		lines[44] = "Path_to_Pubslides %" + adminframe.xmlPubPathTxt.getText(); //Ett \ tas bort var g�ng filen laddas?
		lines[46] = "Saved_Pubslides %" + confValues[9];
		lines[48] = "Saved_Paths %" + confValues[10];
		
		// Write to file (config)
		handler.setConfig(lines);
		
	}

	//	Resets Config to its standard state
	public String[] resetValues() { //Error
		handler = new ConfigHandler();
		
		//	Reset Config-file
		try {
			handler.resetConfig();
		} catch (IOException e) {
			System.out.println("Default config file has been removed or modified, Please contact Hans at <i>hans@kistalk.com</i>.");
		}
		
		// Reset 'confValues'
		getValues();
		return confValues;
//		nrOfImgsTxt.setText(confValues[0]);
//		timeTxt.setText(confValues[1]);
		
	}
}