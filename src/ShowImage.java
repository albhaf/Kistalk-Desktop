import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class ShowImage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 887346353483336091L;

	// Variables
	private ImgRect Rect;
	private TextDisplay imageUserTxtDsp;
	private TextDisplay imageCommentTxtDsp;
	public TextDisplay[] imageComments;
	private boolean outgoing = false;
	BufferedImage slideImage;
	float tp1 = 0;
	// private int space;

	// Variabler som sätts i konstruktorn
	private Rectangle monitorSize;
	private Dimension textStartPosition = new Dimension(); // width == Ypos,
															// height == Xpos.
	private int correction;
	private BufferedImage image;
	private Dimension timeStill = new Dimension(); // height == time to stand
													// still, width == time for
													// current image
	private float imageStopPosition;
	private Dimension imageSize = new Dimension(); // width == image size x,
													// height == image size y.

	// Konstanter

	private final int ImageUserFontSize = 50;
	Font font = new Font("Serif", Font.BOLD, 50);
	Font commentfont = new Font("Serif", Font.BOLD, 30);

	// FontMetrics fontmetrics;

	public ShowImage(BufferedImage tmp, String tmpImageUserString,
			String tmpImageCommentString, Rectangle tmpmonitor,
			int tmpTimeStill, List<CommentXML> ImageComments) {

		monitorSize = tmpmonitor;

		float factor = (float) (tmp.getWidth()) / (float) (tmp.getHeight());

		image = new BufferedImage(100, /* (int)(100*factor) */100,
				BufferedImage.TYPE_INT_RGB);

		timeStill.width = tmpTimeStill;
		timeStill.height = timeStill.width;

		correction = (tmpImageUserString.length() + 6) / 2;

		// Image comments
		// imageComments = new TextDisplay[ImageComments.size()];
		// for(int i =0;i<imageComments.length;i++){
		if (ImageComments.size() > 0) {
			imageComments = new TextDisplay[ImageComments.size()];
			for (int i = 0; i < imageComments.length; i++) {
				imageComments[i] = new TextDisplay(10, 10, 1000, 100);
				imageComments[i].setString(ImageComments.get(i).getContent());
				tp1 = 0;
				imageComments[i].addX(1000);
				imageComments[i].addY(200 + (i * 100));
			}
			// System.out.println(ImageComments.get(i).getContent());
			// }
		}
		// ImageCommentTxt setup
		imageCommentTxtDsp = new TextDisplay(100, 1000, 2, 1);
		imageCommentTxtDsp.setString(tmpImageCommentString);

		// ImageUser setup
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 2));
		imageUserTxtDsp = new TextDisplay(textStartPosition.width, 0, 2, 1);
		imageUserTxtDsp.setString(tmpImageUserString + " posted: ");

		scalePicturesText();

		slideImage = tmp;

		imageStopPosition = (monitorSize.width / 2) - (2 * imageSize.width / 3);
		imageStopPosition = imageStopPosition - (imageStopPosition % 5);
		setDoubleBuffered(true);
		Rect = new ImgRect(-200, 100, imageSize.width * factor,
				imageSize.height);
	}

	public void scalePicturesText() {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.005);
		imageSize.width = (int) (imageSize.height * ration);
		correction = (imageUserTxtDsp.length() + 6) / 2;
		textStartPosition.width = (monitorSize.width / 2)
				- (correction * (ImageUserFontSize / 3));
	}

	public void UpdatePicture(BufferedImage tmp, String tmpUserString,
			String tmpImageText, List<CommentXML> ImageComments) {
		// Bilden
		slideImage = tmp;
		Rect.resetPos();
		// Rect.addHeight(h);

		float factor = (float) (tmp.getWidth()) / (float) (tmp.getHeight());
		Rect.height = imageSize.height;
		Rect.width = Rect.height * factor;
		;
		Rect.addY(100);
		Rect.addX(-200);

		// bild kommentarerna
		// imageComments = new TextDisplay[ImageComments.size()];
		// for(int i =0;i<imageComments.length;i++){
		if (ImageComments.size() > 0) {
			imageComments = new TextDisplay[ImageComments.size()];

			for (int i = 0; i < imageComments.length; i++) {
				String bla = (ImageComments.get(i).getUser() + " wrote: " + ImageComments
						.get(i).getContent());
				imageComments[i] = new TextDisplay(100, 100, 1000, 100);
				imageComments[i].setString(bla);
				imageComments[i].resetPos();
				imageComments[i].addX(1000);
				imageComments[i].addY(200 + (i * 100));
			}
		} else {
			imageComments = null;
		}

		tp1 = 0;
		// imageComments[0].setString( ImageComments.get(0).getContent());
		// }

		// Bildtexten
		imageCommentTxtDsp.setString(tmpImageText);
		imageCommentTxtDsp.resetPos();
		imageCommentTxtDsp.addX(100);
		imageCommentTxtDsp.addY(monitorSize.height + ImageUserFontSize);

		// Image user
		imageUserTxtDsp.setString(tmpUserString + " posted: ");
		imageUserTxtDsp.resetPos();
		scalePicturesText();
		imageUserTxtDsp.addX(textStartPosition.width);
		imageUserTxtDsp.addY(0);

		// Nollst�llning av variabler
		outgoing = false;
		timeStill.height = timeStill.width;
	}

	public void changeDirection() {
		outgoing = true;
	}

	public void MoveObjects() {
		if (timeStill.height != 0 && Rect.getX() == (int) imageStopPosition) {
			timeStill.height = timeStill.height - 1;
		} else {
			Rect.addX(5);
		}

		if (outgoing == false) {
			// if(imageComments[0]!=null){
			if (tp1 < 1) {
				tp1 = (float) (tp1 + 0.008);
			}
			if (tp1 > 1) {
				tp1 = 1;
				// }
			}
		} else if (outgoing == true) {
			if (tp1 > 0.01) {
				tp1 = (float) (tp1 - 0.008);
			}
			if (tp1 < 0.01) {
				tp1 = (float) 0.01;
			}
		}

		if (timeStill.height == 0) {
			outgoing = true;
		}
		// bild user r;relse
		if (outgoing == true) {
			imageUserTxtDsp.addY(-1);
		} else if (outgoing == false && imageUserTxtDsp.getY() < 80) {
			imageUserTxtDsp.addY(1);
		}
		// bildtext r;relse
		if (imageCommentTxtDsp.getY() > 750 && outgoing == false) {
			imageCommentTxtDsp.addY(-2);
		} else if (outgoing == true) {
			imageCommentTxtDsp.addY(2);
		}
		repaint();
	}

	public double getTextX() {
		return imageUserTxtDsp.getX();
	}

	public double getTxtY() {
		return imageUserTxtDsp.getY();
	}

	public double getSlideImageX() {
		return Rect.getX();
	}

	public double getSlideImageY() {
		return Rect.getX();
	}

	public void paint(Graphics g) {
		/*
		 * int fh; int ascent;
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, 1440, 900);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				tp1));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setFont(commentfont);
		g2d.setColor(Color.WHITE);

		if (imageComments != null) {

			for (int i = 0; i < imageComments.length; i++) {
				g2d.drawString(imageComments[i].getString(),
						imageComments[i].x, imageComments[i].y);
			}
		}
		g2d.setFont(font);
		g2d.drawString(imageCommentTxtDsp.getString(), imageCommentTxtDsp.x,
				imageCommentTxtDsp.y);
		g2d.drawString(imageUserTxtDsp.getString(), imageUserTxtDsp.x,
				imageUserTxtDsp.y);
		// for(int i =0;i<imageComments.length;i++){

		// }
		TexturePaint tp = new TexturePaint(slideImage, Rect);
		g2d.setPaint(tp);
		g2d.fill(Rect);
		// g2d.setColor(Color.WHITE);

	}

	class TextDisplay extends Rectangle2D.Float {

		/*
		 * 
		 */
		private static final long serialVersionUID = 2750632610482025427L;
		private String text;

		public TextDisplay(float x, float y, float width, float height) {
			setRect(x, y, width, height);
		}

		public void setString(String tmp) {
			/*
			 * if(tmp.length()>20){ tmp=new StringBuffer(tmp).insert(20,
			 * "<BR>").toString(); }
			 */
			this.text = tmp;
		}

		public int length() {
			return text.length();
		}

		public String getString() {
			return text;
		}

		public void addX(float x) {
			this.x += x;
		}

		public void addY(float y) {
			this.y += y;
		}

		public void addWidth(float w) {
			this.width += w;
		}

		public void addHeight(float h) {
			this.height += h;
		}

		public void resetPos() {
			this.y = 0;
			this.x = 0;
		}

	}

	class ImgRect extends Rectangle2D.Float {
		/**
	 * 
	 */
		private static final long serialVersionUID = 9081629902069934605L;

		public ImgRect(float x, float y, float width, float height) {
			setRect(x, y, width, height);
		}

		public void addX(float x) {
			this.x += x;
		}

		public void addY(float y) {
			this.y += y;
		}

		public void addWidth(float w) {
			this.width += w;
		}

		public void addHeight(float h) {
			this.height += h;
		}

		public void resetPos() {
			this.y = 0;
			this.x = 0;
		}
	}
}