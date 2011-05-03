import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLreader extends DefaultHandler{

	private List<ImageXML> images;
	private List<CommentXML> comments;
	private String XMLURL;

	private String tempVal;

	// to maintain context
	private ImageXML tempImg;
	private CommentXML tempComment;

	public XMLreader(String URL) {
		images = new ArrayList<ImageXML>();
		XMLURL = URL;
	}

	public List<ImageXML> getImagesInfo() {
		this.parseDocument();
		return images;
	}

	private void parseDocument() {
		// get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			// get a new instance of parser
			SAXParser sp = spf.newSAXParser();
			// parse the file and also register this class for call backs
			sp.parse(XMLURL + "?username=znorman&token=vqlcotvzuu", this);
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	// Event Handlers
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// reset
		tempVal = "";
		if (qName.equalsIgnoreCase("Image")) {
			// create a new instance of employee
			tempImg = new ImageXML();
			comments = new ArrayList<CommentXML>();
			// tempEmp.setType(attributes.getValue("type"));
		} else if (qName.equalsIgnoreCase("comment")) {
			tempComment = new CommentXML();
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("image")) {
			tempImg.setComments(comments);
			images.add(tempImg);
		} else if (qName.equalsIgnoreCase("comment")) {
			comments.add(tempComment);
		} else if (qName.equalsIgnoreCase("comment-user-name")) {
			tempComment.setUser(tempVal);
		} else if (qName.equalsIgnoreCase("comment-content")) {
			tempComment.setContent(tempVal);
		} else if (qName.equalsIgnoreCase("URL-big")) {
			URL urli = null;
			try {
				urli = new URL(tempVal.replace(" ", "+"));
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}

			tempImg.setLink(urli);

		} else if (qName.equalsIgnoreCase("image-created_at")) {
			tempImg.setDate((tempVal));
		} else if (qName.equalsIgnoreCase("image-user-name")) {
			tempImg.setUser((tempVal));
		} else if (qName.equalsIgnoreCase("text")) {
			tempImg.setImageText(tempVal);
		} else if (qName.equalsIgnoreCase("image-description")) {
			tempImg.setImageText(tempVal);
		}

	}

}