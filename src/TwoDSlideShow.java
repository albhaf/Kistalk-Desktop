import java.awt.*;

import javax.swing.*;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

@SuppressWarnings("serial")
public class TwoDSlideShow extends Panel implements ActionListener {

	Timer t;

	byte screenIndex;

	int nrOfPicsServer;
	int nrOfPicsPub;
	final int nrOfConfigLines = 10;
	String[] fileFormats = new String[4];
	ImageIcon[] iconArrayServer;
	ImageIcon[] iconArrayPub;
	Image[] serverImgs;
	URL[] urlArray;
	int currentPicture = 0;
	int currentPubPicture = 0;
	int nrOfComments = 0;
	int timeStill = 200;

	Graphics2D g2d;
	JPanel panel;
	XMLreader xmlreader;
	String xmlPath;
	List<ImageXML> imgXMLList;
	ShowImage slideShowHandler;

	int timestill = 100;

	Rectangle monitor = new Rectangle();
	TwoDSlideShowView view;
	TwoDSlideShowInfo info;

	public TwoDSlideShow() throws FileNotFoundException {
		view = new TwoDSlideShowView();
		info = new TwoDSlideShowInfo();
		readConfig();
		getScreenResolution();
		firstPicture();
		createFrame();
		t.start();
	}

	// Build the frame (Slideshow)
	public void createFrame() {
		slideShowHandler = new ShowImage((BufferedImage) serverImgs[0],
				imgXMLList.get(0).getUser(), imgXMLList.get(0).getImageText(),
				monitor, timeStill, imgXMLList.get(0).getComments());
		view.createFrame(slideShowHandler, monitor);

	}

	// Get the size of the monitor
	private void getScreenResolution() {
		monitor = info.getScreenSize(screenIndex);
	}

	private void readConfig() throws FileNotFoundException {
		int value = info.readConfig(this);
		t = new Timer(value, this);
	}

	private void firstPicture() {
		xmlreader = new XMLreader(xmlPath);
		info.setLinks(this);
		info.setPicture(this);
	}

	private void updatePicture() {

		if (currentPicture >= nrOfPicsServer) {
			info.setLinks(this);
			xmlreader = new XMLreader(xmlPath);
			currentPicture = 0;			
		}
		info.setPicture(this);
		slideShowHandler.UpdatePicture(
				(BufferedImage) serverImgs[currentPicture - 1], imgXMLList
						.get(currentPicture - 1).getUser(), imgXMLList.get(
						currentPicture - 1).getImageText(), imgXMLList.get(
						currentPicture - 1).getComments());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t) {

			slideShowHandler.MoveObjects();
			if (slideShowHandler.getSlideImageX() > monitor.width) {
				updatePicture();
			}
		}

	}

	/*
	 * public static void copyPptPics() throws IOException{
	 * 
	 * ZipFile zf = new ZipFile("C:\\Users\\Ludvig\\Documents\\asd.odp");
	 * Enumeration<? extends ZipEntry> files = zf.entries();
	 * 
	 * while (files.hasMoreElements()) { ZipEntry ze = files.nextElement();
	 * 
	 * System.out.println("Decompressing " + ze.getName());
	 * System.out.println("  Compressed Size: " + ze.getCompressedSize() +
	 * "  Expanded Size: " + ze.getSize() + "\n"); if(ze.isDirectory()==false){
	 * BufferedInputStream fin = new BufferedInputStream(zf.getInputStream(ze));
	 * BufferedOutputStream fout = new BufferedOutputStream(
	 * 
	 * new FileOutputStream(ze.getName()));
	 * 
	 * int i; do { i = fin.read(); if (i != -1) fout.write(i); } while (i !=
	 * -1);
	 * 
	 * fout.close(); fin.close(); } } zf.close(); }
	 */

	public static void main(String args[]) throws FileNotFoundException {
		new TwoDSlideShow();
	}
}
