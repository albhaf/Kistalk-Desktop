import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class ConfigHandler {
	public int nrOfConfValues;
	private File fFile = new File("Config.hans");
	
	//	Constructor
	public ConfigHandler(int tmp) {
		nrOfConfValues = tmp;
	}

	//	Gives Config new value/s
	public void setConfig(String[] tmp) {
		BufferedWriter out;
		StringBuffer sb = new StringBuffer();
		
		//	Append all incomming strings
		for (int i = 0; i < tmp.length; i++) {
			sb.append(tmp[i]);
			sb.append("\n");
		}

		//	Write to file
		try {
			out = new BufferedWriter(new FileWriter(fFile));			
			out.write(sb.toString());
			out.close();
		} catch (IOException e) {
			System.out.println("Could'nt write to file...");
		}
	}

	//	Resets config back to normal
	public void resetConfig() throws IOException {
		File f1 = new File("DefaultConfig.hans");
		File f2 = new File("Config.hans");
		InputStream in = new FileInputStream(f1);
		OutputStream out = new FileOutputStream(f2);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();

	}

	//	Returns all wanted values in lines, or parts of lines
	public String[] processLineByLine() throws FileNotFoundException{
		Scanner scanner = new Scanner(new FileReader(fFile));
		String[] lines = new String[nrOfConfValues];
		int i = 0;
		
		while(scanner.hasNextLine()){
			String tmp = processLine(scanner.nextLine());
			if (tmp != null){
				lines[i] = tmp;
				i++;
			}
		}
		
		return lines;
	}
	
	//	Returns a divided String
	public String processLine(String aLine){
		Scanner scanner = new Scanner(aLine);
		String part = null;
		
		scanner.useDelimiter("%");
		
		if (scanner.hasNext()){
		scanner.next();
			if(scanner.hasNext()){
				part = scanner.next();
			}
		}
		
		return part;
	}

}