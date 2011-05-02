import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImportPubSlides {
	private int nrFiles;
	private String path;
	private String[] files;
	private int fileNr;
	PptToPng pptToPng;

	/**
	 * constructor which takes the directory path to the file(s)  as input argument.
	 * @param tmpPath String, filepath to the pubslides in image format.
	 */
	public ImportPubSlides(String tmpPath){
		path = tmpPath;
		fileNr = 0;
		countFiles();
		pptToPng = new PptToPng(tmpPath);
		pptToPng.extractWoScale();
	}
	
	public ImportPubSlides(){
		path = null;
		nrFiles = 0;
	}
		
	/**
	 * Method for setting the path to the directory containing the file(s).
	 * @param tmpPath String, directory path.
	 */
	public void setPath(String tmpPath){
		path = tmpPath;
		files = null;
		nrFiles=0;
	}
	
	/**
	 * Returns the path to the directory which contains the files.
	 * @return String, directory path.
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Method for counting the files in the directory
	 */
	public void countFiles(){
		files = new File(path).list();
		nrFiles = files.length;
	}

	/**
	 * Returns the number of files found in the set directory.
	 * @return int, number of files found
	 */
	public int getNrFiles(){
		return nrFiles;
	}
	
	/**
	 * Methoed which loads one picture in the selected directory and returns it as an Image.
	 * @param fileNr int, which file in the array of found files.
	 * @return Image, returns the chosen file as Image.
	 * @throws IOException Throws IOException if the file couldn't be found.
	 */
	public BufferedImage getImage() throws IOException{
		Image img = ImageIO.read(new File(files[fileNr]));
		if(fileNr >= getNrFiles())
			fileNr = 0;
		else
			fileNr++;
		return (BufferedImage) img;
	}
	/*
	public void delIni(){
		File path = new File("C:\\images");
		deleteDirectory(path);
	}
	
	public boolean deleteDirectory(File path){

		if( path.exists() ) {
		      File[] files = path.listFiles();
		      for(int i=0; i<files.length; i++) {
		         if(files[i].isDirectory()) {
		           deleteDirectory(files[i]);
		         }
		         else {
		           files[i].delete();
		         }
		      }
		    }
		    return( path.delete() );
		  }*/
	}
	
	

