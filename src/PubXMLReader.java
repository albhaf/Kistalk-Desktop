import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class PubXMLReader extends DefaultHandler{

	private List<PubTextXML> slides;
	private String XMLURL ="C:\\Users\\Ludvig\\workspace\\JavaDekstopAppny\\content.xml";
	
	
	private String tempVal;
	
	//to maintain context
	private PubTextXML tempPubText;
	
	public PubXMLReader(){

	
	}
	
	public List<PubTextXML> getPubTextInfo(){
		this.parseDocument();
		return slides;
	}

	public void parseDocument() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			//parse the file and also register this class for call backs
			sp.parse(XMLURL, this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	

	/**
	 * Iterate through the list and print
	 * the contents
	 */
/*	private void printData(){
		
		System.out.println("No of Images '" + images.size() + "'.");
		
		Iterator<ImageXML> it = images.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}*/
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase("text")) {
			//create a new instance of employee
			
			tempPubText = new PubTextXML();
			//tempEmp.setType(attributes.getValue("type"));
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("style:style")) {
		//	slides.add(tempPubText);
		}else if(qName.equalsIgnoreCase("Comment")){
		//	comments.add(tempComment);
/*		}else if(qName.equalsIgnoreCase("comment-user")){
			tempComment.setUser(tempVal);
		}else if(qName.equalsIgnoreCase("comment-content")){
			tempComment.setContent(tempVal);
		}else if(qName.equalsIgnoreCase("comment-created_at")){
			tempComment.setCreated(tempVal);
		}else if (qName.equalsIgnoreCase("URL-big")) {
			URL urli = null;

			
			try {
				urli = new URL(tempVal.replace(" ", "+"));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

				tempImg.setLink(urli);


		}else if (qName.equalsIgnoreCase("image-created_at")) {
			tempImg.setDate((tempVal));
		}else if (qName.equalsIgnoreCase("Image-User")) {
			tempImg.setUser((tempVal));
		}else if (qName.equalsIgnoreCase("text")){
			tempImg.setImageText(tempVal);
		}else if (qName.equalsIgnoreCase("image-description")){
			tempImg.setImageText(tempVal);*/
		}
		
	}
	
}