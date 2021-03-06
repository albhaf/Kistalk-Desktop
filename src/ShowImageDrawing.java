import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ShowImageDrawing {
	private Font commentFont, commentUser, font;
	private Graphics2D g2d;
	private float  transperancy;

	protected ShowImageDrawing() throws IOException {
		commentUser = new Font("Serig", Font.BOLD, 30);
		commentFont = new Font("Serif", Font.BOLD, 20);
		font = new Font("Serif", Font.BOLD, 50);
	}

	protected void drawBackground(Graphics g, Rectangle monitorSize,
			float transperacy) {
		this.transperancy = transperacy;
		g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, monitorSize.width, monitorSize.height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				transperancy));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
	}

	protected void drawComments(TextToDisplay[][] comments) {
		g2d.setFont(commentFont);
		g2d.setColor(Color.WHITE);
		if (comments != null) {
			for (int i = 0; i < comments.length; i++) {
				g2d.setFont(commentUser);

				if (comments[i][0] != null) {
					g2d.drawString(comments[i][0].getString(),
							comments[i][0].x, comments[i][0].y);
					g2d.setFont(commentFont);
					int j = 0;
					int lines = comments[i][0].getLines();
					do {
						j++;
						if (lines > j) {
							g2d.drawString(comments[i][j].getString(),
									comments[i][j].x, comments[i][j].y);
						} else {
							g2d.drawString(comments[i][j].getString(),
									comments[i][j].x, comments[i][j].y);
							break;
						}
					} while (true);
				}
			}
		}
	}

	protected void paintSlide(BufferedImage slideImage, ImgRect imgRect,BufferedImage logo, ImgRect logoRect) {
/*		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				1));
		TexturePaint tpl = new TexturePaint(logo, logoRect);
		g2d.setPaint(tpl);
		g2d.fill(logoRect);*/
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				transperancy));
		TexturePaint tp = new TexturePaint(slideImage, imgRect);
		g2d.setPaint(tp);
		g2d.fill(imgRect);

	}

	protected void paintImage(TextToDisplay[] imageCommentTxtDsp,
			TextToDisplay imageUserTxtDsp, BufferedImage slideImage,
			ImgRect imgRect, BufferedImage logo, ImgRect logoRect) {

		// paints the image text and image user texts

		try {


			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					transperancy));
			g2d.setFont(commentUser);
			int lines = imageCommentTxtDsp[0].getLines();
			for (int i = 0; i < lines; i++) {
				g2d.drawString(imageCommentTxtDsp[i].getString(),
						imageCommentTxtDsp[i].x, imageCommentTxtDsp[i].y);
			}
			g2d.setFont(font);
			g2d.drawString(imageUserTxtDsp.getString(), imageUserTxtDsp.x,
					imageUserTxtDsp.y);
		} catch (NullPointerException e) {
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
		1));
		TexturePaint tpl = new TexturePaint(logo, logoRect);
		g2d.setPaint(tpl);
		g2d.fill(logoRect);
		// Paints the iamge rectangle
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				transperancy));
		TexturePaint tp = new TexturePaint(slideImage, imgRect);
		g2d.setPaint(tp);
		g2d.fill(imgRect);
		

	}
}
