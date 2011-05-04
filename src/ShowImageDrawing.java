import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class ShowImageDrawing {
	private Font commentFont, commentUser;
	private Font font;
	Graphics2D g2d;

	public ShowImageDrawing() {
		commentUser = new Font("Serig", Font.BOLD, 30);
		commentFont = new Font("Serif", Font.BOLD, 20);
		font = new Font("Serif", Font.BOLD, 50);
	}

	protected void drawBackground(Graphics g, Rectangle monitorSize,
			float transperacy) {
		g2d = (Graphics2D) g;
		g2d.setPaint(Color.BLACK);
		g2d.fillRect(0, 0, monitorSize.width, monitorSize.height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				transperacy));
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

	protected void drawComments(TextToDisplay[][] comments) {
		g2d.setFont(commentFont);
		g2d.setColor(Color.WHITE);

		if (comments != null) {

			for (int i = 0; i < comments.length; i++) {
				g2d.setFont(commentUser);
				g2d.drawString(comments[i][0].getString(), comments[i][0].x,
						comments[i][0].y);
				g2d.setFont(commentFont);
				g2d.drawString(comments[i][1].getString(), comments[i][1].x,
						comments[i][1].y);
			}
		}
	}
	protected void paintSlide(BufferedImage slideImage,
			ImgRect imgRect) {
		TexturePaint tp = new TexturePaint(slideImage, imgRect);
		g2d.setPaint(tp);
		g2d.fill(imgRect);
	}
	
	protected void paintImage(TextToDisplay imageCommentTxtDsp,
			TextToDisplay imageUserTxtDsp, BufferedImage slideImage,
			ImgRect imgRect) {
		// paints the image text and image user texts
		g2d.setFont(font);
		try {
			g2d.drawString(imageCommentTxtDsp.getString(),
					imageCommentTxtDsp.x, imageCommentTxtDsp.y);
			g2d.drawString(imageUserTxtDsp.getString(), imageUserTxtDsp.x,
					imageUserTxtDsp.y);
		} catch (NullPointerException e) {
		}

		// Paints the iamge rectangle
		TexturePaint tp = new TexturePaint(slideImage, imgRect);
		g2d.setPaint(tp);
		g2d.fill(imgRect);
	}

}
