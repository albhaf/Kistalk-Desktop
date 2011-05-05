import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;


public class ConfigSettings {
	final int nrOfConfigValues = 11; // Doesn't include regular textlines. If change, then change configHandler too
	
	ConfigHandler handler;
	
	
	//	Reads Config and saves values in 'confValues'
	public String[] getValues() {
		String[] values = new String[nrOfConfigValues];
		handler = new ConfigHandler();
		
		try {
			values = handler.processLineByLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return values;
		
	}

	//	Saves current settings to Config (except Default Turtle, he only lives in config when config is defaultahrized)
	@SuppressWarnings("deprecation")
	public void setValues(String[] values) {
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
		lines[28] = "Max_number_of_Images %" + values[0];
		lines[30] = "Number_of_Hans %-";
		lines[32] = "Timer_interval %" + values[2];
		lines[34] = "Fading_speed %" + values[3];
		lines[36] = "supported_image_formats %.jpg .png .gif .bnp";
		lines[38] = "Screen_index %" + values[5];
		lines[40] = "XMLURL %http://www.kistalk.com/api/feed/desktop.xml";
		lines[42] = "Number_of_comments %" + values[7];
		lines[44] = "Path_to_Pubslides %" + values[8];
		lines[46] = "Saved_Pubslides %" + values[9];
		lines[48] = "Saved_Paths %" + values[10];
		
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
		
		return getValues();
		
	}
}
