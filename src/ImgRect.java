import java.awt.geom.Rectangle2D;


public class ImgRect extends Rectangle2D.Float {
		/**
	 * 
	 */
		private static final long serialVersionUID = 9081629902069934605L;

		/**
		 * 
		 * @param x x-coordinate of the image.
		 * @param y images y-coordinate.
		 * @param width width of the image.
		 * @param height images height.
		 */
		public ImgRect(float x, float y, float width, float height) {
			setRect(x, y, width, height);
		}
		
		/**
		 * Adds specified value to current x-coordinates.
		 * @param x float to increase current x with, "-" to subtract x.
		 */
		public void addX(float x) {
			this.x += x;
		}

		/**
		 * Adds specified value to current y-coordinates.
		 * @param y float to increase current y with, "-" to subtract y.
		 */
		public void addY(float y) {
			this.y += y;
		}

		/**
		 * Adds specified value to current Width.
		 * @param Width float to increase current Width with, "-" to subtract Width.
		 */
		public void addWidth(float w) {
			this.width += w;
		}

		/**
		 * Adds specified value to current height value.
		 * @param height float to increase current height with, "-" to subtract height
		 */
		public void addHeight(float h) {
			this.height += h;
		}

		/**
		 * resets the position to (0,0).
		 */
		public void resetPos() {
			this.y = 0;
			this.x = 0;
		}
	}