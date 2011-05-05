import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class ShowImagePicSet {
	private Dimension imageSize = new Dimension();
	private BufferedImage image;
	private Rectangle monitorSize;
	
	
	protected ShowImagePicSet(Rectangle monitorSize){
		this.image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		this.monitorSize = monitorSize;
	}
	
	protected float setImage(BufferedImage tmpImage, ImgRect imgRect,
			float imageStopPosition) {
		imgRect.resetPos();
		if (tmpImage.getWidth() / monitorSize.getWidth() < tmpImage.getHeight()
				/ monitorSize.getHeight()) {
			float factor = (float) (tmpImage.getWidth())
					/ (float) (tmpImage.getHeight());
			imgRect.height = imageSize.height;
			imgRect.width = imgRect.height * factor;
		} else {
			float factor = (float) (tmpImage.getHeight())
					/ (float) (tmpImage.getWidth());
			imgRect.width = imageSize.width;
			imgRect.height = imgRect.width * factor;
		}
		imgRect.addY(100);
		imgRect.addX(-200);
		imageStopPosition = ((monitorSize.width - imgRect.width
				- (monitorSize.width / 3) - 30));
		imageStopPosition = imageStopPosition - (imageStopPosition % 5);
		this.scalePositionSlide();
		return imageStopPosition;
		
		}

	protected float setSlide(BufferedImage tmpImage, ImgRect imgRect, float imageStopPosition) {

		imgRect.resetPos();

		float factor = (float) (tmpImage.getWidth())
				/ (float) (tmpImage.getHeight());
		imgRect.height = imageSize.height;
		imgRect.width = imgRect.height * factor;
		imgRect.setX(((int) monitorSize.getWidth() - tmpImage.getWidth()) / 4);
		imageStopPosition = ((monitorSize.width - imgRect.width
				- (monitorSize.width / 3) - 30));
		imageStopPosition = imageStopPosition - (imageStopPosition % 5);
		this.scalePositionPicture();
		return imageStopPosition;

	}
	
	private void scalePositionSlide() {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.01);
		imageSize.width = (int) (imageSize.height * ration);
	}
	
	private void scalePositionPicture() {
		double ration = image.getWidth() / image.getHeight();
		imageSize.height = (int) (image.getHeight() * monitorSize.height * 0.006);
		imageSize.width = (int) (imageSize.height * ration);
	}
}
