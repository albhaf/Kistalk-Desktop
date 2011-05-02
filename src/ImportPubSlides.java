import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImportPubSlides {
	PptToPng pptToPng;

	/**
	 * constructor which takes the directory path to the file(s)  as input argument.
	 * @param tmpPath String, filepath to the pubslides in image format.
	 */
	public ImportPubSlides(String tmpPath){
		pptToPng = new PptToPng(tmpPath);
		pptToPng.extract();
	}

	
	/**
	 * Method for counting the files in the directory
	 */

	/**
	 * Returns the number of files found in the set directory.
	 * @return int, number of files found
	 */
	
	public int countFiles(){
		return pptToPng.getNrFiles();

	}
	
	/**
	 * Methoed which loads one picture in the selected directory and returns it as an Image.
	 * @param fileNr int, which file in the array of found files.
	 * @return Image, returns the chosen file as Image.
	 * @throws IOException Throws IOException if the file couldn't be found.
	 */
	public BufferedImage getImage(int i) throws IOException{
		Image img = ImageIO.read(new File("slide-" + i + ".hansimage"));
		return (BufferedImage) img;
	}
	
	}
	
	

