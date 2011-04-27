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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConfigHandler {
	public int i = 0, j = 0;
	private File fFile;
	String fileName;

	//	Constructor
	public ConfigHandler(String fileNameLcl) {
		fFile = new File(fileNameLcl);
		fileName = fileNameLcl;
	}

	//	Counts the number of lines in a file
	public int countLines() {
		int nrOfLinesLocal = 0;
		Scanner scanner = null;

		try {
			scanner = new Scanner(fFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//	Count lines
		while (scanner.hasNextLine()) {
			scanner.nextLine();
			nrOfLinesLocal++;
		}
		return nrOfLinesLocal;

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
			System.out.println("Fel vid skrivning till fil...");
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

	//	Returns all wanted values in the form of lines, or parts of lines
	public List<String> processLineByLine(int type) throws FileNotFoundException{
		Scanner scanner = new Scanner(new FileReader(fFile));
		List<String> hans = new ArrayList<String>();
		
		if(type == 5){ //	Hämta allt
			hans.add(scanner.nextLine());
		}else{
			while(scanner.hasNextLine()){
				String[] tmp = processLine(scanner.nextLine());
				if (tmp[1].equals("-") == false){
					hans.add(tmp[type]);
				}
			}
			
		}
		
		
		return hans;
	}
	
	//	Returns a divided String
	public String[] processLine(String aLine){
		Scanner scanner = new Scanner(aLine);
		String[] value = new String[3];
		
		scanner.useDelimiter("%");
		
		if(scanner.hasNext()){
			try{
				value[0] = scanner.next();
				value[1] = scanner.next();
			}catch(NoSuchElementException e){
				value[1]="-";
			}
		}
		
		return value;
	}
	/*
	
	public final String[] processLineByLine1(String type)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader(fFile));
		String[] lineParts;
		String[] values = new String[countLines() + 1];
		int k = 0;

		if (type == "name") {

			while (scanner.hasNextLine()) {
				lineParts = processLine(scanner.nextLine());

				if (lineParts[0].equals("-") == false && lineParts[0].equals(null) == false) {
					values[k] = lineParts[0];
					k++;
				}

			}
			
		} else if (type == "content") {

			while (scanner.hasNextLine()) {
				lineParts = processLine(scanner.nextLine());

				if (lineParts[1].equals("-") == false && lineParts[1].equals(null) == false) {
					values[k] = lineParts[0];
					k++;
				}

			}

		} else if (type == "link") {
			while (scanner.hasNextLine()) {
				lineParts = processLine(scanner.nextLine());

				if (lineParts[2].equals("-") == false && lineParts[2].equals(null) == false) {
					values[k] = lineParts[1];
				}
				k++;
			}
			
		} else if (type == "all"){
			int l = 0;
			while (scanner.hasNextLine()) {
				values[l] = scanner.nextLine();
				l++;
			}
		}

		scanner.close();
		return values;

	}

	protected String[] processLine1(String aLine) {
		String[] lineParts;
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("%");

		if (scanner.hasNext() && fileName == "PubSlides.hans") {
			lineParts = new String[3];

			try {
				lineParts[0] = scanner.next();
				lineParts[1] = scanner.next();
				lineParts[2] = scanner.next();
			} catch (NoSuchElementException e) {

			}
		} else {
			lineParts = new String[2];

			try {
				lineParts[0] = scanner.next();
				lineParts[1] = scanner.next();
			} catch (NoSuchElementException e) {

			}
		}

		return lineParts;

	}
*/
}